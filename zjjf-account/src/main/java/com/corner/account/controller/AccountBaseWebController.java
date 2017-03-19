package com.corner.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.account.config.SessionConfig;
import com.corner.core.beans.Accounter;
import com.corner.core.web.controller.CoreBaseWebController;


public class AccountBaseWebController extends CoreBaseWebController{

	private static Logger logger = LoggerFactory.getLogger(AccountBaseWebController.class);
	
	public Accounter getCurrentUser(HttpServletRequest request) {
		logger.debug("enter in getCurrentUser function");
		Object object = request.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
		if (object != null) {
			return (Accounter) object;
		}
		return null;
	}
	

}


