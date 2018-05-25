package com.loam.framework.core.security.persistence.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcDao {


    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    protected JdbcTemplate jdbcTemplate;
    protected static Calendar aCalendar = Calendar.getInstance();
    protected static final Logger log = Logger.getLogger(JdbcDao.class);

    protected static SimpleDateFormat SDF_DDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
    protected static SimpleDateFormat SDF_DDMMYYYY_HHMMSS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    static {
        SDF_DDMMYYYY.setLenient(false);
        SDF_DDMMYYYY_HHMMSS.setLenient(false);
    }

    public final void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected void checkAffected(int affected) {
        if (affected > 1) {
            throw new RuntimeException("La operacion afecto mas de 1 registro.");
        }
        if (affected < 1) {
            throw new RuntimeException("La operacion no afecto ningun registro.");
        }
    }

}
