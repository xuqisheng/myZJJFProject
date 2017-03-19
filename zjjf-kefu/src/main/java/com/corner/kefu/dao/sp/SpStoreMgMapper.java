package com.corner.kefu.dao.sp;

import com.corner.core.beans.SpGroup;
import com.corner.core.beans.Store;
import com.corner.core.beans.TempTable;
import com.corner.kefu.beans.ro.StoreMgRo;
import com.corner.kefu.beans.ro.sp.SpVoucherActiveRo;
import com.corner.kefu.beans.ro.sp.StoreRo;
import com.corner.kefu.beans.vo.mobile.StoreMoblieVo;
import com.corner.kefu.beans.vo.sp.ApplyStoreVo;
import com.corner.kefu.beans.vo.sp.StoreMgVo;
import com.corner.kefu.beans.vo.sp.StoreVo;

import java.util.List;
import java.util.Map;


public interface SpStoreMgMapper {
	ApplyStoreVo getApplyStoreById(Integer id);
	
	
	List<ApplyStoreVo> getApplyStores(StoreRo storeRo);
	int getApplyStoresCount(StoreRo storeRo);
	
	
	/**
     * 根据条件统计数量
     * @author aimee at 2015年6月16日上午10:08:29
     * @email 1297579898@qq.com
     * @param spadmin
     * @return
     */
//	List<SpAdminVerifyRecordVo> findRecordList(SpAdminVerifyRecordRo spadmin);
//	int findRecordListCount(SpAdminVerifyRecordRo spadmin);
	
	List<Store> getStoreListBySpGroupId(Integer id);
	
	public List<StoreVo> getStoresIdByCode(String suppliercode);


	List<StoreVo> getSelectedStoreListFromSpPushMsgMap(String id);


	List<StoreVo> getSelectedStoreList(Map<String, Object> map);


	Integer getSpPushMsgCount(Map<String, Object> map);


	List<StoreVo> getStoreListWithSpGroup(Map<String, Object> map);


	List<StoreVo> getAllStoreList(Map<String, Object> conditionMap);


	Integer getCountStoreListWithSpGroup(Map<String, Object> map) throws Exception;

    /**
     * 
    * @Title: getNoOrderStoreList 
    * @Description:查询从未下单用户或几个月内未下单用户
    * @param @param map
    * @param @return
    * @param @throws Exception
    * @return List<StoreVo>    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	List<StoreVo> getNoOrderStoreList(Map<String, Object> map) throws Exception;

    /**
     * 
    * @Title: getCountNoOrderStoreList 
    * @Description:查询所有未删除,已审核通过店铺的总量
    * @param @param map
    * @param @return
    * @param @throws Exception
    * @return Integer    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	Integer getCountNoOrderStoreList(Map<String, Object> map) throws Exception;

    /**
     * 
    * @Title: isHasSpVoucher 
    * @Description:判断店铺是否有该优惠劵
    * @param @param map
    * @param @return
    * @param @throws Exception
    * @return List<StoreVo>    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	List<StoreVo> isHasSpVoucher(Map<String, Object> map) throws Exception;

    /**
     * 
    * @Title: isCountHasSpVoucher 
    * @Description:指定用户总数
    * @param @param map
    * @param @return
    * @param @throws Exception
    * @return Integer    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	Integer isCountHasSpVoucher(Map<String, Object> map) throws Exception;

    /**
     * 
    * @Title: getStoreIdListBySpGroupId 
    * @Description:根据定格id获取店铺id集合 
    * @param @param spGroup
    * @param @return    设定文件 
    * @return List<String>    返回类型 
    * @author 杨开泰   yangkaitai@izjjf.cn
    * @throws
     */
	List<String> getStoreIdListBySpGroupId(SpGroup spGroup);

    /**
     * 
    * @Title: updateStroeSpGroupId 
    * @Description:将Store 的SpGroupId 设置为空 
    * @param @param store
    * @param @return    设定文件 
    * @return int    返回类型 
    * @author 杨开泰   yangkaitai@izjjf.cn
    * @throws
     */
	void updateStroeSpGroupId(Store store);


	/**
	 * 
	* @Title: batchUpdateStoreSpGroup 
	* @Description:批量更新店铺所属定格
	* @param @param map
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void batchUpdateStoreSpGroup(Map<String, Object> map) throws Exception;


	List<StoreVo> getSpvoucherStoreList(Map<String, Object> map) throws Exception;


	Integer getCountSpvoucherStoreList(Map<String, Object> map) throws Exception;

	public Store getStoreByInviteId(Map<String, Object> map);


	void batchRemoveStore(String[] storeIdArr);


	Integer getMaxSequenceNum(Store store);

	/**
	 * 
	* @Title: getAllLegalByIds 
	* @Description:根据店铺id集合获取所有isDelete =0 and status = 1的店铺集合
	* @param @param storeIdList
	* @param @return
	* @param @throws Exception
	* @return List<Store>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<Store> getAllLegalByIds(List<String> storeIdList) throws Exception;


	List<Store> getStoreByRegion(Map<String, Object> map);

	/**
	 * 
	* @Title: getStoreList 
	* @Description:用于阿街足记获取待审核和已拒绝的店铺列表
	* @param @param store
	* @param @return
	* @return Pager<Store>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<StoreMoblieVo> getStoreList(StoreMgRo storeMgRo);

    /**
     * 
    * @Title: getCountStoreList 
    * @Description:用于阿街足迹获取待审核或已拒绝的店铺列表总数
    * @param @param storeMgRo
    * @param @return
    * @return Integer    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	Integer getCountStoreList(StoreMgRo storeMgRo);

    /**
     * 
    * @Title: updateApprovalStore 
    * @Description:用于阿街足迹审核店铺
    * @param @param storeMgRo
    * @return void    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	void updateApprovalStore(StoreMgRo storeMgRo);


	List<TempTable> getTempMap();


	void delTempTable();

    /**
     * 
    * @Title: getAllLegalStoreList 
    * @Description:查询所有status=1 ,isDelete=0的店铺集合
    * @param @param map
    * @param @return
    * @return List<StoreVo>    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	List<StoreVo> getAllLegalStoreList(Map<String, Object> map);


	Integer getCountAllLegalStoreList(Map<String, Object> map);

    List<StoreMgVo> getstore(Map<String, Object> paramMap);

	Integer getCountstore(Map<String, Object> paramMap);

	/**
	 * @Title: 获取累积送券活动指定的用户
	 * @Description:
	 * @param
	 * @return
	 * @throws
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @date 2016/10/24 0024 20:43
	 */
    List<StoreMgVo> getAcculateStoreList(String[] storeIdArr);

    List<StoreMgVo> getAccAllStore(Map<String, Object> map);

    List<StoreMgVo> getAccStore(Map<String, Object> newActive);

	Integer getCountAccStore(Map<String, Object> activeRo);
}
