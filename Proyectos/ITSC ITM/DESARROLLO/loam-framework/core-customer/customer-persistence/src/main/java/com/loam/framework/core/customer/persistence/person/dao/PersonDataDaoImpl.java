package com.loam.framework.core.customer.persistence.person.dao;

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
import com.loam.framework.core.customer.jaxb.ws.customerdetails.DataPerson;
import com.loam.framework.core.customer.persistence.common.CustomGenericException;
import com.loam.framework.core.customer.persistence.common.JdbcDao;
import com.loam.framework.core.customer.persistence.common.JdbcQueryParameters;

public class PersonDataDaoImpl extends JdbcDao implements PersonDataDao {

	private String qryCreatePerson;
	private String qryModifyPerson;
	private String nextIdPerson;
	private String existPersonToken;
	private String existPersonId;
	private JdbcQueryParameters qryAllDataPerson;
	private JdbcQueryParameters qryCountAllDataPerson;

	private static ParameterizedRowMapper<DataPerson> MAPPER_DATA_PERSON = new ParameterizedRowMapper<DataPerson>() {
		public DataPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
			DataPerson obj = new DataPerson();
			obj.setIdPerson(BigInteger.valueOf(Long.valueOf(rs.getString("ID_PERSON"))));
			obj.setIdPerson(BigInteger.valueOf(Long.valueOf(rs.getString("ID_PERSON_CAT"))));
			obj.setTokenPerson(rs.getString("TOKEN_PERSON"));
			obj.setNamePerson(rs.getString("NAME_PERSON"));
			obj.setSurnamesPerson(rs.getString("SURNAMES_PERSON"));
			obj.setSexPerson(Integer.parseInt(rs.getString("SEX_PERSON")));
			obj.setBirthDatePerson(DateUtilsCommons.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("BIRTH_DATE_PERSON"))));
			obj.setAgePerson(Integer.parseInt(rs.getString("AGE_PERSON")));
			obj.setIdPerson(BigInteger.valueOf(Long.valueOf(rs.getString("ID_USER"))));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("START_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));

			return obj;
		}

	};

	@SuppressWarnings("unused")
	@Override
	public DataPerson createDataPersonDao(final DataPerson dataPerson) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("createDataPersonDao...");
		}

		try {
			final int nextPersonId = jdbcTemplate.queryForInt(nextIdPerson);

			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreatePerson);
					ps.setString(1, String.valueOf(nextPersonId));
					ps.setString(2, dataPerson.getTokenPerson());
					ps.setString(3, dataPerson.getNamePerson());
					ps.setString(4, dataPerson.getSurnamesPerson());
					ps.setInt(5, dataPerson.getSexPerson());
					ps.setTimestamp(6, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(dataPerson.getBirthDatePerson())));
					ps.setInt(7, dataPerson.getAgePerson());
					ps.setString(8, String.valueOf(dataPerson.getIdUser()));
					ps.setInt(9, dataPerson.getStatusFlag());
					ps.setTimestamp(10, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(dataPerson.getStartDt())));
					ps.setTimestamp(11, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(dataPerson.getLastUpdateDt())));
					ps.setString(12, dataPerson.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return dataPerson;
	}

	@SuppressWarnings("unused")
	@Override
	public DataPerson modifyDataPersonDao(final DataPerson dataPerson) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modifyDataPersonDao...");
		}

		try {

			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyPerson);
					ps.setString(1, dataPerson.getTokenPerson());
					ps.setString(2, dataPerson.getNamePerson());
					ps.setString(3, dataPerson.getSurnamesPerson());
					ps.setInt(4, dataPerson.getSexPerson());
					ps.setTimestamp(5, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(dataPerson.getBirthDatePerson())));
					ps.setInt(6, dataPerson.getAgePerson());
					ps.setString(7, String.valueOf(dataPerson.getIdUser()));
					ps.setInt(8, dataPerson.getStatusFlag());
					ps.setTimestamp(9, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(dataPerson.getStartDt())));
					ps.setTimestamp(10, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(dataPerson.getLastUpdateDt())));
					ps.setString(11, dataPerson.getLastUpdateUser());
					ps.setString(12, String.valueOf(dataPerson.getIdPerson()));
					
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return dataPerson;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<DataPerson> consultDataPersonDao(long idPerson, long idPersonCat, long tokenPerson, long idUser, int startReg, int endReg, String order) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultDataPersonDao...");
		}

		StringBuffer stb = new StringBuffer(qryAllDataPerson.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllDataPerson.getOptionalParameters();
		if (idPerson != 0) {
			stb.append(" ").append(params.get("idPerson"));
			parameters.add(idPerson);
		}
		if (idPersonCat != 0) {
			stb.append(" ").append(params.get("idPersonCat"));
			parameters.add(idPersonCat);
		}
		if (tokenPerson != 0) {
			stb.append(" ").append(params.get("tokenPerson"));
			parameters.add(tokenPerson);
		}
		if (idUser != 0) {
			stb.append(" ").append(params.get("idUser"));
			parameters.add(idUser);
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

		if (qryAllDataPerson.getSqlLast() != null && !qryAllDataPerson.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllDataPerson.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<DataPerson> consultDataPersonDao = null;

		try {
			consultDataPersonDao = (List<DataPerson>) jdbcTemplate.query(stb.toString(), args, MAPPER_DATA_PERSON);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return consultDataPersonDao;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countDataPersonDao(long idPerson, long idPersonCat, long tokenPerson, long idUser, int startReg,
			int endReg, String order) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("countDataPersonDao...");
		}

		StringBuffer stb = new StringBuffer(qryCountAllDataPerson.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountAllDataPerson.getOptionalParameters();
		if (idPerson != 0) {
			stb.append(" ").append(params.get("idPerson"));
			parameters.add(idPerson);
		}
		if (idPersonCat != 0) {
			stb.append(" ").append(params.get("idPersonCat"));
			parameters.add(idPersonCat);
		}
		if (tokenPerson != 0) {
			stb.append(" ").append(params.get("tokenPerson"));
			parameters.add(tokenPerson);
		}
		if (idUser != 0) {
			stb.append(" ").append(params.get("idUser"));
			parameters.add(idUser);
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

		if (qryCountAllDataPerson.getSqlLast() != null && !qryCountAllDataPerson.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountAllDataPerson.getSqlLast());
		}

		Object[] args = parameters.toArray();
		int countDataPersonDao = 0;

		try {
			countDataPersonDao = jdbcTemplate.queryForInt(stb.toString(), args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return countDataPersonDao;
	}

	@Override
	public int existPersonTokenDao(String personToken) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existPersonTokenDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(personToken);
		Object[] args = parameters.toArray();
		int existpersonToken = 0;

		try {
			existpersonToken = jdbcTemplate.queryForInt(existPersonToken, args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existpersonToken;
	}

	@Override
	public int existIdPersonDao(long idPerson) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existIdPersonDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idPerson);
		Object[] args = parameters.toArray();
		int existidPerson = 0;

		try {
			existidPerson = jdbcTemplate.queryForInt(existPersonId, args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existidPerson;
	}

	public void setQryCreatePerson(String qryCreatePerson) {
		this.qryCreatePerson = qryCreatePerson;
	}

	public void setNextIdPerson(String nextIdPerson) {
		this.nextIdPerson = nextIdPerson;
	}

	public void setExistPersonToken(String existPersonToken) {
		this.existPersonToken = existPersonToken;
	}

	public void setExistPersonId(String existPersonId) {
		this.existPersonId = existPersonId;
	}

	public void setQryAllDataPerson(JdbcQueryParameters qryAllDataPerson) {
		this.qryAllDataPerson = qryAllDataPerson;
	}

	public void setQryCountAllDataPerson(JdbcQueryParameters qryCountAllDataPerson) {
		this.qryCountAllDataPerson = qryCountAllDataPerson;
	}

	public void setQryModifyPerson(String qryModifyPerson) {
		this.qryModifyPerson = qryModifyPerson;
	}

}
