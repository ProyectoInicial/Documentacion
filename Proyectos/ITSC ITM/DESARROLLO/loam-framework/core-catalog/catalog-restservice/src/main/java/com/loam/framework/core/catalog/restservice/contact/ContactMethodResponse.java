package com.loam.framework.core.catalog.restservice.contact;

import java.util.Date;
import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogContactMethod;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.HeaderResponseType;

public class ContactMethodResponse {
	private int total;
	private long tokenOperacion;
	private Date fechaHora;
	private List<CatalogContactMethod> listContactMethod;
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

	public List<CatalogContactMethod> getListContactMethod() {
		return listContactMethod;
	}

	public void setListContactMethod(List<CatalogContactMethod> listContactMethod) {
		this.listContactMethod = listContactMethod;
	}

	public HeaderResponseType getHeaderResponseType() {
		return headerResponseType;
	}

	public void setHeaderResponseType(HeaderResponseType headerResponseType) {
		this.headerResponseType = headerResponseType;
	}

}
