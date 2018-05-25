package com.loam.framework.core.catalog.persistence.color.dao;

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
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogColor;
import com.loam.framework.core.catalog.persistence.common.CustomGenericException;
import com.loam.framework.core.catalog.persistence.common.JdbcDao;
import com.loam.framework.core.catalog.persistence.common.JdbcQueryParameters;

public class CatalogColorDaoImpl extends JdbcDao implements CatalogColorDao {

	private static final Logger log = Logger.getLogger(CatalogColorDaoImpl.class);

	@Autowired
	private CommonProperties commonProperties;

	private String qryCreateColor;
	private String qryModifyColor;
	private String qryConsultColor;
	private String nextIdColor;
	private String existIdColor;
	private JdbcQueryParameters qryAllColor;
	private JdbcQueryParameters qryCountAllColor;

	private static ParameterizedRowMapper<CatalogColor> MAPPER_CAT_COLOR = new ParameterizedRowMapper<CatalogColor>() {
		public CatalogColor mapRow(ResultSet rs, int rowNum) throws SQLException {
			CatalogColor obj = new CatalogColor();
			obj.setIdColor(BigInteger.valueOf(Long.valueOf(rs.getString("ID_COLOR_CAT"))));
			obj.setDescColor(rs.getString("DESC_COLOR_CAT"));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("START_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));

			return obj;
		}

	};

	@SuppressWarnings("unused")
	@Override
	public CatalogColor createCatalogColorDao(final CatalogColor catalogColor) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("createCatalogColorDao...");
		}

		try {
			final int nextIdCol = jdbcTemplate.queryForInt(nextIdColor);
			catalogColor.setIdColor(BigInteger.valueOf(nextIdCol));
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreateColor);
					ps.setString(1, String.valueOf(catalogColor.getIdColor()));
					ps.setString(2, catalogColor.getDescColor());
					ps.setInt(3, catalogColor.getStatusFlag());
					ps.setTimestamp(4, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogColor.getStartDt())));
					ps.setTimestamp(5, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogColor.getLastUpdateDt())));
					ps.setString(6, catalogColor.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogColorDaoImpl", "createCatalogColorDao",
							"Ocurrio un error al hacer createCatalogColorDao "));
		}
		return catalogColor;
	}

	@SuppressWarnings("unused")
	@Override
	public CatalogColor modifyCatalogColorDao(final CatalogColor catalogColor) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modifyCatalogColorDao...");
		}
		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyColor);

					ps.setString(1, catalogColor.getDescColor());
					ps.setInt(2, catalogColor.getStatusFlag());
					ps.setDate(3, (Date) DateUtilsCommons.toDate(catalogColor.getStartDt()));
					ps.setDate(4, (Date) DateUtilsCommons.toDate(catalogColor.getLastUpdateDt()));
					ps.setString(5, catalogColor.getLastUpdateUser());
					ps.setString(6, String.valueOf(catalogColor.getIdColor()));
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogColorDaoImpl", "modifyCatalogColorDao",
							"Ocurrio un error al hacer modifyCatalogColorDao "));
		}
		return catalogColor;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CatalogColor> consultCatalogColorDao(long idColor, int startReg, int endReg, String order,
			String descColor, int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultCatalogColorDao...");
		}

		StringBuffer stb = new StringBuffer(qryAllColor.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllColor.getOptionalParameters();
		if (idColor != 0) {
			stb.append(" ").append(params.get("idColor"));
			parameters.add(idColor);
		}
		if (descColor != null && !descColor.equals("")) {
			stb.append(" ").append(params.get("descColor"));
			parameters.add("%" + descColor.toUpperCase() + "%");
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

		if (qryAllColor.getSqlLast() != null && !qryAllColor.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllColor.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<CatalogColor> consultarcatalogColorDao = null;

		try {
			consultarcatalogColorDao = (List<com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogColor>) jdbcTemplate
					.query(qryConsultColor, args, MAPPER_CAT_COLOR);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogColorDaoImpl", "consultCatalogColorDao",
							"Ocurrio un error al hacer consultCatalogColorDao "));
		}
		return consultarcatalogColorDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countCatalogColorDao(long idColor, int startReg, int endReg, String order, String descColor,
			int statusFlag) throws Exception {
		StringBuffer stb = new StringBuffer(qryCountAllColor.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountAllColor.getOptionalParameters();
		if (idColor != 0) {
			stb.append(" ").append(params.get("idColor"));
			parameters.add(idColor);
		}
		if (descColor != null && !descColor.equals("")) {
			stb.append(" ").append(params.get("descColor"));
			parameters.add("%" + descColor.toUpperCase() + "%");
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

		if (qryCountAllColor.getSqlLast() != null && !qryCountAllColor.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountAllColor.getSqlLast());
		}

		Object[] args = parameters.toArray();

		int countrcatalogColorDao = 0;
		try {
			countrcatalogColorDao = jdbcTemplate.queryForInt(qryConsultColor, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogColorDaoImpl", "countCatalogColorDao",
							"Ocurrio un error al hacer countCatalogColorDao "));
		}
		return countrcatalogColorDao;
	}

	@Override
	public int existCatalogColorDao(long idColor) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existecatalogColorDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idColor);
		Object[] args = parameters.toArray();
		int exisidColor = 0;

		try {
			exisidColor = jdbcTemplate.queryForInt(existIdColor, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogColorDaoImpl", "existCatalogColorDao",
							"Ocurrio un error al hacer existCatalogColorDao "));
		}
		return exisidColor;
	}

	public void setQryCreateColor(String qryCreateColor) {
		this.qryCreateColor = qryCreateColor;
	}

	public void setQryModifyColor(String qryModifyColor) {
		this.qryModifyColor = qryModifyColor;
	}

	public void setQryConsultColor(String qryConsultColor) {
		this.qryConsultColor = qryConsultColor;
	}

	public void setNextIdColor(String nextIdColor) {
		this.nextIdColor = nextIdColor;
	}

	public void setExistIdColor(String existIdColor) {
		this.existIdColor = existIdColor;
	}

	public void setQryAllColor(JdbcQueryParameters qryAllColor) {
		this.qryAllColor = qryAllColor;
	}

	public void setQryCountAllColor(JdbcQueryParameters qryCountAllColor) {
		this.qryCountAllColor = qryCountAllColor;
	}

}
