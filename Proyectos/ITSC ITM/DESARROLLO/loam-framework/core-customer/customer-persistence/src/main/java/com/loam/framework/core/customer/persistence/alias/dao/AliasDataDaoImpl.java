package com.loam.framework.core.customer.persistence.alias.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.loam.framework.core.customer.commons.utils.date.DateUtilsCommons;
import com.loam.framework.core.customer.jaxb.ws.customerdetails.UserAlias;
import com.loam.framework.core.customer.persistence.common.CustomGenericException;
import com.loam.framework.core.customer.persistence.common.JdbcDao;
import com.loam.framework.core.customer.persistence.common.JdbcQueryParameters;

public class AliasDataDaoImpl extends JdbcDao implements AliasDataDao {

	private String qryCreateAlias;
	private String qryModifyAlias;
	private String nextIdAlias;
	private String existAliasId;
	private String existAliasToken;
	private String existAliasEmail;
	private JdbcQueryParameters qryAllConsultAlias;
	private JdbcQueryParameters qryCountAllConsultAlias;

	private static ParameterizedRowMapper<UserAlias> MAPPER_ALIAS_DATA = new ParameterizedRowMapper<UserAlias>() {
		public UserAlias mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserAlias obj = new UserAlias();
			
			obj.setIdUserAlias(BigInteger.valueOf(Long.valueOf(rs.getString("ID_USER_ALIAS"))));
			obj.setTokenUser(rs.getString("TOKEN_USER"));
			obj.setAliasUser(rs.getString("ALIAS_USER"));
			obj.setEmailUser(rs.getString("EMAIL_USER"));
			obj.setPasswordUser(rs.getString("PASS_USER"));
			obj.setIdRolCat(BigInteger.valueOf(Long.valueOf(rs.getString("ID_ROL_CAT"))));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("START_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons.toXMLGregorianCalendar(Date.valueOf(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));

			return obj;
		}

	};

