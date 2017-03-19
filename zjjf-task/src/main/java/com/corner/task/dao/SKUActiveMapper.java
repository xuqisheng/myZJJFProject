package com.corner.task.dao;

import com.corner.task.beans.SKUActive;

public interface SKUActiveMapper {
    int deleteByPrimaryKey(String id);

    int insert(SKUActive record);

    int insertSelective(SKUActive record);

    SKUActive selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SKUActive record);

    int updateByPrimaryKey(SKUActive record);
}