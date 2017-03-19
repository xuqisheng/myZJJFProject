package com.corner.core.dao;

import com.corner.core.beans.SupplierItem;

public interface SupplierItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(SupplierItem record);

    int insertSelective(SupplierItem record);

    SupplierItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SupplierItem record);

    int updateByPrimaryKey(SupplierItem record);
}