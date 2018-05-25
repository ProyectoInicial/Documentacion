package com.loam.framework.core.catalog.restservice.menu;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogMenu;

public class MenuRequest {
	private String tokenOper;
	private String tokenUsuario;
	private String sesion;
	private CatalogMenu catalogMenu;

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

	public CatalogMenu getCatalogMenu() {
		return catalogMenu;
	}

	public void setCatalogMenu(CatalogMenu catalogMenu) {
		this.catalogMenu = catalogMenu;
	}

}
