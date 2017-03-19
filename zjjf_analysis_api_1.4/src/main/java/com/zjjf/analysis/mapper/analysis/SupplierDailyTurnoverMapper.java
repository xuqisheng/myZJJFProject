package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.vo.orders.sporder.SpOrderTurnoverVo;

public interface SupplierDailyTurnoverMapper {

	List<SpOrderTurnoverVo> getTurnoverData(HashMap<String, Object> paramMap);

	Integer getTurnoverDataCount(HashMap<String, Object> paramMap);

	List<SpOrderTurnoverVo> getTurnoverExcelData(HashMap<String, Object> paramMap);

	SpOrderTurnoverVo getDataBySupplierIdAndTime(HashMap<String, Object> paramMap);
}