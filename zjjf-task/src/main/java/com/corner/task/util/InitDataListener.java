package com.corner.task.util;

import com.corner.task.service.TaskService;
import com.corner.task.service.impl.TaskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

public class InitDataListener implements InitializingBean, ServletContextAware{  
	
	@Autowired
	TaskService userService;
	private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Override  
	public void afterPropertiesSet() throws Exception {  
		//在这个方法里面写 初始化的数据也可以。  

	}  
	@Override  
	public void setServletContext(ServletContext arg0) {  
		log.info("初始化任务开始---------------");
		try {
			userService.startAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("初始化任务结束---------------");
	}
}