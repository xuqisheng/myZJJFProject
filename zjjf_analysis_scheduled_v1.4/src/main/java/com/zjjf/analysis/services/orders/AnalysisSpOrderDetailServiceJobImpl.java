package com.zjjf.analysis.services.orders;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderInfo;
import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderDetail;
import com.zjjf.analysis.beans.origin.base.Region;
import com.zjjf.analysis.beans.origin.orders.SpOrderDetail;
import com.zjjf.analysis.beans.origin.store.Store;
import com.zjjf.analysis.beans.origin.supplier.SpGroup;
import com.zjjf.analysis.beans.origin.supplier.Supplier;
import com.zjjf.analysis.common.utils.DateUtil;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderDetailMapper;
import com.zjjf.analysis.mapper.origin.RegionMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.mapper.origin.SpOrderDetailMapper;
import com.zjjf.analysis.mapper.origin.StoreMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class AnalysisSpOrderDetailServiceJobImpl extends AbstractTimeService<SpOrderDetail, AnalysisSpOrderDetail> {

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Autowired
	private StoreMapper storeMapper;

	@Autowired
	private SupplierMapper supplierMapper;

	@Autowired
	private SpGroupMapper spGroupMapper;

	@Autowired
	private SpOrderDetailMapper spOrderDetailMapper;
	
	@Autowired
	private RegionMapper regionMapper;

	@Autowired
	private AnalysisSpOrderDetailMapper analysisSpOrderDetailMapper;

	@Override
	public List<? extends SpOrderDetail> getDataByTime(String yyyyMMdd, Integer offset) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("offset", (offset - 1) * 1000);
		paramMap.put("gaveTimeBegin", DateUtil.timeyyyyMMddConvertyyyy_MM_dd(yyyyMMdd) + " 00:00:00");
		paramMap.put("gaveTimeEnd", DateUtil.timeyyyyMMddConvertyyyy_MM_dd(yyyyMMdd) + " 59:59:59");
		return spOrderDetailMapper.getTodayData(paramMap);
	}

	@Override
	public void _process(SpOrderDetail t, AnalysisSpOrderDetail v, String dayTime) {

		AnalysisSpOrderDetail bean = analysisSpOrderDetailMapper.getByOrgPkId(t.getId());
		if (bean != null && bean.getId() != null) {
		} else {
			this.addBean(t, v, dayTime);
		}
	}

	private int getInfoIdBy_unique(String orderId) {
		AnalysisSpOrderInfo baseSpOrderInfo = analysisSpOrderInfoMapper.getByChirdOrderNo(orderId);
		if (baseSpOrderInfo != null) {
			return baseSpOrderInfo.getId();
		}
		return 0;
	}

	private void addBean(SpOrderDetail t, AnalysisSpOrderDetail v, String dayTime) {

		Integer infoId = getInfoIdBy_unique(t.getOrderId2());
		if (infoId != 0) {
			BeanUtils.copyProperties(t, v);
			v.setId(null);
			v.setInfoId(infoId);
			v.setOrg_pk_id(t.getId() + "");
			v.setGaveTime(t.getGaveTime());
			// 下列为查询冗余字段
			getSupplierQueryParam(v, t.getSpId(), t.getSpGroupId(),t.getStoreId());
			v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));
			v.setDayTime(Integer.valueOf(dayTime));
			analysisSpOrderDetailMapper.insert(v);
		}
	}

	// 获取供应商城市，区域，定格等查询条件的信息
	private void getSupplierQueryParam(AnalysisSpOrderDetail v, String supplierId, Integer spGroupId, Integer storeId) {

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
		}
	}
}
