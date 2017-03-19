package com.zjjf.analysis.producer.base;

import java.util.HashMap;
import java.util.List;

public interface ITimeData<T> {

	public List<HashMap<String, Object>> getData(HashMap<String, Object> paramMap);

	public T mapToObject(HashMap<String, Object> map);

	public HashMap<String, Object> mapToObject(T t);
}
