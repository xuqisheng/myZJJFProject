package com.zjjf.analysis.mapper.ajie;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.ajie.Salesman;
import com.zjjf.analysis.beans.analysis.saleman.SalemanDaily;

public interface SalesmanMapper {
	
	List<Salesman> getAll(HashMap<String, Object> paramMap);
	
	List<Salesman> getByMap(HashMap<String, Object> paramMap);
	
	SalemanDaily getSumInfo(HashMap<String, Object> paramMap);

    Salesman selectByPrimaryKey(String shopId);

}