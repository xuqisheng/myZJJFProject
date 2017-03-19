package com.corner.task.dao;

import com.corner.task.beans.SKUActivePublishTagMap;

public interface SKUActivePublishTagMapMapper {
    int deleteByPrimaryKey(String id);

    int insert(SKUActivePublishTagMap record);

    int insertSelective(SKUActivePublishTagMap record);

    SKUActivePublishTagMap selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SKUActivePublishTagMap record);

    int updateByPrimaryKey(SKUActivePublishTagMap record);
}