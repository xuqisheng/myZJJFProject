package com.corner.core.dao;

import com.corner.core.beans.StoreManager;

public interface StoreManagerMapper {
    int deleteByPrimaryKey(String id);

    int insert(StoreManager record);

    int insertSelective(StoreManager record);

    StoreManager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StoreManager record);

    int updateByPrimaryKey(StoreManager record);
}