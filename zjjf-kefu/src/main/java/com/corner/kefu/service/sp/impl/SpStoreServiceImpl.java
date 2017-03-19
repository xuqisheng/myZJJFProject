package com.corner.kefu.service.sp.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.core.beans.*;
import com.corner.kefu.beans.vo.sp.StoreMgVo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.RegionMapper;
import com.corner.core.dao.SpAdminVerifyRecordMapper;
import com.corner.core.dao.SpGroupMapper;
import com.corner.core.dao.StoreInfoMapper;
import com.corner.core.dao.StoreMapper;
import com.corner.core.dao.TempTableMapper;
import com.corner.core.utils.DateStringUtil;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.PropertiesCacheUtil;
import com.corner.core.utils.code.MatrixUtil;
import com.corner.kefu.beans.ro.StoreMgRo;
import com.corner.kefu.beans.ro.sp.StoreRo;
import com.corner.kefu.beans.vo.mobile.StoreMobileDetailVo;
import com.corner.kefu.beans.vo.mobile.StoreMoblieVo;
import com.corner.kefu.beans.vo.sp.ApplyStoreVo;
import com.corner.kefu.beans.vo.sp.StoreVo;
import com.corner.kefu.config.PropertieNameConts;
import com.corner.kefu.dao.RegionMgMapper;
import com.corner.kefu.dao.SpOrderInfoMgMapper;
import com.corner.kefu.dao.UserMgMapper;
import com.corner.kefu.dao.sp.SpGroupMgMapper;
import com.corner.kefu.dao.sp.SpShoppingCartMgMapper;
import com.corner.kefu.dao.sp.SpStoreMgMapper;
import com.corner.kefu.dao.sp.SpVoucherMgMapper;
import com.corner.kefu.service.sp.SpStoreService;

/**
 * 商铺Service
 * 
 * @author Howe at 2015年2月5日下午1:32:21
 * @Email itzihao@sina.com
 * @Desc
 */
@Service
public class SpStoreServiceImpl implements SpStoreService {
	@Autowired
	StoreMapper storeMapper;
	@Autowired
	SpStoreMgMapper spStoreMgMapper;
	@Autowired
	RegionMgMapper regionMgMapper;
	@Autowired
	UserMgMapper userMgMapper;
	@Autowired
	SpShoppingCartMgMapper spShoppingCartMgMapper;
	@Autowired
	SpOrderInfoMgMapper spOrderInfoMgMapper;
	@Autowired
	SpVoucherMgMapper spVoucherMgMapper;
	@Autowired
	RegionMapper regionMapper;
	@Autowired
	StoreInfoMapper storeInfoMapper;
	@Autowired
	SpGroupMgMapper spGroupMgMapper;
	@Autowired
	SpGroupMapper spGroupMapper;

	@Autowired
	SpAdminVerifyRecordMapper spAdminVerifyRecordMapper;
	
	@Autowired
	TempTableMapper tempTableMapper;

	/**
	 * 根据ID 逻辑删除申请店铺
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteApplyStoreById(Integer id) {
		Store store = new Store();
		store.setId(id);
		store.setIsDelete(true);
		store.setStatus(Byte.parseByte("0"));
		return storeMapper.updateByPrimaryKeySelective(store) == 1 ? true : false;
	}

	/**
	 * 根据ID 查询申请商铺
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ApplyStoreVo getApplyStoreById(Integer id) {
		String url = MatrixUtil.fastfdspreurl;
		ApplyStoreVo vo = spStoreMgMapper.getApplyStoreById(id);
		if (vo != null) {
			vo.setBackCardPic(url + vo.getBackCardPic());
			vo.setImg(url + vo.getImg());
			vo.setIdCardDownPic(url + vo.getIdCardDownPic());
			vo.setIdCardUpPic(url + vo.getIdCardUpPic());
			vo.setLicensePic(url + vo.getLicensePic());
			vo.setTobaccoPic(url + vo.getTobaccoPic());
		}
		return vo;
	}
	/**
	 * 获取存在商铺的操作记录
	 * 
	 * @author aimee at 2015年6月5日上午10:44:02
	 * @email 1297579898@qq.com
	 * @param spadmin
	 * @return
	 */
	// @Override
	// public Pager<SpAdminVerifyRecordVo> findRecordList(SpAdminVerifyRecordRo
	// spadmin) {
	// List<SpAdminVerifyRecordVo> adminVerifyRecordVos =
	// storeMgMapper.findRecordList(spadmin);
	// int count = storeMgMapper.findRecordListCount(spadmin);
	// return new Pager<SpAdminVerifyRecordVo>(count,adminVerifyRecordVos);
	// }

