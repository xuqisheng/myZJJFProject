package com.zjjf.analysis.services.store;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderInfo;
import com.zjjf.analysis.beans.analysis.store.StoreKpiDaily;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.mapper.analysis.StoreKpiDailyMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class StoreDailyKpiTurnoverServiceJobImpl extends AbstractTimeService<AnalysisSpOrderInfo, StoreKpiDaily> {

	@Autowired
	private StoreKpiDailyMapper storeKpiDailyMapper;

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Override
	public List<AnalysisSpOrderInfo> getDataByTime(String dayTime_begin, Integer offset) {

		return analysisSpOrderInfoMapper.getStoreIdKpiList((offset - 1) * 1000, dayTime_begin);
	}

	@Override
	public void _process(AnalysisSpOrderInfo t, StoreKpiDaily v, String dayTime) {

		this.appendParam(t, v, dayTime);
	}

	private void appendParam(AnalysisSpOrderInfo t, StoreKpiDaily v, String dayTime) {

		Integer storeId = t.getStoreId();
		Integer isZj = t.getIsZj();
		Integer spGroupId = t.getSpGroupId();
		StoreKpiDaily storeKpiDaily = storeKpiDailyMapper.getExitRecord(storeId, dayTime, spGroupId, isZj);
		if (storeKpiDaily != null && storeKpiDaily.getStoreId() != null) {
			update_plantdaily(storeKpiDaily.getId(), storeId, dayTime, isZj, spGroupId);
		} else {
			add_plantdaily(v, storeId, dayTime, isZj, spGroupId);
		}
	}

	private void update_plantdaily(Integer id, Integer storeId, String dayTime, Integer isZj, Integer spGroupId) {

		StoreKpiDaily temp = analysisSpOrderInfoMapper.getByStoreKpi(storeId, dayTime, spGroupId, isZj);
		storeKpiDailyMapper.updateKpiTurnover(id, temp.getKpiTurnover());
	}

	private void add_plantdaily(StoreKpiDaily v, Integer storeId, String dayTime, Integer isZj, Integer spGroupId) {

		StoreKpiDaily temp = analysisSpOrderInfoMapper.getByStoreKpi(storeId, dayTime, spGroupId, isZj);
		if (temp == null || temp.getStoreId() == null) {
			return;
		}
		v.setStoreId(storeId);
		v.setSpGroupId(spGroupId);
		v.setDayTime(Integer.valueOf(dayTime));
		v.setKpiTurnover(temp.getKpiTurnover());
		v.setIsZj(isZj);
		v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));
		storeKpiDailyMapper.insert(v);
	}
}
