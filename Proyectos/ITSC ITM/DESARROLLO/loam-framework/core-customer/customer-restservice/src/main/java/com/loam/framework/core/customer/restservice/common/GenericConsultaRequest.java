package com.loam.framework.core.customer.restservice.common;

public class GenericConsultaRequest {
	protected long id;
	protected long idSub;
	protected long idSubSub;
	protected int idStart;
	protected int idEnd;
	protected String ordenar;
	protected String descripcion;

	protected String tokenUser;
	protected String aliasUser;
	protected String emailUser;

	protected long idPerson;
	protected long idPersonCat;
	protected long tokenPerson;
	protected long idUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdSub() {
		return idSub;
	}

	public void setIdSub(long idSub) {
		this.idSub = idSub;
	}

	public int getIdStart() {
		return idStart;
	}

	public void setIdStart(int idStart) {
		this.idStart = idStart;
	}

	public int getIdEnd() {
		return idEnd;
	}

	public void setIdEnd(int idEnd) {
		this.idEnd = idEnd;
	}

	public String getOrdenar() {
		return ordenar;
	}

	public void setOrdenar(String ordenar) {
		this.ordenar = ordenar;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public long getIdSubSub() {
		return idSubSub;
	}

	public void setIdSubSub(long idSubSub) {
		this.idSubSub = idSubSub;
	}

	public long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(long idPerson) {
		this.idPerson = idPerson;
	}

	public long getIdPersonCat() {
		return idPersonCat;
	}

	public void setIdPersonCat(long idPersonCat) {
		this.idPersonCat = idPersonCat;
	}

	public long getTokenPerson() {
		return tokenPerson;
	}

	public void setTokenPerson(long tokenPerson) {
		this.tokenPerson = tokenPerson;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

}
