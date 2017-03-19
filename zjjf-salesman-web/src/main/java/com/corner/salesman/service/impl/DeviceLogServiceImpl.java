package com.corner.salesman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.salesman.dao.DeviceLogMapper;
import com.corner.salesman.model.DeviceLog;
import com.corner.salesman.service.DeviceLogService;

@Service
public class DeviceLogServiceImpl implements DeviceLogService {

	@Autowired
	private DeviceLogMapper deviceLogMapper;
	
	/**
	 * 查询设备列表信息分页方法
	 * @param salesman
	 * @return
	 * @throws Exception
	 */
	public Pager<DeviceLog> getDevicePageList(DeviceLog deviceLog) throws Exception{
		List<DeviceLog> list = deviceLogMapper.getDeviceLogPageList(deviceLog);
		int size = deviceLogMapper.getDeviceLogListSize(deviceLog);
		return new Pager<DeviceLog>(size, list);
	}
	
}
