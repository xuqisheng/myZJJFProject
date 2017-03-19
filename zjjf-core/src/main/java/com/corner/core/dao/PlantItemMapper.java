package com.corner.core.dao;

import com.corner.core.beans.PlantItem;

public interface PlantItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(PlantItem record);

    int insertSelective(PlantItem record);

    PlantItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlantItem record);

    int updateByPrimaryKey(PlantItem record);
}