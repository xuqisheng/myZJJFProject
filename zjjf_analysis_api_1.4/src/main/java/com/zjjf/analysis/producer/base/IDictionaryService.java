package com.zjjf.analysis.producer.base;

import java.util.List;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;

public interface IDictionaryService {

	public List<AnaDictionary> getByDictId(String dictId);
	
	public List<AnaDictionary> getDictionaryList(Integer regionLevel, Integer pid);

}
