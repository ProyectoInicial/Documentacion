package com.loam.framework.core.catalog.services.category;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogCategoryOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCategoryIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCategoryOut;

public interface CatalogCategoryService {
	MaintainCatalogCategoryOut createCatalogCategoryService(MaintainCatalogCategoryIn maintainCatalogCategoryIn) throws Exception;
	ConsultCatalogCategoryOut consultCatalogCategoryService(long idCategory, int startReg, int endReg, String order, String descCategory, int statusFlag) throws Exception;
	int countCatalogCategoryService(long idCategory, int startReg, int endReg, String order, String descCategory, int statusFlag) throws Exception;
	int existCatalogCategoryService(long idCategory) throws Exception;
}
