package com.corner.kefu.service.sp.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.AppItemLabelMap;
import com.corner.core.beans.AppItemLabelMapKey;
import com.corner.core.beans.AppItemTagMap;
import com.corner.core.beans.AppItemTagMapKey;
import com.corner.core.beans.AppItemTagProductMapKey;
import com.corner.core.beans.CustomerService;
import com.corner.core.beans.PlantItem;
import com.corner.core.beans.PlantItemProduct;
import com.corner.core.beans.PlantItemProductMap;
import com.corner.core.beans.SKUActive;
import com.corner.core.beans.SKUActiveGoodInfo;
import com.corner.core.beans.SKUActiveGoodInfoLog;
import com.corner.core.beans.SKUActivePublishTagLog;
import com.corner.core.beans.SKUActivePublishTagMap;
import com.corner.core.beans.vo.Pager;
import com.corner.core.beans.vo.ResponseVo;
import com.corner.core.dao.AppItemLabelMapMapper;
import com.corner.core.dao.AppItemTagMapMapper;
import com.corner.core.dao.AppItemTagProductMapMapper;
import com.corner.core.dao.PlantItemMapper;
import com.corner.core.dao.PlantItemProductMapMapper;
import com.corner.core.dao.PlantItemProductMapper;
import com.corner.core.dao.SKUActiveGoodInfoLogMapper;
import com.corner.core.dao.SKUActiveGoodInfoMapper;
import com.corner.core.dao.SKUActiveMapper;
import com.corner.core.dao.SKUActivePublishTagLogMapper;
import com.corner.core.dao.SKUActivePublishTagMapMapper;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.vo.ItemBaseVo;
import com.corner.kefu.beans.vo.SKUActiveTagVo;
import com.corner.kefu.beans.vo.sp.PlantItemVo;
import com.corner.kefu.beans.vo.sp.SKUActiveGoodInfoVo;
import com.corner.kefu.beans.vo.sp.SKUActiveSessionObject;
import com.corner.kefu.beans.vo.sp.SKUActiveVo;
import com.corner.kefu.beans.vo.sp.SupplierVo;
import com.corner.kefu.dao.sp.SKUActiveMgMapper;
import com.corner.kefu.dao.sp.SpPlantItemMgMapper;
import com.corner.kefu.service.sp.SKUActiveService;
import com.corner.kefu.service.sp.SpSupplierService;

@Service
public class SKUActiveServiceImpl implements SKUActiveService {
	
	private static final Logger logger = LoggerFactory.getLogger(SKUActiveService.class);
	
	@Autowired
	SKUActiveMgMapper skuActiveMgMapper;
	@Autowired
	SKUActiveMapper skuActiveMapper;
	@Autowired
	SKUActiveGoodInfoMapper skuActiveGoodInfoMapper;
	@Autowired
	PlantItemMapper plantItemMapper;
	@Autowired
	SKUActiveGoodInfoLogMapper skuActiveGoodInfoLogMapper;
	@Autowired
	SpSupplierService supplierService;
	@Autowired
	SKUActivePublishTagMapMapper  skuActivePublishTagMapMapper;
	
	@Autowired
	AppItemTagMapMapper appItemTagMapMapper;
	
	@Autowired
	AppItemLabelMapMapper appItemLabelMapMapper;
	
	@Autowired
	SKUActivePublishTagLogMapper skuActivePublishTagLogMapper;
	
	@Autowired
	PlantItemProductMapper plantItemProductMapper;
	
	@Autowired
	PlantItemProductMapMapper plantItemProductMapMapper;
	
	@Autowired
	AppItemTagProductMapMapper appItemTagProductMapMapper;
	
	@Autowired
	SpPlantItemMgMapper spPlantItemMgMapper;
	
	@Override
	public Pager<SKUActiveVo> getAllSKUActive(SKUActive sKUActive) {
		if(sKUActive.getEndTime()!=null){
			Date endTime = sKUActive.getEndTime();
			endTime = DateUtils.addDays(endTime, 1);
			sKUActive.setEndTime(endTime);
		}
		if(!StringUtil.stringIsNullOrEmpty(sKUActive.getActiveName())){
			sKUActive.setActiveName(sKUActive.getActiveName().trim());
		}
		List<SKUActiveVo> list = skuActiveMgMapper.getAllSKUActive(sKUActive);
		int num = skuActiveMgMapper.getAllSKUActiveCount(sKUActive);
		return new Pager<SKUActiveVo>(num, list);
	}
//	@Override
//	public SessionObject addGoodInfo(SessionObject sessionObject) {
//		List<ItemBaseVo> itemBaseList = sessionObject.getItemBases();
//		for (int i = 0; i < itemBaseList.size(); i++) {
//			
//		}
//		return null;
//	}
	@Override
	public boolean addSKUActive(String[] plantItemIds, String[] spGroupIds, String[] supplierIds,
			SKUActiveSessionObject sessionObject, CustomerService customer, String[] buyLimitNums, String[] activePrices,
			String[] tagIds,String[] brandIds,String[] totalLimitNums) throws Exception {
		if(plantItemIds == null || plantItemIds.length==0){
			throw new Exception("plantItemIds为空");
		}
		if(spGroupIds == null || spGroupIds.length==0){
			throw new Exception("spGroupIds为空");
		}
		if(supplierIds == null || supplierIds.length==0){
			throw new Exception("supplierIds为空");
		}
		SKUActive skuActive = new SKUActive();
		skuActive.setId(StringUtil.getUUID());
		skuActive.setActiveName(sessionObject.getActiveName());
		skuActive.setGoodsType(Byte.parseByte("0"));
		skuActive.setActiveDesc(sessionObject.getActiveDesc());
		skuActive.setStartTime(sessionObject.getStartTime());
		skuActive.setEndTime(sessionObject.getEndTime());
		skuActive.setBuyStartTime(sessionObject.getBuyStartTime());
		skuActive.setBuyEndTime(sessionObject.getBuyEndTime());
		skuActive.setUpTime(sessionObject.getUpTime());
		skuActive.setDownTime(sessionObject.getDownTime());
		skuActive.setAddTime(new Date());
		skuActive.setAddUser(customer.getUserName());
		skuActive.setPublish01(sessionObject.getPublish01());
		skuActive.setPublish02(sessionObject.getPublish02());
		skuActive.setPublish03(sessionObject.getPublish03());
		skuActive.setPublish04(sessionObject.getPublish04());
		skuActive.setPublish05(sessionObject.getPublish05());
		skuActive.setPublish06(sessionObject.getPublish06());
		int num = skuActiveMapper.insertSelective(skuActive);
		if(num > 0){
			SKUActiveGoodInfo goodInfo = null;
			String uuid = skuActive.getId();
			for (int i = 0; i < plantItemIds.length; i++) {
				goodInfo = new SKUActiveGoodInfo();
				goodInfo.setId(StringUtil.getUUID());
				goodInfo.setSKUActiveId(uuid);
				goodInfo.setSpGroupId(Integer.parseInt(spGroupIds[i]));
				goodInfo.setSpId(supplierIds[i]);
				goodInfo.setPlantItemId(plantItemIds[i]);
				goodInfo.setTotalBuyNum(0);
				if(buyLimitNums != null && buyLimitNums.length>0){
					goodInfo.setBuyLimitNum(Integer.parseInt(buyLimitNums[i]));
				}else{
					goodInfo.setBuyLimitNum(0);
				}
				if(activePrices != null && activePrices.length>0){
					goodInfo.setActivePrice(new BigDecimal(activePrices[i]));
				}else{
					goodInfo.setActivePrice(new BigDecimal("0"));
				}
				if(tagIds != null && tagIds.length>0){
					goodInfo.setTagId(tagIds[i]);
				}else{
					goodInfo.setTagId(null);
				}
				goodInfo.setGoodsType(Byte.parseByte("0"));
				goodInfo.setAddTime(new Date());
				try{
					goodInfo.setBrandId(brandIds[i] == null ? null : new Integer(brandIds[i]));	
				}catch(Exception e){ }
				goodInfo.setTotalLimitNum( new Integer(totalLimitNums[i]));
				skuActiveGoodInfoMapper.insertSelective(goodInfo);
			}
			
			List<SKUActiveTagVo> publishTagList = sessionObject.getPublishTagList();
			for(SKUActiveTagVo vo:publishTagList){
				if(vo.getChecked().equals(1)){
					SKUActivePublishTagMap activeTag = new SKUActivePublishTagMap();
					activeTag.setId(StringUtil.getUUID());
					activeTag.setAddTime(new Date());
					activeTag.setAddUser(customer.getUserName());
					activeTag.setIsDelete(0);
					activeTag.setPublishTagId(vo.getId());
					activeTag.setSKUActiveId(skuActive.getId());
					activeTag.setStatus(1);
					skuActivePublishTagMapMapper.insert(activeTag);
				}
			}
			
			return true;
		}else{
			return false;
		}
	}
	
