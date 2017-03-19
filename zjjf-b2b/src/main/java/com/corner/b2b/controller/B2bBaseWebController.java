package com.corner.b2b.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.core.web.controller.CoreBaseWebController;


public class B2bBaseWebController extends CoreBaseWebController{

	public static Logger logger = LoggerFactory.getLogger(B2bBaseWebController.class);
	
	public void B2bBaseWebControllerMethod(){
		logger.debug("这些是B2bBaseWebController的方法");
	}


}


