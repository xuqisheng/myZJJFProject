package com.zjjf.analysis.producer.store;

import java.util.HashMap;

import com.zjjf.analysis.producer.base.IViewData;

public interface IStoreDailyReportService<T> extends IViewData<T> {

	public abstract HashMap<String, Object> getBannerData(HashMap<String, Object> paramMap);
}
