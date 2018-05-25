package com.loam.framework.core.logs.persistence.add;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class LoggingAddVo {

	protected BigInteger idLogging;
	protected Timestamp fecTstamp;
	protected String codMensaje;
	protected String mensaje;
	protected String paquete;
	protected String metodo;
	protected String idSesion;
	protected String operacion;
	protected Timestamp startDt;
	protected Timestamp lastUpdateDt;
	protected String lastUpdateUser;

	public BigInteger getIdLogging() {
		return idLogging;
	}

	public void setIdLogging(BigInteger idLogging) {
		this.idLogging = idLogging;
	}

	public Timestamp getFecTstamp() {
		return fecTstamp;
	}

	public void setFecTstamp(Timestamp fecTstamp) {
		this.fecTstamp = fecTstamp;
	}

	public String getCodMensaje() {
		return codMensaje;
	}

	public void setCodMensaje(String codMensaje) {
		this.codMensaje = codMensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getPaquete() {
		return paquete;
	}

	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Timestamp getStartDt() {
		return startDt;
	}

	public void setStartDt(Timestamp startDt) {
		this.startDt = startDt;
	}

	public Timestamp getLastUpdateDt() {
		return lastUpdateDt;
	}

	public void setLastUpdateDt(Timestamp lastUpdateDt) {
		this.lastUpdateDt = lastUpdateDt;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public String toString (){
        String dt="Log "+new Timestamp(new Date().getTime())+" [ "+idLogging+" - " +fecTstamp+" - " +codMensaje+" - " +mensaje+" - " +paquete+" - " +metodo+" - " +idSesion+" - " +operacion+" - " +startDt+" - " +lastUpdateDt+" - " +lastUpdateUser +" ]";
        return dt;
    }
}
