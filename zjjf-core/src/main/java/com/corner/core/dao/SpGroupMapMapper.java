package com.corner.core.dao;

import com.corner.core.beans.SpGroupMapKey;

public interface SpGroupMapMapper {
    int deleteByPrimaryKey(SpGroupMapKey key);

    int insert(SpGroupMapKey record);

    int insertSelective(SpGroupMapKey record);
}