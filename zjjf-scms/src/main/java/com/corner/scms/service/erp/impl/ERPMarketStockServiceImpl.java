package com.corner.scms.service.erp.impl;

import com.corner.core.beans.*;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ERPMarketStockDetailMapper;
import com.corner.core.dao.ERPMarketStockInfoMapper;
import com.corner.core.dao.ItemBaseMapper;
import com.corner.core.dao.ItemCatelogMapper;
import com.corner.core.enums.LogisticsStatus;
import com.corner.core.enums.OrderPrefix;
import com.corner.core.enums.SocktOperateType;
import com.corner.core.enums.SpOrderOwnerType;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPMarketStockRo;
import com.corner.scms.beans.vo.SpOrderDetailVo;
import com.corner.scms.beans.vo.SpOrderListVo;
import com.corner.scms.dao.erp.ERPMarketStockInfoMgMapper;
import com.corner.scms.service.callable.CallAbleService;
import com.corner.scms.service.erp.ERPMarketStockService;
import com.corner.scms.service.erp.ERPSpOrderOwnerService;
import com.corner.scms.service.erp.ERPWarehouseService;
import com.corner.scms.service.sp.ScmsOrderInfoMgService;
import com.corner.scms.service.sp.SpOrderService;
import com.corner.scms.utils.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ERPMarketStockServiceImpl implements ERPMarketStockService {
	private static Logger logger = LoggerFactory.getLogger(ERPMarketStockServiceImpl.class);
	@Autowired
	ERPMarketStockDetailMapper detailMapper;
	@Autowired
	ERPMarketStockInfoMapper inInfoMapper;
	@Autowired
	ItemCatelogMapper itemCatelogMapper;
	@Autowired
	ItemBaseMapper itemBaseMapper;
	@Autowired
	ERPMarketStockInfoMgMapper inInfoMgMapper;
	@Autowired
	CallAbleService callAbleService;
	@Autowired
	ERPWarehouseService warehouseService;
	@Autowired
	SpOrderService spOrderService;
	@Autowired
	ERPSpOrderOwnerService erpSpOrderOwnerService;
	@Autowired
	ScmsOrderInfoMgService scmsOrderInfoMgService;


	@Override
	public void insertInfo(ERPMarketStockInfo info) throws Exception{
		info.setAddTime(new Date());
		if (inInfoMapper.insertSelective(info) != 1)
			throw new Exception("信息新增失败");
	}

	@Override
	public void insertDetail(ERPMarketStockDetail detail) throws Exception{
		detail.setAddTime(new Date());
		if (detailMapper.insertSelective(detail) != 1)
			throw new Exception("信息新增失败");
	}

	@Override
	public ERPMarketStockInfo getInfoById(String id) throws Exception{
		if (StringUtil.stringIsNullOrEmpty(id))
			throw new Exception("传入信息有误");
		ERPMarketStockInfo inInfo = inInfoMapper.selectByPrimaryKey(id);
		if (inInfo == null)
			throw new Exception("查询信息不存在");
		return inInfo;
	}

	@Override
	public List<ERPMarketStockInfo> getInfoByPId(String PId , String POrderId) throws Exception {
		ERPMarketStockInfoExample example = new ERPMarketStockInfoExample();
		ERPMarketStockInfoExample.Criteria criteria = example.createCriteria().andIsDeleteEqualTo(false);
		if(!StringUtil.stringIsNullOrEmpty(PId))
			criteria.andPIdEqualTo(PId);
		if(!StringUtil.stringIsNullOrEmpty(POrderId))
			criteria.andPOrderIdEqualTo(POrderId);
		List<ERPMarketStockInfo> list = inInfoMapper.selectByExample(example);
		return list;
	}

	@Override
	public ERPMarketStockDetail getDetailById(String id) throws Exception{
		if (StringUtil.stringIsNullOrEmpty(id))
			throw new Exception("传入信息有误");
		ERPMarketStockDetail detail = detailMapper.selectByPrimaryKey(id);
		if (detail == null)
			throw new Exception("查询信息不存在");
		return detail;
	}
	@Override
	@Transactional
	public void bacthCheck(Byte status, String userId , String userName,String ... id) throws Exception{
		for (int i = 0; i < id.length; i++) {

			ERPMarketStockInfo erpMarketStockInfo = getInfoById(id[i]);
			erpMarketStockInfo.setCheckUser(userId);
			erpMarketStockInfo.setCheckUserName(userName);
			erpMarketStockInfo.setCheckTime(new Date());
			erpMarketStockInfo.setCheckStatus(status);
			this.socktOperateLog(erpMarketStockInfo);

			this.updateInfo(erpMarketStockInfo);
		}
	}

	@Override
	public void bacthOutStock(String... id) throws Exception {
		for (int i = 0; i < id.length; i++) {
			ERPMarketStockInfo erpMarketStockInfo = getInfoById(id[i]);
			erpMarketStockInfo.setLogisticsStatus(LogisticsStatus.ZAITU.getIndex());
			this.socktOperateLog(erpMarketStockInfo);

			this.updateInfo(erpMarketStockInfo);
		}
	}

	@Override
	public void bacthSend(String... id) throws Exception {
		for (int i = 0; i < id.length; i++) {

			ERPMarketStockInfo erpMarketStockInfo = getInfoById(id[i]);
			erpMarketStockInfo.setLogisticsStatus(LogisticsStatus.SONGDA.getIndex());
			erpMarketStockInfo.setSettleStatus(Byte.valueOf("2"));
			this.socktOperateLog(erpMarketStockInfo);

			this.updateInfo(erpMarketStockInfo);
		}
	}

	@Override
	public void bacthDelete(String userId , String userName,String ... id) throws Exception{
		ERPMarketStockInfo info = new ERPMarketStockInfo();
		info.setCheckUser(userId);
		info.setCheckUserName(userName);
		info.setCheckTime(new Date());
		info.setIsDelete(true);
		for (int i = 0; i < id.length; i++) {
			info.setId(id[i]);
			this.updateInfo(info);
		}
	}
	@Override
	public void updateInfo(ERPMarketStockInfo info) throws Exception{
		inInfoMapper.updateByPrimaryKeySelective(info);
	}

	@Override
	public void updateDetail(ERPMarketStockDetail detail)throws Exception {
		detailMapper.updateByPrimaryKeySelective(detail);
	}
	@Override
	public List<ERPMarketStockDetail> getDetailsByOrderId(String orderId) throws Exception{
		if (StringUtil.stringIsNullOrEmpty(orderId))
			throw new Exception("传入信息有误");
		ERPMarketStockDetailExample example = new ERPMarketStockDetailExample();
		example.createCriteria().andOrderIdEqualTo(orderId).andIsDeleteEqualTo(false);
		return detailMapper.selectByExample(example);
	}

	@Override
	public Pager<ERPMarketStockInfo> getInfoListByRo(ERPMarketStockRo ro) throws Exception {
		List<ERPMarketStockInfo> list = inInfoMgMapper.getInfoListPageByRo(ro);
		Integer count = inInfoMgMapper.getInfoListPageByRoCOUNT(ro);
		return new Pager<ERPMarketStockInfo>(count , list);
	}
	@Override
	public void updateMarketByOwner(String pOrderId) throws Exception{
		List<ERPMarketStockInfo> lists = getInfoByPId(null , pOrderId);
		ERPMarketStockInfo info = lists != null && lists.size() != 0 ? lists.get(0) : null;
		List<ERPSpOrderOwnerDetail> erpSpOrderOwnerDetail = erpSpOrderOwnerService.getOwnerDetail(info.getpId());
		BigDecimal cashPrice = new BigDecimal(0);
		BigDecimal deleteMoney = new BigDecimal(0);
		if(erpSpOrderOwnerDetail != null && lists.size() != 0){
//			erpSpOrderOwnerDetail.forEach( ownerDetail -> cashPrice.add(ownerDetail.getType() == SpOrderOwnerType.MONEY.getIndex() ? null : ownerDetail.getSurePrice()));
			for (ERPSpOrderOwnerDetail spOrderOwnerDetail : erpSpOrderOwnerDetail) {
				if(spOrderOwnerDetail.getType() == SpOrderOwnerType.MONEY.getIndex())
					cashPrice = cashPrice.add(spOrderOwnerDetail.getSurePrice());
			}
			List<ERPMarketStockDetail> detail = getDetailsByOrderId(info.getOrderId());
			for (ERPMarketStockDetail erpMarketStockDetail : detail) {
				for (ERPSpOrderOwnerDetail spOrderOwnerDetail : erpSpOrderOwnerDetail) {
					if(erpMarketStockDetail.getpId().equals(spOrderOwnerDetail.getId())){
						if (erpMarketStockDetail.getOperateStock() != spOrderOwnerDetail.getSureQuantity()){
							erpMarketStockDetail.setOperateStock(spOrderOwnerDetail.getSureQuantity());
							this.updateDetail(erpMarketStockDetail);
						}
						break;
					}
				}
				if(erpMarketStockDetail.getQuantity() != erpMarketStockDetail.getOperateStock()){
					deleteMoney = deleteMoney.add(new BigDecimal(erpMarketStockDetail.getQuantity() - erpMarketStockDetail.getOperateStock()).multiply(erpMarketStockDetail.getPrice()));
				}
			}
			SpOrderInfo spOrderInfo = spOrderService.getInfoById(info.getpId());
			info.setItemPrice(spOrderInfo.getOrderPrice().subtract(deleteMoney.add(cashPrice)));
			updateInfo(info);
		}
	}
	@Override
	public void addMarketStockIn(ERPMarketStockRo ro, String userId, String userName) throws Exception {
		if(ro.getQuantitys() == null || ro.getQuantitys().length == 0)
			throw new Exception("缺少传入参数");
		if(Arrays.asList(ro.getQuantitys()).stream().filter( s -> s != 0).count() == 0)
			throw new Exception("无退货商品");
		ERPMarketStockInfo info= getInfoById(ro.getpId());

		if(!StringUtil.stringIsNullOrEmpty(ro.getId())){
			info = this.getInfoById(ro.getId());
			for (ERPMarketStockDetail detail : this.getDetailsByOrderId(info.getOrderId())) {
				boolean isFind = false;
				for (int i = 0; i < ro.getIds().length; i++) {
					if(ro.getIds()[i].equals(detail.getId())&&ro.getQuantitys()[i]!=0){
						isFind = true;
						continue;
					}
				}
//				for (String s : ro.getIds()) {
//					if(s.equals(detail.getId())){
//						isFind = true;
//						continue;
//					}
//				}
				if (!isFind) {
					detail.setIsDelete(true);
					this.updateDetail(detail);
				}
			}
		}else{
			info.setOrderId(callAbleService.getStockOrderId(OrderPrefix.MarketStockIN));
		}
		info.setTaskTime(new Date());

		info.setSupplierId(ro.getSupplierId());
		info.setSupplierName(ro.getSupplierName());

		info.setpId(ro.getpId());	//
		info.setpOrderId(ro.getpOrderId());

		info.setCheckStatus(null);
		info.setCheckTime(null);
		info.setCheckUser(null);
		info.setCheckUserName(null);

		info.setAddUser(userId);
		info.setAddUserName(userName);
		info.setRemark(ro.getRemark());
		info.setItemPrice(new BigDecimal(0));
		info.setItemQuantity(Short.valueOf("0"));
		info.setLevel(Byte.valueOf("2"));
		info.setWhId(ro.getWhId());
		ERPWarehouse warehouse = warehouseService.getWarehouseById(info.getWhId());
		info.setWhName(warehouse.getName());

		for (int i = 0; i < ro.getQuantitys().length; i++) {
			if(ro.getQuantitys()[i] == 0)
				continue;
			String pId = ro.getDetailPIds()[i];
			ERPMarketStockDetail detail =  getDetailById(pId);
			detail.setpId(pId);
			detail.setId(ro.getIds() == null || ro.getIds().length == 0 ? null : ro.getIds()[i]);
			detail.setAddTime(new Date());
			detail.setId(ro.getIds() == null || ro.getIds().length == 0 ? null : ro.getIds()[i]);
			detail.setOrderId(info.getOrderId());
			detail.setRemark(ro.getRemarks() == null || ro.getRemarks().length == 0 ? null : ro.getRemarks()[i]);
			ERPWarehouse warehouse2,warehouse3;
			if(ro.getWhIds() == null ||ro.getWhIds().length < i || StringUtil.stringIsNullOrEmpty(ro.getWhIds()[i])) {
				warehouse2 = warehouseService.getWarehouseByUpId(info.getWhId(), warehouse.getSupplierId()).get(0);
				warehouse3 = warehouseService.getWarehouseByUpId(warehouse2.getId() , warehouse.getSupplierId()).get(0);
			}else{
				warehouse3 = warehouseService.getWarehouseById(ro.getWhIds()[i]);
				warehouse2 = warehouseService.getWarehouseById(warehouse3.getUpId());
			}
			detail.setWh1Name(info.getWhName());
			detail.setWh2Name(warehouse2.getName());
			detail.setWh3Id(warehouse3.getId());
			detail.setWh3Name(warehouse3.getName());

			detail.setQuantity(detail.getOperateStock());
			detail.setOperateQuantity(Short.valueOf("0"));
			detail.setOperateStock(ro.getQuantitys()[i]);
			detail.setTotalPrice(detail.getPrice().multiply(new BigDecimal(detail.getOperateStock())));
			if(StringUtil.stringIsNullOrEmpty(detail.getId())){
				detail.setId(StringUtil.getUUID());
				this.insertDetail(detail);
			}else{
				this.updateDetail(detail);
			}
			info.setItemPrice(detail.getTotalPrice().add(info.getItemPrice()));
			info.setItemQuantity(Short.valueOf(detail.getOperateStock() + info.getItemQuantity() + ""));
		}
		if(StringUtil.stringIsNullOrEmpty(ro.getId())){
			info.setId(StringUtil.getUUID());
			this.insertInfo(info);
		}else{
			this.updateInfo(info);
		}
	}
	private void socktOperateLog(ERPMarketStockInfo info) throws Exception {
		boolean isOk = true;
		if(info.getLevel() == 1){
			if(info.getLogisticsStatus() == LogisticsStatus.ZAITU.getIndex()){
				//修改订单状态
				SpOrderInfo spOrderInfo = spOrderService.getInfoById(info.getpId());
				spOrderInfo.setLogisticsStatus(LogisticsStatus.ZAITU.getIndex());
				spOrderInfo.setWhSendTime(new Date());
				spOrderService.updateInfo(spOrderInfo);

				if(spOrderInfo.getLevel()==2 && spOrderInfo.getStatus() != null){
					boolean isLastOrder = true;
					for (SpOrderInfo orderInfo : spOrderService.getInfoListByPId(spOrderInfo.getpId())) {
						if(orderInfo.getLevel()!=null && orderInfo.getLevel()==2
								&& orderInfo.getStatus() != null && orderInfo.getStatus() < spOrderInfo.getStatus() ) {
							isLastOrder = false;
						}
					}
					if(isLastOrder){
						SpOrderInfo spOrdTemp= new SpOrderInfo();
						spOrdTemp.setId(spOrderInfo.getpId());//设置父id
						spOrdTemp.setStatus(spOrderInfo.getStatus());//设置状态
						spOrdTemp.setAckTime(spOrderInfo.getAckTime());
						spOrderService.updateInfo(spOrdTemp);
					}
				}
				try {
					callAbleService.socktOperateLog(SocktOperateType.Operate_10075 , info.getOrderId());
				}catch (Exception e){
					logger.error("调用存储错误：" + e.getMessage());
					throw new RuntimeException(e.getMessage());
				}
			}else if(info.getLogisticsStatus() == LogisticsStatus.SONGDA.getIndex()){
				SpOrderInfo spOrderInfo = spOrderService.getInfoById(info.getpId());
				spOrderInfo.setLogisticsStatus(LogisticsStatus.SONGDA.getIndex());
				spOrderInfo.setWhAckTime(new Date());
				spOrderService.updateInfo(spOrderInfo);
				try {
					callAbleService.socktOperateLog(SocktOperateType.Operate_10076 , info.getOrderId());
				}catch (Exception e){
					logger.error("调用存储错误：" + e.getMessage());
					throw new RuntimeException(e.getMessage());
				}
			}else{
				List<ERPMarketStockDetail> details =  getDetailsByOrderId(info.getOrderId());
				for (ERPMarketStockDetail detail : details) {
					SpOrderDetail detail1 = spOrderService.getDetailById(detail.getpId());
					if(detail1 != null){
						short outStockNum = detail1.getOutStockNum();
						outStockNum+=detail.getOperateStock();
						detail1.setOutStockNum(outStockNum);
						if(detail1.getOutStockNum() > detail1.getQuantity()){
							throw new Exception("销售数量超过出库数量");
						}
						spOrderService.updateDetail(detail1);
						detail.setOperateQuantity(detail1.getOutStockNum());
						this.updateDetail(detail);
						if(detail1.getOutStockNum() < detail1.getQuantity())
							isOk = false;
					}else{
						ERPSpOrderOwnerDetail detail2 = erpSpOrderOwnerService.getDetailById(detail.getpId());
						short outStockNum = detail2.getOutStockNum();
						outStockNum+=detail.getOperateStock();
						detail2.setOutStockNum(outStockNum);
						if(detail2.getOutStockNum() > detail2.getQuantity()){
							throw new Exception("销售数量超过出库数量");
						}
						erpSpOrderOwnerService.updateDetail(detail2);
						detail.setOperateQuantity(detail2.getOutStockNum());
						this.updateDetail(detail);
						if(detail2.getOutStockNum() < detail2.getCashQuantity())
							isOk = false;
					}

					//修改订单状态
					SpOrderInfo spOrderInfo = spOrderService.getInfoById(info.getpId());
					spOrderInfo.setStatus(Byte.valueOf("4"));
					spOrderInfo.setPrintTime(new Date());
					spOrderService.updateInfo(spOrderInfo);

					if(spOrderInfo.getLevel()==2 && spOrderInfo.getStatus() != null){
						boolean isLastOrder = true;
						for (SpOrderInfo orderInfo : spOrderService.getInfoListByPId(spOrderInfo.getpId())) {
							if(orderInfo.getLevel()!=null && orderInfo.getLevel()==2
									&& orderInfo.getStatus() != null && orderInfo.getStatus() < spOrderInfo.getStatus() ) {
								isLastOrder = false;
							}
						}
						if(isLastOrder){
							SpOrderInfo spOrdTemp= new SpOrderInfo();
							spOrdTemp.setId(spOrderInfo.getpId());//设置父id
							spOrdTemp.setStatus(spOrderInfo.getStatus());//设置状态
							spOrdTemp.setPrintTime(spOrderInfo.getPrintTime());
							spOrderService.updateInfo(spOrdTemp);
						}
					}
				}
				if(isOk){
					/*for (ERPSpOrderOwnerDetail detail : erpSpOrderOwnerService.getOwnerDetail(info.getpId())) {
						if(detail.getType() == SpOrderOwnerType.MONEY.getIndex())
							continue;
						else if(detail.getCashQuantity() != detail.getOutStockNum()) {
							isOk = false;
							break;
						}
					}
					if(isOk)
						for (SpOrderDetail detail : spOrderService.getDetailByOrderId2(info.getpOrderId())) {
							if(detail.getQuantity() != detail.getOutStockNum()) {
								isOk = false;
								break;
							}
						}
					if (isOk) {
						SpOrderInfo pInfo = new SpOrderInfo();
						pInfo.setId(info.getpId());
						pInfo.setIsOutStock(true);
						spOrderService.updateInfo(pInfo);
					}*/
					if(erpSpOrderOwnerService.getOwnerDetail(info.getpId()).stream().filter(d -> (!d.getType().equals(SpOrderOwnerType.MONEY.getIndex()))&&(!d.getCashQuantity().equals(d.getOutStockNum()))).count() == 0) {
						if (spOrderService.getDetailByOrderId2(info.getpOrderId()).stream().filter(d -> !d.getQuantity().equals(d.getOutStockNum())).count() == 0) {
							SpOrderInfo pInfo = new SpOrderInfo();
							pInfo.setId(info.getpId());
							pInfo.setIsOutStock(true);
							spOrderService.updateInfo(pInfo);
						}
					}
				}
			}
		}else if(info.getLevel() == 2){
			List<ERPMarketStockDetail> details =  getDetailsByOrderId(info.getOrderId());
			for (ERPMarketStockDetail detail : details) {
				ERPMarketStockDetail detail2 = getDetailById(detail.getpId());
				short qperateQuantityBack = detail2.getOperateQuantityBack();
				qperateQuantityBack+=detail.getOperateStock();
				detail2.setOperateQuantityBack(qperateQuantityBack);
				if(detail2.getOperateQuantityBack() > detail2.getOperateQuantity()){
					throw new Exception("退货数量超过销售出库数量");
				}
				this.updateDetail(detail2);

				detail.setOperateQuantity(detail2.getOperateQuantityBack());
				this.updateDetail(detail);
				if(detail2.getOperateQuantityBack() < detail2.getOperateQuantity())
					isOk = false;
			}
			if(isOk){
				for (ERPMarketStockDetail detail : getDetailsByOrderId(info.getpOrderId())) {
					if(detail.getOperateQuantityBack() != detail.getOperateQuantity()) {
						isOk = false;
						break;
					}
				}
				if (isOk) {
					ERPMarketStockInfo pInfo = new ERPMarketStockInfo();
					pInfo.setId(info.getpId());
					pInfo.setIsSaleBack(true);
					updateInfo(pInfo);
				}
			}
			try {
				callAbleService.socktOperateLog(SocktOperateType.Operate_10077 , info.getOrderId());
			}catch (Exception e){
				logger.error("调用存储错误：" + e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public List<SpOrderDetailVo> getOwnerDetailToVo(String orderId) throws Exception{
		List<ERPMarketStockDetail> list = getDetailsByOrderId(orderId);
		List<SpOrderDetailVo> vos = new ArrayList<>();

		ItemBase itemBase = null;
		ItemCatelog cateLog = null;
		for (ERPMarketStockDetail erpMarketStockDetail : list) {
			SpOrderDetailVo vo;
			SpOrderDetail detail = spOrderService.getDetailById(erpMarketStockDetail.getpId());
			if(detail != null){
				vo = BeanUtil.toObject(SpOrderDetailVo.class ,detail);
				vo.setQuantity(erpMarketStockDetail.getOperateStock());
				vo.setTotalPrice(erpMarketStockDetail.getTotalPrice());
			}else{
				vo = BeanUtil.toObject(SpOrderDetailVo.class ,erpMarketStockDetail);
				vo.setYouHui("兑换");
			}
			vo.setOrderId2(erpMarketStockDetail.getOrderId());
			itemBase = (itemBase == null|| itemBase.getId() != erpMarketStockDetail.getItemBaseId()) ? itemBaseMapper.selectByPrimaryKey(erpMarketStockDetail.getItemBaseId()):itemBase;
			cateLog = (cateLog == null || itemBase.getCateId() != cateLog.getId()) ? itemCatelogMapper.selectByPrimaryKey(itemBase.getCateId()):cateLog;
			vo.setCateName(cateLog.getName());
			vo.setQuantity(erpMarketStockDetail.getOperateStock());
			vos.add(vo);
		}
		vos.sort((SpOrderDetailVo h1, SpOrderDetailVo h2) -> h1.getCateName().compareTo(h2.getCateName()));
		return vos;
	}
}
