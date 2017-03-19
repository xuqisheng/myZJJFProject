package com.corner.core.dao;

import com.corner.core.beans.PlantItemLog;

public interface PlantItemLogMapper {
    int insert(PlantItemLog record);

    int insertSelective(PlantItemLog record);
}