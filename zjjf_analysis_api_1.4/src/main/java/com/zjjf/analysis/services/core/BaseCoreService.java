package com.zjjf.analysis.services.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.beans.analysis.base.BaseUserLevel;
import com.zjjf.analysis.mapper.analysis.BaseRegionMapper;
import com.zjjf.analysis.producer.base.IBaseCoreService;
import com.zjjf.analysis.services.AbstractBaseService;
import com.zjjf.analysis.services.authority.impl.AuthorityLevelServiceImpl;
import com.zjjf.analysis.services.base.AnaDictionaryService;
import com.zjjf.analysis.services.base.SpGroupService;

@Service(version="1.0.0")
public class BaseCoreService extends AbstractBaseService<Object> implements IBaseCoreService{

	@Autowired
	private AuthorityLevelServiceImpl baseUserLevelService;

	@Autowired
	private SpGroupService spGroupService;

	@Autowired
	private AnaDictionaryService anaDictionaryService;
	
	@Autowired
	protected BaseRegionMapper baseRegionMapper;

	/**
	 * 获取Region字典
	 * 
	 * @return
	 */
	@Override
	public List<AnaDictionary> getAreaByCityId(String cityId) {

		HashMap<String, Object> cityMap = new HashMap<String, Object>();
		cityMap.put("cityId", cityId);
		cityMap.put("regionLevel", 4);
		return baseRegionMapper.getAreaByCityId(cityMap);
	}

	@Override
	public List<AnaDictionary> getGridByParam(HashMap<String, Object> spGroupMap) {

		return spGroupService.getSpGroupCodeListByAreaId(spGroupMap);
	}

	@Override
	public Object[] getColumn(Object[][] authorityArray, Integer key_or_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Object> getOptionList(Integer baseRoleId, Integer baseRoleUserId, Integer level, String dispatch) {

		HashMap<String, Object> optionMap = new HashMap<String, Object>();

		switch (dispatch) {
		case "sc_order":
			if (level == 2) {
				optionMap.put("city_option", getDefaultCityId(baseRoleId, baseRoleUserId));
				optionMap.put("area_option", getDefaultUserLevelData(baseRoleId, baseRoleUserId, 2));
			} else if (level == 1) {
				optionMap.put("city_option", getDefaultUserLevelData(baseRoleId, baseRoleUserId, 1));
				optionMap.put("area_option", new ArrayList<AnaDictionary>());
			}
			optionMap.put("sc_isprofit_option", anaDictionaryService.getByDictId("sc_isprofit_option"));
			optionMap.put("sc_item_status_option", anaDictionaryService.getByDictId("sc_item_status_option"));
			break;
		case "sp_order":
			if (level == 2) {
				optionMap.put("city_option", getDefaultCityId(baseRoleId, baseRoleUserId));
				optionMap.put("area_option", getDefaultUserLevelData(baseRoleId, baseRoleUserId, 2));
			} else if (level == 1) {
				optionMap.put("city_option", getDefaultUserLevelData(baseRoleId, baseRoleUserId, 1));
				optionMap.put("area_option", new ArrayList<AnaDictionary>());
			}
			optionMap.put("sp_group_option", spGroupService.getSpGroupCodeList(baseRoleId, baseRoleUserId, level));
			optionMap.put("sp_order_supportmetho_option", anaDictionaryService.getByDictId("sp_order_supportmetho"));
			optionMap.put("sp_order_supportstatus_option", getDefaultCityId(baseRoleId, baseRoleUserId));
			optionMap.put("sp_order_status_option", anaDictionaryService.getByDictId("sp_order_status"));
			break;
		default:
			break;
		}
		return optionMap;
	}

	@Override
	public List<AnaDictionary> getDefaultCityId(Integer baseRoleId, Integer baseRoleUserId) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", baseRoleId);
		paramMap.put("roleUserId", baseRoleUserId);
		return baseUserLevelService.getCityByAreaList(paramMap);
	}

	@Override
	public List<AnaDictionary> getDefaultUserLevelData(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		List<BaseUserLevel> levelData = baseUserLevelService.getBaseUserLevel(baseRoleId, baseRoleUserId, level);
		List<AnaDictionary> levelViewList = new ArrayList<AnaDictionary>();
		for (BaseUserLevel baseUserLevel : levelData) {
			AnaDictionary d = new AnaDictionary();
			d.setCode(baseUserLevel.getDataId() + "");
			d.setName(baseUserLevel.getName());
			levelViewList.add(d);
		}
		return levelViewList;
	}

}
