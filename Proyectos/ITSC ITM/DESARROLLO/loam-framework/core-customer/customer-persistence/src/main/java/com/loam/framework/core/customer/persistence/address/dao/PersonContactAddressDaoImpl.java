package com.loam.framework.core.customer.persistence.address.dao;

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
import com.loam.framework.core.customer.jaxb.ws.customerdetails.UserContactAddress;
import com.loam.framework.core.customer.persistence.common.CustomGenericException;
import com.loam.framework.core.customer.persistence.common.ExcepcionGenerica;
import com.loam.framework.core.customer.persistence.common.JdbcDao;
import com.loam.framework.core.customer.persistence.common.JdbcQueryParameters;

public class PersonContactAddressDaoImpl extends JdbcDao implements PersonContactAddressDao {

	private String qryCreatePersonContactAddress;
	private String qryModifyPersonContactAddress;
	private String nextIdPersonContactAddress;
	private String existPersonContactAddress;
	private JdbcQueryParameters qryAllPersonContactAddress;
	private JdbcQueryParameters qryCountAllPersonContactAddress;
	
	private static ParameterizedRowMapper<UserContactAddress> MAPPER_PER_CONT_ADDRESS = new ParameterizedRowMapper<UserContactAddress>() {
		public UserContactAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserContactAddress obj = new UserContactAddress();
			obj.setIdContactAddress(BigInteger.valueOf(Long.valueOf(rs.getString("ID_CONTACT_ADDRESS"))));
			obj.setIdPerson(BigInteger.valueOf(Long.valueOf(rs.getString("ID_PERSON"))));
			obj.setIdAddressCat(BigInteger.valueOf(Long.valueOf(rs.getString("ID_ADDRESS_CAT"))));
			obj.setIdCityCat(BigInteger.valueOf(Long.valueOf(rs.getString("ID_CITY_CAT"))));
			obj.setPostalCode(BigInteger.valueOf(Long.valueOf(rs.getString("POSTAL_CODE"))));
			obj.setDescAddress(rs.getString("DESC_ADDRESS"));
			obj.setColonia(rs.getString("COLONIA"));
			obj.setPrincipalFlag(Integer.parseInt(rs.getString("PRINCIPAL_FLAG")));
			obj.setStatusFlag(Integer.parseInt(rs.getString("STATUS_FLAG")));
			obj.setStartDt(DateUtilsCommons.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("START_DT"))));
			obj.setLastUpdateDt(DateUtilsCommons.toXMLGregorianCalendar(DateUtilsCommons.convertJavaStringToDate(rs.getString("LAST_UPDATE_DT"))));
			obj.setLastUpdateUser(rs.getString("LAST_UPDATE_USER"));
			return obj;
		}
	};
	
	@SuppressWarnings("unused")
	@Override
	public UserContactAddress createPersonContactAddressDao(final UserContactAddress userContactAddress) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("createPersonContactAddressDao...");
		}
		try {
			final int idPersonContactAddress = jdbcTemplate.queryForInt(nextIdPersonContactAddress);

			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryCreatePersonContactAddress);
					ps.setString(1, String.valueOf(idPersonContactAddress));
					ps.setString(2, String.valueOf(userContactAddress.getIdPerson()));
					ps.setString(3, String.valueOf(userContactAddress.getIdAddressCat()));
					ps.setString(4, String.valueOf(userContactAddress.getIdCityCat()));
					ps.setString(5, String.valueOf(userContactAddress.getPostalCode()));
					ps.setString(6, String.valueOf(userContactAddress.getDescAddress()));
					ps.setString(7, userContactAddress.getColonia());
					ps.setInt(8, userContactAddress.getPrincipalFlag());
					ps.setInt(9, userContactAddress.getStatusFlag());
					ps.setTimestamp(10, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(userContactAddress.getStartDt())));
					ps.setTimestamp(11, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(userContactAddress.getLastUpdateDt())));
					ps.setString(12, userContactAddress.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex, new ExcepcionGenerica("ERROR","PersonContactAddressDaoImpl","createPersonContactAddressDao","Ocurrio un error al hacer createCatalogRolDao "));
		}
		return null;
	}

	@SuppressWarnings("unused")
	@Override
	public UserContactAddress modifyPersonContactAddressDao(final UserContactAddress userContactAddress) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("modifyPersonContactAddressDao...");
		}
		try {
			int affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryModifyPersonContactAddress);
					
					ps.setString(1, String.valueOf(userContactAddress.getIdPerson()));
					ps.setString(2, String.valueOf(userContactAddress.getIdAddressCat()));
					ps.setString(3, String.valueOf(userContactAddress.getIdCityCat()));
					ps.setString(4, String.valueOf(userContactAddress.getPostalCode()));
					ps.setString(5, String.valueOf(userContactAddress.getDescAddress()));
					ps.setString(6, userContactAddress.getColonia());
					ps.setInt(7, userContactAddress.getPrincipalFlag());
					ps.setInt(8, userContactAddress.getStatusFlag());
					ps.setTimestamp(9, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(userContactAddress.getStartDt())));
					ps.setTimestamp(10, DateUtilsCommons.convertJavaDateToSqlDate(DateUtilsCommons.toDate(userContactAddress.getLastUpdateDt())));
					ps.setString(11, userContactAddress.getLastUpdateUser());
					ps.setString(12, String.valueOf(userContactAddress.getIdContactAddress()));
					
					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex, new ExcepcionGenerica("ERROR","PersonContactAddressDaoImpl","modifyPersonContactAddressDao","Ocurrio un error al hacer createCatalogRolDao "));
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<UserContactAddress> consultPersonContactAddressDao(long IdContactAddress, long idPerson, int startReg,
			int endReg, String order) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("consultPersonContactAddressDao...");
		}
		StringBuffer stb = new StringBuffer(qryAllPersonContactAddress.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryAllPersonContactAddress.getOptionalParameters();
		if (IdContactAddress != 0) {
			stb.append(" ").append(params.get("idContactAddress"));
			parameters.add(IdContactAddress);
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

		if (qryAllPersonContactAddress.getSqlLast() != null && !qryAllPersonContactAddress.getSqlLast().equals("")) {
			stb.append(" ").append(qryAllPersonContactAddress.getSqlLast());
		}

		Object[] args = parameters.toArray();
		List<UserContactAddress> consultUserContactAddressDao = null;
		try {
			consultUserContactAddressDao = (List<UserContactAddress>) jdbcTemplate.query(stb.toString(), args, MAPPER_PER_CONT_ADDRESS);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex, new ExcepcionGenerica("ERROR","PersonContactAddressDaoImpl","consultPersonContactAddressDao","Ocurrio un error al hacer createCatalogRolDao "));
		}
		return consultUserContactAddressDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int countPersonContactAddressDao(long idContactAddress, long idPerson, int startReg, int endReg,
			String order) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("countPersonContactAddressDao...");
		}
		StringBuffer stb = new StringBuffer(qryCountAllPersonContactAddress.getSqlBase());
		ArrayList parameters = new ArrayList();
		Properties params = qryCountAllPersonContactAddress.getOptionalParameters();
		if (idContactAddress != 0) {
			stb.append(" ").append(params.get("idContactAddress"));
			parameters.add(idContactAddress);
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

		if (qryCountAllPersonContactAddress.getSqlLast() != null && !qryCountAllPersonContactAddress.getSqlLast().equals("")) {
			stb.append(" ").append(qryCountAllPersonContactAddress.getSqlLast());
		}

		Object[] args = parameters.toArray();
		int countUserContactAddressDao = 0;
		try {
			countUserContactAddressDao = jdbcTemplate.queryForInt(stb.toString(), args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex, new ExcepcionGenerica("ERROR","PersonContactAddressDaoImpl","countPersonContactAddressDao","Ocurrio un error al hacer createCatalogRolDao "));
		}
		return countUserContactAddressDao;
	}

	@Override
	public int existPersonContactAddressDao(long idContactAddress) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("existPersonContactAddressDao...");
		}
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(idContactAddress);
		Object[] args = parameters.toArray();
		int existContactAddress = 0;
		try {
			existContactAddress = jdbcTemplate.queryForInt(existPersonContactAddress, args);
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex, new ExcepcionGenerica("ERROR","PersonContactAddressDaoImpl","existPersonContactAddressDao","Ocurrio un error al hacer createCatalogRolDao "));
		}
		return existContactAddress;
	}

	public void setQryCreatePersonContactAddress(String qryCreatePersonContactAddress) {
		this.qryCreatePersonContactAddress = qryCreatePersonContactAddress;
	}

	public void setQryModifyPersonContactAddress(String qryModifyPersonContactAddress) {
		this.qryModifyPersonContactAddress = qryModifyPersonContactAddress;
	}

	public void setNextIdPersonContactAddress(String nextIdPersonContactAddress) {
		this.nextIdPersonContactAddress = nextIdPersonContactAddress;
	}

	public void setExistPersonContactAddress(String existPersonContactAddress) {
		this.existPersonContactAddress = existPersonContactAddress;
	}

	public void setQryAllPersonContactAddress(JdbcQueryParameters qryAllPersonContactAddress) {
		this.qryAllPersonContactAddress = qryAllPersonContactAddress;
	}

	public void setQryCountAllPersonContactAddress(JdbcQueryParameters qryCountAllPersonContactAddress) {
		this.qryCountAllPersonContactAddress = qryCountAllPersonContactAddress;
	}

}
