package com.corner.core.dao;

import com.corner.core.beans.SpShoppingCart;

public interface SpShoppingCartMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpShoppingCart record);

    int insertSelective(SpShoppingCart record);

    SpShoppingCart selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpShoppingCart record);

    int updateByPrimaryKey(SpShoppingCart record);
}