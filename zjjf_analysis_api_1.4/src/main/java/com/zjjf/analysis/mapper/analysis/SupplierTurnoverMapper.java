package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.vo.supplier.SupplierFreeVo;

public interface SupplierTurnoverMapper {

	List<SupplierFreeVo> getDataByParam(HashMap<String, Object> paramMap);

}