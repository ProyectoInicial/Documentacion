package com.loam.framework.core.customer.jaxb.exception;

import java.util.Date;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.springframework.ws.context.MessageContext;

import com.loam.framework.core.customer.commons.utils.date.DateUtilsCommons;
import com.loam.framework.core.customer.commons.utils.properties.CommonProperties;
import com.loam.framework.core.customer.jaxb.common.SoapCommonElement;
import com.loam.framework.core.customer.jaxb.ws.common.excepciongeneral.ExcepcionGenericaType;
import com.loam.framework.core.customer.jaxb.ws.general.headers.EstadoRespuestaType;
import com.loam.framework.core.customer.jaxb.ws.general.headers.HeaderResponseType;

public class ServiceFaultException extends RuntimeException {

	private static final long serialVersionUID = 7885514313311177523L;
	
	private ExcepcionGenericaType excepcionGenericaType;
	
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
    
    public ServiceFaultException(String message, Throwable e, MessageContext messageContext, CommonProperties commonProperties) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
        super(message, e);
        HeaderResponseType headerResponseVo = new HeaderResponseType();
        EstadoRespuestaType estadoRespuestaVo = new EstadoRespuestaType();
        estadoRespuestaVo.setIdOperacion(String.valueOf(e.hashCode()));
        estadoRespuestaVo.setMetodo(e.getStackTrace()[0].getMethodName());
        estadoRespuestaVo.setClase(e.getStackTrace()[0].getClassName());
        estadoRespuestaVo.setMensajeDetallado(e.getMessage());
        estadoRespuestaVo.setNivelSegRequerido(e.hashCode());
        headerResponseVo.setEstadoRespuesta(estadoRespuestaVo);
        
        headerResponseVo.setId(String.valueOf(commonProperties.getIdEstatusError()));
        headerResponseVo.setEstatus(commonProperties.getIdEstatusErrorCodigo());
        headerResponseVo.setMensaje(commonProperties.getIdEstatusErrorMsg());
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

}
