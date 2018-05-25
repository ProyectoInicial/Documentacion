package com.loam.framework.core.customer.services.alias;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserAliasOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserAliasIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserAliasOut;

public interface AliasDataService {
	MaintainUserAliasOut maintainAliasDataService(MaintainUserAliasIn maintainUserAliasIn) throws Exception;
	ConsultUserAliasOut consultAliasDataService(String tokenUser, String aliasUser, String emailUser, int startReg, int endReg, String order) throws Exception;
	int countAliasDataService(String tokenUser, String aliasUser, String emailUser, int startReg, int endReg, String order) throws Exception;
	int existAliasDataService(String aliasUser) throws Exception;
}
