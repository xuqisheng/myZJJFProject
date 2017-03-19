package com.corner.core.dao;

import com.corner.core.beans.PlantItemGroup;

public interface PlantItemGroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(PlantItemGroup record);

    int insertSelective(PlantItemGroup record);

    PlantItemGroup selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlantItemGroup record);

    int updateByPrimaryKey(PlantItemGroup record);
}