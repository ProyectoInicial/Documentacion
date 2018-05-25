package com.loam.framework.core.catalog.persistence.rol.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class CatalogRolVo implements Serializable{
	private static final long serialVersionUID = 1L;
	protected BigInteger idRol;
	protected String descRol;
	protected int statusFlag;
	protected Date startDt;
	protected Date lastUpdateDt;
	protected String lastUpdateUser;

	public BigInteger getIdRol() {
		return idRol;
	}

	public void setIdRol(BigInteger idRol) {
		this.idRol = idRol;
	}

	public String getDescRol() {
		return descRol;
	}

	public void setDescRol(String descRol) {
		this.descRol = descRol;
	}

	public int getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(int statusFlag) {
		this.statusFlag = statusFlag;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getLastUpdateDt() {
		return lastUpdateDt;
	}

	public void setLastUpdateDt(Date lastUpdateDt) {
		this.lastUpdateDt = lastUpdateDt;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

}
