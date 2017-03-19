package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.orders.ErpPurchaseDaily;

public interface ErpPurchaseDailyMapper {
	
	List<ErpPurchaseDaily> getByMap(HashMap<String, Object> paramMap);

    int insert(ErpPurchaseDaily record);

    ErpPurchaseDaily selectByPrimaryKey(Integer id);
    
    void update(ErpPurchaseDaily record);
}