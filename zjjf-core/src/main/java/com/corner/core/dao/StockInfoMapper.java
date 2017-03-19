package com.corner.core.dao;

import com.corner.core.beans.StockInfo;

public interface StockInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(StockInfo record);

    int insertSelective(StockInfo record);

    StockInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StockInfo record);

    int updateByPrimaryKey(StockInfo record);
}