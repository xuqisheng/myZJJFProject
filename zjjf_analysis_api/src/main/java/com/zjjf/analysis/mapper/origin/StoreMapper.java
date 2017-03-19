package com.zjjf.analysis.mapper.origin;

import com.zjjf.analysis.beans.analysis.store.Store;

import java.util.HashMap;
import java.util.List;

public interface StoreMapper {

	Store getById(Integer id);

	List<Integer> getByStoreCode(HashMap<String, Object> paramMap);

	Integer getBySpGroupId(Integer spGroupId);

	String getMobileById(String id);
}