package com.corner.rpc.shop.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.rpc.shop.api.service.RegionService;
import com.corner.rpc.shop.api.service.StoreService;
import com.corner.rpc.shop.dao.StoreMapper;
import com.corner.rpc.shop.model.ConfigShareVo;
import com.corner.rpc.shop.model.ShareDisseminateLog;
import com.corner.rpc.shop.model.SpVoucher;
import com.corner.rpc.shop.model.SpVoucherTemp;
import com.corner.rpc.shop.model.Store;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreMapper storeMapper;
	
	@Autowired
	private RegionService regionService;
	
	@Override
	public Integer getMaxSequenceNum(Store store) throws Exception {
		return storeMapper.getMaxSequenceNum(store);
	}

	@Override
	public int checkShopNoIsExist(String shopNo) throws Exception {
		return storeMapper.checkShopNoIsExist(shopNo);
	}
	
	@Override
	public 	List<Store> querySyncStoreList(Store store) throws Exception{
		return storeMapper.querySyncStoreList(store);
	}

	/**
	 * 
	* Title: getStoreList 
	* Description: 查询店铺列表
	* @param map key=status value=2(查询待审核列表)  value=3(查询拒绝列表)
	*            key=saleManMobiles value=List<String> saleManMobiles(业务员手机号集合)
	* @return 店铺列表集合
	* @see com.corner.rpc.shop.api.service.StoreService#getStoreList(java.util.Map)
	 */
	@Override
	public List<HashMap<String, Object>> getStoreList(Map<String, Object> map) {
		List<HashMap<String, Object>> list = storeMapper.getStoreList(map);
		return list;
	}

	/**
	 * 
	* Title: updateStoreStatus 
	* Description:店铺审核 
	* @param map key=storeId value=店铺id
	*            key=status value=1(审核通过) value=3(审核拒绝)
	*            key=spGroupId value=定格id(status=1,必填;status=3,不填)
	* @see com.corner.rpc.shop.api.service.StoreService#updateStoreStatus(java.util.Map)
	 */
	@Override
	public int updateStoreStatus(Map<String, Object> map) {
		
		String userId = (String) map.get("userId");
		String userName = (String) map.get("userName");
		Object storeIdObj = map.get("storeId");
		Object statusObj = map.get("status");
		
		//通过storeId 查询Store
		Store store = storeMapper.selectByPrimaryKey(storeIdObj == null ?null :Integer.parseInt(storeIdObj.toString()));
		
		if(null != store){
			if(statusObj.toString().equals("1")&&StringUtils.isEmpty(store.getSupplierCode())){//有么有SupplierCode用来判断是否一次审核通过
				//生成店铺编号
				String regionStr = "";
				try {
					regionStr = regionService.getRegionStr(store.getProvinceId(), store.getCityId(), store.getAreaId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				regionStr = regionStr==null || "".equals(regionStr)?"":regionStr;
				//根据区域查出生成的最大值
				Integer num = storeMapper.getMaxSequenceNum(store);
				if(num==null){
					num=0;
				}
				//获取生成的数字
				String sequenceNum = "";
				do {
					sequenceNum = getSequence(num, 4);
					if(sequenceNum.contains("4")){
						num++;
						//不包含4的
						continue;
					}else{
						break;
					}
				} while (true);
				
				store.setSupplierCode(regionStr+sequenceNum);
				store.setSequenceNum(Integer.parseInt(sequenceNum));
				
				storeMapper.updateSupplierCodeAndSequenceNumById(store);
				
				
				//给推荐人发送优惠劵
				if(store.getFromWho().intValue()==2&&StringUtils.isNotEmpty(store.getInviteId())){
					//小店邀请注册,要给推荐邀请人发送优惠劵.
					Store inviterStore = storeMapper.selectByPrimaryKey(Integer.parseInt(store.getInviteId()));
					if(inviterStore!=null&&!inviterStore.getIsDelete()){
						//从ConfigShare表中获取邀请配置项 configId=2 是表中固定值
						Map<String, Object> configSharePramMap = new HashMap<>();
						configSharePramMap.put("configId", "2");
						ConfigShareVo configShare = storeMapper.getConfigShare(configSharePramMap);
						if(configShare!=null){
                             //给推荐人发送优惠劵 ,修改邀请记录	
							String sendId = String.valueOf(configShare.getRuleId());
							SpVoucherTemp spVoucherTemp= storeMapper.getSpVoucherTempById(sendId);
							if(spVoucherTemp!=null){
								//组装插入SpVoucher表中的数据
								SpVoucher spVoucher = getSpVoucher(spVoucherTemp,inviterStore);
								storeMapper.insertSelectiveSpVoucher(spVoucher);
								
								//修改邀请记录
								ShareDisseminateLog log = new ShareDisseminateLog();
								log.setEncourageType(configShare.getAwardType());
								log.setEncourageVoucher(configShare.getRuleId().toString());
								log.setAcceptUserId(store.getId());
								log.setConfirmTime(new Date());
								storeMapper.updateLogByStoreId(log);
							}
						}
					}
				}
				
				//给刚注册的小店发送优惠劵
				//获取全部定格注册送优惠劵活动,以创建时间倒叙排序,取最新创建的活动
				Map<String, Object> activeMap = storeMapper.getAllRegisterActive();
				if(activeMap!=null&&activeMap.get("sendId")!=null){
					String sendId = activeMap.get("sendId").toString();
					//查询要发送的优惠劵
					SpVoucherTemp spVoucherTemp= storeMapper.getSpVoucherTempById(sendId);
					if(spVoucherTemp!=null){
						//组装插入SpVoucher表中的数据
						SpVoucher spVoucher = getSpVoucher(spVoucherTemp,store);
						storeMapper.insertSelectiveSpVoucher(spVoucher);
					}
				}
				
			}
			
			
			
			
			//组装审核记录
			Map<String, Object> chekMap = new HashMap<>();
			chekMap.put("verifyadminId", userId);//审核人id
			chekMap.put("verifyAdminNm", userName);//审核人名字
			chekMap.put("verifyObjIntId", store.getId());//店铺id
			chekMap.put("verifyObjectId", store.getSupplierCode());//店铺编码
			chekMap.put("actionNm", map.get("status"));//操作
			//插入审核记录
			storeMapper.insertCheck(chekMap);
			
		}
		
		return storeMapper.updateStoreStatus(map);
	}

	/**
	 * 
	* @Title: getSpVoucher 
	* @Description:
	* @param @param spVoucherTemp 需要发送的优惠劵
	* @param @param store需要发送的店铺
	* @param @return
	* @return SpVoucher    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	private SpVoucher getSpVoucher(SpVoucherTemp spVoucherTemp, Store store) {
		SpVoucher spVoucher = new SpVoucher();
		Calendar startTime = new GregorianCalendar();
	    startTime.set(Calendar.HOUR_OF_DAY,0);
	    startTime.set(Calendar.MINUTE,0);
	    startTime.set(Calendar.SECOND,0);
	    
		spVoucher.setStoreId(store.getId());
		spVoucher.setStoreNm(store.getName());
		spVoucher.setRuleId(spVoucherTemp.getId());
		spVoucher.setName(spVoucherTemp.getRuleName());
		spVoucher.setRemark(spVoucherTemp.getRuleRemark());
		spVoucher.setMoney(spVoucherTemp.getUseMoney().intValue());
		spVoucher.setBillType(spVoucherTemp.getBillType());
		spVoucher.setStartTime(startTime.getTime());
		spVoucher.setCreateTime(new Date());
		startTime.add(Calendar.DATE, spVoucherTemp.getUseDay()-1);
		startTime.set(Calendar.HOUR_OF_DAY, 23);
		startTime.set(Calendar.MINUTE, 58);
		startTime.set(Calendar.SECOND, 0);
		spVoucher.setExpiredTime(startTime.getTime());
		spVoucher.setStartPrice(spVoucherTemp.getStartPrice());
		spVoucher.setPayType(spVoucherTemp.getPayType());
		spVoucher.setPayStr(spVoucherTemp.getPayStr());
		spVoucher.setGoodsType(spVoucherTemp.getUseItemFlag());
		spVoucher.setGoodsStr(spVoucherTemp.getUseItemIds());
		spVoucher.setPubStatus(new Byte("1"));
		
		return spVoucher;
	}

	private String getSequence(Integer number, Integer length) {
		if(number == null){
			return ""; 
	  }
	  number+=1;
	  char[] ary1 = number.toString().toCharArray();
	  if(length == null){
		  length=4;
	  }
	  char[] ary2 = new char[length];
	  for (int i = 0; i < length; i++) {
		  ary2[i]='0';
	  }
	  System.arraycopy(ary1, 0, ary2, ary2.length-ary1.length, ary1.length);
	  String result = new String(ary2);
	  
	  
	  return result;
	}

	/**
	 * 
	* Title: getStoreDetailInfo 
	* Description: 获取店铺详细信息
	* @param map key=storeId value=店铺id
	* @return 店铺详细信息,以及该店铺所在区域的所有可用定格集合
	* @see com.corner.rpc.shop.api.service.StoreService#getStoreDetailInfo(java.util.Map)
	 */
	@Override
	public Map<String, Object> getStoreDetailInfo(Map<String, Object> map) {
		
		//店铺id
		Map<String, Object> storeMap = null;
		Object storeIdObj = map.get("storeId");
		
		if(null != storeIdObj){
			Integer storeId = Integer.parseInt(storeIdObj+"");
			//查询store,storeInfo
			storeMap = storeMapper.getStoreDetail(storeId);
			String server = "http://www.izjjf.cn/";
			if(null != storeMap){
				//如果数据为空，则不空处理
				Object idCardUpPic = storeMap.get("idCardUpPic");
				Object idCardDownPic = storeMap.get("idCardDownPic");
				Object backCardPic = storeMap.get("backCardPic");
				Object tobaccoPic = storeMap.get("tobaccoPic");
				Object licensePic = storeMap.get("licensePic");
				Object licenseNum = storeMap.get("licenseNum");
				storeMap.put("idCardUpPic", idCardUpPic==null?"":server+idCardUpPic);
				storeMap.put("idCardDownPic", idCardDownPic==null?"":server+idCardDownPic);
				storeMap.put("backCardPic", backCardPic==null?"":server+backCardPic);
				storeMap.put("tobaccoPic", tobaccoPic==null?"":server+tobaccoPic);
				storeMap.put("licensePic", licensePic==null?"":server+licensePic);
				storeMap.put("licenseNum", licenseNum==null?"":licenseNum);
				
				//查询区域下的所有定格信息
				Object areaIdObj = storeMap.get("areaId");
				if(null != areaIdObj){
					Integer areaId = Integer.parseInt(areaIdObj+"");
					List<HashMap<String, Object>> spGroupList = storeMapper.getSpGroupsByAraeId(areaId);
					
					storeMap.put("spGroupList", spGroupList);
				}
			}
		}
		
		return storeMap;
	}


}
