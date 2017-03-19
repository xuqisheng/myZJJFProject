package com.zjjf.analysis.services.ajie;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.supplier.SpGroupTurnover;
import com.zjjf.analysis.mapper.analysis.SpGroupTurnoverMapper;
import com.zjjf.analysis.producer.ajie.ISpGroupTurnoverService;
import com.zjjf.analysis.producer.base.AbstractTimeParam;

@Service(version = "1.0.0")
public class SpGroupTurnoverServiceImpl extends AbstractTimeParam<SpGroupTurnover> implements ISpGroupTurnoverService<SpGroupTurnover> {

	@Autowired
	private SpGroupTurnoverMapper spGroupTurnoverMapper;

	@Override
	public List<HashMap<String, Object>> getData(HashMap<String, Object> paramMap) {

		return spGroupTurnoverMapper.getDataByParam(this.appendParam(paramMap)).stream().map(s -> mapToObject(s)).collect(Collectors.toList());
	}

	@Override
	public HashMap<String, Object> mapToObject(SpGroupTurnover vo) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("todayTurnover", vo.getTodayTurnover());
		map.put("spGroupId", vo.getSpGroupId());
		return map;
	}

	private HashMap<String, Object> appendParam(HashMap<String, Object> paramMap) {

		HashMap<String, Object> map = paramTimeConver(paramMap);
		map.put("spGroupId", paramMap.get("spGroupId"));
		map.put("supplierCode", paramMap.get("supplierCode"));
		map.put("cityId", paramMap.get("cityId"));
		map.put("areaId", paramMap.get("areaId"));
		map.put("orderType", paramMap.get("orderType"));
		map.put("areaIdList", paramMap.get("areaIdList"));
		
		System.out.println("ajie================================= paramMap: " + map);
		return map;
	}

	@Override
	public SpGroupTurnover mapToObject(HashMap<String, Object> map) {

		return null;
	}

}