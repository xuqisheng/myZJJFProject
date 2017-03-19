package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zjjf.analysis.beans.analysis.store.StoreDailyVo;
import com.zjjf.analysis.mapper.IMapper;

public interface StoreDailyMapper extends IMapper<StoreDailyVo> {

	List<StoreDailyVo> getTurnoverDistributed(HashMap<String, Object> paramMap);

	List<StoreDailyVo> getOrdercountDistributed(HashMap<String, Object> paramMap);
	
	Map<String,String> getSalemanSummrization(HashMap<String, Object> paramMap);

}