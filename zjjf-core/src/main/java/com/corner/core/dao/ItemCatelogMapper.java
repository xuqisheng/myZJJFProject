package com.corner.core.dao;

import com.corner.core.beans.ItemCatelog;

public interface ItemCatelogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemCatelog record);

    int insertSelective(ItemCatelog record);

    ItemCatelog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemCatelog record);

    int updateByPrimaryKey(ItemCatelog record);
}