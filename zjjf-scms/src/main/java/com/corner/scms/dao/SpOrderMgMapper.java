package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.scms.beans.ro.SpOrderInfoRo;
import com.corner.scms.beans.vo.OrderInfoVo;
import com.corner.scms.beans.vo.SpOrderDetailVo;
import com.corner.scms.beans.vo.SpOrderListVo;

public interface SpOrderMgMapper {

	List<SpOrderInfo> selectSpOrderInfoSelectivepc(SpOrderInfoRo spOrderInfoRo);

	Integer selectSpOrderCountOfStatuspc(SpOrderInfoRo spOrderInfoRo);

	OrderInfoVo findByOrId(String orderId);

	List<SpOrderDetail> getOrderDetail(String storeid);

	boolean updateOrder(SpOrderInfo sp);

	List<SpOrderListVo> getDgOrderDetail(String getpId); 
	
	List<SpOrderDetailVo> getZjjfOrderDetail(String storeid);

	String getyewuyuanmobile(Integer storeId);

	Integer getGoodsSendStockBySpIdAndItemBaseId(Map<String, Object> map);

	List<SpOrderListVo> getCautionOrder(String supplierid);

	SpOrderInfo getSpOrDerInfoByOrderId(String spOrderId);

	void updateStatusEquals4(SpOrderInfo spOrderInfo);

	void updateStatusEquals5(SpOrderInfo spOrderInfo);

	List<SpOrderInfo> getAllSendSpOrDerList(SpOrderInfo spOrderInfo);

	void updateFuOrDderStatusEquals4(SpOrderInfo orderInfoRo);

	List<SpOrderInfo> getAllNotSendSpOrDerInfoList(SpOrderInfo orderInfoRo);

	void updateFuOrderStatusEquals5(SpOrderInfo orderInfoRo);


	
	
}
	
