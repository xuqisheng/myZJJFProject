package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.store.Store;

public interface StoreMapper {

	Store getById(Integer id);

	List<Integer> getByStoreCode(HashMap<String, Object> paramMap);

	Integer getBySpGroupId(Integer spGroupId);

	Store getSthById(String id);
}