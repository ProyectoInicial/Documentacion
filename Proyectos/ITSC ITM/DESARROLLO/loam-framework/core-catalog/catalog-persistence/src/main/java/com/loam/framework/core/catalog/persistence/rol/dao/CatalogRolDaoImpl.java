package com.loam.framework.core.catalog.persistence.rol.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.loam.framework.core.catalog.commons.properties.CommonProperties;
import com.loam.framework.core.catalog.commons.utils.date.DateUtilsCommons;
import com.loam.framework.core.catalog.commons.utils.exception.CustomGenericException;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogRol;
import com.loam.framework.core.catalog.persistence.common.JdbcDao;
import com.loam.framework.core.catalog.persistence.common.JdbcQueryParameters;

public class CatalogRolDaoImpl extends JdbcDao implements CatalogRolDao {

	private static final Logger log = Logger.getLogger(CatalogRolDaoImpl.class);

	@Autowired
	private CommonProperties commonProperties;

	private String qryCreateRol;
	private String qryModifyRol;
	private String nextIdRol;
	private String existIdRol;
	private JdbcQueryParameters qryConsultRoles;
	private JdbcQueryParameters qryCountConsultRoles;

	@SuppressWarnings("unused")
	@Override
	public CatalogRol createCatalogRolDao(final CatalogRol catalogRolVo) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("createCatalogRol...");
		}
		try {
			final int idRol = jdbcTemplate.queryForInt(nextIdRol);
			catalogRolVo.setIdRol(BigInteger.valueOf(idRol));
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreateRol);
					ps.setString(1, String.valueOf(catalogRolVo.getIdRol()));
					ps.setString(2, catalogRolVo.getDescRol());
					ps.setInt(3, catalogRolVo.getStatusFlag());
					ps.setTimestamp(4, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogRolVo.getStartDt())));
					ps.setTimestamp(5, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogRolVo.getLastUpdateDt())));
					ps.setString(6, catalogRolVo.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogRolDaoImpl", "createCatalogRolDao",
							"Ocurrio un error al hacer createCatalogRolDao "));
		}
		return catalogRolVo;
	}

	@SuppressWarnings("unused")
	@Override
	public CatalogRol modifyCatalogRolDao(final CatalogRol catalogRolVo) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modifyCatalogRol...");
		}
		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyRol);

					ps.setString(1, catalogRolVo.getDescRol());
					ps.setInt(2, catalogRolVo.getStatusFlag());
					ps.setTimestamp(3, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogRolVo.getLastUpdateDt())));
					ps.setString(4, catalogRolVo.getLastUpdateUser());
					ps.setString(5, String.valueOf(catalogRolVo.getIdRol()));
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogRolDaoImpl", "modifyCatalogRolDao",
							"Ocurrio un error al hacer modifyCatalogRolDao "));
		}
		return catalogRolVo;
	}

	private static ParameterizedRowMapper<CatalogRol> MAPPER = new ParameterizedRowMapper<CatalogRol>() {
		public CatalogRol mapRow(ResultSet rs, int rowNum) throws SQLException {
			CatalogRol obj = new CatalogRol();
			obj.setIdRol(BigInteger.valueOf(Long.valueOf(rs.getString("ID_ROL_CAT"))));
			obj.setDescRol(rs.getString("DESC_ROL_CAT"));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("START_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));
			return obj;
		}
	};

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CatalogRol> consultCatalogRolDao(long idRol, int startReg, int endReg, String order, String descRol,
			int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultCatalogRol...");
		}

		StringBuffer stb = new StringBuffer(qryConsultRoles.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryConsultRoles.getOptionalParameters();
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

		if (qryConsultRoles.getSqlLast() != null && !qryConsultRoles.getSqlLast().equals("")) {
			stb.append(" ").append(qryConsultRoles.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<CatalogRol> consultCatalogRolDao = null;

		try {
			consultCatalogRolDao = (List<CatalogRol>) jdbcTemplate.query(stb.toString(), args, MAPPER);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogRolDaoImpl", "consultCatalogRolDao",
							"Ocurrio un error al hacer consultCatalogRolDao "));
		}
		return consultCatalogRolDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countCatalogRolDao(long idRol, int startReg, int endReg, String order, String descRol, int statusFlag)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultCatalogRol...");
		}

		StringBuffer stb = new StringBuffer(qryCountConsultRoles.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountConsultRoles.getOptionalParameters();
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

		if (qryCountConsultRoles.getSqlLast() != null && !qryCountConsultRoles.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountConsultRoles.getSqlLast());
		}

		Object[] args = parameters.toArray();
		int countCatalogRolDao = 0;

		try {
			countCatalogRolDao = jdbcTemplate.queryForInt(stb.toString(), args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogRolDaoImpl", "countCatalogRolDao",
							"Ocurrio un error al hacer countCatalogRolDao "));
		}
		return countCatalogRolDao;
	}

	@Override
	public int existCatalogRolDao(long idRol) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existCatalogRolDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idRol);
		Object[] args = parameters.toArray();
		int existrol = 0;

		try {
			existrol = jdbcTemplate.queryForInt(existIdRol, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogRolDaoImpl", "existCatalogRolDao",
							"Ocurrio un error al hacer existCatalogRolDao "));
		}
		return existrol;
	}

	public void setQryCreateRol(String qryCreateRol) {
		this.qryCreateRol = qryCreateRol;
	}

	public void setQryModifyRol(String qryModifyRol) {
		this.qryModifyRol = qryModifyRol;
	}

	public void setNextIdRol(String nextIdRol) {
		this.nextIdRol = nextIdRol;
	}

	public void setExistIdRol(String existIdRol) {
		this.existIdRol = existIdRol;
	}

	public void setQryConsultRoles(JdbcQueryParameters qryConsultRoles) {
		this.qryConsultRoles = qryConsultRoles;
	}

	public void setQryCountConsultRoles(JdbcQueryParameters qryCountConsultRoles) {
		this.qryCountConsultRoles = qryCountConsultRoles;
	}

}
