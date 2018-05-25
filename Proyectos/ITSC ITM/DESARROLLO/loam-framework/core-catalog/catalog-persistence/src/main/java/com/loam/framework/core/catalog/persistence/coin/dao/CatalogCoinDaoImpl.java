package com.loam.framework.core.catalog.persistence.coin.dao;

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
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogCoin;
import com.loam.framework.core.catalog.persistence.common.CustomGenericException;
import com.loam.framework.core.catalog.persistence.common.JdbcDao;
import com.loam.framework.core.catalog.persistence.common.JdbcQueryParameters;

public class CatalogCoinDaoImpl extends JdbcDao implements CatalogCoinDao {
	private static final Logger log = Logger.getLogger(CatalogCoinDaoImpl.class);

	@Autowired
	private CommonProperties commonProperties;

	private String qryCreateCoin;
	private String qryModifyCoin;
	private String qryConsultCoin;
	private String nextIdCoin;
	private String existIdCoin;
	private JdbcQueryParameters qryAllCoin;
	private JdbcQueryParameters qryCountAllCoin;

	@SuppressWarnings("unused")
	@Override
	public CatalogCoin createCatalogCoinDao(final CatalogCoin catalogCoin) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("createcatalogCoinDao...");
		}

		try {
			final int nextIdMon = jdbcTemplate.queryForInt(nextIdCoin);
			catalogCoin.setIdCoin(BigInteger.valueOf(nextIdMon));
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreateCoin);
					ps.setString(1, String.valueOf(catalogCoin.getIdCoin()));
					ps.setString(2, catalogCoin.getCodCoin());
					ps.setString(3, catalogCoin.getDescCoin());
					ps.setInt(4, catalogCoin.getStatusFlag());
					ps.setTimestamp(5, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogCoin.getStartDt())));
					ps.setTimestamp(6, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogCoin.getLastUpdateDt())));
					ps.setString(7, catalogCoin.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCoinDaoImpl", "createCatalogCoinDao",
							"Ocurrio un error al hacer createCatalogCoinDao "));
		}
		return catalogCoin;
	}

	@SuppressWarnings("unused")
	@Override
	public CatalogCoin modifyCatalogCoinDao(final CatalogCoin catalogCoin) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modificarcatalogCoinDao...");
		}
		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyCoin);

					ps.setString(1, catalogCoin.getCodCoin());
					ps.setString(2, catalogCoin.getDescCoin());
					ps.setInt(3, catalogCoin.getStatusFlag());
					ps.setDate(4, (Date) DateUtilsCommons.toDate(catalogCoin.getStartDt()));
					ps.setDate(5, (Date) DateUtilsCommons.toDate(catalogCoin.getLastUpdateDt()));
					ps.setString(6, catalogCoin.getLastUpdateUser());
					ps.setString(7, String.valueOf(catalogCoin.getIdCoin()));
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCoinDaoImpl", "modifyCatalogCoinDao",
							"Ocurrio un error al hacer modifyCatalogCoinDao "));
		}
		return catalogCoin;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CatalogCoin> consultCatalogCoinDao(long idCoin, int startReg, int endReg, String order, String descCoin,
			int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultarcatalogCoinDao...");
		}

		StringBuffer stb = new StringBuffer(qryAllCoin.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllCoin.getOptionalParameters();
		if (idCoin != 0) {
			stb.append(" ").append(params.get("idCoin"));
			parameters.add(idCoin);
		}
		if (descCoin != null && !descCoin.equals("")) {
			stb.append(" ").append(params.get("descCoin"));
			parameters.add("%" + descCoin.toUpperCase() + "%");
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

		if (qryAllCoin.getSqlLast() != null && !qryAllCoin.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllCoin.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<CatalogCoin> consultarcatalogCoinDao = null;

		try {
			consultarcatalogCoinDao = (List<CatalogCoin>) jdbcTemplate.query(qryConsultCoin, args, MAPPER_CAT_COIN);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCoinDaoImpl", "consultCatalogCoinDao",
							"Ocurrio un error al hacer consultCatalogCoinDao "));
		}
		return consultarcatalogCoinDao;
	}

	private static ParameterizedRowMapper<CatalogCoin> MAPPER_CAT_COIN = new ParameterizedRowMapper<CatalogCoin>() {
		public CatalogCoin mapRow(ResultSet rs, int rowNum) throws SQLException {
			CatalogCoin obj = new CatalogCoin();
			obj.setIdCoin(BigInteger.valueOf(Long.valueOf(rs.getString("ID_COIN_CAT"))));
			obj.setCodCoin(rs.getString("COD_COIN_CAT"));
			obj.setDescCoin(rs.getString("DESC_COIN_CAT"));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("START_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));

			return obj;
		}

	};

	@Override
	public int existCatalogCoinDao(long idCoin) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existecatalogCoinDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idCoin);
		Object[] args = parameters.toArray();
		int exisidCoin = 0;

		try {
			exisidCoin = jdbcTemplate.queryForInt(existIdCoin, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCoinDaoImpl", "existCatalogCoinDao",
							"Ocurrio un error al hacer existCatalogCoinDao "));
		}
		return exisidCoin;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countCatalogCoinDao(long idCoin, int startReg, int endReg, String order, String descCoin, int statusFlag)
			throws Exception {
		StringBuffer stb = new StringBuffer(qryCountAllCoin.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountAllCoin.getOptionalParameters();
		if (idCoin != 0) {
			stb.append(" ").append(params.get("idCoin"));
			parameters.add(idCoin);
		}
		if (descCoin != null && !descCoin.equals("")) {
			stb.append(" ").append(params.get("descCoin"));
			parameters.add("%" + descCoin.toUpperCase() + "%");
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

		if (qryCountAllCoin.getSqlLast() != null && !qryCountAllCoin.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountAllCoin.getSqlLast());
		}

		Object[] args = parameters.toArray();

		int countrcatalogCoinDao = 0;
		try {
			countrcatalogCoinDao = jdbcTemplate.queryForInt(qryConsultCoin, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCoinDaoImpl", "countCatalogCoinDao",
							"Ocurrio un error al hacer countCatalogCoinDao "));
		}

		return countrcatalogCoinDao;
	}

	public void setQryCreateCoin(String qryCreateCoin) {
		this.qryCreateCoin = qryCreateCoin;
	}

	public void setQryModifyCoin(String qryModifyCoin) {
		this.qryModifyCoin = qryModifyCoin;
	}

	public void setQryConsultCoin(String qryConsultCoin) {
		this.qryConsultCoin = qryConsultCoin;
	}

	public void setNextIdCoin(String nextIdCoin) {
		this.nextIdCoin = nextIdCoin;
	}

	public void setExistIdCoin(String existIdCoin) {
		this.existIdCoin = existIdCoin;
	}

	public void setQryAllCoin(JdbcQueryParameters qryAllCoin) {
		this.qryAllCoin = qryAllCoin;
	}

	public void setQryCountAllCoin(JdbcQueryParameters qryCountAllCoin) {
		this.qryCountAllCoin = qryCountAllCoin;
	}

}
