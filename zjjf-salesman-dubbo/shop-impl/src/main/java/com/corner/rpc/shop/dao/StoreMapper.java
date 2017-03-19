package com.corner.rpc.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.rpc.shop.model.ConfigShareVo;
import com.corner.rpc.shop.model.ShareDisseminateLog;
import com.corner.rpc.shop.model.SpVoucher;
import com.corner.rpc.shop.model.SpVoucherTemp;
import com.corner.rpc.shop.model.Store;

public interface StoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKeyWithBLOBs(Store record);

    int updateByPrimaryKey(Store record);
    
	Integer getMaxSequenceNum(Store store);
	
	int checkShopNoIsExist(String shopNo);
	
	List<Store> querySyncStoreList(Store store);

	List<HashMap<String, Object>> getStoreList(Map<String, Object> map);

	int updateStoreStatus(Map<String, Object> map);

	Map<String, Object> getStoreDetailInfo(Object m);

	Map<String, Object> getStoreDetail(Integer storeId);

	Map<String, Object> getStoreInfo(Integer storeId);

	List<HashMap<String, Object>> getSpGroupsByAraeId(Integer areaId);

	Map<String, Object> getSaleManByMobile(String mobile);

	void insertCheck(Map<String, Object> chekMap);

	void updateSupplierCodeAndSequenceNumById(Store store);

	ConfigShareVo getConfigShare(Map<String, Object> configSharePramMap);


	Map<String, Object> getAllRegisterActive();

	SpVoucherTemp getSpVoucherTempById(String sendId);

	void insertSelectiveSpVoucher(SpVoucher spVoucher);

	void updateLogByStoreId(ShareDisseminateLog log);



}