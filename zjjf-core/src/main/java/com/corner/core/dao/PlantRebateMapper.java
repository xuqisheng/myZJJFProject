package com.corner.core.dao;

import com.corner.core.beans.PlantRebate;

public interface PlantRebateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlantRebate record);

    int insertSelective(PlantRebate record);

    PlantRebate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlantRebate record);

    int updateByPrimaryKey(PlantRebate record);
}