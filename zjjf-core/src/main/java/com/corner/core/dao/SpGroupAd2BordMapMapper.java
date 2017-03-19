package com.corner.core.dao;

import com.corner.core.beans.SpGroupAd2BordMapKey;

public interface SpGroupAd2BordMapMapper {
    int deleteByPrimaryKey(SpGroupAd2BordMapKey key);

    int insert(SpGroupAd2BordMapKey record);

    int insertSelective(SpGroupAd2BordMapKey record);
}