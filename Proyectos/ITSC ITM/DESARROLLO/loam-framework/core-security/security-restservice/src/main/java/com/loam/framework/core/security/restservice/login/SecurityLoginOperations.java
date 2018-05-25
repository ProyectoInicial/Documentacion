package com.loam.framework.core.security.restservice.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.loam.framework.core.security.restservice.common.CommonRestOperations;
import com.loam.framework.core.security.restservice.exception.CustomHandlerException;

public class SecurityLoginOperations {
	public static String getResponseSecurity(String cadena) {
		String cadResponse = null;
		Map<String, Object> message = new HashMap<String, Object>();

		Map<String, Object> json1 = new HashMap<String, Object>();
		json1.put("headerResponse", CommonRestOperations.headerResponseExito());
		json1.put("dataResult", message);
		message.put("data", cadena);
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
