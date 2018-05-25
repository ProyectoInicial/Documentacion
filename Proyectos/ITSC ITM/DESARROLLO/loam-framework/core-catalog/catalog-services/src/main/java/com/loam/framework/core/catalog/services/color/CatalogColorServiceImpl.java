package com.loam.framework.core.catalog.services.color;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.exception.CustomGenericException;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogColor;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogColorOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogColorIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogColorOut;
import com.loam.framework.core.catalog.persistence.color.dao.CatalogColorDao;

@Service
public class CatalogColorServiceImpl implements CatalogColorService{
	@Autowired
	protected CatalogColorDao catalogColorDao;
	@Autowired
	private CommonProperties commonProperties;

	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainCatalogColorOut createCatalogColorService(MaintainCatalogColorIn maintainCatalogColorIn) throws Exception {
		CatalogColor catalogColor = null;
		MaintainCatalogColorOut maintainCatalogColorOut = new MaintainCatalogColorOut();
		try{
			if(maintainCatalogColorIn.getCatalogColor().getIdColor().intValue() == 0 && maintainCatalogColorIn.getCatalogColor().getStartDt() != null){
				catalogColor = catalogColorDao.createCatalogColorDao(maintainCatalogColorIn.getCatalogColor());
			}else{
				if(catalogColorDao.existCatalogColorDao(maintainCatalogColorIn.getCatalogColor().getIdColor().intValue()) != 0){
					catalogColor = catalogColorDao.modifyCatalogColorDao(maintainCatalogColorIn.getCatalogColor());
				}else{
					throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
							new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
									commonProperties.getIdEstatusWarningMsg(),
									commonProperties.getIdEstatusWarningCodigo(), "CatalogColorServiceImpl",
									"createCatalogColorService",
									"No existe el Identificador " + maintainCatalogColorIn.getCatalogColor().getIdColor()));
				}
			}
			maintainCatalogColorOut.setCatalogColor(catalogColor);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogColorServiceImpl", "createCatalogColorService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogColorServiceImpl", "createCatalogColorService", ex.getMessage()));
		}
		return maintainCatalogColorOut;
	}

	@Override
	public ConsultCatalogColorOut consultCatalogColorService(long idColor, int startReg, int endReg, String order, String descColor, int statusFlag) throws Exception {
		ConsultCatalogColorOut listaCatalogColorVoOut = new ConsultCatalogColorOut();
		try{
			List<CatalogColor> listCatalogColor = catalogColorDao.consultCatalogColorDao(idColor, startReg, endReg, order, descColor, statusFlag);
			if(listCatalogColor != null && !listCatalogColor.isEmpty() && listCatalogColor.size() > 0){
				listaCatalogColorVoOut.getCatalogColor().addAll(listCatalogColor);
				listaCatalogColorVoOut.setTotalRegs(Long.valueOf(catalogColorDao.countCatalogColorDao(idColor, startReg, endReg, order, descColor, statusFlag)));
			}else{
				throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
						new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
								commonProperties.getIdEstatusWarningMsg(), commonProperties.getIdEstatusWarningCodigo(),
								"CatalogColorServiceImpl", "consultCatalogColorService",
								"La consulta no arrojo registros con el id: " + idColor));
			}
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogColorServiceImpl", "consultCatalogColorService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogColorServiceImpl", "consultCatalogColorService", ex.getMessage()));
		}
		return listaCatalogColorVoOut;
	}

	@Override
	public int existCatalogColorService(long idColor) throws Exception {
		int countCatalogColor = 0;
		try{
			countCatalogColor = catalogColorDao.existCatalogColorDao(idColor);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogColorServiceImpl", "existCatalogColorService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogColorServiceImpl", "existCatalogColorService", ex.getMessage()));
		}
		return countCatalogColor;
	}

	@Override
	public int countCatalogColorService(long idColor, int startReg, int endReg, String order, String descColor, int statusFlag)
			throws Exception {
		int countCatalogColor = 0;
		try{
			countCatalogColor = catalogColorDao.countCatalogColorDao(idColor, startReg, endReg, order, descColor, statusFlag);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogColorServiceImpl", "countCatalogColorService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogColorServiceImpl", "countCatalogColorService", ex.getMessage()));
		}
		return countCatalogColor;
	}
}
