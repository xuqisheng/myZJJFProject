package com.corner.rpc.shop.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.rpc.shop.model.Store;

public interface StoreService {

	public Integer getMaxSequenceNum(Store store) throws Exception;
	
	public int checkShopNoIsExist(String shopNo) throws Exception;
	
	public 	List<Store> querySyncStoreList(Store store) throws Exception;
	
	public List<HashMap<String, Object>> getStoreList(Map<String, Object> map);
	
	public int updateStoreStatus(Map<String, Object> map);
	
	public Map<String, Object> getStoreDetailInfo(Map<String, Object> map);
}
