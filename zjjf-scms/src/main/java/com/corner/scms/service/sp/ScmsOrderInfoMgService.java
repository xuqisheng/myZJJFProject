package com.corner.scms.service.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ScmsOrderDetail;
import com.corner.core.beans.ScmsOrderInfo;
import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.ScmsOrderInfoMgCondition;
import com.corner.scms.beans.ro.SpOrderInfoRo;
import com.corner.scms.beans.vo.OrderInfoVo;
import com.corner.scms.beans.vo.SpOrderDetailVo;
import com.corner.scms.beans.vo.SpOrderListVo;
import com.corner.scms.service.BaseService;

public interface ScmsOrderInfoMgService extends BaseService{

	/**
	 * 
	 * @Title: getScmsOrderInfoPageList 
	 * @Description: 订单列表查询 
	 * @param @return    设定文件
	 * @return Pager<ScmsOrderInfo>    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	Pager<ScmsOrderInfo> getScmsOrderInfoPageList(ScmsOrderInfoMgCondition command);

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective 
	 * @Description: 修改订单信息 
	 * @param @param command
	 * @param @return    设定文件
	 * @return ModelMsg    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	ModelMsg updateByPrimaryKeySelective(ScmsOrderInfoMgCondition command);

	/**
	 * 
	 * @Title: insert 
	 * @Description: 插入一条新的订单信息 
	 * @param @param command
	 * @param @return    设定文件
	 * @return ModelMsg    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	ModelMsg insert(ScmsOrderInfoMgCondition command);

	/**
	 * 
	 * @Title: getOrderTodayAll 
	 * @Description: 查询今天订单总数据 
	 * @param @param command
	 * @param @return    设定文件
	 * @return Map<String, Object>    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	Map<String, Object> getOrderTodayAll(String spId);
	
	/**
	 * 
	* @Title: getScmsOrderTodayAll 
	* @Description: 查询今日线下订单总数据 
	* @param @return    设定文件
	* @return Map<String,Object>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	Map<String, Object> getScmsOrderTodayAll(String spId);
	
	/**
	 * 
	* @Title: selectByOrderId 
	* @Description: 查询单笔订单 通过orderId 
	* @param @param orderId
	* @param @return    设定文件
	* @return ScmsOrderInfo    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ScmsOrderInfoMgCondition selectByOrderId(String orderId);
	ScmsOrderInfo selectById(String id);
	
	/**
	 * 
	* @Title: getDetailByOrderId 
	* @Description: 通过orderId查询子订单信息 
	* @param @param orderId
	* @param @return    设定文件
	* @return List<ScmsOrderDetail>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScmsOrderDetail> getDetailByOrderId(String orderId);

	/**
	 * 
	* @Title: addScmsSpSalePrice 
	* @Description: 添加出货价格 
	* @param @param price
	* @param @return    设定文件
	* @return ModelMsg    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg addScmsSpSalePrice(ScmsOrderInfoMgCondition price);
	
	/**
	 * 
	* @Title: updateStoreType 
	* @Description: 修改客户类型重新计算金额 
	* @param @param condition
	* @param @return    设定文件
	* @return ModelMsg    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg updateStoreType(ScmsOrderInfoMgCondition condition);
	ModelMsg deleteByPrimaryKeySelective(ScmsOrderInfoMgCondition condition);
	
	/**
	 * 
	* @Title: updatePlantItemStock 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param 
	* @return    设定文件 
	* @return ModelMsg    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @exception	下单：map.put("orderId", info.getOrderId()); map.put("actype", 0);
	* 				取消订单：map.put("orderId", info.getOrderId()); map.put("actype", 1);
	* 				确认收货：map.put("orderId", info.getOrderId()); map.put("actype", 2);
	* @throws
	 */
	ModelMsg updatePlantItemStock(Map<String, Object> map) throws RuntimeException;
	/********************************************转角订单 部分***********************************************************/
	
	
	List<SpOrderInfo> getSpOrderInfospc(SpOrderInfoRo spOrderInfoRo);

	Integer selectSpOrderCountOfStatus(SpOrderInfoRo spOrderInfoRo);

	Supplier findSupplier(String id);

	OrderInfoVo findByOrId(String orderId);

	List<SpOrderDetail> getOrderDetail(String storeid);
	
	public List<SpOrderDetailVo> getZjjfOrderDetail(String storeid) ;

	boolean updateOrder(SpOrderInfo sp);

	String getyewuyuanmobile(Integer storeId);

	List<SpOrderListVo> getCautionOrder(String id);


	SpOrderInfo getFuOrDerById(String getpId);
}
