package com.loam.framework.core.catalog.restservice.category;

import java.util.Date;
import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogCategory;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.HeaderResponseType;

public class CategoryResponse {
	private int total;
	private long tokenOperacion;
	private Date fechaHora;
	private List<CatalogCategory> listCategory;
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

	public List<CatalogCategory> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<CatalogCategory> listCategory) {
		this.listCategory = listCategory;
	}

	public HeaderResponseType getHeaderResponseType() {
		return headerResponseType;
	}

	public void setHeaderResponseType(HeaderResponseType headerResponseType) {
		this.headerResponseType = headerResponseType;
	}

}
