package com.corner.core.dao;

import com.corner.core.beans.PlantProtocol;

public interface PlantProtocolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlantProtocol record);

    int insertSelective(PlantProtocol record);

    PlantProtocol selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlantProtocol record);

    int updateByPrimaryKey(PlantProtocol record);
}