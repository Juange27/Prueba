package com.parcial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompartidoController {

	@GetMapping("/")
	public String compartidoIndexTemplate(Model model) {
		return "index-compartido";
	}
}

