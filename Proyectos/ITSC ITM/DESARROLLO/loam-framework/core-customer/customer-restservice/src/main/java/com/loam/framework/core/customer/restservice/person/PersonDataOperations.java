package com.loam.framework.core.customer.restservice.person;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultDataPersonOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainDataPersonOut;
import com.loam.framework.core.customer.restservice.common.CommonRestOperations;
import com.loam.framework.core.customer.restservice.exception.CustomHandlerException;

public class PersonDataOperations {
	public static String getResponse(ConsultDataPersonOut consultDataPersonOut) {
		String cadResponse = null;
		Map<String, Object> message = new HashMap<String, Object>();

		Map<String, Object> json1 = new HashMap<String, Object>();
		json1.put("headerResponse", CommonRestOperations.headerResponseExito());
		json1.put("dataResult", message);
		message.put("data", consultDataPersonOut);
		message.put("total", consultDataPersonOut.getTotalRegs());
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
	public static String getResponsePersonData(MaintainDataPersonOut maintainDataPersonOut) {
		String cadResponse = null;
		Map<String, Object> message = new HashMap<String, Object>();

		Map<String, Object> json1 = new HashMap<String, Object>();
		json1.put("headerResponse", CommonRestOperations.headerResponseExito());
		json1.put("dataResult", message);
		message.put("data", maintainDataPersonOut);
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
