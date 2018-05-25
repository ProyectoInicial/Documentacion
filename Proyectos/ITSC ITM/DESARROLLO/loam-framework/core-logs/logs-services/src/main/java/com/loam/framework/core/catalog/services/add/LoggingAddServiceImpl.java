package com.loam.framework.core.catalog.services.add;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loam.framework.core.logs.persistence.add.LoggingAddDao;
import com.loam.framework.core.logs.persistence.add.LoggingAddVo;
import com.loam.framework.core.logs.persistence.common.CommonProperties;
import com.loam.framework.core.logs.persistence.common.CustomGenericException;

@Service
public class LoggingAddServiceImpl implements LoggingAddService {
	static Logger logger = Logger.getLogger(LoggingAddServiceImpl.class);

	static FileWriter archivo;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH");

	@Autowired
	protected LoggingAddDao loggingAddDao;

	@Autowired
	protected static CommonProperties commonProperties;

	@Override
	public int addLoggingService(LoggingAddVo loggingAddVo) throws Exception {
		int data = 0;
		try {
			data = loggingAddDao.addLoggingDao(loggingAddVo);
		} catch (Exception ex) {
			try {
				crearLog(loggingAddVo.toString());
			} catch (Exception e) {
				throw new CustomGenericException(ex.getMessage(), ex);
			}
		}
		return data;
	}

	public static void crearLog(String operacion) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			if (new File(commonProperties.getLogDir() + "\\log_" + sdf.format(timestamp) + ".log").exists() == false) {
				archivo = new FileWriter(
						new File(commonProperties.getLogDir() + "\\log_" + sdf.format(timestamp) + ".log"), false);
			}
			archivo = new FileWriter(new File(commonProperties.getLogDir() + "\\log_" + sdf.format(timestamp) + ".log"),
					true);
			Calendar fechaActual = Calendar.getInstance();

			archivo.write((String.valueOf(fechaActual.get(Calendar.DAY_OF_MONTH)) + "/"
					+ String.valueOf(fechaActual.get(Calendar.MONTH) + 1) + "/"
					+ String.valueOf(fechaActual.get(Calendar.YEAR)) + "-"
					+ String.valueOf(fechaActual.get(Calendar.HOUR_OF_DAY)) + ":"
					+ String.valueOf(fechaActual.get(Calendar.MINUTE)) + ":"
					+ String.valueOf(fechaActual.get(Calendar.SECOND))) + " :: " + operacion + "\r\n");
			archivo.close();
		} catch (IOException ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
