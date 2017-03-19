package com.corner.core.dao;

import com.corner.core.beans.RegionSpGroupMap;

public interface RegionSpGroupMapMapper {
    int deleteByPrimaryKey(String id);

    int insert(RegionSpGroupMap record);

    int insertSelective(RegionSpGroupMap record);

    RegionSpGroupMap selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RegionSpGroupMap record);

    int updateByPrimaryKey(RegionSpGroupMap record);
}