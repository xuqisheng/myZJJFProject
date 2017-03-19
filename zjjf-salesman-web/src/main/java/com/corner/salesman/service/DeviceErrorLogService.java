package com.corner.salesman.service;
import java.util.List;

import com.corner.core.beans.vo.Pager;
import com.corner.salesman.model.AppMonitorLogs;
import com.corner.salesman.model.DeviceErrorLog;

/**
 * 设备错误日志业务层接口
 * @author Longx
 *
 */
public interface DeviceErrorLogService {
	
	/**
	 * 查询设备错误信息列表（分页方法）
	 * @param deviceLog
	 * @return
	 * @throws Exception
	 */
	public Pager<DeviceErrorLog> getErrorLogPageList(DeviceErrorLog errorLog) throws Exception;
	
	/**
	 * 查询日志记录信息
	 * @param deviceLog
	 * @return
	 * @throws Exception
	 */
	public List<DeviceErrorLog> findErrorLogList(DeviceErrorLog record) throws Exception;
	
	/**
	 * 查询app运行日志信息列表（分页方法）
	 * @param appLogs
	 * @return
	 * @throws Exception
	 */
	public Pager<AppMonitorLogs> getAppLogPageList(AppMonitorLogs appLogs) throws Exception;
	
	/**
	 * 查询导出的app日志记录信息
	 * @param appLogs
	 * @return
	 * @throws Exception
	 */
	public List<AppMonitorLogs> findAppLogList(AppMonitorLogs appLogs) throws Exception;
}