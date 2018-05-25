package com.loam.framework.core.customer.persistence.contact.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.loam.framework.core.customer.commons.utils.date.DateUtilsCommons;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.UserContactMethod;
import com.loam.framework.core.customer.persistence.common.CustomGenericException;
import com.loam.framework.core.customer.persistence.common.ExcepcionGenerica;
import com.loam.framework.core.customer.persistence.common.JdbcDao;
import com.loam.framework.core.customer.persistence.common.JdbcQueryParameters;

public class PersonContactMethodDaoImpl extends JdbcDao implements PersonContactMethodDao{

	private String qryCreatePersonContactMethod;
	private String qryModifyPersonContactMethod;
	private String nextIdPersonContactMethod;
	private String existPersonContactMethod;
	private JdbcQueryParameters qryAllPersonContactMethod;
	private JdbcQueryParameters qryCountAllPersonContactMethod;

	private static ParameterizedRowMapper<UserContactMethod> MAPPER_PER_CONT_METHOD = new ParameterizedRowMapper<UserContactMethod>() {
		public UserContactMethod mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserContactMethod obj = new UserContactMethod();
			obj.setIdContactMethod(BigInteger.valueOf(Long.valueOf(rs.getString("ID_CONTACT_METHOD"))));
			obj.setIdPerson(BigInteger.valueOf(Long.valueOf(rs.getString("ID_PERSON"))));
			obj.setIdContactMethodCat(BigInteger.valueOf(Long.valueOf(rs.getString("ID_CONTACT_METHOD_CAT"))));
			obj.setRefNum(rs.getString("REF_NUM"));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("START_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));
			return obj;
		}
	};
	
