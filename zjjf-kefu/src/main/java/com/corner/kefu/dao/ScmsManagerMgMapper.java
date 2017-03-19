package com.corner.kefu.dao;

import java.util.List;

import com.corner.core.beans.ScmsManager;
import com.corner.kefu.beans.ro.scms.ScmsManagerMgRo;


public interface ScmsManagerMgMapper {

	/**
	 * 
	* @Title: getAllScmsManager 
	* @Description: 获取经销商信息 
	* @param @return    设定文件 
	* @return List<ScmsManager>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScmsManager> getAllScmsManager(ScmsManagerMgRo command);
	/**
	 * 
	* @Title: getPageList 
	* @Description: 获取用户列表信息 
	* @param @param command
	* @param @return    设定文件
	* @return List<ScmsManager>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScmsManager> getPageList(ScmsManagerMgRo command);
	
	/**
	 * 
	* @Title: getPageListSize 
	* @Description: 获取总条数
	* @param @param command
	* @param @return    设定文件
	* @return int    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	int getPageListSize(ScmsManagerMgRo command);
	
	/**
	 * 
	* @Title: findScmsManagerByItemId 
	* @Description: 获取经销商信息 通过商品ID
	* @param @param itemId
	* @param @return    设定文件
	* @return ScmsManager    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ScmsManager findScmsManagerByItemId(String itemId);
	
	
	
}