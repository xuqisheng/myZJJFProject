package com.corner.core.dao;

import com.corner.core.beans.ConfigShare;

public interface ConfigShareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConfigShare record);

    int insertSelective(ConfigShare record);

    ConfigShare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConfigShare record);

    int updateByPrimaryKey(ConfigShare record);
}