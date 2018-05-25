package com.loam.framework.core.catalog.restservice.color;

import java.util.Date;
import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogColor;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.HeaderResponseType;

public class ColorResponse {
	private int total;
	private long tokenOperacion;
	private Date fechaHora;
	private List<CatalogColor> listColor;
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

	public List<CatalogColor> getListColor() {
		return listColor;
	}

	public void setListColor(List<CatalogColor> listColor) {
		this.listColor = listColor;
	}

	public HeaderResponseType getHeaderResponseType() {
		return headerResponseType;
	}

	public void setHeaderResponseType(HeaderResponseType headerResponseType) {
		this.headerResponseType = headerResponseType;
	}

}
