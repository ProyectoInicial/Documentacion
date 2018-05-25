package com.loam.framework.core.customer.services.alias;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.customer.jaxb.ws.customerdetails.ConsultUserAliasOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserAliasIn;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.MaintainUserAliasOut;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.UserAlias;
import com.loam.framework.core.customer.persistence.alias.dao.AliasDataDao;
import com.loam.framework.core.customer.persistence.common.CustomGenericException;
import com.loam.framework.core.customer.persistence.common.ExcepcionGenerica;

@Service
public class AliasDataServiceImpl implements AliasDataService{

	@Autowired
	protected AliasDataDao aliasDataDao;

	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainUserAliasOut maintainAliasDataService(MaintainUserAliasIn maintainUserAliasIn) throws Exception {
		MaintainUserAliasOut maintainUserAliasOut = new MaintainUserAliasOut();
		try{
			if(maintainUserAliasIn != null && maintainUserAliasIn.getUserAlias() != null){
				if(maintainUserAliasIn.getUserAlias().getIdUserAlias() != null){
					if(aliasDataDao.existAliasDao(String.valueOf(maintainUserAliasIn.getUserAlias().getIdUserAlias())) != 0){
						maintainUserAliasOut.setUserAlias(aliasDataDao.modifyUserAliasDao(maintainUserAliasIn.getUserAlias()));
					}else{
						throw new CustomGenericException("WARNING", new ExcepcionGenerica("WARNING","AliasDataServiceImpl","maintainAliasDataService","No existe el Identificador "));
					}
				}else{
					maintainUserAliasOut.setUserAlias(aliasDataDao.createUserAliasDao(maintainUserAliasIn.getUserAlias()));
				}
			}else{
				throw new CustomGenericException("ERROR", new ExcepcionGenerica("ERROR","AliasDataServiceImpl","maintainAliasDataService","Existen objetos nulos."));
			}
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return maintainUserAliasOut;
	}

	@Override
	public ConsultUserAliasOut consultAliasDataService(String tokenUser, String aliasUser, String emailUser,
			int startReg, int endReg, String order) throws Exception {
		ConsultUserAliasOut consultUserAliasOut = new ConsultUserAliasOut();
		try{
			List<UserAlias> listUserAlias = aliasDataDao.consultUserAliasDao(tokenUser, aliasUser, emailUser, startReg, endReg, order);
			if(listUserAlias != null && !listUserAlias.isEmpty() && listUserAlias.size() > 0){
				consultUserAliasOut.getUserAlias().addAll(listUserAlias);
				consultUserAliasOut.setTotalRegs(Long.valueOf(aliasDataDao.countDataAliasDao(tokenUser, aliasUser, emailUser, startReg, endReg, order)));
			}else{
				throw new CustomGenericException("WARNING", new ExcepcionGenerica("WARNING","AliasDataServiceImpl","consultAliasDataService","La consulta no arrojo registros con el id "));
			}
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return consultUserAliasOut;
	}

	@Override
	public int countAliasDataService(String tokenUser, String aliasUser, String emailUser, int startReg, int endReg,
			String order) throws Exception {
		int countAliasData = 0;
		try{
			countAliasData = aliasDataDao.countDataAliasDao(tokenUser, aliasUser, emailUser, startReg, endReg, order);
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return countAliasData;
	}

	@Override
	public int existAliasDataService(String aliasUser) throws Exception {
		int existAliasData = 0;
		try{
			existAliasData = aliasDataDao.existAliasDao(aliasUser);
		}catch(CustomGenericException ex){
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existAliasData;
	}
	
}
