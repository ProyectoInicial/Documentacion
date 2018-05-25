package com.loam.framework.core.catalog.services.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.exception.CustomGenericException;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogCategory;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogCategoryOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCategoryIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCategoryOut;
import com.loam.framework.core.catalog.persistence.category.dao.CatalogCategoryDao;

@Service
public class CatalogCategoryServiceImpl implements CatalogCategoryService{
	@Autowired
	protected CatalogCategoryDao catalogCategoryDao;
	@Autowired
	private CommonProperties commonProperties;

	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainCatalogCategoryOut createCatalogCategoryService(MaintainCatalogCategoryIn maintainCatalogCategoryIn) throws Exception {
		CatalogCategory catalogCategory = null;
		MaintainCatalogCategoryOut maintainCatalogCategoryOut = new MaintainCatalogCategoryOut();
		try{
			if(maintainCatalogCategoryIn.getCatalogCategory().getIdCategory().intValue() == 0 && maintainCatalogCategoryIn.getCatalogCategory().getStartDt() != null){
				catalogCategory = catalogCategoryDao.createCatalogCategoryDao(maintainCatalogCategoryIn.getCatalogCategory());
			}else{
				if(catalogCategoryDao.existCatalogCategoryDao(maintainCatalogCategoryIn.getCatalogCategory().getIdCategory().intValue()) != 0){
					catalogCategory = catalogCategoryDao.modifyCatalogCategoryDao(maintainCatalogCategoryIn.getCatalogCategory());
				}else{
					throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
							new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
									commonProperties.getIdEstatusWarningMsg(),
									commonProperties.getIdEstatusWarningCodigo(), "CatalogCategoryServiceImpl",
									"createCatalogCategoryService",
									"No existe el Identificador " + maintainCatalogCategoryIn.getCatalogCategory().getIdCategory()));
				}
			}
			maintainCatalogCategoryOut.setCatalogCategory(catalogCategory);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogCategoryServiceImpl", "createCatalogCategoryService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCategoryServiceImpl", "createCatalogCategoryService", ex.getMessage()));
		}
		return maintainCatalogCategoryOut;
	}


	@Override
	public ConsultCatalogCategoryOut consultCatalogCategoryService(long idCategory, int startReg, int endReg, String order, String descCategory, int statusFlag) throws Exception {
		ConsultCatalogCategoryOut listaCatalogCategoryVoOut = new ConsultCatalogCategoryOut();
		try{
			List<CatalogCategory> listCatalogCategory = catalogCategoryDao.consultCatalogCategoryDao(idCategory, startReg, endReg, order, descCategory, statusFlag);
			if(listCatalogCategory != null && !listCatalogCategory.isEmpty() && listCatalogCategory.size() > 0){
				listaCatalogCategoryVoOut.getCatalogCategory().addAll(listCatalogCategory);
				listaCatalogCategoryVoOut.setTotalRegs(Long.valueOf(catalogCategoryDao.countCatalogCategoryDao(idCategory, startReg, endReg, order, descCategory, statusFlag)));
			}else{
				throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
						new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
								commonProperties.getIdEstatusWarningMsg(), commonProperties.getIdEstatusWarningCodigo(),
								"CatalogCategoryServiceImpl", "consultCatalogCategoryService",
								"La consulta no arrojo registros con el id: " + idCategory));
			}
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogCategoryServiceImpl", "consultCatalogCategoryService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCategoryServiceImpl", "consultCatalogCategoryService", ex.getMessage()));
		}
		return listaCatalogCategoryVoOut;
	}

	@Override
	public int existCatalogCategoryService(long idCategory) throws Exception {
		int idCatego = 0;
		try{
			idCatego = catalogCategoryDao.existCatalogCategoryDao(idCategory);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogCategoryServiceImpl", "existCatalogCategoryService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCategoryServiceImpl", "existCatalogCategoryService", ex.getMessage()));
		}
		return idCatego;
	}

	@Override
	public int countCatalogCategoryService(long idCategory, int startReg, int endReg, String order,
			String descCategory, int statusFlag) throws Exception {
		int countCatalogCategory = 0;
		try{
			countCatalogCategory = catalogCategoryDao.countCatalogCategoryDao(idCategory, startReg, endReg, order, descCategory, statusFlag);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogCategoryServiceImpl", "countCatalogCategoryService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCategoryServiceImpl", "countCatalogCategoryService", ex.getMessage()));
		}
		return countCatalogCategory;
	}
}
