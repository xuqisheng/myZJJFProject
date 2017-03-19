package com.corner.core.dao;

import com.corner.core.beans.ScmsWarehouse;

public interface ScmsWarehouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScmsWarehouse record);

    int insertSelective(ScmsWarehouse record);

    ScmsWarehouse selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScmsWarehouse record);

    int updateByPrimaryKey(ScmsWarehouse record);
}