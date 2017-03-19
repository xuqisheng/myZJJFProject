/**   
* @Title: ERPMarketStockInfoServiceImpl.java 
* @Package com.corner.scms.service.erp.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月8日 下午2:21:20 
* @version V1.0   
*/

package com.corner.scms.service.erp.impl;

import com.corner.core.beans.*;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.*;
import com.corner.core.enums.OrderPrefix;
import com.corner.core.enums.SocktOperateType;
import com.corner.core.enums.SpOrderOwnerType;
import com.corner.core.utils.CreateOrderIdUtil;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPMarketStockInfoRo;
import com.corner.scms.beans.ro.erp.SpOrderInfoMgRo;
import com.corner.scms.beans.vo.SpOrderDetailMgVo;
import com.corner.scms.beans.vo.SpOrderInfoMgVo;
import com.corner.scms.beans.vo.erp.ERPMarketStockDetailVo;
import com.corner.scms.beans.vo.erp.ERPMarketStockInfoVo;
import com.corner.scms.dao.SpOrderMgMapper;
import com.corner.scms.dao.erp.ERPMarketStockInfoMgMapper;
import com.corner.scms.dao.erp.ERPWarehouseMgMapper;
import com.corner.scms.service.callable.CallAbleService;
import com.corner.scms.service.erp.ERPMarketStockInfoService;
import com.corner.scms.service.erp.ERPMarketStockService;
import com.corner.scms.service.erp.ERPSpOrderOwnerService;
import com.corner.scms.service.sp.SpOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/** 
* @ClassName: ERPMarketStockInfoServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月8日 下午2:21:20 
*  
*/
@Service
public class ERPMarketStockInfoServiceImpl implements ERPMarketStockInfoService {

	@Autowired
	ERPMarketStockInfoMapper eRPMarketStockInfoMapper;
	
	@Autowired
	ERPMarketStockInfoMgMapper eRPMarketStockInfoMgMapper;
	
	@Autowired
	SpOrderMgMapper spOrderMgMapper;
	
	@Autowired
	ERPWarehouseMapper eRPWarehouseMapper;
	
	@Autowired
	SpOrderDetailMapper spOrderDetailMapper;
	
	@Autowired
	ERPMarketStockDetailMapper eRPMarketStockDetailMapper;
	
	@Autowired
	SpOrderInfoMapper spOrderInfoMapper;
	
	@Autowired
	ERPWarehouseMgMapper eRPWarehouseMgMapper;
	@Autowired
	ERPSpOrderOwnerService erpSpOrderOwnerService;
	@Autowired
	ERPMarketStockService erpMarketStockService;
	@Autowired
	CallAbleService callAbleService;
	@Autowired
	SpOrderService spOrderService;

	

	@Override
	public Pager<SpOrderInfoMgVo> getSpOrderInfoList(SpOrderInfoMgRo orderInfoMgRo) {
		List<SpOrderInfoMgVo> list = eRPMarketStockInfoMgMapper.getSpOrderInfoList(orderInfoMgRo);
		Integer count = 0;
		if(list!=null&&list.size()!=0){
			count = eRPMarketStockInfoMgMapper.getCountSpOrderInfoList(orderInfoMgRo);
		}
		return new Pager<>(count, list);
	}

	@Override
	public Pager<SpOrderDetailMgVo> getOrDerDetailList(SpOrderInfoMgRo orderInfoMgRo) {
		List<SpOrderDetailMgVo> list = eRPMarketStockInfoMgMapper.getOrDerDetailList(orderInfoMgRo);
		Integer count = 0;
		if(list!=null&&list.size()!=0){
			count = eRPMarketStockInfoMgMapper.getCountOrDerDetailList(orderInfoMgRo);
		}
		return new Pager<>(count,list);
	}

