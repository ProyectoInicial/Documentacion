package com.loam.framework.core.customer.persistence.address.dao;

import java.util.List;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.UserContactAddress;

public interface PersonContactAddressDao {
	UserContactAddress createPersonContactAddressDao(UserContactAddress userContactAddress) throws Exception;
	UserContactAddress modifyPersonContactAddressDao(UserContactAddress userContactAddress) throws Exception;
	List<UserContactAddress> consultPersonContactAddressDao(long idContactAddress, long idPerson, int startReg, int endReg, String order) throws Exception;
	int countPersonContactAddressDao(long idContactAddress, long idPerson, int startReg, int endReg, String order) throws Exception;
	int existPersonContactAddressDao(long idContactAddress) throws Exception;
}
