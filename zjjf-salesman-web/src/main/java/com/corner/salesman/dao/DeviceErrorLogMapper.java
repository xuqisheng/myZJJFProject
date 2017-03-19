package com.corner.salesman.dao;

import com.corner.salesman.model.DeviceErrorLog;

import java.util.List;

public interface DeviceErrorLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(DeviceErrorLog record);

    int insertSelective(DeviceErrorLog record);

    DeviceErrorLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(DeviceErrorLog record);

    int updateByPrimaryKey(DeviceErrorLog record);
    
    List<DeviceErrorLog> getErrorLogPageList(DeviceErrorLog record);
    
    int getErrorLogPageSize(DeviceErrorLog record);
    
    List<DeviceErrorLog> findErrorLogList(DeviceErrorLog record);
}