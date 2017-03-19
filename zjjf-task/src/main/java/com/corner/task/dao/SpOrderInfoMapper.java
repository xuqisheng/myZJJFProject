package com.corner.task.dao;

import com.corner.task.beans.SpOrderInfo;

public interface SpOrderInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpOrderInfo record);

    int insertSelective(SpOrderInfo record);

    SpOrderInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpOrderInfo record);

    int updateByPrimaryKey(SpOrderInfo record);
}