package com.loam.framework.core.security.persistence.login.dao;

import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityAliasIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityEmailIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityLoginIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityTokenIn;

public interface SecurityLoginDao {
	int securityLoginDao(SecurityLoginIn securityLoginIn) throws Exception;
	int securityTokenDao(SecurityTokenIn securityTokenIn) throws Exception;
	int securityAliasDao(SecurityAliasIn securityAliasIn) throws Exception;
	int securityEmailDao(SecurityEmailIn securityEmailIn) throws Exception;
	
	String tokenSecurityLoginDao(SecurityLoginIn securityLoginIn) throws Exception;
}
