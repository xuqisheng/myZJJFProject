package com.corner.core.dao;

import com.corner.core.beans.ERPPlantItemLog;

public interface ERPPlantItemLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(ERPPlantItemLog record);

    int insertSelective(ERPPlantItemLog record);

    ERPPlantItemLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ERPPlantItemLog record);

    int updateByPrimaryKey(ERPPlantItemLog record);
}