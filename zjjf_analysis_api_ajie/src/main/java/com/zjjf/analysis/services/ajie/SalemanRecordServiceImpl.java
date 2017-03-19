package com.zjjf.analysis.services.ajie;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.store.Store;
import com.zjjf.analysis.beans.vo.saleman.AbstractParamConvert;
import com.zjjf.analysis.beans.vo.saleman.BaseReqParam;
import com.zjjf.analysis.mapper.ajie.ShopOfSalesmanMapper;
import com.zjjf.analysis.mapper.analysis.SalemanDailysMapper;
import com.zjjf.analysis.mapper.analysis.SpOrderMapper;
import com.zjjf.analysis.mapper.analysis.StoreDailyMapper;
import com.zjjf.analysis.mapper.origin.StoreMapper;
import com.zjjf.analysis.producer.ajie.ISalemanRecordService;
import com.zjjf.analysis.utils.DateUtils;

@Service(version = "1.0.0")
public class SalemanRecordServiceImpl extends AbstractParamConvert implements ISalemanRecordService{

	@Autowired
	private SalemanDailysMapper salemanDailysMapper;
	
	@Autowired
	private StoreDailyMapper storeDailyMapper;
	
	@Autowired
	private SpOrderMapper spOrderMapper;
	
	@Autowired
	private ShopOfSalesmanMapper shopOfSalesmanMapper;
	
	@Autowired
	private StoreMapper storeMapper;
	
	@Override
	public List<Map<String, Object>> getSalemanTopList(HashMap<String, Object> requestMap) {
		
		BaseReqParam baseReq = convertRequestParam(requestMap);
		String dayTime = baseReq.getDayTime();
		String timeType = baseReq.getTimeType();
		Integer pageNo = baseReq.getPageNo();
		Integer pageSize = baseReq.getPageSize();
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		putDayTime(paramMap, dayTime, timeType, true, pageNo, pageSize);
		paramMap.put("salesmanId", baseReq.getSalesmanId());
		List<Map<String, Object>> topList = salemanDailysMapper.getTopList(paramMap);
		List<Map<String, Object>> lastTopList = getLastTopList(topList, paramMap);
		if (topList != null && topList.size() > 0) {
			
			for (Map<String, Object> map : topList) {
				setTopList(map, dayTime, timeType);
				//获取排行榜上升名次
				Integer thisSeqNo = Integer.valueOf(map.get("seqNo") + "");
				String salemanId = map.get("salemanId") + "";
				Integer lastSeqNo = getLastSeqNo(salemanId, lastTopList);
				map.put("raseNum", (lastSeqNo - thisSeqNo));
			}
		} else if (timeType == null || "".equals(timeType)) {
			List<Map<String, Object>> prexTopList = getPrexTopList(paramMap, dayTime, 1, pageNo, pageSize, baseReq.getSalesmanId());
			for (Map<String, Object> prexMap : prexTopList) {
				setTopList(prexMap, dayTime, null);
				prexMap.put("raseNum", 0);
				prexMap.put("turnover", 0);
			}
			return prexTopList;
		}
		return topList;
	}
	
