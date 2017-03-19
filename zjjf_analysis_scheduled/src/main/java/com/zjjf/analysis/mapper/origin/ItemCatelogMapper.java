package com.zjjf.analysis.mapper.origin;

import java.util.List;

import com.zjjf.analysis.beans.origin.items.ItemCatelog;

public interface ItemCatelogMapper {
	
	  List<ItemCatelog> selectByIndex(Integer index);
	    
	  ItemCatelog getById(Integer id);

}