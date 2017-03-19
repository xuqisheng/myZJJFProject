package com.corner.scms.service.sp.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ItemBase;
import com.corner.core.beans.PlantItem;
import com.corner.core.beans.ScmsOrderDetail;
import com.corner.core.beans.ScmsOrderInfo;
import com.corner.core.beans.ScmsSpSalePrice;
import com.corner.core.beans.ScmsStockLog;
import com.corner.core.beans.ScmsStore;
import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ItemBaseMapper;
import com.corner.core.dao.ScmsOrderDetailMapper;
import com.corner.core.dao.ScmsOrderInfoMapper;
import com.corner.core.dao.ScmsSpSalePriceMapper;
import com.corner.core.dao.ScmsStoreMapper;
import com.corner.core.dao.SpOrderInfoMapper;
import com.corner.core.dao.SupplierMapper;
import com.corner.core.enums.SocktOperateType;
import com.corner.core.utils.CreateOrderIdUtil;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.ScmsOrderInfoMgCondition;
import com.corner.scms.beans.ro.SpOrderInfoRo;
import com.corner.scms.beans.vo.OrderInfoVo;
import com.corner.scms.beans.vo.SpOrderDetailVo;
import com.corner.scms.beans.vo.SpOrderListVo;
import com.corner.scms.dao.ScmsOrderInfoMgMapper;
import com.corner.scms.dao.ScmsPlantItemMgMapper;
import com.corner.scms.dao.ScmsSpSalePriceMgMapper;
import com.corner.scms.dao.ScmsStoreMgMapper;
import com.corner.scms.dao.SpOrderMgMapper;
import com.corner.scms.service.callable.CallAbleService;
import com.corner.scms.service.sp.ScmsOrderInfoMgService;
import com.corner.scms.service.sp.ScmsStockLogMgService;
/**
 * 
 * @ClassName: ScmsOrderInfoMgServiceImpl 
 * @Description: 订单信息处理
 * @author 孟星魂	mengxinghun@izjjf.cn
 * @date 2015年12月4日 下午6:08:25 
 *
 */
@Service
public class ScmsOrderInfoMgServiceImpl implements ScmsOrderInfoMgService {

	private static Logger logger = LoggerFactory.getLogger(ScmsOrderInfoMgServiceImpl.class);

	@Autowired
	ScmsStoreMapper storeMapper;
	@Autowired
	ScmsOrderInfoMgMapper scmsOrderInfoMgMapper;

	@Autowired
	ScmsOrderInfoMapper scmsOrderInfoMapper;

	@Autowired
	ScmsOrderDetailMapper scmsOrderDetailMapper;

	@Autowired
	ScmsPlantItemMgMapper scmsPlantItemMgMapper;

	@Autowired
	ScmsSpSalePriceMgMapper scmsSpSalePriceMgMapper;
	@Autowired
	ScmsStoreMgMapper storeMgMapper;
	@Autowired
	ItemBaseMapper itemBaseMapper;
	@Autowired
	SupplierMapper supplierMapper;
	@Autowired
	ScmsSpSalePriceMapper scmsSpSalePriceMapper;
	
	@Autowired
	ScmsStockLogMgService scmsStockLogMgService;
	/*******************转角订单**************************/
	@Autowired
	private SpOrderMgMapper spOrderInfoMgMapper;
	
	@Autowired
	private SpOrderInfoMapper sPOrderInfoMapper;
	@Autowired
	CallAbleService callAbleService;

	@Override
	public ModelMsg deleteObjects(String tableName, String[] array) {

		return null;
	}

	@Override
	public Pager<ScmsOrderInfo> getScmsOrderInfoPageList(ScmsOrderInfoMgCondition command) {
		List<ScmsOrderInfo> list = scmsOrderInfoMgMapper.getPageList(command);
		List<ScmsOrderInfo> scmsOrderInfos = new ArrayList<>();
		for (ScmsOrderInfo scmsOrderInfo : list) {
			scmsOrderInfos.add(scmsOrderInfo);
		}

		int size = scmsOrderInfoMgMapper.getPageListSize(command);
		return new Pager<>(size,list);
	}

