package com.zjjf.analysis.services.orders.sp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.TurnoverPage;
import com.zjjf.analysis.beans.analysis.supplier.SaleDailyReport;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.beans.vo.orders.sporder.SpOrderTurnoverVo;
import com.zjjf.analysis.mapper.analysis.SaleDailyReportMapper;
import com.zjjf.analysis.mapper.analysis.TurnoverPageMapper;
import com.zjjf.analysis.producer.authority.IAuthorityLevel;
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.authority.IUserService;
import com.zjjf.analysis.producer.orders.ISpOrdersTurnoverService;
import com.zjjf.analysis.services.AbstractBaseService;
import com.zjjf.analysis.utils.DateUtils;
import com.zjjf.analysis.utils.MapUtils;

@Service(version = "1.0.0")
public class SpOrderTurnoverServiceImpl extends AbstractBaseService<TurnoverPage> implements ISpOrdersTurnoverService {

	@Autowired
	private TurnoverPageMapper turnoverPageMapper;
	
	@Autowired
	private SaleDailyReportMapper saleDailyReportMapper;

	@Autowired
	private IAuthorityLevel authorityLevel;

	@Autowired
	private IUserService userService;

	@Autowired
	private IAuthoritydata authoritydata;

	@Override
	public List<Object[]> getOrderData(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return null;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		Object[][] authorityArray = authoritydata.getAuthorityFilter(userName, menuId);
		MapUtils.array_2_map(authorityArray, paramMap);
		System.out.println(this.getClass() + "" + "======paramMap: " + paramMap);
		List<TurnoverPage> dataList = turnoverPageMapper.getTurnoverData(appendParam(paramMap));
		
		String dayTime = paramMap.get("dayTime") + "";
		HashMap<String, Object> saleDailyMap = new HashMap<String, Object>();
		for (TurnoverPage v : dataList) {
			v.setYesTodayTurnover(getTurnoverByTime(dayTime, "yesToday", v.getSupplierId(), v.getSpGroupId()));
			v.setDailyIncrease(getIncrease(v.getTodayTurnover(), v.getYesTodayTurnover()));
			v.setLastMonthTurnover(getTurnoverByTime(dayTime, "lastMonth", v.getSupplierId(), v.getSpGroupId()));
			v.setThisMonthTurnover(getTurnoverByTime(dayTime, "thisMonth", v.getSupplierId(), v.getSpGroupId()));
			v.setMonthIncrease(getIncrease(v.getThisMonthTurnover(), v.getLastMonthTurnover()));
			
			//合并销售日报
			List<SaleDailyReport> saleDailyDataList = saleDailyReportMapper.getData(append(saleDailyMap, v.getSupplierId(), v.getSpGroupId(), dayTime));
			saleDailyMap.clear();
			if (saleDailyDataList != null && saleDailyDataList.size() > 0) {
				SaleDailyReport saleDailyData = saleDailyDataList.get(0);
				setSaleDailyInfo(v, saleDailyData);
			}
		}
		return stand_by_title(dataList, getOrderTitleEn(authorityArray));
	}
	
	private void setSaleDailyInfo(TurnoverPage v, SaleDailyReport saleDailyData) {
		v.setTodayGoal(saleDailyData.getTodayGoal());
		v.setRateDailyComp(new BigDecimal(saleDailyData.getRateDailyComp()));
		v.setFee(saleDailyData.getFee());
		v.setRateFee(new BigDecimal(saleDailyData.getRateFee()));
		v.setSumMonth(saleDailyData.getSumMonth());
		v.setGoalMonth(saleDailyData.getGoalMonth());
		v.setRateMonthComp(new BigDecimal(saleDailyData.getRateMonthComp()));
		v.setTotalFee(saleDailyData.getTotalFee());
		v.setRateFeeMonth(new BigDecimal(saleDailyData.getRateFeeMonth()));
	}

	public HashMap<String, Object> appendParam(HashMap<String, Object> paramMap) {
		paramMap.put("dayTime", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(paramMap.get("dayTime") + ""));
		return paramMap;
	}
	
	private HashMap<String, Object> append(HashMap<String, Object> paramMap, String supplierId, Integer spGroupId, String dayTime) {
 		paramMap.put("pageNo", 0);
 		paramMap.put("offset", 1);
		paramMap.put("dayTime", dayTime);
		paramMap.put("spGroupId", spGroupId);
		List<String> list = new ArrayList<String>();
		list.add(supplierId);
		paramMap.put("supplierIdList", list);
		return paramMap;
	}

