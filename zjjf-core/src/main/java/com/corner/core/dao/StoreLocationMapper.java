package com.corner.core.dao;

import com.corner.core.beans.StoreLocation;

public interface StoreLocationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StoreLocation record);

    int insertSelective(StoreLocation record);

    StoreLocation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StoreLocation record);

    int updateByPrimaryKey(StoreLocation record);
}