package com.loam.framework.core.catalog.services.coin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.exception.CustomGenericException;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogCoin;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.ConsultCatalogCoinOut;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCoinIn;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.MaintainCatalogCoinOut;
import com.loam.framework.core.catalog.persistence.coin.dao.CatalogCoinDao;

@Service
public class CatalogCoinServiceImpl implements CatalogCoinService{
	@Autowired
	protected CatalogCoinDao catalogCoinDao;
	@Autowired
	private CommonProperties commonProperties;

	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public MaintainCatalogCoinOut createCatalogCoinService(MaintainCatalogCoinIn maintainCatalogCoinIn) throws Exception {
		CatalogCoin catalogCoin = null;
		MaintainCatalogCoinOut maintainCatalogCoinOut = new MaintainCatalogCoinOut();
		try{
			if(maintainCatalogCoinIn.getCatalogCoin().getIdCoin().intValue() == 0 && maintainCatalogCoinIn.getCatalogCoin().getStartDt() != null){
				catalogCoin = catalogCoinDao.createCatalogCoinDao(maintainCatalogCoinIn.getCatalogCoin());
			}else{
				if(catalogCoinDao.existCatalogCoinDao(maintainCatalogCoinIn.getCatalogCoin().getIdCoin().intValue()) != 0){
					catalogCoin = catalogCoinDao.modifyCatalogCoinDao(maintainCatalogCoinIn.getCatalogCoin());
				}else{
					throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
							new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
									commonProperties.getIdEstatusWarningMsg(),
									commonProperties.getIdEstatusWarningCodigo(), "CatalogCoinServiceImpl",
									"createCatalogCoinService",
									"No existe el Identificador " + maintainCatalogCoinIn.getCatalogCoin().getIdCoin()));
				}
			}
			maintainCatalogCoinOut.setCatalogCoin(catalogCoin);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogCoinServiceImpl", "createCatalogCoinService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCoinServiceImpl", "createCatalogCoinService", ex.getMessage()));
		}
		return maintainCatalogCoinOut;
	}

	@Override
	public ConsultCatalogCoinOut consultCatalogCoinService(long idCoin, int startReg, int endReg, String order, String descCoin, int statusFlag) throws Exception {
		ConsultCatalogCoinOut listaCatalogCoinVoOut = new ConsultCatalogCoinOut();
		try{
			List<CatalogCoin> listCatalogCoin = catalogCoinDao.consultCatalogCoinDao(idCoin, startReg, endReg, order, descCoin, statusFlag);
			if(listCatalogCoin != null && !listCatalogCoin.isEmpty() && listCatalogCoin.size() > 0){
				listaCatalogCoinVoOut.getCatalogCoin().addAll(listCatalogCoin);
				listaCatalogCoinVoOut.setTotalRegs(Long.valueOf(catalogCoinDao.countCatalogCoinDao(idCoin, startReg, endReg, order, descCoin, statusFlag)));
			}else{
				throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
						new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
								commonProperties.getIdEstatusWarningMsg(), commonProperties.getIdEstatusWarningCodigo(),
								"CatalogCoinServiceImpl", "consultCatalogCoinService",
								"La consulta no arrojo registros con el id: " + idCoin));
			}
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogCoinServiceImpl", "consultCatalogCoinService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCoinServiceImpl", "consultCatalogCoinService", ex.getMessage()));
		}
		return listaCatalogCoinVoOut;
	}

	@Override
	public int existCatalogCoinService(long idCoin) throws Exception {
		int idMone = 0;
		try{
			idMone = catalogCoinDao.existCatalogCoinDao(idCoin);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogCoinServiceImpl", "existCatalogCoinService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCoinServiceImpl", "existCatalogCoinService", ex.getMessage()));
		}
		return idMone;
	}

	@Override
	public int countCatalogCoinService(long idCoin, int startReg, int endReg, String order, String descCoin, int statusFlag)
			throws Exception {
		int countCatalogCoin = 0;
		try{
			countCatalogCoin = catalogCoinDao.countCatalogCoinDao(idCoin, startReg, endReg, order, descCoin, statusFlag);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogCoinServiceImpl", "countCatalogCoinService", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCoinServiceImpl", "countCatalogCoinService", ex.getMessage()));
		}
		return countCatalogCoin;
	}
}
