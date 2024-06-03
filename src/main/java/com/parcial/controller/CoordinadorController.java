package com.parcial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parcial.entidades.Coordinador;
import com.parcial.repository.CoordinadorRepository;

@RestController
@RequestMapping("/Coordinador")

public class CoordinadorController {


	@Autowired
    private CoordinadorRepository coordinadorRepository;

    

    @PostMapping("")
    public ResponseEntity<?> guardarCoordinador(@RequestBody Coordinador coordinador) {
        try {
            Coordinador saveCoordinador = coordinadorRepository.save(coordinador);
            return new ResponseEntity<Coordinador>(saveCoordinador, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   

    

}
