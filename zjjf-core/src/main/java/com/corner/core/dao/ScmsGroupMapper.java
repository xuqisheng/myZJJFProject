package com.corner.core.dao;

import com.corner.core.beans.ScmsGroup;

public interface ScmsGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScmsGroup record);

    int insertSelective(ScmsGroup record);

    ScmsGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScmsGroup record);

    int updateByPrimaryKey(ScmsGroup record);
}