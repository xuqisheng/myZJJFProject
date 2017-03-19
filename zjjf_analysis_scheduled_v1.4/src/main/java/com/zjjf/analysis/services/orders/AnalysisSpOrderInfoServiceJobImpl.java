package com.zjjf.analysis.services.orders;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderInfo;
import com.zjjf.analysis.beans.origin.base.Region;
import com.zjjf.analysis.beans.origin.orders.SpOrderDetail;
import com.zjjf.analysis.beans.origin.orders.SpOrderInfo;
import com.zjjf.analysis.beans.origin.store.Store;
import com.zjjf.analysis.beans.origin.supplier.SpGroup;
import com.zjjf.analysis.beans.origin.supplier.Supplier;
import com.zjjf.analysis.common.utils.DateUtil;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.mapper.analysis.SupplierIsZjMapper;
import com.zjjf.analysis.mapper.origin.RegionMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.mapper.origin.SpOrderDetailMapper;
import com.zjjf.analysis.mapper.origin.SpOrderInfoMapper;
import com.zjjf.analysis.mapper.origin.StoreMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class AnalysisSpOrderInfoServiceJobImpl extends AbstractTimeService<AnalysisSpOrderInfo, AnalysisSpOrderInfo> {

	@Autowired
	private StoreMapper storeMapper;

	@Autowired
	private SupplierMapper supplierMapper;

	@Autowired
	private SpGroupMapper spGroupMapper;

	@Autowired
	private SupplierIsZjMapper supplierIsZjMapper;

	@Autowired
	private SpOrderInfoMapper spOrderInfoMapper;

	@Autowired
	private SpOrderDetailMapper spOrderDetailMapper;

	@Autowired
	private RegionMapper regionMapper;

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Override
	public List<? extends AnalysisSpOrderInfo> getDataByTime(String yyyyMMdd, Integer offset) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gaveTimeBegin", DateUtil.timeyyyyMMddConvertyyyy_MM_dd(yyyyMMdd) + " 00:00:00");
		paramMap.put("gaveTimeEnd", DateUtil.timeyyyyMMddConvertyyyy_MM_dd(yyyyMMdd) + " 59:59:59");
		paramMap.put("offset", (offset - 1) * 1000);
		if (Integer.valueOf(yyyyMMdd) < 20151104) {
			List<AnalysisSpOrderInfo> supportDataList = spOrderInfoMapper.getSpSupportDataBefore(paramMap);
			if (supportDataList.size() > 0) {
				return supportDataList;
			}
		} else {
			List<AnalysisSpOrderInfo> supportDataList = spOrderInfoMapper.getSpSupportDataToday(paramMap);
			if (supportDataList.size() > 0) {
				return supportDataList;
			}
		}
		return new ArrayList<AnalysisSpOrderInfo>();
	}

	@Override
	public void _process(AnalysisSpOrderInfo t, AnalysisSpOrderInfo v, String dayTime) {

		String supplierId = t.getSupplierId();
		AnalysisSpOrderInfo bean = analysisSpOrderInfoMapper.getByChirdOrderNo(t.getChirdOrderNo());
		// 订单号已经存在，则不需要新增
		if (bean != null && bean.getOrderNo() != null) {
			update_info(v, bean.getId(), t.getStatus(), supplierId);
		} else {
			add_info(t, v, dayTime);
		}
	}

	// 2为联合采购订单，1为店宝订单
	private Integer isZjOrder(String supplierId) {

		List<String> isZjGroup = supplierIsZjMapper.getSupplierList();
		if (isZjGroup.contains(supplierId)) {
			return 2;
		} else {
			return 1;
		}
	}

	// 获取供应商城市，区域，定格等查询条件的信息
	private void getSupplierQueryParam(AnalysisSpOrderInfo v, String supplierId, Integer spGroupId, Integer storeId) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.clear();
		paramMap.put("supplierId", supplierId);
		paramMap.put("spGroupId", spGroupId);
		Supplier supplier = supplierMapper.getBySupplierId(paramMap);
		if (supplier != null) {
			v.setSupplierName(supplier.getSupplierName());
		}
		SpGroup spGroup = spGroupMapper.getById(spGroupId);
		if (spGroup != null) {
			v.setCityId(spGroup.getCityId());
			v.setAreaId(spGroup.getAreaId());
			Region regionCity = regionMapper.getById(spGroup.getCityId());
			Region regionArea = regionMapper.getById(spGroup.getAreaId());
			if (regionCity != null) {
				v.setCityName(regionCity.getName());
			}
			if (regionArea != null) {
				v.setAreaName(regionArea.getName());
			}
			v.setSpGroupName(spGroup.getName());
		}
		Store store = storeMapper.getByStoreId(storeId);
		if (store != null) {
			v.setStoreName(store.getStoreName());
			v.setStoreCode(store.getStoreCode());
		}
	}

	private void add_info(AnalysisSpOrderInfo t, AnalysisSpOrderInfo v, String dayTime) {

		String pid = t.getPid();
		Integer storeId = t.getStoreId();
		String supplierId = t.getSupplierId();
		String chirdOrderNo = t.getChirdOrderNo();
		SpOrderInfo info = spOrderInfoMapper.getParentOrderNoByPid(pid);
		if (info == null) {
			return;
		}
		String orderNo = info.getOrderId();
		SpOrderDetail detail = spOrderDetailMapper.getByOrderId2(chirdOrderNo);
		if (detail == null) {
			return;
		}
		Integer spGroupId = detail.getSpGroupId();
		v.setSpGroupId(spGroupId);
		v.setIsZj(this.isZjOrder(supplierId));
		v.setAddTime(t.getAddTime());
		v.setGaveTime(t.getGaveTime());
		v.setOrderNo(orderNo);
		v.setChirdOrderNo(t.getChirdOrderNo());
		SpOrderDetail spOrderDetail = spOrderDetailMapper.getDetaiGatherByOrderId(t.getChirdOrderNo());
		if (spOrderDetail != null) {
			if (spOrderDetail.getQuantity() != null) {
				v.setQuantity(Integer.valueOf(spOrderDetail.getQuantity()));
			}
			v.setSku(spOrderDetail.getSku());
		} else {
			v.setQuantity(0);
			v.setSku(0);
		}
		v.setGoodsPrice(t.getGoodsPrice());
		v.setCoupon(t.getCoupon());

		v.setRebate(t.getRebate());
		v.setItemPrice(t.getItemPrice());
		v.setOrderPrice(t.getOrderPrice());
		v.setFreight(t.getFreight());// 运费

		// 实付金额 = 订单金额 - 满减 - 优惠券金额 + 运费
		v.setPayMoney(t.getOrderPrice());
		v.setFee(t.getFee());
		v.setSupportmetho(t.getSupportmetho());
		v.setSupportStatus(t.getSupportStatus());
		v.setStatus(t.getStatus());

		v.setSupplierId(supplierId);
		v.setSpGroupId(spGroupId);
		v.setStoreId(storeId);

		v.setDayTime(Integer.valueOf(dayTime));
		v.setAckTime(t.getAckTime());
		v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));
		this.getSupplierQueryParam(v, supplierId, spGroupId, storeId);
		analysisSpOrderInfoMapper.insert(v);
	}

	private void update_info(AnalysisSpOrderInfo v, Integer id, Integer status, String supplierId) {

		v.setStatus(status);
		v.setId(id);
		v.setIsZj(isZjOrder(supplierId));
		analysisSpOrderInfoMapper.updateBean(v);
	}
}
