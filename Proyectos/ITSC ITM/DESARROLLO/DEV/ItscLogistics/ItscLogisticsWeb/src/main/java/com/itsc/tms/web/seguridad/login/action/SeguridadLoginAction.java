package com.itsc.tms.web.seguridad.login.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.itsc.tms.web.common.CommonAction;

@Results({ @Result(name = "input", location = "jsp/seguridad/login/login.jsp"), @Result(name = "success", location = "welcome.jsp") })
public class SeguridadLoginAction extends CommonAction {

	private static final long serialVersionUID = 367718265243853120L;

	private static final Logger log = Logger.getLogger(SeguridadLoginAction.class);

	private String username;
	private String password;
	private String user="hola";

	public String input() throws Exception {
		if (log.isInfoEnabled()) {
			log.info("input UsuariosAction...");
		}
		String input = null;
		input = INPUT;
		/*if(user == null){
			input = INPUT;
		 }else{
			 input = SUCCESS;
		 }*/
		return input;
	}

	public String execute() throws Exception {

		if (log.isInfoEnabled()) {
			log.info("execute...");
		}

		boolean ldapValid = true;
		String result = INPUT;

		log.info("username: " + username + " password: " + password);
		if (ldapValid) {
			result = SUCCESS;
		}
		return result;
	}

}
