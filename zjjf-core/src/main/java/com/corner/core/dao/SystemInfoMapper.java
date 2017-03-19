package com.corner.core.dao;

import com.corner.core.beans.SystemInfo;

public interface SystemInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemInfo record);

    int insertSelective(SystemInfo record);

    SystemInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemInfo record);

    int updateByPrimaryKey(SystemInfo record);
}