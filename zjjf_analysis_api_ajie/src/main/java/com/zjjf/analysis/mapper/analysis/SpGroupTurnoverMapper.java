package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.supplier.SpGroupTurnover;

public interface SpGroupTurnoverMapper {
	
	List<SpGroupTurnover> getDataByParam(HashMap<String, Object> paramMap);
}