package com.corner.kefu.service.sp.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.Region;
import com.corner.core.beans.SpGroup;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.Store;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.SpGroupMapper;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.PropertiesCacheUtil;
import com.corner.kefu.beans.ro.sp.SpGroupMgRo;
import com.corner.kefu.beans.ro.sp.SpGroupRo;
import com.corner.kefu.beans.ro.sp.SupplierMgRo;
import com.corner.kefu.beans.vo.RegionVo;
import com.corner.kefu.beans.vo.sp.SpGroupVo;
import com.corner.kefu.beans.vo.sp.StoreMgVo;
import com.corner.kefu.config.PropertieNameConts;
import com.corner.kefu.dao.SpOrderInfoMgMapper;
import com.corner.kefu.dao.UserMgMapper;
import com.corner.kefu.dao.sp.SpGroupMgMapper;
import com.corner.kefu.dao.sp.SpOrderActiveMapMgMapper;
import com.corner.kefu.dao.sp.SpProductMgMapper;
import com.corner.kefu.dao.sp.SpRegionMgMapper;
import com.corner.kefu.dao.sp.SpShoppingCartMgMapper;
import com.corner.kefu.dao.sp.SpStoreMgMapper;
import com.corner.kefu.dao.sp.SpVoucherMgMapper;
import com.corner.kefu.service.sp.SpGroupService;

/**
 * 
 * @ClassName: SpGroupService
 * @author: 杨开泰
 * @date: 2015年10月10日 上午10:59:38
 */
@Service
public class SpGroupServiceImpl implements SpGroupService {
	@Autowired
	SpGroupMgMapper spGroupMgMapper;
	@Autowired
	SpGroupMapper spGroupMapper;
	@Autowired
	SpProductMgMapper spProductMgMapper;
	@Autowired
	SpRegionMgMapper spRegionMgMapper;
	@Autowired
	SpShoppingCartMgMapper spShoppingCartMgMapper;
	@Autowired
	UserMgMapper userMgMapper;
	@Autowired
	SpStoreMgMapper spStoreMgMapper;
	@Autowired
	SpVoucherMgMapper spVoucherMgMapper;
	@Autowired
	SpOrderActiveMapMgMapper spOrderActiveMapMgMapper;
	@Autowired
	SpOrderInfoMgMapper spOrderInfoMgMapper;
	
	/**
	 * 查询所有定格
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<SpGroup> selectSpGroupList() throws Exception {

		return spGroupMgMapper.selectSpGroupList();
	}

	@Override
	public List<SpGroup> selectSpGroupALL() {
		return spGroupMgMapper.selectSpGroupALL();
	}

	@Override
	public Pager<SpGroupVo> getSpGropuWithList(SpGroupMgRo spGroupMgRo) throws Exception {
		Integer regionLevel = spGroupMgRo.getRegionLevel();
		Integer regionId = spGroupMgRo.getRegionId();
		if (regionId != null && regionLevel != null && regionLevel != 1 && regionLevel != 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("regionId", regionId);
			map.put("regionLevel", regionLevel);
			List<Region> list = null;
			if (regionLevel != 4) {
				list = spRegionMgMapper.getChildrenRegionList(map);
				if (list != null && list.size() != 0) {
					spGroupMgRo.setList(list);
				}
			} else {
				Region region = new Region();
				region.setId(regionId);
				list = new ArrayList<Region>();
				list.add(region);
			}
			if (list != null && list.size() != 0) {
				spGroupMgRo.setList(list);
			}else {
				List<SpGroupVo> list2 = new ArrayList<SpGroupVo>();
				return new Pager<SpGroupVo>(0,list2);
			}
		}
		List<SpGroupVo> groupVos = spGroupMgMapper.getSpGropuWithList(spGroupMgRo);
		int size = spGroupMgMapper.getSpGropuWithListCount(spGroupMgRo);
		return new Pager<SpGroupVo>(size, groupVos);
	}

	/**
	 * 查询定格下有哪些批发商
	 */
	@Override
	public Pager<Supplier> getSpGroupAndSupplierList(SpGroupRo spGroupRo) {
		List<Supplier> list = spGroupMgMapper.getSpGroupAndSupplierList(spGroupRo);
		Integer count = 0;
		if (list != null && list.size() != 0) {
			count = spGroupMgMapper.getCountSpGroupAndSupplierList(spGroupRo);
		}
		return new Pager<Supplier>(count, list);
	}

