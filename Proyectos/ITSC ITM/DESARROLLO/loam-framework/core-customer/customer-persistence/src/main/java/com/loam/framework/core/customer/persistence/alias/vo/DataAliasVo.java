package com.loam.framework.core.customer.persistence.alias.vo;

import java.math.BigInteger;

import com.loam.framework.core.customer.persistence.common.ElementosComunesVo;
import com.loam.framework.core.customer.persistence.person.vo.DataPersonVo;

public class DataAliasVo {
	private BigInteger idUser;
	private String tokenUser;
	private String aliasUser;
	private String emailUser;
	private String passUser;
	private BigInteger idRol;
	protected ElementosComunesVo elementosComunesVo;
	private DataPersonVo dataPersonVo;

	public BigInteger getIdUser() {
		return idUser;
	}

	public void setIdUser(BigInteger idUser) {
		this.idUser = idUser;
	}

	public String getTokenUser() {
		return tokenUser;
	}

	public void setTokenUser(String tokenUser) {
		this.tokenUser = tokenUser;
	}

	public String getAliasUser() {
		return aliasUser;
	}

	public void setAliasUser(String aliasUser) {
		this.aliasUser = aliasUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getPassUser() {
		return passUser;
	}

	public void setPassUser(String passUser) {
		this.passUser = passUser;
	}

	public BigInteger getIdRol() {
		return idRol;
	}

	public void setIdRol(BigInteger idRol) {
		this.idRol = idRol;
	}

	public ElementosComunesVo getElementosComunesVo() {
		return elementosComunesVo;
	}

	public void setElementosComunesVo(ElementosComunesVo elementosComunesVo) {
		this.elementosComunesVo = elementosComunesVo;
	}

	public DataPersonVo getDataPersonVo() {
		return dataPersonVo;
	}

	public void setDataPersonVo(DataPersonVo dataPersonVo) {
		this.dataPersonVo = dataPersonVo;
	}

}
