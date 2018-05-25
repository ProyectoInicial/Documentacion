package com.loam.framework.core.catalog.services.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.exception.CustomGenericException;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogMenu;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogRolMenu;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogMenuOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogRolMenuOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogMenuIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogMenuOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogRolMenuIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogRolMenuOut;
import com.loam.framework.core.catalog.persistence.menu.dao.CatalogMenuDao;

@Service
public class CatalogMenuServiceImpl implements CatalogMenuService{

	@Autowired
	protected CatalogMenuDao catalogMenuDao;
	@Autowired
	private CommonProperties commonProperties;
	
	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainCatalogMenuOut createCatalogMenuService(MaintainCatalogMenuIn maintainCatalogMenuIn)
			throws Exception {
		CatalogMenu catalogMenu = null;
		MaintainCatalogMenuOut maintainCatalogMenuOut = new MaintainCatalogMenuOut();
		try{
			if(maintainCatalogMenuIn.getCatalogMenu().getIdMenu().intValue() == 0 && maintainCatalogMenuIn.getCatalogMenu().getStartDt() != null){
				catalogMenu = catalogMenuDao.createCatalogMenuDao(maintainCatalogMenuIn.getCatalogMenu());
			}else{
				if(catalogMenuDao.existCatalogMenuDao(maintainCatalogMenuIn.getCatalogMenu().getIdMenu().intValue()) != 0){
					catalogMenu = catalogMenuDao.modifyCatalogMenuDao(maintainCatalogMenuIn.getCatalogMenu());
				}else{
					throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
							new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
									commonProperties.getIdEstatusWarningMsg(),
									commonProperties.getIdEstatusWarningCodigo(), "CatalogMenuServiceImpl",
									"createCatalogMenuService",
									"No existe el Identificador " + maintainCatalogMenuIn.getCatalogMenu().getIdMenu()));
				}
			}
			maintainCatalogMenuOut.setCatalogMenu(catalogMenu);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogMenuServiceImpl", "createCatalogMenuService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuServiceImpl", "createCatalogMenuService", ex.getMessage()));
		}
		return maintainCatalogMenuOut;
	}

	@Override
	public ConsultCatalogMenuOut consultCatalogMenuService(long idMenu, int startReg, int endReg, String order,
			String descMenu, int statusFlag) throws Exception {
		ConsultCatalogMenuOut consultCatalogMenuOut = new ConsultCatalogMenuOut();
		try{
			List<CatalogMenu> listCatalogMenu = catalogMenuDao.consultCatalogMenuDao(idMenu, startReg, endReg, order, descMenu, statusFlag);
			if(listCatalogMenu != null && !listCatalogMenu.isEmpty() && listCatalogMenu.size() > 0){
				consultCatalogMenuOut.getCatalogMenu().addAll(listCatalogMenu);
				consultCatalogMenuOut.setTotalRegs(Long.valueOf(catalogMenuDao.countCatalogMenuDao(idMenu, startReg, endReg, order, descMenu, statusFlag)));
			}else{
				throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
						new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
								commonProperties.getIdEstatusWarningMsg(), commonProperties.getIdEstatusWarningCodigo(),
								"CatalogMenuServiceImpl", "consultCatalogMenuService",
								"La consulta no arrojo registros con el id: " + idMenu));
			}
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogMenuServiceImpl", "consultCatalogMenuService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuServiceImpl", "consultCatalogMenuService", ex.getMessage()));
		}
		return consultCatalogMenuOut;
	}

	@Override
	public int countCatalogMenuService(long idMenu, int startReg, int endReg, String order, String descMenu, int statusFlag)
			throws Exception {
		int existCatalogMenu = 0;
		try{
			existCatalogMenu = catalogMenuDao.countCatalogMenuDao(idMenu, startReg, endReg, order, descMenu, statusFlag);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogMenuServiceImpl", "countCatalogMenuService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuServiceImpl", "countCatalogMenuService", ex.getMessage()));
		}
		return existCatalogMenu;
	}

	@Override
	public int existCatalogMenuService(long idMenu) throws Exception {
		int existCatalogMenu = 0;
		try{
			existCatalogMenu = catalogMenuDao.existCatalogMenuDao(idMenu);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogMenuServiceImpl", "existCatalogMenuService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuServiceImpl", "existCatalogMenuService", ex.getMessage()));
		}
		return existCatalogMenu;
	}

	@Override
	public MaintainCatalogRolMenuOut createCatalogRolMenuService(MaintainCatalogRolMenuIn maintainCatalogRolMenuIn)
			throws Exception {
		CatalogRolMenu catalogRolMenu = null;
		MaintainCatalogRolMenuOut maintainCatalogRolMenuOut = new MaintainCatalogRolMenuOut();
		try{
			if(maintainCatalogRolMenuIn.getCatalogRolMenu().getIdRolMenu().intValue() == 0 && maintainCatalogRolMenuIn.getCatalogRolMenu().getStartDt() != null){
				catalogRolMenu = catalogMenuDao.createCatalogRolMenuDao(maintainCatalogRolMenuIn.getCatalogRolMenu());
			}else{
				if(catalogMenuDao.existCatalogRolMenuDao(maintainCatalogRolMenuIn.getCatalogRolMenu().getIdRolMenu().intValue()) != 0){
					catalogRolMenu = catalogMenuDao.modifyCatalogRolMenuDao(maintainCatalogRolMenuIn.getCatalogRolMenu());
				}else{
					throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
							new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
									commonProperties.getIdEstatusWarningMsg(),
									commonProperties.getIdEstatusWarningCodigo(), "CatalogMenuServiceImpl",
									"createCatalogRolMenuService",
									"No existe el Identificador " + maintainCatalogRolMenuIn.getCatalogRolMenu().getIdRolMenu()));
				}
			}
			maintainCatalogRolMenuOut.setCatalogRolMenu(catalogRolMenu);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogMenuServiceImpl", "createCatalogRolMenuService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuServiceImpl", "createCatalogRolMenuService", ex.getMessage()));
		}
		return maintainCatalogRolMenuOut;
	}

	@Override
	public ConsultCatalogRolMenuOut consultCatalogRolMenuService(long idRolMenu, int startReg, int endReg, String order,
			String descRolMenu, int statusFlag) throws Exception {
		ConsultCatalogRolMenuOut consultCatalogRolMenuOut = new ConsultCatalogRolMenuOut();
		try{
			List<CatalogRolMenu> listCatalogRolMenu = catalogMenuDao.consultCatalogRolMenuDao(idRolMenu, startReg, endReg, order, descRolMenu, statusFlag);
			if(listCatalogRolMenu != null && !listCatalogRolMenu.isEmpty() && listCatalogRolMenu.size() > 0){
				consultCatalogRolMenuOut.getCatalogRolMenu().addAll(listCatalogRolMenu);
				consultCatalogRolMenuOut.setTotalRegs(Long.valueOf(catalogMenuDao.countCatalogRolMenuDao(idRolMenu, startReg, endReg, order, descRolMenu, statusFlag)));
			}else{
				throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
						new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
								commonProperties.getIdEstatusWarningMsg(), commonProperties.getIdEstatusWarningCodigo(),
								"CatalogMenuServiceImpl", "consultCatalogRolMenuService",
								"La consulta no arrojo registros con el id: " + idRolMenu));
			}
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogMenuServiceImpl", "consultCatalogRolMenuService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuServiceImpl", "consultCatalogRolMenuService", ex.getMessage()));
		}
		return consultCatalogRolMenuOut;
	}

	@Override
	public int countCatalogRolMenuService(long idRolMenu, int startReg, int endReg, String order, String descRolMenu, int statusFlag)
			throws Exception {
		int existCatalogRolMenu = 0;
		try{
			existCatalogRolMenu = catalogMenuDao.countCatalogRolMenuDao(idRolMenu, startReg, endReg, order, descRolMenu, statusFlag);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogMenuServiceImpl", "countCatalogRolMenuService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuServiceImpl", "countCatalogRolMenuService", ex.getMessage()));
		}
		return existCatalogRolMenu;
	}

	@Override
	public int existCatalogRolMenuService(long idRolMenu) throws Exception {
		int existCatalogRolMenu = 0;
		try{
			existCatalogRolMenu = catalogMenuDao.existCatalogRolMenuDao(idRolMenu);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogMenuServiceImpl", "existCatalogRolMenuService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuServiceImpl", "existCatalogRolMenuService", ex.getMessage()));
		}
		return existCatalogRolMenu;
	}

}
