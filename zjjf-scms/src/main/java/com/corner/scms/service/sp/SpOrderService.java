package com.corner.scms.service.sp;

import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.erp.SpOrderInfoMgRo;
import com.corner.scms.service.BaseService;

import java.util.List;

public interface SpOrderService extends BaseService{
	List<SpOrderInfo> getInfoListByPId(String pId);
	SpOrderInfo getInfoById(String id);
	SpOrderDetail getDetailById(String id);
	List<SpOrderDetail> getDetailByOrderId(String orderId);
	List<SpOrderDetail> getDetailByOrderId2(String orderId2);
	void updateDetail(SpOrderDetail detail) throws Exception;
	void updateInfo(SpOrderInfo info) throws Exception;
	Pager<SpOrderInfo> getSpOrderInfoList(SpOrderInfoMgRo ro);
	List<SpOrderDetail> getOtherDetail(String orderId, String orderId2, List<String> ids);
}
