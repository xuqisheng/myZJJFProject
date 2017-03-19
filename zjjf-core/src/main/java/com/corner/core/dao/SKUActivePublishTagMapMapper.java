package com.corner.core.dao;

import com.corner.core.beans.SKUActivePublishTagMap;

public interface SKUActivePublishTagMapMapper {
    int deleteByPrimaryKey(String id);

    int insert(SKUActivePublishTagMap record);

    int insertSelective(SKUActivePublishTagMap record);

    SKUActivePublishTagMap selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SKUActivePublishTagMap record);

    int updateByPrimaryKey(SKUActivePublishTagMap record);
}