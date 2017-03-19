package com.zjjf.analysis.services.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.mapper.analysis.BaseRegionMapper;
import com.zjjf.analysis.producer.base.IRegionService;

@Service(version="1.0.0")
public class RegionServiceImpl implements IRegionService {

	@Autowired
	private BaseRegionMapper baseRegionMapper;

	@Override
	public HashMap<String, Object> getAreaByCityId(Integer cityId, Integer roleUserId) {

		List<String> idList = new ArrayList<String>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> cityMap = new HashMap<String, Object>();
		cityMap.put("cityId", cityId);
		cityMap.put("regionLevel", 4);
		cityMap.put("roleUserId", roleUserId);
		List<AnaDictionary> areaList = baseRegionMapper.getAreaByCityId(cityMap);
		resultMap.put("areaList", areaList);
		for (AnaDictionary bean : areaList) {
			if (bean.getIsCheck() != null && bean.getIsCheck() == 1) {
				idList.add(bean.getCode());
			}
		}
		resultMap.put("userSelected", idList);
		return resultMap;
	}

	@Override
	public List<AnaDictionary> getCityByareaId(HashMap<String, Object> paramMap) {

		return baseRegionMapper.getCityByareaId(paramMap);
	}
	
	@Override
	public List<AnaDictionary> getRegionList(HashMap<String, Object> paramMap) {

		return baseRegionMapper.getRegionCodeList(paramMap);
	}
}
