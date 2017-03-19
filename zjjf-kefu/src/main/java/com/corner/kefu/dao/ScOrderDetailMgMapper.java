package com.corner.kefu.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ScOrderDetail;


public interface ScOrderDetailMgMapper {
	/**
	 * 
	* @Title: findOrderDetailList 
	* @Description: 查询商品详情可以通过 
	* @param 
	* @return List<ScOrderDetail>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScOrderDetail> findOrderDetailList(Map<String, Object> map);
}