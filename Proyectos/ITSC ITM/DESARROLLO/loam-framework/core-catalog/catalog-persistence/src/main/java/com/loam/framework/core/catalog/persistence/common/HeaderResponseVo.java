package com.loam.framework.core.catalog.persistence.common;

import java.util.Date;

public class HeaderResponseVo {
	protected String id;
	protected String estatus;
	protected String mensaje;
	protected EstadoRespuestaVo estadoRespuesta;
	protected String tokenOperacion;
	protected Date fechaHora;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public EstadoRespuestaVo getEstadoRespuesta() {
		return estadoRespuesta;
	}

	public void setEstadoRespuesta(EstadoRespuestaVo estadoRespuesta) {
		this.estadoRespuesta = estadoRespuesta;
	}

	public String getTokenOperacion() {
		return tokenOperacion;
	}

	public void setTokenOperacion(String tokenOperacion) {
		this.tokenOperacion = tokenOperacion;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

}