	@Override
	public ModelMsg updateByPrimaryKeySelective(ScmsOrderInfoMgCondition command) {
		ScmsOrderInfoMgCondition condition = scmsOrderInfoMgMapper.selectByOrderId(command.getOrderId());
		ScmsOrderInfo info = scmsOrderInfoMapper.selectByPrimaryKey(condition.getId());
		if(!StringUtil.stringIsNullOrEmpty(command.getStatus()) && "4".equals(command.getStatus())){
			/**更新打印状态**/
			info.setPrintTime(new Date());
			info.setStatus(Byte.parseByte(command.getStatus()));
		}else{
			info.setMobile(command.getMobile());
			info.setJsType(Byte.parseByte(command.getEditJsType()));
			info.setSupportStatus(Byte.parseByte(command.getEditSupportStatus()));
			info.setAddress(command.getAddress());
			info.setConsignee(command.getConsignee());
			info.setStoreName(command.getContact());
			info.setSpRemark(command.getSpRemark());
			info.setCol1(command.getEditStoreType()); 	//使用备用字段储存客户类型
		}
		info.setGaveTime(new Date());
		int result = scmsOrderInfoMapper.updateByPrimaryKeySelective(info);
		if(result == 1){
			ScmsOrderInfoMgCondition scmsOrderInfoMgCondition = scmsOrderInfoMgMapper.selectByOrderId(info.getOrderId());
			scmsOrderInfoMgCondition.setScmsOrderDetails(scmsOrderInfoMgMapper.getDetailByOrderId(info.getOrderId()));
			scmsOrderInfoMgCondition.setStoreType(command.getEditStoreType());
			return new ModelMsg(true,"修改成功",scmsOrderInfoMgCondition);	
		}else{
			return new ModelMsg(false,"修改失败");	
		}
	}
	
