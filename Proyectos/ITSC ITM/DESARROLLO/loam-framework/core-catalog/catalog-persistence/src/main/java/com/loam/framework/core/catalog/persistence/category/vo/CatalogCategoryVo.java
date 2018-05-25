package com.loam.framework.core.catalog.persistence.category.vo;

import java.math.BigInteger;
import java.util.List;

import com.loam.framework.core.catalog.persistence.common.ElementosComunesVo;
import com.loam.framework.core.catalog.persistence.subcategory.vo.CatalogSubCategoryVo;

public class CatalogCategoryVo {
	protected BigInteger idCategory;
	protected String descCategory;
	protected ElementosComunesVo elementosComunesVo;
	protected List<CatalogSubCategoryVo> listCatalogSubCategoryVo;

	public BigInteger getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(BigInteger idCategory) {
		this.idCategory = idCategory;
	}

	public String getDescCategory() {
		return descCategory;
	}

	public void setDescCategory(String descCategory) {
		this.descCategory = descCategory;
	}

	public ElementosComunesVo getElementosComunesVo() {
		return elementosComunesVo;
	}

	public void setElementosComunesVo(ElementosComunesVo elementosComunesVo) {
		this.elementosComunesVo = elementosComunesVo;
	}

	public List<CatalogSubCategoryVo> getListCatalogSubCategoryVo() {
		return listCatalogSubCategoryVo;
	}

	public void setListCatalogSubCategoryVo(List<CatalogSubCategoryVo> listCatalogSubCategoryVo) {
		this.listCatalogSubCategoryVo = listCatalogSubCategoryVo;
	}

}
