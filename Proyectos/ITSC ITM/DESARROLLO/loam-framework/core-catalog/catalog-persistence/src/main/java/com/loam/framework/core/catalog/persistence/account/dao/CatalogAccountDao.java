package com.loam.framework.core.catalog.persistence.account.dao;

import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogAccount;

public interface CatalogAccountDao {
	CatalogAccount createCatalogAccountDao(CatalogAccount catalogAccount) throws Exception;
	CatalogAccount modifyCatalogAccountDao(CatalogAccount catalogAccount) throws Exception;
	List<CatalogAccount> consultCatalogAccountDao(long idAccount, int startReg, int endReg, String order, String descAccount, int statusFlag) throws Exception;
	int countCatalogAccountDao(long idAccount, int startReg, int endReg, String order, String descAccount, int statusFlag) throws Exception;
	int existCatalogAccountDao(long idAccount) throws Exception;
}
