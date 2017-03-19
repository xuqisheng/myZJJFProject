package com.zjjf.analysis.services.financial;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.analysis.base.TurnoverPage;
import com.zjjf.analysis.beans.analysis.supplier.SupplierDaily;
import com.zjjf.analysis.beans.origin.supplier.Supplier;
import com.zjjf.analysis.common.utils.DateUtil;
import com.zjjf.analysis.common.utils.NumberUtils;
import com.zjjf.analysis.mapper.analysis.SupplierDailyMapper;
import com.zjjf.analysis.mapper.analysis.TurnoverPageMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class TurnoverPageServiceJobImpl extends AbstractTimeService<SupplierDaily, TurnoverPage> {

	@Autowired
	private SupplierDailyMapper supplierDailyMapper;

	@Autowired
	private SupplierMapper supplierMapper;

	@Autowired
	private TurnoverPageMapper turnoverPageMapper;

	@Override
	public List<? extends SupplierDaily> getDataByTime(String dayTime, Integer i) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("yyyyMMddTime", dayTime);
		paramMap.put("offset", (i - 1) * 1000);
		return supplierDailyMapper.getDataByTime(paramMap);
	}

	@Override
	public void _process(SupplierDaily t, TurnoverPage v, String dayTime) {
		
		String supplierId = t.getSupplierId();
		Supplier supplier = supplierMapper.getById(supplierId);
		if (supplier == null) {
			return;
		}
		v.setSupplierId(supplierId);
		v.setAddTime(t.getDayTime());
		v.setCityId(supplier.getCity());
		v.setAreaId(supplier.getAreaId());
		v.setSpGroupId(t.getSpGroupId());

		v.setTodayTurnover(t.getTurnover());
		SupplierDaily beforDayBean = this.getDayTurnover(t.getDayTime(), supplierId);
		if (beforDayBean != null) {
			v.setYesTodayTurnover(beforDayBean.getTurnover());
		}
		if (t.getTurnover() != null && beforDayBean.getTurnover() != null) {
			v.setDailyIncrease(NumberUtils.getIncrease_subtract(t.getTurnover(), beforDayBean.getTurnover()));
		} else {
			v.setDailyIncrease(new BigDecimal(0));
		}
		SupplierDaily lastMonthBean = this.getLastMonthTurnover(t.getDayTime(), supplierId, 2);
		if (lastMonthBean != null) {
			v.setLastMonthTurnover(lastMonthBean.getTurnover());
		}
		SupplierDaily thisMonthBean = this.getThisMonthTurnover(t.getDayTime(), supplierId, 1);
		if (thisMonthBean != null) {
			v.setThisMonthTurnover(t.getTurnover().add(thisMonthBean.getTurnover()));
		}
		if (lastMonthBean.getTurnover() != null && thisMonthBean.getTurnover() != null) {
			v.setMonthIncrease(NumberUtils.getIncrease_subtract(v.getThisMonthTurnover(), lastMonthBean.getTurnover()));
		} else {
			v.setDailyIncrease(new BigDecimal(0));
		}
		v.setCreateTime(Integer.valueOf(new Date().getTime() / 1000L + ""));

		this.addBean(t, v);
	}

	public void addBean(SupplierDaily t, TurnoverPage v) {

		turnoverPageMapper.insert(v);
	}

	private SupplierDaily getLastMonthTurnover(String dayTime, String supplierId, Integer index) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("monthBegin", DateUtil.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", index));
		paramMap.put("monthEnd", DateUtil.getZjjfMonthEndTime(dayTime, "yyyyMMdd", index - 1));
		paramMap.put("supplierId", supplierId);
		System.out.println("dayTime=" + dayTime + ", getLastMonthTurnover: paramMap=" + paramMap);
		return supplierDailyMapper.getTurnoverByLastMonth(paramMap);
	}

	private SupplierDaily getThisMonthTurnover(String dayTime, String supplierId, Integer index) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("monthBegin", DateUtil.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", index));
		paramMap.put("monthEnd", DateUtil.getZjjfMonthEndTime(dayTime, "yyyyMMdd", index - 1));
		paramMap.put("supplierId", supplierId);
		// System.out.println("getThisMonthTurnover: paramMap=" + paramMap);
		return supplierDailyMapper.getTurnoverByThisMonth(paramMap);
	}

	private SupplierDaily getDayTurnover(String dayTime, String supplierId) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beforeDay", DateUtil.getPreDay(dayTime, "yyyyMMdd"));
		paramMap.put("supplierId", supplierId);
		return supplierDailyMapper.getBeforeDay(paramMap);
	}
}
