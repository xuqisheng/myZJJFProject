package com.corner.core.dao;

import com.corner.core.beans.ScManager;

public interface ScManagerMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScManager record);

    int insertSelective(ScManager record);

    ScManager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScManager record);

    int updateByPrimaryKey(ScManager record);
}