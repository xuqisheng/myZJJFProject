package com.corner.salesman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.salesman.dao.AppMonitorLogsMapper;
import com.corner.salesman.dao.DeviceErrorLogMapper;
import com.corner.salesman.model.AppMonitorLogs;
import com.corner.salesman.model.DeviceErrorLog;
import com.corner.salesman.service.DeviceErrorLogService;

@Service
public class DeviceErrorLogServiceImpl implements DeviceErrorLogService {

	@Autowired
	private DeviceErrorLogMapper errorLogMapper;
	
	@Autowired
	private AppMonitorLogsMapper appLogsMapper;
	
	/**
	 * 查询设备错误日志信息列表（分页方法）
	 * @param deviceLog
	 * @return
	 * @throws Exception
	 */
	@Override
	public Pager<DeviceErrorLog> getErrorLogPageList(DeviceErrorLog deviceLog) throws Exception{
		List<DeviceErrorLog> list = errorLogMapper.getErrorLogPageList(deviceLog);
		int size = errorLogMapper.getErrorLogPageSize(deviceLog);
		return new Pager<DeviceErrorLog>(size, list);
	}
	
	/**
	 * 查询日志记录信息
	 * @param deviceLog
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DeviceErrorLog> findErrorLogList(DeviceErrorLog deviceLog) throws Exception{
		return errorLogMapper.findErrorLogList(deviceLog);
	}
	
	/**
	 * 查询app运行日志信息列表（分页方法）
	 * @param appLogs
	 * @return
	 * @throws Exception
	 */
	@Override
	public Pager<AppMonitorLogs> getAppLogPageList(AppMonitorLogs appLogs) throws Exception{
		List<AppMonitorLogs> list = appLogsMapper.getAppLogPageList(appLogs);
		int size = appLogsMapper.getAppLogPageSize(appLogs);
		return new Pager<AppMonitorLogs>(size, list);
	}
	
	/**
	 * 查询导出的app日志记录信息
	 * @param appLogs
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AppMonitorLogs> findAppLogList(AppMonitorLogs appLogs) throws Exception{
		return appLogsMapper.findAppLogList(appLogs);
	}

}
