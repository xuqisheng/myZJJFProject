package com.corner.task.dao.mg;

import java.util.List;

import com.corner.task.beans.ScOrderInfo;

public interface ScOrderInfoMgMapper { 
	/**
	 * 
	* @Title: getAllNoPayOrderInfoList 
	* @Description:查询所有距离现在超过24小时未支付且未被删除的订单
	* @param @return
	* @return List<ScOrderInfo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<ScOrderInfo> getAllNoPayOrderInfoList();

	/**
	 * 备份购物车记录
	 * @return
	 */
	Integer bakSpShoppingCart();
}