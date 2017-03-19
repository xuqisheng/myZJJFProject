package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.orders.SpOrderDetail;
import com.zjjf.analysis.beans.vo.orders.scorder.ScOrderItemVo;
import com.zjjf.analysis.mapper.IMapper;

public interface SpOrderDetailMapper extends IMapper<SpOrderDetail> {

	List<ScOrderItemVo> getOrderItemList(HashMap<String, Object> paramMap);

	List<ScOrderItemVo> getOrderItemListExcel(HashMap<String, Object> paramMap);

	List<SpOrderDetail> getItemByOrderId(String orderId);
	
	Integer getItemTotalCount(String orderId);

}