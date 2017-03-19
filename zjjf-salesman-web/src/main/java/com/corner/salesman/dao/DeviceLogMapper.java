package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.model.DeviceLog;

public interface DeviceLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(DeviceLog record);

    int insertSelective(DeviceLog record);

    DeviceLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(DeviceLog record);

    int updateByPrimaryKey(DeviceLog record);
    
	List<DeviceLog> getDeviceLogPageList(DeviceLog record);

	int getDeviceLogListSize(DeviceLog record);
}