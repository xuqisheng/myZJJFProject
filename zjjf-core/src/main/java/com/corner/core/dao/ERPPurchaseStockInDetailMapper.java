package com.corner.core.dao;

import com.corner.core.beans.ERPPurchaseStockInDetail;

public interface ERPPurchaseStockInDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(ERPPurchaseStockInDetail record);

    int insertSelective(ERPPurchaseStockInDetail record);

    ERPPurchaseStockInDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ERPPurchaseStockInDetail record);

    int updateByPrimaryKey(ERPPurchaseStockInDetail record);
}