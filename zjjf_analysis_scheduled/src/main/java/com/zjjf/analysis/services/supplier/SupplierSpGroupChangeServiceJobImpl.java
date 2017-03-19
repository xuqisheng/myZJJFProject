package com.zjjf.analysis.services.supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderInfo;
import com.zjjf.analysis.beans.analysis.supplier.SupplierSpGroupIdChangeLog;
import com.zjjf.analysis.beans.origin.supplier.SpGroup;
import com.zjjf.analysis.beans.origin.supplier.SpGroupMap;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.mapper.analysis.SupplierSpGroupIdChangeLogMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class SupplierSpGroupChangeServiceJobImpl extends AbstractTimeService<AnalysisSpOrderInfo, SupplierSpGroupIdChangeLog> {

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Autowired
	private SupplierSpGroupIdChangeLogMapper supplierSpGroupIdChangeLogMapper;

	@Autowired
	private SpGroupMapMapper spGroupMapMapper;

	@Autowired
	private SpGroupMapper spGroupMapper;

	private HashMap<String, List<Integer>> spIdAndGpIdsMap;
	
	private HashMap<String, List<Integer>> remainSpIdAndGpIdsMap;
	
	@Override
	public List<? extends AnalysisSpOrderInfo> getDataByTime(String yyyyMM, Integer i) {
		HashMap<String, Object> supplierMap = new HashMap<String, Object>();
		supplierMap.put("offset", (i - 1) * 1000);
		// supplierMap.put("dayTime", yyyyMM);
		List<AnalysisSpOrderInfo> supplierSpGroupLog = analysisSpOrderInfoMapper.getSupplierSpGroupLog(supplierMap);
		if (supplierSpGroupLog == null || supplierSpGroupLog.size() == 0) {
			return new ArrayList<AnalysisSpOrderInfo>();
		}
		
		return supplierSpGroupLog;
	}

	@Override
	public void _process(AnalysisSpOrderInfo t, SupplierSpGroupIdChangeLog v, String dayTime) {
		String supplierId = t.getSupplierId();
 		Integer spGroupId = t.getSpGroupId();
		if (supplierId == null || spGroupId == null) {
			return;
		}
		
		//根据该合作商 获取 当前所有的 定格
		getAllspGroupIdByspId(supplierId);
		
		SpGroup spGroup = this.getSpGroupById(spGroupId);
		if (spGroup == null) {
			return;
		}
 		if (!isExit_current_spGroupMap(spIdAndGpIdsMap.get(supplierId), spGroupId)) {
			// 当前已经切换定格，写入批发商定格历史
			if (!is_Exit_spId_spGroupId(supplierId, spGroupId)) {
				set_SpChangeLog(v, spGroupId, supplierId, t.getDayTime() == null ? 0 : t.getDayTime(), spGroup.getCityId(), spGroup.getAreaId());
			}
			// 统一最后写入新定格记录(afterProcess)
		} else {
			// 没有切过定格，当前和历史关系完全匹配
			List<Integer> spIds = remainSpIdAndGpIdsMap.get(supplierId);
			if (spIds.contains(spGroupId)) {
				spIds.remove(spGroupId);
			}
			
			if (!is_Exit_spId_spGroupId(supplierId, spGroupId)) {
				set_SpChangeLog(v, spGroupId, supplierId, t.getDayTime() == null ? 0 : t.getDayTime(), spGroup.getCityId(), spGroup.getAreaId());
			}
		}

	}

	@Override
	public void batchAddBean(List<SupplierSpGroupIdChangeLog> _vList,
			String dayTime) {
		
		Set<String> keys = remainSpIdAndGpIdsMap.keySet();
		List<Integer> ids = null;
		
		if (keys.size() != 0) {
			for (String supplierId : keys) {
				ids = remainSpIdAndGpIdsMap.get(supplierId);
				if (ids == null) {
					break;
				}
				for (int i = 0; i < ids.size(); i++) {
					Integer spGroupId = ids.get(i);
					SpGroup spGroup =  this.getSpGroupById(spGroupId);
					if (spGroup == null) {
						continue;
					}
					if (!is_Exit_spId_spGroupId(supplierId, spGroupId)) {
						set_SpChangeLog(new SupplierSpGroupIdChangeLog(), spGroupId, supplierId, Integer.valueOf(dayTime), spGroup.getCityId(), spGroup.getAreaId());
					}
				}
			}
		}
	}
	
	private void getAllspGroupIdByspId(String supplierId) {
		List<Integer> spGroupIds = null;
		if (spIdAndGpIdsMap == null) {
			spIdAndGpIdsMap = new HashMap<String, List<Integer>>();
		}
		if (!spIdAndGpIdsMap.containsKey(supplierId)) {
			List<SpGroupMap> spGroupMapList = spGroupMapMapper.getBySupplierId(supplierId);
			spGroupIds = getSpGroupIds(spGroupMapList);
			spIdAndGpIdsMap.put(supplierId, spGroupIds);
			remainSpIdAndGpIdsMap = spIdAndGpIdsMap;
		}
	}
	
	private boolean is_Exit_spId_spGroupId(String supplierId, Integer spGroupId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplierId", supplierId);
		paramMap.put("spGroupId", spGroupId);
		List<SupplierSpGroupIdChangeLog> list = supplierSpGroupIdChangeLogMapper.query(paramMap);
		if (list == null || list.size() == 0) {
			return false;
		}
		return true;
	}

	private boolean isExit_current_spGroupMap(List<Integer> spGroupIds, Integer spGroupId) {

		if (spGroupIds.contains(spGroupId)) {
			return true;
		} else {
			return false;
		}
	}

	private void set_SpChangeLog(SupplierSpGroupIdChangeLog spGroupChangeLog, Integer spGroupId, String supplierId, Integer dayTime, Integer cityId,
			Integer areaId) {
		spGroupChangeLog.setSpGroupId(spGroupId);
		spGroupChangeLog.setSupplierId(supplierId);
		spGroupChangeLog.setCityId(cityId);
		spGroupChangeLog.setAreaId(areaId);
		spGroupChangeLog.setDayTime(dayTime);
		spGroupChangeLog.setCreateTime((int) (System.currentTimeMillis() / 1000));
		supplierSpGroupIdChangeLogMapper.insert(spGroupChangeLog);
	}
	
	private List<Integer> getSpGroupIds(List<SpGroupMap> spGroupMapList) {
		if (spGroupMapList == null) {
			return null;
		}
		List<Integer> groupIdArray = new ArrayList<Integer>(10);
		for (SpGroupMap spGroupMap : spGroupMapList) {
			groupIdArray.add(spGroupMap.getGroupId());
		}
		return groupIdArray;
	}
	
	private SpGroup getSpGroupById(Integer spGroupId) {
		return spGroupMapper.getById(spGroupId);
	}
}
