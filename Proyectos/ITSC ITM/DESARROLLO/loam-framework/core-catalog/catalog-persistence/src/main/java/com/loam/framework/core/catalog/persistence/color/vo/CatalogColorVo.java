package com.loam.framework.core.catalog.persistence.color.vo;

import java.math.BigInteger;

import com.loam.framework.core.catalog.persistence.common.ElementosComunesVo;

public class CatalogColorVo {
	protected BigInteger idColor;
	protected String descColor;
	protected ElementosComunesVo elementosComunesVo;

	public BigInteger getIdColor() {
		return idColor;
	}

	public void setIdColor(BigInteger idColor) {
		this.idColor = idColor;
	}

	public String getDescColor() {
		return descColor;
	}

	public void setDescColor(String descColor) {
		this.descColor = descColor;
	}

	public ElementosComunesVo getElementosComunesVo() {
		return elementosComunesVo;
	}

	public void setElementosComunesVo(ElementosComunesVo elementosComunesVo) {
		this.elementosComunesVo = elementosComunesVo;
	}

}
