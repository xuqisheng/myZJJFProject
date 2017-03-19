package com.zjjf.analysis.mapper.analysis;

import com.zjjf.analysis.beans.analysis.orders.ERPPurchaseDaily;

public interface ERPPurchaseDailyMapper {

    int insert(ERPPurchaseDaily record);

    ERPPurchaseDaily selectByPrimaryKey(Integer id);
}