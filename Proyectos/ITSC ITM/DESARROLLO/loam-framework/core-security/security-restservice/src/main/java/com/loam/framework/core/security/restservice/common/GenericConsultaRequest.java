package com.loam.framework.core.security.restservice.common;

public class GenericConsultaRequest {
	protected long id;
	protected long idSub;
	protected int idStart;
	protected int idEnd;
	protected String ordenar;
	protected String descripcion;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdSub() {
		return idSub;
	}

	public void setIdSub(long idSub) {
		this.idSub = idSub;
	}

	public int getIdStart() {
		return idStart;
	}

	public void setIdStart(int idStart) {
		this.idStart = idStart;
	}

	public int getIdEnd() {
		return idEnd;
	}

	public void setIdEnd(int idEnd) {
		this.idEnd = idEnd;
	}

	public String getOrdenar() {
		return ordenar;
	}

	public void setOrdenar(String ordenar) {
		this.ordenar = ordenar;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
