package com.loam.framework.core.customer.services.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserContactMethodOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactMethodIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactMethodOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.UserContactMethod;
import com.loam.framework.core.customer.persistence.common.CustomGenericException;
import com.loam.framework.core.customer.persistence.common.ExcepcionGenerica;
import com.loam.framework.core.customer.persistence.contact.dao.PersonContactMethodDao;

@Service
public class PersonContactMethodServiceImpl implements PersonContactMethodService{

	@Autowired
	protected PersonContactMethodDao personContactMethodDao;
	
	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainUserContactMethodOut maintainUserContactMethodService(
			MaintainUserContactMethodIn maintainUserContactMethodIn) throws Exception {
		MaintainUserContactMethodOut maintainUserContactMethodOut = new MaintainUserContactMethodOut();
		try{
			if(maintainUserContactMethodIn != null && maintainUserContactMethodIn.getUserContactMethod() != null){
				if(maintainUserContactMethodIn.getUserContactMethod().getIdContactMethod() != null){
					if(personContactMethodDao.existPersonContactMethodDao(Long.parseLong(String.valueOf(maintainUserContactMethodIn.getUserContactMethod().getIdContactMethod()))) != 0){
						maintainUserContactMethodOut.setUserContactMethod(personContactMethodDao.modifyPersonContactMethodDao(maintainUserContactMethodIn.getUserContactMethod()));
					}else{
						throw new CustomGenericException("WARNING", new ExcepcionGenerica("WARNING","PersonContactMethodServiceImpl","maintainUserContactMethodService","No existe el Identificador "));
					}
				}else{
					maintainUserContactMethodOut.setUserContactMethod(personContactMethodDao.createPersonContactMethodDao(maintainUserContactMethodIn.getUserContactMethod()));
				}
			}else{
				throw new CustomGenericException("ERROR", new ExcepcionGenerica("ERROR","PersonContactMethodServiceImpl","maintainUserContactMethodService","Existen objetos nulos."));
			}
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return maintainUserContactMethodOut;
	}

	@Override
	public ConsultUserContactMethodOut consultUserContactMethodService(long idContactMethod, long idContactMethodCat,
			long idPerson, int startReg, int endReg, String order) throws Exception {
		ConsultUserContactMethodOut consultUserContactMethodOut = new ConsultUserContactMethodOut();
		try{
			List<UserContactMethod> listPersonContactMethod = personContactMethodDao.consultPersonContactMethodDao(idContactMethod, idContactMethodCat, idPerson, startReg, endReg, order);
			if(listPersonContactMethod != null && !listPersonContactMethod.isEmpty() && listPersonContactMethod.size() > 0){
				consultUserContactMethodOut.getUserContactMethod().addAll(listPersonContactMethod);
				consultUserContactMethodOut.setTotalRegs(Long.valueOf(personContactMethodDao.countPersonContactMethodDao(idContactMethod, idContactMethodCat, idPerson, startReg, endReg, order)));
			}else{
				throw new CustomGenericException("WARNING", new ExcepcionGenerica("WARNING","PersonContactMethodServiceImpl","consultUserContactMethodService","La consulta no arrojo registros con el id "));
			}
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return consultUserContactMethodOut;
	}

	@Override
	public int countUserContactMethodService(long idContactMethod, long idContactMethodCat, long idPerson, int startReg,
			int endReg, String order) throws Exception {
		int countUserContactMethod = 0;
		try{
			countUserContactMethod = personContactMethodDao.countPersonContactMethodDao(idContactMethod, idContactMethodCat, idPerson, startReg, endReg, order);
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return countUserContactMethod;
	}

	@Override
	public int existUserContactMethodService(long idContactMethod) throws Exception {
		int existUserContactMethod = 0;
		try{
			existUserContactMethod = personContactMethodDao.existPersonContactMethodDao(idContactMethod);
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existUserContactMethod;
	}

}
