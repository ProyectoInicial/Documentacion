package com.loam.framework.core.catalog.persistence.menu.dao;

import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogMenu;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogRolMenu;

public interface CatalogMenuDao {
	CatalogMenu createCatalogMenuDao(CatalogMenu catalogMenu) throws Exception;
	CatalogMenu modifyCatalogMenuDao(CatalogMenu catalogMenu) throws Exception;
	List<CatalogMenu> consultCatalogMenuDao(long idMenu, int startReg, int endReg, String order, String descMenu, int statusFlag) throws Exception;
	int countCatalogMenuDao(long idMenu, int startReg, int endReg, String order, String descMenu, int statusFlag) throws Exception;
	int existCatalogMenuDao(long idMenu) throws Exception;
	
	CatalogRolMenu createCatalogRolMenuDao(CatalogRolMenu catalogRolMenu) throws Exception;
	CatalogRolMenu modifyCatalogRolMenuDao(CatalogRolMenu catalogRolMenu) throws Exception;
	List<CatalogRolMenu> consultCatalogRolMenuDao(long idRolMenu, int startReg, int endReg, String order, String descRolMenu, int statusFlag) throws Exception;
	int countCatalogRolMenuDao(long idRolMenu, int startReg, int endReg, String order, String descRolMenu, int statusFlag) throws Exception;
	int existCatalogRolMenuDao(long idRolMenu) throws Exception;

}
