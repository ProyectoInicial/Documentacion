package com.loam.framework.core.security.restservice.exception;

public class CustomHandlerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private int idMensage;
	private String estatusMensage;

	public CustomHandlerException(String message) {
		super(message);
	}

	public CustomHandlerException(Throwable e) {
		super(e);
	}

	public CustomHandlerException(String message, Throwable e) {
		super(message, e);
	}

	public CustomHandlerException(int idMensage, String message) {
		super(message);
		this.idMensage = idMensage;
	}

	public CustomHandlerException(int idMensage, String estatusMensage,
			String message) {
		super(message);
		this.idMensage = idMensage;
		this.estatusMensage = estatusMensage;
	}

	public CustomHandlerException(int idMensage, String message, Throwable e) {
		super(message, e);
		this.idMensage = idMensage;
	}
	
	public CustomHandlerException(int idMensage, String estatusMensage, String message, Throwable e) {
		super(message, e);
		this.idMensage = idMensage;
		this.estatusMensage = estatusMensage;
	}

	public int getIdMensage() {
		return idMensage;
	}

	public void setIdMensage(int idMensage) {
		this.idMensage = idMensage;
	}

	public String getEstatusMensage() {
		return estatusMensage;
	}

	public void setEstatusMensage(String estatusMensage) {
		this.estatusMensage = estatusMensage;
	}

}
