package com.loam.framework.core.catalog.services.add;

import com.loam.framework.core.logs.persistence.add.LoggingAddVo;

public interface LoggingAddService {
	int addLoggingService(LoggingAddVo loggingAddVo) throws Exception;
}
