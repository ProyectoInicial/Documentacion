package com.loam.framework.core.catalog.persistence.subcategory.vo;

import java.math.BigInteger;
import java.util.List;

import com.loam.framework.core.catalog.persistence.common.ElementosComunesVo;
import com.loam.framework.core.catalog.persistence.product.vo.CatalogProductVo;

public class CatalogSubCategoryVo {
	protected BigInteger idSubCategory;
	protected BigInteger idCategory;
	protected String descSubCategory;
	protected ElementosComunesVo elementosComunesVo;
	protected List<CatalogProductVo> listCatalogProductVo;

	public BigInteger getIdSubCategory() {
		return idSubCategory;
	}

	public void setIdSubCategory(BigInteger idSubCategory) {
		this.idSubCategory = idSubCategory;
	}

	public BigInteger getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(BigInteger idCategory) {
		this.idCategory = idCategory;
	}

	public String getDescSubCategory() {
		return descSubCategory;
	}

	public void setDescSubCategory(String descSubCategory) {
		this.descSubCategory = descSubCategory;
	}

	public ElementosComunesVo getElementosComunesVo() {
		return elementosComunesVo;
	}

	public void setElementosComunesVo(ElementosComunesVo elementosComunesVo) {
		this.elementosComunesVo = elementosComunesVo;
	}

	public List<CatalogProductVo> getListCatalogProductVo() {
		return listCatalogProductVo;
	}

	public void setListCatalogProductVo(List<CatalogProductVo> listCatalogProductVo) {
		this.listCatalogProductVo = listCatalogProductVo;
	}

}
