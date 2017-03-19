package com.corner.b2c.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.core.web.controller.CoreBaseWebController;


public class B2cBaseWebController extends CoreBaseWebController{

	public static Logger logger = LoggerFactory.getLogger(B2cBaseWebController.class);
	
	public void B2cBaseWebControllerMethod(){
		logger.debug("这些是B2cBaseWebController的方法");
	}


}


