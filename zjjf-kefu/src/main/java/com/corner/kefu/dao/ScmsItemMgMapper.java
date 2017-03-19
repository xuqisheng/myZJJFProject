package com.corner.kefu.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ScmsItem;
import com.corner.kefu.beans.ro.scms.ScmsItemMgRo;

public interface ScmsItemMgMapper {
	/**
	 * 
	* @Title: getPageList 
	* @Description: 查询列表信息 
	* @param @param command
	* @param @return    设定文件
	* @return List<ScmsItemMgRo>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScmsItemMgRo> getPageList(ScmsItemMgRo command);
	
	/**
	 * 
	* @Title: getPageListSize 
	* @Description: 查询总页数
	* @param @param command
	* @param @return    设定文件
	* @return int    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	int getPageListSize(ScmsItemMgRo command);
	
	/**
	 * 
	* @Title: findByItemId 
	* @Description: 通过itemBaseId 获取商品列表 
	* @param @param command
	* @param @return    设定文件
	* @return List<ScmsItemMgRo>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScmsItemMgRo> findByItemId(ScmsItemMgRo command);
	/**
	 * 
	 * @Title: findByItemId 
	 * @Description: 通过itemBaseId 获取商品列表 
	 * @param @param command
	 * @param @return    设定文件
	 * @return List<ScmsItemMgRo>    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	List<ScmsItemMgRo> findByItemIdAndMonthAndYear(ScmsItemMgRo command);
	
	/**
	 * 
	* @Title: findById 
	* @Description: 通过ID过去单条信息
	* @param @param itemId
	* @param @return    设定文件
	* @return ScmsItemMgRo    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ScmsItemMgRo findById(String itemId);
	
	/**
	 * 
	* @Title: updateStatsByItemId 
	* @Description: 修改上下架信息，通过ItemId
	* @param @param scmsItem
	* @param @return    设定文件
	* @return int    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	int updateStatsByItemId(ScmsItem scmsItem);
	
	/**
	 * 
	* @Title: findByMdseIdOrName 
	* @Description: 通过功能码和名称获取商品列表
	* @param @param command
	* @param @return    设定文件
	* @return ScmsItemMgRo    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ScmsItemMgRo findByMdseIdOrName(ScmsItemMgRo command);
	/**
	 * 
	* @Title: findItemByName 
	* @Description: 获取商品可添加信息
	* @param @param map
	* @param @return    设定文件 
	* @return ScmsItemMgRo    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<Map<String, Object>> findItemByName(Map<String, Object> map);
	
	/**
	 * 
	* @Title: deleteScmsShoppingCareBySpId 
	* @Description: 删除购物车商品，通过批发商ID
	* @param	scmsItemId	商品ID
	* @param	id	记录ID
	* @param 	spId 批发商ID
	* @return    设定文件 
	* @return int    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	int deleteScmsShoppingCareBySpId(Map<String, Object> map);
	
}