package com.parcial.controller;




import com.parcial.entidades.Ventas;
import com.parcial.repository.VentasRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller

public class VentasController {


	 @Autowired
	    private VentasRepository ventasRepository;

	    @PostMapping("/guardar-ventas-excel")
	    public String guardarVentasDesdeExcel(@RequestParam("file") MultipartFile file, Model model) {
	        if (file.isEmpty()) {
	            model.addAttribute("message", "Por favor seleccione un archivo para subir.");
	            return "subir";
	        }

	        List<List<String>> datos = new ArrayList<>();
	        try {
	            Workbook workbook = new XSSFWorkbook(file.getInputStream());
	            Sheet sheet = workbook.getSheetAt(0);

	            for (Row row : sheet) {
	                if (row.getRowNum() == 0) {
	                    continue; // Saltar la primera fila (encabezados)
	                }

	                List<String> fila = new ArrayList<>();
	                Ventas venta = new Ventas();
	                for (Cell cell : row) {
	                    fila.add(cell.toString());
	                }
	                datos.add(fila);

	                // Asignar valores a la entidad Ventas
	                venta.setId(fila.get(0));
	                venta.setValor(Double.parseDouble(fila.get(1)));
	                venta.setTipoEquipo(fila.get(2));
	                venta.setEstado(fila.get(3));
	                venta.setUsuario(fila.get(4));
	                venta.setCliente(fila.get(5));

	                ventasRepository.save(venta);
	                System.out.println("Venta guardada: " + venta); // Log para verificar
	            }

	            model.addAttribute("datos", datos);
	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            model.addAttribute("message", "Error al procesar el archivo.");
	            return "subir";
	        }

	        return "redirect:/consultar-ventas"; // Redirigir a la página de consulta de ventas
	    }
	}
