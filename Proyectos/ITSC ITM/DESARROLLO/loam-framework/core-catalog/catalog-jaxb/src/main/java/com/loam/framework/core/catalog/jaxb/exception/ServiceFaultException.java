package com.loam.framework.core.catalog.jaxb.exception;

import java.util.Date;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.springframework.ws.context.MessageContext;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.date.DateUtilsCommons;
import com.loam.framework.core.catalog.commons.utils.exception.CustomGenericException;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.common.SoapCommonElement;
import com.loam.framework.core.catalog.jaxb.ws.common.excepciongeneral.ExcepcionGenericaType;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.EstadoRespuestaType;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.HeaderResponseType;

public class ServiceFaultException extends RuntimeException {

	private static final long serialVersionUID = 7885514313311177523L;

	private ExcepcionGenericaType excepcionGenericaType;
	private ExcepcionGenerica excepcionGenerica;

	public ServiceFaultException(String message) {
		super(message);
	}

	public ServiceFaultException(Throwable e) {
		super(e);
	}

	public ServiceFaultException(String message, Throwable e) {
		super(message, e);
	}

	public ServiceFaultException(String message, ExcepcionGenericaType excepcionGenericaType) {
		super(message);
		this.excepcionGenericaType = excepcionGenericaType;
	}

	public ServiceFaultException(String message, Throwable e, ExcepcionGenericaType excepcionGenericaType) {
		super(message, e);
		this.excepcionGenericaType = excepcionGenericaType;
	}

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

	public ExcepcionGenericaType getExcepcionGenericaType() {
		return excepcionGenericaType;
	}

	public void setExcepcionGenericaType(ExcepcionGenericaType excepcionGenericaType) {
		this.excepcionGenericaType = excepcionGenericaType;
	}

	public ExcepcionGenerica getExcepcionGenerica() {
		return excepcionGenerica;
	}

	public void setExcepcionGenerica(ExcepcionGenerica excepcionGenerica) {
		this.excepcionGenerica = excepcionGenerica;
	}

}
