package com.corner.kefu.dao;

import java.util.List;

import com.corner.core.beans.ItemCatelog;
import com.corner.kefu.beans.ro.ItemCatelogRo;
import com.corner.kefu.beans.vo.sp.ItemCatelogVo;

public interface ItemCatelogMgMapper {

	List<ItemCatelog> getAllItemCateByBatch(ItemCatelogRo item) throws Exception;

	List<ItemCatelogVo> getTreeItemCateLog() throws Exception;

	List<ItemCatelogVo> getItemBaseCateLogVoList() throws Exception;

	/**
	 * 
	* @Title: getItemCateLogTreeList 
	* @Description:获取树形图
	* @param @return
	* @param @throws Exception
	* @return List<ItemCatelogVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ItemCatelogVo> getItemCateLogTreeList() throws Exception;

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
