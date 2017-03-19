package com.zjjf.analysis.producer.base;

import java.util.HashMap;
import java.util.List;

public interface IViewData<T> {

	public HashMap<String, Object> appendParam(HashMap<String, Object> paramMap);

	public List<Object[]> getData(String userName, Integer menuId, HashMap<String, Object> paramMap);

	public Integer getTotalCount(String userName, Integer menuId, HashMap<String, Object> paramMap);

	public List<Object[]> getExcelData(String userName, Integer menuId, HashMap<String, Object> paramMap);

	public Object[] adaptView(T s, Object[] titleEn, HashMap<String, Object> paramMap);
}
