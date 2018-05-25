package com.loam.framework.core.catalog.services.rol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.exception.CustomGenericException;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogRol;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogRolOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogRolIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogRolOut;
import com.loam.framework.core.catalog.persistence.rol.dao.CatalogRolDao;

@Service
public class CatalogRolServiceImpl implements CatalogRolService{

	@Autowired
	protected CatalogRolDao catalogRolDao;
	@Autowired
	private CommonProperties commonProperties;
	
	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainCatalogRolOut maintainCatalogRolService(MaintainCatalogRolIn maintainCatalogRolIn) throws Exception {
		CatalogRol catalogRol = null;
		MaintainCatalogRolOut maintainCatalogRolOut = new MaintainCatalogRolOut();
		try{
			if(maintainCatalogRolIn.getCatalogRol().getIdRol().intValue() == 0 && maintainCatalogRolIn.getCatalogRol().getStartDt() != null){
				catalogRol = catalogRolDao.createCatalogRolDao(maintainCatalogRolIn.getCatalogRol());
			}else{
				if(catalogRolDao.existCatalogRolDao(maintainCatalogRolIn.getCatalogRol().getIdRol().intValue()) != 0){
					catalogRol = catalogRolDao.modifyCatalogRolDao(maintainCatalogRolIn.getCatalogRol());
				}else{
					throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
							new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
									commonProperties.getIdEstatusWarningMsg(),
									commonProperties.getIdEstatusWarningCodigo(), "CatalogRolServiceImpl",
									"maintainCatalogRolService",
									"No existe el Identificador " + maintainCatalogRolIn.getCatalogRol().getIdRol()));
				}
			}
			maintainCatalogRolOut.setCatalogRol(catalogRol);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogRolServiceImpl", "maintainCatalogRolService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogRolServiceImpl", "maintainCatalogRolService", ex.getMessage()));
		}
		return maintainCatalogRolOut;
	}

	@Override
	public ConsultCatalogRolOut consultCatalogRolService(long idRol, int startReg, int endReg, String order, String descRol, int statusFlag) throws Exception {
		ConsultCatalogRolOut consultCatalogRolOut = new ConsultCatalogRolOut();
		try{
			List<CatalogRol> listCatalogRolOut = catalogRolDao.consultCatalogRolDao(idRol, startReg, endReg, order, descRol, statusFlag);
			if(listCatalogRolOut != null && !listCatalogRolOut.isEmpty() && listCatalogRolOut.size() > 0){
				consultCatalogRolOut.getCatalogRol().addAll(listCatalogRolOut);
				consultCatalogRolOut.setTotalRegs(Long.valueOf(catalogRolDao.countCatalogRolDao(idRol, startReg, endReg, order, descRol, statusFlag)));
			}else{
				throw new CustomGenericException(
						new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
								commonProperties.getIdEstatusWarningMsg(), commonProperties.getIdEstatusWarningCodigo(),
								"CatalogRolServiceImpl", "consultCatalogRolService",
								"La consulta no arrojo registros con el id: " + idRol));
			}
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogRolServiceImpl", "consultCatalogRolService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogRolServiceImpl", "consultCatalogRolService", ex.getMessage()));
		}
		return consultCatalogRolOut;
	}

	@Override
	public int countCatalogRolService(long idRol, int startReg, int endReg, String order, String descRol, int statusFlag)
			throws Exception {
		int countCatalogRol = 0;
		try{
			countCatalogRol = catalogRolDao.countCatalogRolDao(idRol, startReg, endReg, order, descRol, statusFlag);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogRolServiceImpl", "countCatalogRolService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogRolServiceImpl", "countCatalogRolService", ex.getMessage()));
		}
		return countCatalogRol;
	}

	@Override
	public int existCatalogRolService(long idRol) throws Exception {
		int existCatalogRol = 0;
		try{
			existCatalogRol = catalogRolDao.existCatalogRolDao(idRol);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogRolServiceImpl", "existCatalogRolService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogRolServiceImpl", "existCatalogRolService", ex.getMessage()));
		}
		return existCatalogRol;
	}

}
