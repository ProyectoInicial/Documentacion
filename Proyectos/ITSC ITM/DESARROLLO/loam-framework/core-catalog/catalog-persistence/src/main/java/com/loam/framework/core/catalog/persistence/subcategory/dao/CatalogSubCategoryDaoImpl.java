package com.loam.framework.core.catalog.persistence.subcategory.dao;

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
import com.loam.framework.core.catalog.commons.utils.exception.CustomGenericException;
import com.loam.framework.core.catalog.commons.utils.exception.ExcepcionGenerica;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogSubCategory;
import com.loam.framework.core.catalog.persistence.common.JdbcDao;
import com.loam.framework.core.catalog.persistence.common.JdbcQueryParameters;

public class CatalogSubCategoryDaoImpl extends JdbcDao implements CatalogSubCategoryDao {
	private static final Logger log = Logger.getLogger(CatalogSubCategoryDaoImpl.class);

	@Autowired
	private CommonProperties commonProperties;

	private String qryCreateSubCategory;
	private String qryModifySubCategory;
	private String nextIdSubCategory;
	private String existIdSubCategory;
	private JdbcQueryParameters qryAllSubCategory;
	private JdbcQueryParameters qryCountAllSubCategory;

	private static ParameterizedRowMapper<CatalogSubCategory> MAPPER_SUB_CATEGORY = new ParameterizedRowMapper<CatalogSubCategory>() {
		public CatalogSubCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
			CatalogSubCategory obj = new CatalogSubCategory();
			obj.setIdSubCategory(BigInteger.valueOf(Long.valueOf(rs.getString("ID_SUBCATEGORIE_CAT"))));
			obj.setIdCategory(BigInteger.valueOf(Long.valueOf(rs.getString("ID_CATEGORIE_CAT"))));
			obj.setDescSubCategory(rs.getString("DESC_SUBCATEGORIE_CAT"));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("START_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));

			return obj;
		}
	};