	@Override
	public ModelMsg save(Map<String, Object> paramMap) throws Exception{
		ModelMsg msg = new ModelMsg();
		msg.setSuccess(false);
		String whId = (String) paramMap.get("whId");
		String id = (String) paramMap.get("id");
		String spOrderId = (String) paramMap.get("spOrderId");
		//SpOrderDetail id
		String[] orderDetailIdArr = (String[]) paramMap.get("orderDetailIdArr");
		//出库操作数量
		String[] operateQuantityArr = (String[]) paramMap.get("operateQuantityArr");
		//库位id
		String[] wareHouseIdArr = (String[]) paramMap.get("wareHouseIdArr");
		//库位id
		String[] ids = (String[]) paramMap.get("ids");
		//已出库数量
		String[] operateStockArr = (String[]) paramMap.get("operateStockArr");
		
		Supplier supplier = (Supplier) paramMap.get("supplier");
		
		SpOrderInfo orderInfo =spOrderMgMapper.getSpOrDerInfoByOrderId(spOrderId);
		if(orderInfo==null||orderInfo.getIsDelete()){
			msg.setMessage("订单不存在或者被删除!");
			return msg;
		}
		
		//level=1 仓库
		ERPWarehouse erpWarehouseLevel1 = eRPWarehouseMapper.selectByPrimaryKey(whId);
		if(erpWarehouseLevel1==null||erpWarehouseLevel1.getStatus().intValue()==0||erpWarehouseLevel1.getIsDelete()){
			msg.setMessage("仓库不存在或被删除!");
		}
		
		
		Date date = new Date();
		
		ERPMarketStockInfo eRPMarketStockInfo = StringUtil.stringIsNullOrEmpty(id) ? new ERPMarketStockInfo() : erpMarketStockService.getInfoById(id);

		//修改和新建修改信息
		eRPMarketStockInfo.setWhId(erpWarehouseLevel1.getId());//仓库id
		eRPMarketStockInfo.setWhName(erpWarehouseLevel1.getName());//仓库name
		eRPMarketStockInfo.setTaskTime(date);//任务时间
		eRPMarketStockInfo.setItemPrice(orderInfo.getOrderPrice());

		//新建订单信息初始化
		if(StringUtil.stringIsNullOrEmpty(eRPMarketStockInfo.getId())){
			eRPMarketStockInfo.setId(StringUtil.getUUID());
			eRPMarketStockInfo.setOrderId(callAbleService.getStockOrderId(OrderPrefix.MarketStockOut));
			eRPMarketStockInfo.setpId(orderInfo.getId());
			eRPMarketStockInfo.setpOrderId(orderInfo.getOrderId());
			eRPMarketStockInfo.setSupplierId(supplier.getId());//Supplier id
			eRPMarketStockInfo.setSupplierName(supplier.getSupplierName());//Supplier name
			eRPMarketStockInfo.setStoreId(orderInfo.getStoreId()+"");//小店id
			eRPMarketStockInfo.setStoreName(orderInfo.getStoreName());//小店Name
			eRPMarketStockInfo.setStoreMobile(orderInfo.getMobile());//小店手机号
			eRPMarketStockInfo.setConsignee(orderInfo.getConsignee());//收货人
			eRPMarketStockInfo.setAddTime(date);//创建时间
			eRPMarketStockInfo.setAddUser(supplier.getId());//创建人id
			eRPMarketStockInfo.setAddUserName(supplier.getSupplierName());//创建人名称
		}else{
			//删除页面删除的商品
			for (ERPMarketStockDetail detail : erpMarketStockService.getDetailsByOrderId(eRPMarketStockInfo.getOrderId())) {
				detail.setIsDelete(true);
				Arrays.asList(ids).stream().filter(s -> detail.getId().equals(s)&&(!StringUtil.stringIsNullOrEmpty(s))).forEach(s -> detail.setIsDelete(false));
				if (detail.getIsDelete()){
					erpMarketStockService.updateDetail(detail);
				}
			}
		}

		//商品数量
		int itemQuantity = 0;
		BigDecimal itemPrice = new BigDecimal(0);
		List<ERPMarketStockDetail> details = new ArrayList<>();
		for(int i=0;i<orderDetailIdArr.length;i++){
			ERPMarketStockDetail detail = new ERPMarketStockDetail();
			detail.setId(ids == null || ids.length == 0 ? "" : ids[i]);
			detail.setOrderId(eRPMarketStockInfo.getOrderId());
			SpOrderDetail orderDetail = spOrderDetailMapper.selectByPrimaryKey(orderDetailIdArr[i]);
			BigDecimal deleteMoney = new BigDecimal(0);
			BigDecimal allMoney = new BigDecimal(0);
			if(orderDetail == null){	//添加附属订单信息
				ERPSpOrderOwnerDetail erpSpOrderOwnerDetail = erpSpOrderOwnerService.getDetailById(orderDetailIdArr[i]);
				detail.setpId(erpSpOrderOwnerDetail.getId());
				detail.setItemBaseId(erpSpOrderOwnerDetail.getItemBaseId());
				detail.setBarCode(erpSpOrderOwnerDetail.getBarCode());
				detail.setName(erpSpOrderOwnerDetail.getName());
				detail.setSpec(erpSpOrderOwnerDetail.getSpec());
				detail.setQuantity(erpSpOrderOwnerDetail.getCashQuantity());
				detail.setOperateQuantity(erpSpOrderOwnerDetail.getOutStockNum());
				detail.setPrice(new BigDecimal(0));//商品单价
				detail.setRemark(erpSpOrderOwnerDetail.getRemark());
			}else{
				detail.setpId(orderDetail.getId());
				detail.setItemBaseId(orderDetail.getItemBaseId());
				detail.setBarCode(orderDetail.getBarCode());
				detail.setName(orderDetail.getName());//商品名称
				detail.setSpec(orderDetail.getSpec());//商品规格
				detail.setImg(orderDetail.getImg());//商品图片
				detail.setQuantity(orderDetail.getQuantity());//购买数量
				Short outStockNum = orderDetail.getOutStockNum();
				if(outStockNum==null){
					outStockNum=0;
				}
				detail.setOperateQuantity(outStockNum);//已出库数量
				detail.setPrice(orderDetail.getPrice());//商品单价
			}
			detail.setWh3Id(wareHouseIdArr[i]);//库位id
			detail.setOperateStock(Short.parseShort(operateQuantityArr[i]));//出库数量
			ERPWarehouse erpWarehouseLevel3 = eRPWarehouseMapper.selectByPrimaryKey(wareHouseIdArr[i]);
			detail.setWh3Name(erpWarehouseLevel3.getName());//库位名称
			ERPWarehouse erpWarehouseLevel2 = eRPWarehouseMapper.selectByPrimaryKey(erpWarehouseLevel3.getUpId());
			detail.setWh2Name(erpWarehouseLevel2.getName());//库区名称
			detail.setWh1Name(erpWarehouseLevel1.getName());//仓库名称
			detail.setAddTime(date);
			detail.setTotalPrice(new BigDecimal(detail.getOperateStock()).multiply(detail.getPrice()));
			details.add(detail);

			deleteMoney = new BigDecimal(detail.getQuantity() - detail.getOperateStock()).multiply(detail.getPrice());
			itemQuantity+=detail.getOperateStock().intValue();
			//itemPrice=itemPrice.subtract(deleteMoney);
			eRPMarketStockInfo.setItemPrice(eRPMarketStockInfo.getItemPrice().subtract(deleteMoney));
		}
		//订单商品删除金额扣除
		spOrderService.getOtherDetail(null ,orderInfo.getOrderId() , Arrays.asList(orderDetailIdArr)).forEach(detail -> eRPMarketStockInfo.setItemPrice(eRPMarketStockInfo.getItemPrice().subtract(detail.getTotalPrice())));

		eRPMarketStockInfo.setItemQuantity(Short.parseShort(itemQuantity+""));
		//eRPMarketStockInfo.setItemPrice(itemPrice);
		for (ERPSpOrderOwnerDetail erpSpOrderOwnerDetail : erpSpOrderOwnerService.getOwnerDetail(eRPMarketStockInfo.getpId(), SpOrderOwnerType.MONEY)) {
			eRPMarketStockInfo.setItemPrice(eRPMarketStockInfo.getItemPrice().subtract(erpSpOrderOwnerDetail.getCashPrice()));
		}
		Integer reult = StringUtil.stringIsNullOrEmpty(id) ? eRPMarketStockInfoMapper.insertSelective(eRPMarketStockInfo):eRPMarketStockInfoMapper.updateByPrimaryKey(eRPMarketStockInfo);
		if (reult == 1){
			for (ERPMarketStockDetail detail : details) {
				if(StringUtil.stringIsNullOrEmpty(detail.getId())){
					detail.setId(StringUtil.getUUID());
					erpMarketStockService.insertDetail(detail);
				}else
					erpMarketStockService.updateDetail(detail);
			}
		}else
			return new ModelMsg(false , "操作失败");
		msg.setData(eRPMarketStockInfo.getId());
		msg.setSuccess(true);
		return msg;
	}

