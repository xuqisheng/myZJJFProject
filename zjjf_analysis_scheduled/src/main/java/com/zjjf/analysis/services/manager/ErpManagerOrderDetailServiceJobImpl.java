package com.zjjf.analysis.services.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.erp.AnalysisErpManagerOrderDetail;
import com.zjjf.analysis.beans.corner.ERPManagerItem;
import com.zjjf.analysis.beans.corner.ERPManagerOrderDetail;
import com.zjjf.analysis.beans.corner.ERPManagerOrderInfo;
import com.zjjf.analysis.mapper.analysis.AnalysisErpManagerOrderDetailMapper;
import com.zjjf.analysis.mapper.corner.ERPManagerItemMapper;
import com.zjjf.analysis.mapper.corner.ERPManagerOrderDetailMapper;
import com.zjjf.analysis.mapper.corner.ERPManagerOrderInfoMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class ErpManagerOrderDetailServiceJobImpl extends AbstractTimeService<ERPManagerOrderDetail, AnalysisErpManagerOrderDetail> {

	@Autowired
	private ERPManagerItemMapper erpManagerItemMapper;

	@Autowired
	private ERPManagerOrderDetailMapper erpManagerOrderDetailMapper;

	@Autowired
	private ERPManagerOrderInfoMapper erpManagerOrderInfoMapper;

	@Autowired
	private AnalysisErpManagerOrderDetailMapper analysisErpManagerOrderDetailMapper;

	@Override
	public List<? extends ERPManagerOrderDetail> getDataByTime(String yyyyMMdd, Integer i) {

		return erpManagerOrderDetailMapper.getAll(yyyyMMdd, (i - 1) * 1000);
	}

	@Override
	public void _process(ERPManagerOrderDetail t, AnalysisErpManagerOrderDetail v, String dayTime) {

		String managerId = t.getManagerId();
		String orderId = t.getOrderId();
		ERPManagerOrderInfo orderInfo = erpManagerOrderInfoMapper.getByOrderId(orderId);
		if (orderInfo == null) {
			return;
		}
		String supplierId = orderInfo.getSupplierId();
		ERPManagerItem erpManagerItem = erpManagerItemMapper.getByItemId(t.getItemId());
		if (erpManagerItem == null) {
			return;
		}
		BigDecimal taxRate = erpManagerItem.getTaxRate();
		v.setDetailId(t.getId());
		v.setOrderId(orderId);
		v.setPkg(t.getPkg());
		v.setBarCode(t.getBarCode());
		v.setMdseId(t.getMdseId());
		v.setItemCode(t.getItemCode());

		v.setName(t.getName());
		v.setSpec(t.getSpec());
		v.setImg(t.getImg());
		v.setItemId(t.getItemId());

		v.setManagerId(managerId);
		v.setSupplierId(supplierId);
		v.setItemBaseId(t.getItemBaseId());
		v.setAreaPrice(t.getAreaPrice());
		v.setTaxRate(taxRate);

		v.setQuantity(t.getQuantity());
		v.setAddTime(t.getAddTime());
		v.setDayTime(dayTime);
		v.setOperateQuantity(t.getOperateQuantity());
		v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));
		analysisErpManagerOrderDetailMapper.insert(v);
	}
}
