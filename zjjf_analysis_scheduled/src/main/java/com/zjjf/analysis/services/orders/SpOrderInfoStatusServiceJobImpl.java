package com.zjjf.analysis.services.orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderInfo;
import com.zjjf.analysis.beans.origin.orders.SpOrderInfo;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.mapper.origin.SpOrderInfoMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class SpOrderInfoStatusServiceJobImpl extends AbstractTimeService<AnalysisSpOrderInfo, AnalysisSpOrderInfo> {

	@Autowired
	private SpOrderInfoMapper spOrderInfoMapper;

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Override
	public List<? extends AnalysisSpOrderInfo> getDataByTime(String yyyyMMdd, Integer offset) {

		return analysisSpOrderInfoMapper.getStatusList((offset - 1) * 1000, yyyyMMdd);
	}

	@Override
	public void _process(AnalysisSpOrderInfo t, AnalysisSpOrderInfo v, String dayTime) {

		String chirdOrderNo = t.getChirdOrderNo();
		SpOrderInfo bean = spOrderInfoMapper.getByOrderId(chirdOrderNo);
		if (bean != null && bean.getOrderId() != null) {
			update_info(chirdOrderNo, t.getId(), bean.getStatus(), bean.getPrintTimes());
		}
	}

	private void update_info(String orderId2, Integer id, Integer status, String printTime) {

		analysisSpOrderInfoMapper.updateStatus(id, printTime, status);
	}
}
