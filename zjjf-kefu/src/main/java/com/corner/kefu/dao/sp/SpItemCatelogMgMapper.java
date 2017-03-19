package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ItemCatelog;

public interface SpItemCatelogMgMapper {
	
	List<ItemCatelog> getAllItemCateByPid(Map<String, Object> map);

	List<ItemCatelog> getSecondCateList();
}
