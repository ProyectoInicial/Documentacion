package com.itsc.tms.persistence.catalogo.roles.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.itsc.tms.persistence.catalogo.roles.vo.CatalogoRolVo;
import com.itsc.tms.persistence.common.CommonProperties;
import com.itsc.tms.persistence.common.CustomGenericException;
import com.itsc.tms.commons.utils.date.DateUtilsCommons;
import com.itsc.tms.commons.utils.exception.ExcepcionGenerica;
import com.itsc.tms.persistence.common.JdbcDao;
import com.itsc.tms.persistence.common.JdbcQueryParameters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class CatalogoRolPersistenceImpl extends JdbcDao implements CatalogoRolPersistence {

	private static final Logger log = Logger.getLogger(CatalogoRolPersistenceImpl.class);

	@Autowired
	private CommonProperties commonProperties;

	private String qryCrearRol;
	private String qryModificarRol;
	private String nextIdRol;
	private String existeIdRol;
	private JdbcQueryParameters qryConsultaRoles;
	private JdbcQueryParameters qryContConsultaRoles;

	@SuppressWarnings("unused")
	@Override
	public CatalogoRolVo crearCatalogoRolDao(final CatalogoRolVo catalogoRolVo) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("crearCatalogRolDao...");
		}
		try {
			final int idRol = jdbcTemplate.queryForInt(nextIdRol);
			catalogoRolVo.setIdRol(idRol);
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCrearRol);
					ps.setInt(1, catalogoRolVo.getIdRol());
					ps.setString(2, catalogoRolVo.getDescRol());
					ps.setInt(3, catalogoRolVo.getStatusFlag());
					ps.setTimestamp(4, DateUtilsCommons.convertJavaDateToSqlDate(catalogoRolVo.getLastUpdateDt()));
					ps.setString(5, catalogoRolVo.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolPersistenceImpl", "crearCatalogoRolDao",
							"Ocurrio un error al hacer crearCatalogoRolDao "));
		}
		return catalogoRolVo;
	}

	@SuppressWarnings("unused")
	@Override
	public CatalogoRolVo modificarCatalogoRolDao(final CatalogoRolVo catalogoRolVo) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modificarCatalogRolDao...");
		}
		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModificarRol);

					ps.setString(1, catalogoRolVo.getDescRol());
					ps.setInt(2, catalogoRolVo.getStatusFlag());
					ps.setTimestamp(3, DateUtilsCommons.convertJavaDateToSqlDate(catalogoRolVo.getLastUpdateDt()));
					ps.setString(4, catalogoRolVo.getLastUpdateUser());
					ps.setString(5, String.valueOf(catalogoRolVo.getIdRol()));
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolPersistenceImpl", "modificarCatalogoRolDao",
							"Ocurrio un error al hacer modificarCatalogoRolDao "));
		}
		return catalogoRolVo;
	}

	private static ParameterizedRowMapper<CatalogoRolVo> MAPPER_CONSULTA = new ParameterizedRowMapper<CatalogoRolVo>() {
		public CatalogoRolVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CatalogoRolVo obj = new CatalogoRolVo();
			obj.setIdRol(Integer.parseInt(rs.getString("ID_ROL_CAT")));
			obj.setDescRol(rs.getString("DESC_ROL_CAT"));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setLastUpdateDt(DateUtilsCommons.convertJavaStringToDate(rs.getString("LAST_UPDATE_DT")));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));
			return obj;
		}
	};

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CatalogoRolVo> consultarCatalogoRolDao(int idRol, int startReg, int endReg, String order,
			String descRol, int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultarCatalogRolDao...");
		}

		StringBuffer stb = new StringBuffer(qryConsultaRoles.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryConsultaRoles.getOptionalParameters();
		if (idRol != 0) {
			stb.append(" ").append(params.get("idRol"));
			parameters.add(idRol);
		}
		if (descRol != null && !descRol.equals("")) {
			stb.append(" ").append(params.get("descRol"));
			parameters.add("%" + descRol.toUpperCase() + "%");
		}
		if (statusFlag <= 1) {
			stb.append(" ").append(params.get("statusFlag"));
			parameters.add(statusFlag);
		}
		if (order != null && !order.equals("")) {
			stb.append(" ").append(params.get("order"));
			if (order.toUpperCase().equals("ASC")) {
				stb.append(" ").append(params.get("asc"));
			} else {
				stb.append(" ").append(params.get("desc"));
			}
		}
		if (startReg != 0 || endReg != 0) {
			stb.append(" ").append(params.get("limite"));
			parameters.add(startReg);
			parameters.add(endReg);
		}

		if (qryConsultaRoles.getSqlLast() != null && !qryConsultaRoles.getSqlLast().equals("")) {
			stb.append(" ").append(qryConsultaRoles.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<CatalogoRolVo> consultCatalogRolDao = null;

		try {
			consultCatalogRolDao = (List<CatalogoRolVo>) jdbcTemplate.query(stb.toString(), args, MAPPER_CONSULTA);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolPersistenceImpl", "consultarCatalogoRolDao",
							"Ocurrio un error al hacer consultarCatalogoRolDao "));
		}
		return consultCatalogRolDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int contCatalogoRolDao(int idRol, int startReg, int endReg, String order, String descRol, int statusFlag)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("countCatalogRolDao...");
		}

		StringBuffer stb = new StringBuffer(qryContConsultaRoles.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryContConsultaRoles.getOptionalParameters();
		if (idRol != 0) {
			stb.append(" ").append(params.get("idRol"));
			parameters.add(idRol);
		}
		if (descRol != null && !descRol.equals("")) {
			stb.append(" ").append(params.get("descRol"));
			parameters.add("%" + descRol.toUpperCase() + "%");
		}
		if (statusFlag <= 1) {
			stb.append(" ").append(params.get("statusFlag"));
			parameters.add(statusFlag);
		}
		if (order != null && !order.equals("")) {
			stb.append(" ").append(params.get("order"));
			if (order.toUpperCase().equals("ASC")) {
				stb.append(" ").append(params.get("asc"));
			} else {
				stb.append(" ").append(params.get("desc"));
			}
		}
		if (startReg != 0 || endReg != 0) {
			stb.append(" ").append(params.get("limite"));
			parameters.add(startReg);
			parameters.add(endReg);
		}

		if (qryContConsultaRoles.getSqlLast() != null && !qryContConsultaRoles.getSqlLast().equals("")) {
			stb.append(" ").append(qryContConsultaRoles.getSqlLast());
		}

		Object[] args = parameters.toArray();
		int countCatalogRolDao = 0;

		try {
			countCatalogRolDao = jdbcTemplate.queryForInt(stb.toString(), args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolPersistenceImpl", "countCatalogoRolDao",
							"Ocurrio un error al hacer countCatalogoRolDao "));
		}
		return countCatalogRolDao;
	}

	@Override
	public int existeCatalogoRolDao(int idRol) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existCatalogoRolDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idRol);
		Object[] args = parameters.toArray();
		int existrol = 0;

		try {
			existrol = jdbcTemplate.queryForInt(existeIdRol, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogoRolPersistenceImpl", "existCatalogoRolDao",
							"Ocurrio un error al hacer existCatalogoRolDao "));
		}
		return existrol;
	}

	public void setCommonProperties(CommonProperties commonProperties) {
		this.commonProperties = commonProperties;
	}

	public void setQryCrearRol(String qryCrearRol) {
		this.qryCrearRol = qryCrearRol;
	}

	public void setQryModificarRol(String qryModificarRol) {
		this.qryModificarRol = qryModificarRol;
	}

	public void setNextIdRol(String nextIdRol) {
		this.nextIdRol = nextIdRol;
	}

	public void setExisteIdRol(String existeIdRol) {
		this.existeIdRol = existeIdRol;
	}

	public void setQryConsultaRoles(JdbcQueryParameters qryConsultaRoles) {
		this.qryConsultaRoles = qryConsultaRoles;
	}

	public void setQryContConsultaRoles(JdbcQueryParameters qryContConsultaRoles) {
		this.qryContConsultaRoles = qryContConsultaRoles;
	}

}
