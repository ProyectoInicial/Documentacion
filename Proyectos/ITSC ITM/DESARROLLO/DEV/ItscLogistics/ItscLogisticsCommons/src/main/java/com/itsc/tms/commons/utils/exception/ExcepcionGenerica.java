package com.itsc.tms.commons.utils.exception;

public class ExcepcionGenerica {
	protected String id;
	protected String mensaje;
	protected String codigo;
	protected String componente;
	protected String metodo;
	protected String descripcion;

	public ExcepcionGenerica() {
	}

	public ExcepcionGenerica(String id, String mensaje, String codigo, String componente, String metodo,
			String descripcion) {
		this.id = id;
		this.mensaje = mensaje;
		this.codigo = codigo;
		this.componente = componente;
		this.metodo = metodo;
		this.descripcion = descripcion;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
