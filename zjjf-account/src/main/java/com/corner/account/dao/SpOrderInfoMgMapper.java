package com.corner.account.dao;

import java.util.List;
import java.util.Map;

import com.corner.account.beans.ro.SpOrderMgCondition;
import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;

public interface SpOrderInfoMgMapper {

	List<SpOrderInfo> getSpOrderInfoByOrderId(SpOrderMgCondition command);
	
	List<SpOrderDetail> getSpOrderDetailByOrderId(SpOrderMgCondition command);

	//通用更新
	int updateSpOrderInfoBatch(SpOrderMgCondition command);

	//订单通用查询
	List<SpOrderInfo> getSpOrderInfoByCondition(SpOrderMgCondition command);
	int getSpOrderInfoByConditionCount(SpOrderMgCondition command);
	
	//明细通用查询
	List<SpOrderDetail> getSpOrderInfoDetailByCondition(SpOrderMgCondition command);
	int getSpOrderInfoDetailByConditionCount(SpOrderMgCondition command);

	int updateSpOrderInfoBatchWithAcSheet(String acSheetId);

	List<SpOrderInfo> getSpOrderInfoByAcSheetId(String id);
	
	int alertSpWallet(Map<String, Object> map);

	//联合采购订单查询
	List<SpOrderInfo> getScmsSpOrderInfoByCondition(SpOrderMgCondition command);
	int getScmsSpOrderInfoByConditionCount(SpOrderMgCondition command);

}
