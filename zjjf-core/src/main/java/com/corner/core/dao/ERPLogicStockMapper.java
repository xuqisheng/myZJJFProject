package com.corner.core.dao;

import com.corner.core.beans.ERPLogicStock;

public interface ERPLogicStockMapper {
    int deleteByPrimaryKey(String id);

    int insert(ERPLogicStock record);

    int insertSelective(ERPLogicStock record);

    ERPLogicStock selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ERPLogicStock record);

    int updateByPrimaryKey(ERPLogicStock record);
}