package com.loam.framework.core.catalog.persistence.coin.vo;

import java.math.BigInteger;

import com.loam.framework.core.catalog.persistence.common.ElementosComunesVo;

public class CatalogCoinVo {
	protected BigInteger idCoin;
	protected String codCoin;
	protected String descCoin;
	protected ElementosComunesVo elementosComunesVo;

	public BigInteger getIdCoin() {
		return idCoin;
	}

	public void setIdCoin(BigInteger idCoin) {
		this.idCoin = idCoin;
	}

	public String getCodCoin() {
		return codCoin;
	}

	public void setCodCoin(String codCoin) {
		this.codCoin = codCoin;
	}

	public String getDescCoin() {
		return descCoin;
	}

	public void setDescCoin(String descCoin) {
		this.descCoin = descCoin;
	}

	public ElementosComunesVo getElementosComunesVo() {
		return elementosComunesVo;
	}

	public void setElementosComunesVo(ElementosComunesVo elementosComunesVo) {
		this.elementosComunesVo = elementosComunesVo;
	}

}
