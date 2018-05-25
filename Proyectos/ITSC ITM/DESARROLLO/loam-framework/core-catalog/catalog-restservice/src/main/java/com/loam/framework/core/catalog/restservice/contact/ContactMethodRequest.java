package com.loam.framework.core.catalog.restservice.contact;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogContactMethod;

public class ContactMethodRequest {
	private String tokenOper;
	private String tokenUsuario;
	private String sesion;
	private CatalogContactMethod catalogContactMethod;

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

	public CatalogContactMethod getCatalogContactMethod() {
		return catalogContactMethod;
	}

	public void setCatalogContactMethod(CatalogContactMethod catalogContactMethod) {
		this.catalogContactMethod = catalogContactMethod;
	}

}
