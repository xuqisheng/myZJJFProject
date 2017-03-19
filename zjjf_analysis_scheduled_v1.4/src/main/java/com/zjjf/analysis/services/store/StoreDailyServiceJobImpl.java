package com.zjjf.analysis.services.store;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderInfo;
import com.zjjf.analysis.beans.analysis.store.StoreDaily;
import com.zjjf.analysis.beans.origin.base.Region;
import com.zjjf.analysis.beans.origin.store.Store;
import com.zjjf.analysis.beans.origin.supplier.SpGroup;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.mapper.analysis.StoreDailyMapper;
import com.zjjf.analysis.mapper.origin.RegionMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.mapper.origin.StoreMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class StoreDailyServiceJobImpl extends AbstractTimeService<AnalysisSpOrderInfo, StoreDaily> {

	@Autowired
	private StoreDailyMapper storeDailyMapper;

	@Autowired
	private StoreMapper storeMapper;
	
	@Autowired
	private SpGroupMapper spGroupMapper;
	
	@Autowired
	private RegionMapper regionMapper;

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Override
	public List<AnalysisSpOrderInfo> getDataByTime(String dayTime_begin, Integer offset) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dayTime", dayTime_begin);
		paramMap.put("offset", (offset - 1) * 1000);
		return analysisSpOrderInfoMapper.getStoreIdList(paramMap);
	}

	@Override
	public void _process(AnalysisSpOrderInfo t, StoreDaily v, String dayTime) {

		this.appendParam(t, v, dayTime);
	}

	private void appendParam(AnalysisSpOrderInfo t, StoreDaily v, String dayTime) {

		Integer storeId = t.getStoreId();
		Integer isZj = t.getIsZj();
		Integer spGroupId = t.getSpGroupId();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeId", storeId);
		paramMap.put("spGroupId", spGroupId);
		paramMap.put("dayTime", dayTime);
		paramMap.put("isZj", isZj);
		StoreDaily storeDaily = storeDailyMapper.getExitRecord(paramMap);
		if (storeDaily != null && storeDaily.getStoreId() != null) {
			update_plantdaily(storeDaily, paramMap);
		} else {
			add_plantdaily(v, paramMap, storeId, dayTime, isZj, spGroupId);
		}
	}

	private void update_plantdaily(StoreDaily storeDaily, HashMap<String, Object> paramMap) {

		StoreDaily temp = analysisSpOrderInfoMapper.getByStoreIdAndAddTime(paramMap);
		StoreDaily _v = new StoreDaily();
		_v.setId(storeDaily.getId());
		_v.setTurnover(temp.getTurnover());
		_v.setTotalCoupon(temp.getTotalCoupon());
		_v.setTotalFreight(temp.getTotalFreight());
		_v.setTotalRebate(temp.getTotalRebate());
		_v.setOrderCount(temp.getOrderCount());
		_v.setQuantity(temp.getQuantity());
		_v.setSku(temp.getSku());
		storeDailyMapper.update_daily(_v);
	}

	private void add_plantdaily(StoreDaily v, HashMap<String, Object> paramMap, Integer storeId, String dayTime, Integer isZj,
			Integer spGroupId) {

		StoreDaily temp = analysisSpOrderInfoMapper.getByStoreIdAndAddTime(paramMap);
		if (temp.getStoreId() != null && temp.getStoreId().intValue() != storeId.intValue()) {
			return;
		}
		v.setStoreId(storeId);
		v.setSpGroupId(spGroupId);
		v.setDayTime(Integer.valueOf(dayTime));
		v.setStoreCode(temp.getStoreCode());
		v.setTurnover(temp.getTurnover());
		v.setTotalRebate(temp.getTotalRebate());
		v.setIsVisit(0);
		v.setIsZj(isZj);
		v.setTotalCoupon(temp.getTotalCoupon());
		v.setTotalFreight(temp.getTotalFreight());
		v.setOrderCount(temp.getOrderCount());
		v.setSku(temp.getSku());
		v.setQuantity(temp.getQuantity());

		Store store = storeMapper.getByStoreId(storeId);
		if (store != null) {
			v.setStoreName(store.getStoreName());
			v.setStoreCode(store.getStoreCode());
			v.setRegisterTime(store.getRegisterTime());
		}
		SpGroup spGroup = spGroupMapper.getById(spGroupId);
		if (spGroup != null) {
			v.setCityId(spGroup.getCityId());
			v.setAreaId(spGroup.getAreaId());
			Region regionCity = regionMapper.getById(spGroup.getCityId());
			Region regionArea = regionMapper.getById(spGroup.getAreaId());
			if (regionCity != null) {
				v.setCityName(regionCity.getName());
			}
			if (regionArea != null) {
				v.setAreaName(regionArea.getName());
			}
			v.setSpGroupName(spGroup.getName());
		}
		v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));
		storeDailyMapper.insert(v);
	}
}
