package com.loam.framework.core.logs.persistence.common;

public class EstadoRespuestaVo {
	protected String IdOperacion;
	protected String metodo;
	protected String clase;
	protected String mensajeDetallado;
	protected Integer nivelSegRequerido;

	public String getIdOperacion() {
		return IdOperacion;
	}

	public void setIdOperacion(String idOperacion) {
		IdOperacion = idOperacion;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getMensajeDetallado() {
		return mensajeDetallado;
	}

	public void setMensajeDetallado(String mensajeDetallado) {
		this.mensajeDetallado = mensajeDetallado;
	}

	public Integer getNivelSegRequerido() {
		return nivelSegRequerido;
	}

	public void setNivelSegRequerido(Integer nivelSegRequerido) {
		this.nivelSegRequerido = nivelSegRequerido;
	}

}
