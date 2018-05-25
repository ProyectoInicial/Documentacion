package com.loam.framework.core.catalog.persistence.account.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
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
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogAccount;
import com.loam.framework.core.catalog.persistence.common.CustomGenericException;
import com.loam.framework.core.catalog.persistence.common.JdbcDao;
import com.loam.framework.core.catalog.persistence.common.JdbcQueryParameters;

public class CatalogAccountDaoImpl extends JdbcDao implements CatalogAccountDao {
	private static final Logger log = Logger.getLogger(CatalogAccountDaoImpl.class);

	@Autowired
	private CommonProperties commonProperties;

	private String qryCreateAccount;
	private String qryModifyAccount;
	private String qryConsultAccount;
	private String nextIdAccount;
	private String existIdAccount;
	private JdbcQueryParameters qryAllAccount;
	private JdbcQueryParameters qryCountAllAccount;

	@SuppressWarnings("unused")
	@Override
	public CatalogAccount createCatalogAccountDao(final CatalogAccount catalogAccount) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("CreateCatalogAccountDao...");
		}

		try {
			final int nextId = jdbcTemplate.queryForInt(nextIdAccount);
			catalogAccount.setIdAccount(BigInteger.valueOf(nextId));
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreateAccount);
					ps.setString(1, String.valueOf(catalogAccount.getIdAccount()));
					ps.setString(2, catalogAccount.getTypeAccount());
					ps.setString(3, catalogAccount.getDescAccount());
					ps.setInt(4, catalogAccount.getStatusFlag());
					ps.setTimestamp(5, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogAccount.getStartDt())));
					ps.setTimestamp(6, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogAccount.getLastUpdateDt())));
					ps.setString(7, catalogAccount.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogAccountDaoImpl", "createCatalogAccountDao",
							"Ocurrio un error al hacer createCatalogAccountDao "));
		}
		return catalogAccount;
	}

	@SuppressWarnings("unused")
	@Override
	public CatalogAccount modifyCatalogAccountDao(final CatalogAccount catalogAccount) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ModifyCatalogAccountDao...");
		}
		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyAccount);

					ps.setString(1, catalogAccount.getTypeAccount());
					ps.setString(2, catalogAccount.getDescAccount());
					ps.setInt(3, catalogAccount.getStatusFlag());
					ps.setDate(4, (Date) DateUtilsCommons.toDate(catalogAccount.getStartDt()));
					ps.setDate(5, (Date) DateUtilsCommons.toDate(catalogAccount.getLastUpdateDt()));
					ps.setString(6, catalogAccount.getLastUpdateUser());
					ps.setString(7, String.valueOf(catalogAccount.getIdAccount()));
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogAccountDaoImpl", "modifyCatalogAccountDao",
							"Ocurrio un error al hacer modifyCatalogAccountDao "));
		}
		return catalogAccount;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CatalogAccount> consultCatalogAccountDao(long idAccount, int startReg, int endReg, String order,
			String descAccount, int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ConsultCatalogAccountDao...");
		}

		StringBuffer stb = new StringBuffer(qryAllAccount.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllAccount.getOptionalParameters();
		if (idAccount != 0) {
			stb.append(" ").append(params.get("idAccount"));
			parameters.add(idAccount);
		}
		if (descAccount != null && !descAccount.equals("")) {
			stb.append(" ").append(params.get("descAccount"));
			parameters.add("%" + descAccount.toUpperCase() + "%");
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

		if (qryAllAccount.getSqlLast() != null && !qryAllAccount.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllAccount.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<CatalogAccount> ConsultCatalogAccountDao = null;

		try {
			ConsultCatalogAccountDao = (List<CatalogAccount>) jdbcTemplate.query(qryConsultAccount, args,
					MAPPER_CAT_ACCOUNT);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogAccountDaoImpl", "consultCatalogAccountDao",
							"Ocurrio un error al hacer consultCatalogAccountDao "));
		}
		return ConsultCatalogAccountDao;
	}

	@Override
	public int existCatalogAccountDao(long idAccount) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existCatalogAccountDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idAccount);
		Object[] args = parameters.toArray();
		int exisIdAccount = 0;

		try {
			exisIdAccount = jdbcTemplate.queryForInt(existIdAccount, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogAccountDaoImpl", "existCatalogAccountDao",
							"Ocurrio un error al hacer existCatalogAccountDao "));
		}
		return exisIdAccount;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countCatalogAccountDao(long idAccount, int startReg, int endReg, String order, String descAccount,
			int statusFlag) throws Exception {
		StringBuffer stb = new StringBuffer(qryCountAllAccount.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountAllAccount.getOptionalParameters();
		if (idAccount != 0) {
			stb.append(" ").append(params.get("idAccount"));
			parameters.add(idAccount);
		}
		if (descAccount != null && !descAccount.equals("")) {
			stb.append(" ").append(params.get("descAccount"));
			parameters.add("%" + descAccount.toUpperCase() + "%");
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

		if (qryCountAllAccount.getSqlLast() != null && !qryCountAllAccount.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountAllAccount.getSqlLast());
		}

		Object[] args = parameters.toArray();
		int countCatalogAccountDao = 0;
		try {
			countCatalogAccountDao = jdbcTemplate.queryForInt(qryConsultAccount, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogAccountDaoImpl", "countCatalogAccountDao",
							"Ocurrio un error al hacer countCatalogAccountDao "));
		}
		return countCatalogAccountDao;
	}

	private static ParameterizedRowMapper<CatalogAccount> MAPPER_CAT_ACCOUNT = new ParameterizedRowMapper<CatalogAccount>() {
		public CatalogAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
			CatalogAccount obj = new CatalogAccount();

			obj.setIdAccount(BigInteger.valueOf(Long.valueOf(rs.getString("ID_ACCOUNT_CAT"))));
			obj.setTypeAccount(rs.getString("TYPE_ACCOUNT_CAT"));
			obj.setDescAccount(rs.getString("DESC_ACCOUNT_CAT"));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("START_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));

			return obj;
		}

	};

	public String getQryCreateAccount() {
		return qryCreateAccount;
	}

	public void setQryCreateAccount(String qryCreateAccount) {
		this.qryCreateAccount = qryCreateAccount;
	}

	public String getQryModifyAccount() {
		return qryModifyAccount;
	}

	public void setQryModifyAccount(String qryModifyAccount) {
		this.qryModifyAccount = qryModifyAccount;
	}

	public String getQryConsultAccount() {
		return qryConsultAccount;
	}

	public void setQryConsultAccount(String qryConsultAccount) {
		this.qryConsultAccount = qryConsultAccount;
	}

	public String getNextIdAccount() {
		return nextIdAccount;
	}

	public void setNextIdAccount(String nextIdAccount) {
		this.nextIdAccount = nextIdAccount;
	}

	public void setexistIdAccount(String existIdAccount) {
		this.existIdAccount = existIdAccount;
	}

	public void setQryAllAccount(JdbcQueryParameters qryAllAccount) {
		this.qryAllAccount = qryAllAccount;
	}

	public void setQryCountAllAccount(JdbcQueryParameters qryCountAllAccount) {
		this.qryCountAllAccount = qryCountAllAccount;
	}

}