	@SuppressWarnings("unused")
	@Override
	public UserContactMethod createPersonContactMethodDao(final UserContactMethod userContactMethod) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("createPersonContactMethodDao...");
		}
		try {
			final int dPersonContactMethod = jdbcTemplate.queryForInt(nextIdPersonContactMethod);

			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreatePersonContactMethod);
					ps.setInt(1, dPersonContactMethod);
					ps.setString(2, String.valueOf(userContactMethod.getIdPerson()));
					ps.setString(3, String.valueOf(userContactMethod.getIdContactMethodCat()));
					ps.setString(4, userContactMethod.getRefNum());
					ps.setInt(5, userContactMethod.getStatusFlag());
					ps.setTimestamp(6, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(userContactMethod.getStartDt())));
					ps.setTimestamp(7, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(userContactMethod.getLastUpdateDt())));
					ps.setString(8, userContactMethod.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex, new ExcepcionGenerica("ERROR","PersonContactMethodDaoImpl","createPersonContactMethodDao","Ocurrio un error al hacer createCatalogRolDao "));
		}
		return userContactMethod;
	}

	@SuppressWarnings("unused")
	@Override
	public UserContactMethod modifyPersonContactMethodDao(final UserContactMethod userContactMethod) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modifyPersonContactMethodDao...");
		}
		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyPersonContactMethod);
					ps.setString(1, String.valueOf(userContactMethod.getIdPerson()));
					ps.setString(2, String.valueOf(userContactMethod.getIdContactMethodCat()));
					ps.setString(3, userContactMethod.getRefNum());
					ps.setInt(4, userContactMethod.getStatusFlag());
					ps.setTimestamp(5, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(userContactMethod.getStartDt())));
					ps.setTimestamp(6, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(userContactMethod.getLastUpdateDt())));
					ps.setString(7, userContactMethod.getLastUpdateUser());
					ps.setString(8, String.valueOf(userContactMethod.getIdContactMethod()));
					
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex, new ExcepcionGenerica("ERROR","PersonContactMethodDaoImpl","modifyPersonContactMethodDao","Ocurrio un error al hacer createCatalogRolDao "));
		}
		return userContactMethod;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<UserContactMethod> consultPersonContactMethodDao(long idContactMethod, long idContactMethodCat,
			long idPerson, int startReg, int endReg, String order) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultPersonContactMethodDao...");
		}
		StringBuffer stb = new StringBuffer(qryAllPersonContactMethod.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllPersonContactMethod.getOptionalParameters();
		if (idContactMethod != 0) {
			stb.append(" ").append(params.get("idContactMethod"));
			parameters.add(idContactMethod);
		}
		if (idContactMethodCat != 0) {
			stb.append(" ").append(params.get("idContactMethodCat"));
			parameters.add(idContactMethodCat);
		}
		if (idPerson != 0) {
			stb.append(" ").append(params.get("idPerson"));
			parameters.add(idPerson);
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

		if (qryAllPersonContactMethod.getSqlLast() != null && !qryAllPersonContactMethod.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllPersonContactMethod.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<UserContactMethod> consultUserContactMethodDao = null;

		try {
			consultUserContactMethodDao = (List<UserContactMethod>) jdbcTemplate.query(stb.toString(), args, MAPPER_PER_CONT_METHOD);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex, new ExcepcionGenerica("ERROR","PersonContactMethodDaoImpl","consultPersonContactMethodDao","Ocurrio un error al hacer createCatalogRolDao "));
		}
		return consultUserContactMethodDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countPersonContactMethodDao(long idContactMethod, long idContactMethodCat, long idPerson, int startReg,
			int endReg, String order) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("countPersonContactMethodDao...");
		}
		StringBuffer stb = new StringBuffer(qryCountAllPersonContactMethod.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountAllPersonContactMethod.getOptionalParameters();
		if (idContactMethod != 0) {
			stb.append(" ").append(params.get("idContactMethod"));
			parameters.add(idContactMethod);
		}
		if (idContactMethodCat != 0) {
			stb.append(" ").append(params.get("idContactMethodCat"));
			parameters.add(idContactMethodCat);
		}
		if (idPerson != 0) {
			stb.append(" ").append(params.get("idPerson"));
			parameters.add(idPerson);
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

		if (qryCountAllPersonContactMethod.getSqlLast() != null && !qryCountAllPersonContactMethod.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountAllPersonContactMethod.getSqlLast());
		}

		Object[] args = parameters.toArray();
		int countUserContactMethodDao = 0;
		
		try {
			countUserContactMethodDao = jdbcTemplate.queryForInt(stb.toString(), args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex, new ExcepcionGenerica("ERROR","PersonContactMethodDaoImpl","countPersonContactMethodDao","Ocurrio un error al hacer createCatalogRolDao "));
		}
		return countUserContactMethodDao;
	}

	@Override
	public int existPersonContactMethodDao(long idContactMethod) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existPersonContactMethodDao...");
		}
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idContactMethod);
		Object[] args = parameters.toArray();
		int existContactMethod = 0;
		try {
			existContactMethod = jdbcTemplate.queryForInt(existPersonContactMethod, args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex, new ExcepcionGenerica("ERROR","PersonContactMethodDaoImpl","existPersonContactMethodDao","Ocurrio un error al hacer createCatalogRolDao "));
		}
		return existContactMethod;
	}

	public void setQryCreatePersonContactMethod(String qryCreatePersonContactMethod) {
		this.qryCreatePersonContactMethod = qryCreatePersonContactMethod;
	}

	public void setNextIdPersonContactMethod(String nextIdPersonContactMethod) {
		this.nextIdPersonContactMethod = nextIdPersonContactMethod;
	}

	public void setExistPersonContactMethod(String existPersonContactMethod) {
		this.existPersonContactMethod = existPersonContactMethod;
	}

	public void setQryAllPersonContactMethod(JdbcQueryParameters qryAllPersonContactMethod) {
		this.qryAllPersonContactMethod = qryAllPersonContactMethod;
	}

	public void setQryCountAllPersonContactMethod(JdbcQueryParameters qryCountAllPersonContactMethod) {
		this.qryCountAllPersonContactMethod = qryCountAllPersonContactMethod;
	}

	public void setQryModifyPersonContactMethod(String qryModifyPersonContactMethod) {
		this.qryModifyPersonContactMethod = qryModifyPersonContactMethod;
	}

}
