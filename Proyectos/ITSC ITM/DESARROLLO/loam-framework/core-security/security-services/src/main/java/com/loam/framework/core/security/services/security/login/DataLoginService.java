package com.loam.framework.core.security.services.security.login;

import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityAliasIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityAliasOut;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityEmailIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityEmailOut;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityLoginIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityLoginOut;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityTokenIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityTokenOut;

public interface DataLoginService {
	SecurityLoginOut securityLoginService(SecurityLoginIn securityLoginIn) throws Exception;
	SecurityTokenOut securityTokenService(SecurityTokenIn securityTokenIn) throws Exception;
	SecurityAliasOut securityAliasService(SecurityAliasIn securityAliasIn) throws Exception;
	SecurityEmailOut securityEmailService(SecurityEmailIn securityEmailIn) throws Exception;
	
}
