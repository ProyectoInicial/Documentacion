package com.loam.framework.core.catalog.services.subcategory;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogSubCategoryOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogSubCategoryIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogSubCategoryOut;

public interface CatalogSubCategoryService {
	MaintainCatalogSubCategoryOut createCatalogSubCategoryService(MaintainCatalogSubCategoryIn maintainCatalogSubCategoryIn) throws Exception;
	ConsultCatalogSubCategoryOut consultCatalogSubCategoryService(long idSubCategory, long idCategory, int startReg, int endReg, String order, String descSubCategory, int statusFlag) throws Exception;
	int countCatalogSubCategoryService(long idSubCategory, long idCategory, int startReg, int endReg, String order, String descSubCategory, int statusFlag) throws Exception;
	int existCatalogSubCategoryService(long idSubCategory) throws Exception;
}