	@Override
	public Pager<ERPMarketStockInfoVo> getSaleOutList(ERPMarketStockInfoRo infoRo) {
		List<ERPMarketStockInfoVo> list = eRPMarketStockInfoMgMapper.getSaleOutList(infoRo);
		Integer count = 0 ;
		if(list!=null&&list.size()!=0){
			count = eRPMarketStockInfoMgMapper.getCountSaleOutList(infoRo);
		}
		return new Pager<>(count,list);
	}

	@Override
	public ERPMarketStockInfoVo getERPMarketStockInfoVoById(ERPMarketStockInfoRo infoRo) {
		return eRPMarketStockInfoMgMapper.getERPMarketStockInfoVoById(infoRo);
	}

	@Override
	public List<ERPMarketStockDetailVo> getStockDetailList(ERPMarketStockInfoVo erpMarketStockInfoVo) {
		return eRPMarketStockInfoMgMapper.getStockDetailList(erpMarketStockInfoVo);
	}

	@Override
	public ModelMsg updateStatus(Map<String, Object> paramMap) {
		ModelMsg msg = new ModelMsg();
		msg.setSuccess(false);
		String[] idArr = (String[]) paramMap.get("idArr");
		if(idArr==null){
			msg.setMessage("缺少要审核的出库单id！");
			return msg;
		}
		if(idArr.length>1){
			msg.setMessage("一次只能审核一个出库单！");
			return msg;
		}
		
		ERPMarketStockInfo stockInfo = eRPMarketStockInfoMapper.selectByPrimaryKey(idArr[0]);
		if(stockInfo==null||stockInfo.getIsDelete()){
			msg.setMessage("出库单不存在或者已被删除！");
			return msg;
		}
		//获取出库单详情
		List<ERPMarketStockDetail> stockDetails = eRPMarketStockInfoMgMapper.getMarketStockDetails(stockInfo);
		for (ERPMarketStockDetail erpMarketStockDetail : stockDetails) {
			SpOrderDetail spOrderDetail = spOrderDetailMapper.selectByPrimaryKey(erpMarketStockDetail.getpId());
			int chuKu = erpMarketStockDetail.getOperateStock().intValue();
			int yiChuKu = spOrderDetail.getOutStockNum()==null?0:spOrderDetail.getOutStockNum().intValue();
			int xiaoShou = spOrderDetail.getQuantity().intValue();
			if(chuKu+yiChuKu>xiaoShou){
				msg.setMessage("该笔销售订单出库数据异常,请重新编辑或生成新的出库单!");
				return msg;
			}
		}
		//修改SpOrderDetail outStockNum
		eRPMarketStockInfoMgMapper.updateSpOrderInfoStockNum(stockDetails);
		//判断是否要修改SpOrDerInfo 中的isOutStock
		List<SpOrderDetail> spOrderDetails = eRPMarketStockInfoMgMapper.getSpOrDerDetails(stockInfo);
		if(spOrderDetails==null||spOrderDetails.size()==0){
        	SpOrderInfo orderInfo = eRPMarketStockInfoMgMapper.getSpOrderInfoById(stockDetails.get(0).getpId());
        	eRPMarketStockInfoMgMapper.updateSpOrderInfoIsOutStock(orderInfo);
        }
		eRPMarketStockInfoMgMapper.updateStatus(paramMap);
		msg.setSuccess(true);
		return msg;
	}
	@Override
	public void updateOutStock(Map<String, Object> paramMap) throws Exception{
		
		eRPMarketStockInfoMgMapper.updateOutStock(paramMap);
		
		//修改SpOrDerInfo 状态
		String[] idArr = (String[]) paramMap.get("idArr");
		List<ERPMarketStockInfoVo> marketStockInfoVos = eRPMarketStockInfoMgMapper.getListByIds(idArr);
		for (ERPMarketStockInfoVo erpMarketStockInfoVo : marketStockInfoVos) {
			callAbleService.socktOperateLog(SocktOperateType.Operate_10075 , erpMarketStockInfoVo.getOrderId());

			SpOrderInfo spOrderInfo = new SpOrderInfo();
			spOrderInfo.setOrderId(erpMarketStockInfoVo.getpOrderId());//ERPMarketStockInfo 存的是level=2的SpOrDerInfo
			spOrderInfo.setId(erpMarketStockInfoVo.getpId());
			
			spOrderMgMapper.updateStatusEquals4(spOrderInfo);
			
			//判断是否要修改父订单的status
			List<SpOrderInfo> spOrderInfos = spOrderMgMapper.getAllSendSpOrDerList(spOrderInfo);
			if(spOrderInfos==null||spOrderInfos.size()==0){
				spOrderMgMapper.updateFuOrDderStatusEquals4(spOrderInfo);
			}
			
			//这里只是对子单进行发货处理,不知道要不要对父单发货处理
			//只有isOutStock=1才是发货完成
			//只有同一笔SpOrderInfo 的所有出库单都已经出库了才能修改SpOrderInfo
			/*List<ERPMarketStockInfoVo> allSendStockVo = eRPMarketStockInfoMgMapper.getAllSendStockVo(erpMarketStockInfoVo);
			if(allSendStockVo==null||allSendStockVo.size()==0){
				spOrderMgMapper.updateStatusEquals4(spOrderInfo);
				
				//判断是否要修改父订单status
				SpOrderInfo orderInfoRo = spOrderInfoMapper.selectByPrimaryKey(spOrderInfo.getId());
				List<SpOrderInfo> spOrderInfos = spOrderMgMapper.getAllSendSpOrDerList(orderInfoRo);
				if(spOrderInfos==null||spOrderInfos.size()==0){
					spOrderMgMapper.updateFuOrDderStatusEquals4(orderInfoRo);
				}
			}*/
		}
	}

