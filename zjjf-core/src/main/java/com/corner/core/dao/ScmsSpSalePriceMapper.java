package com.corner.core.dao;

import com.corner.core.beans.ScmsSpSalePrice;

public interface ScmsSpSalePriceMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScmsSpSalePrice record);

    int insertSelective(ScmsSpSalePrice record);

    ScmsSpSalePrice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScmsSpSalePrice record);

    int updateByPrimaryKey(ScmsSpSalePrice record);
}