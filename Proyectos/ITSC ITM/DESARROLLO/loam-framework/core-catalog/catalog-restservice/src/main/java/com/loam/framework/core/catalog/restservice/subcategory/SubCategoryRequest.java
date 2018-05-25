package com.loam.framework.core.catalog.restservice.subcategory;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogSubCategory;

public class SubCategoryRequest {
	private String tokenOper;
	private String tokenUsuario;
	private String sesion;
	private CatalogSubCategory catalogSubCategory;

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

	public CatalogSubCategory getCatalogSubCategory() {
		return catalogSubCategory;
	}

	public void setCatalogSubCategory(CatalogSubCategory catalogSubCategory) {
		this.catalogSubCategory = catalogSubCategory;
	}

}
