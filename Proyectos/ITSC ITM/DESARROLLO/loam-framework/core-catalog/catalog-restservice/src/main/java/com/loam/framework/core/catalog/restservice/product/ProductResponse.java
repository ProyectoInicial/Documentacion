package com.loam.framework.core.catalog.restservice.product;

import java.util.Date;
import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogProduct;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.HeaderResponseType;

public class ProductResponse {
	private int total;
	private long tokenOperacion;
	private Date fechaHora;
	private List<CatalogProduct> listProduct;
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

	public List<CatalogProduct> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<CatalogProduct> listProduct) {
		this.listProduct = listProduct;
	}

	public HeaderResponseType getHeaderResponseType() {
		return headerResponseType;
	}

	public void setHeaderResponseType(HeaderResponseType headerResponseType) {
		this.headerResponseType = headerResponseType;
	}

}
