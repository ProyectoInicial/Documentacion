package com.loam.framework.core.catalog.services.subcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogSubCategory;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogSubCategoryOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogSubCategoryIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogSubCategoryOut;
import com.loam.framework.core.catalog.persistence.common.CustomGenericException;
import com.loam.framework.core.catalog.persistence.subcategory.dao.CatalogSubCategoryDao;

@Service
public class CatalogSubCategoryServiceImpl implements CatalogSubCategoryService{

	@Autowired
	protected CatalogSubCategoryDao catalogSubCategoryDao;
	@Autowired
	private CommonProperties commonProperties;
	
	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainCatalogSubCategoryOut createCatalogSubCategoryService(MaintainCatalogSubCategoryIn maintainCatalogSubCategoryIn)
			throws Exception {
		CatalogSubCategory catalogSubCategory = null;
		MaintainCatalogSubCategoryOut maintainCatalogSubCategoryOut = new MaintainCatalogSubCategoryOut();
		try{
			if(maintainCatalogSubCategoryIn.getCatalogSubCategory().getIdSubCategory().intValue() == 0 && maintainCatalogSubCategoryIn.getCatalogSubCategory().getStartDt() != null){
				catalogSubCategory = catalogSubCategoryDao.createCatalogSubCategoryDao(maintainCatalogSubCategoryIn.getCatalogSubCategory());
			}else{
				if(catalogSubCategoryDao.existCatalogSubCategoryDao(maintainCatalogSubCategoryIn.getCatalogSubCategory().getIdSubCategory().intValue()) != 0){
					catalogSubCategory = catalogSubCategoryDao.modifyCatalogSubCategoryDao(maintainCatalogSubCategoryIn.getCatalogSubCategory());
				}else{
					throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
							new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
									commonProperties.getIdEstatusWarningMsg(),
									commonProperties.getIdEstatusWarningCodigo(), "CatalogSubCategoryServiceImpl",
									"createCatalogSubCategoryService",
									"No existe el Identificador " + maintainCatalogSubCategoryIn.getCatalogSubCategory().getIdSubCategory()));
				}
			}
			maintainCatalogSubCategoryOut.setCatalogSubCategory(catalogSubCategory);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogSubCategoryServiceImpl", "createCatalogSubCategoryService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogSubCategoryServiceImpl", "createCatalogSubCategoryService", ex.getMessage()));
		}
		return maintainCatalogSubCategoryOut;
	}

	@Override
	public ConsultCatalogSubCategoryOut consultCatalogSubCategoryService(long idSubCategory, long idCategory, int startReg, int endReg, String order, String descSubCategory, int statusFlag) throws Exception {
		ConsultCatalogSubCategoryOut consultCatalogSubCategory = new ConsultCatalogSubCategoryOut();
		try{
			List<CatalogSubCategory> listCatalogSubCategory = catalogSubCategoryDao.consultCatalogSubCategoryDao(idSubCategory, idCategory, startReg, endReg, order, descSubCategory, statusFlag);
			if(listCatalogSubCategory != null && !listCatalogSubCategory.isEmpty() && listCatalogSubCategory.size() > 0){
				consultCatalogSubCategory.setTotalRegs(Long.valueOf(catalogSubCategoryDao.countCatalogSubCategoryDao(idSubCategory, idCategory, startReg, endReg, order, descSubCategory, statusFlag)));
				consultCatalogSubCategory.getCatalogSubCategory().addAll(listCatalogSubCategory);
			}else{
				throw new CustomGenericException(
						new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
								commonProperties.getIdEstatusWarningMsg(), commonProperties.getIdEstatusWarningCodigo(),
								"CatalogSubCategoryServiceImpl", "consultCatalogSubCategoryService",
								"La consulta no arrojo registros con el id: " + idSubCategory));
			}
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogSubCategoryServiceImpl", "consultCatalogSubCategoryService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogSubCategoryServiceImpl", "consultCatalogSubCategoryService", ex.getMessage()));
		}
		return consultCatalogSubCategory;
	}

	@Override
	public int existCatalogSubCategoryService(long idSubCategory) throws Exception {
		int existCatalogSubCategory = 0;
		try{
			existCatalogSubCategory = catalogSubCategoryDao.existCatalogSubCategoryDao(idSubCategory);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogSubCategoryServiceImpl", "existCatalogSubCategoryService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogSubCategoryServiceImpl", "existCatalogSubCategoryService", ex.getMessage()));
		}
		return existCatalogSubCategory;
	}

	@Override
	public int countCatalogSubCategoryService(long idSubCategory, long IdCategory, int startReg, int endReg,
			String order, String descSubCategory, int statusFlag) throws Exception {
		int countCatalogSubCategory = 0;
		try{
			countCatalogSubCategory = catalogSubCategoryDao.countCatalogSubCategoryDao(idSubCategory, IdCategory, startReg, endReg, order, descSubCategory, statusFlag);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogSubCategoryServiceImpl", "countCatalogSubCategoryService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogSubCategoryServiceImpl", "countCatalogSubCategoryService", ex.getMessage()));
		}
		return countCatalogSubCategory;
	}

}