	@SuppressWarnings("unused")
	@Override
	public CatalogSubCategory createCatalogSubCategoryDao(final CatalogSubCategory catalogSubCategory)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("createCatalogSubCategoryDao...");
		}
		try {
			final int idSubCategory = jdbcTemplate.queryForInt(nextIdSubCategory);
			catalogSubCategory.setIdSubCategory(BigInteger.valueOf(idSubCategory));
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreateSubCategory);
					ps.setString(1, String.valueOf(catalogSubCategory.getIdSubCategory()));
					ps.setString(2, String.valueOf(catalogSubCategory.getIdCategory()));
					ps.setString(3, catalogSubCategory.getDescSubCategory());
					ps.setInt(4, catalogSubCategory.getStatusFlag());
					ps.setTimestamp(5, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogSubCategory.getStartDt())));
					ps.setTimestamp(6, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogSubCategory.getLastUpdateDt())));
					ps.setString(7, catalogSubCategory.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogSubCategoryDaoImpl", "createCatalogSubCategoryDao",
							"Ocurrio un error al hacer createCatalogSubCategoryDao "));
		}
		return catalogSubCategory;
	}

	@SuppressWarnings("unused")
	@Override
	public CatalogSubCategory modifyCatalogSubCategoryDao(final CatalogSubCategory catalogSubCategory)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modifyCatalogSubCategoryDao...");
		}
		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifySubCategory);

					ps.setString(1, String.valueOf(catalogSubCategory.getIdCategory()));
					ps.setString(2, catalogSubCategory.getDescSubCategory());
					ps.setInt(3, catalogSubCategory.getStatusFlag());
					ps.setTimestamp(4, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogSubCategory.getStartDt())));
					ps.setTimestamp(5, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogSubCategory.getLastUpdateDt())));
					ps.setString(6, catalogSubCategory.getLastUpdateUser());
					ps.setString(7, String.valueOf(catalogSubCategory.getIdSubCategory()));
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogSubCategoryDaoImpl", "modifyCatalogSubCategoryDao",
							"Ocurrio un error al hacer modifyCatalogSubCategoryDao "));
		}
		return catalogSubCategory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CatalogSubCategory> consultCatalogSubCategoryDao(long idSubCategory, long idCategory, int startReg,
			int endReg, String order, String descSubCategory, int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultCatalogSubCategoryDao...");
		}
		StringBuffer stb = new StringBuffer(qryAllSubCategory.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllSubCategory.getOptionalParameters();
		if (idSubCategory != 0) {
			stb.append(" ").append(params.get("idSubCategory"));
			parameters.add(idSubCategory);
		}
		if (idCategory != 0) {
			stb.append(" ").append(params.get("idCategory"));
			parameters.add(idCategory);
		}
		if (descSubCategory != null && !descSubCategory.equals("")) {
			stb.append(" ").append(params.get("descSubCategory"));
			parameters.add("%" + descSubCategory.toUpperCase() + "%");
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

		if (qryAllSubCategory.getSqlLast() != null && !qryAllSubCategory.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllSubCategory.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<CatalogSubCategory> consultCatalogSubCategoryDao = null;
		try {
			consultCatalogSubCategoryDao = (List<CatalogSubCategory>) jdbcTemplate.query(stb.toString(), args,
					MAPPER_SUB_CATEGORY);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogSubCategoryDaoImpl", "consultCatalogSubCategoryDao",
							"Ocurrio un error al hacer consultCatalogSubCategoryDao "));
		}
		return consultCatalogSubCategoryDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countCatalogSubCategoryDao(long idSubCategory, long idCategory, int startReg, int endReg, String order,
			String descSubCategory, int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("countCatalogSubCategoryDao...");
		}
		StringBuffer stb = new StringBuffer(qryCountAllSubCategory.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountAllSubCategory.getOptionalParameters();
		if (idSubCategory != 0) {
			stb.append(" ").append(params.get("idSubCategory"));
			parameters.add(idSubCategory);
		}
		if (idCategory != 0) {
			stb.append(" ").append(params.get("idCategory"));
			parameters.add(idCategory);
		}
		if (descSubCategory != null && !descSubCategory.equals("")) {
			stb.append(" ").append(params.get("descRol"));
			parameters.add("%" + descSubCategory.toUpperCase() + "%");
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

		if (qryCountAllSubCategory.getSqlLast() != null && !qryCountAllSubCategory.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountAllSubCategory.getSqlLast());
		}

		Object[] args = parameters.toArray();
		int countSubCategoryDao = 0;
		try {
			countSubCategoryDao = jdbcTemplate.queryForInt(stb.toString(), args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogSubCategoryDaoImpl", "countCatalogSubCategoryDao",
							"Ocurrio un error al hacer countCatalogSubCategoryDao "));
		}
		return countSubCategoryDao;
	}

	@Override
	public int existCatalogSubCategoryDao(long idSubCategory) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existCatalogSubCategoryDao...");
		}
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idSubCategory);
		Object[] args = parameters.toArray();
		int existSubCategory = 0;
		try {
			existSubCategory = jdbcTemplate.queryForInt(existIdSubCategory, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogSubCategoryDaoImpl", "existCatalogSubCategoryDao",
							"Ocurrio un error al hacer existCatalogSubCategoryDao "));
		}
		return existSubCategory;
	}

	public void setQryCreateSubCategory(String qryCreateSubCategory) {
		this.qryCreateSubCategory = qryCreateSubCategory;
	}

	public void setQryModifySubCategory(String qryModifySubCategory) {
		this.qryModifySubCategory = qryModifySubCategory;
	}

	public void setNextIdSubCategory(String nextIdSubCategory) {
		this.nextIdSubCategory = nextIdSubCategory;
	}

	public void setExistIdSubCategory(String existIdSubCategory) {
		this.existIdSubCategory = existIdSubCategory;
	}

	public void setQryAllSubCategory(JdbcQueryParameters qryAllSubCategory) {
		this.qryAllSubCategory = qryAllSubCategory;
	}

	public void setQryCountAllSubCategory(JdbcQueryParameters qryCountAllSubCategory) {
		this.qryCountAllSubCategory = qryCountAllSubCategory;
	}

}
