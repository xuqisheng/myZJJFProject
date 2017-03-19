package com.corner.core.dao;

import com.corner.core.beans.SKUActivePublishTagLog;

public interface SKUActivePublishTagLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SKUActivePublishTagLog record);

    int insertSelective(SKUActivePublishTagLog record);

    SKUActivePublishTagLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SKUActivePublishTagLog record);

    int updateByPrimaryKey(SKUActivePublishTagLog record);
}