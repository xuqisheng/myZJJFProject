package com.corner.core.dao;

import com.corner.core.beans.RegionStoreMapKey;

public interface RegionStoreMapMapper {
    int deleteByPrimaryKey(RegionStoreMapKey key);

    int insert(RegionStoreMapKey record);

    int insertSelective(RegionStoreMapKey record);
}