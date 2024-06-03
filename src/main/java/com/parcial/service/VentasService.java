package com.parcial.service;

import com.parcial.entidades.Ventas;
import com.parcial.repository.VentasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasService {

    @Autowired
    private VentasRepository ventasRepository;

    public List<Ventas> obtenerTodasVentas() {
        return ventasRepository.findAll();
    }

    public Ventas guardarVenta(Ventas ventas) {
        return ventasRepository.save(ventas);
    }

    public void eliminarVenta(String id) {
        ventasRepository.deleteById(id);
    }

    public Ventas obtenerVentaPorId(String id) {
        return ventasRepository.findById(id).orElse(null);
    }

    // Puedes agregar más métodos según las necesidades de tu aplicación
}
