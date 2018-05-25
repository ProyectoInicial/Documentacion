package com.loam.framework.core.catalog.restservice.menu;

import java.util.Date;
import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogMenu;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.HeaderResponseType;

public class MenuResponse {
	private int total;
	private long tokenOperacion;
	private Date fechaHora;
	private List<CatalogMenu> listMenu;
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

	public List<CatalogMenu> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<CatalogMenu> listMenu) {
		this.listMenu = listMenu;
	}

	public HeaderResponseType getHeaderResponseType() {
		return headerResponseType;
	}

	public void setHeaderResponseType(HeaderResponseType headerResponseType) {
		this.headerResponseType = headerResponseType;
	}

}
