package com.corner.task.dao;

import com.corner.task.beans.SKUActiveGoodInfo;

public interface SKUActiveGoodInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SKUActiveGoodInfo record);

    int insertSelective(SKUActiveGoodInfo record);

    SKUActiveGoodInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SKUActiveGoodInfo record);

    int updateByPrimaryKey(SKUActiveGoodInfo record);
}