package com.corner.core.dao;

import com.corner.core.beans.ItemTag;

public interface ItemTagMapper {
    int deleteByPrimaryKey(Integer tag_id);

    int insert(ItemTag record);

    int insertSelective(ItemTag record);

    ItemTag selectByPrimaryKey(Integer tag_id);

    int updateByPrimaryKeySelective(ItemTag record);

    int updateByPrimaryKey(ItemTag record);
}