package com.zjjf.analysis.producer.authority;

import java.util.List;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;

public interface IAuthorityLevel {

	public List<AnaDictionary> getDefaultCityId(Integer baseRoleId, Integer baseRoleUserId);

	public List<AnaDictionary> getDefaultUserLevelData(Integer baseRoleId, Integer baseRoleUserId, Integer level);

	public List<Integer> getDataIdLevel(Integer baseRoleId, Integer baseRoleUserId, Integer level);
}
