package com.zjjf.analysis.services.supplier;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.supplier.SaleDailyReport;
import com.zjjf.analysis.beans.analysis.supplier.SupplierDaily;
import com.zjjf.analysis.beans.analysis.supplier.SupplierSpGroupIdChangeLog;
import com.zjjf.analysis.beans.analysis.supplier.SupplierSpGroupSaleGoal;
import com.zjjf.analysis.beans.origin.supplier.SpGroup;
import com.zjjf.analysis.common.utils.DateUtil;
import com.zjjf.analysis.common.utils.NumberUtils;
import com.zjjf.analysis.mapper.analysis.SaleDailyReportMapper;
import com.zjjf.analysis.mapper.analysis.SupplierDailyMapper;
import com.zjjf.analysis.mapper.analysis.SupplierSpGroupIdChangeLogMapper;
import com.zjjf.analysis.mapper.analysis.SupplierSpGroupSaleGoalMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class SaleDailyReportServiceJobImpl extends AbstractTimeService<SupplierSpGroupIdChangeLog, SaleDailyReport> {

	@Autowired
	private SaleDailyReportMapper saleDailyReportMapper;

	@Autowired
	private SupplierDailyMapper supplierDailyMapper;

	@Autowired
	private SupplierSpGroupIdChangeLogMapper supplierSpGroupIdChangeLogMapper;

	@Autowired
	private SpGroupMapper spGroupMapper;

	@Autowired
	private SupplierSpGroupSaleGoalMapper supplierSpGroupSaleGoalMapper;

	@Override
	public List<SupplierSpGroupIdChangeLog> getDataByTime(String dayTime, Integer i) {

		return supplierSpGroupIdChangeLogMapper.getAll(dayTime, (i - 1) * 1000);
	}

	@Override
	public void _process(SupplierSpGroupIdChangeLog t, SaleDailyReport v, String dayTime) {

		this.appendParam(t, v, dayTime);
	}

	private void appendParam(SupplierSpGroupIdChangeLog t, SaleDailyReport v, String dayTime) {

		String supplierId = t.getSupplierId();
		Integer spGroupId = t.getSpGroupId();
		SaleDailyReport exitBean = saleDailyReportMapper.getExitRecord(supplierId, spGroupId, dayTime);
		if (exitBean != null && exitBean.getSpGroupId() != 0) {
			update_report(exitBean.getId(), spGroupId, supplierId, dayTime);
		} else {
			this.add_bean(v, spGroupId, supplierId, dayTime);
		}
	}

	private void add_bean(SaleDailyReport v, Integer spGroupId, String supplierId, String dayTime) {

		SpGroup spGroup = spGroupMapper.getById(spGroupId);
		if (spGroup != null) {
			v.setCityId(spGroup.getCityId());
			v.setAreaId(spGroup.getAreaId());
		}
		v.setSpGroupId(spGroupId);
		v.setSupplierId(supplierId);
		v.setDayTime(Integer.valueOf(dayTime));
		String yestoday = DateUtil.getPreDay(dayTime, "yyyyMMdd");
		SupplierDaily yestodayDaily = supplierDailyMapper.getBySpGroupId_supplierId_dayTime(spGroupId, supplierId, yestoday);
		if (yestodayDaily != null) {
			v.setYesTodayturnover(yestodayDaily.getTurnover());
		}
		change_by_todayData(v, spGroupId, supplierId, dayTime);
		v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));
		saleDailyReportMapper.insert(v);
	}

	private void update_report(Integer id, Integer spGroupId, String supplierId, String dayTime) {

		SaleDailyReport v = new SaleDailyReport();
		change_by_todayData(v, spGroupId, supplierId, dayTime);
		v.setId(id);
		saleDailyReportMapper.update(v);
	}

	private void change_by_todayData(SaleDailyReport v, Integer spGroupId, String supplierId, String dayTime) {

		SupplierDaily temp = supplierDailyMapper.getBySpGroupId_supplierId_dayTime(spGroupId, supplierId, dayTime);
		if (temp != null) {
			v.setTurnover(temp.getTurnover());
			v.setFee(temp.getTotalCoupon().add(temp.getTotalFreight()).add(temp.getTotalRebate()));
		}
		SupplierSpGroupSaleGoal monthGoal = supplierSpGroupSaleGoalMapper.getGoal(spGroupId, supplierId, dayTime.substring(0, 6));
		if (monthGoal != null) {
			v.setTodayGoal(monthGoal.getDailyGoal());
			v.setGoalMonth(monthGoal.getMonthGoal());
		}
		SupplierDaily monthBean = supplierDailyMapper.getMonthData(spGroupId, supplierId, DateUtil.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", 1), dayTime);
		if (monthBean != null && monthBean.getId() != 0) {
			v.setSumMonth(monthBean.getTurnover());
			v.setTotalFee(monthBean.getTotalFree());
			v.setRateFeeMonth(NumberUtils.getIncrease(v.getTotalFee(), v.getSumMonth()));
		}
		v.setRateFee(NumberUtils.getIncrease(v.getFee(), v.getTurnover()));
		v.setRateDailyComp(NumberUtils.getIncrease(v.getTurnover(), new BigDecimal(v.getTodayGoal())));
		v.setRateMonthComp(NumberUtils.getIncrease(v.getSumMonth(), new BigDecimal(v.getGoalMonth())));
	}
}
