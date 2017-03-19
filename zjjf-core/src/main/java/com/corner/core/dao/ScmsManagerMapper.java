package com.corner.core.dao;

import com.corner.core.beans.ScmsManager;

public interface ScmsManagerMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScmsManager record);

    int insertSelective(ScmsManager record);

    ScmsManager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScmsManager record);

    int updateByPrimaryKey(ScmsManager record);
}