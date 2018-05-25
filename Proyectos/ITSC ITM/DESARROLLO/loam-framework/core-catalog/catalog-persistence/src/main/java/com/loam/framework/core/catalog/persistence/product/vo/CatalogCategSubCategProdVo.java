package com.loam.framework.core.catalog.persistence.product.vo;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CatalogCategSubCategProdVo {
	protected BigInteger idCategory;
	protected String descCategory;
	protected BigInteger idSubCategory;
	protected String descSubCategory;
	protected BigInteger idProduct;
	protected String nameProduct;
	protected BigInteger idAccountProduct;
	protected BigDecimal price;
	protected BigInteger idCoin;
	protected long stock;

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

	public BigInteger getIdSubCategory() {
		return idSubCategory;
	}

	public void setIdSubCategory(BigInteger idSubCategory) {
		this.idSubCategory = idSubCategory;
	}

	public String getDescSubCategory() {
		return descSubCategory;
	}

	public void setDescSubCategory(String descSubCategory) {
		this.descSubCategory = descSubCategory;
	}

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

	public BigInteger getIdAccountProduct() {
		return idAccountProduct;
	}

	public void setIdAccountProduct(BigInteger idAccountProduct) {
		this.idAccountProduct = idAccountProduct;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigInteger getIdCoin() {
		return idCoin;
	}

	public void setIdCoin(BigInteger idCoin) {
		this.idCoin = idCoin;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

}
