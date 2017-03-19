package com.corner.account.service;

import org.springframework.ui.Model;

import com.corner.account.beans.ro.SpOrderMgCondition;
import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;

public interface SpOrderInfoService extends BaseService{

	Pager<SpOrderInfo> getSpOrderInfoList(SpOrderMgCondition command);

	Pager<SpOrderDetail> getSpOrderInfoDetailList(SpOrderMgCondition command);

	void getSpOrderAllForPage(String spOrderId, Model model);

	ModelMsg updateAcStatus(SpOrderMgCondition command);

	ModelMsg updateSpOrderCol1(String remark , String id , String userId);

	Pager<SpOrderInfo> getScmsSpOrderInfoList(SpOrderMgCondition command);
}
