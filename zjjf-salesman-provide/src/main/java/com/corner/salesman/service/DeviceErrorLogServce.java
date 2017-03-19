package com.corner.salesman.service;

import com.corner.salesman.model.AppMonitorLogs;
import com.corner.salesman.model.DeviceErrorLog;

/**
 * 设备错误日志业务层接口
 * @author Longx
 *
 */
public interface DeviceErrorLogServce {
	/**
	 * 添加设备错误日志
	 * @param errorLog
	 * @return
	 */
	public void addDeviceErrorLog(DeviceErrorLog errorLog) throws Exception;
	
	/**
	 * 添加APP运行日志
	 * @param errorLog
	 * @return
	 */
	public void addAppRuningLog(AppMonitorLogs appLogs) throws Exception;
}
