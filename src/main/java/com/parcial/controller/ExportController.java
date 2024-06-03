package com.parcial.controller;

import com.parcial.service.ExcelService;

import jakarta.servlet.http.HttpServletResponse;

import com.parcial.entidades.Estudiante;
import com.parcial.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class ExportController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/export/excel")
    public StreamingResponseBody exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=estudiantes.xlsx");

        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return outputStream -> {
            ByteArrayInputStream excelFile = excelService.exportEstudiantesToExcel(estudiantes);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = excelFile.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        };
    }
}