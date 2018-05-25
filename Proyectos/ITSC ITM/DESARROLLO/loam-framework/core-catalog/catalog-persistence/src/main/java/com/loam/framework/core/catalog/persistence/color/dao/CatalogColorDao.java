package com.loam.framework.core.catalog.persistence.color.dao;

import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogColor;

public interface CatalogColorDao {
	CatalogColor createCatalogColorDao(CatalogColor catalogColor) throws Exception;
	CatalogColor modifyCatalogColorDao(CatalogColor catalogColor) throws Exception;
	List<CatalogColor> consultCatalogColorDao(long idColor, int startReg, int endReg, String order, String descColor, int statusFlag) throws Exception;
	int countCatalogColorDao(long idColor, int startReg, int endReg, String order, String descColor, int statusFlag) throws Exception;
	int existCatalogColorDao(long idColor) throws Exception;
}
