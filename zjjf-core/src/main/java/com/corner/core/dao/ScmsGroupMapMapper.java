package com.corner.core.dao;

import com.corner.core.beans.ScmsGroupMapKey;

public interface ScmsGroupMapMapper {
    int deleteByPrimaryKey(ScmsGroupMapKey key);

    int insert(ScmsGroupMapKey record);

    int insertSelective(ScmsGroupMapKey record);
}