	@Override
	/**
	 * 根据id查询定格
	 * 
	 * @param spGroup
	 * @return
	 * @throws Exception
	 */
	public SpGroup getSpGroupById(Integer id) {
		return spGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public ModelMsg updateSpGroup(SpGroup spGroup) {
		List<String> storeIdList = spStoreMgMapper.getStoreIdListBySpGroupId(spGroup);
		if (storeIdList != null && storeIdList.size() != 0) {
			String[] storeIdArr = storeIdList.toArray(new String[storeIdList.size()]);
			List<String> userIdList = userMgMapper.getUserIdByStoreIdArr(storeIdArr);
			if (userIdList != null && userIdList.size() != 0) {
				spShoppingCartMgMapper.emptySpShopCartByUserId(userIdList);
			}
		}
		int result = spGroupMapper.updateByPrimaryKeySelective(spGroup);
		if (result == 0) {
			return new ModelMsg(false, "修改失败");
		}
		return new ModelMsg(true, "修改成功");
	}

	@Override
	public List<SpGroup> isExist(Map<String, Object> map) {
		return spGroupMgMapper.isExist(map);
	}

	/**
	 *查询定格下关联的店铺集合 
	 */
	@Override
	public Pager<Store> getSpGroupAndStoreList(SpGroupRo spGroupRo) {
		List<Store> list = spGroupMgMapper.getSpGroupAndStoreList(spGroupRo);
		Integer count = 0;
		if (list != null && list.size() != 0) {
			count = spGroupMgMapper.getCountSpGroupAndStoreList(spGroupRo);
		}
		return new Pager<Store>(count, list);
	}

	/**
	 * 定格中批量添加批发商
	 * @param map
	 * @throws Exception 
	 */
	@Override
	public void batchAddSupplier(Map<String, Object> map) {
		spGroupMgMapper.batchAddSupplier(map);
		spProductMgMapper.batchUpdatePlantItemBySupplierIdAndSpGroupId(map);
		spProductMgMapper.batchUpdatePlantItemPreBySupplierIdAndSpGroupId(map);
	}

	@Override
	public void addSupplier(Map<String, Object> map) {
		spGroupMgMapper.addSupplier(map);
		spProductMgMapper.updateSpProductBySupplierIdAndSpGroupId(map);
		spProductMgMapper.updatePlantItemPreBySupplierIdAndSpGroupId(map);

	}

	@Override
	public SpGroup selectByPrimaryKey(Integer spGroupId) throws Exception {
		return spGroupMapper.selectByPrimaryKey(spGroupId);
	}

	@Override
	public List<SpGroup> getSpGroupListBySupplierId(SupplierMgRo supplierMgRo) throws Exception {
		return spGroupMgMapper.getSpGroupListBySupplierId(supplierMgRo);
	}

	@Override
	public void batchRemoveSupplier(Map<String, Object> map) {
		spGroupMgMapper.batchRemoveSupplier(map);
		spProductMgMapper.batchUpdatePlantItemBySupplierIdAndSpGroupId(map);
		spProductMgMapper.batchUpdatePlantItemPreBySupplierIdAndSpGroupId(map);
	}

	@Override
	public void removeSupplier(Map<String, Object> map) {
		spGroupMgMapper.removeSupplier(map);
		spProductMgMapper.updateSpProductBySupplierIdAndSpGroupId(map);
		spProductMgMapper.updatePlantItemPreBySupplierIdAndSpGroupId(map);
	}

	@Override
	public List<SpGroup> getSpGroupListBySupplierId(Supplier supplier) throws Exception {
		return spGroupMgMapper.getSpGroupListBySupplierId(supplier);
	}

	@Override
	public List<SpGroupVo> getSelectedSpGroupList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spGroupMgMapper.getSelectedSpGroupList(map);
	}

