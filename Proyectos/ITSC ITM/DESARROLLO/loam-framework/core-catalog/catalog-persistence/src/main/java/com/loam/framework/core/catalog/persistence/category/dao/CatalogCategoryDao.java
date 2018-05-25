package com.loam.framework.core.catalog.persistence.category.dao;

import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogCategory;

public interface CatalogCategoryDao {
	CatalogCategory createCatalogCategoryDao(CatalogCategory catalogCategory) throws Exception;
	CatalogCategory modifyCatalogCategoryDao(CatalogCategory catalogCategory) throws Exception;
	List<CatalogCategory> consultCatalogCategoryDao(long idCategory, int startReg, int endReg, String order, String descCategory, int statusFlag) throws Exception;
	int countCatalogCategoryDao(long idCategory, int startReg, int endReg, String order, String descCategory, int statusFlag) throws Exception;
	int existCatalogCategoryDao(long idCategory) throws Exception;
}
