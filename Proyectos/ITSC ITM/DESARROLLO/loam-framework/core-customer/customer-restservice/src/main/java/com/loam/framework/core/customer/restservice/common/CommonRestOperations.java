package com.loam.framework.core.customer.restservice.common;

import org.springframework.http.HttpHeaders;

import com.loam.framework.core.customer.persistence.common.EstadoRespuestaVo;
import com.loam.framework.core.customer.persistence.common.HeaderResponseVo;
import com.loam.framework.core.customer.restservice.CustomerDataService;

public class CommonRestOperations {
	public static HttpHeaders getHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		headers.add("X-Fsl-Location", "/");
		headers.add("X-Fsl-Response-Code", "302");
		return headers;
	}

	public static HeaderResponseVo headerResponseExito() {
		HeaderResponseVo headerResponse = new HeaderResponseVo();
		EstadoRespuestaVo estadoRespuesta = new EstadoRespuestaVo();
		headerResponse.setId(String.valueOf(ErroresComunes.ID_EXITO.getIntValue()));
		headerResponse.setEstatus(ErroresComunes.ESTATUS_EXITO.getSrtValue());
		headerResponse.setMensaje(ErroresComunes.MSG_EXITO.getSrtValue());

		estadoRespuesta.setIdOperacion("12342134");
		estadoRespuesta.setClase(CustomerDataService.class.getName());
		estadoRespuesta.setMensajeDetallado(ErroresComunes.MSG_EXITO.getSrtValue());
		estadoRespuesta.setMetodo("consultarCatalogoRolOut");
		estadoRespuesta.setNivelSegRequerido(ErroresComunes.NIVEL_SUG.getIntValue());

		headerResponse.setEstadoRespuesta(estadoRespuesta);
		headerResponse.setTokenOperacion("2312421");
		headerResponse.setFechaHora(ErroresComunes.FECHA.getDateValue());
		return headerResponse;
	}
}
