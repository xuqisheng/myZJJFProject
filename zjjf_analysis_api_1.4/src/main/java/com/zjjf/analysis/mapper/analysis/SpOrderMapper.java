package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zjjf.analysis.beans.vo.orders.sporder.SpOrderVo;
import com.zjjf.analysis.mapper.IMapper;

public interface SpOrderMapper extends IMapper<SpOrderVo> {
	
	public List<Map<String, String>> getOrderListByMap(HashMap<String, Object> paramMap);
	
}