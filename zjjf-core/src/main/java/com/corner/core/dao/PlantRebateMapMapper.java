package com.corner.core.dao;

import com.corner.core.beans.PlantRebateMapKey;

public interface PlantRebateMapMapper {
    int deleteByPrimaryKey(PlantRebateMapKey key);

    int insert(PlantRebateMapKey record);

    int insertSelective(PlantRebateMapKey record);
}