package com.zjjf.analysis.producer.ajie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ISalemanRecordService {
	
	/**
	 * @category 业务员排名
	 * @param dayTime
	 * @param timeType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> getSalemanTopList(HashMap<String, Object> requestMap);
	
	/**
	 * @category 历史业绩，业绩分析图
	 * @param salemanIds
	 * @param dayTime
	 * @param timeType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> getDailyList(HashMap<String, Object> requestMap);
	
	/**
	 * @category 业绩汇总
	 * @param salemanIds
	 * @param dayTime
	 * @param timeType
	 * @return
	 */
	public Map<String, Object> getSummrization(HashMap<String, Object> requestMap);
	
	/**
	 * @category 订单明细
	 * @param salemanIds
	 * @param dayTime
	 * @param timeTye
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> getOrderDetail(HashMap<String, Object> requestMap);
	
}
