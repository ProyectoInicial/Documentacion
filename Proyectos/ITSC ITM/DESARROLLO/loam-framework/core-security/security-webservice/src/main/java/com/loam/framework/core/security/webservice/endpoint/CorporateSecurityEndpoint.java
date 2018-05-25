package com.loam.framework.core.security.webservice.endpoint;

import java.util.Date;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.loam.framework.core.security.commons.properties.CommonProperties;
import com.loam.framework.core.security.commons.utils.date.DateUtilsCommons;
import com.loam.framework.core.security.jaxb.common.SoapCommonElement;
import com.loam.framework.core.security.jaxb.exception.CodigosError;
import com.loam.framework.core.security.jaxb.exception.ServiceFaultException;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityAliasIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityAliasOut;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityEmailIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityEmailOut;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityLoginIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityLoginOut;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityTokenIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityTokenOut;
import com.loam.framework.core.security.jaxb.ws.general.headers.EstadoRespuestaType;
import com.loam.framework.core.security.jaxb.ws.general.headers.HeaderResponseType;
import com.loam.framework.core.security.persistence.common.CustomGenericException;
import com.loam.framework.core.security.services.security.login.DataLoginService;

@Endpoint
public class CorporateSecurityEndpoint {
	private static final String TARGET_NAMESPACE = "http://www.loam.com/framework/core/security/jaxb/ws/CorporateSecurity";

	@Autowired
	private DataLoginService dataLoginService;
	@Autowired
	private CommonProperties commonProperties;

	@PayloadRoot(localPart = "SecurityAliasIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public SecurityAliasOut seguridadAlias(@RequestPayload SecurityAliasIn request, MessageContext messageContext)
			throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException {
		SecurityAliasOut response = new SecurityAliasOut();
		try {
			response = dataLoginService.securityAliasService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("seguridadAlias"));
		} catch (CustomGenericException ex) {
			throw new ServiceFaultException(ex.getMessage(), ex);
		} catch (Exception ex) {
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}
		return response;
	}

	@PayloadRoot(localPart = "SecurityEmailIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public SecurityEmailOut seguridadEmail(@RequestPayload SecurityEmailIn request, MessageContext messageContext)
			throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException {
		SecurityEmailOut response = new SecurityEmailOut();
		try {
			response = dataLoginService.securityEmailService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("seguridadEmail"));
		} catch (CustomGenericException ex) {
			throw new ServiceFaultException(ex.getMessage(), ex);
		} catch (Exception ex) {
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}
		return response;
	}

	@PayloadRoot(localPart = "SecurityTokenIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public SecurityTokenOut seguridadToken(@RequestPayload SecurityTokenIn request, MessageContext messageContext)
			throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException {
		SecurityTokenOut response = new SecurityTokenOut();
		try {
			response = dataLoginService.securityTokenService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("seguridadToken"));
		} catch (CustomGenericException ex) {
			throw new ServiceFaultException(ex.getMessage(), ex);
		} catch (Exception ex) {
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}
		return response;
	}

	@PayloadRoot(localPart = "SecurityLoginIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public SecurityLoginOut seguridadLogin(@RequestPayload SecurityLoginIn request, MessageContext messageContext)
			throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException {
		SecurityLoginOut response = new SecurityLoginOut();
		try {
			response = dataLoginService.securityLoginService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("seguridadLogin"));
		} catch (CustomGenericException ex) {
			throw new ServiceFaultException(ex.getMessage(), ex);
		} catch (Exception ex) {
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}
		return response;
	}

	public void setDataLoginService(DataLoginService dataLoginService) {
		this.dataLoginService = dataLoginService;
	}

	public void setCommonProperties(CommonProperties commonProperties) {
		this.commonProperties = commonProperties;
	}

	public HeaderResponseType getDefaultHeaderResponseVo(String metodo) throws Exception {
		HeaderResponseType headerResponseVo = new HeaderResponseType();
		EstadoRespuestaType estadoRespuestaVo = new EstadoRespuestaType();

		try {
			estadoRespuestaVo.setIdOperacion(String.valueOf(CorporateSecurityEndpoint.class.hashCode()));
			estadoRespuestaVo.setMetodo(metodo);
			estadoRespuestaVo.setClase(CorporateSecurityEndpoint.class.getName());
			estadoRespuestaVo.setMensajeDetallado(commonProperties.getIdEstatusSuccessMsg());
			estadoRespuestaVo.setNivelSegRequerido(commonProperties.getIdNivelRequeridoWeb());
			headerResponseVo.setEstadoRespuesta(estadoRespuestaVo);
			headerResponseVo.setId(String.valueOf(commonProperties.getIdEstatusSuccess()));
			headerResponseVo.setEstatus(commonProperties.getIdEstatusSuccessCodigo());
			headerResponseVo.setMensaje(commonProperties.getIdEstatusSuccessMsg());
			headerResponseVo.setTokenOperacion(String.valueOf(CorporateSecurityEndpoint.class.hashCode()));
			headerResponseVo.setFechaHora(DateUtilsCommons.toXMLGregorianCalendar(new Date()));
		} catch (Exception ex) {
			throw new ServiceFaultException(ex.getMessage(), ex);
		}
		return headerResponseVo;
	}
}
