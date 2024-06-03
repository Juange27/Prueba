package com.parcial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.parcial.entidades.Ventas;

public interface VentasRepository  extends MongoRepository<Ventas, String> {

}
