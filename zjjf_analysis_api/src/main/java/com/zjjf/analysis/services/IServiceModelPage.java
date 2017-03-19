package com.zjjf.analysis.services;

import java.util.HashMap;
import java.util.List;

public interface IServiceModelPage {

	public List<Object[]> getData(String userName, Integer menuId, HashMap<String, Object> paramMap);

	public Integer getTotalCount(String userName, Integer menuId, HashMap<String, Object> paramMap);

	public List<Object[]> getExcelData(String userName, Integer menuId, HashMap<String, Object> paramMap);
}
