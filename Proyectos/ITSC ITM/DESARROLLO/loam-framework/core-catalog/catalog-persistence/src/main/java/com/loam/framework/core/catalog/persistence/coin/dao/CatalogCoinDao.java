package com.loam.framework.core.catalog.persistence.coin.dao;

import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogCoin;

public interface CatalogCoinDao {
	CatalogCoin createCatalogCoinDao(CatalogCoin catalogCoin) throws Exception;
	CatalogCoin modifyCatalogCoinDao(CatalogCoin catalogCoin) throws Exception;
	List<CatalogCoin> consultCatalogCoinDao(long idCoin, int startReg, int endReg, String order, String descCoin, int statusFlag) throws Exception;
	int countCatalogCoinDao(long idCoin, int startReg, int endReg, String order, String descCoin, int statusFlag) throws Exception;
	int existCatalogCoinDao(long idCoin) throws Exception;
}
