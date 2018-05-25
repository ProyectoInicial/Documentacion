package com.loam.framework.core.catalog.restservice.product;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogProduct;

public class ProductRequest {
	private String tokenOper;
	private String tokenUsuario;
	private String sesion;
	private CatalogProduct catalogProduct;

	public String getTokenOper() {
		return tokenOper;
	}

	public void setTokenOper(String tokenOper) {
		this.tokenOper = tokenOper;
	}

	public String getTokenUsuario() {
		return tokenUsuario;
	}

	public void setTokenUsuario(String tokenUsuario) {
		this.tokenUsuario = tokenUsuario;
	}

	public String getSesion() {
		return sesion;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

	public CatalogProduct getCatalogProduct() {
		return catalogProduct;
	}

	public void setCatalogProduct(CatalogProduct catalogProduct) {
		this.catalogProduct = catalogProduct;
	}

}
