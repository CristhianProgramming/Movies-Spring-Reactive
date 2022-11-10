package com.cristhian.springboot.webflux.webclient.app.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Movies {
	
	private String id;
	
	private String nombre;
	
	private Integer visitas = 0;
	
	private Date fechaCreacion;
	
	private Date fechaModificacion;
	

	public Movies(String nombre, Categoria categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
	}

	private Categoria categoria;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getVisitas() {
		return visitas;
	}
	
	public void setVisitas(Integer visitas) {
		this.visitas = visitas;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
	
	//private String imagen;
	
}
