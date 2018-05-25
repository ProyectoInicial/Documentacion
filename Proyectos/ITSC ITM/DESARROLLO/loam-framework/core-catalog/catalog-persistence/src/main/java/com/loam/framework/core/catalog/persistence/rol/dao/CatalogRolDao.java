package com.loam.framework.core.catalog.persistence.rol.dao;

import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogRol;

public interface CatalogRolDao {
	CatalogRol createCatalogRolDao(CatalogRol maintainCatalogRolOut) throws Exception;
	CatalogRol modifyCatalogRolDao(CatalogRol maintainCatalogRolOut) throws Exception;
	List<CatalogRol> consultCatalogRolDao(long idRol, int startReg, int endReg, String order, String descRol, int statusFlag) throws Exception;
	int countCatalogRolDao(long idRol, int startReg, int endReg, String order, String descRol, int statusFlag) throws Exception;
	int existCatalogRolDao(long idRol) throws Exception;
}
