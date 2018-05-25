package com.loam.framework.core.catalog.persistence.product.vo;

import java.math.BigInteger;

import com.loam.framework.core.catalog.persistence.common.ElementosComunesVo;

public class CatalogProductVo {
	protected BigInteger idProduct;
	protected String nameProduct;
	protected String descProduct;
	protected BigInteger idSubCategory;
	protected ElementosComunesVo elementosComunesVo;

	public BigInteger getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(BigInteger idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getDescProduct() {
		return descProduct;
	}

	public void setDescProduct(String descProduct) {
		this.descProduct = descProduct;
	}

	public BigInteger getIdSubCategory() {
		return idSubCategory;
	}

	public void setIdSubCategory(BigInteger idSubCategory) {
		this.idSubCategory = idSubCategory;
	}

	public ElementosComunesVo getElementosComunesVo() {
		return elementosComunesVo;
	}

	public void setElementosComunesVo(ElementosComunesVo elementosComunesVo) {
		this.elementosComunesVo = elementosComunesVo;
	}

}
