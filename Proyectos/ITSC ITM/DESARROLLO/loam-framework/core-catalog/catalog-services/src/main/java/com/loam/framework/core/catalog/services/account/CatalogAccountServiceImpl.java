package com.loam.framework.core.catalog.services.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.exception.CustomGenericException;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogAccount;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogAccountOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogAccountIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogAccountOut;
import com.loam.framework.core.catalog.persistence.account.dao.CatalogAccountDao;

@Service
public class CatalogAccountServiceImpl implements CatalogAccountService{
	@Autowired
	protected CatalogAccountDao catalogAccountDao;
	@Autowired
	private CommonProperties commonProperties;

	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainCatalogAccountOut createCatalogAccountService(MaintainCatalogAccountIn maintainCatalogAccountIn) throws Exception {
		CatalogAccount catalogAccount = null;
		MaintainCatalogAccountOut maintainCatalogAccountOut = new MaintainCatalogAccountOut();
		try{
			if(maintainCatalogAccountIn.getCatalogAccount().getIdAccount().intValue() == 0 && maintainCatalogAccountIn.getCatalogAccount().getStartDt() != null){
				catalogAccount = catalogAccountDao.createCatalogAccountDao(maintainCatalogAccountIn.getCatalogAccount());
			}else{
				if(catalogAccountDao.existCatalogAccountDao(maintainCatalogAccountIn.getCatalogAccount().getIdAccount().intValue()) != 0){
					catalogAccount = catalogAccountDao.modifyCatalogAccountDao(maintainCatalogAccountIn.getCatalogAccount());
				}else{
					throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
							new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
									commonProperties.getIdEstatusWarningMsg(),
									commonProperties.getIdEstatusWarningCodigo(), "CatalogAccountServiceImpl",
									"createCatalogAccountService",
									"No existe el Identificador " + maintainCatalogAccountIn.getCatalogAccount().getIdAccount()));
				}
			}
			maintainCatalogAccountOut.setCatalogAccount(catalogAccount);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogAccountServiceImpl", "createCatalogAccountService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogAccountServiceImpl", "createCatalogAccountService", ex.getMessage()));
		}
		return maintainCatalogAccountOut;
	}

	@Override
	public ConsultCatalogAccountOut consultCatalogAccountService(long idAccount, int startReg, int endReg, String order, String descAccount, int statusFlag) throws Exception {
		ConsultCatalogAccountOut listaCatalogAccountVoOut = new ConsultCatalogAccountOut();
		try{
			List<CatalogAccount> listCatalogAccount = catalogAccountDao.consultCatalogAccountDao(idAccount, startReg, endReg, order, descAccount, statusFlag);
			if(listCatalogAccount != null && !listCatalogAccount.isEmpty() && listCatalogAccount.size() > 0){
				listaCatalogAccountVoOut.getCatalogAccount().addAll(listCatalogAccount);
			}else{
				throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
						new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
								commonProperties.getIdEstatusWarningMsg(), commonProperties.getIdEstatusWarningCodigo(),
								"CatalogAccountServiceImpl", "ConsultCatalogAccountOut",
								"La consulta no arrojo registros con el id: " + idAccount));
			}
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogAccountServiceImpl", "consultCatalogAccountService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogAccountServiceImpl", "consultCatalogAccountService", ex.getMessage()));
		}
		return listaCatalogAccountVoOut;
	}

	@Override
	public int existCatalogAccountService(long idAccount) throws Exception {
		int idCuen = 0;
		try{
			idCuen = catalogAccountDao.existCatalogAccountDao(idAccount);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogAccountServiceImpl", "existCatalogAccountService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogAccountServiceImpl", "existCatalogAccountService", ex.getMessage()));
		}
		return idCuen;
	}

	@Override
	public int countCatalogAccountService(long idAccount, int startReg, int endReg, String order, String descAccount, int statusFlag)
			throws Exception {
		int countCatalogAccount = 0;
		try{
			countCatalogAccount = catalogAccountDao.countCatalogAccountDao(idAccount, startReg, endReg, order, descAccount, statusFlag);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogAccountServiceImpl", "countCatalogAccountService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogAccountServiceImpl", "countCatalogAccountService", ex.getMessage()));
		}
		return countCatalogAccount;
	}
}