	public ResponseVo stopSKUActiveByUser(final String id,String opUser) {
		ResponseVo iVo = invalidSKUActive(id, opUser);
		logger.debug("停止单品促销活动，失效活动设置的商品!" + JSONUtil.objectToJSONString(iVo));
		int count = updateSKUActiveStatus(actype_stopByUser, id, opUser);
		ResponseVo vo = new ResponseVo(true);
		vo.setDesc("处理成功");
		if(1==count){
			vo.setMessage(opUser + "执行停止单品促销活动("+id+")，成功处理1条数据"); 
		}else{
			vo.setMessage(opUser + "执行停止单品促销活动("+id+")，成功处理0条数据");
		}
		
		Runnable r = new Runnable() {
			public void run() {
				invalidSKUActive(id, opUser);
			}
		};
		r.run();
		
		logger.debug(JSONUtil.objectToJSONString(vo));
		return vo;
	}
	
	public ResponseVo confirmSKUActiveByUser(String activeId,String opUser) {
		int count = updateSKUActiveStatus(actype_confirmByUser, activeId, opUser);
		ResponseVo vo = new ResponseVo(true);
		vo.setDesc("处理成功");
		if(1==count){
			vo.setMessage(opUser + "执行审批单品促销活动("+activeId+")，成功处理1条数据"); 
		}else{
			vo.setMessage(opUser + "执行审批单品促销活动("+activeId+")，成功处理0条数据");
		}
		logger.debug(JSONUtil.objectToJSONString(vo));
		
		Runnable r = new Runnable() {
			public void run() {
				effecSKUActive(activeId, opUser);
			}
		};
		r.run();
		
		return vo;
	}
	
	public int updateSKUActiveStatus(Integer actype,String id,String opUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("actype",actype);
		map.put("id", id);
		map.put("opUser", opUser);
		try{
			return skuActiveMgMapper.updateSKUActiveStatus(map);	
		}catch(Exception e){
			e.getMessage();
			logger.error(opUser + "执行更改单品促销活动状态操作，发生异常;actype:"+actype+"    ;id:"+id+"    ;opUser:" + opUser);
		}
		return 0;
	}
	
	private List<SKUActiveGoodInfo> querySKUActiveGoodInfoByActiveId(String id){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("SKUActiveId", id);
		return skuActiveMgMapper.selectSKUActiveGoodInfoByActiveId(param);
	}
	
	private PlantItem queryPlantItemById(String id){
		return plantItemMapper.selectByPrimaryKey(id);
	}
	
	private boolean writeBackSKUActiveGoodInfoTotalBuyNum(String opUser,String plantItemId)
	{
		PlantItem oldPlantItem = queryPlantItemById(plantItemId);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("SKUActiveId", oldPlantItem.getSKUPromotionId());
		param.put("plantItemId", plantItemId);
		List<SKUActiveGoodInfoVo>  oldlistVo = skuActiveMgMapper.getSKUActiveGoodInfoByMap(param);
		if(null != oldlistVo && oldlistVo.size() > 0) {
			param.put("totalBuyNum", oldlistVo.get(0).getTotalLimitNum() - oldPlantItem.getSKUProTotalLimitNum());
			int rs = skuActiveMgMapper.updateSKUActiveGoodInfototalBuyNum(param);
			logger.debug(opUser + " 生效活动,回写商品的原来单品活动["+oldPlantItem.getSKUPromotionId()+"]购买总量; 结果:" + rs);
		}
		
		return true;
	}
	
	private boolean effecSKUActive4PlantItem(String opUser,SKUActive active,SKUActiveGoodInfo goodInfo){
		PlantItem oldPlantItem = queryPlantItemById(goodInfo.getPlantItemId());
		//查询批发商的商品表，看当前商品是否在单品促销活动里面，如果在单品促销活动，就需要把原来活动的库存使用量，写入原来单品促销表里面。
		if(null != oldPlantItem.getSKUPromotionId() && oldPlantItem.getSKUPromotionId().length() > 0){
			writeBackSKUActiveGoodInfoTotalBuyNum(opUser, oldPlantItem.getId());
		}
		
		//然后在更新写入plantItem里面
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("SKUActiveId", goodInfo.getSKUActiveId());
		param.put("plantItemId", goodInfo.getPlantItemId());
		param.put("SKUProStartTime", active.getStartTime());
		param.put("SKUProEndTime", active.getEndTime());
		param.put("SKUProLimitNum", goodInfo.getBuyLimitNum());
		int total = goodInfo.getTotalLimitNum() - (goodInfo.getTotalBuyNum() == null ? 0 : goodInfo.getTotalBuyNum());
		param.put("SKUProTotalLimitNum", total > 0 ? total : 0);
		param.put("SKUProPrice", goodInfo.getActivePrice());
		param.put("SKUProTagId", goodInfo.getTagId());
		int isSKUPromotion = 0;
		if(null != goodInfo.getBuyLimitNum() && null != goodInfo.getTotalLimitNum() 
				&& goodInfo.getBuyLimitNum() != 0 && goodInfo.getTotalBuyNum() != 0){
			isSKUPromotion = 1;
		}
		param.put("isSKUPromotion", isSKUPromotion);
		logger.debug("单品限购更新商品数据(生效)，入参:" + JSONUtil.objectToJSONString(param));
		int rs = skuActiveMgMapper.effecSKUActive4PlantItem(param);
		logger.debug("单品限购更新商品数据(生效)，结果:" + rs);
		
		SKUActiveGoodInfoLog log = new SKUActiveGoodInfoLog();
		log.setId(StringUtil.getUUID()); 
		log.setActiveGoodInfoId(goodInfo.getId());
		log.setActTime(new Date());
		log.setActype(log_actype_effec);
		log.setGoodsType(goodsType_goods);
		log.setOpUser(opUser);
		log.setPlantItemId(goodInfo.getPlantItemId());
		log.setRsNum(rs);
		log.setSkuActiveId(goodInfo.getSKUActiveId());
		log.setSpGroupId(oldPlantItem.getSpGroupId());
		log.setSpId(oldPlantItem.getSpId());
		log.setActivePrice4new(goodInfo.getActivePrice());
		log.setActivePrice4old(oldPlantItem.getSKUProPrice());
		log.setBuyLimitNum4new(goodInfo.getBuyLimitNum());
		log.setBuyLimitNum4old(oldPlantItem.getSKUProLimitNum());
		log.setTagId4new(goodInfo.getTagId());
		log.setTagId4old(oldPlantItem.getTagLabelId1());
		
		logSKUActiveGoodInfoLog(log);
		
		if(0 == rs){
			return false;
		}else{
			return true;
		}
	}
	
