package com.loam.framework.core.customer.services.contact;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserContactMethodOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactMethodIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactMethodOut;

public interface PersonContactMethodService {
	MaintainUserContactMethodOut maintainUserContactMethodService(MaintainUserContactMethodIn maintainUserContactMethodIn) throws Exception;
	ConsultUserContactMethodOut consultUserContactMethodService(long idContactMethod, long idContactMethodCat, long idPerson, int startReg, int endReg, String order) throws Exception;
	int countUserContactMethodService(long idContactMethod, long idContactMethodCat, long idPerson, int startReg, int endReg, String order) throws Exception;
	int existUserContactMethodService(long idContactMethod) throws Exception;
}
