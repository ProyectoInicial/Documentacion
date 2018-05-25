package com.loam.framework.core.customer.persistence.person.dao;

import java.util.List;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.DataPerson;

public interface PersonDataDao {
	DataPerson createDataPersonDao(DataPerson dataPerson) throws Exception;
	DataPerson modifyDataPersonDao(DataPerson dataPerson) throws Exception;
	List<DataPerson> consultDataPersonDao(long idPerson, long idPersonCat, long tokenPerson, long idUser, int startReg, int endReg, String order) throws Exception;
	int countDataPersonDao(long idPerson,long idPersonCat, long tokenPerson, long idUser, int startReg, int endReg, String order) throws Exception;
	int existPersonTokenDao(String personToken) throws Exception;
	int existIdPersonDao(long idPerson) throws Exception;
}
