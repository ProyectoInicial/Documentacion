package com.loam.framework.core.security.persistence.common;

public class CustomPersistenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int idMensage;
	private String estatusMensage;
	private int idEstatusError;
	private String idEstatusErrorCodigo;
	private String idEstatusErrorMsg;

	public CustomPersistenceException(String message) {
		super(message);
	}

	public CustomPersistenceException(Throwable e) {
		super(e);
	}

	public CustomPersistenceException(String message, Throwable e) {
		super(message, e);
	}

	public CustomPersistenceException(int idMensage, String message) {
		super(message);
		this.idMensage = idMensage;
	}

	public CustomPersistenceException(int idMensage, String estatusMensage, String message) {
		super(message);
		this.idMensage = idMensage;
		this.estatusMensage = estatusMensage;
	}

	public CustomPersistenceException(int idMensage, String message, Throwable e) {
		super(message, e);
		this.idMensage = idMensage;
	}

	public CustomPersistenceException(int idEstatusError, String idEstatusErrorCodigo, String idEstatusErrorMsg,
			String message, Throwable e) {
		super(message, e);
		this.idEstatusError = idEstatusError;
		this.idEstatusErrorCodigo = idEstatusErrorCodigo;
		this.idEstatusErrorMsg = idEstatusErrorMsg;
	}

	public CustomPersistenceException(int idMensage, String estatusMensage, String message, Throwable e) {
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

	public int getIdEstatusError() {
		return idEstatusError;
	}

	public void setIdEstatusError(int idEstatusError) {
		this.idEstatusError = idEstatusError;
	}

	public String getIdEstatusErrorCodigo() {
		return idEstatusErrorCodigo;
	}

	public void setIdEstatusErrorCodigo(String idEstatusErrorCodigo) {
		this.idEstatusErrorCodigo = idEstatusErrorCodigo;
	}

	public String getIdEstatusErrorMsg() {
		return idEstatusErrorMsg;
	}

	public void setIdEstatusErrorMsg(String idEstatusErrorMsg) {
		this.idEstatusErrorMsg = idEstatusErrorMsg;
	}

}
