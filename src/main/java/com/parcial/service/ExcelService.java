package com.parcial.service;

import com.parcial.entidades.Estudiante;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public ByteArrayInputStream exportEstudiantesToExcel(List<Estudiante> estudiantes) throws IOException {
        String[] columns = {"CC", "Nombre", "Apellido", "Registro", "Correo", "Telefono", "ComunE", "RazoC", "LeC", "CompC", "Ingl", "FormProyectos", "PenCientifico", "Diseño"};

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Estudiantes");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Crear la fila de encabezado
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Crear las filas de datos
            int rowIdx = 1;
            for (Estudiante estudiante : estudiantes) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(estudiante.getCc());
                row.createCell(1).setCellValue(estudiante.getNombre());
                row.createCell(2).setCellValue(estudiante.getApellido());
                row.createCell(3).setCellValue(estudiante.getRegistro());
                row.createCell(4).setCellValue(estudiante.getCorreo());
                row.createCell(5).setCellValue(estudiante.getTelefono());
                row.createCell(6).setCellValue(estudiante.getComunE());
                row.createCell(7).setCellValue(estudiante.getRazoC());
                row.createCell(8).setCellValue(estudiante.getLeC());
                row.createCell(9).setCellValue(estudiante.getCompC());
                row.createCell(10).setCellValue(estudiante.getIngl());
                row.createCell(11).setCellValue(estudiante.getFormProyectos());
                row.createCell(12).setCellValue(estudiante.getPenCientifico());
                row.createCell(13).setCellValue(estudiante.getDiseño());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}