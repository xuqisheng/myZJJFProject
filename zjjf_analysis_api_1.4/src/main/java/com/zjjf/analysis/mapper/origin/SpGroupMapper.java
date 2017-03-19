package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.beans.analysis.base.SpGroup;

public interface SpGroupMapper {

	List<AnaDictionary> getSpGroupCodeList(HashMap<String, Object> cityMap);
	
	SpGroup getById(Integer id);
}