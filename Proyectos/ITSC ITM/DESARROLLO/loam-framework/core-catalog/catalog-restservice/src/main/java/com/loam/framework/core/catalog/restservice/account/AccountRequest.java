package com.loam.framework.core.catalog.restservice.account;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogAccount;

public class AccountRequest {
	private String tokenOper;
	private String tokenUsuario;
	private String sesion;
	private CatalogAccount catalogAccount;

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

	public CatalogAccount getCatalogAccount() {
		return catalogAccount;
	}

	public void setCatalogAccount(CatalogAccount catalogAccount) {
		this.catalogAccount = catalogAccount;
	}

}
