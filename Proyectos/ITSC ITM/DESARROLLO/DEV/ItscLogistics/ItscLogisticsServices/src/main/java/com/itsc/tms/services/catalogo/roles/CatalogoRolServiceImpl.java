package com.itsc.tms.services.catalogo.roles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsc.tms.business.catalogo.roles.CatalogoRolBusiness;
import com.itsc.tms.commons.properties.CommonProperties;
import com.itsc.tms.commons.utils.exception.CustomGenericException;
import com.itsc.tms.commons.utils.exception.ExcepcionGenerica;
import com.itsc.tms.persistence.catalogo.roles.vo.CatalogoRolVo;

@Service
public class CatalogoRolServiceImpl implements CatalogoRolService{

	@Autowired
	protected CatalogoRolBusiness catalogoRolBusiness;
	@Autowired
	private CommonProperties commonProperties;
	
	@Override
	public CatalogoRolVo maintainCatalogoRolService(CatalogoRolVo catalogoRolVo) throws Exception {
		CatalogoRolVo catalogoRolVoOut = null;
		try{
			catalogoRolVoOut = catalogoRolBusiness.maintainCatalogoRolBusiness(catalogoRolVo);
		} catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolServiceImpl", "maintainCatalogoRolService", ex.getMessage()));
		}
		return catalogoRolVoOut;
	}

	@Override
	public List<CatalogoRolVo> consultarCatalogoRolService(int idRol, int startReg, int endReg, String order,
			String descRol, int statusFlag) throws Exception {
		List<CatalogoRolVo> listCatalogoRolVoOut = null;
		try{
			listCatalogoRolVoOut = catalogoRolBusiness.consultarCatalogoRolBusiness(idRol, startReg, endReg, order, descRol, statusFlag);
		} catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolServiceImpl", "maintainCatalogoRolService", ex.getMessage()));
		}
		return listCatalogoRolVoOut;
	}

	@Override
	public int contCatalogoRolService(int idRol, int startReg, int endReg, String order, String descRol, int statusFlag)
			throws Exception {
		int contCatalogoRolVo = 0;
		try{
			contCatalogoRolVo = catalogoRolBusiness.contCatalogoRolBusiness(idRol, startReg, endReg, order, descRol, statusFlag);
		} catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolServiceImpl", "contCatalogoRolService", ex.getMessage()));
		}
		return contCatalogoRolVo;
	}

	@Override
	public int existeCatalogoRolService(int idRol) throws Exception {
		int existeCatalogoRolVo = 0;
		try{
			existeCatalogoRolVo = catalogoRolBusiness.existeCatalogoRolBusiness(idRol);
		} catch(CustomGenericException customEx){
			throw new CustomGenericException(customEx.getExcepcionGenerica().getCodigo(), customEx,
					customEx.getExcepcionGenerica());
		} catch(Exception ex){
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolServiceImpl", "existeCatalogoRolService", ex.getMessage()));
		}
		return existeCatalogoRolVo;
	}

}
