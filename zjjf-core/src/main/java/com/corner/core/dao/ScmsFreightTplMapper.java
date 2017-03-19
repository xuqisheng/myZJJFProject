package com.corner.core.dao;

import com.corner.core.beans.ScmsFreightTpl;

public interface ScmsFreightTplMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScmsFreightTpl record);

    int insertSelective(ScmsFreightTpl record);

    ScmsFreightTpl selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScmsFreightTpl record);

    int updateByPrimaryKey(ScmsFreightTpl record);
}