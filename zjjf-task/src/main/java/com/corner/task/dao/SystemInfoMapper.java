package com.corner.task.dao;

import com.corner.task.beans.SystemInfo;

public interface SystemInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemInfo record);

    int insertSelective(SystemInfo record);

    SystemInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemInfo record);

    int updateByPrimaryKey(SystemInfo record);
}