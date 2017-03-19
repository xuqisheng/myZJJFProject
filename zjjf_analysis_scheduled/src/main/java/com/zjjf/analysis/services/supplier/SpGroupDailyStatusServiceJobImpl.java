package com.zjjf.analysis.services.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.supplier.SpGroupDaily;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.mapper.analysis.SpGroupDailyMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class SpGroupDailyStatusServiceJobImpl extends AbstractTimeService<SpGroupDaily, SpGroupDaily> {

	@Autowired
	private SpGroupDailyMapper spGroupDailyMapper;

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Override
	public List<SpGroupDaily> getDataByTime(String dayTime, Integer i) {

		return spGroupDailyMapper.getSpGroupList((i - 1) * 1000, dayTime);
	}

	@Override
	public void _process(SpGroupDaily t, SpGroupDaily v, String dayTime) {

		this.appendParam(t.getId(), t.getSpGroupId(), v, dayTime);
	}

	private void appendParam(Integer id, Integer spGroupId, SpGroupDaily v, String dayTime) {

		SpGroupDaily temp = analysisSpOrderInfoMapper.getBySpGroupIdAndAddTime(spGroupId, dayTime);
		if (temp != null && temp.getSpGroupId() != 0) {
			spGroupDailyMapper.updateTurnover(id, temp.getTurnover());
		}
	}
}
