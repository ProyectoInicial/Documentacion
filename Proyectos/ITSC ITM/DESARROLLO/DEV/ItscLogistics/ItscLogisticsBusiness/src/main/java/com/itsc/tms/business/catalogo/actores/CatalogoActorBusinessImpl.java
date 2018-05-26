package com.itsc.tms.business.catalogo.actores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsc.tms.commons.properties.CommonProperties;
import com.itsc.tms.persistence.catalogo.actores.vo.CatalogoActorVo;

@Service
public class CatalogoActorBusinessImpl implements CatalogoActorBusiness{

	@Autowired
	private CommonProperties commonProperties;
	
	@Override
	public CatalogoActorVo maintainCatalogoActorBusiness(CatalogoActorVo catalogoActorVo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatalogoActorVo> consultarCatalogoActorBusiness(int idActor, int startReg, int endReg, String order,
			String descActor, int statusFlag) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int contCatalogoActorBusiness(int idActor, int startReg, int endReg, String order, String descRol,
			int statusFlag) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int existeCatalogoActorBusiness(int idActor) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
