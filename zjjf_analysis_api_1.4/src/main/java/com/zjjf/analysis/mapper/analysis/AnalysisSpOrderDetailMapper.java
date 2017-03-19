package com.zjjf.analysis.mapper.analysis;

import java.util.List;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderDetail;
import com.zjjf.analysis.beans.analysis.orders.SpOrderDetailVo;
import com.zjjf.analysis.mapper.IMapper;

public interface AnalysisSpOrderDetailMapper extends IMapper<SpOrderDetailVo> {

	List<AnalysisSpOrderDetail> getItemByOrderId(String orderId);
}