package com.corner.core.dao;

import com.corner.core.beans.ScmsItem;

public interface ScmsItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScmsItem record);

    int insertSelective(ScmsItem record);

    ScmsItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScmsItem record);

    int updateByPrimaryKey(ScmsItem record);
}