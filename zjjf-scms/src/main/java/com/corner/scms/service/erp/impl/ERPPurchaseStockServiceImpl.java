package com.corner.scms.service.erp.impl;

import com.corner.core.beans.*;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.*;
import com.corner.core.enums.OrderPrefix;
import com.corner.core.enums.SocktOperateType;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPPurchaseStockRo;
import com.corner.scms.beans.vo.erp.ERPPurchaseStockVo;
import com.corner.scms.dao.erp.ERPPurchaseStockInfoMgMapper;
import com.corner.scms.service.callable.CallAbleService;
import com.corner.scms.service.erp.ERPManagerItemService;
import com.corner.scms.service.erp.ERPManagerService;
import com.corner.scms.service.erp.ERPPurchaseStockService;
import com.corner.scms.service.erp.ERPWarehouseService;
import com.corner.scms.utils.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ERPPurchaseStockServiceImpl implements ERPPurchaseStockService {
	private static Logger logger = LoggerFactory.getLogger(ERPPurchaseStockServiceImpl.class);
	@Autowired
	ERPPurchaseStockDetailMapper detailMapper;
	@Autowired
	ERPPurchaseStockInfoMapper inInfoMapper;
	@Autowired
	ERPPurchaseStockInfoMgMapper inInfoMgMapper;
	@Autowired
	CallAbleService callAbleService;
	@Autowired
	ERPWarehouseService warehouseService;
	@Autowired
	ERPManagerService managerService;
	@Autowired
	ERPManagerItemService erpManagerItemService;
	@Autowired
	ERPPlantItemLogMapper erpPlantItemLogMapper;
	@Autowired
	ItemBaseMapper itemBaseMapper;
	@Autowired
	ERPManagerOrderInfoMapper eRPManagerOrderInfoMapper;
	@Autowired
	ERPManagerOrderDetailMapper eRPManagerOrderDetailMapper;


	@Override
	public void insertInfo(ERPPurchaseStockInfo info) throws Exception{
		info.setAddTime(new Date());
		if (inInfoMapper.insertSelective(info) != 1)
			throw new Exception("信息新增失败");
	}

	@Override
	public void insertDetail(ERPPurchaseStockDetail detail) throws Exception{
		detail.setAddTime(new Date());
		if (detailMapper.insertSelective(detail) != 1)
			throw new Exception("信息新增失败");
	}

	@Override
	public ERPPurchaseStockInfo getInfoById(String id) throws Exception{
		if (StringUtil.stringIsNullOrEmpty(id))
			throw new Exception("传入信息有误");
		ERPPurchaseStockInfo inInfo = inInfoMapper.selectByPrimaryKey(id);
		if (inInfo == null)
			throw new Exception("查询信息不存在");
		return inInfo;
	}

	@Override
	public ERPPurchaseStockDetail getDetailById(String id) throws Exception{
		if (StringUtil.stringIsNullOrEmpty(id))
			throw new Exception("传入信息有误");
		ERPPurchaseStockDetail detail = detailMapper.selectByPrimaryKey(id);
		if (detail == null)
			throw new Exception("查询信息不存在");
		return detail;
	}
	@Override
	public void bacthCheck(Byte status, String userId , String userName,String ... id) throws Exception{
		for (int i = 0; i < id.length; i++) {
			ERPPurchaseStockInfo erpPurchaseStockInfo = getInfoById(id[i]);
			erpPurchaseStockInfo.setCheckUser(userId);
			erpPurchaseStockInfo.setCheckUserName(userName);
			erpPurchaseStockInfo.setCheckTime(new Date());
			erpPurchaseStockInfo.setCheckStatus(status);
			//操作库存	订单信息校验
			socktOperateLog(erpPurchaseStockInfo);

			this.updateInfo(erpPurchaseStockInfo);
		}
	}
	@Override
	public void bacthDelete(String userId , String userName,String ... id) throws Exception{
		ERPPurchaseStockInfo erpPurchaseStockInfo = new ERPPurchaseStockInfo();
		erpPurchaseStockInfo.setCheckUser(userId);
		erpPurchaseStockInfo.setCheckUserName(userName);
		erpPurchaseStockInfo.setCheckTime(new Date());
		erpPurchaseStockInfo.setIsDelete(true);
		for (int i = 0; i < id.length; i++) {
			erpPurchaseStockInfo.setId(id[i]);
			this.updateInfo(erpPurchaseStockInfo);
		}
	}
	@Override
	public void updateInfo(ERPPurchaseStockInfo info) throws Exception{
		inInfoMapper.updateByPrimaryKeySelective(info);
	}

	@Override
	public void updateDetail(ERPPurchaseStockDetail detail)throws Exception {
		detailMapper.updateByPrimaryKeySelective(detail);
	}

	@Override
	public List<ERPPurchaseStockDetail> getDetailsByOrderId(String orderId) throws Exception{
		if (StringUtil.stringIsNullOrEmpty(orderId))
			throw new Exception("传入信息有误");
		ERPPurchaseStockDetailExample example = new ERPPurchaseStockDetailExample();
		example.createCriteria().andOrderIdEqualTo(orderId).andIsDeleteEqualTo(false);
		return detailMapper.selectByExample(example);
	}

	@Override
	public Pager<ERPPurchaseStockInfo> getInfoListByRo(ERPPurchaseStockRo ro) throws Exception {

		ERPPurchaseStockInfoExample example = new ERPPurchaseStockInfoExample();
		example.setOrderByClause("checkStatus asc, addTime desc");
		ERPPurchaseStockInfoExample.Criteria criteria =example.createCriteria();

		criteria.andSupplierIdEqualTo(ro.getSupplierId());
		criteria.andIsDeleteEqualTo(false);
		if(!StringUtil.stringIsNullOrEmpty(ro.getManagerName()))
			criteria.andManagerNameLike("%"+ro.getManagerName()+"%");
		if(ro.getLevel() != null && ro.getLevel() != 0)
			criteria.andLevelEqualTo(ro.getLevel());
		if(ro.getCheckStatus() != null && ro.getCheckStatus() != 0)
			criteria.andCheckStatusEqualTo(ro.getCheckStatus());
		if(ro.getStatus() != null && ro.getStatus() != 0)
			criteria.andStatusEqualTo(ro.getStatus());
		if(ro.getBeginTime() != null)
			criteria.andAddTimeGreaterThan(ro.getBeginTime());
		if(ro.getEndTime() != null)
			criteria.andAddTimeLessThan(ro.getEndTime());
		Page page = PageHelper.startPage(ro.getPageIndex() == 0 ? 1:(ro.getPageIndex()/10)+1 , ro.getPageSize());
		List<ERPPurchaseStockInfo> list = inInfoMapper.selectByExample(example);
//		List<ERPPurchaseStockInfo> list = inInfoMgMapper.getInfoListPageByRo(ro);
//		Integer count = inInfoMgMapper.getInfoListPageByRoCOUNT(ro);
		return new Pager<ERPPurchaseStockInfo>((int) page.getTotal() , list);
	}

	@Override
	public List<ERPPurchaseStockVo> selectStockListByPorderId(String PorderId) throws Exception {
		ERPPurchaseStockInfoExample example = new ERPPurchaseStockInfoExample();
		example.createCriteria().andPOrderIdEqualTo(PorderId).andIsDeleteEqualTo(false);
		example.setOrderByClause("checkStatus asc , checkTime desc");
		List<ERPPurchaseStockInfo> list = inInfoMapper.selectByExample(example);
		if(list == null || list.size() == 0){
			return null;
		}
		List<ERPPurchaseStockVo> voList = BeanUtil.toObject(ERPPurchaseStockVo.class , list);
		for (ERPPurchaseStockVo vo : voList) {
			vo.setDetails(getDetailsByOrderId(vo.getOrderId()));
		}
		return voList;
	}
	@Override
	public ERPPurchaseStockVo selectStockListByOrderId(String orderId) throws Exception {
		ERPPurchaseStockInfoExample example = new ERPPurchaseStockInfoExample();
		example.createCriteria().andOrderIdEqualTo(orderId).andIsDeleteEqualTo(false);
		List<ERPPurchaseStockInfo> list = inInfoMapper.selectByExample(example);
		if(list == null || list.size() == 0){
			throw new Exception("无对应数据");
		}
		ERPPurchaseStockVo vo = BeanUtil.toObject(ERPPurchaseStockVo.class , list.get(0));
		vo.setDetails(getDetailsByOrderId(vo.getOrderId()));
		return vo;
	}

	@Override
	public void addERPPurchaseStockOutInfo(ERPPurchaseStockRo ro, String userId, String userName) throws Exception {
		if(ro.getQuantitys() == null || ro.getQuantitys().length == 0)
			throw new Exception("缺少传入参数");
		ERPPurchaseStockInfo info= new ERPPurchaseStockInfo();
		if(!StringUtil.stringIsNullOrEmpty(ro.getId())){
			info = this.getInfoById(ro.getId());
			for (ERPPurchaseStockDetail erpPurchaseStockDetail : this.getDetailsByOrderId(info.getOrderId())) {
				boolean isFind = false;
				for (String s : ro.getIds()) {
					if(s.equals(erpPurchaseStockDetail.getId())){
						isFind = true;
						continue;
					}
				}
				if (!isFind) {
					erpPurchaseStockDetail.setIsDelete(true);
					this.updateDetail(erpPurchaseStockDetail);
				}
			}
		}else{
			info.setOrderId(callAbleService.getStockOrderId(OrderPrefix.PurchaseStockOut));
		}

		ERPPurchaseStockDetail detail = new ERPPurchaseStockDetail();
		detail.setOrderId(info.getOrderId());
		detail.setProductionTime(new Date());
		detail.setRemark(ro.getRemark());
		info.setTaskTime(ro.getTaskTime());

		info.setManagerId(ro.getManagerId());
		ERPManager manager = managerService.getERPManagerById(info.getManagerId());
		info.setManagerName(manager.getManagerName());
		info.setManagerCode(manager.getManagerCode());

		info.setSupplierId(ro.getSupplierId());
		info.setSupplierName(ro.getSupplierName());

		info.setpId(info.getId());	//
		info.setpOrderId(info.getOrderId());

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
			ItemBase itemBase = itemBaseMapper.selectByPrimaryKey(ro.getItemBaseIds()[i]);

			detail.setName(itemBase.getName());
			detail.setId(ro.getIds() == null || ro.getIds().length == 0 ? null : ro.getIds()[i]);
			detail.setAddTime(info.getAddTime());
			detail.setBarCode(itemBase.getMdseId());
			detail.setImg(itemBase.getImgB());
			detail.setItemBaseId(ro.getItemBaseIds()[i]);
			detail.setSpec(itemBase.getSpec());
			detail.setPkg(itemBase.getPkg());
			detail.setRemark(ro.getRemarks() == null || ro.getRemarks().length == 0 ? null : ro.getRemarks()[i]);
			//获取箱码
			if(itemBase.getUpId() == 0){
				detail.setMdseId(itemBase.getMdseId());
			}else{
				ItemBaseExample example = new ItemBaseExample();
				example.createCriteria().andTgIdEqualTo(itemBase.getTgId()).andUpIdEqualTo(0);
				List<ItemBase> list = itemBaseMapper.selectByExample(example);
				if(list.size() == 0){
					detail.setMdseId(detail.getMdseId());
				}else{
					detail.setMdseId(list.get(0).getMdseId());
				}
			}
			detail.setItemCode(ro.getItemCodes()[i]);
			detail.setAreaPrice(ro.getAreaPrices()[i]);
//			ERPManagerItem erpManagerItem = erpManagerItemService.getERPManagerItem(info.getManagerId() , itemBase.getId());
//			detail.setItemCode(erpManagerItem.getItemCode());
			detail.setPrice(detail.getAreaPrice());

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

			detail.setQuantity(ro.getQuantitys()[i]);
			detail.setOperateQuantity(detail.getQuantity());
			detail.setOperateStock(detail.getQuantity());
			detail.setTotalPrice(detail.getAreaPrice().multiply(new BigDecimal(detail.getQuantity())));
			if(StringUtil.stringIsNullOrEmpty(detail.getId())){
				detail.setId(StringUtil.getUUID());
				detail.setpId(detail.getId());
				this.insertDetail(detail);
			}else{
				detail.setpId(detail.getId());
				this.updateDetail(detail);
			}
			info.setItemPrice(detail.getTotalPrice().add(info.getItemPrice()));
			info.setItemQuantity(Short.valueOf(detail.getQuantity() + info.getItemQuantity() + ""));
		}
		if(StringUtil.stringIsNullOrEmpty(info.getId())){
			info.setId(StringUtil.getUUID());
			info.setpId(info.getId());
			this.insertInfo(info);
		}else{
			info.setpId(info.getId());
			this.updateInfo(info);
		}
	}
	private void socktOperateLog(ERPPurchaseStockInfo stockInfo) throws Exception {
		String orderId = stockInfo.getOrderId();
		if(stockInfo.getLevel() == 1){
			ERPPlantItemLog erpPlantItemLog = new ERPPlantItemLog();
			erpPlantItemLog.setOrderId(stockInfo.getOrderId());
			erpPlantItemLog.setAddTime(new Date());
			erpPlantItemLog.setSupplierId(stockInfo.getSupplierId());
			erpPlantItemLog.setActionUserId(stockInfo.getCheckUser());
			erpPlantItemLog.setActionUserName(stockInfo.getCheckUserName());
			erpPlantItemLog.setRemark(stockInfo.getRemark());
			boolean isOk = true;
			ERPManagerOrderInfo info = eRPManagerOrderInfoMapper.selectByPrimaryKey(stockInfo.getpId());
			List<ERPPurchaseStockDetail> list = getDetailsByOrderId(stockInfo.getOrderId());
			for (ERPPurchaseStockDetail erpPurchaseStockDetail : list) {
				ERPManagerOrderDetail detail = eRPManagerOrderDetailMapper.selectByPrimaryKey(erpPurchaseStockDetail.getpId());
				short operateStock = erpPurchaseStockDetail.getOperateStock();
				short operateQuantity = detail.getOperateQuantity();
				operateQuantity +=operateStock; //入库本次后的已入库数量

				erpPlantItemLog.setOperateQuantity(erpPurchaseStockDetail.getQuantity());
				erpPlantItemLog.setId(StringUtil.newUUID());
				erpPlantItemLog.setOrderDetailId(erpPurchaseStockDetail.getpId());

				erpPlantItemLog.setWarehouseId(erpPurchaseStockDetail.getWh3Id());
				erpPlantItemLog.setInfoOrderId(info.getOrderId());
				erpPlantItemLog.setAreaPrice(erpPurchaseStockDetail.getAreaPrice());
				erpPlantItemLog.setProductionDate(erpPurchaseStockDetail.getProductionTime());
				erpPlantItemLog.setGoodsStock(detail.getQuantity()-erpPurchaseStockDetail.getQuantity());  //剩余入库量
				erpPlantItemLog.setOperateQuantity(operateQuantity);
				erpPlantItemLog.setItemBaseId(detail.getItemBaseId());
				erpPlantItemLog.setItemId(detail.getItemId());

				if(operateQuantity < detail.getQuantity())
					isOk = false;
				else if(operateQuantity > detail.getQuantity())
					throw new Exception("入库数量超过采购数量");

				erpPlantItemLogMapper.insertSelective(erpPlantItemLog);
				detail.setOperateQuantity(operateQuantity);
				eRPManagerOrderDetailMapper.updateByPrimaryKeySelective(detail);

				erpPurchaseStockDetail.setOperateQuantity(operateQuantity) ;
				this.updateDetail(erpPurchaseStockDetail);
			}
			if(isOk){
				ERPManagerOrderDetailExample example2 = new ERPManagerOrderDetailExample();
				example2.createCriteria().andOrderIdEqualTo(info.getOrderId());
				List<ERPManagerOrderDetail> details = eRPManagerOrderDetailMapper.selectByExample(example2);
				for (ERPManagerOrderDetail detail : details) {
					if(!detail.getOperateQuantity().equals(detail.getQuantity())) {
						isOk = false;
						break;
					}
				}
				if (isOk) {
					info.setStatus(Byte.valueOf("1"));
					info.setEndTime(new Date());
					eRPManagerOrderInfoMapper.updateByPrimaryKeySelective(info);
				}
			}
		}
		try {
			callAbleService.socktOperateLog(stockInfo.getLevel() == 1 ?SocktOperateType.Operate_10085 :SocktOperateType.Operate_10087 , stockInfo.getOrderId());
		}catch (Exception e){
			logger.error("调用存储错误：" + e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
}
