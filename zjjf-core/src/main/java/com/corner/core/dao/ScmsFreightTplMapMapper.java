package com.corner.core.dao;

import com.corner.core.beans.ScmsFreightTplMap;

public interface ScmsFreightTplMapMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScmsFreightTplMap record);

    int insertSelective(ScmsFreightTplMap record);

    ScmsFreightTplMap selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScmsFreightTplMap record);

    int updateByPrimaryKey(ScmsFreightTplMap record);
}