package com.zjjf.analysis.producer.base;

import java.util.List;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;

public interface IViewOptionList {

	public List<List<AnaDictionary>> getOptionList(String userName, Integer type);

	public List<AnaDictionary> getDefaultCityId(Integer baseRoleId, Integer baseRoleUserId);

	public List<AnaDictionary> getDefaultUserLevelData(Integer baseRoleId, Integer baseRoleUserId, Integer level);

	public List<Integer> getDataIdLevel(Integer baseRoleId, Integer baseRoleUserId, Integer level);
}
