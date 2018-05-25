package com.loam.framework.core.catalog.webservice.endpoint;

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

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.date.DateUtilsCommons;
import com.loam.framework.core.catalog.jaxb.common.SoapCommonElement;
import com.loam.framework.core.catalog.jaxb.exception.ServiceFaultException;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogAccountIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogAccountOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogCategoryIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogCategoryOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogCoinIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogCoinOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogColorIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogColorOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogContactMethodIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogContactMethodOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogMenuIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogMenuOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogProductIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogProductOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogRolIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogRolOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogSubCategoryIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogSubCategoryOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogAccountIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogAccountOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCategoryIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCategoryOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCoinIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCoinOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogColorIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogColorOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogContactMethodIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogContactMethodOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogMenuIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogMenuOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogProductIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogProductOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogRolIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogRolOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogSubCategoryIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogSubCategoryOut;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.EstadoRespuestaType;
import com.loam.framework.core.catalog.jaxb.ws.general.headers.HeaderResponseType;
import com.loam.framework.core.catalog.persistence.common.CustomGenericException;
import com.loam.framework.core.catalog.services.account.CatalogAccountService;
import com.loam.framework.core.catalog.services.category.CatalogCategoryService;
import com.loam.framework.core.catalog.services.coin.CatalogCoinService;
import com.loam.framework.core.catalog.services.color.CatalogColorService;
import com.loam.framework.core.catalog.services.contact.CatalogContactMethodService;
import com.loam.framework.core.catalog.services.menu.CatalogMenuService;
import com.loam.framework.core.catalog.services.product.CatalogProductService;
import com.loam.framework.core.catalog.services.rol.CatalogRolService;
import com.loam.framework.core.catalog.services.subcategory.CatalogSubCategoryService;

@Endpoint
public class CatalogManagerEndpoint {
private static final String TARGET_NAMESPACE = "http://www.loam.com/framework/core/catalog/jaxb/ws/CatalogManager";

	@Autowired
	private CatalogAccountService catalogAccountService;
	@Autowired
	private CatalogCategoryService catalogCategoryService;
	@Autowired
	private CatalogCoinService catalogCoinService;
	@Autowired
	private CatalogColorService catalogColorService;
	@Autowired
	private CatalogContactMethodService catalogContactMethodService;
	@Autowired
	private CatalogMenuService catalogMenuService;
	@Autowired
	private CatalogProductService catalogProductService;
	@Autowired
	private CatalogRolService catalogRolService;
	@Autowired
	private CatalogSubCategoryService catalogSubCategoryService;
	@Autowired
	private CommonProperties commonProperties;
	
