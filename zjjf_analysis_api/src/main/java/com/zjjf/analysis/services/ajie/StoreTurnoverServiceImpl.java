package com.zjjf.analysis.services.ajie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.store.Store;
import com.zjjf.analysis.beans.analysis.store.StoreTurnover;
import com.zjjf.analysis.beans.analysis.store.StoreTurnoverVo;
import com.zjjf.analysis.mapper.analysis.StoreTurnoverMapper;
import com.zjjf.analysis.mapper.origin.StoreMapper;
import com.zjjf.analysis.producer.ajie.IStoreTurnoverService;
import com.zjjf.analysis.producer.base.AbstractTimeParam;

@Service(version = "1.0.0")
public class StoreTurnoverServiceImpl extends AbstractTimeParam<StoreTurnover> implements IStoreTurnoverService<StoreTurnover> {

	@Autowired
	private StoreTurnoverMapper storeTurnoverMapper;
	
	@Autowired
	private StoreMapper storeMapper;

	@Override
	public List<HashMap<String, Object>> getData(HashMap<String, Object> paramMap) {

		List<StoreTurnover> list = storeTurnoverMapper.getDataByParam(this.appendParam(paramMap));
		return list.stream().map(s -> mapToObject(s)).collect(Collectors.toList());
	}
	
	@Override
	public List<HashMap<String, Object>> getTurnoverBySpGoupAndStore(HashMap<String, Object> paramMap){
		
		List<StoreTurnoverVo> list = storeTurnoverMapper.getTurnoverBySpGoupAndStore(this.appendParamOrderList(paramMap));
		return list.stream().map(s -> mapToObjectOrderList(s)).collect(Collectors.toList());
	}

	@Override
	public StoreTurnover mapToObject(HashMap<String, Object> map) {

		return null;
	}

	private HashMap<String, Object> appendParam(HashMap<String, Object> paramMap) {

		HashMap<String, Object> map = this.paramTimeConver(paramMap);
		map.put("storeId", paramMap.get("storeId"));
		map.put("storeCode", paramMap.get("storeCode"));
		map.put("isVisit", paramMap.get("isVisit"));
		map.put("cityId", paramMap.get("cityId"));
		map.put("areaId", paramMap.get("areaId"));
		map.put("spGroupId", paramMap.get("spGroupId"));
		map.put("isZj", paramMap.get("isUnion"));
		map.put("areaIdList", paramMap.get("areaIdList"));
		@SuppressWarnings("unchecked")
		List<String> storeCodeList = paramMap.get("storeCodeList") == null ? new ArrayList<String>() : (List<String>) paramMap.get("storeCodeList");
		String storeCode = paramMap.get("storeCode") + "";
		storeCodeList.add(storeCode);
		conver_storeCode_to_storeId(map, storeCodeList);
		map.put("spGroupIdList", paramMap.get("spGroupIdList"));
		System.out.println("ajie================================= paramMap: " + map);
		return map;
	}
	
	private HashMap<String, Object> appendParamOrderList(HashMap<String, Object> paramMap) {

		HashMap<String, Object> map = this.paramTimeConver(paramMap);
		map.put("storeId", paramMap.get("storeId"));
		map.put("storeCode", paramMap.get("storeCode"));
		map.put("spGroupId", paramMap.get("spGroupId"));
		map.put("dayTime", paramMap.get("dayTime"));
		map.put("isZj", paramMap.get("isUnion"));
		map.put("spGroupIdList", paramMap.get("spGroupIdList"));
		Integer pageNo = paramMap.get("pageNo") == null ? 1 : Integer.valueOf(paramMap.get("pageNo") + "");
		Integer offset = paramMap.get("offset") == null ? 10 : Integer.valueOf(paramMap.get("offset") + "");
		map.put("pageNo", (pageNo - 1) * offset);
		map.put("offset", offset);
		System.out.println("ajie================================= paramMap: " + map);
		@SuppressWarnings("unchecked")
		List<String> storeCodeList = paramMap.get("storeCodeList") == null ? new ArrayList<String>() : (List<String>) paramMap.get("storeCodeList");
		String storeCode = paramMap.get("storeCode") + "";
		System.out.println("storeCode=====" + storeCode);;
		storeCodeList.add(storeCode);
		conver_storeCode_to_storeId(map, storeCodeList);
		System.out.println("ajie_conver_storeCode_to_storeId================================= paramMap: " + map);
		return map;
	}

	private void conver_storeCode_to_storeId(HashMap<String, Object> paramMap, List<String> storeCodeList) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("supplierCodeList", storeCodeList);
		System.out.println("conver_storeCode_to_storeId======storeCodeList :" + storeCodeList);
		if(storeCodeList != null && storeCodeList.size() > 0) {
			List<Integer> storeIdList = storeMapper.getByStoreCode(map);
			System.out.println("conver_storeCode_to_storeId======storeIdList :" + storeIdList);
			paramMap.put("storeIdList", storeIdList);
		}
	}
	

	@Override
	public HashMap<String, Object> mapToObject(StoreTurnover vo) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("storeId", vo.getStoreId());
		map.put("storeCode", vo.getStoreCode());
		map.put("turnover", vo.getTodayTurnover());
		return map;
	}

	public HashMap<String, Object> mapToObjectOrderList(StoreTurnoverVo vo) {

		Integer storeId = vo.getStoreId();
		Store store = storeMapper.getById(storeId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dayTime", vo.getAddTime());
		map.put("orderPrice", vo.getOrderPrice());
		map.put("orderNo", vo.getOrderNo());
		map.put("status", vo.getStatus());
		if(store != null){
			map.put("mobile", store.getMobile());
			map.put("storeName", store.getName());
		}
		return map;
	}

	public Integer updateIsVisitByStoreId(Integer dayTime, String storeCode, Integer isVisit) {
		try {
			StoreTurnover vo = new StoreTurnover();
			vo.setStoreCode(storeCode);
			vo.setIsVisit(isVisit);
			vo.setDayTime(dayTime);
			storeTurnoverMapper.updateIsVisitByStoreCode(vo);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
}
