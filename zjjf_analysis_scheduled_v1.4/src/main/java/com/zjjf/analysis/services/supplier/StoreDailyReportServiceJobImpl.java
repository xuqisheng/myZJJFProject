package com.zjjf.analysis.services.supplier;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.store.StoreSpGroupSaleGoal;
import com.zjjf.analysis.beans.analysis.supplier.SpGroupDaily;
import com.zjjf.analysis.beans.analysis.supplier.SpgroupDailyReport;
import com.zjjf.analysis.beans.origin.supplier.SpGroup;
import com.zjjf.analysis.common.utils.DateUtil;
import com.zjjf.analysis.common.utils.NumberUtils;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.mapper.analysis.SpGroupDailyMapper;
import com.zjjf.analysis.mapper.analysis.SpgroupDailyReportMapper;
import com.zjjf.analysis.mapper.analysis.StoreSpGroupSaleGoalMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.mapper.origin.StoreMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class StoreDailyReportServiceJobImpl extends AbstractTimeService<SpGroup, SpgroupDailyReport> {

	@Autowired
	private SpGroupDailyMapper spGroupDailyMapper;

	@Autowired
	private SpgroupDailyReportMapper spgroupDailyReportMapper;

	@Autowired
	private SpGroupMapper spGroupMapper;

	@Autowired
	private StoreSpGroupSaleGoalMapper storeSpgroupSaleGoalMapper;

	@Autowired
	private StoreMapper storeMapper;

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Override
	public List<SpGroup> getDataByTime(String dayTime, Integer i) {

		return spGroupMapper.getAll(dayTime, (i - 1) * 1000);
	}

	@Override
	public void _process(SpGroup spGroup, SpgroupDailyReport v, String dayTime) {

		this.appendParam(spGroup, v, dayTime);
	}

	private void appendParam(SpGroup t, SpgroupDailyReport v, String dayTime) {

		SpgroupDailyReport exitBean = spgroupDailyReportMapper.getExitRecord(t.getId(), dayTime);
		if (exitBean != null && exitBean.getSpGroupId() != 0) {
			update_report(t, exitBean.getId(), t.getId(), dayTime);
		} else {
			this.add_bean(v, t, dayTime);
		}
	}

	private void add_bean(SpgroupDailyReport v, SpGroup t, String dayTime) {

		Integer spGroupId = t.getId();
		Integer cityId = t.getCityId();
		Integer areaId = t.getAreaId();

		v.setCityId(cityId);
		v.setAreaId(areaId);
		v.setSpGroupId(spGroupId);
		v.setDayTime(Integer.valueOf(dayTime));
		SpGroupDaily yestodayDaily = spGroupDailyMapper.getYesTodayData(DateUtil.getPreDay(dayTime, "yyyyMMdd"), spGroupId);
		if (yestodayDaily != null) {
			// 昨日小计
			v.setYestodayStoreOrderCount(yestodayDaily.getStoreOrderCount());
			// 昨日维护
			v.setYestodayMaintain(yestodayDaily.getStoreOrderCount() - yestodayDaily.getNewRegStoreOrdercount());
			// 昨日新增活跃
			v.setYestodayAddActive(yestodayDaily.getNewRegStoreOrdercount());
		}
		// 昨日新增注册终端
		v.setYestodayAddReg(storeMapper.getBySpGroupIdAndTime(spGroupId, DateUtil.getPreDay(dayTime, "yyyyMMdd")));
		
		change_by_real_time(v, t, spGroupId, dayTime);
		v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));
		spgroupDailyReportMapper.insert(v);
	}

	// 本月新增注册
	private Integer add_month_store(Integer spGroupId, String dayTime) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("spGroupId", spGroupId);
		map.put("dayTimeBegin", DateUtil.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", 1));
		map.put("dayTimeEnd", dayTime);
		Integer total = storeMapper.getBySpGroupIdMonth(spGroupId, DateUtil.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", 1), dayTime);
		return total == null ? 0 : total;
	}

	private Integer getAlreadyOrderCount(Integer spGroupId, String dayTime) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("spGroupId", spGroupId);
		map.put("dayTimeEnd", dayTime);
		List<Integer> storeIdList = storeMapper.getBySpGroupIdBeforeTime(map);
		map.put("storeIdList", storeIdList);
		if (storeIdList.isEmpty()) {
			return 0;
		}
		return analysisSpOrderInfoMapper.getByStoreIdBeforeTime(map);
	}

	private void update_report(SpGroup t, Integer spGroupDailyId, Integer spGroupId, String dayTime) {

		SpgroupDailyReport v = new SpgroupDailyReport();
		change_by_real_time(v, t, spGroupId, dayTime);
		v.setId(spGroupDailyId);
		spgroupDailyReportMapper.update(v);
	}
	
	private void change_by_real_time(SpgroupDailyReport v, SpGroup t, Integer spGroupId, String dayTime) {

		SpGroupDaily temp = spGroupDailyMapper.getBySpGroupIdAndDayTime(spGroupId, dayTime);
		if (temp != null) {
			v.setTodayAddActive(temp.getNewRegStoreOrdercount());
			v.setStoreOrderCount(temp.getStoreOrderCount());
			v.setMonthActive(temp.getMonthActive());
			v.setTodayMaintain(temp.getStoreOrderCount() - temp.getNewRegStoreOrdercount());
			v.setMonthAddActive(temp.getMonthAddActive());
		}
		v.setTotalStore(storeMapper.getBySpGroupIdAndMonthTime(spGroupId, dayTime));
		v.setUnorderStore(v.getTotalStore() - getAlreadyOrderCount(spGroupId, dayTime));
		v.setTodayAddReg(storeMapper.getBySpGroupIdAndTime(spGroupId, dayTime));
		v.setMonthReg(add_month_store(spGroupId, dayTime));
		StoreSpGroupSaleGoal monthGoal = storeSpgroupSaleGoalMapper.getMonthGoal(spGroupId, dayTime.substring(0, 6));
		if (monthGoal != null) {
			v.setMonthGoalActive(monthGoal.getActiveStoreGole());
			v.setMonthGoalReg(monthGoal.getNewRisteredGole());
		}
		if (monthGoal == null) {
			v.setMonthRegIncrease(new BigDecimal(0));
			v.setMonthActiveIncrease(new BigDecimal(0));
		} else {
			v.setMonthRegIncrease(NumberUtils.getIncrease(new BigDecimal(v.getMonthReg()), new BigDecimal(monthGoal.getNewRisteredGole())));
			v.setMonthActiveIncrease(NumberUtils.getIncrease(new BigDecimal(v.getMonthActive()), new BigDecimal(monthGoal.getActiveStoreGole())));
		}
	}
}
