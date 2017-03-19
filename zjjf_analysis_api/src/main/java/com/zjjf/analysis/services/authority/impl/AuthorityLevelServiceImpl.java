package com.zjjf.analysis.services.authority.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.beans.analysis.base.BaseUserLevel;
import com.zjjf.analysis.mapper.analysis.BaseUserLevelMapper;
import com.zjjf.analysis.mapper.origin.RegionMapper;
import com.zjjf.analysis.producer.authority.IAuthorityLevel;


@Service(version="1.0.0")
public class AuthorityLevelServiceImpl implements IAuthorityLevel {

	@Autowired
	private BaseUserLevelMapper baseUserLevelMapper;

	@Autowired
	private RegionMapper regionMapper;

	@Override
	public List<AnaDictionary> getDefaultCityId(Integer baseRoleId, Integer baseRoleUserId) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", baseRoleId);
		paramMap.put("roleUserId", baseRoleUserId);
		return getCityByAreaList(paramMap);
	}

	@Override
	public List<AnaDictionary> getDefaultUserLevelData(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		List<BaseUserLevel> levelData = getBaseUserLevel(baseRoleId, baseRoleUserId, level);
		List<AnaDictionary> levelViewList = new ArrayList<AnaDictionary>();
		for (BaseUserLevel baseUserLevel : levelData) {
			AnaDictionary d = new AnaDictionary();
			d.setCode(baseUserLevel.getDataId() + "");
			d.setName(baseUserLevel.getName());
			levelViewList.add(d);
		}
		return levelViewList;
	}

	@Override
	public List<Integer> getDataIdLevel(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		List<Integer> idList = new ArrayList<Integer>();
		List<BaseUserLevel> levelData = getBaseUserLevel(baseRoleId, baseRoleUserId, level);
		for (BaseUserLevel bean : levelData) {
			idList.add(bean.getDataId());
		}
		return idList.size() > 0 ? idList : null;
	}

	public void deleteByRoleUserId(Integer roleUserId) {

		baseUserLevelMapper.deleteByRoleUserId(roleUserId);
	}

	public List<BaseUserLevel> getBaseUserLevel(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", baseRoleId);
		paramMap.put("roleUserId", baseRoleUserId);
		paramMap.put("level", level);
		return baseUserLevelMapper.getDataByMap(paramMap);
	}

	public List<AnaDictionary> getBaseAreaList(HashMap<String, Object> paramMap) {

		List<BaseUserLevel> list = baseUserLevelMapper.getDataByMap(paramMap);
		Integer areaId = 0;
		if (list.size() > 0) {
			areaId = list.get(0).getDataId();
		}
		paramMap.clear();
		paramMap.put("areaId", areaId);
		return regionMapper.getAreaListByareaId(paramMap);
	}

	public List<AnaDictionary> getCityByAreaList(HashMap<String, Object> paramMap) {

		List<BaseUserLevel> list = baseUserLevelMapper.getDataByMap(paramMap);
		Integer areaId = 0;
		if (list.size() > 0) {
			areaId = list.get(0).getDataId();
		}
		paramMap.clear();
		paramMap.put("areaId", areaId);
		return regionMapper.getCityByareaId(paramMap);
	}

	public List<BaseUserLevel> getDataByMap(HashMap<String, Object> paramMap) {

		return baseUserLevelMapper.getDataByMap(paramMap);
	}

	public void deleteByParamMap(HashMap<String, Object> paramMap) {

		baseUserLevelMapper.deleteByParamMap(paramMap);
	}

	public void insert(BaseUserLevel baseUserLevel) {

		baseUserLevelMapper.insert(baseUserLevel);
	}

}
