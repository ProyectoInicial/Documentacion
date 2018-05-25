package com.loam.framework.core.catalog.restservice.account;

import java.util.Date;
import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogAccount;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.HeaderResponseType;

public class AccountResponse {
	private int total;
	private long tokenOperacion;
	private Date fechaHora;
	private List<CatalogAccount> listAccount;
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

	public List<CatalogAccount> getListAccount() {
		return listAccount;
	}

	public void setListAccount(List<CatalogAccount> listAccount) {
		this.listAccount = listAccount;
	}

	public HeaderResponseType getHeaderResponseType() {
		return headerResponseType;
	}

	public void setHeaderResponseType(HeaderResponseType headerResponseType) {
		this.headerResponseType = headerResponseType;
	}

}
