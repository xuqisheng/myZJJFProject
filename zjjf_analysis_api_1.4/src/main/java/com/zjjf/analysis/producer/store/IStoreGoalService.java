package com.zjjf.analysis.producer.store;

import java.util.HashMap;
import java.util.List;

public interface IStoreGoalService {

	public Integer batchUpdate(HashMap<String, Object> paramMap);

	public List<Object[]> query(String userName, Integer menuId,
			HashMap<String, Object> paramMap);
}
