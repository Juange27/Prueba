package com.parcial.controller;

import com.parcial.entidades.Ventas;
import com.parcial.repository.VentasRepository;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsultaController {

    private final VentasRepository ventasRepository;

    public ConsultaController(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @GetMapping("/consultar-ventas")
    public String consultarVentas(Model model) {
        List<Ventas> ventasList = ventasRepository.findAll();
        model.addAttribute("ventas", ventasList);
        return "consultar-ventas";
    }
}
