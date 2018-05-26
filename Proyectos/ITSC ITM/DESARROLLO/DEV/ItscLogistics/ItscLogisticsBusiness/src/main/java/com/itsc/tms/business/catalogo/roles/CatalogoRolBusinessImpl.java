package com.itsc.tms.business.catalogo.roles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itsc.tms.commons.properties.CommonProperties;
import com.itsc.tms.commons.utils.exception.CustomGenericException;
import com.itsc.tms.commons.utils.exception.ExcepcionGenerica;
import com.itsc.tms.persistence.catalogo.roles.dao.CatalogoRolPersistence;
import com.itsc.tms.persistence.catalogo.roles.vo.CatalogoRolVo;

@Service
public class CatalogoRolBusinessImpl implements CatalogoRolBusiness {

	@Autowired
	protected CatalogoRolPersistence catalogoRolPersistence;
	@Autowired
	private CommonProperties commonProperties;
	
	@Transactional(rollbackFor=CustomGenericException.class)
	@Override
	public CatalogoRolVo maintainCatalogoRolBusiness(CatalogoRolVo catalogoRolVo) throws Exception {
		CatalogoRolVo catalogoRolVoOut = null;
		try{
			if(catalogoRolVo.getIdRol() == 0){
				catalogoRolVoOut = catalogoRolPersistence.crearCatalogoRolDao(catalogoRolVo);
			}else{
				if(catalogoRolPersistence.existeCatalogoRolDao(catalogoRolVo.getIdRol()) != 0){
					catalogoRolVoOut = catalogoRolPersistence.modificarCatalogoRolDao(catalogoRolVo);
				}else{
					throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusWarning()),
							new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
									commonProperties.getIdEstatusWarningMsg(),
									commonProperties.getIdEstatusWarningCodigo(), "CatalogoRolBusinessImpl",
									"maintainCatalogoRolBusiness",
									"No existe el Identificador " + catalogoRolVo.getIdRol()));
				}
			}
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogoRolBusinessImpl", "maintainCatalogoRolBusiness", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolBusinessImpl", "maintainCatalogoRolBusiness", ex.getMessage()));
		}
		return catalogoRolVoOut;
	}

	@Override
	public List<CatalogoRolVo> consultarCatalogoRolBusiness(int idRol, int startReg, int endReg, String order,
			String descRol, int statusFlag) throws Exception {
		List<CatalogoRolVo> consultCatalogoRolVoOut = new ArrayList<CatalogoRolVo>();
		try{
			List<CatalogoRolVo> listCatalogRolOut = catalogoRolPersistence.consultarCatalogoRolDao(idRol, startReg, endReg, order, descRol, statusFlag);
			if(listCatalogRolOut != null && !listCatalogRolOut.isEmpty() && listCatalogRolOut.size() > 0){
				consultCatalogoRolVoOut.addAll(listCatalogRolOut);
//				consultCatalogRolOut.setTotalRegs(Long.valueOf(catalogRolDao.countCatalogRolDao(idRol, startReg, endReg, order, descRol, statusFlag)));
			}else{
				throw new CustomGenericException(
						new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusWarning()),
								commonProperties.getIdEstatusWarningMsg(), commonProperties.getIdEstatusWarningCodigo(),
								"CatalogoRolBusinessImpl", "consultarCatalogoRolBusiness",
								"La consulta no arrojo registros con el id: " + idRol));
			}
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogoRolBusinessImpl", "consultarCatalogoRolBusiness", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolBusinessImpl", "consultarCatalogoRolBusiness", ex.getMessage()));
		}
		return consultCatalogoRolVoOut;
	}

	@Override
	public int contCatalogoRolBusiness(int idRol, int startReg, int endReg, String order, String descRol,
			int statusFlag) throws Exception {
		int contCatalogoRol = 0;
		try{
			contCatalogoRol = catalogoRolPersistence.contCatalogoRolDao(idRol, startReg, endReg, order, descRol, statusFlag);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogoRolBusinessImpl", "contCatalogoRolBusiness", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolBusinessImpl", "contCatalogoRolBusiness", ex.getMessage()));
		}
		return contCatalogoRol;
	}

	@Override
	public int existeCatalogoRolBusiness(int idRol) throws Exception {
		int existCatalogRol = 0;
		try{
			existCatalogRol = catalogoRolPersistence.existeCatalogoRolDao(idRol);
		} catch(NullPointerException nullEx){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusNull()), nullEx,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusNull()),
							commonProperties.getIdEstatusNullMsg(), commonProperties.getIdEstatusNullCodigo(),
							"CatalogoRolBusinessImpl", "existeCatalogoRolBusiness", nullEx.getClass().toString()));
		}catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolBusinessImpl", "existeCatalogoRolBusiness", ex.getMessage()));
		}
		return existCatalogRol;
	}

}
