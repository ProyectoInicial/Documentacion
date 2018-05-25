package com.loam.framework.core.catalog.services.rol;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogRolOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogRolIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogRolOut;

public interface CatalogRolService {
	MaintainCatalogRolOut maintainCatalogRolService(MaintainCatalogRolIn maintainCatalogRolIn) throws Exception;
	ConsultCatalogRolOut consultCatalogRolService(long idRol, int startReg, int endReg, String order, String descRol, int statusFlag) throws Exception;
	int countCatalogRolService(long idRol, int startReg, int endReg, String order, String descRol, int statusFlag) throws Exception;
	int existCatalogRolService(long idRol) throws Exception;
}
