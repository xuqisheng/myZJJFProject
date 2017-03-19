package com.zjjf.analysis.mapper;

import java.util.HashMap;
import java.util.List;

public interface IMapper<T> {

	List<T> getData(HashMap<String, Object> paramMap);
	
	List<T> getDataNoPage(HashMap<String, Object> paramMap);

	Integer getDataCount(HashMap<String, Object> paramMap);

	List<T> getDataExcel(HashMap<String, Object> paramMap);
}
