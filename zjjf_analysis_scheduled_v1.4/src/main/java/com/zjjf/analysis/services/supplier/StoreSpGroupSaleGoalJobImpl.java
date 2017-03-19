package com.zjjf.analysis.services.supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.supplier.GoalSet;
import com.zjjf.analysis.beans.analysis.store.StoreSpGroupSaleGoal;
import com.zjjf.analysis.beans.origin.supplier.SpGroup;
import com.zjjf.analysis.mapper.analysis.GoalSetMapper;
import com.zjjf.analysis.mapper.analysis.StoreSpGroupSaleGoalMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class StoreSpGroupSaleGoalJobImpl extends AbstractTimeService<SpGroup, StoreSpGroupSaleGoal>{

	@Autowired
	private StoreSpGroupSaleGoalMapper storeSpGroupSaleGoalMapper;
	
	@Autowired
	private SpGroupMapper spGroupMapper;
	
	@Autowired
	private GoalSetMapper goalSetMapper;
	
	@Override
	public List<? extends SpGroup> getDataByTime(String yyyyMMdd, Integer offset) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("offset", (offset-1)*1000);
		//判断是否添加过月目标201608
		if (Boolean.valueOf(this.isSettedGoal(yyyyMMdd).get("flag") + "")) {
			return spGroupMapper.query(paramMap);
		} else {
			return new ArrayList<SpGroup>();
		}
	}

	@Override
	public void _process(SpGroup t, StoreSpGroupSaleGoal v, String dayTime) {
		
		String dayTime2 = this.isSettedGoal(dayTime).get("dayTime") + "";
		Integer settedTime = Integer.valueOf(dayTime2.toString());
		
		List<StoreSpGroupSaleGoal> storeGoalList = List_isExit(t.getId(), settedTime);
		if (storeGoalList == null) {
			throw new RuntimeException("storeGoalList:null,查询storeGoal失败" + t.getId() + ", " + settedTime);
		} else if (storeGoalList.size() == 0) {
			v.setCityId(t.getCityId());
			v.setAreaId(t.getAreaId());
			v.setSpGroupId(t.getId());
			v.setCreateTime((int) (System.currentTimeMillis()/1000));
			v.setDayTime(settedTime);
			v.setNewRisteredGole(0);
			v.setActiveStoreGole(0);
			v.setNewRisteredGoleDaily(0);
			v.setActiveStoreGoleDaily(0);
			storeSpGroupSaleGoalMapper.insert(v);
		} else {
			return;
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
	
	private List<StoreSpGroupSaleGoal> List_isExit(Integer spGroupId, Integer dayTime) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("spGroupId", spGroupId);
		paramMap.put("dayTime", dayTime);
		return storeSpGroupSaleGoalMapper.queryByMap(paramMap);
	}
}