	public ScmsStore insertScmsStore(ScmsOrderInfoMgCondition command){
		ScmsStore scmsStore = new ScmsStore();
		
		scmsStore.setAddTime(new Date());
		scmsStore.setStatus((byte) 1);
		scmsStore.setAddress(command.getAddress());
		scmsStore.setMobile(command.getMobile());
		scmsStore.setContact(command.getConsignee());
		scmsStore.setName(command.getStoreName());
		scmsStore.setSuType(new Byte(command.getStoreType()));
		scmsStore.setSpId(command.getSupplierId());
		/**判断用户信息是否未录入过**/
		if(StringUtil.stringIsNullOrEmpty(command.getStoreId())){
			logger.info("无店铺信息，新增店铺信息");
			int result = storeMapper.insertSelective(scmsStore);
			if(result == 1){
				return storeMgMapper.findBySpidAndMobaile(scmsStore);
			}else{
				return scmsStore;	
			}
		}else{
			logger.info("有店铺信息，校验店铺信息");
			scmsStore = storeMgMapper.findById(Integer.parseInt(command.getStoreId()));
			if(scmsStore == null){
				logger.info("店铺ID未查找到记录，新增店铺信息");
				int result = storeMapper.insertSelective(scmsStore);
				if(result == 1){
					scmsStore = storeMgMapper.findBySpidAndMobaile(scmsStore);
				}else{
					return scmsStore;	
				}
			}else{
//				logger.info("验证  手机号、地址、店名    是否一致 ");
//				if(scmsStore.getMobile().equals(command.getMobile()) && scmsStore.getAddress().equals(command.getAddress()) && scmsStore.getName().equals(command.getSortName())){
//					logger.info("验证  手机号、地址、店名    :	验证结果true");
//					return scmsStore;
//				}else{
//					logger.info("验证  手机号、地址、店名    :	验证结果false,新增一条数据,上传信息："+JSONUtil.objectToJSONString(command) +" 数据库信息："+JSONUtil.objectToJSONString(scmsStore));
//					scmsStore.setAddTime(new Date());
//					scmsStore.setStatus((byte) 1);
//					scmsStore.setAddress(command.getAddress());
//					scmsStore.setMobile(command.getMobile());
//					scmsStore.setContact(command.getConsignee());
//					scmsStore.setName(command.getStoreName());
//					scmsStore.setSuType(new Byte(command.getStoreType()));
//					scmsStore.setSpId(command.getSupplierId());
//					int result = storeMapper.insertSelective(scmsStore);
//					if(result == 1){
//						scmsStore = storeMgMapper.findBySpidAndMobaile(scmsStore);
//					}else{
//						return scmsStore;	
//					}
//				}
					
			}
			
		}
		return scmsStore;
	}
	@Override
	public ModelMsg insert(ScmsOrderInfoMgCondition command) throws RuntimeException {
		logger.info("订单信息："+JSONUtil.objectToJSONString(command));
		
		ScmsOrderInfo info = new ScmsOrderInfo();
		info.setId(StringUtil.getUUID());
		info.setAddTime(new Date());//添加时间，当前
		info.setGetOrderTime(new Date());
		info.setOrderId(CreateOrderIdUtil.dateToString());// 订单编号生成规则
		
		ScmsStore scmsStore = new ScmsStore();
		logger.info("---------------验证店铺信息开始");
		scmsStore = insertScmsStore(command);
		if(scmsStore == null || scmsStore.getId() == null){
			logger.info("---------------验证店铺信息结束:验证结果false,交易正常进行");
			info.setStoreName(command.getSortName()); //店铺名称
			info.setConsignee(command.getConsignee());	//收件人
			info.setMobile(command.getMobile());	//收件人电话
			info.setUserTel(command.getUserTel());	//固定电话
			info.setAddress(command.getAddress());	//收货地址
		}else{
			logger.info("---------------验证店铺信息结束:验证结果true");
			info.setStoreId(scmsStore.getId());// 店铺ID
			info.setStoreName(scmsStore.getName()); //店铺名称
			info.setConsignee(scmsStore.getContact());	//收件人
			info.setMobile(scmsStore.getMobile());	//收件人电话
			info.setUserTel(scmsStore.getTel());	//固定电话
			info.setAddress(scmsStore.getAddress());	//收货地址
		}
		logger.info("---------------核验金额开始");
		
		Map<String, Object> map = new HashMap<>();
		BigDecimal totalPrice = new BigDecimal("0.00");
		int total = 0;
		for (int i = 0; i < command.getQuantity().length; i++) {
			total += Integer.parseInt(command.getQuantity()[i]);
			totalPrice = totalPrice.add(new BigDecimal(command.getPrice()[i]).multiply(new BigDecimal(command.getQuantity()[i])));
		}
		if(command.getGoodsPrice() != totalPrice){
			command.setGoodsPrice(totalPrice);
			logger.info("订单金额有误！");
		}
		
		/** 添加出货价记录开始 **/
		insertScmsSpSalePrice(command);
		
		info.setCol1(command.getStoreType()); 	//使用备用字段储存客户类型
		info.setJsType(Byte.parseByte(command.getJsType()));	//结算方式（1-现结，2-批结，3-周期结算，4-其他）
		info.setGoodsPrice(command.getGoodsPrice());	//
		info.setOrderPrice(command.getGoodsPrice());

		/**客服信息**/
		/**
		info.setKfCheckTime(new Date());
		info.setKfId(command.getKfId());
		info.setKfName(command.getKfName());
		info.setKfStatus(command.getKfStatus());
		info.setKfnote(command.getKfnote());
		 **/

		info.setUserId("");//小店老板ID
		info.setUserName("");//小店老板名称
		info.setUserRemark("");//用户下单备注
		info.setUserTel("");

		info.setStatus(Byte.parseByte("1"));	//订单状态 1:已下单 2：已派单  3:已提单 4:已打印 5:已送达 6:取消的订单
		info.setSupportStatus(Byte.parseByte(command.getSupportStatus()));	//支付状态
		if("1".equals(command.getSupportStatus()))
			info.setSupportTime(new Date());	//支付时间
		info.setSupportmetho(Byte.parseByte("2"));	//支付方式1、快钱支付2、货到付款 3、支付宝支付 4、微信支付
		info.setOrdertype(command.getOrdertype());	//订单配送方式：0送货上门，1当面付，2店面自提
		info.setGaveTime(new Date());


		/**一级批发商编号**/
		/**
		info.setSupplierId("");	//一级批发商编号
		info.setSupplierTel("");	//一级批发商手机号
		info.setSupplierNam("");	//一级批发商名称
		info.setSpStatus(null);	//批发商是否核对费用：1-费用未审核，2-费用已审核
		info.setSpRemark("");	//批发商备注
		info.setSpCheckTime(new Date());	//批发商最后核对时间

		info.setDeliveryTime(new Date());	//一级批发商发货时间
		info.setAckTime(new Date());	//小店老板收货时间
		info.setAckCode(new Short(""));	//小店老板收货码
		 **/

		info.setGetOrderTime(new Date());	//提单时间
		
		info.setSupplierId(command.getSupplierId());	//一级批发商编号
		Supplier supplier =  supplierMapper.selectByPrimaryKey(info.getSupplierId());
		info.setSupplierTel(supplier.getMobile());	//一级批发商手机号
		info.setSupplierNam(supplier.getSupplierName());	//一级批发商名称
		info.setSpRemark(command.getSpRemark());


		info.setLevel(Byte.parseByte("1"));	//订单层级：1-总订单，2-拆单后订单








		int result = scmsOrderInfoMapper.insertSelective(info);
		if(result == 0)
			throw new RuntimeException("订单生成失败");
		ScmsOrderInfoMgCondition scmsOrderInfoMgCondition = scmsOrderInfoMgMapper.selectByOrderId(info.getOrderId());
		List<ScmsOrderDetail> scmsOrderDetails = new ArrayList<>();

		for (int i = 0; i < command.getItemBaseName().length; i++) {
			/**添加子订单记录**/
			ScmsOrderDetail detail = new ScmsOrderDetail();
			detail.setId(StringUtil.getUUID());
			detail.setOrderId(info.getOrderId());
			detail.setStoreId(info.getStoreId());
			//detail.setSpGroupId();	//批发商组
			detail.setSpId(info.getSupplierId());	//批发商ID
			
			ItemBase base = itemBaseMapper.selectByPrimaryKey(Integer.parseInt(command.getItemBaseId()[i]));
			
			PlantItem item = new PlantItem();
			item.setItemBaseId(base.getId());
			item.setSpId(detail.getSpId());
			List<PlantItem> plantItems = scmsPlantItemMgMapper.selectPlantItemBySpIdAndBaseId(item);
			detail.setItemId(plantItems.get(0).getId());
			
			detail.setBarCode(base.getMdseId());
			detail.setName(base.getName());
			detail.setSpec(base.getSpec());
			detail.setQuantity(Short.valueOf(command.getQuantity()[i]));
			detail.setPrice(new BigDecimal(command.getPrice()[i]));
			detail.setTotalPrice(detail.getPrice().multiply(new BigDecimal(detail.getQuantity())));
			detail.setAddTime(new Date());
			logger.info("添加自订单信息，订单编号："+detail.getOrderId()+",子订单ID"+detail.getId());
			if(scmsOrderDetailMapper.insertSelective(detail) == 0){
				logger.debug("子订单信息插入失败，子订单ID"+detail.getId());
				throw new RuntimeException("子订单生成失败");
			}
			scmsOrderDetails.add(detail);
//			if(updatePlantItem(detail)){
//				logger.info("库存修正成功");
//			}else{
//				logger.info("库存修正失败");
//				return new ModelMsg(false,"订单生成失败,请联系管理员");
//			}
			
		}
		scmsOrderInfoMgCondition.setScmsOrderDetails(scmsOrderDetails);
		scmsOrderInfoMgCondition.setStoreType(scmsOrderInfoMgCondition.getCol1()); 	//使用备用字段储存客户类型
//		Map<String, Object> map2 = new HashMap<String, Object>();
//		map2.put("orderId", info.getOrderId());
//		map2.put("actype", "0");
//
//		ModelMsg modelMsg = updatePlantItemStock(map2);
//		modelMsg.setData(scmsOrderInfoMgCondition);
		ModelMsg modelMsg = new ModelMsg();
		try {
			callAbleService.socktOperateLog(SocktOperateType.Operate_10093 , scmsOrderInfoMgCondition.getOrderId());
			modelMsg.setSuccess(true);
			modelMsg.setMessage("添加成功");
			modelMsg.setData(scmsOrderInfoMgCondition);
		}catch (Exception e){
			logger.error(e.toString());
			modelMsg.setMessage(e.getMessage());
			modelMsg.setSuccess(false);
		}
		return modelMsg;
	}
	@Override
	public ModelMsg addScmsSpSalePrice(ScmsOrderInfoMgCondition price) {
		ScmsSpSalePrice scmsSpSalePrice = new ScmsSpSalePrice();
		
		PlantItem plantItem = new PlantItem();
		plantItem.setItemBaseId(Integer.parseInt(price.getBaseId()));
		plantItem.setSpId(price.getSupplierId());
		List<PlantItem> plantItems = scmsPlantItemMgMapper.selectPlantItemBySpIdAndBaseId(plantItem);
		if(plantItems == null){
			//TODO   无库存信息  是否添加库存
			logger.info("无对应库存信息");
			return new ModelMsg(false,"无对应库存信息");
		}else{
			scmsSpSalePrice.setPlantItemId(plantItems.get(0).getId());
			scmsSpSalePrice.setId(StringUtil.getUUID());
			scmsSpSalePrice.setStatus(new Byte("1"));	//状态为启用
			scmsSpSalePrice.setIsDelete(false);	
			scmsSpSalePrice.setAddTime(new Date());
			scmsSpSalePrice.setCusType(Byte.parseByte(price.getStoreType()));
			scmsSpSalePrice.setSalePrice(price.getSalePrice());
			int result = scmsSpSalePriceMapper.insertSelective(scmsSpSalePrice);
			if(result == 1){
				return new ModelMsg(true,"出货价新增成功");	
			}else{
				return new ModelMsg(false,"出货价失败,请联系管理员");	
			}
		}
	}
	/**
	 * 
	* @Title: insertScmsSpSalePrice 
	* @Description: 添加出货价记录 
	* @param @param item
	* @param @param condition
	* @param @return    设定文件
	* @return boolean    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	public void insertScmsSpSalePrice(ScmsOrderInfoMgCondition condition){
		try {
			PlantItem item = new PlantItem();
			ScmsSpSalePrice scmsSpSalePrice = new ScmsSpSalePrice();
			item.setSpId(condition.getSupplierId());
			Map<String, Object> map = new HashMap<>();
			for (int j = 0; j < condition.getItemBaseId().length; j++) {
				String itemBaseId = condition.getItemBaseId()[j];
				if(StringUtil.stringIsNullOrEmpty(itemBaseId))
					continue;
				map.put("spId" , condition.getSupplierId());
				map.put("itemBaseId" , itemBaseId);
				List<ScmsSpSalePrice> mappers = scmsSpSalePriceMgMapper.getPriceByCondition(map);
				
				if(mappers == null || mappers.size() == 0){
					scmsSpSalePrice.setId(StringUtil.getUUID());
					scmsSpSalePrice.setItemBaseId(Integer.parseInt(itemBaseId));
					scmsSpSalePrice.setSpId(condition.getSupplierId());
					scmsSpSalePrice.setStatus(new Byte("1"));	//状态为启用
					scmsSpSalePrice.setIsDelete(false);	
					scmsSpSalePrice.setAddTime(new Date());
					scmsSpSalePrice.setCusType(Byte.parseByte(condition.getStoreType()));
					scmsSpSalePrice.setSalePrice(new BigDecimal(condition.getPrice()[j]));
					int result = scmsSpSalePriceMapper.insertSelective(scmsSpSalePrice);
					logger.info("添加出货价记录：" + (result == 1));
				}else{
					logger.info("修改出货价记录");
					for (ScmsSpSalePrice spSalePrice : mappers) {
						if(Byte.parseByte(condition.getStoreType()) != spSalePrice.getCusType())
							continue;
						else{
							spSalePrice.setSalePrice(new BigDecimal(condition.getPrice()[j]));
							scmsSpSalePriceMapper.updateByPrimaryKeySelective(spSalePrice);
						}
							
					}
				}
			}
		} catch (Exception e) {
			logger.error("出货价添加失败," + e);
		}
	}
	/**
	 * 
	 * @Title: updatePlantItem 
	 * @Description: 更新库存信息 
	 * @param @param detail
	 * @param @return    设定文件
	 * @return boolean    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	public boolean updatePlantItem(ScmsOrderDetail detail){
		PlantItem item = new PlantItem();
		item.setItemBaseId(Integer.parseInt(detail.getItemId()));
		item.setSpId(detail.getSpId());
		List<PlantItem> plantItems = scmsPlantItemMgMapper.selectPlantItemBySpIdAndBaseId(item);
		int goodsStock = 0;
		if(plantItems != null && plantItems.size() != 0){
			goodsStock = plantItems.get(0).getGoodsStock();
			goodsStock = goodsStock - Integer.valueOf(detail.getQuantity());
			
			/**添加库存修改记录**/
			ScmsStockLog scmsStockLog = new ScmsStockLog();
			scmsStockLog.setId(StringUtil.getUUID());
			scmsStockLog.setAddTime(new Date());
			scmsStockLog.setIsDelete(false);
			scmsStockLog.setStatus(Byte.parseByte("1"));
			scmsStockLog.setSpId(detail.getSpId());
			scmsStockLog.setXtype(Byte.parseByte("1"));
			scmsStockLog.setPlantItemId(plantItems.get(0).getId());
			