	@Override
	public void updateSend(Map<String, Object> paramMap) throws Exception{
		//callAbleService.socktOperateLog(SocktOperateType.Operate_10076 , erpSpOrderOwnerService.);
		
		eRPMarketStockInfoMgMapper.updateSend(paramMap);
		
		//修改SpOrDerInfo 状态
		/*String[] idArr = (String[]) paramMap.get("idArr");
		List<ERPMarketStockInfoVo> marketStockInfoVos = eRPMarketStockInfoMgMapper.getListByIds(idArr);
		for (ERPMarketStockInfoVo erpMarketStockInfoVo : marketStockInfoVos) {
			SpOrderInfo spOrderInfo = new SpOrderInfo();
			spOrderInfo.setOrderId(erpMarketStockInfoVo.getpOrderId());
			spOrderInfo.setId(erpMarketStockInfoVo.getpId());
			//这里只是对子单进行送达处理
			//只有在isOutStock=1的前提下才能确认送达
			//该笔SpOrderInfo 对应的所有销售出库单必须都已经确认送达才可以确认送达
			List<ERPMarketStockInfoVo> allConfrimStokVo = eRPMarketStockInfoMgMapper.getAllConfrimStokVo(erpMarketStockInfoVo);
			if(allConfrimStokVo==null||allConfrimStokVo.size()==0){
				spOrderMgMapper.updateStatusEquals5(spOrderInfo);
				//判断是否要对父订单进行已送达处理
				SpOrderInfo orderInfoRo = spOrderInfoMapper.selectByPrimaryKey(spOrderInfo.getId());
				List<SpOrderInfo> spOrderInfos = spOrderMgMapper.getAllNotSendSpOrDerInfoList(orderInfoRo);
				if(spOrderInfos==null||spOrderInfos.size()==0){
					spOrderMgMapper.updateFuOrderStatusEquals5(orderInfoRo);
				}
			}
		
		}*/
		
	}

