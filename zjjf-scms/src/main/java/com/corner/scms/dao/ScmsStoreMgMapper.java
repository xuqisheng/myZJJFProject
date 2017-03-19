package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ScmsStore;
import com.corner.scms.beans.ro.ScmsStoreCondition;
import com.corner.scms.beans.vo.ScmsStoreVo;
import com.corner.scms.beans.vo.StoreVo;

public interface ScmsStoreMgMapper { 

	/**
	 * 
	 * @Title: getList 
	 * @Description: 根据用户信息查询信息，根据店主名字 
	 * @param @param command
	 * @param @return    设定文件
	 * @return List<ScmsOrderInfo>    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	List<Map<String, Object>> getListByContact(ScmsStore command);
	
	/**
	 * 
	* @Title: getItemBaseListByName 
	* @Description: 获取商品信息，根据商品名称 
	* @param @param command
	* @param @return    设定文件
	* @return List<Map<String,Object>>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<Map<String, Object>> getItemBaseListByName(Map<String, Object> command);
	
	/**
	 * 
	* @Title: findBySpidAndMobaile 
	* @Description: 查找 
	* @param @param scmsStore
	* @param @return    设定文件
	* @return ScmsStore    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ScmsStore findBySpidAndMobaile(ScmsStore scmsStore);
	
	/******************************************************************************/
	
	List<ScmsStoreVo> getPageList(ScmsStoreCondition command);

	int getPageListSize(ScmsStoreCondition command);

	ScmsStoreVo findById(Integer parseInt);

	int deleteById(Integer id);

	List<StoreVo> getPageListDown(ScmsStoreCondition condition);

	int getPageListSizeDown(ScmsStoreCondition condition);
	
	
}
	
