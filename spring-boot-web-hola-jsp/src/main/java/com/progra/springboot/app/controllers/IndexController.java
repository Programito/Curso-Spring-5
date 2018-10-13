package com.progra.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;

@Controller
public class IndexController {
	
	@Value("${application.controller.titulo}")
	private String titulo;
	
	// Model permite pasar atributos a la vista
	@GetMapping("/index")
	public String index(Model model) {
		// model.addAttribute("titulo","Hola Spring Boot!");
		model.addAttribute("titulo",this.titulo);
		return "index";
	}

}
