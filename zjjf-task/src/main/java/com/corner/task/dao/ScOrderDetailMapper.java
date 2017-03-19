package com.corner.task.dao;

import com.corner.task.beans.ScOrderDetail;

public interface ScOrderDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScOrderDetail record);

    int insertSelective(ScOrderDetail record);

    ScOrderDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScOrderDetail record);

    int updateByPrimaryKey(ScOrderDetail record);
}