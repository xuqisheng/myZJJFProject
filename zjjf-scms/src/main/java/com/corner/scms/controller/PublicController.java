package com.corner.scms.controller;

import com.corner.scms.service.PublicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/scms/public")
public class PublicController extends ScmsBaseWebController {
	private static Logger logger = LoggerFactory.getLogger(PublicController.class);
}
