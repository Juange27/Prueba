package com.parcial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.parcial.entidades.Estudiante;

public interface EstudianteRepository extends MongoRepository<Estudiante, String> {

}
