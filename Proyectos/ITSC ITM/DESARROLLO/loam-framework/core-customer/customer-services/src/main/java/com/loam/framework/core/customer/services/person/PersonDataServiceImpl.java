package com.loam.framework.core.customer.services.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultDataPersonOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.DataPerson;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainDataPersonIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainDataPersonOut;
import com.loam.framework.core.customer.persistence.common.CustomGenericException;
import com.loam.framework.core.customer.persistence.common.ExcepcionGenerica;
import com.loam.framework.core.customer.persistence.person.dao.PersonDataDao;

@Service
public class PersonDataServiceImpl implements PersonDataService{

	@Autowired
	protected PersonDataDao personDataDao;

	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainDataPersonOut maintainDataPersonService(MaintainDataPersonIn maintainDataPersonIn) throws Exception {
		MaintainDataPersonOut maintainDataPersonOut = new MaintainDataPersonOut();
		try{
			if(maintainDataPersonIn != null && maintainDataPersonIn.getDataPerson() != null){
				if(maintainDataPersonIn.getDataPerson().getIdPerson() != null){
					if(personDataDao.existIdPersonDao(Long.parseLong(String.valueOf(maintainDataPersonIn.getDataPerson().getIdPerson()))) != 0){
						maintainDataPersonIn.setDataPerson(personDataDao.modifyDataPersonDao(maintainDataPersonIn.getDataPerson()));
					}else{
						throw new CustomGenericException("WARNING", new ExcepcionGenerica("WARNING","PersonDataServiceImpl","maintainDataPersonService","No existe el Identificador "));
					}
				}else{
					maintainDataPersonIn.setDataPerson(personDataDao.createDataPersonDao(maintainDataPersonIn.getDataPerson()));
				}
			}else{
				throw new CustomGenericException("ERROR", new ExcepcionGenerica("ERROR","PersonDataServiceImpl","maintainDataPersonService","Existen objetos nulos."));
			}
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return maintainDataPersonOut;
	}

	@Override
	public ConsultDataPersonOut consultDataPersonService(long idPerson, long idPersonCat, long tokenPerson, long idUser,
			int startReg, int endReg, String order) throws Exception {
		ConsultDataPersonOut consultDataPersonOut = new ConsultDataPersonOut();
		try{
			List<DataPerson> listDataPerson = personDataDao.consultDataPersonDao(idPerson, idPersonCat, tokenPerson, idUser, startReg, endReg, order);
			if(listDataPerson != null && !listDataPerson.isEmpty() && listDataPerson.size() > 0){
				consultDataPersonOut.getDataPerson().addAll(listDataPerson);
				consultDataPersonOut.setTotalRegs(Long.valueOf(personDataDao.countDataPersonDao(idPerson, idPersonCat, tokenPerson, idUser, startReg, endReg, order)));
			}else{
				throw new CustomGenericException("WARNING", new ExcepcionGenerica("WARNING","PersonDataServiceImpl","consultDataPersonService","La consulta no arrojo registros con el id "));
			}
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return consultDataPersonOut;
	}

	@Override
	public int countDataPersonService(long idPerson, long idPersonCat, long tokenPerson, long idUser, int startReg,
			int endReg, String order) throws Exception {
		int countDataPerson = 0;
		try{
			countDataPerson = personDataDao.countDataPersonDao(idPerson, idPersonCat, tokenPerson, idUser, startReg, endReg, order);
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return countDataPerson;
	}

	@Override
	public int existDataPersonService(long idPerson) throws Exception {
		int existDataPerson = 0;
		try{
			existDataPerson = personDataDao.existIdPersonDao(idPerson);
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existDataPerson;
	}

}
