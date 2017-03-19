package com.corner.core.dao;

import com.corner.core.beans.PlantItemProduct;

public interface PlantItemProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(PlantItemProduct record);

    int insertSelective(PlantItemProduct record);

    PlantItemProduct selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlantItemProduct record);

    int updateByPrimaryKey(PlantItemProduct record);
}