	@Override
	public void updateOrderBack(Map<String, Object> paramMap) {
		eRPMarketStockInfoMgMapper.updateOrderBack(paramMap);
	}

	@Override
	public void updateDel(Map<String, Object> paramMap) {
		String[] idArr = (String[]) paramMap.get("idArr");
		List<ERPMarketStockInfoVo> list = eRPMarketStockInfoMgMapper.getListByIds(idArr);
		if(list!=null&&list.size()!=0){
			eRPMarketStockInfoMgMapper.updateBatchDelete(list);
			eRPMarketStockInfoMgMapper.updateBatchDetailDelete(list);
		}
	}

	@Override
	public Pager<Map<String, Object>> getErpMarketStockInfos(ERPMarketStockInfoRo stockInfoRo) {
		List<Map<String, Object>> list = eRPMarketStockInfoMgMapper.getErpMarketStockInfos(stockInfoRo);
		Integer count = 0;
		if(list!=null&&list.size()!=0){
			count = eRPMarketStockInfoMgMapper.getCountErpMarketStockInfos(stockInfoRo);
		}
		return new Pager<>(count,list);
	}

	/**
	 * 
	* @Title: getStockDetailListByStockInfoId 
	* @Description:查询ERPMarketStockDetail 集合
	* @param @param id ERPMarketStockInfo 集合
	* @param @return ERPMarketStockDetail 集合
	* @return List<ERPMarketStockInfoVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public List<ERPMarketStockDetailVo> getStockDetailListByStockInfoId(String id) {
		return eRPMarketStockInfoMgMapper.getStockDetailListByStockInfoId(id);
	}

	/**
	 * 
	* Title: addSaleBack 
	* Description:保存销售退货单 
	* @param paramMap
	* @return 
	* @see com.corner.scms.service.erp.ERPMarketStockInfoService#addSaleBack(java.util.Map)
	 */
	@Override
	public ModelMsg addSaleBack(Map<String, Object> paramMap) {
		ModelMsg msg = new ModelMsg();
		msg.setSuccess(false);
		
		//销售出库单
		String marketStockInfoId = (String) paramMap.get("marketStockInfoId");
		//ERPMarketStockDetail id 数组
		String[] detailIdArr = (String[]) paramMap.get("detailIdArr");
		//operateStockArr 退货数量
		String[] operateStockArr = (String[]) paramMap.get("operateStockArr");
		//备注信息
		String[] remarkArr = (String[]) paramMap.get("remarkArr");
		//登陆的supplier
		Supplier supplier = (Supplier) paramMap.get("supplier");
		
		ERPMarketStockInfo saleOutStockInfo = eRPMarketStockInfoMapper.selectByPrimaryKey(marketStockInfoId);
		if(saleOutStockInfo==null||saleOutStockInfo.getIsDelete()){
			msg.setMessage("销售出库单不存在或被删除!");
			return msg;
		}
		List<ERPMarketStockDetail> saleOutDetails = eRPMarketStockInfoMgMapper.getMarketStockDetails(saleOutStockInfo);
		if(saleOutDetails==null||saleOutDetails.size()==0){
			msg.setMessage("该笔销售出库单没有详情数据!");
			return msg;
		}
		
		Date addDate = new Date();
		
		//新建销售退货对象
		ERPMarketStockInfo saleBack = new ERPMarketStockInfo();
		saleBack.setId(StringUtil.getUUID());
		saleBack.setpId(saleOutStockInfo.getId());//退货单的pId为出库单的id
		saleBack.setpOrderId(saleOutStockInfo.getOrderId());//退货的的PId为出库单的orderId
		saleBack.setOrderId(OrderPrefix.MarketStockIN.getPrefix()+CreateOrderIdUtil.dateToString());
		saleBack.setWhId(saleOutStockInfo.getWhId());
		saleBack.setWhName(saleOutStockInfo.getWhName());
		saleBack.setAddUser(supplier.getId());
		saleBack.setAddUserName(supplier.getSupplierName());
		saleBack.setAddTime(addDate);
		saleBack.setTaskTime(addDate);
		saleBack.setStoreId(saleOutStockInfo.getStoreId());
		saleBack.setStoreName(saleOutStockInfo.getStoreName());
		saleBack.setSupplierId(saleOutStockInfo.getSupplierId());
		saleBack.setSupplierName(saleOutStockInfo.getSupplierName());
		saleBack.setLevel((byte)2);//销售退货
		
		BigDecimal itemPrice = new BigDecimal("0");//商品金额
		Integer itemQuantity = 0;//商品数量(退货单商品数量就是退货数量)
		//生成销售退货详情
		List<ERPMarketStockDetail> saleBackList = new ArrayList<>();
		for(int i=0;i<detailIdArr.length;i++){
           	ERPMarketStockDetail saleBackDetail = eRPMarketStockDetailMapper.selectByPrimaryKey(detailIdArr[i]);
           	ERPMarketStockDetail newSaleBackDetail = new ERPMarketStockDetail();
           	BeanUtils.copyProperties(saleBackDetail, newSaleBackDetail);
           	newSaleBackDetail.setId(StringUtil.getUUID());
           	newSaleBackDetail.setpId(saleBackDetail.getId());
           	newSaleBackDetail.setOrderId(saleBack.getOrderId());
           	newSaleBackDetail.setOperateStock(Short.parseShort(operateStockArr[i]));//退货数量
            newSaleBackDetail.setAddTime(addDate);   	
           	itemPrice = itemPrice.add(newSaleBackDetail.getTotalPrice());
           	itemQuantity+=Integer.parseInt(operateStockArr[i]);
		}
        saleBack.setItemPrice(itemPrice);
        saleBack.setItemQuantity(Short.parseShort(itemQuantity+""));
        
        eRPMarketStockInfoMapper.insertSelective(saleBack);
        for (ERPMarketStockDetail erpMarketStockDetail : saleBackList) {
			eRPMarketStockDetailMapper.insertSelective(erpMarketStockDetail);
		}
		msg.setSuccess(true);
		return msg;
	}

