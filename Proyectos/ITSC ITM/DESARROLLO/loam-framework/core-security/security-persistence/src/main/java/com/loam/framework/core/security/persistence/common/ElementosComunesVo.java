package com.loam.framework.core.security.persistence.common;

import java.util.Date;

public class ElementosComunesVo {
	protected int statusFlag;
	protected Date startDt;
	protected Date lastUpdateDt;
	protected String lastUpdateUser;

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