	@PayloadRoot(localPart = "MaintainCatalogMenuIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public MaintainCatalogMenuOut maintainCatalogMenu(@RequestPayload MaintainCatalogMenuIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		MaintainCatalogMenuOut response = new MaintainCatalogMenuOut();	
		try {
			response = catalogMenuService.createCatalogMenuService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainCatalogMenu"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "ConsultCatalogMenuIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public ConsultCatalogMenuOut consultCatalogMenu(@RequestPayload ConsultCatalogMenuIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		ConsultCatalogMenuOut response = new ConsultCatalogMenuOut();	
		try {
			response = catalogMenuService.consultCatalogMenuService(Long.valueOf(String.valueOf(request.getIdMenu())), request.getIdStart(), request.getIdEnd(), request.getOrder(), request.getDescMenu(), request.getStatusFlag());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultCatalogMenu"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "MaintainCatalogContactMethodIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public MaintainCatalogContactMethodOut maintainCatalogContactMethod(@RequestPayload MaintainCatalogContactMethodIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		MaintainCatalogContactMethodOut response = new MaintainCatalogContactMethodOut();	
		try {
			response = catalogContactMethodService.createCatalogContactMethodService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainCatalogContactMethod"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "ConsultCatalogContactMethodIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public ConsultCatalogContactMethodOut consultCatalogContactMethod(@RequestPayload ConsultCatalogContactMethodIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		ConsultCatalogContactMethodOut response = new ConsultCatalogContactMethodOut();	
		try {
			response = catalogContactMethodService.consultCatalogContactMethodService(Long.valueOf(String.valueOf(request.getIdContactMethod())), request.getIdStart(), request.getIdEnd(), request.getOrder(), request.getDescContactMethod(), request.getStatusFlag());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultCatalogContactMethod"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "MaintainCatalogAccountIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public MaintainCatalogAccountOut maintainCatalogAccount(@RequestPayload MaintainCatalogAccountIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		MaintainCatalogAccountOut response = new MaintainCatalogAccountOut();	
		try {
			response = catalogAccountService.createCatalogAccountService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainCatalogAccount"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "ConsultCatalogAccountIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public ConsultCatalogAccountOut consultCatalogAccount(@RequestPayload ConsultCatalogAccountIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		ConsultCatalogAccountOut response = new ConsultCatalogAccountOut();	
		try {
			response = catalogAccountService.consultCatalogAccountService(Long.valueOf(String.valueOf(request.getIdAccount())), request.getIdStart(), request.getIdEnd(), request.getOrder(), request.getDescAccount(), request.getStatusFlag());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultCatalogAccount"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "MaintainCatalogCoinIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public MaintainCatalogCoinOut maintainCatalogCoin(@RequestPayload MaintainCatalogCoinIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		MaintainCatalogCoinOut response = new MaintainCatalogCoinOut();	
		try {
			response = catalogCoinService.createCatalogCoinService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainCatalogCoin"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "ConsultCatalogCoinIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public ConsultCatalogCoinOut consultCatalogCoin(@RequestPayload ConsultCatalogCoinIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		ConsultCatalogCoinOut response = new ConsultCatalogCoinOut();	
		try {
			response = catalogCoinService.consultCatalogCoinService(Long.valueOf(String.valueOf(request.getIdCoin())), request.getIdStart(), request.getIdEnd(), request.getOrder(), request.getDescCoin(), request.getStatusFlag());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultCatalogCoin"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "MaintainCatalogColorIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public MaintainCatalogColorOut maintainCatalogColor(@RequestPayload MaintainCatalogColorIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		MaintainCatalogColorOut response = new MaintainCatalogColorOut();	
		try {
			response = catalogColorService.createCatalogColorService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainCatalogColor"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "ConsultCatalogColorIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public ConsultCatalogColorOut consultCatalogColor(@RequestPayload ConsultCatalogColorIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		ConsultCatalogColorOut response = new ConsultCatalogColorOut();	
		try {
			response = catalogColorService.consultCatalogColorService(Long.valueOf(String.valueOf(request.getIdColor())), request.getIdStart(), request.getIdEnd(), request.getOrder(), request.getDescColor(), request.getStatusFlag());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultCatalogColor"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "MaintainCatalogProductIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public MaintainCatalogProductOut maintainCatalogProduct(@RequestPayload MaintainCatalogProductIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		MaintainCatalogProductOut response = new MaintainCatalogProductOut();	
		try {
			response = catalogProductService.createCatalogProductService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainCatalogProduct"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "ConsultCatalogProductIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public ConsultCatalogProductOut consultCatalogProduct(@RequestPayload ConsultCatalogProductIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		ConsultCatalogProductOut response = new ConsultCatalogProductOut();	
		try {
			response = catalogProductService.consultCatalogProductService(Long.valueOf(String.valueOf(request.getIdProduct())), Long.valueOf(String.valueOf(request.getIdSubCategory())), request.getIdStart(), request.getIdEnd(), request.getOrder(), request.getDescProduct(), request.getStatusFlag());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultCatalogProduct"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "MaintainCatalogRolIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  
	public MaintainCatalogRolOut maintainCatalogRol(@RequestPayload MaintainCatalogRolIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		MaintainCatalogRolOut response = new MaintainCatalogRolOut();	
		try {
			response = catalogRolService.maintainCatalogRolService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainCatalogRol"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "ConsultCatalogRolIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public ConsultCatalogRolOut consultCatalogRol(@RequestPayload ConsultCatalogRolIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		ConsultCatalogRolOut response = new ConsultCatalogRolOut();	
		try {
			response = catalogRolService.consultCatalogRolService(Long.valueOf(String.valueOf(request.getIdRol())), request.getIdStart(), request.getIdEnd(), request.getOrder(), request.getDescRol(), request.getStatusFlag());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultCatalogRol"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "MaintainCatalogCategoryIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public MaintainCatalogCategoryOut maintainCatalogCategory(@RequestPayload MaintainCatalogCategoryIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		MaintainCatalogCategoryOut response = new MaintainCatalogCategoryOut();	
		try {
			response = catalogCategoryService.createCatalogCategoryService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainCatalogCategory"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "ConsultCatalogCategoryIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public ConsultCatalogCategoryOut consultCatalogCategory(@RequestPayload ConsultCatalogCategoryIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		ConsultCatalogCategoryOut response = new ConsultCatalogCategoryOut();	
		try {
			response = catalogCategoryService.consultCatalogCategoryService(request.getIdCategory(), request.getIdStart(), request.getIdEnd(), request.getOrder(), request.getDescCategory(), request.getStatusFlag());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultCatalogCategory"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "MaintainCatalogSubCategoryIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public MaintainCatalogSubCategoryOut maintainCatalogSubCategory(@RequestPayload MaintainCatalogSubCategoryIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		MaintainCatalogSubCategoryOut response = new MaintainCatalogSubCategoryOut();	
		try {
			response = catalogSubCategoryService.createCatalogSubCategoryService(request);
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("maintainCatalogSubCategory"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	@PayloadRoot(localPart = "ConsultCatalogSubCategoryIn", namespace = TARGET_NAMESPACE)
	@ResponsePayload  public ConsultCatalogSubCategoryOut consultCatalogSubCategory(@RequestPayload ConsultCatalogSubCategoryIn request, MessageContext messageContext) throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		ConsultCatalogSubCategoryOut response = new ConsultCatalogSubCategoryOut();	
		try {
			response = catalogSubCategoryService.consultCatalogSubCategoryService(request.getIdSubCategory(), request.getIdCategory(), request.getIdStart(), request.getIdEnd(), request.getOrder(), request.getDescSubCategory(), request.getStatusFlag());
			SoapCommonElement.addHeaderResponse(messageContext, getDefaultHeaderResponseVo("consultCatalogSubCategory"));
		}catch(CustomGenericException ex){
			throw new ServiceFaultException(ex.getMessage(), ex.getExcepcionGenerica(), messageContext, commonProperties);
		}catch(Exception e){
		    throw new ServiceFaultException(e.getMessage(), e, messageContext, commonProperties);
		}
		return response;
	}
	
	public void setCatalogAccountService(CatalogAccountService catalogAccountService) {
		this.catalogAccountService = catalogAccountService;
	}

	public void setCatalogCategoryService(CatalogCategoryService catalogCategoryService) {
		this.catalogCategoryService = catalogCategoryService;
	}

	public void setCatalogCoinService(CatalogCoinService catalogCoinService) {
		this.catalogCoinService = catalogCoinService;
	}

	public void setCatalogColorService(CatalogColorService catalogColorService) {
		this.catalogColorService = catalogColorService;
	}

	public void setCatalogContactMethodService(CatalogContactMethodService catalogContactMethodService) {
		this.catalogContactMethodService = catalogContactMethodService;
	}

	public void setCatalogMenuService(CatalogMenuService catalogMenuService) {
		this.catalogMenuService = catalogMenuService;
	}

	public void setCatalogProductService(CatalogProductService catalogProductService) {
		this.catalogProductService = catalogProductService;
	}

	public void setCatalogRolService(CatalogRolService catalogRolService) {
		this.catalogRolService = catalogRolService;
	}

	public void setCatalogSubCategoryService(CatalogSubCategoryService catalogSubCategoryService) {
		this.catalogSubCategoryService = catalogSubCategoryService;
	}

	public void setCommonProperties(CommonProperties commonProperties) {
		this.commonProperties = commonProperties;
	}

	public HeaderResponseType getDefaultHeaderResponseVo(String metodo) throws Exception {
		HeaderResponseType headerResponseVo = new HeaderResponseType();
        EstadoRespuestaType estadoRespuestaVo = new EstadoRespuestaType();
		try {
	        estadoRespuestaVo.setIdOperacion(String.valueOf(CatalogManagerEndpoint.class.hashCode()));
	        estadoRespuestaVo.setMetodo(metodo);
	        estadoRespuestaVo.setClase(CatalogManagerEndpoint.class.getName());
	        estadoRespuestaVo.setMensajeDetallado(commonProperties.getIdEstatusSuccessMsg());
	        estadoRespuestaVo.setNivelSegRequerido(commonProperties.getIdNivelRequeridoWeb());
	        headerResponseVo.setEstadoRespuesta(estadoRespuestaVo);
			headerResponseVo.setId(String.valueOf(commonProperties.getIdEstatusSuccess()));
	        headerResponseVo.setEstatus(commonProperties.getIdEstatusSuccessCodigo());
	        headerResponseVo.setMensaje(commonProperties.getIdEstatusSuccessMsg());
	        headerResponseVo.setTokenOperacion(String.valueOf(CatalogManagerEndpoint.class.hashCode()));
	        headerResponseVo.setFechaHora(DateUtilsCommons.toXMLGregorianCalendar(new Date()));
		}catch(Exception ex){
			throw new ServiceFaultException(ex.getMessage(), ex);
		}
		return headerResponseVo;
	}
}
