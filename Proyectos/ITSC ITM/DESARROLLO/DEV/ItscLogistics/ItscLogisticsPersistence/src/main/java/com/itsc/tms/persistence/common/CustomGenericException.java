package com.itsc.tms.persistence.common;

import com.itsc.tms.commons.utils.exception.ExcepcionGenerica;

public class CustomGenericException extends Exception {

	private static final long serialVersionUID = 5375670050662639530L;

	private ExcepcionGenerica excepcionGenerica;
//	private ExcepcionGenericaType excepcionGenericaType;

	public CustomGenericException(String message) {
		super(message);
	}

	public CustomGenericException(Throwable e) {
		super(e);
	}

	public CustomGenericException(String message, Throwable e) {
		super(message, e);
	}

	public CustomGenericException(String message, ExcepcionGenerica excepcionGenerica) {
		super(message);
		this.excepcionGenerica = excepcionGenerica;
	}

//	public CustomGenericException(String message, ExcepcionGenericaType excepcionGenericaType) {
//		super(message);
//		this.excepcionGenericaType = excepcionGenericaType;
//	}
//	
//	public CustomGenericException(ExcepcionGenericaType excepcionGenericaType) {
//		this.excepcionGenericaType = excepcionGenericaType;
//	}

	public CustomGenericException(ExcepcionGenerica excepcionGenerica) {
		this.excepcionGenerica = excepcionGenerica;
	}

	public CustomGenericException(String message, Throwable e, ExcepcionGenerica excepcionGenerica) {
		super(message, e);
		this.excepcionGenerica = excepcionGenerica;
	}

	public ExcepcionGenerica getExcepcionGenerica() {
		return excepcionGenerica;
	}

	public void setExcepcionGenerica(ExcepcionGenerica excepcionGenerica) {
		this.excepcionGenerica = excepcionGenerica;
	}

//	public ExcepcionGenericaType getExcepcionGenericaType() {
//		return excepcionGenericaType;
//	}
//
//	public void setExcepcionGenericaType(ExcepcionGenericaType excepcionGenericaType) {
//		this.excepcionGenericaType = excepcionGenericaType;
//	}

}
