package com.loam.framework.core.customer.services.person;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultDataPersonOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainDataPersonIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainDataPersonOut;

public interface PersonDataService {
	MaintainDataPersonOut maintainDataPersonService(MaintainDataPersonIn maintainDataPersonIn) throws Exception;
	ConsultDataPersonOut consultDataPersonService(long idPerson, long idPersonCat, long tokenPerson, long idUser, int startReg, int endReg, String order) throws Exception;
	int countDataPersonService(long idPerson, long idPersonCat, long tokenPerson, long idUser, int startReg, int endReg, String order) throws Exception;
	int existDataPersonService(long idPerson) throws Exception;
}
