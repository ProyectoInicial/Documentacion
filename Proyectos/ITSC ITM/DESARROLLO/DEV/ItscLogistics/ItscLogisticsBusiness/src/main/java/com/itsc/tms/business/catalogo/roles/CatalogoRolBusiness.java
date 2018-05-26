package com.itsc.tms.business.catalogo.roles;

import java.util.List;

import com.itsc.tms.persistence.catalogo.roles.vo.CatalogoRolVo;

public interface CatalogoRolBusiness {
	CatalogoRolVo maintainCatalogoRolBusiness(CatalogoRolVo catalogoRolVo) throws Exception;
	List<CatalogoRolVo> consultarCatalogoRolBusiness(int idRol, int startReg, int endReg, String order, String descRol, int statusFlag) throws Exception;
	int contCatalogoRolBusiness(int idRol, int startReg, int endReg, String order, String descRol, int statusFlag) throws Exception;
	int existeCatalogoRolBusiness(int idRol) throws Exception;
}
