package com.loam.framework.core.customer.persistence.person.vo;

import java.math.BigInteger;
import java.util.Date;

import com.loam.framework.core.customer.persistence.address.vo.ContactAddressVo;
import com.loam.framework.core.customer.persistence.common.ElementosComunesVo;
import com.loam.framework.core.customer.persistence.contact.vo.ContactMethodVo;

public class DataPersonVo {
	private BigInteger idPerson;
	private String tokenPerson;
	private String namePerson;
	private String surnamesPerson;
	private int sexPerson;
	private Date birthDatePerson;
	private int agePerson;
	private BigInteger idUser;
	protected ElementosComunesVo elementosComunesVo;
	private ContactMethodVo contactMethodVo;
	private ContactAddressVo contactAddressVo;

	public BigInteger getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(BigInteger idPerson) {
		this.idPerson = idPerson;
	}

	public String getTokenPerson() {
		return tokenPerson;
	}

	public void setTokenPerson(String tokenPerson) {
		this.tokenPerson = tokenPerson;
	}

	public String getNamePerson() {
		return namePerson;
	}

	public void setNamePerson(String namePerson) {
		this.namePerson = namePerson;
	}

	public String getSurnamesPerson() {
		return surnamesPerson;
	}

	public void setSurnamesPerson(String surnamesPerson) {
		this.surnamesPerson = surnamesPerson;
	}

	public int getSexPerson() {
		return sexPerson;
	}

	public void setSexPerson(int sexPerson) {
		this.sexPerson = sexPerson;
	}

	public Date getBirthDatePerson() {
		return birthDatePerson;
	}

	public void setBirthDatePerson(Date birthDatePerson) {
		this.birthDatePerson = birthDatePerson;
	}

	public int getAgePerson() {
		return agePerson;
	}

	public void setAgePerson(int agePerson) {
		this.agePerson = agePerson;
	}

	public BigInteger getIdUser() {
		return idUser;
	}

	public void setIdUser(BigInteger idUser) {
		this.idUser = idUser;
	}

	public ElementosComunesVo getElementosComunesVo() {
		return elementosComunesVo;
	}

	public void setElementosComunesVo(ElementosComunesVo elementosComunesVo) {
		this.elementosComunesVo = elementosComunesVo;
	}

	public ContactMethodVo getContactMethodVo() {
		return contactMethodVo;
	}

	public void setContactMethodVo(ContactMethodVo contactMethodVo) {
		this.contactMethodVo = contactMethodVo;
	}

	public ContactAddressVo getContactAddressVo() {
		return contactAddressVo;
	}

	public void setContactAddressVo(ContactAddressVo contactAddressVo) {
		this.contactAddressVo = contactAddressVo;
	}

}
