package com.zjjf.analysis.mapper.analysis;

import com.zjjf.analysis.beans.vo.supplier.SupplierFreeVo;

import java.util.HashMap;
import java.util.List;

public interface SupplierTurnoverMapper {

	List<SupplierFreeVo> getDataByParam(HashMap<String, Object> paramMap);

}