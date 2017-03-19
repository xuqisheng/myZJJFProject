package com.corner.core.dao;

import com.corner.core.beans.SKUActiveGoodInfo;

public interface SKUActiveGoodInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SKUActiveGoodInfo record);

    int insertSelective(SKUActiveGoodInfo record);

    SKUActiveGoodInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SKUActiveGoodInfo record);

    int updateByPrimaryKey(SKUActiveGoodInfo record);
}