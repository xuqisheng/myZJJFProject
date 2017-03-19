package com.corner.task.dao;

import com.corner.task.beans.SKUActiveGoodInfoLog;

public interface SKUActiveGoodInfoLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SKUActiveGoodInfoLog record);

    int insertSelective(SKUActiveGoodInfoLog record);

    SKUActiveGoodInfoLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SKUActiveGoodInfoLog record);

    int updateByPrimaryKey(SKUActiveGoodInfoLog record);
}