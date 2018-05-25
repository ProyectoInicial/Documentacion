package com.loam.framework.core.catalog.persistence.subcategory.dao;

import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogSubCategory;

public interface CatalogSubCategoryDao {
	CatalogSubCategory createCatalogSubCategoryDao(CatalogSubCategory catalogSubCategory) throws Exception;
	CatalogSubCategory modifyCatalogSubCategoryDao(CatalogSubCategory catalogSubCategory) throws Exception;
	List<CatalogSubCategory> consultCatalogSubCategoryDao(long idSubCategory, long idCategory, int startReg, int endReg, String order, String descSubCategory, int statusFlag) throws Exception;
	int countCatalogSubCategoryDao(long idSubCategory, long idCategory, int startReg, int endReg, String order, String descSubCategory, int statusFlag) throws Exception;
	int existCatalogSubCategoryDao(long idSubCategory) throws Exception;
}