	private List<Map<String, Object>> getPrexTopList(HashMap<String, Object> paramMap, String dayTime, Integer prex, Integer pageNo, Integer pageSize, String salesmanId) {
		
		paramMap.put("salesmanId", salesmanId);
		paramMap.put("pageNo", (pageNo-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		if (paramMap.containsKey("dayTime")) {
			paramMap.put("dayTime", DateUtils.getPrex(dayTime, prex));
		}
		List<Map<String, Object>> topList = salemanDailysMapper.getTopList(paramMap);
		return topList;
	}
	
	private void setTopList(Map<String, Object> map, String dayTime, String timeType) {
		
		if (map.containsKey("dayTime")) {
			map.remove("dayTimeBegin");
			map.remove("dayTimeEnd");
			map.put("dayTime", getDayTimeFormat(map.get("dayTime")+"", timeType));
		} else if (map.containsKey("dayTimeBegin") && map.containsKey("dayTimeEnd")) {
			map.put("dayTime", getDayTimeFormat(map.get("dayTimeEnd")+"", timeType));
		}
		map.put("updateTime", setNowTime("HH:mm"));
	}
	
	@Override
	public List<Map<String, Object>> getDailyList(HashMap<String, Object> requestMap) {
		
		BaseReqParam baseReq = convertRequestParam(requestMap);
		List<String> salemanIds = baseReq.getSalemanIds();
		String dayTime = baseReq.getDayTime();
		String timeType = baseReq.getTimeType();
		Integer pageNo = baseReq.getPageNo();
		Integer pageSize = baseReq.getPageSize();
		Integer isSum = baseReq.getIsSum();
		String areaName = baseReq.getAreaName();
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String appendIds = appendIds2String(salemanIds);
		paramMap.put("salemanIds", appendIds);
		paramMap.put("isSum", isSum != null ? isSum : 0);
		paramMap.put("areaName", areaName != null ? areaName : null);
		
		putDayTime(paramMap, dayTime, timeType, true, pageNo, pageSize);
		
		//今天 -> 倒退pageSize天 
		if (timeType == null || "".equals(timeType)) {
			
			paramMap.remove("dayTime");
			paramMap.remove("pageNo");
			paramMap.put("pageNo", 0);
			String endFlag = DateUtils.getPrex(dayTime, (pageNo-1)*pageSize);
			paramMap.put("dayTimeEnd", endFlag);
			paramMap.put("dayTimeBegin", DateUtils.getPrex(endFlag, (pageSize-1)));
		} else if ("month".equals(timeType)) {
			List<Map<String, Object>> monthDataList = new ArrayList<Map<String,Object>>();
			String dayTimeOfthisMonth = dayTime;
			for (int i = 0; i < (pageNo-1)*pageSize; i++) {
				if (pageNo == 1) {
					break;
				}
				dayTimeOfthisMonth = DateUtils.getPrex(DateUtils.getZjjfMonthBeginTime(dayTimeOfthisMonth, "yyyyMMdd", 1), 1);
			}
			Integer counter = 0;
			while (counter < pageSize) {
				paramMap.clear();
				paramMap.put("salemanIds", appendIds);
				paramMap.put("dayTimeBegin", DateUtils.getZjjfMonthBeginTime(dayTimeOfthisMonth, "yyyyMMdd", 1));
				paramMap.put("dayTimeEnd", DateUtils.getZjjfMonthEndTime(dayTimeOfthisMonth, "yyyyMMdd", 0));
				Map<String, Object> summrization = salemanDailysMapper.getSummrization(paramMap);
				if (summrization != null) {
					summrization.put("dayTime", getDayTimeFormat(dayTimeOfthisMonth, "month"));
					if (isSum == 1) {
						summrization.put("salemanId", null);
						summrization.put("salemanName", areaName);
					}
					monthDataList.add(summrization);
				} 
				counter ++;
				dayTimeOfthisMonth = DateUtils.getPrex(DateUtils.getZjjfMonthBeginTime(dayTimeOfthisMonth, "yyyyMMdd", 1), 1);
			}
			return monthDataList;
		} 
		
		List<Map<String, Object>> dailyList = salemanDailysMapper.getDailyList(paramMap);
		
		if (dailyList != null && "nearMonth".equals(timeType)) {
			String dayTimeBegin = paramMap.get("dayTimeBegin") + "";
			return setNearMonthData(dailyList, dayTimeBegin);
		}
		return dailyList;
	}
	
	@Override
	public Map<String, Object> getSummrization(HashMap<String, Object> requestMap) {
		
		BaseReqParam baseReq = convertRequestParam(requestMap);
		List<String> salemanIds = baseReq.getSalemanIds();
		String dayTime = baseReq.getDayTime();
		String timeType = baseReq.getTimeType();
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String appendIds = appendIds2String(salemanIds);
		paramMap.put("salemanIds", appendIds);
		putDayTime(paramMap, dayTime, timeType, false, null, null);
		
		Map<String, Object> resultMap = salemanDailysMapper.getSummrization(paramMap);
		if (resultMap != null) {
			String dayTimeFormat = getDayTimeFormat(dayTime, timeType);
			resultMap.put("dayTime", dayTimeFormat);
		}
		
		List<String> shopList = shopOfSalesmanMapper.getShopBySalesmanIds(appendIds);
		String appendStoreListStr = appendIds2String(shopList);
		paramMap.put("storeIds", appendStoreListStr);
		Map<String, Object> summrizationMap = storeDailyMapper.getSalemanSummrization(paramMap);
		if (resultMap != null && summrizationMap != null) {
			resultMap.put("activeStore", summrizationMap.get("activeStore"));
		}
		
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> getOrderDetail(HashMap<String, Object> requestMap) {
		
		BaseReqParam baseReq = convertRequestParam(requestMap);
		List<String> salemanIds = baseReq.getSalemanIds();
		String dayTime = baseReq.getDayTime();
		String timeType = baseReq.getTimeType();
		Integer pageNo = baseReq.getPageNo();
		Integer pageSize = baseReq.getPageSize();
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		//业务员店铺对应关系
		if (salemanIds != null && salemanIds.size() != 0) {
			String ids = appendIds2String(salemanIds);
			String storeList = getStoresBySalemanIds(ids);
			paramMap.put("storeIds", storeList);
		} else {
		}
		putDayTime(paramMap, dayTime, timeType, true, pageNo, pageSize);
		List<Map<String, Object>> mapList = spOrderMapper.getOrderListByMap(paramMap);
		//添加店铺手机号 - mapList
		if (mapList != null) {
			for (Map<String, Object> map : mapList) {
				Store s = storeMapper.getSthById(String.valueOf(map.get("storeId")));
				//如果是月，时间格式为yyyy-MM
				String dayTimeFormat = getDayTimeFormat(String.valueOf(map.get("dayTime") + ""), timeType);
				map.put("dayTime", dayTimeFormat);
				map.put("mobile", s != null ? s.getMobile() : null);
				map.put("storeCode", s != null ? s.getSupplierCode() : null);
				map.put("storeName", s != null ? s.getName() : null);
				//isZj字段返回含义：2(是)--1(是),1(否)--0(否)
				changeIsZj(map);
			}
		}
		
		return mapList;
	}
	
	private List<Map<String, Object>> getLastTopList(List<Map<String, Object>> topList, HashMap<String, Object> paramMap) {
		
		String idsStr = getSalemanIdsStr_from_topList(topList);
		HashMap<String, Object> reqMap = paramMap;
		reqMap.remove("salesmanId");
		reqMap.remove("pageNo");
		reqMap.remove("pageSize");
		if (reqMap.containsKey("dayTime")) {
			String prex = DateUtils.getPrex((reqMap.get("dayTime") + ""), 1);
			reqMap.put("dayTime", prex);
		} else if (reqMap.containsKey("dayTimeBegin") && reqMap.containsKey("dayTimeEnd")) {
			reqMap.put("dayTimeEnd", DateUtils.getPrex((reqMap.get("dayTimeBegin") + ""), 1));
			reqMap.put("dayTimeBegin", DateUtils.getZjjfMonthBeginTime((reqMap.get("dayTimeEnd") + ""), "yyyyMMdd", 1));
		}
		reqMap.put("salemanIds", idsStr);
		return salemanDailysMapper.getLastTopList(reqMap);
	}
	
	String getSalemanIdsStr_from_topList(List<Map<String, Object>> topList) {
		
		String idsStr = null;
		List<String> ids = new ArrayList<String>();
		for (Map<String, Object> map : topList) {
			ids.add(map.get("salemanId") + "");
		}
		idsStr = appendIds2String(ids);
		return idsStr;
	}
	
	private Integer getLastSeqNo(String salemanId, List<Map<String, Object>> lastTopList) {
		
		Integer lastSeqNo = 0;
		if (lastTopList != null) {
			for (Map<String, Object> map : lastTopList) {
				if ((map.get("salemanId") + "").equals(salemanId)) {
					lastSeqNo = Integer.valueOf(map.get("seqNo") + "");
					break;
				}
			}
		}
		return lastSeqNo;
	}
	
	private List<Map<String, Object>> setNearMonthData(List<Map<String, Object>> dailyList, String dayTimeBegin) {
		
		if (dailyList != null && dailyList.size() == 0) {
			for (int i = 0; i < 31; i++) {
				String tomorrow = DateUtils.getPrex(dayTimeBegin, -(i-1));
				HashMap<String, Object> rsMap = new HashMap<String, Object>();
				rsMap.put("dayTime", DateUtils.formatDate(DateUtils.str2Date(tomorrow, "yyyyMMdd"), "yyyy-MM-dd"));
				rsMap.put("turnover", 0);
				rsMap.put("zjturnover", 0);
				
				dailyList.add(rsMap);
			}
		} else if (dailyList != null && dailyList.size() != 0) {
			List<String> usedList = getDayTimeListFromDataList(dailyList);
			for (int i = 0; i < 31; i++) {
				String tomorrow = DateUtils.getPrex(dayTimeBegin, -(i-1));
				String dayTime = DateUtils.formatDate(DateUtils.str2Date(tomorrow, "yyyyMMdd"), "yyyy-MM-dd");
				if (! usedList.contains(dayTime)) {
					HashMap<String, Object> rsMap = new HashMap<String, Object>();
					rsMap.put("dayTime", dayTime);
					rsMap.put("turnover", 0);
					rsMap.put("zjturnover", 0);
					
					dailyList.add(rsMap);
				} 
			}
			return sortBydayTime(dailyList, dayTimeBegin);
		}
		
		return dailyList;
	}
	
	List<Map<String, Object>> sortBydayTime(List<Map<String, Object>> dailyList, String dayTimeBegin) {
		
		List<Map<String, Object>> sortedDailyList = new ArrayList<Map<String,Object>>();
		//对1+30条数据排序
		for (int i = 0; i < 31; i++) {
 			String tomorrow = DateUtils.getPrex(dayTimeBegin, -(i-1));
			for (Map<String, Object> map : dailyList) {
				String yyyy_MM_dd = map.get("dayTime") + "";
				String dayTime = DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(yyyy_MM_dd);
				if (tomorrow.equals(dayTime)) {
					map.remove("salemanId");
					map.remove("salemanName");
					map.remove("updateTime");
					map.remove("orderCount");
					map.remove("activeStore");
					map.remove("regStore");
					map.remove("kpiTurnover");
					map.remove("zjKpiTurnover");
					sortedDailyList.add(i, map);
					break;
				}
			}
		}
		
		return sortedDailyList;
	}
	
	private List<String> getDayTimeListFromDataList(List<Map<String, Object>> dailyList) {
		List<String> usedDayTime = new ArrayList<String>();
		if (dailyList != null && dailyList.size() != 0) {
			for (Map<String, Object> map : dailyList) {
				String used = map.get("dayTime") + "";
				usedDayTime.add(used);
			}
		}
		return usedDayTime;
	}
	
	private String getDayTimeFormat(String dayTime, String timeType) {
		if ("month".equals(timeType)) {
			String yyyyMM = DateUtils.getZjjfMonthEndTime(dayTime, "yyyyMMdd", 0).substring(0, 6);
			return DateFormatUtils.format(DateUtils.str2Date(yyyyMM, "yyyyMM"), "yyyy-MM");
		} else {
			return DateFormatUtils.format(DateUtils.str2Date(dayTime, "yyyyMMdd"), "yyyy-MM-dd");
		}
		
	}

	private String getStoresBySalemanIds(String salemanIds) {
		List<String> shopList = shopOfSalesmanMapper.getShopBySalesmanIds(salemanIds);
		return appendIds2String(shopList);
	}
	
	private String appendIds2String(List<String> salemanIds) {
		StringBuilder sb = new StringBuilder("(");
		if (salemanIds != null && salemanIds.size() != 0) {
			for (String s : salemanIds) {
				sb.append("'").append(s).append("'").append(",");
			}
			String rs = sb.substring(0, sb.length()-1).toString() + ")";
			return rs;
		}
		return "(NULL)";
	}
	
	private String setNowTime(String format) {
		Date now = new Date();
		String formatDate = DateUtils.formatDate(now, format);
		return formatDate;
	}
	
	private void changeIsZj(Map<String, Object> map) {
		
		Integer is = null;
		if (map.containsKey("isZj")) {
			try {
				is = Integer.valueOf(map.get("isZj") + "");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			if (is == 2) {
				map.put("isZj", 1);
			} else if (is == 1) {
				map.put("isZj", 0);
			}
		}
		
	}
	
}
