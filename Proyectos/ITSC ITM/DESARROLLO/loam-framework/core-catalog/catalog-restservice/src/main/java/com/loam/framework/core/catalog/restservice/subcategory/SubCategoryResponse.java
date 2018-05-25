package com.loam.framework.core.catalog.restservice.subcategory;

import java.util.Date;
import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogSubCategory;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.HeaderResponseType;

public class SubCategoryResponse {
	private int total;
	private long tokenOperacion;
	private Date fechaHora;
	private List<CatalogSubCategory> listSubCategory;
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

	public List<CatalogSubCategory> getListSubCategory() {
		return listSubCategory;
	}

	public void setListSubCategory(List<CatalogSubCategory> listSubCategory) {
		this.listSubCategory = listSubCategory;
	}

	public HeaderResponseType getHeaderResponseType() {
		return headerResponseType;
	}

	public void setHeaderResponseType(HeaderResponseType headerResponseType) {
		this.headerResponseType = headerResponseType;
	}

}