	/**
	 * 
	* @Title: selectMarketStockVoById 
	* @Description:连表查询(spOrDerInfo) ERPMarketStockInfoVo
	* @param @param stockInfoRo
	* @param @return
	* @return ERPMarketStockInfoVo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public ERPMarketStockInfoVo selectMarketStockVoById(ERPMarketStockInfoRo stockInfoRo) {
		return eRPMarketStockInfoMgMapper.selectMarketStockVoById(stockInfoRo);
	}

	
	/**
	 * 
	* @Title: selectMarketStockDetail 
	* @Description:连表查询(spOrderDetail)  ERPMarketStockDetailVo
	* @param @param stockInfoVo
	* @param @return
	* @return List<ERPMarketStockDetailVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public List<ERPMarketStockDetailVo> selectMarketStockDetail(ERPMarketStockInfoVo stockInfoVo) {
		return eRPMarketStockInfoMgMapper.selectMarketStockDetail(stockInfoVo);
	}

	/**
	 * 
	* @Title: updateSaleOut 
	* @Description:编辑销售出库单
	* @param @param paramMap
	* @param @return
	* @return ModelMsg    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public ModelMsg updateSaleOut(Map<String, Object> paramMap) {
		ModelMsg msg = new ModelMsg();
		msg.setSuccess(false);
		//区位id数组
		String[] wareHouseIdArr = (String[]) paramMap.get("wareHouseIdArr");
		//出库数量数组
		String[] operateQuantityArr = (String[]) paramMap.get("operateQuantityArr");
		
		//SpOrderDetailid 数组
		String[] orderDetailIdArr = (String[]) paramMap.get("orderDetailIdArr");
		
		Supplier supplier = (Supplier) paramMap.get("supplier");
		
		ERPMarketStockInfoRo stockInfoRo = (ERPMarketStockInfoRo) paramMap.get("stockInfoRo");
		
		//查询 ERPMarketStockInfo
		ERPMarketStockInfo stockInfo = eRPMarketStockInfoMapper.selectByPrimaryKey(stockInfoRo.getId());
		if(stockInfo==null||stockInfo.getIsDelete()){
		  msg.setMessage("该笔销售出库单不存在或被删除!");
		  return msg;
		}
		//查询仓库
		ERPWarehouse warehouse = eRPWarehouseMapper.selectByPrimaryKey(stockInfoRo.getWhId());
		if(warehouse==null||warehouse.getIsDelete()){
			  msg.setMessage("所选仓库不存在或被删除!");
			  return msg;
			}
		Date addDate = new Date();
		//修改 ERPMarketStockInfo 确保数据正常
		stockInfo.setWhId(warehouse.getId());
		stockInfo.setWhName(warehouse.getName());
		stockInfo.setAddUser(supplier.getId());
		stockInfo.setAddUserName(supplier.getSupplierName());
		stockInfo.setCheckStatus((byte)1);
		stockInfo.setLevel((byte)1);
		stockInfo.setLogisticsStatus((byte)1);
		stockInfo.setIsSaleBack(false);
		stockInfo.setAddTime(addDate);
		//通过页面控制
		/*stockInfo.setCheckUser("");
		stockInfo.setCheckTime(null);*/

