package com.corner.account.service;

import java.util.List;

import com.corner.account.beans.ro.MaOrderMgCondition;
import com.corner.account.beans.vo.MaOrderInfoMgVo;
import com.corner.core.beans.MaOrderInfo;
import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;


public interface MaOrderInfoService extends BaseService{

	Pager<MaOrderInfoMgVo> getMaOrderInfoList(MaOrderMgCondition command);
	
    MaOrderInfoMgVo getScOrderInfoById(String id);
    
    MaOrderInfo selectMaOrderInfoById(String id);
    
    List<ScOrderDetail> getOrderDetailList(boolean isDelete,String id,String orderId , String orderId2 ,String maOrderInfoId,String total);
    
    ModelMsg submitMaOrderInfo(MaOrderInfo maOrderInfo);
}
