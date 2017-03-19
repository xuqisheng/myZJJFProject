package com.corner.core.dao;

import com.corner.core.beans.SpGroupAdvertisment;

public interface SpGroupAdvertismentMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpGroupAdvertisment record);

    int insertSelective(SpGroupAdvertisment record);

    SpGroupAdvertisment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpGroupAdvertisment record);

    int updateByPrimaryKey(SpGroupAdvertisment record);
}