package com.itsc.tms.services.catalogo.roles;

import java.util.List;

import com.itsc.tms.persistence.catalogo.roles.vo.CatalogoRolVo;

public interface CatalogoRolService {
	CatalogoRolVo maintainCatalogoRolService(CatalogoRolVo catalogoRolVo) throws Exception;
	List<CatalogoRolVo> consultarCatalogoRolService(int idRol, int startReg, int endReg, String order, String descRol, int statusFlag) throws Exception;
	int contCatalogoRolService(int idRol, int startReg, int endReg, String order, String descRol, int statusFlag) throws Exception;
	int existeCatalogoRolService(int idRol) throws Exception;
}
