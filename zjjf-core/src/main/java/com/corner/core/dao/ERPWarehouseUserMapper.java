package com.corner.core.dao;

import com.corner.core.beans.ERPWarehouseUser;

public interface ERPWarehouseUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(ERPWarehouseUser record);

    int insertSelective(ERPWarehouseUser record);

    ERPWarehouseUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ERPWarehouseUser record);

    int updateByPrimaryKey(ERPWarehouseUser record);
}