package com.zjjf.analysis.producer.corner;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.orders.ErpPurchaseDaily;

public interface IPurchaseStockAnaService {

	List<ErpPurchaseDaily> getErpPurchaseReportAna(HashMap<String, Object> paramMap);
}
