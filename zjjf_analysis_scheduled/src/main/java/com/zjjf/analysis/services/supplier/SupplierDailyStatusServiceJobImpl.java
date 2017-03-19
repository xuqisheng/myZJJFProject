package com.zjjf.analysis.services.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.supplier.SupplierDaily;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.mapper.analysis.SupplierDailyMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class SupplierDailyStatusServiceJobImpl extends AbstractTimeService<SupplierDaily, SupplierDaily> {

	@Autowired
	SupplierDailyMapper supplierDailyMapper;

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Override
	public List<SupplierDaily> getDataByTime(String dayTime, Integer i) {

		return supplierDailyMapper.getSupplierList((i - 1) * 1000, dayTime);
	}

	@Override
	public void _process(SupplierDaily t, SupplierDaily v, String dayTime) {

		SupplierDaily temp = analysisSpOrderInfoMapper.getBySupplierIdSpGroupIdAndAddTime(t.getSpGroupId(), t.getSupplierId(), dayTime);
		if (temp != null && temp.getSupplierId() != null) {
			supplierDailyMapper.updateTurnover(t.getId(), temp.getTurnover());
		}
	}
}
