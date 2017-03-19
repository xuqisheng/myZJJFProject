package com.zjjf.analysis.services.store;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.supplier.SpGroupDailyReport;
import com.zjjf.analysis.mapper.analysis.SpGroupDailyReportMapper;
import com.zjjf.analysis.mapper.origin.RegionMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.producer.store.IStoreDailyReportService;
import com.zjjf.analysis.services.AbstractModelService;
import com.zjjf.analysis.utils.DateUtils;
import com.zjjf.analysis.utils.StringUtil;

@Service(version = "1.0.0")
public class StoreDailyReportServiceImpl extends AbstractModelService<SpGroupDailyReport> implements IStoreDailyReportService<SpGroupDailyReport> {

	@Autowired
	private SpGroupDailyReportMapper spGroupDailyReportMapper;

	@Autowired
	private RegionMapper regionMapper;

	@Autowired
	private SpGroupMapper spGroupMapper;

	@Override
	public List<SpGroupDailyReport> getDataList(HashMap<String, Object> paramMap) {

		return spGroupDailyReportMapper.getReportData(this.appendParam(paramMap));
	}

	@Override
	public Integer getDataCount(HashMap<String, Object> paramMap) {

		return spGroupDailyReportMapper.getReportDataCount(this.appendParam(paramMap));
	}

	@Override
	public List<SpGroupDailyReport> getDataExcel(HashMap<String, Object> paramMap) {

		return spGroupDailyReportMapper.getReportDataExcel(this.appendParam(paramMap));
	}

	public HashMap<String, Object> getBannerData(HashMap<String, Object> paramMap) {

		HashMap<String, Object> vo = new HashMap<String, Object>();
		String dayTime = paramMap.get("addTime") + "";
		HashMap<String, Object> monthMap = new HashMap<String, Object>();
		monthMap.put("dayTime", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(dayTime));
		monthMap.put("spGroupId", paramMap.get("spGroupId"));
		SpGroupDailyReport monthReporty = spGroupDailyReportMapper.getDataSum(monthMap);
		if (monthReporty != null) {
			vo.put("todayAddReg", monthReporty.getTodayAddReg());
			vo.put("yestodayAddReg", monthReporty.getYestodayAddReg());
			vo.put("monthReg", monthReporty.getMonthReg());
			vo.put("yesTodayAddActive", monthReporty.getYestodayAddActive());
			vo.put("todayAddActive", monthReporty.getTodayAddActive());
			vo.put("monthAddActive", monthReporty.getMonthAddActive());
			vo.put("totalStore", monthReporty.getTotalStore());
			vo.put("unorderStore", monthReporty.getUnorderStore());
		}
		return vo;
	}

	@Override
	public void adaptConvert(SpGroupDailyReport t, HashMap<String, Object> paramMap) {

		t.setCityName(regionMapper.getById(t.getCityId()).getName());
		t.setAreaName(regionMapper.getById(t.getAreaId()).getName());
		t.setSpGroupName(spGroupMapper.getById(t.getSpGroupId()).getName());
		t.setMonthRegIncrease(t.getMonthRegIncrease() + "%");
		t.setMonthActiveIncrease(t.getMonthActiveIncrease() + "%");
	}

	@Override
	public HashMap<String, Object> appendParam(HashMap<String, Object> map) {

		String addTime = map.get("addTime") + "";
		if (StringUtil.isNotNullAndEmpty(addTime)) {
			map.put("dayTime", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(addTime));
		}
		return map;
	}
}
