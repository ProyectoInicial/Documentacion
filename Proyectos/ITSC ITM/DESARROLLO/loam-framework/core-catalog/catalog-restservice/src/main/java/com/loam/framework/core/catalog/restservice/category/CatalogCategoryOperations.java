package com.loam.framework.core.catalog.restservice.category;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogCategory;
import com.loam.framework.core.catalog.restservice.common.CommonRestOperations;
import com.loam.framework.core.catalog.restservice.exception.CustomHandlerException;

public class CatalogCategoryOperations {
	public static String getResponse(List<CatalogCategory> consultarCatalogoCategoriaOut) {
		String cadResponse = null;
		Map<String, Object> message = new HashMap<String, Object>();

		Map<String, Object> json1 = new HashMap<String, Object>();
		json1.put("headerResponse", CommonRestOperations.headerResponseExito());
		json1.put("dataResult", message);
		message.put("data", consultarCatalogoCategoriaOut);
		message.put("total", consultarCatalogoCategoriaOut.size());
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
	
	public static String getResponseObj(CatalogCategory catalogoCategoriaOut) {
		String cadResponse = null;
		Map<String, Object> message = new HashMap<String, Object>();

		Map<String, Object> json1 = new HashMap<String, Object>();
		json1.put("headerResponse", CommonRestOperations.headerResponseExito());
		json1.put("dataResult", message);
		message.put("data", catalogoCategoriaOut);
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
