package com.corner.kefu.dao;

import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.kefu.beans.ro.CheckBillCondition;
import com.corner.kefu.beans.ro.SpOrderInfoRo;
import com.corner.kefu.beans.ro.SpOrderMgCondition;
import com.corner.kefu.beans.vo.sp.SpOrderInfoVo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SpOrderInfoMgMapper {

	List<SpOrderInfo> getSpOrderInfoByOrderId(SpOrderMgCondition command);
	
	List<SpOrderDetail> getSpOrderDetailByOrderId(SpOrderMgCondition command);

	int updateSpOrderInfoBatch(CheckBillCondition command);

	List<SpOrderInfo> getSpOrderInfospc(SpOrderInfoRo spOrderInfoRo);

	Integer selectSpOrderInfoSelectivepcCount(SpOrderInfoRo spOrderInfoRo);

	List<SpOrderInfo> selectSpOrderInfoSelective(SpOrderInfoRo spOrderInfoRo);

	List<SpOrderInfoVo> selectFinaceSpOrderInfo(SpOrderInfoRo spOrderInfoRo);

	Integer selectCountFinaceSpOrderInfo(SpOrderInfoRo spOrderInfoRo);

	List<SpOrderInfoVo> selectSpOrderInfoFinace(SpOrderInfoRo spOrderInfoRo);

	Integer selectSpOrderCountOfFinace(SpOrderInfoRo spOrderInfoRo);

	Integer selectSpOrderInfoCountpc(SpOrderInfoRo spOrderInfoRo);

	List<SpOrderInfo> getStoreAllOrder(Map<String, Object> map);

	/**
	 * 
	* @Title: getCountUnfinishOrders 
	* @Description:根据店铺id查询该店铺已经支付但还没有确认收货的订单数量
	* @param @param map
	* @param @return
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Integer getCountUnfinishOrders(Map<String, Object> map);

	/**
	 * 
	* @Title: getUnPayOrders 
	* @Description:根据店铺id获取所有该店铺还没有支付的订单
	* @param @param map
	* @param @return
	* @return List<SpOrderInfo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<SpOrderInfo> getUnPayOrders(Map<String, Object> map);

	/**
	 * 
	* @Title: getOrdersFromSpOrderActiveMap 
	* @Description:从SpOrderActiveMap表中根据活动id和批发商id获取订单信息
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<SpOrderInfo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<SpOrderInfo> getOrdersFromSpOrderActiveMap(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getCountOrdersFromSpOrderActiveMap 
	* @Description:
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Integer getCountOrdersFromSpOrderActiveMap(Map<String, Object> map) throws Exception;


    BigDecimal getAccTotalOrderPrice(Map<String, Object> paramMap);

	BigDecimal getAccTotalReturnOrderPrice(Map<String, Object> paramMap);
}
