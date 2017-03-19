package com.zjjf.analysis.services.core;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.zjjf.analysis.beans.analysis.AnaProperty;
import com.zjjf.analysis.mapper.analysis.AnaPropertyMapper;

public abstract class AbstractIndexSursor<T, V> extends AbstractCoreService<T, V> {

	@Autowired
	private AnaPropertyMapper anaPropertyMapper;

	public Integer getIndex(String key) {

		AnaProperty itemJobProperty = anaPropertyMapper.selectByAnaKey(key);
		if (itemJobProperty != null) {
			return Integer.valueOf(itemJobProperty.getAna_value());
		}
		return 0;
	}

	public void saveIndex(String key, Integer index) {

		AnaProperty itemJobProperty = anaPropertyMapper.selectByAnaKey(key);
		if (itemJobProperty != null && !"".equals(index)) {
			HashMap<String, String> param = new HashMap<String, String>();
			param.put("anaKey", key);
			param.put("anaValue", index + "");
			anaPropertyMapper.updateAnaValueByAnaKey(param);
		}
	}
}
