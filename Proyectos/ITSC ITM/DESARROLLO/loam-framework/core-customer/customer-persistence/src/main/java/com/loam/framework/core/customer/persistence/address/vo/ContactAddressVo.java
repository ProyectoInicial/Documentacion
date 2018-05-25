package com.loam.framework.core.customer.persistence.address.vo;

import java.math.BigInteger;

import com.loam.framework.core.customer.persistence.common.ElementosComunesVo;

public class ContactAddressVo {
	private BigInteger idContactAddress;
	private BigInteger idPerson;
	private BigInteger idAddressType;
	private BigInteger idCity;
	private BigInteger postalCode;
	private String descAddress;
	private String colonia;
	private BigInteger principalFlag;
	protected ElementosComunesVo elementosComunesVo;

	public BigInteger getIdContactAddress() {
		return idContactAddress;
	}

	public void setIdContactAddress(BigInteger idContactAddress) {
		this.idContactAddress = idContactAddress;
	}

	public BigInteger getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(BigInteger idPerson) {
		this.idPerson = idPerson;
	}

	public BigInteger getIdAddressType() {
		return idAddressType;
	}

	public void setIdAddressType(BigInteger idAddressType) {
		this.idAddressType = idAddressType;
	}

	public BigInteger getIdCity() {
		return idCity;
	}

	public void setIdCity(BigInteger idCity) {
		this.idCity = idCity;
	}

	public BigInteger getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(BigInteger postalCode) {
		this.postalCode = postalCode;
	}

	public String getDescAddress() {
		return descAddress;
	}

	public void setDescAddress(String descAddress) {
		this.descAddress = descAddress;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public BigInteger getPrincipalFlag() {
		return principalFlag;
	}

	public void setPrincipalFlag(BigInteger principalFlag) {
		this.principalFlag = principalFlag;
	}

	public ElementosComunesVo getElementosComunesVo() {
		return elementosComunesVo;
	}

	public void setElementosComunesVo(ElementosComunesVo elementosComunesVo) {
		this.elementosComunesVo = elementosComunesVo;
	}

}
