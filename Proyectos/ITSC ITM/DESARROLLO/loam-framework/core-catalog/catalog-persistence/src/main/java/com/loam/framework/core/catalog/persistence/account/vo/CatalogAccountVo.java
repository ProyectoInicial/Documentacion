package com.loam.framework.core.catalog.persistence.account.vo;

import java.math.BigInteger;

import com.loam.framework.core.catalog.persistence.common.ElementosComunesVo;

public class CatalogAccountVo {
	protected BigInteger idAccount;
	protected String descAccount;
	protected String typeAccount;
	protected ElementosComunesVo elementosComunesVo;

	public BigInteger getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(BigInteger idAccount) {
		this.idAccount = idAccount;
	}

	public String getDescAccount() {
		return descAccount;
	}

	public void setDescAccount(String descAccount) {
		this.descAccount = descAccount;
	}

	public ElementosComunesVo getElementosComunesVo() {
		return elementosComunesVo;
	}

	public void setElementosComunesVo(ElementosComunesVo elementosComunesVo) {
		this.elementosComunesVo = elementosComunesVo;
	}

	public String getTypeAccount() {
		return typeAccount;
	}

	public void setTypeAccount(String typeAccount) {
		this.typeAccount = typeAccount;
	}

}
