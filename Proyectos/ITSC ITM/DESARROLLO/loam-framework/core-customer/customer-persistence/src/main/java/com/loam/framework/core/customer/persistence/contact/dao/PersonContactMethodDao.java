package com.loam.framework.core.customer.persistence.contact.dao;

import java.util.List;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.UserContactMethod;

public interface PersonContactMethodDao {
	UserContactMethod createPersonContactMethodDao(UserContactMethod userContactMethod) throws Exception;
	UserContactMethod modifyPersonContactMethodDao(UserContactMethod userContactMethod) throws Exception;
	List<UserContactMethod> consultPersonContactMethodDao(long idContactMethod, long idContactMethodCat, long idPerson, int startReg, int endReg, String order) throws Exception;
	int countPersonContactMethodDao(long idContactMethod, long idContactMethodCat, long idPerson, int startReg, int endReg, String order) throws Exception;
	int existPersonContactMethodDao(long idContactMethod) throws Exception;
}
