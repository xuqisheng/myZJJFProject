package com.corner.core.dao;

import com.corner.core.beans.ERPPhysicsStock;

public interface ERPPhysicsStockMapper {
    int deleteByPrimaryKey(String id);

    int insert(ERPPhysicsStock record);

    int insertSelective(ERPPhysicsStock record);

    ERPPhysicsStock selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ERPPhysicsStock record);

    int updateByPrimaryKey(ERPPhysicsStock record);
}