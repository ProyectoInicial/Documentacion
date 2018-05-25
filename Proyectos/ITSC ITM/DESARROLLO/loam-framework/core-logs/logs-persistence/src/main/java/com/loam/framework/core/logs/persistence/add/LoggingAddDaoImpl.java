package com.loam.framework.core.logs.persistence.add;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.loam.framework.core.logs.persistence.common.CustomGenericException;
import com.loam.framework.core.logs.persistence.common.JdbcDao;

public class LoggingAddDaoImpl extends JdbcDao implements LoggingAddDao {
	private static final Logger log = Logger.getLogger(LoggingAddDaoImpl.class);

	private String qryLogging;
	private String nextIdLogging;

	@Override
	public int addLoggingDao(final LoggingAddVo loggingAddVo) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("addLoggingDao...");
		}
		int affected = 0;
		try {
			final int nextIdMon = jdbcTemplate.queryForInt(nextIdLogging);

			affected = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(qryLogging);
					ps.setString(1, String.valueOf(nextIdMon));
					ps.setTimestamp(2, loggingAddVo.getFecTstamp());
					ps.setString(3, loggingAddVo.getCodMensaje());
					ps.setString(4, loggingAddVo.getMensaje());
					ps.setString(5, loggingAddVo.getPaquete());
					ps.setString(6, loggingAddVo.getMetodo());
					ps.setString(7, loggingAddVo.getIdSesion());
					ps.setString(8, loggingAddVo.getOperacion());
					ps.setTimestamp(9, loggingAddVo.getStartDt());
					ps.setTimestamp(10, loggingAddVo.getLastUpdateDt());
					ps.setString(11, loggingAddVo.getLastUpdateUser());

					return ps;
				}
			});
		} catch (Exception ex) {
			throw new CustomGenericException(ex.getMessage(), ex);
		}
		return affected;
	}

	public void setQryLogging(String qryLogging) {
		this.qryLogging = qryLogging;
	}

	public void setNextIdLogging(String nextIdLogging) {
		this.nextIdLogging = nextIdLogging;
	}

}