			String num = "-" + detail.getQuantity().toString();
			scmsStockLog.setQuantity(Integer.parseInt(num));
			scmsStockLog.setCurStock(goodsStock);
			scmsStockLogMgService.addStockLog(scmsStockLog);
			
			/**计算库存量**/
			Map<String, Object> map = new HashMap<>();
			map.put("num", detail.getQuantity());
			map.put("spId", detail.getSpId());
			map.put("itemBaseId", item.getItemBaseId());
			map.put("updateTime", new Date());
			map.put("plantDisPrice", detail.getPrice());
			
			int result = scmsPlantItemMgMapper.updatePlantItemBySpIdAndItemBaseId(map); 
			if(result == 0)
				return false;
			return true;	
		}else{
			return false;
		}
	}

	@Override
	public ModelMsg updatePlantItemStock(Map<String, Object> map) throws RuntimeException {
		if(scmsPlantItemMgMapper.updatePlantItemStock(map) == 0)
			throw new RuntimeException("库存更新失败");
		if("0".equals(map.get("actype"))){
			List<ScmsOrderDetail> details = this.getDetailByOrderId(map.get("orderId").toString());
			for (ScmsOrderDetail detail : details) {
				/**添加库存修改记录**/
				ScmsStockLog scmsStockLog = new ScmsStockLog();
				scmsStockLog.setSpId(detail.getSpId());
				scmsStockLog.setPlantItemId(detail.getItemId());
				String num = "-" + detail.getQuantity().toString();
				scmsStockLog.setQuantity(Integer.parseInt(num));
				scmsStockLog.setXtype(Byte.parseByte("1"));
				if(scmsStockLogMgService.addStockLog(scmsStockLog) == 0)
					throw new RuntimeException("添加库存修改记录失败");
			}
		}else if("1".equals(map.get("actype"))){
			List<ScmsOrderDetail> details = this.getDetailByOrderId(map.get("orderId").toString());
			for (ScmsOrderDetail detail : details) {
				/**添加库存修改记录**/
				ScmsStockLog scmsStockLog = new ScmsStockLog();
				scmsStockLog.setSpId(detail.getSpId());
				scmsStockLog.setPlantItemId(detail.getItemId());
				String num = "+" + detail.getQuantity().toString();
				scmsStockLog.setQuantity(Integer.parseInt(num));
				scmsStockLog.setXtype(Byte.parseByte("3"));
				if(scmsStockLogMgService.addStockLog(scmsStockLog)==0)
					throw new RuntimeException("添加库存修改记录失败");
			}
		}
		return new ModelMsg(true, "库存更新成功");
	}
	@Override
	public Map<String, Object> getOrderTodayAll(String spId) {
		return scmsOrderInfoMgMapper.getOrderTodayAll(spId);
	}
	@Override
	public Map<String, Object> getScmsOrderTodayAll(String spId) {
		return scmsOrderInfoMgMapper.getScmsOrderTodayAll(spId);
	}

	@Override
	public ScmsOrderInfoMgCondition selectByOrderId(String orderId) {
		return scmsOrderInfoMgMapper.selectByOrderId(orderId);
	}
	@Override
	public ScmsOrderInfo selectById(String id) {
		return scmsOrderInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ScmsOrderDetail> getDetailByOrderId(String orderId) {
		return scmsOrderInfoMgMapper.getDetailByOrderId(orderId);
	}

	@Override
	public List<SpOrderInfo> getSpOrderInfospc(SpOrderInfoRo spOrderInfoRo) {
		// TODO Auto-generated method stub
		if(spOrderInfoRo.getPageSize()<=0){
			spOrderInfoRo.setPageSize(10);
		}
		if(spOrderInfoRo.getPageIndex()<=0){
			spOrderInfoRo.setPageIndex(1);
		}
		return spOrderInfoMgMapper.selectSpOrderInfoSelectivepc(spOrderInfoRo);
	}

	@Override
	public Integer selectSpOrderCountOfStatus(SpOrderInfoRo spOrderInfoRo) {
		// TODO Auto-generated method stub
		return spOrderInfoMgMapper.selectSpOrderCountOfStatuspc(spOrderInfoRo);
	}

	@Override
	public Supplier findSupplier(String id) {
		
		return supplierMapper.selectByPrimaryKey(id);
	}
	@Override
	public OrderInfoVo findByOrId(String orderId) {
		OrderInfoVo spOrderInfo = spOrderInfoMgMapper.findByOrId(orderId);
		spOrderInfo.setAddress(spOrderInfo.getSsqName()+spOrderInfo.getAddress());
		return spOrderInfo;
	}

	@Override
	public List<SpOrderDetail> getOrderDetail(String storeid) {
		return this.spOrderInfoMgMapper.getOrderDetail(storeid);
	}
	
	@Override
	public List<SpOrderDetailVo> getZjjfOrderDetail(String storeid) {
		return this.spOrderInfoMgMapper.getZjjfOrderDetail(storeid);
	}
	

	@Override
	public boolean updateOrder(SpOrderInfo spOrderInfo) {
		boolean rstFlag = false;
		Integer res = sPOrderInfoMapper.updateByPrimaryKeySelective(spOrderInfo);
		rstFlag = (res==1?true:false);
		if(rstFlag && spOrderInfo.getLevel()==2 && spOrderInfo.getStatus() != null){
			List<SpOrderListVo> list = spOrderInfoMgMapper.getDgOrderDetail(spOrderInfo.getpId());//根据Pid获取所有订单信息
			boolean isLastOrder = true;
			for (Iterator<SpOrderListVo> iterator = list.iterator(); iterator.hasNext();) {
				SpOrderListVo votmp = iterator.next();
				if(votmp.getLevel()!=null && votmp.getLevel()==2 
						&& votmp.getStatus() != null && votmp.getStatus() < spOrderInfo.getStatus() ){
					isLastOrder = false;
				}
			}
			if(isLastOrder){
				SpOrderInfo spOrdTemp= new SpOrderInfo();
				spOrdTemp.setId(spOrderInfo.getpId());//设置父id
				spOrdTemp.setStatus(spOrderInfo.getStatus());//设置状态
				spOrdTemp.setGetOrderTime(spOrderInfo.getGetOrderTime());
				spOrdTemp.setPrintTime(spOrderInfo.getPrintTime());
				spOrdTemp.setAckTime(spOrderInfo.getAckTime());
				Integer res2 = sPOrderInfoMapper.updateByPrimaryKeySelective(spOrdTemp);
				rstFlag = rstFlag && (res2==1?true:false);
			}
		}


		try {
			if(spOrderInfo.getStatus() != null && spOrderInfo.getStatus() == 4)
				callAbleService.socktOperateLog(SocktOperateType.Operate_10013 , spOrderInfo.getOrderId());
			else if(spOrderInfo.getLogisticsStatus() != null && spOrderInfo.getLogisticsStatus() == 1)
				callAbleService.socktOperateLog(SocktOperateType.Operate_10014 , spOrderInfo.getOrderId());
			else if(spOrderInfo.getLogisticsStatus() != null && spOrderInfo.getLogisticsStatus() == 4)
				callAbleService.socktOperateLog(SocktOperateType.Operate_10016 , spOrderInfo.getOrderId());
		}catch (Exception e){
			logger.error(e.toString());
			throw new RuntimeException(e.getMessage());
		}
		return rstFlag;
	}

	@Override
	public ModelMsg updateStoreType(ScmsOrderInfoMgCondition condition) {
		if("1".equals(condition.getIsDelete())){
			
		}
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> lists = new ArrayList<>();
		BigDecimal goodsPrice = new BigDecimal("0.00");
		int total= 0;
		Map<String, Object> detail = new HashMap<>();
		for (int i = 0; i < condition.getItemBaseId().length; i++) {
			detail = new HashMap<>();
			detail.put("itemBaseId",condition.getItemBaseId()[i]);
			detail.put("itemBaseName",condition.getItemBaseName()[i]);
			detail.put("price",condition.getPrice()[i]);
			
			/**获取商品出货价**/
			detail.put("itemBaseId", detail.get("itemBaseId"));
			detail.put("spId", condition.getSupplierId());
			detail.put("storeType", condition.getStoreType());
			if(!"1".equals(condition.getIsDelete())){
				ScmsSpSalePrice price = scmsPlantItemMgMapper.selectSalePrice(detail);
				if(price != null){
					if(price.getSalePrice() != null){
						try {
							detail.put("price", new BigDecimal(price.getSalePrice().toString()));
						} catch (Exception e) {
							logger.error("获取金额信息失败，请查看金额是否为空或者非法字符, ScmsSpSalePrice  ID"+price.getId());
						}
					}
				}
			}
			try {
				detail.put("quantity",condition.getQuantity()[i]);
				if(!StringUtil.stringIsNullOrEmpty(condition.getQuantity()[i])){
					try {
						total +=Integer.parseInt(condition.getQuantity()[i]);
					} catch (Exception e) {
						logger.error("计算总数量失败,上送数量："+condition.getQuantity()[i]);
					}
					if(!StringUtil.stringIsNullOrEmpty(detail.get("price").toString())){
						goodsPrice = goodsPrice.add(new BigDecimal(detail.get("price").toString()).multiply(new BigDecimal(condition.getQuantity()[i])));
					}
				}
				lists.add(detail);
			} catch (Exception e) {
				logger.error(i + "获取商品数量失败：" + condition.getQuantity());
				detail.put("quantity","");
				lists.add(detail);
			}
		}
		map.put("details", lists);
		map.put("goodsPrice", goodsPrice);
		map.put("total", total);
		
		return new ModelMsg(true, "交易成功",map);
	}

	@Override
	public ModelMsg deleteByPrimaryKeySelective(ScmsOrderInfoMgCondition condition) {
		ScmsOrderInfo info = new ScmsOrderInfo();
		info.setId(condition.getId());
		info.setIsDelete(true);
		info.setStatus(Byte.valueOf("6"));
		int result = scmsOrderInfoMapper.updateByPrimaryKeySelective(info);
		if(result == 0 )
			return new ModelMsg(false, "删除失败");
		List<ScmsOrderDetail> details = scmsOrderInfoMgMapper.getDetailByOrderId(info.getOrderId());
		for (ScmsOrderDetail scmsOrderDetail : details) {
			ScmsOrderDetail orderDetail = new ScmsOrderDetail();
			orderDetail.setId(scmsOrderDetail.getId());
			orderDetail.setIsDelete(true);
			result = scmsOrderDetailMapper.updateByPrimaryKeySelective(orderDetail);
			if(result == 0 )
				return new ModelMsg(false, "删除失败");
		}
		return new ModelMsg(true, "删除成功");
	}

	@Override
	public String getyewuyuanmobile(Integer storeId) {
		// TODO Auto-generated method stub
		return spOrderInfoMgMapper.getyewuyuanmobile(storeId);
	}

	@Override
	public List<SpOrderListVo> getCautionOrder(String Supplierid) {
		return spOrderInfoMgMapper.getCautionOrder(Supplierid);
	}


	@Override
	public SpOrderInfo getFuOrDerById(String id) {
		return sPOrderInfoMapper.selectByPrimaryKey(id);
	}

	
}