		//商品数量
		int itemQuantity = 0;
		BigDecimal itemPrice = new BigDecimal(0);
		
		//修改ERPMarketStockDetail
		List<ERPMarketStockDetail> stockDetails = new ArrayList<>();
		for(int i=0;i<orderDetailIdArr.length;i++){
			SpOrderDetail orderDetail = spOrderDetailMapper.selectByPrimaryKey(orderDetailIdArr[i]);
			ERPMarketStockDetail stockDetail = new ERPMarketStockDetail();
			stockDetail.setId(StringUtil.getUUID());
			stockDetail.setpId(orderDetail.getId());
			stockDetail.setOrderId(stockInfo.getOrderId());
			stockDetail.setItemBaseId(orderDetail.getItemBaseId());
			stockDetail.setBarCode(orderDetail.getBarCode());
			stockDetail.setName(orderDetail.getName());//商品名称
			stockDetail.setSpec(orderDetail.getSpec());//商品规格
			stockDetail.setImg(orderDetail.getImg());//商品图片
			stockDetail.setQuantity(orderDetail.getQuantity());//购买数量
			Short outStockNum = orderDetail.getOutStockNum();
			if(outStockNum==null){
				outStockNum=0;
			}
			stockDetail.setOperateQuantity(outStockNum);//已出库数量
			stockDetail.setOperateStock(Short.parseShort(operateQuantityArr[i]));//出库数量
			stockDetail.setPrice(orderDetail.getPrice());//商品单价
			stockDetail.setTotalPrice(orderDetail.getTotalPrice());
			
			stockDetail.setWh3Id(wareHouseIdArr[i]);//库位id
			ERPWarehouse erpWarehouseLevel3 = eRPWarehouseMapper.selectByPrimaryKey(wareHouseIdArr[i]);
			stockDetail.setWh3Name(erpWarehouseLevel3.getName());//库位名称
			ERPWarehouse erpWarehouseLevel2 = eRPWarehouseMapper.selectByPrimaryKey(erpWarehouseLevel3.getUpId());
			stockDetail.setWh2Name(erpWarehouseLevel2.getName());//库区名称
			stockDetail.setWh1Name(warehouse.getName());//仓库名称
			stockDetail.setAddTime(addDate);
			stockDetails.add(stockDetail);
		
			itemQuantity+=orderDetail.getQuantity().intValue();
			itemPrice=itemPrice.add(orderDetail.getTotalPrice());
		}
		
