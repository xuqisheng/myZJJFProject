package com.zjjf.analysis.mapper.analysis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderDetail;
import com.zjjf.analysis.beans.analysis.orders.SpOrderDetailVo;
import com.zjjf.analysis.mapper.IMapper;

public interface AnalysisSpOrderDetailMapper extends IMapper<SpOrderDetailVo> {

	List<AnalysisSpOrderDetail> getItemByOrderId(String orderId);

	List<AnalysisSpOrderDetail> getBySupplierIdItemBaseId(@Param("spId") String spId, @Param("itemBaseId") Integer itemBaseId,
			@Param("dayTimeBegin") String dayTimeBegin, @Param("dayTimeEnd") String dayTimeEnd);
}