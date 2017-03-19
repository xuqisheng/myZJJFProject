package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpGroup;
import com.corner.core.beans.Store;
import com.corner.core.beans.Supplier;
import com.corner.kefu.beans.ro.sp.SpGroupMgRo;
import com.corner.kefu.beans.ro.sp.SpGroupRo;
import com.corner.kefu.beans.ro.sp.SupplierMgRo;
import com.corner.kefu.beans.vo.RegionVo;
import com.corner.kefu.beans.vo.sp.PlantItemVo;
import com.corner.kefu.beans.vo.sp.SpGroupVo;
import com.corner.kefu.beans.vo.sp.StoreMgVo;


public interface SpGroupMgMapper {
	List<SpGroup> selectSpGroupALL();

	List<SpGroup> selectSpGroupList();

	/**
	 * 
	* @Title: getSpGroupListBySupplierId 
	* @Description:获取批发商所在的定格列表
	* @param @param id
	* @param @return
	* @param @throws Exception
	* @return List<SpGroup>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<SpGroup> getSpGroupListBySupplierId(SupplierMgRo supplierMgRo) throws Exception; 
	
	/**
	 * 
	* @Title: getSpGropuWithList 
	* @Description:查询定格列表，同时查询每个定格下所包含的店铺数和批发商数
	* @param @param spGroupMgRo
	* @param @return
	* @return List<SpGroupVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<SpGroupVo> getSpGropuWithList(SpGroupMgRo spGroupMgRo) throws Exception;
	
	int getSpGropuWithListCount(SpGroupMgRo spGroupMgRo) throws Exception;
	
	
	/**
	 * 
	* @Title: getSpGroupAndSupplierList 
	* @Description:查询定格下有哪些批发商 
	* @param @param spGroupRo
	* @param @return    设定文件 
	* @return SpGroupVo    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	List<Supplier> getSpGroupAndSupplierList(SpGroupRo spGroupRo);
	
	public List<SpGroup> isExist(Map<String, Object> map);
	
	
	/**
	 * 
	* @Title: getSpGroupAndStoreList 
	* @Description:查询定格下关联的店铺集合 
	* @param @param spGroupRo
	* @param @return    设定文件 
	* @return SpGroupVo    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	public List<Store> getSpGroupAndStoreList(SpGroupRo spGroupRo);
	
	public void batchAddSupplier(Map<String, Object> map);
	public void addSupplier(Map<String, Object> map);
	public void batchRemoveSupplier(Map<String, Object> map);
	public void removeSupplier(Map<String, Object> map);
	public void batchRemoveStore(Map<String, Object> map);

	/**
	 * 
	* @Title: getSpGroupListBySupplierId 
	* @Description:
	* @param @param supplier
	* @param @return
	* @param @throws Exception
	* @return List<SpGroup>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<SpGroup> getSpGroupListBySupplierId(Supplier supplier) throws Exception;

	List<SpGroupVo> getSelectedSpGroupList(Map<String, Object> map);

	Integer getMatchConditionSpGroupCount(Map<String, Object> map);

	List<SpGroupVo> getSelectedSpGroupListFromSpPushMsgMap(Map<String, Object> map);

	List<SpGroup> getAllSpGroup(Map<String, Object> map);

	List<RegionVo> getAcTiveSpGroupList(RegionVo regionVo) throws Exception;

	List<RegionVo> getActiveAreaList(RegionVo regionVo) throws Exception;

	List<RegionVo> getActiveCityList(RegionVo regionVo) throws Exception;

	List<RegionVo> getActiveProvinList(RegionVo regionVo) throws Exception;

	/**
	 * 
	* @Title: getSpGroupListByIds 
	* @Description:根据定格id数组查询定格相关信息
	* @param @param spGroupIdArr
	* @param @return
	* @param @throws Exception
	* @return List<SpGroupVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<SpGroupVo> getSpGroupListByIds(String[] spGroupIdArr) throws Exception;

	/**
	 * 
	* @Title: getVoucherSpGroupList 
	* @Description:优惠劵获取定格列表
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<SpGroupVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<SpGroupVo> getVoucherSpGroupList(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getCountVoucherSpGroupList 
	* @Description:优惠劵获取定格列表总量
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Integer getCountVoucherSpGroupList(Map<String, Object> map) throws Exception;
	
	
	public SpGroupVo getSpGroupAndAreaById(Map<String, Object> map);

	/**
	 * 
	* @Title: getCountSpGroupAndSupplierList 
	* @Description:查询定格下批发商的总数 
	* @param @param spGroupRo
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	Integer getCountSpGroupAndSupplierList(SpGroupRo spGroupRo);

	SpGroupVo getSpGoupVoById(SpGroupRo spGroupRo);

	/**
	 * 
	* @Title: getCountSpGroupAndStoreList 
	* @Description:查询定格下店铺总量 
	* @param @param spGroupRo
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	Integer getCountSpGroupAndStoreList(SpGroupRo spGroupRo);

	/**
	 * 
	* @Title: getActiveStoreMember 
	* @Description:查询参与活动的某个批发商下的分组详情 
	* @param @param supplierId
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<StoreMgVo>    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	List<StoreMgVo> getActiveStoreMember(Map<String, Object> map) throws Exception;

	List<SpGroup> getAllSpGroup1();

	List<Supplier> getSupplierIdByGroupId(Integer groupId);
	
	/**
	 * 
	* @Title: getAllEnableGroupIdList 
	* @Description:获取所有定格的id List
	* @param @return
	* @param @throws Exception
	* @return List<String>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<String> getAllEnableGroupIdList() throws Exception;

	/**
	 * 
	* @Title: getTreeSpGroupList 
	* @Description:获取定格树形图
	* @param @return
	* @param @throws Exception
	* @return List<RegionVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<RegionVo> getTreeSpGroupList() throws Exception;
	
	
	/**
	 * 根据区域id获取对应定格
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年4月26日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public List<SpGroup> getSpGroupByAreaId(Map<String, Object> map);

	/**
	 * 
	* @Title: getSpGroupListRetrunPlantItemVo 
	* @Description:根据批发商id,查询批发商所属定格,并且将数据封装成PlantItemVo 返回 
	* @param @param paramMap
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<PlantItemVo>    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	List<PlantItemVo> getSpGroupListRetrunPlantItemVo(Map<String, Object> paramMap) throws Exception;

	List<SpGroup> spOrderActiveMapMgMapper(Map<String, Object> map);

	/**
	 * 
	* @Title: getSpGroupListByAreaId 
	* @Description:根据区域id活动所有启用状态的定格列表
	* @param @param areaId
	* @param @return
	* @return List<SpGroup>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<SpGroup> getSpGroupListByAreaId(Integer areaId);
}
