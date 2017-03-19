package com.zjjf.analysis.services.supplier;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.base.SupplierFreePage;
import com.zjjf.analysis.beans.analysis.supplier.SupplierDaily;
import com.zjjf.analysis.beans.origin.supplier.SpGroup;
import com.zjjf.analysis.beans.origin.supplier.Supplier;
import com.zjjf.analysis.common.utils.DateUtil;
import com.zjjf.analysis.common.utils.NumberUtils;
import com.zjjf.analysis.mapper.analysis.SupplierDailyMapper;
import com.zjjf.analysis.mapper.analysis.SupplierFreePageMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class SupplierFeeServiceJobImpl extends AbstractTimeService<SupplierDaily, SupplierFreePage> {

	@Autowired
	private SupplierFreePageMapper supplierFreePageMapper;

	@Autowired
	private SupplierDailyMapper supplierDailyMapper;

	@Autowired
	private SupplierMapper supplierMapper;
	
	@Autowired
	private SpGroupMapper spGroupMapper;

	@Override
	public List<? extends SupplierDaily> getDataByTime(String yyyyMMdd, Integer i) {
		HashMap<String, Object> supplierMap = new HashMap<String, Object>();
		supplierMap.put("offset", (i - 1) * 1000);
		supplierMap.put("yyyyMMddTime", yyyyMMdd);
		return supplierDailyMapper.getDataByTime(supplierMap);
	}

	@Override
	public void _process(SupplierDaily t, SupplierFreePage v, String dayTime) {

		String supplierId = t.getSupplierId();
		Supplier supplierBean = supplierMapper.getById(supplierId);
		if (supplierBean == null) {
			return;
		}
		v.setSupplierId(supplierId);
		v.setSpGroupId(t.getSpGroupId());
		SpGroup spGroup = spGroupMapper.getById(t.getSpGroupId());
		if (spGroup != null) {
			v.setCityId(spGroup.getCityId());
			v.setAreaId(spGroup.getAreaId());
		}
		v.setDayTime(Integer.valueOf(dayTime));
		v.setAddTime(t.getDayTime());
		v.setTodayTurnover(t.getTurnover());
		v.setTotalRebate(t.getTotalRebate());
		v.setTotalCoupon(t.getTotalCoupon());
		v.setTotalFree(t.getTotalRebate().add(t.getTotalCoupon()));
		if (v.getTotalFree() != null && t.getTurnover() != null) {
			v.setIncrease(NumberUtils.getIncrease(v.getTotalFree(), t.getTurnover()));
		} else {
			v.setIncrease(new BigDecimal(0));
		}
		SupplierFreePage monthBean = getThisMonthTurnover(t.getDayTime(), supplierId, t.getSpGroupId(), 1);
		if (monthBean != null) {
			v.setMonthTurnover(monthBean.getMonthTurnover().add(v.getTodayTurnover()));
			v.setMonthRebate(monthBean.getMonthRebate().add(v.getTotalRebate()));
			v.setMonthCoupon(monthBean.getMonthCoupon().add(v.getTotalCoupon()));
			v.setMonthTotalFree(monthBean.getMonthTotalFree().add(v.getTotalFree()));
			if (monthBean.getMonthTotalFree() != null && monthBean.getMonthTurnover() != null) {
				v.setMonthIncrease(NumberUtils.getIncrease(v.getMonthTotalFree(), v.getMonthTurnover()));
			} else {
				v.setMonthIncrease(new BigDecimal(0));
			}
		}
		v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));
		supplierFreePageMapper.insert(v);
	}

	private SupplierFreePage getThisMonthTurnover(String dayTime, String supplierId, Integer spGroupId, Integer index) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("monthBegin", DateUtil.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", index));
		paramMap.put("monthEnd", dayTime);
		paramMap.put("supplierId", supplierId);
		paramMap.put("spGroupId", spGroupId);
		return supplierDailyMapper.getThisMonthTurnover(paramMap);
	}
}
