package com.corner.salesman.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import com.alibaba.fastjson.JSON;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.model.AppMonitorLogs;
import com.corner.salesman.model.DeviceErrorLog;
import com.corner.salesman.service.DeviceErrorLogServce;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping("/mobile/log")
public class DeviceErrorLogController {
	private static final Logger logger = LoggerFactory.getLogger(DeviceErrorLogController.class);

	@Autowired
	private DeviceErrorLogServce appLogServce;
	
	/**
	 * 添加手机错误日志记录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addErrorLog"})
	public Object addDeviceErrorLog(DeviceErrorLog errorLog, HttpServletRequest request){
		Json json = new Json();
		try {
			 //logger.info("添加手机错误日志记录信息为：{}", JSON.toJSON(errorLog));
			 appLogServce.addDeviceErrorLog(errorLog);
			 json.setMsg("添加手机错误日志成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			logger.info("添加手机错误日志异常：{}",e.getMessage());
			json.setMsg("添加手机错误日志异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		return json;
	}
	
	
	/**
	 * 添加APP运行日志
	 * @param errorLog
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addAppRuningLog"})
	public Object addAppRuningLog(AppMonitorLogs appLogs, HttpServletRequest request){
		Json json = new Json();
		try {
			 //logger.info("添加手机错误日志记录信息为：{}", JSON.toJSON(errorLog));
			 appLogServce.addAppRuningLog(appLogs);
			 json.setMsg("添加APP运行日志成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			logger.info("添加APP运行日志异常：{}",e.getMessage());
			json.setMsg("添加APP运行日志异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		return json;
	}
	
}
