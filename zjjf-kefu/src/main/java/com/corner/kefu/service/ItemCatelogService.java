package com.corner.kefu.service;

import java.util.List;

import com.corner.core.beans.ItemCatelog;
import com.corner.kefu.beans.ro.ItemCatelogRo;
import com.corner.kefu.beans.vo.sp.ItemCatelogVo;

public interface ItemCatelogService {

	List<ItemCatelog> getAllItemCate(ItemCatelogRo item) throws Exception;

	List<ItemCatelogVo> getTreeItemCateLogAndItemBase() throws Exception;
 
	/**
	 * 
	* @Title: getItemCateLogList 
	* @Description:获取商品分类
	* @param @return
	* @param @throws Exception
	* @return List<ItemCatelogVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ItemCatelogVo> getItemCateLogList() throws Exception;

}
