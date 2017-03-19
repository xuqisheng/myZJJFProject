package com.corner.core.dao;

import com.corner.core.beans.PlantItemProductMap;
import com.corner.core.beans.PlantItemProductMapKey;

public interface PlantItemProductMapMapper {
    int deleteByPrimaryKey(PlantItemProductMapKey key);

    int insert(PlantItemProductMap record);

    int insertSelective(PlantItemProductMap record);

    PlantItemProductMap selectByPrimaryKey(PlantItemProductMapKey key);

    int updateByPrimaryKeySelective(PlantItemProductMap record);

    int updateByPrimaryKey(PlantItemProductMap record);
}