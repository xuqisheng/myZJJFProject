package com.corner.kefu.service.sp;

import com.corner.core.beans.SpVoucherActive;
import com.corner.core.beans.Store;
import com.corner.core.beans.TempTable;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.StoreMgRo;
import com.corner.kefu.beans.ro.sp.StoreRo;
import com.corner.kefu.beans.vo.mobile.StoreMoblieVo;
import com.corner.kefu.beans.vo.sp.ApplyStoreVo;
import com.corner.kefu.beans.vo.sp.StoreMgVo;
import com.corner.kefu.beans.vo.sp.StoreVo;

import java.util.List;
import java.util.Map;

/**
 * 商铺Service
 * 
 * @author Howe at 2015年2月5日下午1:32:21
 * @Email itzihao@sina.com
 * @Desc
 */
public interface SpStoreService {
	/**
	 * 根据ID 逻辑删除申请店铺
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteApplyStoreById(Integer id);
	/**
	 * 获取存在商铺的操作记录
	 * 
	 * @author aimee at 2015年6月5日上午10:44:02
	 * @email 1297579898@qq.com
	 * @param spadmin
	 * @return
	 */
//	Pager<SpAdminVerifyRecordVo> findRecordList(SpAdminVerifyRecordRo spadmin);
	
	List<StoreVo> getStoresIdByCode(String code);
	Pager<ApplyStoreVo> getApplyStores(StoreRo storeRo);
	
	ApplyStoreVo getApplyStoreById(Integer id);
	
	Store getStoreById(Integer id);
	/**
	 * 根据sotreId 查询User
	 * @param stId
	 * @return
	 */
	ModelMsg updetaStore(Store store);
	boolean updateRegionHasStore(Integer integer, Integer integer2, Integer integer3);
	
	/**
	 * 
	 * @Title: getStoreListBySpGroupId
	 * @Description:
	 * @param @param id定格id
	 * @param @return
	 * @return List<Store>
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	public List<Store> getStoreListBySpGroupId(Integer id);
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
	
	public ModelMsg updetaStoreStatusAll(Store store);
	
	public ModelMsg updetaStoreStatus(Store store) ;
	
	
	List<StoreVo> getSpvoucherStoreList(Map<String, Object> map) throws Exception;
	Integer getCountSpvoucherStoreList(Map<String, Object> map) throws Exception;
	
	public Store getStoreByInviteId(Map<String, Object> map);
	
	public Integer getMaxSequenceNum(Store store);
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
	Pager<StoreMoblieVo> getStoreList(StoreMgRo storeMgRo);

	/**
	 * 
	* @Title: getStoreDetail 
	* @Description:获取店铺详细信息和店铺所属区域下的定格信息
	* @param @param storeMgRo
	* @param @return
	* @return ModelMsg    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ModelMsg getStoreDetail(StoreMgRo storeMgRo);
	/**
	 * 
	* @Title: updateApprovalStore 
	* @Description:用于阿街足迹审核店铺
	* @param @param storeMgRo
	* @param @return
	* @return ModelMsg    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	ModelMsg updateApprovalStore(StoreMgRo storeMgRo) throws Exception;
	void addTempTable(TempTable tempTable);
	List<TempTable> getTempMap();
	void delTempTable();
	/**
	 * 
	* @Title: getAllLegalStoreList 
	* @Description:获取所有status=1 ,isDelete = 0 的店铺
	* 
	* @param @param map
	* @param @return
	* @return List<StoreVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<StoreVo> getAllLegalStoreList(Map<String, Object> map);
	
	Integer getCountAllLegalStoreList(Map<String, Object> map);

    Pager<StoreMgVo> getStore(Map<String, Object> paramMap);

	/**
	 * @Title: 获取累积送券活动指定的用户
	 * @Description:
	 * @param 
	 * @return
	 * @throws
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @date 2016/10/24 0024 21:02
	 */
    List<StoreMgVo> getAcculateStoreList(SpVoucherActive active);
}
