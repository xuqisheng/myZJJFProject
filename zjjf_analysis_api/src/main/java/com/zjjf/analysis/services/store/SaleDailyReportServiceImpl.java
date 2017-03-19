package com.zjjf.analysis.services.store;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.supplier.SaleDailyReport;
import com.zjjf.analysis.beans.analysis.supplier.Supplier;
import com.zjjf.analysis.mapper.analysis.SaleDailyReportMapper;
import com.zjjf.analysis.mapper.origin.RegionMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.producer.store.ISaleDailyReportService;
import com.zjjf.analysis.services.AbstractModelService;
import com.zjjf.analysis.utils.DateUtils;

@Service(version = "1.0.0")
public class SaleDailyReportServiceImpl extends AbstractModelService<SaleDailyReport> implements ISaleDailyReportService<SaleDailyReport> {

	@Autowired
	private SaleDailyReportMapper saleDailyReportMapper;

	@Autowired
	private RegionMapper regionMapper;

	@Autowired
	private SpGroupMapper spGroupMapper;

	@Autowired
	private SupplierMapper supplierMapper;

	@Override
	public List<SaleDailyReport> getDataList(HashMap<String, Object> paramMap) {

		return saleDailyReportMapper.getData(this.appendParam(paramMap));
	}

	@Override
	public Integer getDataCount(HashMap<String, Object> paramMap) {

		return saleDailyReportMapper.getDataCount(this.appendParam(paramMap));
	}

	@Override
	public List<SaleDailyReport> getDataExcel(HashMap<String, Object> paramMap) {

		return saleDailyReportMapper.getDataExcel(this.appendParam(paramMap));
	}

	@Override
	public HashMap<String, Object> appendParam(HashMap<String, Object> map) {

		String addTime = map.get("addTime") + "";
		String dayTime = DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(addTime);
		map.put("dayTime", dayTime);
		if (map.containsKey("supplierName") && map.get("supplierName") != null) {
			List<String> supplierIdList = supplierMapper.getByName(map.get("supplierName") + "").stream().map(s -> s.getId()).collect(Collectors.toList());
			if (supplierIdList != null && supplierIdList.size() > 0) {
				map.put("supplierIdList", supplierIdList);
			}
		}
		return map;
	}

	@Override
	public void adaptConvert(SaleDailyReport t, HashMap<String, Object> paramMap) {

		t.setCityName(regionMapper.getById(t.getCityId()).getName());
		t.setAreaName(regionMapper.getById(t.getAreaId()).getName());
		t.setSpGroupName(spGroupMapper.getById(t.getSpGroupId()).getName());
		Supplier supplier = supplierMapper.getById(t.getSupplierId());
		if (supplier != null) {
			t.setSupplierName(supplier.getSupplierName());
		}
		t.setRateMonthComp(t.getRateMonthComp() + "%");
		t.setRateDailyComp(t.getRateDailyComp() + "%");
		t.setRateFee(t.getRateFee() + "%");
		t.setRateFeeMonth(t.getRateFeeMonth() + "%");
	}

}
