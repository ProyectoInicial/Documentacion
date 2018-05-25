package com.loam.framework.core.customer.persistence.contact.vo;

import java.math.BigInteger;

import com.loam.framework.core.customer.persistence.common.ElementosComunesVo;

public class ContactMethodVo {
	private BigInteger idContactMethod;
	private BigInteger idPerson;
	private BigInteger idContactMethotCat;
	private String refNum;
	protected ElementosComunesVo elementosComunesVo;

	public BigInteger getIdContactMethod() {
		return idContactMethod;
	}

	public void setIdContactMethod(BigInteger idContactMethod) {
		this.idContactMethod = idContactMethod;
	}

	public BigInteger getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(BigInteger idPerson) {
		this.idPerson = idPerson;
	}

	public BigInteger getIdContactMethotCat() {
		return idContactMethotCat;
	}

	public void setIdContactMethotCat(BigInteger idContactMethotCat) {
		this.idContactMethotCat = idContactMethotCat;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public ElementosComunesVo getElementosComunesVo() {
		return elementosComunesVo;
	}

	public void setElementosComunesVo(ElementosComunesVo elementosComunesVo) {
		this.elementosComunesVo = elementosComunesVo;
	}

}
