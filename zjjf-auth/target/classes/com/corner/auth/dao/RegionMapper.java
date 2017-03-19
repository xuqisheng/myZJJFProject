package com.corner.auth.dao;

import com.corner.auth.beans.Region;

public interface RegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);
}