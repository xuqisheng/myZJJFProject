package com.corner.core.dao;

import com.corner.core.beans.ScOrderDetail;

public interface ScOrderDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScOrderDetail record);

    int insertSelective(ScOrderDetail record);

    ScOrderDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScOrderDetail record);

    int updateByPrimaryKey(ScOrderDetail record);
}