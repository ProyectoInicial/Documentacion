package com.loam.framework.core.security.persistence.login.dao;

import java.util.ArrayList;
import java.util.List;

import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityAliasIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityEmailIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityLoginIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityTokenIn;
import com.loam.framework.core.security.persistence.common.CustomGenericException;
import com.loam.framework.core.security.persistence.common.JdbcDao;

public class SecurityLoginDaoImpl extends JdbcDao implements SecurityLoginDao {

	private String existSecurityLogin;
	private String existSecurityToken;
	private String existSecurityAlias;
	private String existSecurityEmail;

	private String tokenSecurityLogin;

	@Override
	public int securityLoginDao(SecurityLoginIn securityLoginIn) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("securityLoginDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(securityLoginIn.getAliasUser());
		parameters.add(securityLoginIn.getPassUser());
		parameters.add("1");
		Object[] args = parameters.toArray();
		int existUserPass = 0;

		try {
			existUserPass = jdbcTemplate.queryForInt(existSecurityLogin, args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existUserPass;
	}

	@Override
	public int securityTokenDao(SecurityTokenIn securityTokenIn) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("securityTokenDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(securityTokenIn.getTokenUser());
		parameters.add("1");
		Object[] args = parameters.toArray();
		int existUserToken = 0;

		try {
			existUserToken = jdbcTemplate.queryForInt(existSecurityToken, args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existUserToken;
	}

	@Override
	public int securityAliasDao(SecurityAliasIn securityAliasIn) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("securityAliasDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(securityAliasIn.getAliasUser());
		parameters.add("1");
		Object[] args = parameters.toArray();
		int existUserToken = 0;

		try {
			existUserToken = jdbcTemplate.queryForInt(existSecurityAlias, args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existUserToken;
	}

	@Override
	public int securityEmailDao(SecurityEmailIn securityEmailIn) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("securityEmailDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(securityEmailIn.getEmailUser());
		parameters.add("1");
		Object[] args = parameters.toArray();
		int existUserEmail = 0;

		try {
			existUserEmail = jdbcTemplate.queryForInt(existSecurityEmail, args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existUserEmail;
	}

	@Override
	public String tokenSecurityLoginDao(SecurityLoginIn securityLoginIn) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("tokenSecurityLoginDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(securityLoginIn.getAliasUser());
		parameters.add("1");
		Object[] args = parameters.toArray();
		String tokenUser = null;

		try {
			tokenUser = jdbcTemplate.queryForObject(tokenSecurityLogin, args, String.class);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return tokenUser;
	}

	public void setExistSecurityLogin(String existSecurityLogin) {
		this.existSecurityLogin = existSecurityLogin;
	}

	public void setExistSecurityToken(String existSecurityToken) {
		this.existSecurityToken = existSecurityToken;
	}

	public void setExistSecurityAlias(String existSecurityAlias) {
		this.existSecurityAlias = existSecurityAlias;
	}

	public void setExistSecurityEmail(String existSecurityEmail) {
		this.existSecurityEmail = existSecurityEmail;
	}

	public void setTokenSecurityLogin(String tokenSecurityLogin) {
		this.tokenSecurityLogin = tokenSecurityLogin;
	}

}