	private synchronized boolean effecAppItemTagMap(final String opUser,final String activeId){
		if(StringUtil.stringIsNullOrEmpty(activeId) || StringUtil.stringIsNullOrEmpty(opUser)){
			logger.error("入参错误");
			return false;
		}
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				SKUActive skuActive = skuActiveMapper.selectByPrimaryKey(activeId);
				if(status_effec.intValue() != skuActive.getStatus().intValue()){
					logger.error(opUser + "执行生效单品促销活动("+activeId+")，因活动状态不正确，无法处理。");
				}
				//把数据字表数据查询出来
				List<SKUActiveGoodInfo> glist = querySKUActiveGoodInfoByActiveId(activeId);
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("SKUActiveId", skuActive.getId());
				List<SKUActivePublishTagMap> publishList = skuActiveMgMapper.querySKUActivePublishTagMap(param);
				for(SKUActiveGoodInfo goodInfo:glist){
					effecAppItemTagMap(opUser, skuActive, goodInfo, publishList);
				}
			}
		};
		r.run();
		return true;
	}
	
	
	private boolean effecAppItemTagMap(String opUser,SKUActive active,SKUActiveGoodInfo goodInfo,List<SKUActivePublishTagMap> publishList){
		try{
			AppItemTagMapKey key = new AppItemTagMapKey();
			key.setPlantItemId(goodInfo.getPlantItemId());
			for(SKUActivePublishTagMap tag:publishList){
				key.setTagId(tag.getPublishTagId());
				AppItemTagMap appTagMap = appItemTagMapMapper.selectByPrimaryKey(key);
				if(appTagMap != null){
					logger.debug("AppItemTagMap 已存在" + JSONUtil.objectToJSONString(appTagMap));
				}else{
					AppItemTagMap record = new AppItemTagMap();
					record.setPlantItemId(goodInfo.getPlantItemId());
					record.setSKUActiveId(active.getId());
					record.setTagId(tag.getPublishTagId());
					record.setBrandId(goodInfo.getBrandId());
					int rs = appItemTagMapMapper.insert(record);
					logger.debug("AppItemTagMap 生效一条数据("+rs+"); " + JSONUtil.objectToJSONString(record));
					
					SKUActivePublishTagLog log = new SKUActivePublishTagLog();
					log.setActTime(new Date());
					log.setActype(log_actype_effec);
					log.setGoodsType(goodsType_goods);
					log.setId(StringUtil.getUUID());
					log.setOpUser(opUser);
					log.setPlantItemId(goodInfo.getPlantItemId());
					log.setSkuActiveId(active.getId());
					log.setTagId(tag.getPublishTagId());
					logSKUActivePublishTagLog(log);
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean effecAppItemLabelMap(String opUser,SKUActive active,SKUActiveGoodInfo goodInfo,List<SKUActivePublishTagMap> publishList){
		try{
			if(!goodInfo.getTagId().equals("0")){
				AppItemLabelMapKey key = new AppItemLabelMapKey();
				key.setLabelId(goodInfo.getTagId());
				key.setPlantItemId(goodInfo.getPlantItemId());
				AppItemLabelMap applabelMap = appItemLabelMapMapper.selectByPrimaryKey(key);
				if(applabelMap != null && applabelMap.getLabelId() != null && applabelMap.getPlantItemId() != null ){
					if(!applabelMap.getSKUActiveId().equals(goodInfo.getSKUActiveId())){
						applabelMap.setSKUActiveId(goodInfo.getSKUActiveId());
						appItemLabelMapMapper.updateByPrimaryKey(applabelMap);
					}
				}else{
					applabelMap = new AppItemLabelMap();
					applabelMap.setLabelId(goodInfo.getTagId());
					applabelMap.setPlantItemId(goodInfo.getPlantItemId());
					applabelMap.setSKUActiveId(goodInfo.getSKUActiveId());
					appItemLabelMapMapper.insert(applabelMap);
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public void logSKUActivePublishTagLog(final List<SKUActivePublishTagLog> listlog){
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for(SKUActivePublishTagLog log:listlog){
					skuActivePublishTagLogMapper.insert(log);
				}
			}
		};
		r.run();
	}
	
	public void logSKUActivePublishTagLog(final SKUActivePublishTagLog log){
		Runnable r = new Runnable() {
			@Override
			public void run() {
				skuActivePublishTagLogMapper.insert(log);
			}
		};
		r.run();
	}
	
	private void logSKUActiveGoodInfoLog(final SKUActiveGoodInfoLog log){
		skuActiveGoodInfoLogMapper.insert(log);
	}
	
	private boolean invalidSKUActive4PlantItem(String opUser,SKUActive active,SKUActiveGoodInfo goodInfo){
		PlantItem oldPlantItem = queryPlantItemById(goodInfo.getPlantItemId());
		//查询批发商的商品表，看当前商品是否在单品促销活动里面，如果在单品促销活动，就需要把原来活动的库存使用量，写入原来单品促销表里面。
		if(null != oldPlantItem.getSKUPromotionId() && oldPlantItem.getSKUPromotionId().length() > 0){
			writeBackSKUActiveGoodInfoTotalBuyNum(opUser, oldPlantItem.getId());
		}
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("SKUActiveId", goodInfo.getSKUActiveId());
		param.put("plantItemId", goodInfo.getPlantItemId());
		logger.debug("单品限购更新商品数据(失效)，入参:" + JSONUtil.objectToJSONString(param));
		int rs =  skuActiveMgMapper.invalidSKUActive4PlantItem(param);
		logger.debug("单品限购更新商品数据(失效)，结果:" + rs);
		
		SKUActiveGoodInfoLog log = new SKUActiveGoodInfoLog();
		log.setId(StringUtil.getUUID()); 
		log.setActiveGoodInfoId(goodInfo.getId());
		log.setActTime(new Date());
		log.setActype(log_actype_invalid);
		log.setGoodsType(goodsType_goods);
		log.setOpUser(opUser);
		log.setPlantItemId(goodInfo.getPlantItemId());
		log.setRsNum(rs);
		log.setSkuActiveId(goodInfo.getSKUActiveId());
		log.setSpGroupId(oldPlantItem.getSpGroupId());
		log.setSpId(oldPlantItem.getSpId());
		log.setActivePrice4new(null);
		log.setActivePrice4old(oldPlantItem.getSKUProPrice());
		log.setBuyLimitNum4new(null);
		log.setBuyLimitNum4old(oldPlantItem.getSKUProLimitNum());
		log.setTagId4new(null);
		log.setTagId4old(oldPlantItem.getTagLabelId1());
		
		logSKUActiveGoodInfoLog(log);
		
		if(0 == rs){
			return false;
		}else{
			return true;
		}
	}
	
	private boolean invalidAppItemTagMap(String opUser,SKUActive active,SKUActiveGoodInfo goodInfo,List<SKUActivePublishTagMap> publishList){
		try{
			AppItemTagMapKey key = new AppItemTagMapKey();
			key.setPlantItemId(goodInfo.getPlantItemId());
			List<SKUActivePublishTagLog> listlog = new ArrayList<SKUActivePublishTagLog>();
			for(SKUActivePublishTagMap tag:publishList){
				key.setTagId(tag.getPublishTagId());
				AppItemTagMap appTagMap = appItemTagMapMapper.selectByPrimaryKey(key);
				if(appTagMap != null){
					int rs = appItemTagMapMapper.deleteByPrimaryKey(key);
					logger.debug("AppItemTagMap 已存在" + JSONUtil.objectToJSONString(appTagMap) + "现在清除; " + rs);
					SKUActivePublishTagLog log = new SKUActivePublishTagLog();
					log.setActTime(new Date());
					log.setActype(log_actype_invalid);
					log.setGoodsType(goodsType_goods);
					log.setId(StringUtil.getUUID());
					log.setOpUser(opUser);
					log.setPlantItemId(goodInfo.getPlantItemId());
					log.setSkuActiveId(active.getId());
					log.setTagId(tag.getPublishTagId());
					listlog.add(log);
				}
			}
			logSKUActivePublishTagLog(listlog);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public synchronized ResponseVo  effecSKUActive_V1_4PlantItem(String activeId,String opUser){
		if(StringUtil.stringIsNullOrEmpty(activeId) || StringUtil.stringIsNullOrEmpty(opUser)){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("入参错误");
			return vo;
		}
		//把数据主表查询出来
		SKUActive skuActive = skuActiveMapper.selectByPrimaryKey(activeId);
//		if(status_init.intValue() == skuActive.getStatus().intValue() 
//				|| status_stopJob.intValue() == skuActive.getStatus()){
//			ResponseVo vo = new ResponseVo(true);
//			vo.setDesc("处理成功");
//			vo.setMessage(opUser + "执行生效单品促销活动("+activeId+")，因活动状态不正确，无法处理。");
//			return vo;
//		}
		//把数据字表数据查询出来
		List<SKUActiveGoodInfo> glist = querySKUActiveGoodInfoByActiveId(activeId);
		boolean hErrorData = false;
		
		if(glist == null || glist.size() ==0){
			hErrorData = true;
		}else{
			for(SKUActiveGoodInfo one : glist){
				if(StringUtil.stringIsNullOrEmpty(one.getPlantItemId())
						|| null == one.getActivePrice()
						|| null == one.getBuyLimitNum()){
					hErrorData = true;
				}
			}
		}
		if(hErrorData){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("数据异常");
			return vo;
		}
		
		int okCount = 0;
		int errorCount = 0;
		//把数据写入到PlatItem里面
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("SKUActiveId", skuActive.getId());
		List<SKUActivePublishTagMap> publishList = skuActiveMgMapper.querySKUActivePublishTagMap(param);
		
		for(SKUActiveGoodInfo one : glist){
			boolean flag = effecSKUActive4PlantItem(opUser,skuActive,one);
			if(flag){
				okCount ++;
			}else{
				errorCount ++;
			}
			flag = effecAppItemTagMap(opUser, skuActive, one, publishList);
			if(flag){
				okCount ++;
			}else{
				errorCount ++;
			}
		}
		String msg = "";
		msg += "生效，商品成功条数("+okCount+");";
		if(errorCount > 0)
		{
			msg += "生效，商品失败条数("+errorCount+");";
		}
		
		//更新主表数据和字表数据
		Integer count = skuActive.getEffecCount() == null ? 0 : skuActive.getEffecCount();
		count ++;
		skuActive.setEffecCount(count);
		skuActive.setEffecTime(new Date());
		skuActive.setEffecUser(opUser);
		skuActive.setStatus(status_effec.byteValue());
		int fCount = skuActiveMapper.updateByPrimaryKey(skuActive);
		if( 0 == fCount){
			msg +="更新单品活动的生效次数失败;";
		}else{
			msg +="更新单品活动的生效次数成功;";
		}
		ResponseVo vo = new ResponseVo(true);
		vo.setDesc("处理成功");
		vo.setMessage(msg);
		return vo;
	}
	
	

	/**
	 * 只能生效一次
	 * @Title: effecSKUActive_V2_4PlantItemProduct
	 * @date 2016年9月21日  下午2:29:09
	 * @author 小武
	 * @version  飓风
	 * @param activeId
	 * @param opUser
	 * @return
	 */
	public synchronized ResponseVo effecSKUActive_V2_4PlantItemProduct(String activeId,String opUser){
		if(StringUtil.stringIsNullOrEmpty(activeId) || StringUtil.stringIsNullOrEmpty(opUser)){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("入参错误");
			return vo;
		}
		//把数据主表查询出来
		SKUActive skuActive = skuActiveMapper.selectByPrimaryKey(activeId);
//		if(status_init.intValue() == skuActive.getStatus().intValue() 
//				|| status_stopJob.intValue() == skuActive.getStatus()){
//			ResponseVo vo = new ResponseVo(true);
//			vo.setDesc("处理成功");
//			vo.setMessage(opUser + "执行生效单品促销活动("+activeId+")，因活动状态不正确，无法处理。");
//			return vo;
//		}
//		if(skuActive.getEffecCount() > 0){
//			ResponseVo vo = new ResponseVo(true);
//			vo.setDesc("已经生效过，不再重复生效。");
//			vo.setMessage(opUser + "执行生效单品促销活动("+activeId+")，因该活动已经生效过，不在重复生效。");
//			return vo;
//		}
		//把数据字表数据查询出来
		List<SKUActiveGoodInfo> glist = querySKUActiveGoodInfoByActiveId(activeId);
		boolean hErrorData = false;
		
		if(glist == null || glist.size() ==0){
			hErrorData = true;
		}else{
			for(SKUActiveGoodInfo one : glist){
				if(StringUtil.stringIsNullOrEmpty(one.getPlantItemId())
						|| null == one.getActivePrice()
						|| null == one.getBuyLimitNum()){
					hErrorData = true;
				}
			}
		}
		if(hErrorData){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("数据异常");
			return vo;
		}
		
		//生效单品促销活动到产品模型里面
		effecPlantItemProductTask(opUser,activeId);
		
		//更新主表数据和字表数据
		Integer count = skuActive.getEffecCount() == null ? 0 : skuActive.getEffecCount();
		count ++;
		skuActive.setEffecCount(count);
		skuActive.setEffecTime(new Date());
		skuActive.setEffecUser(opUser);
		skuActive.setStatus(status_effec.byteValue());
		int rs = skuActiveMapper.updateByPrimaryKey(skuActive);
		logger.debug("生效单品促销活动，更新执行结果," + rs);
		ResponseVo vo = new ResponseVo(true);
		vo.setDesc("处理成功");
		return vo;
	}
	
	public synchronized ResponseVo effecSKUActive(final String activeId,final String opUser){
		return effecSKUActive_V2_4PlantItemProduct(activeId, opUser);
	}
	
	public ResponseVo effecSKUActiveTask(final String activeId,final String opUser){
		Runnable r = new Runnable() {
			public void run() {
				effecSKUActive_V2_4PlantItemProduct(activeId, opUser);
			}
		};
		r.run();
		ResponseVo vo = new ResponseVo(true);
		vo.setDesc("处理成功");
		vo.setMessage("生效活动的任务启动");
		return vo;
	}
	
	
	public synchronized ResponseVo invalidSKUActive_V1_PlantItem(String activeId,String opUser){
		if(StringUtil.stringIsNullOrEmpty(activeId) || StringUtil.stringIsNullOrEmpty(opUser)){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("入参错误");
			return vo;
		}
		//把数据主表查询出来
		SKUActive skuActive = skuActiveMapper.selectByPrimaryKey(activeId);
//		if(status_init.intValue() == skuActive.getStatus().intValue() 
//				|| status_stopJob.intValue() == skuActive.getStatus()){
//			ResponseVo vo = new ResponseVo(true);
//			vo.setDesc("处理成功");
//			vo.setMessage(opUser + "执行生效单品促销活动("+activeId+")，因活动状态不正确，无法处理。");
//			return vo;
//		}
		//把数据字表数据查询出来
		List<SKUActiveGoodInfo> glist = querySKUActiveGoodInfoByActiveId(activeId);
		boolean hErrorData = false;
		
		if(glist == null || glist.size() ==0){
			hErrorData = true;
		}else{
			for(SKUActiveGoodInfo one : glist){
				if(StringUtil.stringIsNullOrEmpty(one.getPlantItemId())
						|| null == one.getActivePrice()
						|| null == one.getBuyLimitNum()){
					hErrorData = true;
				}
			}
		}
		if(hErrorData){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("数据异常");
			return vo;
		}
		
		int okCount = 0;
		int errorCount = 0;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("SKUActiveId", skuActive.getId());
		List<SKUActivePublishTagMap> publishList = skuActiveMgMapper.querySKUActivePublishTagMap(param);
		
		//把数据写入到PlatItem里面
		for(SKUActiveGoodInfo one : glist){
			boolean flag = invalidSKUActive4PlantItem(opUser,skuActive,one);
			if(flag){
				okCount ++;
			}else{
				errorCount ++;
			}
			flag = invalidAppItemTagMap(opUser,skuActive,one, publishList);
			if(flag){
				okCount ++;
			}else{
				errorCount ++;
			}
		}
		String msg = "";
		msg += "失效，商品成功条数("+okCount+");";
		if(errorCount > 0)
		{
			msg += "失效，商品失败条数("+errorCount+");";
		}
		//更新主表数据和字表数据
		Integer count = skuActive.getInvalidCount() == null ? 0 : skuActive.getInvalidCount();
		count ++;
		skuActive.setInvalidCount(count);
		skuActive.setInvalidTime(new Date());
		skuActive.setInvalidUser(opUser);
		if("job".equals(opUser)){
			skuActive.setStatus(status_stopJob.byteValue());
		}else{
			skuActive.setStatus(status_stopUser.byteValue());
		}
		
		int fCount = skuActiveMapper.updateByPrimaryKey(skuActive);
		if( 0 == fCount){
			msg +="更新单品活动的生效次数失败;";
		}else{
			msg +="更新单品活动的生效次数成功;";
		}
		ResponseVo vo = new ResponseVo(true);
		vo.setDesc("处理成功");
		vo.setMessage(msg);
		return vo;
	}
	
	public synchronized ResponseVo invalidSKUActive_V2_PlantItemProduct(String activeId,String opUser){
		if(StringUtil.stringIsNullOrEmpty(activeId) || StringUtil.stringIsNullOrEmpty(opUser)){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("入参错误");
			return vo;
		}
		//把数据主表查询出来
		SKUActive skuActive = skuActiveMapper.selectByPrimaryKey(activeId);
//		if(status_init.intValue() == skuActive.getStatus().intValue() 
//				|| status_stopJob.intValue() == skuActive.getStatus()){
//			ResponseVo vo = new ResponseVo(true);
//			vo.setDesc("处理成功");
//			vo.setMessage(opUser + "执行生效单品促销活动("+activeId+")，因活动状态不正确，无法处理。");
//			return vo;
//		}
		//把数据字表数据查询出来
		List<SKUActiveGoodInfo> glist = querySKUActiveGoodInfoByActiveId(activeId);
		boolean hErrorData = false;
		
		if(glist == null || glist.size() ==0){
			hErrorData = true;
		}else{
			for(SKUActiveGoodInfo one : glist){
				if(StringUtil.stringIsNullOrEmpty(one.getPlantItemId())
						|| null == one.getActivePrice()
						|| null == one.getBuyLimitNum()){
					hErrorData = true;
				}
			}
		}
		if(hErrorData){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("数据异常");
			return vo;
		}
		
		int okCount = 0;
		int errorCount = 0;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("SKUActiveId", skuActive.getId());
		List<SKUActivePublishTagMap> publishList = skuActiveMgMapper.querySKUActivePublishTagMap(param);
		
		//把数据写入到PlatItem里面
		for(SKUActiveGoodInfo one : glist){
			boolean flag = invalidSKUActive4PlantItem(opUser,skuActive,one);
			if(flag){
				okCount ++;
			}else{
				errorCount ++;
			}
			flag = invalidAppItemTagMap(opUser,skuActive,one, publishList);
			if(flag){
				okCount ++;
			}else{
				errorCount ++;
			}
		}
		String msg = "";
		msg += "失效，商品成功条数("+okCount+");";
		if(errorCount > 0)
		{
			msg += "失效，商品失败条数("+errorCount+");";
		}
		
		//失效产品模型里面的产品
		invalidPlantItemProductTask(opUser, activeId);
		
		//更新主表数据和字表数据
		Integer count = skuActive.getInvalidCount() == null ? 0 : skuActive.getInvalidCount();
		count ++;
		skuActive.setInvalidCount(count);
		skuActive.setInvalidTime(new Date());
		skuActive.setInvalidUser(opUser);
		if("job".equals(opUser)){
			skuActive.setStatus(status_stopJob.byteValue());
		}else{
			skuActive.setStatus(status_stopUser.byteValue());
		}
		
		int fCount = skuActiveMapper.updateByPrimaryKey(skuActive);
		if( 0 == fCount){
			msg +="更新单品活动的生效次数失败;";
		}else{
			msg +="更新单品活动的生效次数成功;";
		}
		ResponseVo vo = new ResponseVo(true);
		vo.setDesc("处理成功");
		vo.setMessage(msg);
		return vo;
	}
	
	public synchronized ResponseVo invalidSKUActive(String activeId,String opUser){
		return invalidSKUActive_V2_PlantItemProduct(activeId, opUser);
	}
	/**
	 * 获取数据并且去重复
	 * @Title: getItemBaseIds
	 * @date 2016年8月30日  下午3:02:51
	 * @author 小武
	 * @version  飓风
	 * @param sessionObject
	 * @return
	 */
	private List<Integer> getItemBaseIds(SKUActiveSessionObject sessionObject){
		List<ItemBaseVo> list = sessionObject.getItemBases();
		if(null != list && list.size() > 0)
		{
			List<Integer> itemBaseIds = new ArrayList<Integer>();
			for(ItemBaseVo one:list){
				boolean eflag = false;
				for(Integer itemBaseId:itemBaseIds){
					if(one.getId().equals(itemBaseId)){
						eflag = true;
					}
				}
				if(!eflag){
					itemBaseIds.add(one.getId());
				}
			}
			return itemBaseIds;
		}
		return null;
	}
	/**
	 * 获取数据并且去重复
	 * @Title: getSpGroupIds
	 * @date 2016年8月30日  下午3:02:46
	 * @author 小武
	 * @version  飓风
	 * @param sessionObject
	 * @return
	 */
	private List<Integer> getSpGroupIds(SKUActiveSessionObject sessionObject){
		List<SupplierVo> list = sessionObject.getSuppliers();
		if(null != list && list.size() > 0)
		{
			List<Integer> spGroupIds = new ArrayList<Integer>();
			for(SupplierVo one:list){
				boolean eflag = false;
				for(Integer spGroupId:spGroupIds){
					if(one.getSpGroupId().equals(spGroupId)){
						eflag = true;
					}
				}
				if(!eflag){
					spGroupIds.add(one.getSpGroupId());
				}
			}
			return spGroupIds;
		}
		return null;
	}
	/**
	 * 获取数据并且去重复
	 * @Title: getSpIds
	 * @date 2016年8月30日  下午3:02:39
	 * @author 小武
	 * @version  飓风
	 * @param sessionObject
	 * @return
	 */
	private List<String> getSpIds(SKUActiveSessionObject sessionObject){
		List<SupplierVo> list = sessionObject.getSuppliers();
		if(null != list && list.size() > 0)
		{
			List<String> spIds = new ArrayList<String>();
			for(SupplierVo one:list){
				boolean eflag = false;
				for(String spId:spIds){
					if(one.getId().equals(spId)){
						eflag = true;
					}
				}
				if(!eflag){
					spIds.add(one.getId());
				}
			}
			return spIds;
		}
		return null;
	}
	
	public List<PlantItemVo> queryPlatItemList(SKUActiveSessionObject sessionObject){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("spIds", getSpIds(sessionObject));
		param.put("spGroupIds", getSpGroupIds(sessionObject));
		param.put("itemBaseIds", getItemBaseIds(sessionObject));
		logger.debug("查询的商品数据,参数:" + JSONUtil.objectToJSONString(param));
		List<PlantItemVo> list = skuActiveMgMapper.getPlatItemByMap(param);
		logger.debug("查询的商品数据,结果:" + JSONUtil.objectToJSONString(list));
		//重置活动价和限购数量
		list = initItemBaseLimitPriceAndNum(list, sessionObject.getItemBases());
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				PlantItemVo one = list.get(i);
				for(ItemBaseVo vo : sessionObject.getItemBases()){
					if(one.getItemBaseId().equals(vo.getId())){
						one.setSKUProPrice(vo.getProPrice());
						one.setRestrict(vo.getProLimitNum());
						one.setSKUProLimitNum(vo.getProLimitNum());
						one.setTagLabelId1(vo.getTagId());
						break;
					}
				}
				list.set(i, one);
			}
		}
		
		logger.debug("设置单品促销价格、限购数量、标贴后的数据:" + JSONUtil.objectToJSONString(list));
		return list;
	}
	
	@Override
	public SKUActive getSKUActiveById(String id) {
		SKUActive skuActive = skuActiveMapper.selectByPrimaryKey(id);
		return skuActive;
	}
	
	@Override
	public List<SKUActiveGoodInfo> queryListById(String id) {
		List<SKUActiveGoodInfo> glist = querySKUActiveGoodInfoByActiveId(id);
		return glist;
	}
	@Override
	public List<PlantItemVo> queryPlatItemList(List<SKUActiveGoodInfo> listActiveGoods) {
		if(null == listActiveGoods || listActiveGoods.size() == 0){
			return new ArrayList<PlantItemVo>();
		}
		Map<String,Object> param = new HashMap<String,Object>();
		List<String> spIds = new ArrayList<String>();
		List<Integer> spGroupIds = new ArrayList<Integer>();
		List<String> plantItemIds = new ArrayList<String>();
		for(SKUActiveGoodInfo one:listActiveGoods){
			boolean eflag = false;
			for(String spId:spIds){
				if(one.getSpId().equals(spId)){
					eflag = true;
				}
			}
			if(!eflag){
				spIds.add(one.getSpId());
			}
			
			eflag = false;
			for(Integer spGroupId:spGroupIds){
				if(one.getSpGroupId().equals(spGroupId)){
					eflag = true;
				}
			}
			if(!eflag){
				spGroupIds.add(one.getSpGroupId());
			}
			
			eflag = false;
			for(String plantItemId:plantItemIds){ 
				if(one.getPlantItemId().equals(plantItemId)){
					eflag = true;
				}
			}
			if(!eflag){
				plantItemIds.add(one.getPlantItemId());
			}
		}
		
		param.put("spIds", spIds);
		param.put("spGroupIds", spGroupIds);
		param.put("plantItemIds", plantItemIds);
		param.put("SKUActiveId", listActiveGoods.get(0).getSKUActiveId());
		logger.debug("查询的商品数据,参数:" + JSONUtil.objectToJSONString(param));
		List<PlantItemVo> list = skuActiveMgMapper.getPlatItemByMap(param);
		logger.debug("查询的商品数据,结果:" + JSONUtil.objectToJSONString(list));
		//重置活动价和限购数量
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				PlantItemVo one = list.get(i);
				for(SKUActiveGoodInfo vo : listActiveGoods){
					if(one.getId().equals(vo.getPlantItemId())){
						one.setSKUProPrice(vo.getActivePrice());
						one.setRestrict(vo.getBuyLimitNum());
						one.setSKUProLimitNum(vo.getBuyLimitNum());
						one.setTagLabelId1(vo.getTagId());
						break;
					}
				}
				list.set(i, one);
			}
		}
		
		logger.debug("设置单品促销价格、限购数量、标贴后的数据:" + JSONUtil.objectToJSONString(list));
		return list;
	
	}
	
	
	public List<PlantItemVo> queryPlatItemList(List<String> spIds,List<Integer> spGroupIds,List<Integer> itemBaseIds){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("spIds", spIds);
		param.put("spGroupIds", spGroupIds);
		param.put("itemBaseIds", itemBaseIds);
		logger.debug("查询的商品数据,参数:" + JSONUtil.objectToJSONString(param));
		List<PlantItemVo> list = skuActiveMgMapper.getPlatItemByMap(param);
		logger.debug("查询的商品数据,结果:" + JSONUtil.objectToJSONString(list));
		return list;
	}
	
	
	/**
	 * 
	 * @Title: initItemBaseLimitPriceAndNum
	 * @date 2016年9月1日  下午3:17:33
	 * @author 小武
	 * @version  飓风
	 * @param list
	 * @param itemBases
	 * @return
	 */
	private List<PlantItemVo> initItemBaseLimitPriceAndNum(List<PlantItemVo> list, List<ItemBaseVo> itemBases){
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				PlantItemVo one = list.get(i);
				for(ItemBaseVo vo : itemBases){
					if(one.getItemBaseId().equals(vo.getId())){
						one.setSKUProPrice(vo.getProPrice());
						one.setRestrict(vo.getProLimitNum());
						one.setSKUProLimitNum(vo.getProLimitNum());
						one.setTagLabelId1(vo.getTagId());
						break;
					}
				}
				list.set(i, one);
			}
		}
		return list;
	}
	
	
	public List<SKUActiveGoodInfo> querySKUActiveGoodInfoList4AddGoodInfoList(SKUActiveSessionObject obj,Integer[] spGroupIds,Integer[] itemBaseIds)
	{
		SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)obj;
		List<SupplierVo> supplierList = supplierService.getSupplierBySpGroupId(spGroupIds);
		sessionObject.setSuppliers(supplierList);
		List<String> spIds2 = new ArrayList<String>();
		List<Integer> spGroupIds2 = new ArrayList<Integer>();
		List<Integer> itemBaseIds2 = new ArrayList<Integer>();
		
		boolean eflag = false;
		for(Integer one:spGroupIds){
			for(Integer e:spGroupIds2){
				if(e.equals(one)){
					eflag = true;
				}
			}
			if(!eflag){
				spGroupIds2.add(one);
			}
		}
		
		eflag = false;
		for(Integer one:itemBaseIds){
			for(Integer e:itemBaseIds2){
				if(e.equals(one)){
					eflag = true;
				}
			}
			if(!eflag){
				itemBaseIds2.add(one);
			}
		}
		
		eflag = false;
		for(SupplierVo one:supplierList){
			for(String e:spIds2){
				if(e.equals(one.getId())){
					eflag = true;
				}
			}
			if(!eflag){
				spIds2.add(one.getId());
			}
		}
		List<PlantItemVo> plantItemList2 = queryPlatItemList(spIds2, spGroupIds2, itemBaseIds2);
		List<SKUActiveGoodInfo> addlist = switchSKUActiveGoodInfoList(sessionObject, plantItemList2);
		List<SKUActiveGoodInfo> list = mergeGoodInfoList(sessionObject, sessionObject.getGoodsList4New(), addlist);
		return list;
	}
	
	/**
	 * 合并新旧数据
	 * @Title: mergeGoodInfoList
	 * @date 2016年9月1日  下午4:42:01
	 * @author 小武
	 * @version  飓风
	 * @param skuActive
	 * @param oldList
	 * @param addlist
	 * @return
	 */
	private List<SKUActiveGoodInfo> mergeGoodInfoList(SKUActive skuActive,List<SKUActiveGoodInfo> oldList,List<SKUActiveGoodInfo> addlist){
		List<SKUActiveGoodInfo> list = new ArrayList<SKUActiveGoodInfo>();
		Map<String,SKUActiveGoodInfo> map = new HashMap<String,SKUActiveGoodInfo>();
		for(SKUActiveGoodInfo one:oldList){
			if(!map.containsKey(one.getPlantItemId())){
				map.put(one.getPlantItemId(), one);
				list.add(one);
			}
		}
		for(SKUActiveGoodInfo one:addlist){
			if(!map.containsKey(one.getPlantItemId())){
				map.put(one.getPlantItemId(), one);
				list.add(one);
			}
		}
		return list;
	}
	

	
	/**
	 * 集合转换
	 * @Title: switchSKUActiveGoodInfoList
	 * @date 2016年9月1日  下午4:19:34
	 * @author 小武
	 * @version  飓风
	 * @param skuActive
	 * @param plantItemList
	 * @return
	 */
	public List<SKUActiveGoodInfo> switchSKUActiveGoodInfoList(SKUActive skuActive,List<PlantItemVo> plantItemList){
		List<SKUActiveGoodInfo> list = new ArrayList<SKUActiveGoodInfo>();
		for(int i=0;i<plantItemList.size();i++){
			SKUActiveGoodInfo info = switchSKUActiveGoodInfo(skuActive, plantItemList.get(i));
			list.add(info);
		}
		return list;
	}
	
	/**
	 * 对象转换
	 * @Title: switchSKUActiveGoodInfo
	 * @date 2016年9月1日  下午4:19:45
	 * @author 小武
	 * @version  飓风
	 * @param skuActive
	 * @param vo
	 * @return
	 */
	public SKUActiveGoodInfo switchSKUActiveGoodInfo(SKUActive skuActive,PlantItemVo vo){
		SKUActiveGoodInfo info = new SKUActiveGoodInfo();
		info.setActivePrice(vo.getAreaPrice());
		info.setAddTime(new Date());
		info.setBuyLimitNum(vo.getSKUProLimitNum());
		info.setGoodsType(goodsType_goods.byteValue());
		info.setId(StringUtil.getUUID());
		info.setPlantItemId(vo.getId());
		info.setSKUActiveId(skuActive.getId());
		info.setSpGroupId(vo.getSpGroupId());
		info.setSpId(vo.getSpId());
		info.setStatus(false);
		info.setTagId(vo.getTagLabelId1());
		info.setIsDelete((byte)0);
		
		return info;
	}
	
	public List<SKUActiveGoodInfoVo> switchSKUActiveGoodInfoVoList(SKUActive skuActive,List<PlantItemVo> plantItemList){
		List<SKUActiveGoodInfoVo> list = new ArrayList<SKUActiveGoodInfoVo>();
		for(int i=0;i<plantItemList.size();i++){
			SKUActiveGoodInfoVo info = switchSKUActiveGoodInfoVo(skuActive, plantItemList.get(i));
			list.add(info);
		}
		return list;
	}
	
	public SKUActiveGoodInfoVo switchSKUActiveGoodInfoVo(SKUActive skuActive,PlantItemVo vo){
		SKUActiveGoodInfo info = switchSKUActiveGoodInfo(skuActive, vo);
		SKUActiveGoodInfoVo infoVo = new SKUActiveGoodInfoVo();
		try {
			PropertyUtils.copyProperties(infoVo, info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		infoVo.setSpGroupName(vo.getSpGroupName());
		infoVo.setSupplierName(vo.getSupplierName());
		infoVo.setMdseId(vo.getMdseId());
		infoVo.setItemBaseName(vo.getName());
		infoVo.setSKUProPrice(vo.getSKUProPrice());
		infoVo.setSKUProLimitNum(vo.getSKUProLimitNum());
		return infoVo;
	}
	
	@Override
	public ResponseVo saveEditSKUActive(String opUser,SKUActiveSessionObject sessionObject) {
		String msg="";
		List<SKUActiveGoodInfo> listOld = sessionObject.getGoodsList4Old();
		for(SKUActiveGoodInfo one:listOld){
			Map<String,Object> param = new HashMap<String,Object>();	
			param.put("id", one.getId());
			int count = skuActiveMgMapper.deleteSKUActiveGoodInfoById(param);
			logger.debug("逻辑删除(id:"+ one.getId() + ";结果:"+count+")");
		}
		Map<String,SKUActiveGoodInfo> addMap = new HashMap<String,SKUActiveGoodInfo>();
		for(SKUActiveGoodInfo one:sessionObject.getGoodsList4New()){
			if(!addMap.containsKey(one.getPlantItemId())){
				one.setId(StringUtil.getUUID());
				int count = skuActiveGoodInfoMapper.insertSelective(one);
				logger.debug("编辑新增(结果:"+count+"; + "+JSONUtil.objectToJSONString(one)+")");
			}
		}
		
		ResponseVo vo = new ResponseVo(true);
		vo.setDesc("处理成功");
		vo.setMessage(msg);
		return vo;
	}
	
	public ResponseVo delSKUActiveGoodInfo(String skuActiveId,String plantItemId){
		List<SKUActiveGoodInfoVo> list = querySKUActiveInfoVoList(skuActiveId);
		boolean eflag = false;
		SKUActiveGoodInfo info = new SKUActiveGoodInfo();
		for(SKUActiveGoodInfoVo vo:list){
			if(skuActiveId.equals(vo.getSKUActiveId()) && plantItemId.equals(vo.getPlantItemId())){
				eflag = true;
				info.setId(vo.getId());
				break;
			}
		}
		
		Map<String,Object> param = new HashMap<String,Object>();	
		param.put("id", info.getId());
		int count = skuActiveMgMapper.deleteSKUActiveGoodInfoById(param);
		String msg = "逻辑删除(id:"+ info.getId() + ";结果:"+count+")";
		logger.debug(msg);
		ResponseVo vo = new ResponseVo(true);
		vo.setDesc("处理成功");
		vo.setMessage(msg);
		return vo;
	}
	
	@Override
	public List<SKUActiveGoodInfoVo> querySKUActiveInfoVoList(String skuActiveId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("SKUActiveId", skuActiveId);
		logger.debug("查询的商品数据,参数:" + JSONUtil.objectToJSONString(param));
		List<SKUActiveGoodInfoVo> list = skuActiveMgMapper.getSKUActiveGoodInfoByMap(param);
		logger.debug("查询的商品数据,结果:" + JSONUtil.objectToJSONString(list));
		return list;
	}
	
	public ResponseVo updateSKUActiveGoodInfo(String skuActiveId,List<SKUActiveGoodInfo> listInfo){
		int countOk = 0;
		int countError = 0;
		ResponseVo vo = null;
		for(SKUActiveGoodInfo info:listInfo){
			if(info.getBuyLimitNum() == null){
				info.setBuyLimitNum(0);
			}
			if(info.getTotalLimitNum() == null){
				info.setTotalLimitNum(0);
			}
			vo = updateSKUActiveGoodInfo(skuActiveId, info);
			if(vo.isSuccess()){
				countOk ++;
			}else{
				countError ++ ;
			}
		}
		
		vo = new ResponseVo(true);
		vo.setDesc("处理成功");
		vo.setMessage("业务处理完成;成功("+countOk+");失败("+countError+")");
		return vo;
	}
	
	public ResponseVo updateSKUActiveGoodInfo(String skuActiveId,SKUActiveGoodInfo info){
		List<SKUActiveGoodInfoVo> list = querySKUActiveInfoVoList(skuActiveId);
		boolean eflag = false;
		for(SKUActiveGoodInfoVo vo:list){
			if(info.getSKUActiveId().equals(vo.getSKUActiveId()) 
					&& info.getPlantItemId().equals(vo.getPlantItemId())
					&& vo.getIsDelete() == 0){
				eflag = true;
				info.setId(vo.getId());
				info.setIsDelete((byte)0);
			}
		}
		int rs = 0;
		if(!eflag){
			rs = skuActiveGoodInfoMapper.insert(info);
		}else{
			rs = skuActiveGoodInfoMapper.updateByPrimaryKey(info);
		}
		
		if(1==rs){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("业务处理完成");
			return vo;
		}else{
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("业务处理完成");
			return vo;
		}
		
	}
	@Override
	public ResponseVo updateSKUActiveGoodInfoTagId(String skuActiveId, String plantItemId, String tagId) {
		List<SKUActiveGoodInfoVo> list = querySKUActiveInfoVoList(skuActiveId);
		boolean eflag = false;
		SKUActiveGoodInfo info = new SKUActiveGoodInfo();
		info.setSKUActiveId(skuActiveId);
		info.setPlantItemId(plantItemId);
		for(SKUActiveGoodInfoVo vo:list){
			if(skuActiveId.equals(vo.getSKUActiveId()) && plantItemId.equals(vo.getPlantItemId())){
				eflag = true;
				try {
					PropertyUtils.copyProperties(info, vo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		info.setTagId(tagId);
		info.setUpdateTime(new Date());
		int rs = skuActiveGoodInfoMapper.updateByPrimaryKey(info);
		if(1==rs){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("业务处理完成");
			return vo;
		}else{
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("业务处理完成");
			return vo;
		}
	}
	@Override
	public ResponseVo updateSKUActiveGoodInfoLimitNum(String skuActiveId, String plantItemId, Integer num) {
		List<SKUActiveGoodInfoVo> list = querySKUActiveInfoVoList(skuActiveId);
		boolean eflag = false;
		SKUActiveGoodInfo info = new SKUActiveGoodInfo();
		info.setSKUActiveId(skuActiveId);
		info.setPlantItemId(plantItemId);
		for(SKUActiveGoodInfoVo vo:list){
			if(skuActiveId.equals(vo.getSKUActiveId()) && plantItemId.equals(vo.getPlantItemId())){
				eflag = true;
				try {
					PropertyUtils.copyProperties(info, vo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		info.setBuyLimitNum(num);
		info.setUpdateTime(new Date());
		int rs = skuActiveGoodInfoMapper.updateByPrimaryKey(info);
		if(1==rs){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("业务处理完成");
			return vo;
		}else{
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("业务处理完成");
			return vo;
		}
	}
	
	public ResponseVo updateSKUActiveGoodInfototalLimitNum(String skuActiveId, String plantItemId, Integer num) {
		List<SKUActiveGoodInfoVo> list = querySKUActiveInfoVoList(skuActiveId);
		boolean eflag = false;
		SKUActiveGoodInfo info = new SKUActiveGoodInfo();
		info.setSKUActiveId(skuActiveId);
		info.setPlantItemId(plantItemId);
		for(SKUActiveGoodInfoVo vo:list){
			if(skuActiveId.equals(vo.getSKUActiveId()) && plantItemId.equals(vo.getPlantItemId())){
				eflag = true;
				try {
					PropertyUtils.copyProperties(info, vo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		info.setTotalLimitNum(num);
		info.setUpdateTime(new Date());
		int rs = skuActiveGoodInfoMapper.updateByPrimaryKey(info);
		if(1==rs){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("业务处理完成");
			return vo;
		}else{
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("业务处理完成");
			return vo;
		}
	}
	
	@Override
	public ResponseVo updateSKUActiveGoodInfoPrice(String skuActiveId, String plantItemId, BigDecimal price) {
		List<SKUActiveGoodInfoVo> list = querySKUActiveInfoVoList(skuActiveId);
		boolean eflag = false;
		SKUActiveGoodInfo info = new SKUActiveGoodInfo();
		info.setSKUActiveId(skuActiveId);
		info.setPlantItemId(plantItemId);
		for(SKUActiveGoodInfoVo vo:list){
			if(skuActiveId.equals(vo.getSKUActiveId()) && plantItemId.equals(vo.getPlantItemId())){
				eflag = true;
				try {
					PropertyUtils.copyProperties(info, vo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		info.setActivePrice(price);
		info.setUpdateTime(new Date());
		int rs = skuActiveGoodInfoMapper.updateByPrimaryKey(info);
		if(1==rs){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("业务处理完成");
			return vo;
		}else{
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("业务处理完成");
			return vo;
		}
	}
	
	public ResponseVo updateEffecSKUActiveGoodInfoPrice(String skuActiveId, String plantItemId, BigDecimal price) {
		List<SKUActiveGoodInfoVo> list = querySKUActiveInfoVoList(skuActiveId);
		boolean eflag = false;
		SKUActive skuActive = skuActiveMapper.selectByPrimaryKey(skuActiveId);
		SKUActiveGoodInfo info = new SKUActiveGoodInfo();
		info.setSKUActiveId(skuActiveId);
		info.setPlantItemId(plantItemId);
		for(SKUActiveGoodInfoVo vo:list){
			if(skuActiveId.equals(vo.getSKUActiveId()) && plantItemId.equals(vo.getPlantItemId())){
				eflag = true;
				try {
					PropertyUtils.copyProperties(info, vo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		info.setActivePrice(price);
		info.setUpdateTime(new Date());
		int rs = skuActiveGoodInfoMapper.updateByPrimaryKey(info);
		logger.debug("更新单品促销活动设置的价格，更新条数:" + rs);
		
		//处理单品对应的产品的价格内容
		if(skuActive.getStatus().intValue() == status_effec){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("plantItemId", plantItemId);
			param.put("SKUActiveId", skuActiveId);
			List<PlantItemProduct> listPP = skuActiveMgMapper.selectPlantItemProductBySKUActiveIdAndPlantItemId(param);
			if(null != listPP && listPP.size() > 0){
				param = new HashMap<String,Object>();
				param.put("productPrice", price);
				param.put("productId", listPP.get(0).getId());
				rs = skuActiveMgMapper.updateProductPrice4PlantItemProductById(param); 
				logger.debug("更新产品["+JSONUtil.objectToJSONString(listPP.get(0))+"]价格["+price+"]，更新条数:" + rs);
			}
		}
		
		if(1==rs){
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("业务处理完成");
			return vo;
		}else{
			ResponseVo vo = new ResponseVo(true);
			vo.setDesc("处理成功");
			vo.setMessage("业务处理完成");
			return vo;
		}
	}
	
	@Override
	public List<SKUActiveTagVo> selectSKUActivePublishTag() {
		return skuActiveMgMapper.selectSKUActivePublishTag(null);
	}
	
	public List<SKUActiveTagVo> querySKUActivePublishTag(String id){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("SKUActiveId", id);
		List<SKUActivePublishTagMap> listTag = skuActiveMgMapper.querySKUActivePublishTagMap(param);
		List<SKUActiveTagVo> list = selectSKUActivePublishTag();
		if(null != list && list.size() > 0 ){
			for(int i=0;i<list.size();i++){
				SKUActiveTagVo vo = list.get(i);
				if(null != listTag && listTag.size() > 0){
					for(SKUActivePublishTagMap one:listTag){
						if(one.getPublishTagId().equals(vo.getId())){
							vo.setChecked(1);
							list.set(i, vo);
						}
					}
				}
			}
		}
		return list;
	}
	
	public synchronized Map<String,Object> taskSKUActive(){
		logger.debug("定时执行任务，开始！");
		Map<String,Object> reMap = new HashMap<String,Object>();
		List<String> effeckList = new ArrayList<String>();
		List<String> invalidList = new ArrayList<String>();
		Map<String,Object> param = new HashMap<String,Object>();
		List<SKUActive> list = skuActiveMgMapper.getTaskAllSKUActive(param);
		for(final SKUActive vo:list){
			if(vo.getEndTime().compareTo(new Date()) <= 0 ) {
				//失效并停止
				invalidList.add(vo.getActiveName());
				stopSKUActiveByUser(vo.getId(), "job");
			}else if(vo.getStartTime().compareTo(new Date()) <= 0 && vo.getEndTime().compareTo(new Date()) >= 0 ){
				//生效
				if(vo.getEffecCount().equals(0)){
					effeckList.add(vo.getActiveName());
					Runnable r = new Runnable() {
						public void run() {
							effecSKUActive(vo.getId(), "job");
						}
					};
					r.run();
				}
			}
		}
		reMap.put("invalidList", invalidList);
		reMap.put("effeckList", effeckList);
		logger.debug("定时执行任务，结束！");
		return reMap;
	}
	
	/**
	 * 生效单品促销活动，处理产品的数据
	 * @Title: effecPlantItemProductTask
	 * @date 2016年10月14日  下午2:08:44
	 * @author 小武
	 * @version  七彩虹
	 * @param opUser
	 * @param activeId
	 * @return
	 */
	public boolean effecPlantItemProductTask(final String opUser,final String activeId){
		Runnable r = new Runnable() {
			@Override
			public void run() {
				int okCount = 0;
				int errorCount = 0;
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("SKUActiveId", activeId);
				SKUActive active = skuActiveMapper.selectByPrimaryKey(activeId);
				List<SKUActiveGoodInfo> glist = querySKUActiveGoodInfoByActiveId(activeId);
				List<SKUActivePublishTagMap> publishList = skuActiveMgMapper.querySKUActivePublishTagMap(param);
//				int count = skuActiveMgMapper.delPlantItemProductBySKUActiveId(param);
//				logger.debug("删除数据["+count+"]条");
				for(SKUActiveGoodInfo goodInfo : glist){
					
					//先判断是否存在，如果存在就更新，如果不存在就新增
					param.put("plantItemId", goodInfo.getPlantItemId());
					List<PlantItemProduct> listPP = skuActiveMgMapper.selectPlantItemProductBySKUActiveIdAndPlantItemId(param);
					
					if(null == listPP || listPP.size() == 0){
						boolean flag = effecSKUActive4AddPlantItemProduct(opUser, active, goodInfo, publishList);
						if(flag){
							okCount ++;
						}else{
							errorCount ++;
						}
						logger.debug("生效活动[" + active.getActiveName() + " "+active.getId()+"] 的新增商品["+goodInfo.getPlantItemId()+"]的对应的产品 结果:" + flag);
					}else{
						boolean flag = effecSKUActive4UpdatePlantItemProduct(opUser, active, goodInfo, publishList,listPP.get(0));
						if(flag){
							okCount ++;
						}else{
							errorCount ++;
						}
						logger.debug("生效活动[" + active.getActiveName() + " "+active.getId()+"] 的更新商品["+goodInfo.getPlantItemId()+"]的对应的产品 结果:" + flag);
					}
				}
				logger.info("生效活动[" + active.getActiveName() + " "+active.getId()+"],总数量["+glist.size()+"],成功["+okCount+"],失败["+errorCount+"]");
			}
		};
		r.run();
		return true;
	}
	
	/**
	 * 生效活动，处理产品数据，新增产品数据信息(单一产品)
	 * @Title: effecPlantItemProduct4Add
	 * @date 2016年10月14日  下午1:44:49
	 * @author 小武
	 * @version  七彩虹
	 * @param opUser
	 * @param active
	 * @param goodInfo
	 * @param publishList
	 * @return
	 */
	public boolean effecSKUActive4AddPlantItemProduct(String opUser,SKUActive active,SKUActiveGoodInfo goodInfo,List<SKUActivePublishTagMap> publishList){
		try{
			PlantItemProduct p = new PlantItemProduct();
			PlantItemVo pVo = spPlantItemMgMapper.getPlantItemById(goodInfo.getPlantItemId());
			p.setId(StringUtil.getUUID());
			p.setAddtime(new Date());
			p.setBuyStartTime(active.getBuyStartTime());
			p.setBuyEndTime(active.getBuyEndTime());
			p.setUpTime(active.getUpTime());
			p.setDownTime(active.getDownTime());
			p.setPkgName(pVo.getName());
			p.setProductPrice(goodInfo.getActivePrice());
			p.setSKUActiveId(active.getId());
			p.setBuyLimitNum(goodInfo.getBuyLimitNum());
			p.setTotalBuyNum(goodInfo.getTotalBuyNum());
			p.setTotalLimitNum(goodInfo.getTotalLimitNum());
			p.setAsDefault(false);
			p.setProMarketPrice(pVo.getPlantMemPrice() == null ? new BigDecimal(0):pVo.getPlantMemPrice());
			p.setPkgType((byte)0);
			p.setStatus((byte)1);
			p.setIsDelete(false);
			plantItemProductMapper.insert(p);
			
			PlantItemProductMap pm = new PlantItemProductMap();
			pm.setPkgPrice(goodInfo.getActivePrice());
			pm.setPlantItemId(goodInfo.getPlantItemId());
			pm.setProductId(p.getId());
			plantItemProductMapMapper.insertSelective(pm);
			
			for(SKUActivePublishTagMap tag:publishList){
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("tagId", tag.getPublishTagId());
				param.put("productId", p.getId());
				List<AppItemTagProductMapKey> list = skuActiveMgMapper.queryAppItemTagProductMap(param);
				if(list == null || list.size() == 0){
					AppItemTagProductMapKey k = new AppItemTagProductMapKey();
					k.setProductId(p.getId());
					k.setTagId(tag.getPublishTagId());
					appItemTagProductMapMapper.insert(k);
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 生效活动，处理产品数据，更新产品数据信息(单一产品)
	 * @Title: effecSKUActive4UpdatePlantItemProduct
	 * @date 2016年10月14日  下午2:08:02
	 * @author 小武
	 * @version  七彩虹
	 * @param opUser
	 * @param active
	 * @param goodInfo
	 * @param publishList
	 * @param p
	 * @return
	 */
	public boolean effecSKUActive4UpdatePlantItemProduct(String opUser,SKUActive active,SKUActiveGoodInfo goodInfo,List<SKUActivePublishTagMap> publishList,PlantItemProduct p){
		try{
			PlantItemVo pVo = spPlantItemMgMapper.getPlantItemById(goodInfo.getPlantItemId());
			p.setBuyStartTime(active.getBuyStartTime());
			p.setBuyEndTime(active.getBuyEndTime());
			p.setUpTime(active.getUpTime());
			p.setDownTime(active.getDownTime());
			p.setPkgName(pVo.getName());
			p.setProductPrice(goodInfo.getActivePrice());
			p.setBuyLimitNum(goodInfo.getBuyLimitNum());
			p.setTotalBuyNum(goodInfo.getTotalBuyNum());
			p.setTotalLimitNum(goodInfo.getTotalLimitNum());
			p.setProMarketPrice(pVo.getPlantMemPrice() == null ? new BigDecimal(0):pVo.getPlantMemPrice());
			p.setStatus((byte)1);
			p.setIsDelete(false);
			plantItemProductMapper.updateByPrimaryKeySelective(p);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 失效单品促销活动，修改产品状态。
	 * @Title: invalidPlantItemProductTask
	 * @date 2016年10月14日  下午2:43:57
	 * @author 小武
	 * @version  七彩虹
	 * @param opUser
	 * @param activeId
	 * @return
	 */
	public boolean invalidPlantItemProductTask(final String opUser,final String activeId){
		Runnable r = new Runnable() {
			@Override
			public void run() {
				//下架所有的产品数据，删除所有的标签关联数据
				int okCount = 0;
				int errorCount = 0;
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("SKUActiveId", activeId);
				SKUActive active = skuActiveMapper.selectByPrimaryKey(activeId);
//				List<SKUActiveGoodInfo> glist = querySKUActiveGoodInfoByActiveId(activeId);
				List<SKUActivePublishTagMap> publishList = skuActiveMgMapper.querySKUActivePublishTagMap(param);
				int rs = skuActiveMgMapper.invalidPlantItemProductBySKUActiveId(param);
				List<PlantItemProduct> pps = skuActiveMgMapper.selectPlantItemProductBySKUActiveId(param);
				for(PlantItemProduct p : pps){
					for(SKUActivePublishTagMap tag:publishList){
						AppItemTagProductMapKey k = new AppItemTagProductMapKey();
						k.setProductId(p.getId());
						k.setTagId(tag.getPublishTagId());
						int tagRs = appItemTagProductMapMapper.deleteByPrimaryKey(k);
						if(tagRs == 1){
							okCount ++;
						}else{
							errorCount ++;
						}
					}
				}
				logger.info(opUser + "失效活动[" + active.getActiveName() + " "+active.getId()+"],产品["+rs+"],标签成功["+okCount+"],标签失败["+errorCount+"]");
			}
		};
		r.run();
		return true;
	}
}