	private BigDecimal getTurnoverByTime(String dayTime, String type, String supplierId, Integer spGroupId) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplierId", supplierId);
		paramMap.put("spGroupId", spGroupId);
		switch (type) {
		case "yesToday":
			paramMap.put("dayTime", DateUtils.getPreDay(dayTime, "yyyyMMdd"));
			break;
		case "lastMonth":
			paramMap.put("dayTimeBegin", DateUtils.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", 2));
			paramMap.put("dayTimeEnd", DateUtils.getZjjfMonthEndTime(dayTime, "yyyyMMdd", 1));
			break;
		case "thisMonth":
			paramMap.put("dayTimeBegin", DateUtils.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", 1));
			paramMap.put("dayTimeEnd", dayTime);
			break;
		default:
			break;
		}
		SpOrderTurnoverVo bean = turnoverPageMapper.getTurnoverBySpIdSpGroupId(paramMap);
		if (bean != null) {
			return bean.getTurnover();
		} else {
			return new BigDecimal(0);
		}
	}

	@Override
	public Integer getTotalCount(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return null;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		Object[][] authorityArray = authoritydata.getAuthorityFilter(userName, menuId);
		MapUtils.array_2_map(authorityArray, paramMap);
		System.out.println(this.getClass() + "" + "======paramMap: " + paramMap);
		return turnoverPageMapper.getTurnoverDataCount(appendParam(paramMap));
	}

	@Override
	public List<Object[]> getExcelData(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return null;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		Object[][] authorityArray = authoritydata.getAuthorityFilter(userName, menuId);
		MapUtils.array_2_map(authorityArray, paramMap);
		System.out.println(this.getClass() + "" + "======paramMap: " + paramMap);
		List<TurnoverPage> dataList = turnoverPageMapper.getTurnoverDataExcel(appendParam(paramMap));
		String dayTime = paramMap.get("dayTime") + "";
		for (TurnoverPage v : dataList) {
			v.setYesTodayTurnover(getTurnoverByTime(dayTime, "yesToday", v.getSupplierId(), v.getSpGroupId()));
			v.setDailyIncrease(getIncrease(v.getTodayTurnover(), v.getYesTodayTurnover()));
			v.setLastMonthTurnover(getTurnoverByTime(dayTime, "lastMonth", v.getSupplierId(), v.getSpGroupId()));
			v.setThisMonthTurnover(getTurnoverByTime(dayTime, "thisMonth", v.getSupplierId(), v.getSpGroupId()));
			v.setMonthIncrease(getIncrease(v.getThisMonthTurnover(), v.getLastMonthTurnover()));
		}
		return stand_by_title(dataList, getOrderTitleEn(authorityArray));
	}

	@Override
	public HashMap<String, Object> getMonthMummary(HashMap<String, Object> paramMap, String userName, Integer menuId) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return null;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		Object[][] authorityArray = authoritydata.getAuthorityFilter(userName, menuId);
		MapUtils.array_2_map(authorityArray, paramMap);
		System.out.println(this.getClass() + "" + "======paramMap: " + paramMap);
		String dayTime = DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(paramMap.get("dayTime") + "");
		paramMap.remove("dayTime");

		HashMap<String, Object> lastMonthMap = new  HashMap<String, Object>();
		lastMonthMap.put("dayTimeBegin", DateUtils.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", 2));
		lastMonthMap.put("dayTimeEnd", DateUtils.getZjjfMonthEndTime(dayTime, "yyyyMMdd", 1));
		lastMonthMap.putAll(paramMap);
		SpOrderTurnoverVo lastMonth = turnoverPageMapper.getTurnoverByTime(lastMonthMap);
		resultMap.put("lastMonthTurnoverSum", lastMonth.getTurnover());

		HashMap<String, Object> thisMonthMap = new HashMap<String, Object>();
		thisMonthMap.put("dayTimeBegin", DateUtils.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", 1));
		thisMonthMap.put("dayTimeEnd", dayTime);
		thisMonthMap.putAll(paramMap);
		SpOrderTurnoverVo thisMonth = turnoverPageMapper.getTurnoverByTime(thisMonthMap);
		resultMap.put("thisMonthTurnoverSum", thisMonth.getTurnover());
		
		resultMap.put("monthIncrease", getIncrease(thisMonth.getTurnover(), lastMonth.getTurnover()));
		return resultMap;
	}

	@Override
	public HashMap<String, Object> getDailyMummary(HashMap<String, Object> paramMap, String userName, Integer menuId) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return null;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		Object[][] authorityArray = authoritydata.getAuthorityFilter(userName, menuId);
		MapUtils.array_2_map(authorityArray, paramMap);
		System.out.println(this.getClass() + "" + "======paramMap: " + paramMap);
		String dayTime = DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(paramMap.get("dayTime") + "");
		paramMap.remove("dayTime");
		
		HashMap<String, Object> yesTodayMap = new HashMap<String, Object>();
		yesTodayMap.put("dayTime", DateUtils.getPreDay(dayTime, "yyyyMMdd"));
		yesTodayMap.putAll(paramMap);
		SpOrderTurnoverVo yesToday = turnoverPageMapper.getTurnoverByTime(yesTodayMap);
		resultMap.put("yesTodayTurnoverSum", yesToday.getTurnover());
		
		HashMap<String, Object> todayMap = new HashMap<String, Object>();
		todayMap.put("dayTime", dayTime);
		todayMap.putAll(paramMap);
		SpOrderTurnoverVo today = turnoverPageMapper.getTurnoverByTime(todayMap);
		resultMap.put("todayTurnoverSum", today.getTurnover());
		
		resultMap.put("dailyIncrease", getIncrease(today.getTurnover(), yesToday.getTurnover()));
		return resultMap;
	}

	private static BigDecimal getIncrease(BigDecimal a, BigDecimal b) {
		if (BigDecimal.ZERO.equals(b) || b.doubleValue() == new BigDecimal(0.00).doubleValue()) {
			return new BigDecimal(0);
		} else {
			return a.subtract(b).multiply(new BigDecimal(100)).divide(b, 2, BigDecimal.ROUND_HALF_UP);
		}
	}
}
