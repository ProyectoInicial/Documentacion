package com.loam.framework.core.catalog.persistence.contact.dao;

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
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogContactMethod;
import com.loam.framework.core.catalog.persistence.common.CustomGenericException;
import com.loam.framework.core.catalog.persistence.common.JdbcDao;
import com.loam.framework.core.catalog.persistence.common.JdbcQueryParameters;

public class CatalogContactMethodDaoImpl extends JdbcDao implements CatalogContactMethodDao {
	private static final Logger log = Logger.getLogger(CatalogContactMethodDaoImpl.class);

	@Autowired
	private CommonProperties commonProperties;

	private String qryCreateContactMethod;
	private String qryModifyContactMethod;
	private String nextIdContactMethod;
	private String existIdContactMethod;
	private JdbcQueryParameters qryAllContactMethod;
	private JdbcQueryParameters qryCountAllContactMethod;

	private static ParameterizedRowMapper<CatalogContactMethod> MAPPER = new ParameterizedRowMapper<CatalogContactMethod>() {
		public CatalogContactMethod mapRow(ResultSet rs, int rowNum) throws SQLException {
			CatalogContactMethod obj = new CatalogContactMethod();
			obj.setIdContactMethod(BigInteger.valueOf(Long.valueOf(rs.getString("ID_CONTACT_METHOD_CAT"))));
			obj.setDescContactMethod(rs.getString("DESC_CONTACT_METHOD_CAT"));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons
					.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("START_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons
					.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));
			return obj;
		}
	};

	@SuppressWarnings("unused")
	@Override
	public CatalogContactMethod createContactMethodDao(final CatalogContactMethod catalogContactMethod)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("creatContactMethodDao...");
		}

		try {
			final int nextId = jdbcTemplate.queryForInt(nextIdContactMethod);
			catalogContactMethod.setIdContactMethod(BigInteger.valueOf(nextId));
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreateContactMethod);
					ps.setString(1, String.valueOf(catalogContactMethod.getIdContactMethod()));
					ps.setString(2, catalogContactMethod.getDescContactMethod());
					ps.setInt(3, catalogContactMethod.getStatusFlag());
					ps.setTimestamp(4, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogContactMethod.getStartDt())));
					ps.setTimestamp(5, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogContactMethod.getLastUpdateDt())));
					ps.setString(6, catalogContactMethod.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogContactMethodDaoImpl", "createContactMethodDao",
							"Ocurrio un error al hacer createContactMethodDao "));
		}
		return catalogContactMethod;
	}

	@SuppressWarnings("unused")
	@Override
	public CatalogContactMethod modifyContactMethodDao(final CatalogContactMethod catalogContactMethod)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modifyContactMethodDao...");
		}

		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyContactMethod);

					ps.setString(1, catalogContactMethod.getDescContactMethod());
					ps.setInt(2, catalogContactMethod.getStatusFlag());
					ps.setTimestamp(3, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogContactMethod.getStartDt())));
					ps.setTimestamp(4, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogContactMethod.getLastUpdateDt())));
					ps.setString(5, catalogContactMethod.getLastUpdateUser());
					ps.setString(6, String.valueOf(catalogContactMethod.getIdContactMethod()));

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogContactMethodDaoImpl", "modifyContactMethodDao",
							"Ocurrio un error al hacer modifyContactMethodDao "));
		}
		return catalogContactMethod;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CatalogContactMethod> consultContactMethodDao(long idContactMethod, int startReg, int endReg,
			String order, String descContactMethod, int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultContactMethodDao...");
		}
		StringBuffer stb = new StringBuffer(qryAllContactMethod.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllContactMethod.getOptionalParameters();
		if (idContactMethod != 0) {
			stb.append(" ").append(params.get("idContactMethod"));
			parameters.add(idContactMethod);
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

		if (qryAllContactMethod.getSqlLast() != null && !qryAllContactMethod.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllContactMethod.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<CatalogContactMethod> catalogContactMethodDao = null;
		try {
			catalogContactMethodDao = (List<CatalogContactMethod>) jdbcTemplate.query(stb.toString(), args, MAPPER);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogContactMethodDaoImpl", "consultContactMethodDao",
							"Ocurrio un error al hacer consultContactMethodDao "));
		}
		return catalogContactMethodDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countContactMethodDao(long idContactMethod, int startReg, int endReg, String order,
			String descContactMethod, int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("countContactMethodDao...");
		}
		StringBuffer stb = new StringBuffer(qryCountAllContactMethod.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountAllContactMethod.getOptionalParameters();
		if (idContactMethod != 0) {
			stb.append(" ").append(params.get("idContactMethod"));
			parameters.add(idContactMethod);
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

		if (qryCountAllContactMethod.getSqlLast() != null && !qryCountAllContactMethod.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountAllContactMethod.getSqlLast());
		}

		Object[] args = parameters.toArray();
		int countAllContactMethodDao = 0;
		try {
			countAllContactMethodDao = jdbcTemplate.queryForInt(stb.toString(), args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogContactMethodDaoImpl", "countContactMethodDao",
							"Ocurrio un error al hacer countContactMethodDao "));
		}
		return countAllContactMethodDao;
	}

	@Override
	public int existContactMethodDao(long idContactMethod) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existContactMethodDao...");
		}
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idContactMethod);
		Object[] args = parameters.toArray();
		int existContactMethod = 0;
		try {
			existContactMethod = jdbcTemplate.queryForInt(existIdContactMethod, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogContactMethodDaoImpl", "existContactMethodDao",
							"Ocurrio un error al hacer existContactMethodDao "));
		}
		return existContactMethod;
	}

	public void setQryCreateContactMethod(String qryCreateContactMethod) {
		this.qryCreateContactMethod = qryCreateContactMethod;
	}

	public void setQryModifyContactMethod(String qryModifyContactMethod) {
		this.qryModifyContactMethod = qryModifyContactMethod;
	}

	public void setNextIdContactMethod(String nextIdContactMethod) {
		this.nextIdContactMethod = nextIdContactMethod;
	}

	public void setExistIdContactMethod(String existIdContactMethod) {
		this.existIdContactMethod = existIdContactMethod;
	}

	public void setQryAllContactMethod(JdbcQueryParameters qryAllContactMethod) {
		this.qryAllContactMethod = qryAllContactMethod;
	}

	public void setQryCountAllContactMethod(JdbcQueryParameters qryCountAllContactMethod) {
		this.qryCountAllContactMethod = qryCountAllContactMethod;
	}

}
