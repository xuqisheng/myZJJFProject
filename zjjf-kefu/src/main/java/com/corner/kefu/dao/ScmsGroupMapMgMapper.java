package com.corner.kefu.dao;

import java.util.List;

import com.corner.core.beans.ScmsGroupMapKey;

public interface ScmsGroupMapMgMapper {
	/**
	 * 
	* @Title: deleteByManagerId 
	* @Description: 删除区域关系  通过用户ID 
	* @param @param managerId
	* @param @return    设定文件
	* @return int    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	int deleteByManagerId(String managerId);
	
	/**
	 * 
	* @Title: findByManagerId 
	* @Description: 查询区域关系 通过用户ID 
	* @param @param managerId
	* @param @return    设定文件
	* @return List<ScmsGroupMapKey>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScmsGroupMapKey> findByManagerId(String managerId);
}