package com.corner.core.dao;

import com.corner.core.beans.DBAppConfig;

public interface DBAppConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DBAppConfig record);

    int insertSelective(DBAppConfig record);

    DBAppConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DBAppConfig record);

    int updateByPrimaryKey(DBAppConfig record);
}