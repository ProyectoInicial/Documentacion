package com.loam.framework.core.catalog.services.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.exception.CustomGenericException;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogProduct;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogProductOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogProductIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogProductOut;
import com.loam.framework.core.catalog.persistence.product.dao.CatalogProductDao;

@Service
public class CatalogProductServiceImpl implements CatalogProductService {

	@Autowired
	protected CatalogProductDao catalogProductDao;
	@Autowired
	private CommonProperties commonProperties;
	
	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainCatalogProductOut createCatalogProductService(MaintainCatalogProductIn maintainCatalogProductIn) throws Exception {
		CatalogProduct catalogProduct = null;
		MaintainCatalogProductOut maintainCatalogProductOut = new MaintainCatalogProductOut();
		try{
			if(maintainCatalogProductIn.getCatalogProduct().getIdProduct().intValue() == 0 && maintainCatalogProductIn.getCatalogProduct().getStartDt() != null){
				catalogProduct = catalogProductDao.createCatalogProductDao(maintainCatalogProductIn.getCatalogProduct());
			}else{
				if(catalogProductDao.existCatalogProductDao(maintainCatalogProductIn.getCatalogProduct().getIdProduct().intValue()) != 0){
					catalogProduct = catalogProductDao.modifyCatalogProductDao(maintainCatalogProductIn.getCatalogProduct());
				}else{
					throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
							new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
									commonProperties.getIdEstatusWarningMsg(),
									commonProperties.getIdEstatusWarningCodigo(), "CatalogProductServiceImpl",
									"createCatalogProductService",
									"No existe el Identificador " + maintainCatalogProductIn.getCatalogProduct()));
				}
			}
			maintainCatalogProductOut.setCatalogProduct(catalogProduct);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogProductServiceImpl", "createCatalogProductService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogProductServiceImpl", "createCatalogProductService", ex.getMessage()));
		}
		return maintainCatalogProductOut;
	}

	@Override
	public ConsultCatalogProductOut consultCatalogProductService(long idProduct, long idSubCategory, int startReg, int endReg, String order, String descProduct, int statusFlag) throws Exception {
		ConsultCatalogProductOut consultCatalogProduct = new ConsultCatalogProductOut();
		try{
			List<CatalogProduct> listCatalogProduct = catalogProductDao.consultCatalogProductDao(idProduct, idSubCategory, startReg, endReg, order, descProduct, statusFlag);
			if(listCatalogProduct != null && !listCatalogProduct.isEmpty() && listCatalogProduct.size() > 0){
				consultCatalogProduct.getCatalogProduct().addAll(listCatalogProduct);
				consultCatalogProduct.setTotalRegs(Long.valueOf(catalogProductDao.countCatalogProductDao(idProduct, idSubCategory, startReg, endReg, order, descProduct, statusFlag)));
			}else{
				throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
						new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
								commonProperties.getIdEstatusWarningMsg(), commonProperties.getIdEstatusWarningCodigo(),
								"CatalogProductServiceImpl", "consultCatalogProductService",
								"La consulta no arrojo registros con el id: " + idProduct));
			}
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogProductServiceImpl", "consultCatalogProductService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogProductServiceImpl", "consultCatalogProductService", ex.getMessage()));
		}
		return consultCatalogProduct;
	}

	@Override
	public int existCatalogProductService(long idProduct) throws Exception {
		int existCatalogProduct = 0;
		try{
			existCatalogProduct = catalogProductDao.existCatalogProductDao(idProduct);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogProductServiceImpl", "existCatalogProductService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogProductServiceImpl", "existCatalogProductService", ex.getMessage()));
		}
		return existCatalogProduct;
	}

	@Override
	public int countCatalogProductService(long idProduct, long idSubCategory, int startReg, int endReg,
			String order, String descProduct, int statusFlag) throws Exception {
		int countCatalogProduct = 0;
		try{
			countCatalogProduct = catalogProductDao.countCatalogProductDao(idProduct, idSubCategory, startReg, endReg, order, descProduct, statusFlag);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogProductServiceImpl", "countCatalogProductService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogProductServiceImpl", "countCatalogProductService", ex.getMessage()));
		}
		return countCatalogProduct;
	}

}
