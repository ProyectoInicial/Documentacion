package com.loam.framework.core.security.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityAliasIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityEmailIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityLoginIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityTokenIn;
import com.loam.framework.core.security.restservice.common.CommonRestOperations;
import com.loam.framework.core.security.restservice.common.RestConstants;
import com.loam.framework.core.security.restservice.exception.CustomHandlerException;
import com.loam.framework.core.security.restservice.login.SecurityLoginOperations;
import com.loam.framework.core.security.services.security.login.DataLoginService;

@RestController
@RequestMapping(value = RestConstants.SEGURIDAD_PATH)
public class CorporateSecurityService {

	@Autowired
	private DataLoginService dataLoginService;

	@RequestMapping(value = RestConstants.SEGURIDAD_CONSULTAR_LOGIN, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> consultSecurityLoginOut(@RequestBody SecurityLoginIn securityLoginIn) throws Exception {
		String dataResponse = null;
		try {
			dataResponse  = SecurityLoginOperations.getResponseSecurity(String.valueOf(dataLoginService.securityLoginService(securityLoginIn)));
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.SEGURIDAD_CONSULTAR_TOKEN, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> consultSecurityTokenOut(@RequestBody SecurityTokenIn securityTokenIn) throws Exception {
		String dataResponse = null;
		try {
			dataResponse  = SecurityLoginOperations.getResponseSecurity(String.valueOf(dataLoginService.securityTokenService(securityTokenIn)));
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.SEGURIDAD_CONSULTAR_ALIAS, method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> consultSecurityAliasOut(@RequestBody SecurityAliasIn securityAliasIn) throws Exception {
		String dataResponse = null;
		try {
			dataResponse  = SecurityLoginOperations.getResponseSecurity(String.valueOf(dataLoginService.securityAliasService(securityAliasIn)));
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	@RequestMapping(value = RestConstants.SEGURIDAD_CONSULTAR_EMAIL, method = RequestMethod.GET)
	public ResponseEntity<String> consultSecurityEmailOut(@RequestBody SecurityEmailIn securityEmailIn) throws Exception {
		String dataResponse = null;
		try {
			dataResponse  = SecurityLoginOperations.getResponseSecurity(String.valueOf(dataLoginService.securityEmailService(securityEmailIn)));
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return new ResponseEntity<String>(dataResponse, CommonRestOperations.getHeader(), HttpStatus.OK);
	}
	
	public void setDataLoginService(DataLoginService dataLoginService) {
		this.dataLoginService = dataLoginService;
	}
}
