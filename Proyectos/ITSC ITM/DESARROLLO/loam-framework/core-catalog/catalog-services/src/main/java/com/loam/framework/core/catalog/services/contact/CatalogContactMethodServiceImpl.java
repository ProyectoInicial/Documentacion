package com.loam.framework.core.catalog.services.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.exception.CustomGenericException;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogContactMethod;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogContactMethodOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogContactMethodIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogContactMethodOut;
import com.loam.framework.core.catalog.persistence.contact.dao.CatalogContactMethodDao;

@Service
public class CatalogContactMethodServiceImpl implements CatalogContactMethodService{

	@Autowired
	protected CatalogContactMethodDao catalogContactMethodDao;
	@Autowired
	private CommonProperties commonProperties;
	
	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainCatalogContactMethodOut createCatalogContactMethodService(
			MaintainCatalogContactMethodIn maintainCatalogContactMethodIn) throws Exception {
		CatalogContactMethod catalogContactMethod = null;
		MaintainCatalogContactMethodOut maintainCatalogContactMethodOut = new MaintainCatalogContactMethodOut();
		try{
			if(maintainCatalogContactMethodIn.getCatalogContactMethod().getIdContactMethod().intValue() == 0 && maintainCatalogContactMethodIn.getCatalogContactMethod().getStartDt() != null){
				catalogContactMethod = catalogContactMethodDao.createContactMethodDao(maintainCatalogContactMethodIn.getCatalogContactMethod());
			}else{
				if(catalogContactMethodDao.existContactMethodDao(maintainCatalogContactMethodIn.getCatalogContactMethod().getIdContactMethod().intValue()) != 0){
					catalogContactMethod = catalogContactMethodDao.modifyContactMethodDao(maintainCatalogContactMethodIn.getCatalogContactMethod());
				}else{
					throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
							new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
									commonProperties.getIdEstatusWarningMsg(),
									commonProperties.getIdEstatusWarningCodigo(), "CatalogContactMethodServiceImpl",
									"createCatalogContactMethodService",
									"No existe el Identificador " + maintainCatalogContactMethodIn.getCatalogContactMethod().getIdContactMethod()));
				}
			}
			maintainCatalogContactMethodOut.setCatalogContactMethod(catalogContactMethod);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogContactMethodServiceImpl", "createCatalogContactMethodService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogContactMethodServiceImpl", "createCatalogContactMethodService", ex.getMessage()));
		}
		return maintainCatalogContactMethodOut;
	}

	@Override
	public ConsultCatalogContactMethodOut consultCatalogContactMethodService(long idContactMethod,
			int startReg, int endReg, String order, String descContactMethod, int statusFlag) throws Exception {
		ConsultCatalogContactMethodOut consultCatalogContactMethodOut = new ConsultCatalogContactMethodOut();
		try{
			List<CatalogContactMethod> listCatalogContactMethod = catalogContactMethodDao.consultContactMethodDao(idContactMethod, startReg, endReg, order, descContactMethod, statusFlag);
			if(listCatalogContactMethod != null && !listCatalogContactMethod.isEmpty() && listCatalogContactMethod.size() > 0){
				consultCatalogContactMethodOut.getCatalogContactMethod().addAll(listCatalogContactMethod);
				consultCatalogContactMethodOut.setTotalRegs(Long.valueOf(catalogContactMethodDao.countContactMethodDao(idContactMethod, startReg, endReg, order, descContactMethod, statusFlag)));
			}else{
				throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
						new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
								commonProperties.getIdEstatusWarningMsg(), commonProperties.getIdEstatusWarningCodigo(),
								"CatalogContactMethodServiceImpl", "consultCatalogContactMethodService",
								"La consulta no arrojo registros con el id: " + idContactMethod));
			}
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogContactMethodServiceImpl", "consultCatalogContactMethodService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogContactMethodServiceImpl", "consultCatalogContactMethodService", ex.getMessage()));
		}
		return consultCatalogContactMethodOut;
	}

	@Override
	public int countCatalogContactMethodService(long idContactMethod, int startReg, int endReg,
			String order, String descContactMethod, int statusFlag) throws Exception {
		int existContactMethod = 0;
		try{
			existContactMethod = catalogContactMethodDao.countContactMethodDao(idContactMethod, startReg, endReg, order, descContactMethod, statusFlag);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogContactMethodServiceImpl", "countCatalogContactMethodService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogContactMethodServiceImpl", "countCatalogContactMethodService", ex.getMessage()));
		}
		return existContactMethod;
	}

	@Override
	public int existCatalogContactMethodService(long idContactMethod) throws Exception {
		int existContactMethod = 0;
		try{
			existContactMethod = catalogContactMethodDao.existContactMethodDao(idContactMethod);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogContactMethodServiceImpl", "existCatalogContactMethodService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogContactMethodServiceImpl", "existCatalogContactMethodService", ex.getMessage()));
		}
		return existContactMethod;
	}

}
