package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.beans.analysis.items.ItemCatelog;

public interface ItemCatelogMapper {

	List<AnaDictionary> getByClassfiOneList(HashMap<String, Object> paramMap);
	
	List<AnaDictionary> getByClassfiTwoList(HashMap<String, Object> paramMap);
	
	ItemCatelog getById(Integer id);
}