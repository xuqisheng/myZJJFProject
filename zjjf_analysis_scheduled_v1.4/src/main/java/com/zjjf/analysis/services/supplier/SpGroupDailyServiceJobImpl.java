package com.zjjf.analysis.services.supplier;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.supplier.SpGroupDaily;
import com.zjjf.analysis.beans.origin.supplier.SpGroup;
import com.zjjf.analysis.common.utils.DateUtil;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.mapper.analysis.SpGroupDailyMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.mapper.origin.StoreMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class SpGroupDailyServiceJobImpl extends AbstractTimeService<Integer, SpGroupDaily> {

	@Autowired
	private SpGroupDailyMapper spGroupDailyMapper;

	@Autowired
	private SpGroupMapper spGroupMapper;

	@Autowired
	private StoreMapper storeMapper;

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Override
	public List<Integer> getDataByTime(String dayTime, Integer i) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("offset", (i - 1) * 1000);
		paramMap.put("dayTime", dayTime);
		return analysisSpOrderInfoMapper.getSpGroupLogByDayTime(paramMap);
	}

	@Override
	public void _process(Integer spGroupId, SpGroupDaily v, String dayTime) {

		this.appendParam(spGroupId, v, dayTime);
	}

	private void appendParam(Integer spGroupId, SpGroupDaily v, String dayTime) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("spGroupId", spGroupId);
		paramMap.put("dayTime", dayTime);
		SpGroupDaily exitBean = spGroupDailyMapper.getExitRecord(paramMap);
		if (exitBean != null && exitBean.getSpGroupId() != 0) {
			update_plantdaily(exitBean.getId(), spGroupId, paramMap, dayTime);
		} else {
			this.add_spGroup_daily(v, spGroupId, paramMap, dayTime);
		}
	}

	private void add_spGroup_daily(SpGroupDaily v, Integer spGroupId, HashMap<String, Object> paramMap, String dayTime) {

		SpGroupDaily temp = analysisSpOrderInfoMapper.getBySpGroupIdAndAddTime(paramMap);
		if (temp != null && temp.getSpGroupId() != null) {

			v.setSpGroupId(spGroupId);
			v.setDayTime(Integer.valueOf(dayTime));
			v.setTurnover(temp.getTurnover());
			v.setTotalRebate(temp.getTotalRebate());
			v.setTotalFreight(temp.getTotalFreight());

			v.setTotalCoupon(temp.getTotalCoupon());
			v.setOrderCount(temp.getOrderCount());
			v.setSku(temp.getSku());
			v.setQuantity(temp.getQuantity());
			// 下单的终端，称为活跃店。
			v.setStoreOrderCount(temp.getStoreOrderCount());

			v.setNewRegStoreOrdercount(getNewRegStoreOrdercount(spGroupId, dayTime));
			SpGroup spGroup = spGroupMapper.getById(spGroupId);
			if (spGroup != null) {
				v.setCityId(spGroup.getCityId());
				v.setAreaId(spGroup.getAreaId());
			}
			SpGroupDaily monthBean = getMonth(spGroupId, dayTime);
			if (monthBean != null) {
				// 活跃店铺本月累积
				v.setMonthActive(monthBean.getMonthActive());
			}
			// 今日新增活跃终端数
			v.setNewRegStoreOrdercount(getNewRegStoreOrdercount(spGroupId, dayTime));
			// 本月活跃新增
			v.setMonthAddActive(getMonthAddActive(spGroupId, dayTime) + v.getNewRegStoreOrdercount());
			v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));
			spGroupDailyMapper.insert(v);
		}
	}

	private void update_plantdaily(Integer spGroupDailyId, Integer spGroupId, HashMap<String, Object> paramMap, String dayTime) {

		SpGroupDaily temp = analysisSpOrderInfoMapper.getBySpGroupIdAndAddTime(paramMap);
		if (temp != null && temp.getSpGroupId() != 0) {
			SpGroupDaily v = new SpGroupDaily();
			v.setId(spGroupDailyId);
			v.setSpGroupId(spGroupId);
			v.setDayTime(Integer.valueOf(dayTime));
			v.setTurnover(temp.getTurnover());
			v.setTotalRebate(temp.getTotalRebate());
			v.setTotalFreight(temp.getTotalFreight());

			v.setTotalCoupon(temp.getTotalCoupon());
			v.setOrderCount(temp.getOrderCount());
			v.setSku(temp.getSku());
			v.setQuantity(temp.getQuantity());
			// 下单的终端，称为活跃店。
			v.setStoreOrderCount(temp.getStoreOrderCount());
			spGroupDailyMapper.update_daily(v);
		}
	}

	// 本月累积注册店铺
	private Integer getMonthAddActive(Integer spGroupId, String dayTime) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("spGroupId", spGroupId);
		map.put("dayTimeBegin", DateUtil.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", 1));
		map.put("dayTimeEnd", dayTime);
		Integer total = spGroupDailyMapper.getBySpGroupIdAndTime(map);
		return total == null ? 0 : total;
	}

	private SpGroupDaily getMonth(Integer spGroupId, String dayTime) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("spGroupId", spGroupId);
		map.put("dayTimeBegin", DateUtil.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", 1));
		map.put("dayTimeEnd", dayTime);
		return analysisSpOrderInfoMapper.getMonthActiveBySpGroupId(map);
	}

	// 自注册以来，第一次下单的用户
	private int getNewRegStoreOrdercount(Integer spGroupId, String dayTime) {

		return (int) storeMapper.getBySpGroupId(spGroupId).stream().filter(s -> countSpGroupStore(spGroupId, s.getId(), dayTime)).count();
	}

	private boolean countSpGroupStore(Integer spGroupId, Integer storeId, String dayTime) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("storeId", storeId);
		map.put("spGroupId", spGroupId);
		map.put("dayTime", DateUtil.getPreDay(dayTime, "yyyyMMdd"));
		if (analysisSpOrderInfoMapper.getNewRegStoreOrdercount(map) <= 0) {
			map.put("dayTime", dayTime);
			return analysisSpOrderInfoMapper.getNewRegStoreOrdercount(map) > 0 ? true : false;
		}
		return false;
	}

}
