package com.corner.scms.service.erp.impl;

import com.corner.core.beans.*;
import com.corner.core.dao.ERPSpOrderOwnerDetailMapper;
import com.corner.core.dao.ItemBaseMapper;
import com.corner.core.dao.ItemCatelogMapper;
import com.corner.core.enums.OrderPrefix;
import com.corner.core.enums.SpOrderOwnerType;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPSpOrderOwnerRo;
import com.corner.scms.beans.vo.OrderInfoVo;
import com.corner.scms.beans.vo.SpOrderDetailVo;
import com.corner.scms.beans.vo.erp.ERPOrderDetailVo;
import com.corner.scms.service.callable.CallAbleService;
import com.corner.scms.service.erp.ERPSpOrderOwnerService;
import com.corner.scms.service.erp.ERPWarehouseService;
import com.corner.scms.service.sp.ScmsOrderInfoMgService;
import com.corner.scms.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ERPSpOrderOwnerServiceImpl implements ERPSpOrderOwnerService {
	@Autowired
	ERPSpOrderOwnerDetailMapper erpSpOrderOwnerDetailMapper;
	@Autowired
	ScmsOrderInfoMgService scmsOrderInfoMgService;
	@Autowired
	ERPWarehouseService warehouseService;
	@Autowired
	CallAbleService callAbleService;
	@Autowired
	ItemBaseMapper itemBaseMapper;
	@Autowired
	ItemCatelogMapper itemCatelogMapper;
	private void insertDetail(ERPSpOrderOwnerDetail detail){
		detail.setAddTime(new Date());
		erpSpOrderOwnerDetailMapper.insertSelective(detail);
	}

	@Override
	public void addERPSpOrderOwnerDetail(ERPSpOrderOwnerRo ro, String orderId) throws Exception {
		if(StringUtil.stringIsNullOrEmpty(orderId))
			throw new Exception("请传入订单号");
		OrderInfoVo orderInfoVo = scmsOrderInfoMgService.findByOrId(orderId);
		List<ERPSpOrderOwnerDetail> list = this.getOwnerDetail(orderInfoVo.getId());
		if(list != null && list.size() != 0){
			for (ERPSpOrderOwnerDetail erpSpOrderOwnerDetail : list) {
				boolean isDelete = true;
				for (int i = 0; i < ro.getIds().length; i++) {
					if (erpSpOrderOwnerDetail.getId().equals(ro.getIds()[i])){
						isDelete =false;
						break;
					}
				}
				if (isDelete){
					erpSpOrderOwnerDetail.setIsDelete(true);
					updateDetail(erpSpOrderOwnerDetail);
				}
			}
		}
		if(orderInfoVo == null)
			throw  new Exception("订单信息不存在");
		ERPSpOrderOwnerDetail detail = new ERPSpOrderOwnerDetail();
		detail.setOrderId(orderInfoVo.getOrderId());
		detail.setOrderInfoId(orderInfoVo.getId());
		detail.setOrderInfoPid(orderInfoVo.getpId());
		for (int i = 0; i < ro.getTypes().length; i++) {
			if(orderInfoVo.getStatus() != 3 && !StringUtil.stringIsNullOrEmpty(ro.getIds()==null || ro.getIds().length == 0 ? null : ro.getIds()[i])){
				detail.setSurePrice(ro.getSurePrices() == null || ro.getSurePrices().length == 0 ? null : ro.getSurePrices()[i]);
				detail.setBackQuantity(ro.getBackQuantitys()[i]);
				detail.setSureQuantity(ro.getSureQuantitys() == null || ro.getSureQuantitys().length == 0 ? null : ro.getSureQuantitys()[i]);
				detail.setId(ro.getIds()[i]);
				updateDetail(detail);
			}else {
				if (ro.getQuantitys() == null || ro.getQuantitys()[i] == null || ro.getQuantitys()[i] <= 0) {
					if(StringUtil.stringIsNullOrEmpty(ro.getIds()[i]))
						continue;
				}
				if (ro.getTypes()[i] == SpOrderOwnerType.ITEM.getIndex()) {
					detail.setItemBaseId(ro.getItemBaseIds() == null || ro.getItemBaseIds().length == 0 ? null : ro.getItemBaseIds()[i]);
					ItemBase itemBase = itemBaseMapper.selectByPrimaryKey(detail.getItemBaseId());
					if (itemBase == null) {
						throw new Exception(SpOrderOwnerType.ITEM.getName() + ":商品信息不能为空");
					}
					detail.setSpec(itemBase.getSpec());
					detail.setName(itemBase.getName());
					detail.setPkg(itemBase.getPkg());
					detail.setBarCode(itemBase.getMdseId());
					ERPWarehouse warehouse1, warehouse2, warehouse3;
					if (ro.getWh3Ids() == null || ro.getWh3Ids().length < i || StringUtil.stringIsNullOrEmpty(ro.getWh3Ids()[i])) {
						warehouse1 = warehouseService.getWarehouseById(ro.getWhId());
						warehouse2 = warehouseService.getWarehouseByUpId(warehouse1.getId(), orderInfoVo.getSupplierId()).get(0);
						warehouse3 = warehouseService.getWarehouseByUpId(warehouse2.getId(), orderInfoVo.getSupplierId()).get(0);
					} else {
						warehouse3 = warehouseService.getWarehouseById(ro.getWh3Ids()[i]);
						warehouse2 = warehouseService.getWarehouseById(warehouse3.getUpId());
						warehouse1 = warehouseService.getWarehouseById(warehouse2.getUpId());
					}
					detail.setWh1Id(warehouse1.getId());
					detail.setWh1Name(warehouse1.getName());
					detail.setWh2Name(warehouse2.getName());
					detail.setWh3Id(warehouse3.getId());
					detail.setWh3Name(warehouse3.getName());
				} else {
					detail.setItemBaseId(null);
					detail.setSpec("");
					detail.setName("");
					detail.setPkg("");
					detail.setBarCode("");
					detail.setWh1Id(null);
					detail.setWh1Name(null);
					detail.setWh2Name(null);
					detail.setWh3Id(null);
					detail.setWh3Name(null);
				}
				detail.setQuantity(ro.getQuantitys() == null || ro.getQuantitys().length == 0 ? null : ro.getQuantitys()[i]);
				detail.setType(ro.getTypes()[i]);
				detail.setCashQuantity(ro.getCashQuantitys() == null || ro.getCashQuantitys().length == 0 ? null : ro.getCashQuantitys()[i]);
				detail.setCashPrice(ro.getCashPrices() == null || ro.getCashPrices().length == 0 ? null : ro.getCashPrices()[i]);
				detail.setCash(ro.getCashs() == null || ro.getCashs().length == 0 ? null : ro.getCashs()[i]);
				detail.setRemark(ro.getRemarks() == null || ro.getRemarks().length == 0 ? null : ro.getRemarks()[i]);

				detail.setBackQuantity(ro.getBackQuantitys() == null || ro.getBackQuantitys().length == 0 ? detail.getQuantity() : ro.getBackQuantitys()[i]);
				detail.setSurePrice(ro.getSurePrices() == null || ro.getSurePrices().length == 0 ? detail.getCashPrice() : ro.getSurePrices()[i]);
				detail.setSureQuantity(ro.getSureQuantitys() == null || ro.getSureQuantitys().length == 0 ? detail.getCashQuantity() : ro.getSureQuantitys()[i]);

				detail.setId(ro.getIds() == null || ro.getIds().length == 0 ? null : ro.getIds()[i]);
				if (StringUtil.stringIsNullOrEmpty(detail.getId())) {
					detail.setId(StringUtil.getUUID());
					insertDetail(detail);
				} else {
					updateDetail(detail);
				}
			}
		}
	}

	@Override
	public List<ERPSpOrderOwnerDetail> getOwnerDetail(String orderInfoId) {
		return getOwnerDetail(orderInfoId , null);
	}
	@Override
	public List<ERPSpOrderOwnerDetail> getOwnerDetail(String orderInfoId , SpOrderOwnerType type) {
		return getOwnerDetail(orderInfoId , type , null);
	}
	@Override
	public List<ERPSpOrderOwnerDetail> getOwnerDetailByPid(String orderInfoPId) {
		return getOwnerDetail(null , null , orderInfoPId);
	}
	private List<ERPSpOrderOwnerDetail> getOwnerDetail(String orderInfoId , SpOrderOwnerType type , String orderInfoPId) {
		ERPSpOrderOwnerDetailExample example = new ERPSpOrderOwnerDetailExample();
		ERPSpOrderOwnerDetailExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeleteEqualTo(false);
		if(!StringUtil.stringIsNullOrEmpty(orderInfoId)){
			criteria.andOrderInfoIdEqualTo(orderInfoId);
		}
		if(type != null){
			criteria.andTypeEqualTo(type.getIndex());
		}
		if(!StringUtil.stringIsNullOrEmpty(orderInfoPId)){
			criteria.andOrderInfoPidEqualTo(orderInfoPId);
		}
		return erpSpOrderOwnerDetailMapper.selectByExample(example);
	}
	@Override
	public ERPSpOrderOwnerDetail getDetailById(String id) {
		return erpSpOrderOwnerDetailMapper.selectByPrimaryKey(id);
	}
	@Override
	public void updateDetail(ERPSpOrderOwnerDetail detail) throws Exception{
		if(erpSpOrderOwnerDetailMapper.updateByPrimaryKeySelective(detail) != 1){
			throw new Exception("修改失败");
		}
	}

	@Override
	public List<SpOrderDetailVo> getOwnerDetailToVo(String orderInfoId) {
		List<ERPSpOrderOwnerDetail> list = getOwnerDetail(orderInfoId);
		List<SpOrderDetailVo> vos = new ArrayList<>();
		for (ERPSpOrderOwnerDetail erpSpOrderOwnerDetail : list) {
			SpOrderDetailVo vo = BeanUtil.toObject(SpOrderDetailVo.class ,erpSpOrderOwnerDetail);
			if(erpSpOrderOwnerDetail.getType() == 1){
				ItemBase itemBase = itemBaseMapper.selectByPrimaryKey(erpSpOrderOwnerDetail.getItemBaseId());
				ItemCatelog cateLog = itemCatelogMapper.selectByPrimaryKey(itemBase.getCateId());
				vo.setCateName(cateLog.getName());
				vo.setQuantity(erpSpOrderOwnerDetail.getCashQuantity());
				vo.setGoodsType(1);
			}else{
				vo.setPrice(erpSpOrderOwnerDetail.getSurePrice());
				vo.setGoodsType(2);
			}
			vos.add(vo);
		}
		return vos;
	}
}
