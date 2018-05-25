package com.loam.framework.core.catalog.services.coin;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogCoinOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCoinIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCoinOut;

public interface CatalogCoinService {
	MaintainCatalogCoinOut createCatalogCoinService(MaintainCatalogCoinIn maintainCatalogCoinIn) throws Exception;
	ConsultCatalogCoinOut consultCatalogCoinService(long idCoin, int startReg, int endReg, String order, String descCoin, int statusFlag) throws Exception;
	int countCatalogCoinService(long idCoin, int startReg, int endReg, String order, String descCoin, int statusFlag) throws Exception;
	int existCatalogCoinService(long idCoin) throws Exception;
}
