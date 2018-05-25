package com.loam.framework.core.customer.webservice.endpoint;

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

import com.loam.framework.core.customer.commons.utils.date.DateUtilsCommons;
import com.loam.framework.core.customer.commons.utils.properties.CommonProperties;
import com.loam.framework.core.customer.jaxb.common.SoapCommonElement;
import com.loam.framework.core.customer.jaxb.exception.CodigosError;
import com.loam.framework.core.customer.jaxb.exception.ServiceFaultException;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultDataPersonIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultDataPersonOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserAliasIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserAliasOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserContactAddressIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserContactAddressOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserContactMethodIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserContactMethodOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainDataPersonIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainDataPersonOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserAliasIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserAliasOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactAddressIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactAddressOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactMethodIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactMethodOut;
import com.loam.framework.core.customer.jaxb.ws.general.headers.EstadoRespuestaType;
import com.loam.framework.core.customer.jaxb.ws.general.headers.HeaderResponseType;
import com.loam.framework.core.customer.persistence.common.CustomGenericException;
import com.loam.framework.core.customer.services.address.PersonContactAddressService;
import com.loam.framework.core.customer.services.alias.AliasDataService;
import com.loam.framework.core.customer.services.contact.PersonContactMethodService;
import com.loam.framework.core.customer.services.person.PersonDataService;

@Endpoint
public class CustomerDataEndpoint {
	private static final String TARGET_NAMESPACE = "http://www.loam.com/framework/core/customer/jaxb/ws/CustomerDetails";

	@Autowired
	private PersonContactAddressService personContactAddressService;
	@Autowired
	private AliasDataService aliasDataService;
	@Autowired
	private PersonContactMethodService personContactMethodService;
	@Autowired
	private PersonDataService personDataService;
	@Autowired
	private CommonProperties commonProperties;

	@PayloadRoot(localPart = "MaintainUserContactAddressIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public MaintainUserContactAddressOut maintainUserContactAddress(@RequestPayload MaintainUserContactAddressIn request,
			MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		MaintainUserContactAddressOut response = new MaintainUserContactAddressOut();
		try {
			response = personContactAddressService.maintainPersonContactAddressService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainUserContactAddress"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}catch(Exception ex){
		    throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}
		return response;
	}

