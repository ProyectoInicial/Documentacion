package com.loam.framework.core.catalog.restservice.common;

import java.util.Date;

public enum ErroresComunes {

	ID_EXITO(1), ID_ERROR(-1), ID_INTERNAL(-2), ID_NOT_FOUND("0"), ID_FATAL(-100), NIVEL_SUG(100), 
	ESTATUS_EXITO("SUCCESS"), ESTATUS_ERROR("ERROR"), ESTATUS_INTERNAL("INTERNAL"), ESTATUS_NOT_FOUND("NOT_FOUND"), ESTATUS_FATAL("FATAL"),
	MSG_EXITO("El registro se proceso correctamente."), MSG_ERROR("Ocurio un error al procesar el registro."),
	MSG_INTERNAL("Ocurio un error interno en la aplicacion."), MSG_NOT_FOUND("El Registro no se encontro en la aplicacion."),
	MSG_FATAL("Ocurio un error fatal en la aplicacion."), FECHA(new java.util.Date());

	private String srtValue;
	private int intValue;
	private Date dateValue;

	private ErroresComunes(String srtValue) {
		this.srtValue = srtValue;
	}
	
	private ErroresComunes(int intValue) {
		this.intValue = intValue;
	}

	private ErroresComunes(Date dateValue) {
		this.dateValue = dateValue;
	}
	
	public String getSrtValue() {
		return srtValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public Date getDateValue() {
		return dateValue;
	}

}
