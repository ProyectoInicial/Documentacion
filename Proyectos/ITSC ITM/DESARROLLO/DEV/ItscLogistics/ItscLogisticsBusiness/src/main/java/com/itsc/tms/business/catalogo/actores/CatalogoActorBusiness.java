package com.itsc.tms.business.catalogo.actores;

import java.util.List;

import com.itsc.tms.persistence.catalogo.actores.vo.CatalogoActorVo;

public interface CatalogoActorBusiness {
	CatalogoActorVo maintainCatalogoActorBusiness(CatalogoActorVo catalogoActorVo) throws Exception;
	List<CatalogoActorVo> consultarCatalogoActorBusiness(int idActor, int startReg, int endReg, String order, String descActor, int statusFlag) throws Exception;
	int contCatalogoActorBusiness(int idActor, int startReg, int endReg, String order, String descRol, int statusFlag) throws Exception;
	int existeCatalogoActorBusiness(int idActor) throws Exception;
}
