package com.zjjf.analysis.producer.base;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;

public interface IBaseCoreService {

	public List<AnaDictionary> getAreaByCityId(String cityId);

	public List<AnaDictionary> getGridByParam(HashMap<String, Object> spGroupMap);

	public Object[] getColumn(Object[][] authorityArray, Integer key_or_name);

	public HashMap<String, Object> getOptionList(Integer baseRoleId, Integer baseRoleUserId, Integer level, String dispatch);

	public List<AnaDictionary> getDefaultCityId(Integer baseRoleId, Integer baseRoleUserId);

	public List<AnaDictionary> getDefaultUserLevelData(Integer baseRoleId, Integer baseRoleUserId, Integer level);

}
