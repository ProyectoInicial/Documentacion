package com.loam.framework.core.security.persistence.login.vo;

public class SecurityLoginVo {
	private String loginUserToken;
	private String loginUserAlias;
	private String loginUserEmail;
	private String loginUserPass;

	public String getLoginUserToken() {
		return loginUserToken;
	}

	public void setLoginUserToken(String loginUserToken) {
		this.loginUserToken = loginUserToken;
	}

	public String getLoginUserAlias() {
		return loginUserAlias;
	}

	public void setLoginUserAlias(String loginUserAlias) {
		this.loginUserAlias = loginUserAlias;
	}

	public String getLoginUserEmail() {
		return loginUserEmail;
	}

	public void setLoginUserEmail(String loginUserEmail) {
		this.loginUserEmail = loginUserEmail;
	}

	public String getLoginUserPass() {
		return loginUserPass;
	}

	public void setLoginUserPass(String loginUserPass) {
		this.loginUserPass = loginUserPass;
	}

}
