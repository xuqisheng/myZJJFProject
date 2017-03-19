package com.corner.core.dao;

import com.corner.core.beans.SpGroupAddsMapKey;

public interface SpGroupAddsMapMapper {
    int deleteByPrimaryKey(SpGroupAddsMapKey key);

    int insert(SpGroupAddsMapKey record);

    int insertSelective(SpGroupAddsMapKey record);
}