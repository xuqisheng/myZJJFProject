package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.scms.beans.vo.sc.ScOrderDetailVo;

public interface ScmsItemMgMapper {

	/**
	 * 
	* @Title: batchUpdateSales 
	* @Description:批量更新商品销量
	* @param @param orderDetailList
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void batchUpdateSales(List<ScOrderDetailVo> orderDetailList) throws Exception;
	/**
	 * 
	* @Title: updateScmsItemGoodsStockByOrderId 
	* @Description: 修改scmsItem销量通过orderId
	* @param map:
	* @param:orderId订单号
	* @param:actype（0-下单，1-取消订单，2-确认收货）类型
	* @return	设定文件 
	* @return int    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	int updateScmsItemGoodsStockByOrderId(Map<String, Object> map);
}