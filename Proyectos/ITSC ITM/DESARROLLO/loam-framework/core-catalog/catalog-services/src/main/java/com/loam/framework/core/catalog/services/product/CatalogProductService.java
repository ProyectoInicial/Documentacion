package com.loam.framework.core.catalog.services.product;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogProductOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogProductIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogProductOut;

public interface CatalogProductService {
	MaintainCatalogProductOut createCatalogProductService(MaintainCatalogProductIn maintainCatalogProductIn) throws Exception;
	ConsultCatalogProductOut consultCatalogProductService(long idProduct, long idSubCategory, int startReg, int endReg, String order, String descProduct, int statusFlag) throws Exception;
	int countCatalogProductService(long idProduct, long idSubCategory, int startReg, int endReg, String order, String descProduct, int statusFlag) throws Exception;
	int existCatalogProductService(long idProduct) throws Exception;
	
}