	@Override
	public Integer getMatchConditionSpGroupCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spGroupMgMapper.getMatchConditionSpGroupCount(map);
	}

	@Override
	public List<SpGroupVo> getSelectedSpGroupListFromSpPushMsgMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spGroupMgMapper.getSelectedSpGroupListFromSpPushMsgMap(map);
	}

	@Override
	public List<SpGroup> getAllSpGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spGroupMgMapper.getAllSpGroup(map);
	}

	/**
	 * 
	* Title: getAcTiveSpGroupList 
	* Description:获取活动管理定格列表 
	* @param regionVo
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpGroupService#getAcTiveSpGroupList(com.corner.kefu.beans.vo.sp.RegionVo)
	 */
	@Override
	public List<RegionVo> getAcTiveSpGroupList(RegionVo regionVo) throws Exception {
		List<RegionVo> list = spGroupMgMapper.getTreeSpGroupList();
		return list;
	}

	@Override
	public List<SpGroupVo> getSpGroupListByIds(String[] spGroupIdArr) throws Exception {
		return spGroupMgMapper.getSpGroupListByIds(spGroupIdArr);
	}

	@Override
	public Pager<SpGroupVo> getVoucherSpGroupList(Map<String, Object> map) throws Exception {
		List<SpGroupVo> list = spGroupMgMapper.getVoucherSpGroupList(map);
		Integer totalSize = spGroupMgMapper.getCountVoucherSpGroupList(map);
		return new Pager<SpGroupVo>(totalSize, list);
	}

	/**
	 * 
	* Title: insertSelective 
	* Description:新建定格 
	* @param spGroup
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpGroupService#insertSelective(com.corner.core.beans.SpGroup)
	 */
	@Override
	public void insertSelective(SpGroup spGroup) throws Exception {
		spGroupMapper.insertSelective(spGroup);
	}

	/**	
	 * 查询定格下批发商总数
	 */
	@Override
	public Integer getCountSpGroupAndSupplierList(SpGroupRo spGroupRo) {
		return spGroupMgMapper.getCountSpGroupAndSupplierList(spGroupRo);
	}

	@Override
	public SpGroupVo getSpGoupVoById(SpGroupRo spGroupRo) throws Exception {
		return spGroupMgMapper.getSpGoupVoById(spGroupRo);
	}

	

	
	/**
	 * 查询参与活动的某个批发商下的分组详情
	 */
	@Override
	public List<StoreMgVo> getActiveStoreMember(Map<String, Object> map) throws Exception {
		List<StoreMgVo> list  = spGroupMgMapper.getActiveStoreMember(map);
		return list;
	}

	@Override
	public List<SpGroup> getAllSpGroup1() {
		// TODO Auto-generated method stub
		return spGroupMgMapper.getAllSpGroup1();
	}

	@Override
	public List<Supplier> getSupplierIdByGroupId(Integer groupId) {
		return spGroupMgMapper.getSupplierIdByGroupId(groupId);
	}

	
	/**
	 * 
	* Title: batchAddStore 
	* Description:批量新增店铺 
	* @param map
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpGroupService#batchAddStore(java.util.Map)
	 */
	@Override
	public ModelMsg batchAddStore(Map<String, Object> map) throws Exception {
		String[] storeIdArr = (String[]) map.get("chkList");
		ModelMsg msg = new ModelMsg();
		//获取配置的服务器域名
		String server = PropertiesCacheUtil.getValue("serverStr", PropertieNameConts.System);
		//处理订单
		Map<String, Object> searchMap = new HashMap<String, Object>();
		List<String> msgList = new ArrayList<String>();
		//清空订单
		for (String storeId : storeIdArr) {
			searchMap.put("storeId", storeId);
			List<SpOrderInfo> list = spOrderInfoMgMapper.getUnPayOrders(searchMap);
			if(list!=null&&list.size()!=0){
				for (SpOrderInfo spOrderInfo : list) {
					String url = "http://"+server+"/CornerV2/Mobile/SpOrder/OrderRevoke.do";
					PostMethod method = new PostMethod(url);
					method.addParameter("id", spOrderInfo.getId());
					HttpClient client = new HttpClient();
					try {
						int resultStatus = client.executeMethod(method);
						if(resultStatus == 200){
							String str = method.getResponseBodyAsString();
							str = str.substring(1, str.length()-1);
							ModelMsg resultMsg = JSONUtil.JSONToObject(str, ModelMsg.class);
							if(resultMsg.isSuccess()){
								continue;
							}
						}
					} catch (HttpException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		//清空购物车
		List<String> userIdList = userMgMapper.getUserIdByStoreIdArr(storeIdArr);
		if(userIdList!=null&&userIdList.size()!=0){
			spShoppingCartMgMapper.emptySpShopCartByUserId(userIdList);
		}
		//清空优惠劵
		spVoucherMgMapper.batchRemoveSpVoucher(storeIdArr);
		
		//清空店铺所在的批发商的用户组
		spStoreMgMapper.batchRemoveStore(storeIdArr);
		
		spStoreMgMapper.batchUpdateStoreSpGroup(map);
		
		return new ModelMsg(true, "");
	}
	
	/**
	 * 
	* Title: removeBatchStore 
	* Description:批量删除定格下的店铺 
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpGroupService#removeBatchStore(java.util.Map)
	 */
	@Override
	public ModelMsg removeBatchStore(Map<String, Object> map) throws Exception {
		String[] storeIdArr = (String[]) map.get("chkList");
		ModelMsg msg = new ModelMsg();
		//获取配置的服务器域名
		String server = PropertiesCacheUtil.getValue("serverStr", PropertieNameConts.System);
		//处理订单
		Map<String, Object> searchMap = new HashMap<String, Object>();
		List<String> msgList = new ArrayList<String>();
		//清空订单
		for (String storeId : storeIdArr) {
			searchMap.put("storeId", storeId);
			List<SpOrderInfo> list = spOrderInfoMgMapper.getUnPayOrders(searchMap);
			if(list!=null&&list.size()!=0){
				for (SpOrderInfo spOrderInfo : list) {
					String url = "http://"+server+"/CornerV2/Mobile/SpOrder/OrderRevoke.do";
					PostMethod method = new PostMethod(url);
					method.addParameter("id", spOrderInfo.getId());
					HttpClient client = new HttpClient();
					try {
						int resultStatus = client.executeMethod(method);
						if(resultStatus == 200){
							String str = method.getResponseBodyAsString();
							str = str.substring(1, str.length()-1);
							ModelMsg resultMsg = JSONUtil.JSONToObject(str, ModelMsg.class);
							if(resultMsg.isSuccess()){
								continue;
							}
						}
					} catch (HttpException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		//清空购物车
		List<String> userIdList = userMgMapper.getUserIdByStoreIdArr(storeIdArr);
		if(userIdList!=null&&userIdList.size()!=0){
			spShoppingCartMgMapper.emptySpShopCartByUserId(userIdList);
		}
	    //清空优惠劵
		spVoucherMgMapper.batchRemoveSpVoucher(storeIdArr);
		
		//清空店铺所在的批发商的用户组
	    spStoreMgMapper.batchRemoveStore(storeIdArr);
		
		spGroupMgMapper.batchRemoveStore(map);
		return new ModelMsg(true, "");
	}

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
	@Override
	public List<SpGroup> getSpGroupByAreaId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spGroupMgMapper.getSpGroupByAreaId(map);
	}

	
	/**
	 * 
	* @Title: getManjianActiveStore 
	* @Description:获取参与满减活动的店铺信息以及分组信息 
	* @param @param map
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<StoreMgVo>    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public List<StoreMgVo> getManjianActiveStore(Map<String, Object> map) throws Exception {
		return spOrderActiveMapMgMapper.getManjianActiveStore(map);
	}

	@Override
	public List<SpGroup> getSpGroupBySpGroupName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spGroupMgMapper.spOrderActiveMapMgMapper(map);
	}
}
