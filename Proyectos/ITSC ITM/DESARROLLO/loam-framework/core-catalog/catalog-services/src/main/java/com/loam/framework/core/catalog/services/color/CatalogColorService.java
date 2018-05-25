package com.loam.framework.core.catalog.services.color;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogColorOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogColorIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogColorOut;

public interface CatalogColorService {
	MaintainCatalogColorOut createCatalogColorService(MaintainCatalogColorIn maintainCatalogColorIn) throws Exception;
	ConsultCatalogColorOut consultCatalogColorService(long idColor, int startReg, int endReg, String order, String descColor, int statusFlag) throws Exception;
	int countCatalogColorService(long idColor, int startReg, int endReg, String order, String descColor, int statusFlag) throws Exception;
	int existCatalogColorService(long idColor) throws Exception;
}
