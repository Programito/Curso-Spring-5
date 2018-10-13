package com.progra.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
	
	@Value("${appplication.controllers.mensaje}")
	private String mensaje;
	
	@GetMapping("/")
	public String hola(Model model) {
		//model.addAttribute("mensaje","hola que tal! Spring Boot!");
		model.addAttribute("mensaje", mensaje);
		return "hola";
	}
}
