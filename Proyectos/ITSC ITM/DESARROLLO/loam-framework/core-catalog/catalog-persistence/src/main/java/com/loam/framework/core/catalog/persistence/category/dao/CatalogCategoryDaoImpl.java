package com.loam.framework.core.catalog.persistence.category.dao;

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
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogCategory;
import com.loam.framework.core.catalog.persistence.common.JdbcDao;
import com.loam.framework.core.catalog.persistence.common.JdbcQueryParameters;

public class CatalogCategoryDaoImpl extends JdbcDao implements CatalogCategoryDao {
	private static final Logger log = Logger.getLogger(CatalogCategoryDaoImpl.class);

	@Autowired
	private CommonProperties commonProperties;

	private String qryCreateCategory;
	private String qryModifyCategory;
	private String nextIdCategory;
	private String existIdCategory;
	private JdbcQueryParameters qryAllCategory;
	private JdbcQueryParameters qryCountAllCategory;

	private static ParameterizedRowMapper<CatalogCategory> MAPPER_CAT_CATEGORY = new ParameterizedRowMapper<CatalogCategory>() {
		public CatalogCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
			CatalogCategory obj = new CatalogCategory();

			obj.setIdCategory(BigInteger.valueOf(Long.valueOf(rs.getString("ID_CATEGORIE_CAT"))));
			obj.setDescCategory(rs.getString("DESC_CATEGORIE_CAT"));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("Start_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));

			return obj;
		}
	};

	@SuppressWarnings("unused")
	@Override
	public CatalogCategory createCatalogCategoryDao(final CatalogCategory catalogCategory) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("createCatalogCategoryDao...");
		}
		try {
			final int idCategory = jdbcTemplate.queryForInt(nextIdCategory);
			catalogCategory.setIdCategory(BigInteger.valueOf(idCategory));
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreateCategory);
					ps.setString(1, String.valueOf(catalogCategory.getIdCategory()));
					ps.setString(2, catalogCategory.getDescCategory());
					ps.setInt(3, catalogCategory.getStatusFlag());
					ps.setTimestamp(4, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogCategory.getStartDt())));
					ps.setTimestamp(5, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogCategory.getLastUpdateDt())));
					ps.setString(6, catalogCategory.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCategoryDaoImpl", "createCatalogCategoryDao",
							"Ocurrio un error al hacer createCatalogCategoryDao "));
		}
		return catalogCategory;
	}

	@SuppressWarnings("unused")
	@Override
	public CatalogCategory modifyCatalogCategoryDao(final CatalogCategory catalogCategory) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modifyCatalogCategoryDao...");
		}
		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyCategory);

					ps.setString(1, catalogCategory.getDescCategory());
					ps.setInt(2, catalogCategory.getStatusFlag());
					ps.setTimestamp(3, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogCategory.getStartDt())));
					ps.setTimestamp(4, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogCategory.getLastUpdateDt())));
					ps.setString(5, catalogCategory.getLastUpdateUser());
					ps.setString(6, String.valueOf(catalogCategory.getIdCategory()));
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCategoryDaoImpl", "modifyCatalogCategoryDao",
							"Ocurrio un error al hacer modifyCatalogCategoryDao "));
		}
		return catalogCategory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CatalogCategory> consultCatalogCategoryDao(long idCategory, int startReg, int endReg, String order,
			String descCategory, int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultCatalogCategoryDao...");
		}
		StringBuffer stb = new StringBuffer(qryAllCategory.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllCategory.getOptionalParameters();
		if (idCategory != 0) {
			stb.append(" ").append(params.get("idCategory"));
			parameters.add(idCategory);
		}
		if (descCategory != null && !descCategory.equals("")) {
			stb.append(" ").append(params.get("descCategory"));
			parameters.add("%" + descCategory.toUpperCase() + "%");
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

		if (qryAllCategory.getSqlLast() != null && !qryAllCategory.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllCategory.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<CatalogCategory> consultCatalogCategoryDao = null;
		try {
			consultCatalogCategoryDao = (List<CatalogCategory>) jdbcTemplate.query(stb.toString(), args,
					MAPPER_CAT_CATEGORY);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCategoryDaoImpl", "consultCatalogCategoryDao",
							"Ocurrio un error al hacer consultCatalogCategoryDao "));
		}
		return consultCatalogCategoryDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countCatalogCategoryDao(long idCategory, int startReg, int endReg, String order, String descCategory,
			int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("countCatalogCategoryDao...");
		}
		StringBuffer stb = new StringBuffer(qryCountAllCategory.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountAllCategory.getOptionalParameters();
		if (idCategory != 0) {
			stb.append(" ").append(params.get("idCategory"));
			parameters.add(idCategory);
		}
		if (descCategory != null && !descCategory.equals("")) {
			stb.append(" ").append(params.get("descCategory"));
			parameters.add("%" + descCategory.toUpperCase() + "%");
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

		if (qryCountAllCategory.getSqlLast() != null && !qryCountAllCategory.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountAllCategory.getSqlLast());
		}

		Object[] args = parameters.toArray();
		int countCategorylDao = 0;
		try {
			countCategorylDao = jdbcTemplate.queryForInt(stb.toString(), args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCategoryDaoImpl", "countCatalogCategoryDao",
							"Ocurrio un error al hacer countCatalogCategoryDao "));
		}
		return countCategorylDao;
	}

	@Override
	public int existCatalogCategoryDao(long idCategory) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existCatalogCategoryDao...");
		}
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idCategory);
		Object[] args = parameters.toArray();
		int existCategory = 0;
		try {
			existCategory = jdbcTemplate.queryForInt(existIdCategory, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogCategoryDaoImpl", "existCatalogCategoryDao",
							"Ocurrio un error al hacer existCatalogCategoryDao "));
		}
		return existCategory;
	}

	public void setQryCreateCategory(String qryCreateCategory) {
		this.qryCreateCategory = qryCreateCategory;
	}

	public void setQryModifyCategory(String qryModifyCategory) {
		this.qryModifyCategory = qryModifyCategory;
	}

	public void setNextIdCategory(String nextIdCategory) {
		this.nextIdCategory = nextIdCategory;
	}

	public void setExistIdCategory(String existIdCategory) {
		this.existIdCategory = existIdCategory;
	}

	public void setQryAllCategory(JdbcQueryParameters qryAllCategory) {
		this.qryAllCategory = qryAllCategory;
	}

	public void setQryCountAllCategory(JdbcQueryParameters qryCountAllCategory) {
		this.qryCountAllCategory = qryCountAllCategory;
	}

}
