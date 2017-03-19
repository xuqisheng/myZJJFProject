package com.zjjf.analysis.producer.supplier;

import java.util.HashMap;
import java.util.List;

public interface ISupplierSaleGoalService {
	
	public List<Object[]> query(String userName, int menuId, HashMap<String, Object> paramMap);
	
	Integer batchUpdate(HashMap<String, Object> hashMap);
}
