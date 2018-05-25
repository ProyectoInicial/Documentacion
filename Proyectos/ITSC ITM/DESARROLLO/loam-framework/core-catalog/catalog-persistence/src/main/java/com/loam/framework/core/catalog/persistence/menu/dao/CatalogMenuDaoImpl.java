package com.loam.framework.core.catalog.persistence.menu.dao;

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
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogMenu;
import com.loam.framework.core.catalog.jaxb.ws.catalogmanager.CatalogRolMenu;
import com.loam.framework.core.catalog.persistence.common.CustomGenericException;
import com.loam.framework.core.catalog.persistence.common.JdbcDao;
import com.loam.framework.core.catalog.persistence.common.JdbcQueryParameters;

public class CatalogMenuDaoImpl extends JdbcDao implements CatalogMenuDao {
	private static final Logger log = Logger.getLogger(CatalogMenuDaoImpl.class);

	@Autowired
	private CommonProperties commonProperties;

	private String qryCreateMenu;
	private String qryModifyMenu;
	private String nextIdMenu;
	private String existIdMenu;
	private JdbcQueryParameters qryAllMenu;
	private JdbcQueryParameters qryCountMenu;

	private String qryCreateRolMenu;
	private String qryModifyRolMenu;
	private String nextIdRolMenu;
	private String existIdRolMenu;
	private JdbcQueryParameters qryAllRolMenu;
	private JdbcQueryParameters qryCountRolMenu;

	private String qryConsultMenuStart;

