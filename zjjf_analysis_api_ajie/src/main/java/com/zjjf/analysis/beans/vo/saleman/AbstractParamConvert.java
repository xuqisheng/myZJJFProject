package com.zjjf.analysis.beans.vo.saleman;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.utils.DateUtils;

public abstract class AbstractParamConvert {

	@SuppressWarnings("unchecked")
	protected BaseReqParam convertRequestParam(
			HashMap<String, Object> requestMap) {

		BaseReqParam reqParam = new BaseReqParam();
		String salesmanId = requestMap.get("salesmanId") != null ? (requestMap
				.get("salesmanId") + "") : null;
		String dayTime = requestMap.get("dayTime") != null ? (requestMap
				.get("dayTime") + "") : null;
		String timeType = requestMap.get("timeType") != null ? (requestMap
				.get("timeType") + "") : null;
		Integer pageNo = requestMap.get("pageNo") != null ? (Integer) (requestMap
				.get("pageNo")) : null;
		Integer pageSize = requestMap.get("pageSize") != null ? (Integer) (requestMap
				.get("pageSize")) : null;
		List<String> salemanIds = requestMap.get("salemanIds") != null ? (List<String>) (requestMap
				.get("salemanIds")) : null;
		String areaName = requestMap.get("areaName") != null ? (String) (requestMap
				.get("areaName")) : null;
		Integer isSum = requestMap.get("isSum") != null ? (Integer) (requestMap
				.get("isSum")) : null;
		setParam(reqParam, salesmanId, dayTime, timeType, pageNo, pageSize,
				salemanIds, areaName, isSum);
		return reqParam;
	}
	
	protected void putDayTime(HashMap<String, Object> paramMap, String dayTime, String timeType, Boolean isPaging , Integer pageNo, Integer pageSize) {
		switch (timeType!=null ? timeType : "") {
		case "week":
			paramMap.put("dayTimeBegin", DateUtils.getCurrentMonday(dayTime));
			paramMap.put("dayTimeEnd", DateUtils.getCurrentSunday(dayTime));
			break;
		case "month":
			paramMap.put("dayTimeBegin", DateUtils.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", 1));
			paramMap.put("dayTimeEnd", DateUtils.getZjjfMonthEndTime(dayTime, "yyyyMMdd", 0));
			break;
		case "nearWeek":
			paramMap.put("dayTimeBegin", DateUtils.getPrex(dayTime, 7));
			paramMap.put("dayTimeEnd", DateUtils.getPrex(dayTime, 1));
			break;
		case "nearMonth":
			paramMap.put("dayTimeBegin", DateUtils.getPrex(dayTime, 30));
			paramMap.put("dayTimeEnd", DateUtils.getPrex(dayTime, 1));
			break;
		case "yesterday":
			paramMap.put("dayTime", DateUtils.getPrex(dayTime, 1));
			break;
		case "lastWeek":
			paramMap.put("dayTimeBegin", DateUtils.getPrex(DateUtils.getCurrentMonday(dayTime), 7));
			paramMap.put("dayTimeEnd", DateUtils.getPrex(DateUtils.getCurrentSunday(dayTime), 7));
			break;
		case "lastMonth":
			String lastMonthLastDay = DateUtils.getPrex(DateUtils.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", 1), 1);
			String lastMonthFristDay = DateUtils.getZjjfMonthBeginTime(lastMonthLastDay, "yyyyMMdd", 1);
			paramMap.put("dayTimeBegin", lastMonthFristDay);
			paramMap.put("dayTimeEnd", lastMonthLastDay);
			break;
		default:
			paramMap.put("dayTime", dayTime);
			break;
		}
		if (isPaging) {
			paramMap.put("pageSize", (pageSize != null && pageSize > 0) ? pageSize : 10);
			paramMap.put("pageNo", (pageNo != null && pageNo > 0) ? (pageNo-1)*pageSize : 0);
		}
	}

	private void setParam(BaseReqParam reqParam, String salesmanId,
			String dayTime, String timeType, Integer pageNo, Integer pageSize,
			List<String> salemanIds, String areaName, Integer isSum) {
		reqParam.setSalesmanId(salesmanId);
		reqParam.setDayTime(dayTime);
		reqParam.setTimeType(timeType);
		reqParam.setPageNo(pageNo);
		reqParam.setPageSize(pageSize);
		reqParam.setSalemanIds(salemanIds);
		reqParam.setAreaName(areaName);
		reqParam.setIsSum(isSum);
	}
}
