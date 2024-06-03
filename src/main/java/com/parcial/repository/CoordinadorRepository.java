package com.parcial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.parcial.entidades.Coordinador;

public interface CoordinadorRepository extends MongoRepository<Coordinador, String>{

}
