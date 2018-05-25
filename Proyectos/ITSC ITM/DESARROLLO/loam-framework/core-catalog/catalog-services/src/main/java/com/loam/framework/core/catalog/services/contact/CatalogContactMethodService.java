package com.loam.framework.core.catalog.services.contact;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogContactMethodOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogContactMethodIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogContactMethodOut;

public interface CatalogContactMethodService {
	MaintainCatalogContactMethodOut createCatalogContactMethodService(MaintainCatalogContactMethodIn maintainCatalogContactMethodIn) throws Exception;
	ConsultCatalogContactMethodOut consultCatalogContactMethodService(long idContactMethod, int startReg, int endReg, String order, String descContactMethod, int statusFlag) throws Exception;
	int countCatalogContactMethodService(long idContactMethod, int startReg, int endReg, String order, String descContactMethod, int statusFlag) throws Exception;
	int existCatalogContactMethodService(long idContactMethod) throws Exception;
}