	@PayloadRoot(localPart = "ConsultUserContactAddressIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public ConsultUserContactAddressOut consultUserContactAddress(@RequestPayload ConsultUserContactAddressIn request,
			MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		ConsultUserContactAddressOut response = new ConsultUserContactAddressOut();
		try {
			response = personContactAddressService.consultPersonContactAddressService(Long.valueOf(String.valueOf(request.getIdContactAddress())), Long.valueOf(String.valueOf(request.getIdPerson())), request.getIdStart(), request.getIdEnd(), request.getOrder());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultUserContactAddress"));
		} catch (CustomGenericException ex) {
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		} catch (Exception ex) {
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "MaintainUserAliasIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public MaintainUserAliasOut maintainUserAlias(@RequestPayload MaintainUserAliasIn request,
			MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		MaintainUserAliasOut response = new MaintainUserAliasOut();
		try {
			response = aliasDataService.maintainAliasDataService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainUserAlias"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}catch(Exception ex){
		    throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "ConsultUserAliasIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public ConsultUserAliasOut consultUserAlias(@RequestPayload ConsultUserAliasIn request,
			MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		ConsultUserAliasOut response = new ConsultUserAliasOut();
		try {
			response = aliasDataService.consultAliasDataService(String.valueOf(request.getTokenUser()), request.getAliasUser(), request.getEmailUser(), request.getIdStart(), request.getIdEnd(), request.getOrder());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultUserAlias"));
		} catch (CustomGenericException ex) {
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		} catch (Exception ex) {
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}
		return response;
	}

	@PayloadRoot(localPart = "MaintainUserContactMethodIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public MaintainUserContactMethodOut maintainUserContactMethod(@RequestPayload MaintainUserContactMethodIn request,
			MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		MaintainUserContactMethodOut response = new MaintainUserContactMethodOut();
		try {
			response = personContactMethodService.maintainUserContactMethodService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainUserContactMethod"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}catch(Exception ex){
		    throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "ConsultUserContactMethodIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public ConsultUserContactMethodOut consultUserContactMethod(@RequestPayload ConsultUserContactMethodIn request,
			MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		ConsultUserContactMethodOut response = new ConsultUserContactMethodOut();
		try {
			response = personContactMethodService.consultUserContactMethodService(Long.valueOf(String.valueOf(request.getIdContactMethod())), Long.valueOf(String.valueOf(request.getIdContactMethodCat())), Long.valueOf(String.valueOf(request.getIdPerson())), request.getIdStart(), request.getIdEnd(), request.getOrder());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultUserContactMethod"));
		} catch (CustomGenericException ex) {
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		} catch (Exception ex) {
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}
		return response;
	}
	
//	personDataService
	@PayloadRoot(localPart = "MaintainDataPersonIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public MaintainDataPersonOut maintainDataPerson(@RequestPayload MaintainDataPersonIn request,
			MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		MaintainDataPersonOut response = new MaintainDataPersonOut();
		try {
			response = personDataService.maintainDataPersonService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainDataPerson"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}catch(Exception ex){
		    throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "ConsultDataPersonIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload
	public ConsultDataPersonOut consultDataPerson(@RequestPayload ConsultDataPersonIn request,
			MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		ConsultDataPersonOut response = new ConsultDataPersonOut();
		try {
			response = personDataService.consultDataPersonService(Long.valueOf(String.valueOf(request.getIdPerson())), Long.valueOf(String.valueOf(request.getIdPersonCat())), Long.valueOf(String.valueOf(request.getTokenPerson())), Long.valueOf(String.valueOf(request.getIdUser())), request.getIdStart(), request.getIdEnd(), request.getOrder());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultDataPerson"));
		} catch (CustomGenericException ex) {
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		} catch (Exception ex) {
			throw new ServiceFaultException(CodigosError.ERROR.toString(), ex, messageContext, commonProperties);
		}
		return response;
	}
	
	public HeaderResponseType getDefaultHeaderResponseVo(String metodo) throws Exception {
		HeaderResponseType headerResponseVo = new HeaderResponseType();
		EstadoRespuestaType estadoRespuestaVo = new EstadoRespuestaType();
		try {
			estadoRespuestaVo.setIdOperacion(String.valueOf(CustomerDataEndpoint.class.hashCode()));
			estadoRespuestaVo.setMetodo(metodo);
			estadoRespuestaVo.setClase(CustomerDataEndpoint.class.getName());
			estadoRespuestaVo.setMensajeDetallado(commonProperties.getIdEstatusSuccessMsg());
			estadoRespuestaVo.setNivelSegRequerido(commonProperties.getIdNivelRequeridoWeb());
			headerResponseVo.setEstadoRespuesta(estadoRespuestaVo);
			headerResponseVo.setId(String.valueOf(commonProperties.getIdEstatusSuccess()));
			headerResponseVo.setEstatus(commonProperties.getIdEstatusSuccessCodigo());
			headerResponseVo.setMensaje(commonProperties.getIdEstatusSuccessMsg());
			headerResponseVo.setTokenOperacion(String.valueOf(CustomerDataEndpoint.class.hashCode()));
			headerResponseVo.setFechaHora(DateUtilsCommons.toXMLGregorianCalendar(new Date()));
		} catch (Exception ex) {
			throw new ServiceFaultException(ex.getMessage(), ex);
		}
		return headerResponseVo;
	}

	public void setPersonContactAddressService(PersonContactAddressService personContactAddressService) {
		this.personContactAddressService = personContactAddressService;
	}

	public void setAliasDataService(AliasDataService aliasDataService) {
		this.aliasDataService = aliasDataService;
	}

	public void setPersonContactMethodService(PersonContactMethodService personContactMethodService) {
		this.personContactMethodService = personContactMethodService;
	}

	public void setPersonDataService(PersonDataService personDataService) {
		this.personDataService = personDataService;
	}

	public void setCommonProperties(CommonProperties commonProperties) {
		this.commonProperties = commonProperties;
	}
	
}