		stockInfo.setItemQuantity(Short.parseShort(itemQuantity+""));
		stockInfo.setItemPrice(itemPrice);
		
		eRPMarketStockInfoMapper.updateByPrimaryKeySelective(stockInfo);
		//清理ERPMarketStockDetail
		eRPMarketStockInfoMgMapper.deleteERPMarketStockDetailByOrderId(stockInfo);
		for (ERPMarketStockDetail erpMarketStockDetail : stockDetails) {
			eRPMarketStockDetailMapper.insertSelective(erpMarketStockDetail);
		}
	    msg.setSuccess(true);
		return msg;
	}

	/**
	 * 
	* Title: updateSaleBackStatus 
	* Description:审核退货单 
	* @param paramMap
	* @return 
	* @see com.corner.scms.service.erp.ERPMarketStockInfoService#updateSaleBackStatus(java.util.Map)
	 */
	@Override
	public ModelMsg updateSaleBackStatus(Map<String, Object> paramMap) {
		ModelMsg msg = new ModelMsg();
		msg.setSuccess(false);
		
		String[] idArr = (String[]) paramMap.get("idArr");
		Supplier supplier = (Supplier) paramMap.get("supplier");
		
		if(idArr==null){
			msg.setMessage("缺少退货单id!");
			return msg;
		}
		if(idArr.length>1){
			msg.setMessage("一次只能审核一个退货单!");
			return msg;
		}
		
		ERPMarketStockInfo saleBack = eRPMarketStockInfoMapper.selectByPrimaryKey(idArr[0]);
		if(saleBack==null||saleBack.getIsDelete()){
			msg.setMessage("退货单不存在或已被删除!");
			return msg;
		}
		
		List<ERPMarketStockDetail> saleBaceDetalis = eRPMarketStockInfoMgMapper.getMarketStockDetails(saleBack);
		if(saleBaceDetalis==null||saleBaceDetalis.size()==0){
			msg.setMessage("该笔退货单缺少详情数据!");
			return msg;
		}
		
		for (ERPMarketStockDetail erpMarketStockDetail : saleBaceDetalis) {
			//int chuKu = erpMarketStockDetail.
		}
		
		msg.setSuccess(true);
		return msg;
	}
}
