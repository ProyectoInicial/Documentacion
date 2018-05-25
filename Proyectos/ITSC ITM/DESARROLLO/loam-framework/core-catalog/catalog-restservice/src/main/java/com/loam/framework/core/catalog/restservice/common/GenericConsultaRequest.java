package com.loam.framework.core.catalog.restservice.common;

import java.io.Serializable;

public class GenericConsultaRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	protected long id;
	protected long idSub;
	protected int idStart;
	protected int idEnd;
	protected int statusFlag;
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

	public int getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(int statusFlag) {
		this.statusFlag = statusFlag;
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
