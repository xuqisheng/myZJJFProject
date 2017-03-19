package com.corner.kefu.service.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ItemCatelog;

public interface SpItemCatelogService {

	public List<ItemCatelog> getAllItemCateByPid(Map<String, Object> map);
	
	/**
	 * 查询二级分类列表
	 * @return
	 */
	public List<ItemCatelog> getSecondCateList() ;
}
