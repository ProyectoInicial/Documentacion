package com.loam.framework.core.catalog.restservice.coin;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogCoin;

public class CoinRequest {
	private String tokenOper;
	private String tokenUsuario;
	private String sesion;
	private CatalogCoin catalogCoin;

	public String getTokenOper() {
		return tokenOper;
	}

	public void setTokenOper(String tokenOper) {
		this.tokenOper = tokenOper;
	}

	public CatalogCoin getCatalogCoin() {
		return catalogCoin;
	}

	public void setCatalogCoin(CatalogCoin catalogCoin) {
		this.catalogCoin = catalogCoin;
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

}
