package com.corner.salesman.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.utils.DateUtils;
import com.corner.salesman.dao.AppMonitorLogsMapper;
import com.corner.salesman.dao.DeviceErrorLogMapper;
import com.corner.salesman.model.AppMonitorLogs;
import com.corner.salesman.model.DeviceErrorLog;
import com.corner.salesman.service.DeviceErrorLogServce;
/**
 * 设备错误日志业务实现层
 * @author Longx
 *
 */
@Service
@Transactional(readOnly = true)
public class DeviceErrorLogServceImpl implements DeviceErrorLogServce {

	@Autowired
	private DeviceErrorLogMapper errorLogMapper;
	@Autowired
	private AppMonitorLogsMapper appMonitorMapper;
	
	/**
	 * 添加日志错误记录
	 */
	@Transactional(readOnly = false)
	@Override
	public void addDeviceErrorLog(DeviceErrorLog errorLog) throws Exception {
		String errorTime = errorLog.getErrorTime();
		errorTime = DateUtils.timestampToDate(errorTime);
		errorLog.setErrorTime(errorTime);
		errorLog.setCreateBy(errorLog.getUserId());
		errorLog.setCreateTime(DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT));
		errorLogMapper.insertSelective(errorLog);
	}
	
	/**
	 * 添加APP运行日志
	 * @param errorLog
	 * @return
	 */
	@Transactional(readOnly = false)
	@Override
	public void addAppRuningLog(AppMonitorLogs appLogs) throws Exception{
		String appTime = appLogs.getAppTime();
		appLogs.setCreateBy(appLogs.getUserId());
		appLogs.setCreateTime(DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT));
		if(StringUtils.isNotBlank(appTime)){
			appLogs.setAppTime(DateUtils.timestampToDate(appTime));
		}
		appMonitorMapper.insertSelective(appLogs);
	}

}
