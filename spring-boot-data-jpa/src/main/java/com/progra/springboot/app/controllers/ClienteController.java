package com.progra.springboot.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
// cuando tienes varias implemenaciones y las nombras
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

// import com.progra.springboot.app.models.dao.IClienteDao;
import com.progra.springboot.app.models.entity.Cliente;
import com.progra.springboot.app.models.service.IClienteService;
import com.progra.springboot.app.models.service.IUploadFileService;
import com.progra.springboot.app.utill.paginator.PageRender;

// el session atributes es mas seguro que ocultar el id en html
@Controller
//@SessionAttributes("cliente")
public class ClienteController {

	// qualifier es para indicar cual es implementacion de la interficie por si
	// tiene varias
//	@Autowired
//	@Qualifier("clienteDaoJPA")
//	private IClienteService clienteService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IUploadFileService uploadFileService;

	// .+ para poner la extension
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliene no existe en la base de datos");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Detalle cliente:  " + cliente.getNombre());
		return "ver";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);

		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);

		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteService.findAll());

		model.addAttribute("page", pageRender);
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, @RequestParam("file") MultipartFile foto,
			Model model, RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario Cliente");
			return "form";
		}
		if (!foto.isEmpty()) {
			// Path directorioRecursos= Paths.get("src//main//ressources//static/upload");
			// String rootPath= directorioRecursos.toFile().getAbsolutePath();

			// Path fuera del proyecto
			// String rootPath="C:\\uploads";

			if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null
					&& cliente.getFoto().length() > 0) {
				uploadFileService.delete(cliente.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
			cliente.setFoto(uniqueFilename);

		}
		String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con éxito" : "Cliente creado con éxito";
		clienteService.save(cliente);
		// elimina el objeto cliente de la sesion
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

	// PathVariable inyecta el valor del parametro de la ruta
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {

			Cliente cliente = clienteService.findOne(id);

			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito");

			if (uploadFileService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("info", "Foto " + cliente.getFoto() + " eliminada con exito!");
			}
		}

		return "redirect:/listar";
	}

}
