package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.TurnoverPage;
import com.zjjf.analysis.beans.vo.orders.sporder.SpOrderTurnoverVo;

public interface TurnoverPageMapper {

	List<TurnoverPage> getTurnoverData(HashMap<String, Object> paramMap);

	List<TurnoverPage> getTurnoverDataExcel(HashMap<String, Object> paramMap);

	Integer getTurnoverDataCount(HashMap<String, Object> paramMap);

	SpOrderTurnoverVo getTurnoverByTime(HashMap<String, Object> paramMap);
	
	SpOrderTurnoverVo getTurnoverBySpIdSpGroupId(HashMap<String, Object> paramMap);
}