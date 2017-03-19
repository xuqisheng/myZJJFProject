package com.zjjf.analysis.services.store;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.store.StoreDaily;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.mapper.analysis.StoreDailyMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class StoreDailyStatusServiceJobImpl extends AbstractTimeService<StoreDaily, StoreDaily> {

	@Autowired
	private StoreDailyMapper storeDailyMapper;

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Override
	public List<StoreDaily> getDataByTime(String dayTime_begin, Integer offset) {

		return storeDailyMapper.getStoreList((offset - 1) * 1000, dayTime_begin);
	}

	@Override
	public void _process(StoreDaily t, StoreDaily v, String dayTime) {

		this.appendParam(t, v, dayTime);
	}

	private void appendParam(StoreDaily t, StoreDaily v, String dayTime) {

		Integer storeId = t.getStoreId();
		Integer isZj = t.getIsZj();
		Integer spGroupId = t.getSpGroupId();
		StoreDaily temp = analysisSpOrderInfoMapper.getValidTurnover(storeId, dayTime, spGroupId, isZj);
		if (temp != null && temp.getStoreId() != null) {
			storeDailyMapper.updateTurnover(t.getId(), temp.getTurnover());
		} else {
			storeDailyMapper.updateTurnover(t.getId(), new BigDecimal(0));
		}
	}

}
