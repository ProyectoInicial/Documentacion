package com.loam.framework.core.logs.persistence.common;

public class ExcepcionGenerica {
	protected String codigo;
	protected String componente;
	protected String metodo;
	protected String descripcion;

	public ExcepcionGenerica(String codigo,String componente, String metodo, String descripcion){
		this.codigo=codigo;
		this.componente=componente;
		this.metodo=metodo;
		this.descripcion=descripcion;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
