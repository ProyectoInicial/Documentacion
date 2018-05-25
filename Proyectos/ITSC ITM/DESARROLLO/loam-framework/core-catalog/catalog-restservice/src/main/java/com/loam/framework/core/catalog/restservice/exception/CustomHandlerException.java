package com.loam.framework.core.catalog.restservice.exception;

import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;

public class CustomHandlerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private int idMensage;
	private String estatusMensage;
	private ExcepcionGenerica excepcionGenerica;

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

	public CustomHandlerException(String message, ExcepcionGenerica excepcionGenerica,  Throwable e) {
		super(message, e);
		this.excepcionGenerica = excepcionGenerica;
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

	public ExcepcionGenerica getExcepcionGenerica() {
		return excepcionGenerica;
	}

	public void setExcepcionGenerica(ExcepcionGenerica excepcionGenerica) {
		this.excepcionGenerica = excepcionGenerica;
	}
	
/*
public ServiceFaultException(String message, ExcepcionGenerica e, MessageContext messageContext,
			CommonProperties commonProperties) throws SOAPException, JAXBException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		super(message);
		HeaderResponseType headerResponseVo = new HeaderResponseType();
		EstadoRespuestaType estadoRespuestaVo = new EstadoRespuestaType();
		estadoRespuestaVo.setIdOperacion(String.valueOf(e.hashCode()));
		estadoRespuestaVo.setMetodo(e.getMetodo());
		estadoRespuestaVo.setClase(e.getComponente());
		estadoRespuestaVo.setMensajeDetallado(e.getDescripcion());
		estadoRespuestaVo.setNivelSegRequerido(e.hashCode());
		headerResponseVo.setEstadoRespuesta(estadoRespuestaVo);
		
		headerResponseVo.setId(String.valueOf(e.getId()));
		headerResponseVo.setEstatus(e.getCodigo());
		headerResponseVo.setMensaje(e.getMensaje());
		headerResponseVo.setFechaHora(DateUtilsCommons.toXMLGregorianCalendar(new Date()));
		headerResponseVo.setTokenOperacion(String.valueOf(e.hashCode()));

		SoapCommonElement.addHeaderResponse(messageContext, headerResponseVo);
	}

	public ServiceFaultException(String message, Throwable e, MessageContext messageContext,
			CommonProperties commonProperties) throws SOAPException, JAXBException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		super(message, e);
		
		CustomGenericException ext = (CustomGenericException)e;
		
		HeaderResponseType headerResponseVo = new HeaderResponseType();
		EstadoRespuestaType estadoRespuestaVo = new EstadoRespuestaType();
		estadoRespuestaVo.setIdOperacion(String.valueOf(e.hashCode()));
		estadoRespuestaVo.setMetodo(ext.getExcepcionGenerica().getMetodo());
		estadoRespuestaVo.setClase(ext.getExcepcionGenerica().getComponente());
		estadoRespuestaVo.setMensajeDetallado(ext.getExcepcionGenerica().getDescripcion());
		estadoRespuestaVo.setNivelSegRequerido(e.hashCode());
		headerResponseVo.setEstadoRespuesta(estadoRespuestaVo);

		headerResponseVo.setId(String.valueOf(ext.getExcepcionGenerica().getId()));
		headerResponseVo.setEstatus(ext.getExcepcionGenerica().getCodigo());
		headerResponseVo.setMensaje(ext.getExcepcionGenerica().getMensaje());
		headerResponseVo.setFechaHora(DateUtilsCommons.toXMLGregorianCalendar(new Date()));
		headerResponseVo.setTokenOperacion(String.valueOf(e.hashCode()));
		
		SoapCommonElement.addHeaderResponse(messageContext, headerResponseVo);
	}
 * */
}
