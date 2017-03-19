package com.corner.core.dao;

import com.corner.core.beans.OrderInfo;
import com.corner.core.beans.OrderInfoWithBLOBs;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderInfoWithBLOBs record);

    int insertSelective(OrderInfoWithBLOBs record);

    OrderInfoWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(OrderInfoWithBLOBs record);

    int updateByPrimaryKey(OrderInfo record);
}