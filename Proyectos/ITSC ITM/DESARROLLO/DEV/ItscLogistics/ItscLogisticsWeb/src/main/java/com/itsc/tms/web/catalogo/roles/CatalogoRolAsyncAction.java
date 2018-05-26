package com.itsc.tms.web.catalogo.roles;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.itsc.tms.web.common.CommonAction;

@Results( { @Result(name = "input", location = "welcome.jsp"),  @Result(name = "success", location = "welcome.jsp") })
public class CatalogoRolAsyncAction extends CommonAction{
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(CatalogoRolAsyncAction.class);
}
