package com.loam.framework.core.catalog.restservice.rol;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogRol;

public class RolRequest {
	private String tokenOper;
	private String tokenUsuario;
	private String sesion;
	private CatalogRol catalogRol;

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

	public CatalogRol getCatalogRol() {
		return catalogRol;
	}

	public void setCatalogRol(CatalogRol catalogRol) {
		this.catalogRol = catalogRol;
	}

}
