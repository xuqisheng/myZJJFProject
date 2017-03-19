package com.corner.core.dao;

import com.corner.core.beans.ERPPurchaseStockInInfo;

public interface ERPPurchaseStockInInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ERPPurchaseStockInInfo record);

    int insertSelective(ERPPurchaseStockInInfo record);

    ERPPurchaseStockInInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ERPPurchaseStockInInfo record);

    int updateByPrimaryKey(ERPPurchaseStockInInfo record);
}