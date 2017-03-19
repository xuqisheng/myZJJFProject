package com.zjjf.analysis.services.suppliser;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.SpGroup;
import com.zjjf.analysis.beans.analysis.store.StoreDailyVo;
import com.zjjf.analysis.beans.analysis.supplier.StoreAnalysis;
import com.zjjf.analysis.mapper.analysis.SpGroupDailyMapper;
import com.zjjf.analysis.mapper.analysis.StoreDailyMapper;
import com.zjjf.analysis.mapper.origin.RegionMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.producer.supplier.IAnaSpGroupService;
import com.zjjf.analysis.services.AbstractModelService;
import com.zjjf.analysis.utils.DateUtils;
import com.zjjf.analysis.utils.StringUtil;

@Service(version = "1.0.0")
public class AnaSpGroupServiceImpl extends AbstractModelService<StoreAnalysis> implements IAnaSpGroupService<StoreAnalysis> {

	@Autowired
	private SpGroupDailyMapper spGroupDailyMapper;

	@Autowired
	private StoreDailyMapper storeDailyMapper;

	@Autowired
	private RegionMapper regionMapper;

	@Autowired
	private SpGroupMapper spGroupMapper;

	@Override
	public List<StoreAnalysis> getDataList(HashMap<String, Object> paramMap) {

		return spGroupDailyMapper.getData(this.appendParam(paramMap));
	}

	@Override
	public Integer getDataCount(HashMap<String, Object> paramMap) {

		return spGroupDailyMapper.getDataCount(this.appendParam(paramMap));
	}

	@Override
	public List<StoreAnalysis> getDataExcel(HashMap<String, Object> paramMap) {

		return spGroupDailyMapper.getDataExcel(this.appendParam(paramMap));
	}

	@Override
	public void adaptConvert(StoreAnalysis t, HashMap<String, Object> paramMap) {

		int day = 1;
		try {
			day = DateUtils.daysBetween(paramMap.get("addTimeBegin") + "", paramMap.get("addTimeEnd") + "") + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		t.setCityName(regionMapper.getById(t.getCityId()).getName());
		t.setAreaName(regionMapper.getById(t.getAreaId()).getName());
		SpGroup spGroup = spGroupMapper.getById(t.getSpGroupId());
		t.setSpGroupName(spGroup != null ? spGroup.getName() : null);
		t.setTurnoverDaily(t.getTurnover().divide(new BigDecimal(day), 2, BigDecimal.ROUND_HALF_EVEN));
		t.setOrderCountDaily(new BigDecimal(t.getOrderCount()).divide(new BigDecimal(day), 2, BigDecimal.ROUND_HALF_EVEN));
		turnover_ordercount_distributed(t, paramMap);
		if(t.getTotalTurnoverTimes() != 0){
			t.setAvgOrderCount(new BigDecimal(t.getOrderCount()).divide(new BigDecimal(t.getTotalTurnoverTimes()), 2, BigDecimal.ROUND_HALF_EVEN));
			t.setAvgTurnover(t.getTurnover().divide(new BigDecimal(t.getTotalTurnoverTimes()), 2, BigDecimal.ROUND_HALF_EVEN));
		}else{
			t.setAvgOrderCount(new BigDecimal(0));
			t.setAvgTurnover(new BigDecimal(0));
		}
	}

	@Override
	public HashMap<String, Object> appendParam(HashMap<String, Object> map) {

		String addTimeBegin = map.get("addTimeBegin") + "";
		String addTimeEnd = map.get("addTimeEnd") + "";
		if (StringUtil.isNotNullAndEmpty(addTimeBegin)) {
			map.put("dayTimeBegin", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(addTimeBegin));
		}
		if (StringUtil.isNotNullAndEmpty(addTimeEnd)) {
			map.put("dayTimeEnd", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(addTimeEnd));
		}
		return map;
	}

	private void turnover_ordercount_distributed(StoreAnalysis t, HashMap<String, Object> paramMap) {

		paramMap.put("spGroupId", t.getSpGroupId());
		List<StoreDailyVo> turnoverList = storeDailyMapper.getTurnoverDistributed(paramMap);
		if (turnoverList != null && turnoverList.size() > 0) {
			t.setCol1TurnoverTimes(turnoverList.get(0).getCol1TurnoverTimes());
			t.setCol2TurnoverTimes(turnoverList.get(1).getCol1TurnoverTimes());
			t.setCol3TurnoverTimes(turnoverList.get(2).getCol1TurnoverTimes());
			t.setCol4TurnoverTimes(turnoverList.get(3).getCol1TurnoverTimes());
			t.setCol5TurnoverTimes(turnoverList.get(4).getCol1TurnoverTimes());
			t.setCol6TurnoverTimes(turnoverList.get(5).getCol1TurnoverTimes());
			t.setTotalCountTimes(turnoverList.get(0).getCol1TurnoverTimes() + turnoverList.get(1).getCol1TurnoverTimes()
					+ turnoverList.get(2).getCol1TurnoverTimes() + turnoverList.get(3).getCol1TurnoverTimes()
					+ turnoverList.get(4).getCol1TurnoverTimes() + turnoverList.get(5).getCol1TurnoverTimes());
		}
		List<StoreDailyVo> orderCountList = storeDailyMapper.getOrdercountDistributed(paramMap);
		if (orderCountList != null && orderCountList.size() > 0) {
			t.setCol1OrderCountTimes(orderCountList.get(0).getCol1OrderCountTimes());
			t.setCol2OrderCountTimes(orderCountList.get(1).getCol1OrderCountTimes());
			t.setCol3OrderCountTimes(orderCountList.get(2).getCol1OrderCountTimes());
			t.setCol4OrderCountTimes(orderCountList.get(3).getCol1OrderCountTimes());
			t.setTotalTurnoverTimes(orderCountList.get(0).getCol1OrderCountTimes() + orderCountList.get(1).getCol1OrderCountTimes()
					+ orderCountList.get(2).getCol1OrderCountTimes() + orderCountList.get(3).getCol1OrderCountTimes());
		}
	}

}
