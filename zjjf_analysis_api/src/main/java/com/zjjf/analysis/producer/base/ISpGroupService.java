package com.zjjf.analysis.producer.base;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;

public interface ISpGroupService {

	public List<AnaDictionary> getSpGroupCodeList(Integer baseRoleId, Integer baseRoleUserId, Integer level);

	public List<AnaDictionary> getSpGroupCodeListByAreaId(HashMap<String, Object> spGroupMap);

}