	/**
	 * 获所有审核商铺列表
	 * 
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	@Override
	public Pager<ApplyStoreVo> getApplyStores(StoreRo storeRo) {
		List<ApplyStoreVo> list = spStoreMgMapper.getApplyStores(storeRo);
		int count = spStoreMgMapper.getApplyStoresCount(storeRo);
		return new Pager<ApplyStoreVo>(count, list);
	}

	@Override
	public List<StoreVo> getStoresIdByCode(String code) {
		return spStoreMgMapper.getStoresIdByCode(code);
	}

	@Override
	public Store getStoreById(Integer id) {
		return storeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	* Title: updetaStore 
	* Description:更新店铺信息 
	* @param store
	* @return 
	* @see com.corner.kefu.service.sp.SpStoreService#updetaStore(com.corner.core.beans.Store)
	 */
	@Override
	public ModelMsg updetaStore(Store store) {
		ModelMsg msg = new ModelMsg();
		String[] storeIdArr = { store.getId().toString() };
		// 获取配置的服务器域名
		String server = PropertiesCacheUtil.getValue("serverStr", PropertieNameConts.System);
		// 处理订单
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> msgList = new ArrayList<String>();
		// 清空订单
		for (String storeId : storeIdArr) {
			map.put("storeId", storeId);
			List<SpOrderInfo> list = spOrderInfoMgMapper.getUnPayOrders(map);
			if (list != null && list.size() != 0) {
				for (SpOrderInfo spOrderInfo : list) {
					String url = "http://" + server + "/CornerV2/Mobile/SpOrder/OrderRevoke.do";
					PostMethod method = new PostMethod(url);
					method.addParameter("id", spOrderInfo.getId());
					HttpClient client = new HttpClient();
					try {
						int resultStatus = client.executeMethod(method);
						if (resultStatus == 200) {
							String str = method.getResponseBodyAsString();
							str = str.substring(1, str.length() - 1);
							ModelMsg resultMsg = JSONUtil.JSONToObject(str, ModelMsg.class);
							if (resultMsg.isSuccess()) {
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

		// 清空购物车
		List<String> userIdList = userMgMapper.getUserIdByStoreIdArr(storeIdArr);
		if (userIdList != null && userIdList.size() != 0) {
			spShoppingCartMgMapper.emptySpShopCartByUserId(userIdList);
		}
		// 清空优惠劵（暂时处理方法.切换定格的时候，把小店下的批发商发的优惠劵都清掉）
		spVoucherMgMapper.batchRemoveSpVoucher(storeIdArr);

		// 清空店铺所在的批发商的用户组
		spStoreMgMapper.batchRemoveStore(storeIdArr);

		spStoreMgMapper.updateStroeSpGroupId(store);
		return new ModelMsg(true, "修改成功");
	}

	/**
	 * 商铺审核(切换定格)
	 * 
	 * @author aimee at 2015年6月16日上午10:39:40
	 * @email 1297579898@qq.com
	 * @param integer
	 * @param integer2
	 * @param integer3
	 * @return
	 */
	@Override
	public ModelMsg updetaStoreStatusAll(Store store) {
		// 获取配置的服务器域名
		String server = PropertiesCacheUtil.getValue("serverStr", PropertieNameConts.System);
		// 2：清空订单
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeId", store.getId());
		List<SpOrderInfo> allOrderList = spOrderInfoMgMapper.getStoreAllOrder(map); // 所有订单
		if (allOrderList != null && allOrderList.size() > 0) {
			List<SpOrderInfo> orderList = new ArrayList<SpOrderInfo>(); // 可以取消的订单
			int num = 0;
			for (SpOrderInfo spOrderInfo : allOrderList) {
				if (spOrderInfo.getSupportStatus() == 1 && spOrderInfo.getStatus() != 1) {
					num++;
				} else {
					orderList.add(spOrderInfo);
				}
			}
			// if(num>0){
			// return new ModelMsg(false, "此店铺还有订单未收货，不能切换定格");
			// }else{
			// 取消可以取消的订单
			if (orderList != null && orderList.size() > 0) {
				for (SpOrderInfo spOrderInfo : orderList) {
					if (spOrderInfo.getSupportStatus() == 0 && spOrderInfo.getStatus() == 1) {
						String url = "http://" + server + "/CornerV2/Mobile/SpOrder/OrderRevoke.do";
						PostMethod method = new PostMethod(url);
						method.addParameter("id", spOrderInfo.getId());
						HttpClient client = new HttpClient();
						try {
							int resultStatus = client.executeMethod(method);
							if (resultStatus == 200) {
								String str = method.getResponseBodyAsString();
								str = str.substring(1, str.length() - 1);
								ModelMsg resultMsg = JSONUtil.JSONToObject(str, ModelMsg.class);
								if (resultMsg.isSuccess()) {
									continue;
								}
							}
						} catch (HttpException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							method.releaseConnection();
						}
					}
				}
			}
			// }
		}
		String[] storeIdArr = { store.getId().toString() };
		// 1:清空购物车
		List<String> userIdList = userMgMapper.getUserIdByStoreIdArr(storeIdArr);
		if (userIdList != null && userIdList.size() != 0) {
			spShoppingCartMgMapper.emptySpShopCartByUserId(userIdList);
		}
		// 3：清空优惠券
		spVoucherMgMapper.batchRemoveSpVoucher(storeIdArr);

		// 4:清空店铺所在的批发商的用户组
		spStoreMgMapper.batchRemoveStore(storeIdArr);

		// 5:修改店铺信息
		int result = storeMapper.updateByPrimaryKeySelective(store);
		if (result == 0) {
			return new ModelMsg(false, "审核失败");
		}
		return new ModelMsg(true, "审核成功");
	}

	/**
	 * 商铺审核(不切换定格)
	 * 
	 * @author aimee at 2015年6月16日上午10:39:40
	 * @email 1297579898@qq.com
	 * @param integer
	 * @param integer2
	 * @param integer3
	 * @return
	 */
	@Override
	public ModelMsg updetaStoreStatus(Store store) {
		int result = storeMapper.updateByPrimaryKeySelective(store);
		if (result == 0) {
			return new ModelMsg(false, "审核失败");
		}
		return new ModelMsg(true, "审核成功");
	}

	/**
	 * 更新区域有商铺
	 * 
	 * @author aimee at 2015年6月16日上午10:39:40
	 * @email 1297579898@qq.com
	 * @param integer
	 * @param integer2
	 * @param integer3
	 * @return
	 */
	@Override
	public boolean updateRegionHasStore(Integer integer, Integer integer2, Integer integer3) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("provinceId", integer);
		map.put("cityId", integer2);
		map.put("areaId", integer3);
		Integer result = regionMgMapper.updateRegionHasStore(map);
		return result > 0 ? true : false;
	}

	@Override
	public List<Store> getStoreListBySpGroupId(Integer id) {
		return spStoreMgMapper.getStoreListBySpGroupId(id);
	}

	@Override
	public List<StoreVo> getSelectedStoreListFromSpPushMsgMap(String id) {
		// TODO Auto-generated method stub
		return spStoreMgMapper.getSelectedStoreListFromSpPushMsgMap(id);
	}

	@Override
	public List<StoreVo> getSelectedStoreList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spStoreMgMapper.getSelectedStoreList(map);
	}

	@Override
	public Integer getSpPushMsgCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spStoreMgMapper.getSpPushMsgCount(map);
	}

	@Override
	public List<StoreVo> getStoreListWithSpGroup(Map<String, Object> map) {
		return spStoreMgMapper.getStoreListWithSpGroup(map);
	}

	@Override
	public List<StoreVo> getAllStoreList(Map<String, Object> conditionMap) {
		// TODO Auto-generated method stub
		return spStoreMgMapper.getAllStoreList(conditionMap);
	}

	@Override
	public Integer getCountStoreListWithSpGroup(Map<String, Object> map) throws Exception {
		return spStoreMgMapper.getCountStoreListWithSpGroup(map);
	}

	/**
	 * 
	* Title: getNoOrderStoreList 
	* Description: 查询从未下单用户或几个月内未下单用户
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpStoreService#getNoOrderStoreList(java.util.Map)
	 */
	@Override
	public List<StoreVo> getNoOrderStoreList(Map<String, Object> map) throws Exception {
		return spStoreMgMapper.getNoOrderStoreList(map);
	}

	/**
	 * 
	* Title: getCountNoOrderStoreList 
	* Description: 查询所有未删除,已审核通过店铺的总量
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpStoreService#getCountNoOrderStoreList(java.util.Map)
	 */
	@Override
	public Integer getCountNoOrderStoreList(Map<String, Object> map) throws Exception {
		return spStoreMgMapper.getCountNoOrderStoreList(map);
	}

	/**
	 * 
	* Title: isHasSpVoucher 
	* Description:判断店铺是否有该优惠劵 
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpStoreService#isHasSpVoucher(java.util.Map)
	 */
	@Override
	public List<StoreVo> isHasSpVoucher(Map<String, Object> map) throws Exception {
		return spStoreMgMapper.isHasSpVoucher(map);
	}

	/**
	 * 
	* Title: isCountHasSpVoucher 
	* Description: 指定用户总数
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpStoreService#isCountHasSpVoucher(java.util.Map)
	 */
	@Override
	public Integer isCountHasSpVoucher(Map<String, Object> map) throws Exception {
		return spStoreMgMapper.isCountHasSpVoucher(map);
	}

	@Override
	public List<StoreVo> getSpvoucherStoreList(Map<String, Object> map) throws Exception {
		return spStoreMgMapper.getSpvoucherStoreList(map);
	}

	@Override
	public Integer getCountSpvoucherStoreList(Map<String, Object> map) throws Exception {
		return spStoreMgMapper.getCountSpvoucherStoreList(map);
	}

	@Override
	public Store getStoreByInviteId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spStoreMgMapper.getStoreByInviteId(map);
	}

	@Override
	public Integer getMaxSequenceNum(Store store) {
		// TODO Auto-generated method stub
		return spStoreMgMapper.getMaxSequenceNum(store);
	}

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
	@Override
	public List<Store> getAllLegalByIds(List<String> storeIdList) throws Exception {
		return spStoreMgMapper.getAllLegalByIds(storeIdList);
	}

	@Override
	public List<Store> getStoreByRegion(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spStoreMgMapper.getStoreByRegion(map);
	}

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
	@Override
	public Pager<StoreMoblieVo> getStoreList(StoreMgRo storeMgRo) {
		List<StoreMoblieVo> list = spStoreMgMapper.getStoreList(storeMgRo);
		for (StoreMoblieVo storeMoblieVo : list) {
			if (StringUtils.isEmpty(storeMoblieVo.getAddress())) {
				storeMoblieVo.setAddress("");
			}
			if (StringUtils.isEmpty(storeMoblieVo.getName())) {
				storeMoblieVo.setName("");
			}
		}
		Integer count = spStoreMgMapper.getCountStoreList(storeMgRo);
		return new Pager<StoreMoblieVo>(count, list);
	}

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
	@Override
	public ModelMsg getStoreDetail(StoreMgRo storeMgRo) {
		ModelMsg msg = new ModelMsg();
		msg.setSuccess(false);
		List<Region> regionList = regionMgMapper.getStoreRegion(storeMgRo);
		String areaStr = "";
		for (Region region : regionList) {
			areaStr += region.getName();
		}
		Store store = storeMapper.selectByPrimaryKey(storeMgRo.getId());
		StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeMgRo.getId());
		if (store == null) {
			msg.setMessage("没有对应的店铺!");
			return msg;
		}
		if (storeInfo == null) {
			msg.setMessage("对应店铺缺少相关联数据!");
			return msg;
		}
		//Store oldStore = storeMapper.selectByPrimaryKey(storeMgRo.getId());
		List<SpGroup> spGroupList = null;
		if (store.getFromWho() != null && store.getFromWho().intValue() == 1) {// 批发商邀请注册
			spGroupList = new ArrayList<SpGroup>();
			SpGroup spGroup = spGroupMapper.selectByPrimaryKey(store.getSpGroupId());
			if (spGroup != null) {
				spGroupList.add(spGroup);
			}
		} else {
			spGroupList = spGroupMgMapper.getSpGroupListByAreaId(storeMgRo.getAreaId());
		}
		if (spGroupList == null || spGroupList.size() == 0) {
			msg.setMessage("该店铺下没有相关联的定格!");
			return msg;
		}
		String server = "http://www.izjjf.cn/";
		StoreMobileDetailVo storeMobileDetailVo = new StoreMobileDetailVo();
		storeMobileDetailVo.setId(store.getId());
		if (StringUtils.isEmpty(store.getName())) {
			storeMobileDetailVo.setName("");// 店铺名字
		} else {
			storeMobileDetailVo.setName(store.getName());// 店铺名字
		}
		if (StringUtils.isEmpty(store.getContact())) {
			storeMobileDetailVo.setContact("");// 店主名
		} else {
			storeMobileDetailVo.setContact(store.getContact());// 店主名
		}
		if (StringUtils.isEmpty(store.getMobile())) {
			storeMobileDetailVo.setMobile("");// 手机号
		} else {
			storeMobileDetailVo.setMobile(store.getMobile());// 手机号
		}
		storeMobileDetailVo.setAreaStr(areaStr);// 所属区域
		if (StringUtils.isEmpty(store.getAddress())) {
			storeMobileDetailVo.setAddress(store.getAddress());// 店铺详细地址
		} else {
			storeMobileDetailVo.setAddress(store.getAddress());// 店铺详细地址
		}
		if (StringUtils.isEmpty(storeInfo.getLicenseNum())) {
			storeMobileDetailVo.setLicenseNum("");// 店铺营业执照号码
		} else {
			storeMobileDetailVo.setLicenseNum(storeInfo.getLicenseNum());// 店铺营业执照号码
		}
		if (StringUtils.isEmpty(storeInfo.getIdCardUpPic())) {
			storeMobileDetailVo.setIdCardUpPic("");// 身份证正面照片
		} else {
			storeMobileDetailVo.setIdCardUpPic(server + storeInfo.getIdCardUpPic());// 身份证正面照片
		}
		if (StringUtils.isEmpty(storeInfo.getIdCardDownPic())) {
			storeMobileDetailVo.setIdCardDownPic("");// 身份证背面照片
		} else {
			storeMobileDetailVo.setIdCardDownPic(server + storeInfo.getIdCardDownPic());// 身份证背面照片
		}
		if (StringUtils.isEmpty(storeInfo.getLicensePic())) {
			storeMobileDetailVo.setLicensePic("");// 营业执照图片
		} else {
			storeMobileDetailVo.setLicensePic(server + storeInfo.getLicensePic());// 营业执照图片
		}
		if (StringUtils.isEmpty(storeInfo.getTobaccoPic())) {
			storeMobileDetailVo.setTobaccoPic("");;// 营业执照图片
		} else {
			storeMobileDetailVo.setLicensePic(server + storeInfo.getTobaccoPic());// 营业执照图片
		}
		if (StringUtils.isEmpty(storeInfo.getBackCardPic())) {
			storeMobileDetailVo.setTobaccoPic("");;
		} else {
			storeMobileDetailVo.setTobaccoPic(server + storeInfo.getBackCardPic());
		}
		storeMobileDetailVo.setSpGroupList(spGroupList);
		msg.setSuccess(true);
		msg.setData(storeMobileDetailVo);
		return msg;
	}

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
	@Override
	public ModelMsg updateApprovalStore(StoreMgRo storeMgRo) throws Exception {
		ModelMsg msg = new ModelMsg(false, "");
		Store oldStore = storeMapper.selectByPrimaryKey(storeMgRo.getId());
		if (oldStore == null) {
			msg.setMessage("没有该店铺!");
			return msg;
		}
		SpAdminVerifyRecord spAdminVerifyRecord = new SpAdminVerifyRecord();
		spAdminVerifyRecord.setActionTime(new Date());
		spAdminVerifyRecord.setVerifyadminId(storeMgRo.getUserId());// 审批人id
		spAdminVerifyRecord.setVerifyAdminNm(storeMgRo.getUserName());// 审批人name
		spAdminVerifyRecord.setVerifyObjIntId(storeMgRo.getId());// 店铺id
		if(storeMgRo.getStatus().intValue()==3){
			spAdminVerifyRecord.setActionNm(2+"");// 操作 1通过
		}else {
			spAdminVerifyRecord.setActionNm(storeMgRo.getStatus() + "");// 操作 1通过
		}
																	// 3拒绝  在spAdminVerifyRecord表中actionNm=2表示拒绝
		spAdminVerifyRecordMapper.insertSelective(spAdminVerifyRecord);// 插入审核记录

		/*
		 * Byte status = storeMgRo.getStatus();// 操作状态 String code = "";// 商铺编码
		 * String sequenceNum = "";// if (status.intValue() == 1) {// 通过 if
		 * (StringUtils.isEmpty(oldStore.getSupplierCode())) {//商铺编码为空表示第一次审核通过
		 * Map<String, String> resultMap =
		 * CreateNumberUtil.createNumber(oldStore); code =
		 * resultMap.get("regionStr"); sequenceNum =
		 * resultMap.get("sequenceNum"); oldStore.setSupplierCode(code);
		 * oldStore.setSequenceNum(Integer.parseInt(sequenceNum));
		 * 
		 * oldStore.setStatus((byte)1);//通过
		 * if(oldStore.getSpGroupId()!=null&&oldStore.getSpGroupId().intValue()!
		 * =storeMgRo.getSpGroupId().intValue()){
		 * //如果旧店铺spGroupId不为空且与现在分配的定格不一致，表示需要切换定格
		 *//**
			* 店铺切换定格有4个操作。
			* 1.清空购物车
			* 2.清空未支付订单
			* 3.
			*//*
			 * } } }
			 */
		// 初始化送货时间与起送价，评价数
		Date start = DateStringUtil.stringToDate3("08:00:00");
		Date end = DateStringUtil.stringToDate3("22:00:00");
		storeMgRo.setSendTimeBegin(start);
		storeMgRo.setSendTimeEnd(end);
		storeMgRo.setSendess(new BigDecimal(20));
		BigDecimal avgComment = new BigDecimal(5);
		storeMgRo.setAvgComment(avgComment);
		spStoreMgMapper.updateApprovalStore(storeMgRo);
		return null;
	}

	@Override
	public void addTempTable(TempTable tempTable) {
		tempTableMapper.insertSelective(tempTable);
		
	}

	@Override
	public List<TempTable> getTempMap() {
		// TODO Auto-generated method stub
		return spStoreMgMapper.getTempMap();
	}

	@Override
	public void delTempTable() {
		// TODO Auto-generated method stub
		spStoreMgMapper.delTempTable();
	}

	@Override
	public List<StoreVo> getAllLegalStoreList(Map<String, Object> map) {
		return spStoreMgMapper.getAllLegalStoreList(map);
	}

	@Override
	public Integer getCountAllLegalStoreList(Map<String, Object> map) {
		return spStoreMgMapper.getCountAllLegalStoreList(map);
	}

	@Override
	public Pager<StoreMgVo> getStore(Map<String, Object> paramMap) {
		Integer count = 0 ;
		List<StoreMgVo> list = spStoreMgMapper.getstore(paramMap);
		if(list!=null&&list.size()!=0){
			count = spStoreMgMapper.getCountstore(paramMap);
		}
		return new Pager<StoreMgVo>(count,list);
	}

	/**
	 * @Title: 获取累积送券活动指定的用户
	 * @Description:
	 * @param
	 * @return
	 * @throws
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @date 2016/10/24 0024 20:40
	 */
	@Override
	public List<StoreMgVo> getAcculateStoreList(SpVoucherActive active) {
		String storeIds = active.getStoreIds();
		storeIds = StringUtils.substring(storeIds,1,storeIds.lastIndexOf(","));
		active.setStoreIds(storeIds);
		System.out.println(storeIds);
		return spStoreMgMapper.getAcculateStoreList(storeIds.split(","));
	}
}
