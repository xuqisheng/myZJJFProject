package com.zjjf.analysis.mapper.corner;

import java.util.List;
import java.util.Map;

import com.zjjf.analysis.beans.analysis.corner.ErpManagerItemVo;
import com.zjjf.analysis.beans.analysis.corner.ManagerItemVo;
import com.zjjf.analysis.mapper.IMapper;

public interface ErpManagerItemMapper extends IMapper<ErpManagerItemVo> {

	List<ManagerItemVo> getByMap(Map<String, Object> paramMap);
}