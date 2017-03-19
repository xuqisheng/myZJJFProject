package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ItemBase;
import com.corner.kefu.beans.ro.ItemBaseMgRo;
import com.corner.kefu.beans.ro.ItemBaseRo;
import com.corner.kefu.beans.vo.ItemBaseVo;


public interface SpItemBaseMgMapper {


	List<ItemBaseVo> getAllItemBaseByPatam(ItemBaseRo itemBaseRo);

	int getCountItemBase(ItemBaseRo itemBaseRo);

	void addOneItemBase(ItemBaseRo itemBaseRo);

	ItemBaseVo getItemBaseById(Integer id);

	List<ItemBaseVo> getLogisticsSpecificationsById(Map<String, Object> map);

	int okUniqueness(String mdseId);

	void updateItemBaseOneById(ItemBaseRo itemBaseRo);

	int getCountItemBaseById(Map<String, Object> map);

	void deleteItemBase(Integer id);

	void deleteLogisticsById(Map<String, Object> map);

	/**
	 * 
	* @Title: getPagingItemBaseList 
	* @Description:分页查询商品基本库
	* @param @param itemBaseRo
	* @param @return
	* @param @throws Exception
	* @return List<ItemBase>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ItemBase> getPagingItemBaseList(ItemBaseMgRo itemBaseMgRo) throws Exception;

	/**
	 * 
	* @Title: getCountPagingItemBaseList 
	* @Description:查询商品商品库的总量
	* @param @param itemBaseRo
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Integer getCountPagingItemBaseList(ItemBaseMgRo itemBaseMgRo) throws Exception;

	/**
	 * 
	* @Title: getYijiYixiaItemBase 
	* @Description:根据一级分类id获取所有商品
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<ItemBase>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ItemBase> getYijiYixiaItemBase(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getErjiYixiaItemBase 
	* @Description:根据二级分类id获取所有商品
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<ItemBase>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ItemBase> getErjiYixiaItemBase(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getTongJiItemBase 
	* @Description:根据ItemBase的id查询同种类别下的所有商品
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<ItemBase>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ItemBase> getTongJiItemBase(Map<String, Object> map) throws Exception;
	
	ItemBase getItemBaseByMdseId(Map<String, Object> map);
}