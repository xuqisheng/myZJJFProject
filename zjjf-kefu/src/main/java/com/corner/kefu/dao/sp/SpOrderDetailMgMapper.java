package com.corner.kefu.dao.sp;

import java.util.List;

import com.corner.core.beans.SpOrderDetail;
import com.corner.kefu.beans.ro.SpOrderInfoRo;
import com.corner.kefu.beans.vo.sp.SpOrderInfoVo;


public interface SpOrderDetailMgMapper {
	public List<SpOrderDetail> getOrderDetailByOrderId(String orderId);

}
