package com.parcial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parcial.entidades.Estudiante;
import com.parcial.repository.EstudianteRepository;



@RestController
@RequestMapping("/Estudiante")

public class EstudianteController {

	

	@Autowired
    private EstudianteRepository estudianteRepository;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstudiante(@PathVariable("id") String id) {
        try {
            estudianteRepository.deleteById(id);
            return new ResponseEntity<String>("Registro eliminado", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> guardarEstudiante(@RequestBody Estudiante estudiante) {
        try {
            Estudiante saveEstudiante = estudianteRepository.save(estudiante);
            return new ResponseEntity<Estudiante>(saveEstudiante, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> listarTodosEstudiantes() {
        try {
            List<Estudiante> estudiantes = estudianteRepository.findAll();
            return new ResponseEntity<List<Estudiante>>(estudiantes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarUnEstudiante(@PathVariable String id) {
        try {
            Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id);
            if (estudianteOptional.isPresent()) {
                Estudiante estudiante = estudianteOptional.get();
                return ResponseEntity.ok(estudiante);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateEstudiante(@RequestBody Estudiante estudiante) {
        try {
            Optional<Estudiante> estudianteOptional = estudianteRepository.findById(estudiante.getId());
            if (estudianteOptional.isPresent()) {
                Estudiante estudianteSave = estudianteRepository.save(estudiante);
                return ResponseEntity.ok(estudianteSave);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El estudiante no se encontró");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().toString());
        }
    }
    
    
}
