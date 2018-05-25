package com.loam.framework.core.catalog.restservice.coin;

import java.util.Date;
import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogCoin;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.HeaderResponseType;

public class CoinResponse {
	private int total;
	private long tokenOperacion;
	private Date fechaHora;
	private List<CatalogCoin> listCoin;
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

	public List<CatalogCoin> getListCoin() {
		return listCoin;
	}

	public void setListCoin(List<CatalogCoin> listCoin) {
		this.listCoin = listCoin;
	}

	public HeaderResponseType getHeaderResponseType() {
		return headerResponseType;
	}

	public void setHeaderResponseType(HeaderResponseType headerResponseType) {
		this.headerResponseType = headerResponseType;
	}

}
