package com.loam.framework.core.customer.persistence.alias.dao;

import java.util.List;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.UserAlias;

public interface AliasDataDao {
	UserAlias createUserAliasDao(UserAlias dataUserAlias) throws Exception;
	UserAlias modifyUserAliasDao(UserAlias dataUserAlias) throws Exception;
	List<UserAlias> consultUserAliasDao(String tokenUser, String aliasUser, String emailUser, int startReg, int endReg, String order) throws Exception;
	int countDataAliasDao(String tokenUser, String aliasUser, String emailUser, int startReg, int endReg, String order) throws Exception;
	int existAliasTokenDao(String tokenUser) throws Exception;
	int existAliasDao(String aliasUser) throws Exception;
	int existAliasEmailDao(String emailUser) throws Exception;
}
