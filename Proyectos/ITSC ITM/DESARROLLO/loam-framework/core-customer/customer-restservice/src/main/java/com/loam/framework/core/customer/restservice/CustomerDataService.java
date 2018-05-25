package com.loam.framework.core.customer.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultDataPersonOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserAliasOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserContactAddressOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserContactMethodOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainDataPersonIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserAliasIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactAddressIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactMethodIn;
import com.loam.framework.core.customer.persistence.common.CommonProperties;
import com.loam.framework.core.customer.restservice.address.PersonContactAddressOperations;
import com.loam.framework.core.customer.restservice.alias.AliasDataOperations;
import com.loam.framework.core.customer.restservice.common.CommonRestOperations;
import com.loam.framework.core.customer.restservice.common.GenericConsultaRequest;
import com.loam.framework.core.customer.restservice.common.RestConstants;
import com.loam.framework.core.customer.restservice.contact.PersonContactMethodOperations;
import com.loam.framework.core.customer.restservice.exception.CustomHandlerException;
import com.loam.framework.core.customer.restservice.person.PersonDataOperations;
import com.loam.framework.core.customer.services.address.PersonContactAddressService;
import com.loam.framework.core.customer.services.alias.AliasDataService;
import com.loam.framework.core.customer.services.contact.PersonContactMethodService;
import com.loam.framework.core.customer.services.person.PersonDataService;

@RestController
@RequestMapping(value = RestConstants.CUSTOMER_PATH)
public class CustomerDataService {

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

	@RequestMapping(value = RestConstants.CUSTOMER_ADDRESS_CREAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> maintainPersonContactAddressOut(@RequestBody MaintainUserContactAddressIn userContactAddressIn) throws Exception {
		String dataResponse = null;
		try {
			dataResponse  = PersonContactAddressOperations.getResponsePersonContactAddress(personContactAddressService.maintainPersonContactAddressService(userContactAddressIn));
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.CUSTOMER_ADDRESS_CONSULTAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> consultPersonContactAddressOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultUserContactAddressOut consultUserContactAddressOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(),
						commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultUserContactAddressOut = personContactAddressService.consultPersonContactAddressService(request.getId(), request.getIdSub(), request.getIdStart(), request.getIdEnd(), request.getOrdenar());
			dataResponse = PersonContactAddressOperations.getResponse(consultUserContactAddressOut);
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.CUSTOMER_ALIAS_CREAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String>  maintainUserAliasOut(@RequestBody MaintainUserAliasIn maintainUserAliasIn) throws Exception {
		String dataResponse = null;
		try {
			dataResponse  = AliasDataOperations.getResponseUserAlias(aliasDataService.maintainAliasDataService(maintainUserAliasIn));
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.CUSTOMER_ALIAS_CONSULTAR, method = {RequestMethod.GET})
	public ResponseEntity<String> consultUserAliasOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultUserAliasOut consultUserAliasOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(),
						commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultUserAliasOut = aliasDataService.consultAliasDataService(request.getTokenUser(), request.getAliasUser(), request.getEmailUser(), request.getIdStart(), request.getIdEnd(), request.getOrdenar());
			dataResponse = AliasDataOperations.getResponse(consultUserAliasOut);
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.CUSTOMER_CTMETHOD_CREAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String>  maintainUserContactMethodOut(@RequestBody MaintainUserContactMethodIn maintainUserContactMethodIn) throws Exception {
		String dataResponse = null;
		try {
			dataResponse  = PersonContactMethodOperations.getResponseContactMethod(personContactMethodService.maintainUserContactMethodService(maintainUserContactMethodIn));
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.CUSTOMER_CTMETHOD_CONSULTAR, method = {RequestMethod.GET})
	public ResponseEntity<String> consultUserContactMethodOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultUserContactMethodOut consultUserContactMethodOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(),
						commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultUserContactMethodOut = personContactMethodService.consultUserContactMethodService(request.getId(), request.getIdSub(), request.getIdSubSub(), request.getIdStart(), request.getIdEnd(), request.getOrdenar());
			dataResponse = PersonContactMethodOperations.getResponse(consultUserContactMethodOut);
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.INFO_CUSTOMER_CREAR, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String>  maintainDataPersonOut(@RequestBody MaintainDataPersonIn maintainDataPersonIn) throws Exception {
		String dataResponse = null;
		try {
			dataResponse  = PersonDataOperations.getResponsePersonData(personDataService.maintainDataPersonService(maintainDataPersonIn));
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.INFO_CUSTOMER_CONSULTAR, method = {RequestMethod.GET})
	public ResponseEntity<String> consultDataPersonOut(@RequestBody GenericConsultaRequest request) throws Exception {
		ConsultDataPersonOut consultDataPersonOut = null;
		String dataResponse = null;
		try {
			 if(request == null || request.getIdEnd() == 0){
				throw new CustomHandlerException(commonProperties.getIdEstatusNull(),
						commonProperties.getIdEstatusNullCodigo(), commonProperties.getIdEstatusNullMsg());
			 }
			 consultDataPersonOut = personDataService.consultDataPersonService(request.getIdPerson(), request.getIdPersonCat(), request.getTokenPerson(), request.getIdUser(), request.getIdStart(), request.getIdEnd(), request.getOrdenar());
			dataResponse = PersonDataOperations.getResponse(consultDataPersonOut);
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
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
	
}
