package com.loam.framework.core.customer.services.address;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserContactAddressOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactAddressIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactAddressOut;

public interface PersonContactAddressService {
	MaintainUserContactAddressOut maintainPersonContactAddressService(MaintainUserContactAddressIn maintainUserContactAddressIn) throws Exception;
	ConsultUserContactAddressOut consultPersonContactAddressService(long idContactAddress, long idPerson, int startReg, int endReg, String order) throws Exception;
	int countPersonContactAddressService(long idContactAddress, long idPerson, int startReg, int endReg, String order) throws Exception;
	int existPersonContactAddressService(long idContactAddress) throws Exception;
}
