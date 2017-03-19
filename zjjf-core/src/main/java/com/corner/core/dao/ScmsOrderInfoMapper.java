package com.corner.core.dao;

import com.corner.core.beans.ScmsOrderInfo;

public interface ScmsOrderInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScmsOrderInfo record);

    int insertSelective(ScmsOrderInfo record);

    ScmsOrderInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScmsOrderInfo record);

    int updateByPrimaryKey(ScmsOrderInfo record);
}