package com.corner.core.dao;

import com.corner.core.beans.ERPPhysicsStockLog;

public interface ERPPhysicsStockLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(ERPPhysicsStockLog record);

    int insertSelective(ERPPhysicsStockLog record);

    ERPPhysicsStockLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ERPPhysicsStockLog record);

    int updateByPrimaryKey(ERPPhysicsStockLog record);
}