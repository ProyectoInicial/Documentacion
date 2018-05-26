package com.itsc.tms.persistence.catalogo.roles.dao;

import java.util.List;

import com.itsc.tms.persistence.catalogo.roles.vo.CatalogoRolVo;

public interface CatalogoRolPersistence {
	CatalogoRolVo crearCatalogoRolDao(CatalogoRolVo catalogoRolVo) throws Exception;
	CatalogoRolVo modificarCatalogoRolDao(CatalogoRolVo catalogoRolVo) throws Exception;
	List<CatalogoRolVo> consultarCatalogoRolDao(int idRol, int startReg, int endReg, String order, String descRol, int statusFlag) throws Exception;
	int contCatalogoRolDao(int idRol, int startReg, int endReg, String order, String descRol, int statusFlag) throws Exception;
	int existeCatalogoRolDao(int idRol) throws Exception;
}
