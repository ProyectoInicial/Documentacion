package com.loam.framework.core.catalog.persistence.product.dao;

import java.util.List;

import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogProduct;
import com.loam.framework.core.catalog.persistence.product.vo.CatalogCategSubCategProdVo;

public interface CatalogProductDao {
	CatalogProduct createCatalogProductDao(CatalogProduct catalogProduct) throws Exception;
	CatalogProduct modifyCatalogProductDao(CatalogProduct catalogProduct) throws Exception;
	List<CatalogProduct> consultCatalogProductDao(long idProduct, long idSubCategory, int startReg, int endReg, String order, String descProduct, int statusFlag) throws Exception;
	int countCatalogProductDao(long idProduct, long idSubCategory, int startReg, int endReg, String order, String descProduct, int statusFlag) throws Exception;
	List<CatalogProduct> consultAllCatalogProductDao(long idSubCategory, int start, int end, int statusFlag) throws Exception;
	int existCatalogProductDao(long idProduct) throws Exception;
	List<CatalogCategSubCategProdVo> consultCategSubCategProdDao(long idSubCategory, int startReg, int endReg, String order, int statusFlag) throws Exception;
}
