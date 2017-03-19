package com.corner.core.dao;

import com.corner.core.beans.ScmsShoppingCart;

public interface ScmsShoppingCartMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScmsShoppingCart record);

    int insertSelective(ScmsShoppingCart record);

    ScmsShoppingCart selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScmsShoppingCart record);

    int updateByPrimaryKey(ScmsShoppingCart record);

	ScmsShoppingCart findShopById(ScmsShoppingCart cart);

	int selectCount(ScmsShoppingCart cart);
}