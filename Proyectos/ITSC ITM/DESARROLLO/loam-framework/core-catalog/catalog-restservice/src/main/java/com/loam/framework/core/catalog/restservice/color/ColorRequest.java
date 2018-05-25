package com.loam.framework.core.catalog.restservice.color;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogColor;

public class ColorRequest {
	private String tokenOper;
	private String tokenUsuario;
	private String sesion;
	private CatalogColor catalogColor;

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

	public CatalogColor getCatalogColor() {
		return catalogColor;
	}

	public void setCatalogColor(CatalogColor catalogColor) {
		this.catalogColor = catalogColor;
	}

}
