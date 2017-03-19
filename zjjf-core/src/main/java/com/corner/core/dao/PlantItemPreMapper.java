package com.corner.core.dao;

import com.corner.core.beans.PlantItemPre;

public interface PlantItemPreMapper {
    int deleteByPrimaryKey(String id);

    int insert(PlantItemPre record);

    int insertSelective(PlantItemPre record);

    PlantItemPre selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlantItemPre record);

    int updateByPrimaryKey(PlantItemPre record);
}