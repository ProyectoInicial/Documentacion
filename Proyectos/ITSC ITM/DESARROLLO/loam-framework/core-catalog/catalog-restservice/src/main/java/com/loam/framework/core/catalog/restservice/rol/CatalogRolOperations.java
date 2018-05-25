package com.loam.framework.core.catalog.restservice.rol;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogRol;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogRolOut;
import com.loam.framework.core.catalog.restservice.common.CommonRestOperations;
import com.loam.framework.core.catalog.restservice.exception.CustomHandlerException;

public class CatalogRolOperations {

	public static String getResponseCatalogoTol(ConsultCatalogRolOut consultarCatalogoRolOut) {
		String dataResponse = null;
		try {
			dataResponse = CatalogRolOperations.getResponse(consultarCatalogoRolOut);
		} catch (CustomHandlerException ex) {
			ex.printStackTrace();
			throw new CustomHandlerException(ex.getIdMensage(), ex.getEstatusMensage(), ex.getMessage(), ex);
		}catch (Exception e) {
			throw new CustomHandlerException(e.getMessage(), e);
		}
		return dataResponse;
	}

	public static String getResponse(ConsultCatalogRolOut consultarCatalogoRolOut) {
		String cadResponse = null;
		Map<String, Object> message = new HashMap<String, Object>();

		Map<String, Object> json1 = new HashMap<String, Object>();
		json1.put("headerResponse", CommonRestOperations.headerResponseExito());
		json1.put("dataResult", message);
		message.put("data", consultarCatalogoRolOut.getCatalogRol());
		message.put("total", consultarCatalogoRolOut.getTotalRegs());
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

	public static String getResponseObj(CatalogRol catalogoRolOut) {
		String cadResponse = null;
		Map<String, Object> message = new HashMap<String, Object>();

		Map<String, Object> json1 = new HashMap<String, Object>();
		json1.put("headerResponse", CommonRestOperations.headerResponseExito());
		json1.put("dataResult", message);
		message.put("data", catalogoRolOut);
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
