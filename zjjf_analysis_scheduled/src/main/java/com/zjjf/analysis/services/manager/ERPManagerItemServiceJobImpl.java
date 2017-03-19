package com.zjjf.analysis.services.manager;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.items.ERPManagerItemDaily;
import com.zjjf.analysis.beans.corner.ERPManagerItemVo;
import com.zjjf.analysis.beans.corner.ERPManagerOrderDetail;
import com.zjjf.analysis.beans.corner.ERPManagerOrderInfo;
import com.zjjf.analysis.beans.origin.supplier.Supplier;
import com.zjjf.analysis.mapper.analysis.ERPManagerItemDailyMapper;
import com.zjjf.analysis.mapper.corner.ERPManagerItemMapper;
import com.zjjf.analysis.mapper.corner.ERPManagerOrderDetailMapper;
import com.zjjf.analysis.mapper.corner.ERPManagerOrderInfoMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class ERPManagerItemServiceJobImpl extends AbstractTimeService<ERPManagerItemVo, ERPManagerItemDaily> {

	@Autowired
	private ERPManagerItemMapper erpManagerItemMapper;

	@Autowired
	private ERPManagerItemDailyMapper erpManagerItemDailyMapper;

	@Autowired
	private ERPManagerOrderDetailMapper erpManagerOrderDetailMapper;

	@Autowired
	private ERPManagerOrderInfoMapper erpManagerOrderInfoMapper;

	@Autowired
	private SupplierMapper supplierMapper;

	@Override
	public List<? extends ERPManagerItemVo> getDataByTime(String yyyyMMdd, Integer i) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (i - 1) * 1000);
		return erpManagerItemMapper.getAll(map);
	}

	private boolean isValidItem(String managerId, String supplierId, Integer itemBaseId) {

		if (managerId == null || supplierId == null || itemBaseId == null) {
			return false;
		}
		return true;
	}

	@Override
	public void _process(ERPManagerItemVo t, ERPManagerItemDaily v, String dayTime) {

		String managerId = t.getManagerId();
		String supplierId = t.getSupplierId();
		Integer itemBaseId = t.getItemBaseId();
		String itemId = t.getId();
		String itemCode = t.getItemCode();
		if (!isValidItem(managerId, supplierId, itemBaseId)) {
			return;
		}
		v.setManagerId(managerId);
		v.setItemId(t.getId());
		v.setItemBaseId(itemBaseId);
		v.setSupplierId(supplierId);
		v.setTaxRate(t.getTaxRate());
		Supplier supp = supplierMapper.getById(supplierId);
		v.setCooperWarehouse(supp != null ? supp.getSupplierName() : null);
		v.setItemCode(itemCode);
		List<ERPManagerOrderInfo> managerOrderInfoList = erpManagerOrderInfoMapper.getByManagerIdAndSupplierId(managerId, supplierId);
		managerOrderInfoList.stream().forEach(s -> getOrderDetealItem(v, s.getOrderId(), managerId, supplierId, itemId, itemBaseId, dayTime));
	}

	private void getOrderDetealItem(ERPManagerItemDaily v, String orderId, String managerId, String supplierId, String itemId, Integer itemBaseId,
			String dayTime) {

		List<ERPManagerOrderDetail> managerOrderDetailList = erpManagerOrderDetailMapper.getByOrderInfo(managerId, orderId, itemBaseId, itemId);
		managerOrderDetailList.stream().forEach(s -> avgDetail(v, s, s.getOrderId(), dayTime));
	}

	private void avgDetail(ERPManagerItemDaily v, ERPManagerOrderDetail detail, String orderId, String dayTime) {

		// 插入该 商品信息
		v.setBarCode(detail.getBarCode());
		v.setBoxCode(detail.getMdseId());
		// 采购价
		v.setAreaPrice(detail.getAreaPrice());
		// 插入 dayTime(dayTimeFromAddTime)
		v.setDayTime(Integer.valueOf(dayTime));
		// 冗余 orderId,pid
		v.setOrderId(orderId);
		v.setPid(detail.getId());
		erpManagerItemDailyMapper.insert(v);
	}
}
