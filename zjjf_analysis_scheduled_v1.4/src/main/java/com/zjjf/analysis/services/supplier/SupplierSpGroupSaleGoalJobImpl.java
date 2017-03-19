package com.zjjf.analysis.services.supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.supplier.GoalSet;
import com.zjjf.analysis.beans.analysis.supplier.SupplierSpGroupIdChangeLog;
import com.zjjf.analysis.beans.analysis.supplier.SupplierSpGroupSaleGoal;
import com.zjjf.analysis.mapper.analysis.GoalSetMapper;
import com.zjjf.analysis.mapper.analysis.SupplierSpGroupIdChangeLogMapper;
import com.zjjf.analysis.mapper.analysis.SupplierSpGroupSaleGoalMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class SupplierSpGroupSaleGoalJobImpl extends AbstractTimeService<SupplierSpGroupIdChangeLog, SupplierSpGroupSaleGoal>{

	@Autowired
	private SupplierSpGroupSaleGoalMapper supplierSpGroupSaleGoalMapper;
	
	@Autowired
	private SupplierSpGroupIdChangeLogMapper supplierSpGroupIdChangeLogMapper;
	
	@Autowired
	private GoalSetMapper goalSetMapper;
	
	@Override
	public List<? extends SupplierSpGroupIdChangeLog> getDataByTime(
			String yyyyMMdd, Integer offset) {
		//查询目标设置表 --当月是否设置过目标
		if (Boolean.valueOf(this.isSettedGoal(yyyyMMdd).get("flag")+"")) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("offset", (offset-1)*1000);
			return supplierSpGroupIdChangeLogMapper.query(paramMap);
		} else {
			return new ArrayList<SupplierSpGroupIdChangeLog>();
		}
	}

	@Override
	public void _process(SupplierSpGroupIdChangeLog t,
			SupplierSpGroupSaleGoal v, String dayTime) {
		
		String dayTime2 = this.isSettedGoal(dayTime).get("dayTime") + "";
		Integer settedTime = Integer.valueOf(dayTime2.toString());
		//有记录--nothing,无--insert + 时间判断
		String supplierId = t.getSupplierId();
		Integer spGroupId = t.getSpGroupId();
		List<SupplierSpGroupSaleGoal> saleGoalList = supplierSpGroupSaleGoalMapper.getBySpIdAndSpGroupIdAndDayTime(supplierId, spGroupId, settedTime);
		
		if ((saleGoalList == null || saleGoalList.size() == 0)) {
			
			v.setCityId(t.getCityId());
			v.setAreaId(t.getAreaId());
			v.setDayTime(settedTime);
			v.setSupplierId(t.getSupplierId());
			v.setSpGroupId(t.getSpGroupId());
			v.setCreateTime((int) (System.currentTimeMillis()/1000));
			v.setMonthGoal(0);
			v.setDailyGoal(0);
			supplierSpGroupSaleGoalMapper.insert(v);
		} else {
		}
	}
	
	public HashMap<String, Object> isSettedGoal(String dayTime) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Boolean isSetted = false;
		Integer jobYear = Integer.valueOf(dayTime.substring(0, 4));
		Integer jobMonth = Integer.valueOf(dayTime.substring(4, 6));
		Integer jobDay = Integer.valueOf(dayTime.substring(6, 8));
		if (jobDay >= 29) {
			jobMonth++;
			if (jobMonth == 12) {
				jobYear++;
			}
		}
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("year", jobYear);
		paramMap.put("month", jobMonth);
		List<GoalSet> goalSetList = goalSetMapper.selectByMap(paramMap);
		String month = "";
		StringBuilder dayTime2 = new StringBuilder();
		if (goalSetList != null && goalSetList.size() != 0) {
			isSetted = true;
			if (jobMonth < 10) {
				month = "0" + jobMonth;
			}
			dayTime2 = new StringBuilder(jobYear.toString() + month);
			map.put("flag", isSetted);
			map.put("dayTime", dayTime2.toString());
		}
		
		return map;
	}

}
