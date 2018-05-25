package com.loam.framework.core.security.services.security.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loam.framework.core.security.commons.properties.CommonProperties;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityAliasIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityAliasOut;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityEmailIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityEmailOut;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityLoginIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityLoginOut;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityTokenIn;
import com.loam.framework.core.security.jaxb.ws.corporatesecurity.SecurityTokenOut;
import com.loam.framework.core.security.persistence.common.CustomGenericException;
import com.loam.framework.core.security.persistence.login.dao.SecurityLoginDao;

@Service
public class DataLoginServiceImpl implements DataLoginService{

	@Autowired
	protected SecurityLoginDao securityLoginDao;
	@Autowired
	private CommonProperties commonProperties;
	
	@Override
	public SecurityLoginOut securityLoginService(SecurityLoginIn securityLoginIn) throws Exception {
		long existe = 0;
		String token = null;
		SecurityLoginOut securityLoginOut = new SecurityLoginOut();
		try{
			existe = securityLoginDao.securityLoginDao(securityLoginIn);
			if(existe != 0){
				token = securityLoginDao.tokenSecurityLoginDao(securityLoginIn);
				securityLoginOut.setTokenUser(token);
				securityLoginOut.setDescIdStatusLogin(commonProperties.getIdEstatusSuccessCodigo());
			}else{
				securityLoginOut.setDescIdStatusLogin(commonProperties.getIdEstatusErrorCodigo());
			}
			securityLoginOut.setIdStatusLogin(existe);
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return securityLoginOut;
	}

	@Override
	public SecurityTokenOut securityTokenService(SecurityTokenIn securityTokenIn) throws Exception {
		long existe = 0;
		SecurityTokenOut securityTokenOut = new SecurityTokenOut();
		try{
			existe = securityLoginDao.securityTokenDao(securityTokenIn);
			if(existe != 0){
				securityTokenOut.setDescIdStatusToken(commonProperties.getIdEstatusSuccessCodigo());
			}else{
				securityTokenOut.setDescIdStatusToken(commonProperties.getIdEstatusErrorCodigo());
			}
			securityTokenOut.setIdStatusToken(existe);
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return securityTokenOut;
	}

	@Override
	public SecurityAliasOut securityAliasService(SecurityAliasIn securityAliasIn) throws Exception {
		long existe = 0;
		SecurityAliasOut securityAliasOut = new SecurityAliasOut();
		try{
			existe = securityLoginDao.securityAliasDao(securityAliasIn);
			if(existe != 0){
				securityAliasOut.setDescIdStatusAliasUser(commonProperties.getIdEstatusSuccessCodigo());
			}else{
				securityAliasOut.setDescIdStatusAliasUser(commonProperties.getIdEstatusErrorCodigo());
			}
			securityAliasOut.setIdStatusAliasUser(existe);
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return securityAliasOut;
	}

	@Override
	public SecurityEmailOut securityEmailService(SecurityEmailIn securityEmailIn) throws Exception {
		long existe = 0;
		SecurityEmailOut securityEmailOut = new SecurityEmailOut();
		try{
			existe = securityLoginDao.securityEmailDao(securityEmailIn);
			if(existe != 0){
				securityEmailOut.setDescIdStatusEmail(commonProperties.getIdEstatusSuccessCodigo());
			}else{
				securityEmailOut.setDescIdStatusEmail(commonProperties.getIdEstatusErrorCodigo());
			}
			securityEmailOut.setIdStatusEmail(existe);
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return securityEmailOut;
	}

}
