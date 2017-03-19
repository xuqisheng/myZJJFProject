package com.zjjf.analysis.services.supplier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderInfo;
import com.zjjf.analysis.beans.analysis.supplier.SupplierDaily;
import com.zjjf.analysis.beans.origin.base.Region;
import com.zjjf.analysis.beans.origin.supplier.SpGroup;
import com.zjjf.analysis.beans.origin.supplier.Supplier;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderInfoMapper;
import com.zjjf.analysis.mapper.analysis.SupplierDailyMapper;
import com.zjjf.analysis.mapper.origin.RegionMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.mapper.origin.StoreMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class SupplierDailyServiceJobImpl extends AbstractTimeService<AnalysisSpOrderInfo, SupplierDaily> {

	@Autowired
	SupplierDailyMapper supplierDailyMapper;

	@Autowired
	private AnalysisSpOrderInfoMapper analysisSpOrderInfoMapper;

	@Autowired
	private StoreMapper storeMapper;

	@Autowired
	private SupplierMapper supplierMapper;

	@Autowired
	private SpGroupMapper spGroupMapper;

	@Autowired
	private RegionMapper regionMapper;

	@Override
	public List<AnalysisSpOrderInfo> getDataByTime(String dayTime, Integer i) {

		return analysisSpOrderInfoMapper.getSupplierIdList((i - 1) * 1000, dayTime);
	}

	@Override
	public void _process(AnalysisSpOrderInfo t, SupplierDaily v, String dayTime) {

		this.appendParam(t, v, dayTime);
	}

	private void appendParam(AnalysisSpOrderInfo t, SupplierDaily v, String dayTime) {

		String supplierId = t.getSupplierId();
		Integer spGroupId = t.getSpGroupId();
		SupplierDaily exitBean = supplierDailyMapper.getExitRecord(spGroupId, supplierId, dayTime);
		if (exitBean != null && exitBean.getSpGroupId() != 0) {
			update_supplier_daily(exitBean.getId(), v, spGroupId, supplierId, dayTime);
		} else {
			this.add_supplier_daily(v, spGroupId, supplierId, dayTime);
		}
	}

	private void add_supplier_daily(SupplierDaily v, Integer spGroupId, String supplierId, String dayTime) {

		SupplierDaily kpiSupplier = analysisSpOrderInfoMapper.getBySupplierIdKpi(supplierId, dayTime, spGroupId);
		if (kpiSupplier != null) {
			v.setValidTurnover(kpiSupplier.getTurnover());
		}
		SupplierDaily temp = analysisSpOrderInfoMapper.getBySupplierIdSpGroupIdAndAddTime(spGroupId, supplierId, dayTime);
		if (temp != null && temp.getSupplierId() != null) {
			v.setSupplierId(supplierId);
			v.setSpGroupId(spGroupId);
			v.setDayTime(dayTime);
			v.setTurnover(temp.getTurnover());
			v.setSku(temp.getSku());

			v.setTotalCoupon(temp.getTotalCoupon());
			v.setTotalRebate(temp.getTotalRebate());
			v.setTotalFreight(temp.getTotalFreight());
			v.setOrderCount(temp.getOrderCount());

			v.setQuantity(temp.getQuantity());
			v.setNewRegistered(getNewRegisterStore(spGroupId, dayTime));
			v.setDeliveryTimes(getDeliveryOrderTimes(spGroupId, supplierId, dayTime));
			getSupplierQueryParam(v, supplierId, spGroupId);
			v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));
			supplierDailyMapper.insert(v);
		}
	}

	private void update_supplier_daily(Integer id, SupplierDaily v, Integer spGroupId, String supplierId, String dayTime) {

		SupplierDaily kpiSupplier = analysisSpOrderInfoMapper.getBySupplierIdKpi(supplierId, dayTime, spGroupId);
		if (kpiSupplier != null) {
			v.setValidTurnover(kpiSupplier.getTurnover());
		}
		SupplierDaily temp = analysisSpOrderInfoMapper.getBySupplierIdSpGroupIdAndAddTime(spGroupId, supplierId, dayTime);
		if (temp != null && temp.getSupplierId() != null) {
			v.setSupplierId(supplierId);
			v.setSpGroupId(spGroupId);
			v.setDayTime(dayTime);
			v.setTurnover(temp.getTurnover());
			v.setSku(temp.getSku());

			v.setTotalCoupon(temp.getTotalCoupon());
			v.setTotalRebate(temp.getTotalRebate());
			v.setTotalFreight(temp.getTotalFreight());
			v.setOrderCount(temp.getOrderCount());

			v.setQuantity(temp.getQuantity());
			v.setNewRegistered(getNewRegisterStore(spGroupId, dayTime));
			v.setDeliveryTimes(getDeliveryOrderTimes(spGroupId, supplierId, dayTime));
			getSupplierQueryParam(v, supplierId, spGroupId);
			v.setId(id);
			supplierDailyMapper.updateBean(v);
		}
	}

	private Integer getNewRegisterStore(Integer spGroupId, String dayTime) {

		return storeMapper.getBySpGroupIdAndTime(spGroupId, dayTime);
	}

	private Integer getDeliveryOrderTimes(Integer spGroupId, String supplierId, String dayTime) {

		return analysisSpOrderInfoMapper.getOrderCount(spGroupId, supplierId, dayTime);
	}

	// 获取供应商城市，区域，定格等查询条件的信息
	private void getSupplierQueryParam(SupplierDaily v, String supplierId, Integer spGroupId) {

		Supplier supplier = supplierMapper.getBySupplierId(supplierId);
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
	}
}
