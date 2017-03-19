package com.corner.core.dao;

import com.corner.core.beans.SystemVersion;

public interface SystemVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemVersion record);

    int insertSelective(SystemVersion record);

    SystemVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemVersion record);

    int updateByPrimaryKey(SystemVersion record);
}