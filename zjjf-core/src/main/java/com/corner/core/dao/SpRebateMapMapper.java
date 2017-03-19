package com.corner.core.dao;

import com.corner.core.beans.SpRebateMapKey;

public interface SpRebateMapMapper {
    int deleteByPrimaryKey(SpRebateMapKey key);

    int insert(SpRebateMapKey record);

    int insertSelective(SpRebateMapKey record);
}