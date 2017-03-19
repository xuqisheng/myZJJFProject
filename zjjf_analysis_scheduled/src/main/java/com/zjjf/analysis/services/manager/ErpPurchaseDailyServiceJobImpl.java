package com.zjjf.analysis.services.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.orders.ERPPurchaseDaily;
import com.zjjf.analysis.beans.corner.ERPManagerOrderDetail;
import com.zjjf.analysis.beans.corner.ERPManagerOrderInfo;
import com.zjjf.analysis.beans.corner.ERPPurchaseStockDetail;
import com.zjjf.analysis.beans.corner.ERPPurchaseStockInfo;
import com.zjjf.analysis.beans.origin.items.ItemBase;
import com.zjjf.analysis.mapper.corner.ERPManagerOrderDetailMapper;
import com.zjjf.analysis.mapper.corner.ERPManagerOrderInfoMapper;
import com.zjjf.analysis.mapper.corner.ERPPurchaseStockDetailMapper;
import com.zjjf.analysis.mapper.corner.ERPPurchaseStockInfoMapper;
import com.zjjf.analysis.mapper.origin.ItemBaseMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class ErpPurchaseDailyServiceJobImpl extends AbstractTimeService<ERPManagerOrderDetail, ERPPurchaseDaily> {

	@Autowired
	private ERPManagerOrderDetailMapper erpManagerOrderDetailMapper;

	@Autowired
	private ERPManagerOrderInfoMapper erpManagerOrderInfoMapper;

	@Autowired
	private ERPPurchaseStockInfoMapper erpPurchaseStockInfoMapper;

	@Autowired
	private ERPPurchaseStockDetailMapper erpPurchaseStockDetailMapper;

	@Autowired
	private ItemBaseMapper itemBaseMapper;

	@Override
	public List<? extends ERPManagerOrderDetail> getDataByTime(String yyyyMMdd, Integer offset) {

		List<ERPManagerOrderDetail> orderDetailList = erpManagerOrderDetailMapper.getAll(yyyyMMdd, (offset - 1) * 1000);
		if (orderDetailList != null && orderDetailList.size() != 0) {
			return orderDetailList;
		}
		return new ArrayList<ERPManagerOrderDetail>();
	}

	@Override
	public void _process(ERPManagerOrderDetail t, ERPPurchaseDaily v, String dayTime) {

		// *采购单信息
		String orderId = t.getOrderId();
		if (orderId == null) {
			return;
		}
		v.setPurchaseId(orderId);
		v.setItemBaseId(t.getItemBaseId());
		v.setItemId(t.getItemId());

		ERPManagerOrderInfo managerOrderInfo = getManagerOrderInfoByOrderId(orderId);
		if (managerOrderInfo != null) {
			v.setType(managerOrderInfo.getType());
			v.setSupplierId(managerOrderInfo.getSupplierId());
			v.setManagerId(managerOrderInfo.getManagerId());
			v.setManagerName(managerOrderInfo.getManagerName());
			v.setBarCode(t.getBarCode());
			v.setSupplierId(managerOrderInfo.getSupplierId());
			// 箱码 ItemBase里面upId为0，箱码为自身msdId，否则的话为upId的msdId
			ItemBase itemBase = itemBaseMapper.getById(t.getItemBaseId());
			if (itemBase == null) {
				return;
			}
			if (itemBase.getUpId() == 0) {
				v.setBoxCode(t.getMdseId());
			} else {
				v.setBoxCode(itemBase.getMdseId());
			}
			v.setItemName(t.getName());
			v.setItemSpec(t.getSpec());
			v.setOperateQuantity(Integer.valueOf(t.getOperateQuantity()));
			v.setNeedQuantity(Integer.valueOf(t.getQuantity()));
			v.setAddTime(t.getAddTime());

			// *入库信息
			ERPPurchaseStockInfo stockInfo = getPurchaseStockInfoByPOrderId(orderId);
			ERPPurchaseStockDetail stockDetail = null;
			if (stockInfo != null) {
				stockDetail = getPurchaseStockDetail(stockInfo.getOrderId(), t.getItemBaseId(), t.getId());
				if (stockDetail != null) {
					v.setInStorageNo(stockInfo.getOrderId());
					v.setInStorageUser(stockInfo.getAddUserName());
					v.setWarehouseId(stockInfo.getWhId());
					v.setWarehouseName(stockInfo.getWhName());
					v.setWh3Name(stockDetail.getWh3Name());
					v.setAddTime(stockInfo.getAddTime());
					v.setAddUser(stockInfo.getAddUserName());
					// 交货时间
					v.setGaveTime(stockInfo.getAddTime());
					v.setProductTime(stockDetail.getProductionTime());
					v.setRealItemPrice(stockDetail.getAreaPrice());
					v.setTotalInStoragePrice(stockDetail.getTotalPrice());
				}
			}
			// 采购单价
			v.setItemPrice(t.getAreaPrice());
			// 平均售价，毛利，毛利率，平均采购价，历史最低采购价api中计算
		}
	}

	private ERPManagerOrderInfo getManagerOrderInfoByOrderId(String orderId) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		return erpManagerOrderInfoMapper.getByMap(param).get(0);
	}

	private ERPPurchaseStockInfo getPurchaseStockInfoByPOrderId(String pOrderId) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("pOrderId", pOrderId);
		List<ERPPurchaseStockInfo> info = erpPurchaseStockInfoMapper.getByMap(param);
		if (info != null && info.size() != 0) {
			return info.get(0);
		}
		return null;
	}

	private ERPPurchaseStockDetail getPurchaseStockDetail(String orderId, Integer itemBaseId, String pid) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("pId", pid);
		param.put("itemBaseId", itemBaseId);
		List<ERPPurchaseStockDetail> detail = erpPurchaseStockDetailMapper.getByMap(param);
		if (detail != null && detail.size() != 0) {
			return detail.get(0);
		}
		return null;
	}
}