	private static ParameterizedRowMapper<CatalogMenu> MAPPER_MENU = new ParameterizedRowMapper<CatalogMenu>() {
		public CatalogMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
			CatalogMenu obj = new CatalogMenu();
			obj.setIdMenu(BigInteger.valueOf(Long.valueOf(rs.getString("ID_MENU_CAT"))));
			obj.setDescMenu(rs.getString("DESC_MENU_CAT"));
			obj.setUrlMenu(rs.getString("URL_MENU_CAT"));
			obj.setIdMenuPadre(BigInteger.valueOf(Long.valueOf(rs.getString("ID_MENU_PADRE_CAT"))));
			obj.setDescMenuPadre(rs.getString("DESC_MENU_PADRE_CAT"));
			obj.setTypeMenu(rs.getString("TYPE_MENU_CAT"));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons
					.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("START_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons
					.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));
			return obj;
		}
	};

	private static ParameterizedRowMapper<CatalogRolMenu> MAPPER_ROL_MENU = new ParameterizedRowMapper<CatalogRolMenu>() {
		public CatalogRolMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
			CatalogRolMenu obj = new CatalogRolMenu();
			obj.setIdRolMenu(BigInteger.valueOf(Long.valueOf(rs.getString("ID_ROL_MENU"))));
			obj.setIdMenu(BigInteger.valueOf(Long.valueOf(rs.getString("ID_MENU"))));
			obj.setIdRol(BigInteger.valueOf(Long.valueOf(rs.getString("ID_ROL"))));
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
	public CatalogMenu createCatalogMenuDao(final CatalogMenu catalogMenu) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("createCatalogMenuDao...");
		}

		try {
			final int nextId = jdbcTemplate.queryForInt(nextIdMenu);
			catalogMenu.setIdMenu(BigInteger.valueOf(nextId));
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreateMenu);
					ps.setString(1, String.valueOf(catalogMenu.getIdMenu()));
					ps.setString(2, catalogMenu.getDescMenu());
					ps.setString(3, catalogMenu.getUrlMenu());
					ps.setString(4, String.valueOf(catalogMenu.getIdMenuPadre()));
					ps.setString(5, catalogMenu.getDescMenuPadre());
					ps.setString(6, catalogMenu.getTypeMenu());
					ps.setInt(7, catalogMenu.getStatusFlag());
					ps.setTimestamp(8, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogMenu.getStartDt())));
					ps.setTimestamp(9, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogMenu.getLastUpdateDt())));
					ps.setString(10, catalogMenu.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuDaoImpl", "createCatalogMenuDao",
							"Ocurrio un error al hacer createCatalogMenuDao "));
		}
		return catalogMenu;
	}

	@SuppressWarnings("unused")
	@Override
	public CatalogMenu modifyCatalogMenuDao(final CatalogMenu catalogMenu) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modifyCatalogMenuDao...");
		}

		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyMenu);

					ps.setString(1, catalogMenu.getDescMenu());
					ps.setString(2, catalogMenu.getUrlMenu());
					ps.setString(3, String.valueOf(catalogMenu.getIdMenuPadre()));
					ps.setString(4, catalogMenu.getDescMenuPadre());
					ps.setString(5, catalogMenu.getTypeMenu());
					ps.setInt(6, catalogMenu.getStatusFlag());
					ps.setTimestamp(7, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogMenu.getStartDt())));
					ps.setTimestamp(8, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogMenu.getLastUpdateDt())));
					ps.setString(9, catalogMenu.getLastUpdateUser());
					ps.setString(10, String.valueOf(catalogMenu.getIdMenu()));

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuDaoImpl", "modifyCatalogMenuDao",
							"Ocurrio un error al hacer modifyCatalogMenuDao "));
		}
		return catalogMenu;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CatalogMenu> consultCatalogMenuDao(long idMenu, int startReg, int endReg, String order, String descMenu,
			int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultCatalogMenuDao...");
		}
		StringBuffer stb = new StringBuffer(qryAllMenu.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllMenu.getOptionalParameters();
		if (idMenu != 0) {
			stb.append(" ").append(params.get("idMenu"));
			parameters.add(idMenu);
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

		if (qryAllMenu.getSqlLast() != null && !qryAllMenu.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllMenu.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<CatalogMenu> catalogMenuDao = null;
		try {
			catalogMenuDao = (List<CatalogMenu>) jdbcTemplate.query(stb.toString(), args, MAPPER_MENU);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuDaoImpl", "consultCatalogMenuDao",
							"Ocurrio un error al hacer consultCatalogMenuDao "));
		}
		return catalogMenuDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countCatalogMenuDao(long idMenu, int startReg, int endReg, String order, String descMenu, int statusFlag)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("countCatalogMenuDao...");
		}
		StringBuffer stb = new StringBuffer(qryCountMenu.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountMenu.getOptionalParameters();
		if (idMenu != 0) {
			stb.append(" ").append(params.get("idMenu"));
			parameters.add(idMenu);
		}
		if (descMenu != null) {
			stb.append(" ").append(params.get("descMenu"));
			parameters.add(descMenu);
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

		if (qryCountMenu.getSqlLast() != null && !qryCountMenu.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountMenu.getSqlLast());
		}

		Object[] args = parameters.toArray();
		int countMenuDao = 0;
		try {
			countMenuDao = jdbcTemplate.queryForInt(stb.toString(), args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuDaoImpl", "countCatalogMenuDao",
							"Ocurrio un error al hacer countCatalogMenuDao "));
		}
		return countMenuDao;
	}

	@Override
	public int existCatalogMenuDao(long idMenu) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existCatalogMenuDao...");
		}
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idMenu);
		Object[] args = parameters.toArray();
		int existMenu = 0;
		try {
			existMenu = jdbcTemplate.queryForInt(existIdMenu, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuDaoImpl", "existCatalogMenuDao",
							"Ocurrio un error al hacer existCatalogMenuDao "));
		}
		return existMenu;
	}

	@SuppressWarnings("unused")
	@Override
	public CatalogRolMenu createCatalogRolMenuDao(final CatalogRolMenu catalogRolMenu) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("createCatalogRolMenuDao...");
		}

		try {
			final int nextId = jdbcTemplate.queryForInt(nextIdRolMenu);

			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreateRolMenu);
					ps.setInt(1, nextId);
					ps.setString(2, String.valueOf(catalogRolMenu.getIdMenu()));
					ps.setString(3, String.valueOf(catalogRolMenu.getIdRol()));
					ps.setInt(4, catalogRolMenu.getStatusFlag());
					ps.setTimestamp(5, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogRolMenu.getStartDt())));
					ps.setTimestamp(6, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogRolMenu.getLastUpdateDt())));
					ps.setString(7, catalogRolMenu.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuDaoImpl", "createCatalogRolMenuDao",
							"Ocurrio un error al hacer createCatalogRolMenuDao "));
		}
		return catalogRolMenu;
	}

	@SuppressWarnings("unused")
	@Override
	public CatalogRolMenu modifyCatalogRolMenuDao(final CatalogRolMenu catalogRolMenu) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modifyCatalogRolMenuDao...");
		}

		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyRolMenu);

					ps.setString(1, String.valueOf(catalogRolMenu.getIdMenu()));
					ps.setString(2, String.valueOf(catalogRolMenu.getIdRol()));
					ps.setInt(3, catalogRolMenu.getStatusFlag());
					ps.setTimestamp(4, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogRolMenu.getStartDt())));
					ps.setTimestamp(5, DateUtilsCommons
							.convertJavaDateToSqlDate(DateUtilsCommons.toDate(catalogRolMenu.getLastUpdateDt())));
					ps.setString(6, catalogRolMenu.getLastUpdateUser());
					ps.setString(7, String.valueOf(catalogRolMenu.getIdRolMenu()));

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuDaoImpl", "modifyCatalogRolMenuDao",
							"Ocurrio un error al hacer modifyCatalogRolMenuDao "));
		}
		return catalogRolMenu;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CatalogRolMenu> consultCatalogRolMenuDao(long idRolMenu, int startReg, int endReg, String order,
			String descRolMenu, int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultCatalogRolMenuDao...");
		}
		StringBuffer stb = new StringBuffer(qryAllRolMenu.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllRolMenu.getOptionalParameters();
		if (idRolMenu != 0) {
			stb.append(" ").append(params.get("idRolMenu"));
			parameters.add(idRolMenu);
		}
		if (descRolMenu != null) {
			stb.append(" ").append(params.get("descRolMenu"));
			parameters.add(descRolMenu);
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

		if (qryAllRolMenu.getSqlLast() != null && !qryAllRolMenu.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllRolMenu.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<CatalogRolMenu> catalogRolMenuDao = null;
		try {
			catalogRolMenuDao = (List<CatalogRolMenu>) jdbcTemplate.query(stb.toString(), args, MAPPER_ROL_MENU);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuDaoImpl", "consultCatalogRolMenuDao",
							"Ocurrio un error al hacer consultCatalogRolMenuDao "));
		}
		return catalogRolMenuDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countCatalogRolMenuDao(long idRolMenu, int startReg, int endReg, String order, String descRolMenu,
			int statusFlag) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("countCatalogRolMenuDao...");
		}
		StringBuffer stb = new StringBuffer(qryCountRolMenu.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountRolMenu.getOptionalParameters();
		if (idRolMenu != 0) {
			stb.append(" ").append(params.get("idRolMenu"));
			parameters.add(idRolMenu);
		}
		if (descRolMenu != null) {
			stb.append(" ").append(params.get("descRolMenu"));
			parameters.add(descRolMenu);
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

		if (qryCountRolMenu.getSqlLast() != null && !qryCountRolMenu.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountRolMenu.getSqlLast());
		}

		Object[] args = parameters.toArray();
		int countRolMenuDao = 0;
		try {
			countRolMenuDao = jdbcTemplate.queryForInt(stb.toString(), args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuDaoImpl", "countCatalogRolMenuDao",
							"Ocurrio un error al hacer countCatalogRolMenuDao "));
		}
		return countRolMenuDao;
	}

	@Override
	public int existCatalogRolMenuDao(long idRolMenu) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existCatalogRolMenuDao...");
		}
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idRolMenu);
		Object[] args = parameters.toArray();
		int existRolMenu = 0;
		try {
			existRolMenu = jdbcTemplate.queryForInt(existIdRolMenu, args);
		} catch (Exception ex) {
			throw new CustomGenericException(String.valueOf(commonProperties.getIdEstatusError()), ex,
					new ExcepcionGenerica(String.valueOf(commonProperties.getIdEstatusError()),
							commonProperties.getIdEstatusErrorMsg(), commonProperties.getIdEstatusErrorCodigo(),
							"CatalogMenuDaoImpl", "existCatalogRolMenuDao",
							"Ocurrio un error al hacer existCatalogRolMenuDao "));
		}
		return existRolMenu;
	}

	public void setQryCreateMenu(String qryCreateMenu) {
		this.qryCreateMenu = qryCreateMenu;
	}

	public void setQryModifyMenu(String qryModifyMenu) {
		this.qryModifyMenu = qryModifyMenu;
	}

	public void setNextIdMenu(String nextIdMenu) {
		this.nextIdMenu = nextIdMenu;
	}

	public void setExistIdMenu(String existIdMenu) {
		this.existIdMenu = existIdMenu;
	}

	public void setQryAllMenu(JdbcQueryParameters qryAllMenu) {
		this.qryAllMenu = qryAllMenu;
	}

	public void setQryCountMenu(JdbcQueryParameters qryCountMenu) {
		this.qryCountMenu = qryCountMenu;
	}

	public void setQryCreateRolMenu(String qryCreateRolMenu) {
		this.qryCreateRolMenu = qryCreateRolMenu;
	}

	public void setQryModifyRolMenu(String qryModifyRolMenu) {
		this.qryModifyRolMenu = qryModifyRolMenu;
	}

	public void setNextIdRolMenu(String nextIdRolMenu) {
		this.nextIdRolMenu = nextIdRolMenu;
	}

	public void setExistIdRolMenu(String existIdRolMenu) {
		this.existIdRolMenu = existIdRolMenu;
	}

	public void setQryAllRolMenu(JdbcQueryParameters qryAllRolMenu) {
		this.qryAllRolMenu = qryAllRolMenu;
	}

	public void setQryCountRolMenu(JdbcQueryParameters qryCountRolMenu) {
		this.qryCountRolMenu = qryCountRolMenu;
	}

	public void setQryConsultMenuStart(String qryConsultMenuStart) {
		this.qryConsultMenuStart = qryConsultMenuStart;
	}

}
