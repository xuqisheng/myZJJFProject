package com.corner.salesman.dao;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.DeviceErrorLog;

@MyBatisDao
public interface DeviceErrorLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(DeviceErrorLog record);

    int insertSelective(DeviceErrorLog record);

    DeviceErrorLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(DeviceErrorLog record);

    int updateByPrimaryKey(DeviceErrorLog record);
}