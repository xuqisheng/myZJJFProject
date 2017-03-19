package com.corner.core.dao;

import com.corner.core.beans.StoreItem;

public interface StoreItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(StoreItem record);

    int insertSelective(StoreItem record);

    StoreItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StoreItem record);

    int updateByPrimaryKey(StoreItem record);
}