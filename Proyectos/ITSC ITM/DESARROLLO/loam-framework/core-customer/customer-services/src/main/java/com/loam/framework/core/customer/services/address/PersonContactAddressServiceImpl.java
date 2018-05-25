package com.loam.framework.core.customer.services.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserContactAddressOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactAddressIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserContactAddressOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.UserContactAddress;
import com.loam.framework.core.customer.persistence.address.dao.PersonContactAddressDao;
import com.loam.framework.core.customer.persistence.common.CustomGenericException;
import com.loam.framework.core.customer.persistence.common.ExcepcionGenerica;

@Service
public class PersonContactAddressServiceImpl implements PersonContactAddressService{

	@Autowired
	protected PersonContactAddressDao personContactAddressDao;
	
	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainUserContactAddressOut maintainPersonContactAddressService(
			MaintainUserContactAddressIn maintainUserContactAddressIn) throws Exception {
		MaintainUserContactAddressOut maintainUserContactAddressOut = new MaintainUserContactAddressOut();
		try{
			if(maintainUserContactAddressIn != null && maintainUserContactAddressIn.getUserContactAddress() != null){
				if(maintainUserContactAddressIn.getUserContactAddress().getIdContactAddress() != null){
					if(personContactAddressDao.existPersonContactAddressDao(Long.parseLong(String.valueOf(maintainUserContactAddressIn.getUserContactAddress().getIdContactAddress()))) != 0){
						maintainUserContactAddressOut.setUserContactAddress(personContactAddressDao.modifyPersonContactAddressDao(maintainUserContactAddressIn.getUserContactAddress()));
					}else{
						throw new CustomGenericException("WARNING", new ExcepcionGenerica("WARNING","PersonContactAddressServiceImpl","maintainPersonContactAddressService","No existe el Identificador "));
					}
				}else{
					maintainUserContactAddressOut.setUserContactAddress(personContactAddressDao.createPersonContactAddressDao(maintainUserContactAddressIn.getUserContactAddress()));
				}
			}else{
				throw new CustomGenericException("ERROR", new ExcepcionGenerica("ERROR","PersonContactAddressServiceImpl","maintainPersonContactAddressService","Existen objetos nulos."));
			}
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return maintainUserContactAddressOut;
	}

	@Override
	public ConsultUserContactAddressOut consultPersonContactAddressService(long idContactAddress, long idPerson,
			int startReg, int endReg, String order) throws Exception {
		ConsultUserContactAddressOut consultUserContactAddressOut = new ConsultUserContactAddressOut();
		try{
			List<UserContactAddress> listPersonContactAddress = personContactAddressDao.consultPersonContactAddressDao(idContactAddress, idPerson, startReg, endReg, order);
			if(listPersonContactAddress != null && !listPersonContactAddress.isEmpty() && listPersonContactAddress.size() > 0){
				consultUserContactAddressOut.getUserContactAddress().addAll(listPersonContactAddress);
				consultUserContactAddressOut.setTotalRegs(Long.valueOf(personContactAddressDao.countPersonContactAddressDao(idContactAddress, idPerson, startReg, endReg, order)));
			}else{
				throw new CustomGenericException("WARNING", new ExcepcionGenerica("WARNING","PersonContactAddressServiceImpl","consultPersonContactAddressService","La consulta no arrojo registros con el id "));
			}
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return consultUserContactAddressOut;
	}

	@Override
	public int countPersonContactAddressService(long idContactAddress, long idPerson, int startReg, int endReg,
			String order) throws Exception {
		int countPersonContactAddress = 0;
		try{
			countPersonContactAddress = personContactAddressDao.countPersonContactAddressDao(idContactAddress, idPerson, startReg, endReg, order);
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return countPersonContactAddress;
	}

	@Override
	public int existPersonContactAddressService(long idContactAddress) throws Exception {
		int  existPersonContactAddress = 0;
		try{
			existPersonContactAddress = personContactAddressDao.existPersonContactAddressDao(idContactAddress);
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existPersonContactAddress;
	}

}
