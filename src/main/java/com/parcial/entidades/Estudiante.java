package com.parcial.entidades;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Estudiante")
public class Estudiante {

	
	
	
	 @Id
	    private String id = ObjectId.get().toString(); // Inicializa el id con un valor generado automáticamente
	
	int cc;
	String nombre;
	String apellido;
	String registro;
	String correo;
	String telefono;
	int ComunE;
	int RazoC;
	int LeC;
	int CompC;
	int Ingl;
	int FormProyectos;
	int PenCientifico;
	int Diseño;
	private String usuario;
	private String contrasena;

	
	   

	    
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public int getComunE() {
		return ComunE;
	}
	public void setComunE(int comunE) {
		ComunE = comunE;
	}
	public int getRazoC() {
		return RazoC;
	}
	public void setRazoC(int razoC) {
		RazoC = razoC;
	}
	public int getLeC() {
		return LeC;
	}
	public void setLeC(int leC) {
		LeC = leC;
	}
	public int getCompC() {
		return CompC;
	}
	public void setCompC(int compC) {
		CompC = compC;
	}
	public int getIngl() {
		return Ingl;
	}
	public void setIngl(int ingl) {
		Ingl = ingl;
	}
	public int getFormProyectos() {
		return FormProyectos;
	}
	public void setFormProyectos(int formProyectos) {
		FormProyectos = formProyectos;
	}
	public int getPenCientifico() {
		return PenCientifico;
	}
	public void setPenCientifico(int penCientifico) {
		PenCientifico = penCientifico;
	}
	public int getDiseño() {
		return Diseño;
	}
	public void setDiseño(int diseño) {
		Diseño = diseño;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
	
	
	
}
