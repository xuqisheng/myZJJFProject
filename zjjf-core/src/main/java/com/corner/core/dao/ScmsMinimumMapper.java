package com.corner.core.dao;

import com.corner.core.beans.ScmsMinimum;

public interface ScmsMinimumMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScmsMinimum record);

    int insertSelective(ScmsMinimum record);

    ScmsMinimum selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScmsMinimum record);

    int updateByPrimaryKey(ScmsMinimum record);
}