package com.loam.framework.core.customer.restservice.alias;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserAliasOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserAliasOut;
import com.loam.framework.core.customer.restservice.common.CommonRestOperations;
import com.loam.framework.core.customer.restservice.exception.CustomHandlerException;

public class AliasDataOperations {
	public static String getResponse(ConsultUserAliasOut maintainUserAliasOut) {
		String cadResponse = null;
		Map<String, Object> message = new HashMap<String, Object>();

		Map<String, Object> json1 = new HashMap<String, Object>();
		json1.put("headerResponse", CommonRestOperations.headerResponseExito());
		json1.put("dataResult", message);
		message.put("data", maintainUserAliasOut);
		message.put("total", maintainUserAliasOut.getTotalRegs());
		ObjectMapper mapper = new ObjectMapper();
		try {
			cadResponse = mapper.writeValueAsString(json1);
		} catch (IOException e) {
			throw new CustomHandlerException(e.getMessage(), e);
		} catch (CustomHandlerException ex) {
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		} catch (Exception ext) {
			throw new CustomHandlerException(ext.getMessage(), ext);
		}
		return cadResponse;
	}
	public static String getResponseUserAlias(MaintainUserAliasOut maintainUserAliasOut) {
		String cadResponse = null;
		Map<String, Object> message = new HashMap<String, Object>();

		Map<String, Object> json1 = new HashMap<String, Object>();
		json1.put("headerResponse", CommonRestOperations.headerResponseExito());
		json1.put("dataResult", message);
		message.put("data", maintainUserAliasOut);
		message.put("total", 1);
		ObjectMapper mapper = new ObjectMapper();
		try {
			cadResponse = mapper.writeValueAsString(json1);
		} catch (IOException e) {
			throw new CustomHandlerException(e.getMessage(), e);
		} catch (CustomHandlerException ex) {
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		} catch (Exception ext) {
			throw new CustomHandlerException(ext.getMessage(), ext);
		}
		return cadResponse;
	}
}
