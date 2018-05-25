package com.loam.framework.core.catalog.restservice.rol;

import java.util.Date;
import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogRol;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.HeaderResponseType;

public class RolResponse {
	private int total;
	private long tokenOperacion;
	private Date fechaHora;
	private List<CatalogRol> listRol;
	private HeaderResponseType headerResponseType;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public long getTokenOperacion() {
		return tokenOperacion;
	}

	public void setTokenOperacion(long tokenOperacion) {
		this.tokenOperacion = tokenOperacion;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public List<CatalogRol> getListRol() {
		return listRol;
	}

	public void setListRol(List<CatalogRol> listRol) {
		this.listRol = listRol;
	}

	public HeaderResponseType getHeaderResponseType() {
		return headerResponseType;
	}

	public void setHeaderResponseType(HeaderResponseType headerResponseType) {
		this.headerResponseType = headerResponseType;
	}

}
