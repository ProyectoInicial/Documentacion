package com.loam.framework.core.catalog.services.menu;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogMenuOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogRolMenuOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogMenuIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogMenuOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogRolMenuIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogRolMenuOut;

public interface CatalogMenuService {
	MaintainCatalogMenuOut createCatalogMenuService(MaintainCatalogMenuIn maintainCatalogMenuIn) throws Exception;
	ConsultCatalogMenuOut consultCatalogMenuService(long idMenu, int startReg, int endReg, String order, String descMenu, int statusFlag) throws Exception;
	int countCatalogMenuService(long idMenu, int startReg, int endReg, String order, String descMenu, int statusFlag) throws Exception;
	int existCatalogMenuService(long idMenu) throws Exception;
	
	MaintainCatalogRolMenuOut createCatalogRolMenuService(MaintainCatalogRolMenuIn maintainCatalogRolMenuIn) throws Exception;
	ConsultCatalogRolMenuOut consultCatalogRolMenuService(long idRolMenu, int startReg, int endReg, String order, String descRolMenu, int statusFlag) throws Exception;
	int countCatalogRolMenuService(long idRolMenu, int startReg, int endReg, String order, String descRolMenu, int statusFlag) throws Exception;
	int existCatalogRolMenuService(long idRolMenu) throws Exception;
}
