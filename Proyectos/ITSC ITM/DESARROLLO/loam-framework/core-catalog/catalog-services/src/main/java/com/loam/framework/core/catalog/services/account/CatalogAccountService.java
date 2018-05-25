package com.loam.framework.core.catalog.services.account;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogAccountOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogAccountIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogAccountOut;

public interface CatalogAccountService {
	MaintainCatalogAccountOut createCatalogAccountService(MaintainCatalogAccountIn maintainCatalogAccountIn) throws Exception;
	ConsultCatalogAccountOut consultCatalogAccountService(long idAccount, int startReg, int endReg, String order, String descAccount, int statusFlag) throws Exception;
	int countCatalogAccountService(long idAccount, int startReg, int endReg, String order, String descAccount, int statusFlag) throws Exception;
	int existCatalogAccountService(long idAccount) throws Exception;
}
