package com.loam.framework.core.catalog.restservice.category;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogCategory;

public class CategoryRequest {
	private String tokenOper;
	private String tokenUsuario;
	private String sesion;
	private CatalogCategory catalogCategory;

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

	public CatalogCategory getCatalogCategory() {
		return catalogCategory;
	}

	public void setCatalogCategory(CatalogCategory catalogCategory) {
		this.catalogCategory = catalogCategory;
	}

}
