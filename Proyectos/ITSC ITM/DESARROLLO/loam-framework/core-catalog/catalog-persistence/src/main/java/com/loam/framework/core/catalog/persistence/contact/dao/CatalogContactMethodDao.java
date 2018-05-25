package com.loam.framework.core.catalog.persistence.contact.dao;

import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogContactMethod;

public interface CatalogContactMethodDao {
	CatalogContactMethod createContactMethodDao(CatalogContactMethod catalogContactMethod) throws Exception;
	CatalogContactMethod modifyContactMethodDao(CatalogContactMethod catalogContactMethod) throws Exception;
	List<CatalogContactMethod> consultContactMethodDao(long idContactMethod, int startReg, int endReg, String order, String descContactMethod, int statusFlag) throws Exception;
	int countContactMethodDao(long idContactMethod, int startReg, int endReg, String order, String descContactMethod, int statusFlag) throws Exception;
	int existContactMethodDao(long idContactMethod) throws Exception;
}
