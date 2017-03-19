package com.zjjf.analysis.services.base;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.producer.base.ISpGroupService;
import com.zjjf.analysis.services.authority.impl.AuthorityLevelServiceImpl;

@Service(version = "1.0.0")
public class SpGroupService implements ISpGroupService {

	@Autowired
	private SpGroupMapper spGroupMapper;

	@Autowired
	private AuthorityLevelServiceImpl baseUserLevelService;

	public List<AnaDictionary> getSpGroupCodeList(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		HashMap<String, Object> spGroupMap = new HashMap<String, Object>();
		if (level == 2) {
			spGroupMap.put("areaIdList", baseUserLevelService.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		} else if (level == 1) {
			spGroupMap.put("cityIdList", baseUserLevelService.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		}
		return spGroupMapper.getSpGroupCodeList(spGroupMap);
	}

	public List<AnaDictionary> getSpGroupCodeListByAreaId(HashMap<String, Object> spGroupMap) {

		return spGroupMapper.getSpGroupCodeList(spGroupMap);
	}

}
