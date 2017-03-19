package com.corner.core.dao;

import com.corner.core.beans.ERPManagerSpWarehouseMap;

public interface ERPManagerSpWarehouseMapMapper {
    int deleteByPrimaryKey(ERPManagerSpWarehouseMap key);

    int insert(ERPManagerSpWarehouseMap record);

    int insertSelective(ERPManagerSpWarehouseMap record);
}