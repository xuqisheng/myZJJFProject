package com.zjjf.analysis.services.base;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.mapper.analysis.AnaDictionaryMapper;
import com.zjjf.analysis.mapper.analysis.BaseRegionMapper;
import com.zjjf.analysis.producer.base.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

@Service(version="1.0.0")
public class AnaDictionaryService implements IDictionaryService {

	@Autowired
	private AnaDictionaryMapper anaDictionaryMapper;

	@Autowired
	private BaseRegionMapper baseRegionMapper;

	public List<AnaDictionary> getByDictId(String dictId) {

		return anaDictionaryMapper.getByDictId(dictId);
	}

	public List<AnaDictionary> getDictionaryList(Integer regionLevel, Integer pid) {

		HashMap<String, Object> cityMap = new HashMap<String, Object>();
		cityMap.put("regionLevel", regionLevel);
		cityMap.put("pid", pid);
		return baseRegionMapper.getRegionCodeList(cityMap);
	}

}
