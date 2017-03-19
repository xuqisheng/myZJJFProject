package com.zjjf.analysis.producer.base;

import java.util.HashMap;

import com.zjjf.analysis.utils.DateUtils;

public abstract class AbstractTimeParam<T> {

	public HashMap<String, Object> paramTimeConver(HashMap<String, Object> paramMap) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		String dayTime = paramMap.get("dayTime") + "";
		String timeType = paramMap.get("timeType") + "";
		switch (timeType) {
		case "week":
			map.put("dayTimeBegin", DateUtils.getCurrentMonday(dayTime));
			map.put("dayTimeEnd", dayTime);
			break;
		case "month":
			map.put("dayTimeBegin", DateUtils.getZjjfMonthBeginTime(dayTime, "yyyyMMdd", 1));
			map.put("dayTimeEnd", DateUtils.getZjjfMonthEndTime(dayTime, "yyyyMMdd", 0));
			break;
		default:
			map.put("dayTime", paramMap.get("dayTime"));
			break;
		}
		Integer pageNo = paramMap.get("offset") == null ? 1 : Integer.valueOf(paramMap.get("offset") + "");
		map.put("pageNo", (pageNo - 1) * 1000);
		map.put("offset", paramMap.get("offset") == null ? 10 : paramMap.get("offset"));
		return map;
	}
}
