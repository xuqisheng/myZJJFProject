package com.corner.task.dao;

import com.corner.task.beans.SKUActivePublishTagLog;

public interface SKUActivePublishTagLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SKUActivePublishTagLog record);

    int insertSelective(SKUActivePublishTagLog record);

    SKUActivePublishTagLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SKUActivePublishTagLog record);

    int updateByPrimaryKey(SKUActivePublishTagLog record);
}