	@SuppressWarnings("unused")
	@Override
	public UserAlias createUserAliasDao(final UserAlias dataUserAlias) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("createDatosAliasDao...");
		}

		try {
			final int idNextAlias = jdbcTemplate.queryForInt(nextIdAlias);

			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreateAlias);
					ps.setInt(1, idNextAlias);
					ps.setString(2, dataUserAlias.getTokenUser());
					ps.setString(3, dataUserAlias.getAliasUser());
					ps.setString(4, dataUserAlias.getEmailUser());
					ps.setString(5, dataUserAlias.getPasswordUser());
					ps.setString(6, String.valueOf(dataUserAlias.getIdRolCat()));
					ps.setInt(7, dataUserAlias.getStatusFlag());
					ps.setTimestamp(8, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(dataUserAlias.getStartDt())));
					ps.setTimestamp(9, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(dataUserAlias.getLastUpdateDt())));
					ps.setString(10, dataUserAlias.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return dataUserAlias;
	}

	@SuppressWarnings("unused")
	@Override
	public UserAlias modifyUserAliasDao(final UserAlias dataUserAlias) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("createDatosAliasDao...");
		}

		try {

			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyAlias);
					
					ps.setString(1, dataUserAlias.getTokenUser());
					ps.setString(2, dataUserAlias.getAliasUser());
					ps.setString(3, dataUserAlias.getEmailUser());
					ps.setString(4, dataUserAlias.getPasswordUser());
					ps.setString(5, String.valueOf(dataUserAlias.getIdRolCat()));
					ps.setInt(6, dataUserAlias.getStatusFlag());
					ps.setTimestamp(7, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(dataUserAlias.getStartDt())));
					ps.setTimestamp(8, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(dataUserAlias.getLastUpdateDt())));
					ps.setString(9, dataUserAlias.getLastUpdateUser());
					ps.setString(10, String.valueOf(dataUserAlias.getIdUserAlias()));
					
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return dataUserAlias;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<UserAlias> consultUserAliasDao(String userToken, String userAlias, String userEmail, int startReg, int endReg, String order)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultDatosAliasDao...");
		}

		StringBuffer stb = new StringBuffer(qryAllConsultAlias.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllConsultAlias.getOptionalParameters();
		
		if (userToken != null && !userToken.equals("")) {
			stb.append(" ").append(params.get("tokenUser"));
			parameters.add(userToken);
		}
		
		if (userAlias != null && !userAlias.equals("")) {
			stb.append(" ").append(params.get("aliasUser"));
			parameters.add(userAlias);
		}
		
		if (userEmail != null && !userEmail.equals("")) {
			stb.append(" ").append(params.get("emailUser"));
			parameters.add(userEmail);
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

		if (qryAllConsultAlias.getSqlLast() != null && !qryAllConsultAlias.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllConsultAlias.getSqlLast());
		}

		Object[] args = parameters.toArray();
		
		List<UserAlias> consultDatosAliasVo = null;

		try {
			consultDatosAliasVo = (List<UserAlias>) jdbcTemplate.query(stb.toString(), args, MAPPER_ALIAS_DATA);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return consultDatosAliasVo;
	}

	@Override
	public int existAliasTokenDao(String userToken) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existAliasTokenDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(userToken);
		Object[] args = parameters.toArray();
		int existuserToken = 0;

		try {
			existuserToken = jdbcTemplate.queryForInt(existAliasId, args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existuserToken;
	}

	@Override
	public int existAliasDao(String userAlias) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existAliasDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(userAlias);
		Object[] args = parameters.toArray();
		int existuserAlias = 0;

		try {
			existuserAlias = jdbcTemplate.queryForInt(existAliasToken, args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existuserAlias;
	}

	@Override
	public int existAliasEmailDao(String userEmail) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existAliasEmailDao...");
		}

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(userEmail);
		Object[] args = parameters.toArray();
		int existuserEmail = 0;

		try {
			existuserEmail = jdbcTemplate.queryForInt(existAliasEmail, args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return existuserEmail;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countDataAliasDao(String userToken, String userAlias,
			String userEmail, int startReg, int endReg, String order) throws Exception {
		StringBuffer stb = new StringBuffer(qryCountAllConsultAlias.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountAllConsultAlias.getOptionalParameters();
		
		if (userToken != null && !userToken.equals("")) {
			stb.append(" ").append(params.get("tokenUser"));
			parameters.add(userToken);
		}
		
		if (userAlias != null && !userAlias.equals("")) {
			stb.append(" ").append(params.get("aliasUser"));
			parameters.add(userAlias);
		}
		
		if (userEmail != null && !userEmail.equals("")) {
			stb.append(" ").append(params.get("emailUser"));
			parameters.add(userEmail);
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

		if (qryCountAllConsultAlias.getSqlLast() != null && !qryCountAllConsultAlias.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountAllConsultAlias.getSqlLast());
		}

		Object[] args = parameters.toArray();
		
		int countDataAliasDao = 0;
		
		try {
			countDataAliasDao = jdbcTemplate.queryForInt(stb.toString(), args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return countDataAliasDao;
	}
	
	public void setQryCreateAlias(String qryCreateAlias) {
		this.qryCreateAlias = qryCreateAlias;
	}

	public void setNextIdAlias(String nextIdAlias) {
		this.nextIdAlias = nextIdAlias;
	}

	public void setExistAliasId(String existAliasId) {
		this.existAliasId = existAliasId;
	}

	public void setExistAliasToken(String existAliasToken) {
		this.existAliasToken = existAliasToken;
	}

	public void setExistAliasEmail(String existAliasEmail) {
		this.existAliasEmail = existAliasEmail;
	}

	public void setQryAllConsultAlias(JdbcQueryParameters qryAllConsultAlias) {
		this.qryAllConsultAlias = qryAllConsultAlias;
	}

	public void setQryCountAllConsultAlias(
			JdbcQueryParameters qryCountAllConsultAlias) {
		this.qryCountAllConsultAlias = qryCountAllConsultAlias;
	}

	public void setQryModifyAlias(String qryModifyAlias) {
		this.qryModifyAlias = qryModifyAlias;
	}

}
