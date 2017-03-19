package com.zjjf.analysis.services.orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderInfo;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderDetailMapper;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class SpOrderDetailStatusServiceJobImpl extends AbstractTimeService<AnalysisSpOrderInfo, AnalysisSpOrderInfo> {

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Autowired
	private AnalysisSpOrderDetailMapper analysisSpOrderDetailMapper;

	@Override
	public List<? extends AnalysisSpOrderInfo> getDataByTime(String yyyyMMdd, Integer offset) {

		return analysisSpOrderInfoMapper.getAllStatusList((offset - 1) * 1000, yyyyMMdd);
	}

	@Override
	public void _process(AnalysisSpOrderInfo t, AnalysisSpOrderInfo v, String dayTime) {

		analysisSpOrderDetailMapper.updateStatus(t.getChirdOrderNo(), t.getStatus());
	}

}
