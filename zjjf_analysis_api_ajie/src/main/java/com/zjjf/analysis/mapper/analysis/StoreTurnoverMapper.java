package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.store.StoreTurnover;
import com.zjjf.analysis.beans.analysis.store.StoreTurnoverVo;
import com.zjjf.analysis.mapper.IMapper;

public interface StoreTurnoverMapper extends IMapper<StoreTurnoverVo> {

	List<StoreTurnover> getDataByParam(HashMap<String, Object> paramMap);

	List<StoreTurnoverVo> getTurnoverBySpGoupAndStore(HashMap<String, Object> paramMap);

	void updateIsVisitByStoreCode(StoreTurnover vo);
}