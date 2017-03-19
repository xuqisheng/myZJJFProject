package com.zjjf.analysis.producer.erp;

import java.util.HashMap;

import com.zjjf.analysis.producer.base.IViewData;

public interface IManagerPurchaseService <T> extends IViewData<T> {

	public HashMap<String, Object> getDailyMummary(HashMap<String, Object> paramMap, String userName, Integer menuId);
}
