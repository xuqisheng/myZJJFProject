package com.corner.core.dao;

import com.corner.core.beans.ERPWarehouseUserMapKey;

public interface ERPWarehouseUserMapMapper {
    int deleteByPrimaryKey(ERPWarehouseUserMapKey key);

    int insert(ERPWarehouseUserMapKey record);

    int insertSelective(ERPWarehouseUserMapKey record);
}