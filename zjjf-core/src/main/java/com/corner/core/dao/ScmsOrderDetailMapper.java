package com.corner.core.dao;

import com.corner.core.beans.ScmsOrderDetail;

public interface ScmsOrderDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScmsOrderDetail record);

    int insertSelective(ScmsOrderDetail record);

    ScmsOrderDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScmsOrderDetail record);

    int updateByPrimaryKey(ScmsOrderDetail record);
}