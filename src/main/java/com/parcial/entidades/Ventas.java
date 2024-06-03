package com.parcial.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "ventas")
public class Ventas {

	
	 @Id
	    private String id;
	    private double valor;
	    private String tipoEquipo;
	    private String estado;
	    private String usuario;
	    private String cliente;

	    // Getters y setters
	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public double getValor() {
	        return valor;
	    }

	    public void setValor(double valor) {
	        this.valor = valor;
	    }

	    public String getTipoEquipo() {
	        return tipoEquipo;
	    }

	    public void setTipoEquipo(String tipoEquipo) {
	        this.tipoEquipo = tipoEquipo;
	    }

	    public String getEstado() {
	        return estado;
	    }

	    public void setEstado(String estado) {
	        this.estado = estado;
	    }

	    public String getUsuario() {
	        return usuario;
	    }

	    public void setUsuario(String usuario) {
	        this.usuario = usuario;
	    }

	    public String getCliente() {
	        return cliente;
	    }

	    public void setCliente(String cliente) {
	        this.cliente = cliente;
	    }
	}