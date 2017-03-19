package com.corner.kefu.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ItemBase;
import com.corner.kefu.beans.vo.ItemBaseVo;


public interface ItemBaseMgMapper {
	List<ItemBase> getItemBaseList(ItemBase itemBase);
	
	ItemBase getItemBaseByMdseId(ItemBase itemBase);
	
	List<ItemBase> getListByItem(ItemBase itemBase);
	
	Map<String, Object> selectByPrimaryKey(Integer id);

	/**
	 * 
	* @Title: selectItemBaseByMdseId 
	* @Description:根据条形码查询ItemBase
	* @param @param string
	* @param @return
	* @param @throws Exception
	* @return ItemBaseVo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ItemBaseVo selectItemBaseByMdseId(String string) throws Exception;

	ItemBase selectItemBaseByBrand(String mdseId) throws Exception;

	void update(ItemBase itemBase) throws Exception;

	/**
	 * 
	* @Title: getSpVoucherItemBaseList 
	* @Description:优惠劵查询符合条件的itemBase id 集合
	* @param @param itemBase
	* @param @return
	* @param @throws Exception
	* @return List<String>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<String> getSpVoucherItemBaseList(ItemBase itemBase) throws Exception;

	ItemBase selectItemByBrand(String mdseId) throws Exception;


}