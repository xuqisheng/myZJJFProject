package com.corner.core.dao;

import com.corner.core.beans.SendOrderLog;

public interface SendOrderLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SendOrderLog record);

    int insertSelective(SendOrderLog record);

    SendOrderLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SendOrderLog record);

    int updateByPrimaryKey(SendOrderLog record);
}