package com.zjjf.analysis.services.graph;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.TurnoverPage;
import com.zjjf.analysis.beans.analysis.corner.ErpPurchaseStockDetail;
import com.zjjf.analysis.beans.analysis.items.ItemBase;
import com.zjjf.analysis.beans.analysis.items.PlantItemDailyVo;
import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderDetail;
import com.zjjf.analysis.beans.analysis.store.StoreDailyVo;
import com.zjjf.analysis.beans.analysis.supplier.SpGroupDailyReport;
import com.zjjf.analysis.beans.analysis.supplier.StoreAnalysis;
import com.zjjf.analysis.beans.analysis.supplier.Supplier;
import com.zjjf.analysis.beans.erp.order.ErpManagerOrderDetailVo;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderDetailMapper;
import com.zjjf.analysis.mapper.analysis.PlantItemDailyMapper;
import com.zjjf.analysis.mapper.analysis.SpGroupDailyMapper;
import com.zjjf.analysis.mapper.analysis.SpGroupDailyReportMapper;
import com.zjjf.analysis.mapper.analysis.StoreDailyMapper;
import com.zjjf.analysis.mapper.analysis.TurnoverPageMapper;
import com.zjjf.analysis.mapper.corner.ErpManagerOrderDetailMapper;
import com.zjjf.analysis.mapper.corner.ErpPurchaseStockDetailMapper;
import com.zjjf.analysis.mapper.origin.ItemBaseMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.producer.authority.IAuthorityLevel;
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.authority.IUserService;
import com.zjjf.analysis.producer.graph.IGraphService;
import com.zjjf.analysis.utils.DateUtils;
import com.zjjf.analysis.utils.MapUtils;
import com.zjjf.analysis.utils.StringUtil;

@Service(version = "1.0.0")
public class GraphServiceImpl implements IGraphService {

	@Autowired
	private StoreDailyMapper storeDailyMapper;

	@Autowired
	private IUserService userService;

	@Autowired
	private IAuthoritydata authoritydata;

	@Autowired
	private IAuthorityLevel authorityLevel;

	@Autowired
	private TurnoverPageMapper turnoverPageMapper;

	@Autowired
	private SpGroupDailyReportMapper spGroupDailyReportMapper;

	@Autowired
	private PlantItemDailyMapper plantItemDailyMapper;

	@Autowired
	private ItemBaseMapper itemBaseMapper;

	@Autowired
	private SupplierMapper supplierMapper;

	@Autowired
	private SpGroupMapMapper spGroupMapMapper;
	
	@Autowired
	private SpGroupDailyMapper spGroupDailyMapper;

	@Autowired
	private ErpManagerOrderDetailMapper erpManagerOrderDetailMapper;
	
	@Autowired
	private ErpPurchaseStockDetailMapper erpPurchaseStockDetailMapper;
	

	@Autowired
	private AnalysisSpOrderDetailMapper analysisSpOrderDetailMapper;

	@Override
	public Map<String, Object> getGmv(HashMap<String, Object> paramMap, String userName, Integer menuId, Integer preNumDay) {

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
		String dayTime = DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(paramMap.get("dayTime") + "");
		paramMap.put("dayTimeBegin", DateUtils.getPrex(dayTime, preNumDay));
		paramMap.put("dayTimeEnd", DateUtils.getPrex(dayTime, 1));
		List<TurnoverPage> datList = turnoverPageMapper.getTurnoverGraphView(paramMap);
		List<String> xList = new ArrayList<String>();
		List<String> ydataList = new ArrayList<String>();
		List<String> zjydataList = new ArrayList<String>();

		String dayTimeBegin = paramMap.get("dayTimeBegin") + "";
		getPreNumDayDataGraph(dayTimeBegin, preNumDay, datList, xList, ydataList, zjydataList);

		resultMap.put("xdata", xList.toArray());
		resultMap.put("ydata", ydataList.toArray());
		resultMap.put("y1data", zjydataList.toArray());
		return resultMap;
	}

	// 终端维护与更新
	@Override
	public Map<String, Object> getStoreMaintain(HashMap<String, Object> paramMap, Integer preNumDay) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String dayTime = DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(paramMap.get("addTime") + "");
		// 1.得到 该时间的 终端总数ydata
		// 2.活跃总段y1data
		List<String> xList = new ArrayList<String>();
		List<String> ydataList = new ArrayList<String>();
		List<String> zjydataList = new ArrayList<String>();
		paramMap.put("dayTimeBegin", DateUtils.getPrex(dayTime, 30));
		paramMap.put("dayTimeEnd", DateUtils.getPrex(dayTime, 1));
		List<SpGroupDailyReport> dataList = spGroupDailyReportMapper.getTurnoverGraphView(paramMap);

		String dayTimeBegin = paramMap.get("dayTimeBegin") + "";
		getPreNumDayDataGraph(dayTimeBegin, preNumDay, dataList, xList, ydataList, zjydataList);

		resultMap.put("xdata", xList.toArray());
		resultMap.put("ydata", ydataList.toArray());
		resultMap.put("y1data", zjydataList.toArray());
		return resultMap;
	}

	// 活跃终端分布//1.订单次数的 活跃终端数 2.交易额
	@Override
	public Map<String, Object> getActiveStoreByOrderCountAndTurnover(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return null;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		
		String addTimeBegin = paramMap.get("addTimeBegin") + "";
		String addTimeEnd = paramMap.get("addTimeEnd") + "";
		if (StringUtil.isNotNullAndEmpty(addTimeBegin)) {
			paramMap.put("dayTimeBegin", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(addTimeBegin));
		}
		if (StringUtil.isNotNullAndEmpty(addTimeEnd)) {
			paramMap.put("dayTimeEnd", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(addTimeEnd));
		}
		
		List<StoreAnalysis> dataList = spGroupDailyMapper.getDataNoPage(this.appendParam(paramMap));
		Integer col1TurnoverTimes = 0;
		Integer col2TurnoverTimes = 0;
		Integer col3TurnoverTimes = 0;
		Integer col4TurnoverTimes = 0;
		Integer col5TurnoverTimes = 0;
		Integer col6TurnoverTimes = 0;
		Integer col1OrderTimes = 0;
		Integer col2OrderTimes = 0;
		Integer col3OrderTimes = 0;
		Integer col4OrderTimes = 0;
		for (StoreAnalysis t : dataList) {
			paramMap.put("spGroupId", t.getSpGroupId());
			List<StoreDailyVo> turnoverList = storeDailyMapper.getTurnoverDistributed(paramMap);
			
			if (turnoverList != null && turnoverList.size() > 0) {
				t.setCol1TurnoverTimes(turnoverList.get(0).getCol1TurnoverTimes());
				t.setCol2TurnoverTimes(turnoverList.get(1).getCol1TurnoverTimes());
				t.setCol3TurnoverTimes(turnoverList.get(2).getCol1TurnoverTimes());
				t.setCol4TurnoverTimes(turnoverList.get(3).getCol1TurnoverTimes());
				t.setCol5TurnoverTimes(turnoverList.get(4).getCol1TurnoverTimes());
				t.setCol6TurnoverTimes(turnoverList.get(5).getCol1TurnoverTimes());
				col1TurnoverTimes += t.getCol1TurnoverTimes();
				col2TurnoverTimes += t.getCol2TurnoverTimes();
				col3TurnoverTimes += t.getCol3TurnoverTimes();
				col4TurnoverTimes += t.getCol4TurnoverTimes();
				col5TurnoverTimes += t.getCol5TurnoverTimes();
				col6TurnoverTimes += t.getCol6TurnoverTimes();
			}
			List<StoreDailyVo> orderCountList = storeDailyMapper.getOrdercountDistributed(paramMap);
			
			if (orderCountList != null && orderCountList.size() > 0) {
				t.setCol1OrderCountTimes(orderCountList.get(0).getCol1OrderCountTimes());
				t.setCol2OrderCountTimes(orderCountList.get(1).getCol1OrderCountTimes());
				t.setCol3OrderCountTimes(orderCountList.get(2).getCol1OrderCountTimes());
				t.setCol4OrderCountTimes(orderCountList.get(3).getCol1OrderCountTimes());
				col1OrderTimes += t.getCol1OrderCountTimes();
				col2OrderTimes += t.getCol2OrderCountTimes();
				col3OrderTimes += t.getCol3OrderCountTimes();
				col4OrderTimes += t.getCol4OrderCountTimes();
			}
			
		}

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		String col1 = String.valueOf(col1OrderTimes);
		String col2 = String.valueOf(col2OrderTimes);
		String col3 = String.valueOf(col3OrderTimes);
		String col4 = String.valueOf(col4OrderTimes);
		
		String col1Turnover = String.valueOf(col1TurnoverTimes);
		String col2Turnover = String.valueOf(col2TurnoverTimes);
		String col3Turnover = String.valueOf(col3TurnoverTimes);
		String col4Turnover = String.valueOf(col4TurnoverTimes);
		String col5Turnover = String.valueOf(col5TurnoverTimes);
		String col6Turnover = String.valueOf(col6TurnoverTimes);
		
		List<String> ydataList = new ArrayList<String>();
		List<String> y1dataList = new ArrayList<String>();
		ydataList.add(col1);
		ydataList.add(col2);
		ydataList.add(col3);
		ydataList.add(col4);
		y1dataList.add(col1Turnover);
		y1dataList.add(col2Turnover);
		y1dataList.add(col3Turnover);
		y1dataList.add(col4Turnover);
		y1dataList.add(col5Turnover);
		y1dataList.add(col6Turnover);
		
		resultMap.put("ydata", ydataList.toArray());
		resultMap.put("y1data", y1dataList.toArray());
		return resultMap;
	}

	// 供应商分析
	@Override
	public Map<String, Object> getErpManagerAnalysis(HashMap<String, Object> paramMap) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String dayTimeBegin = paramMap.get("dayTimeBegin") + "";
		String dayTimeEnd = paramMap.get("dayTimeEnd") + "";
		List<ErpManagerOrderDetailVo> list = erpManagerOrderDetailMapper.getDataExcel(paramMap);
		for (ErpManagerOrderDetailVo s : list) {
			String managerId = s.getManagerId();
			Integer itemBaseId = s.getItemBaseId();
			String whId = s.getWhId();
			Supplier supplier = supplierMapper.getById(s.getSupplierId());
			if (supplier == null) {
				continue;
			}
			s.setSupplierName(supplier.getSupplierName());
			s.setAvgAreaPrice(getAvgAreaPrice(managerId, whId, itemBaseId, dayTimeBegin, dayTimeEnd));
			s.setSalePrice(getSalePrice(s.getSupplierId(), itemBaseId, dayTimeBegin, dayTimeEnd));
			s.setGrossProfits((new BigDecimal(s.getSalePrice()).subtract(s.getAvgAreaPrice())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		list.stream().sorted(Comparator.comparing(ErpManagerOrderDetailVo::getGrossProfits));
//		Map<String, Double> managerNameGroup = list.parallelStream().collect(
//				Collectors.groupingBy(ErpManagerOrderDetailVo::getManagerName, Collectors.summingDouble(ErpManagerOrderDetailVo::getGrossProfits)));
//		Map<String, Double> itemNameGroup = list.parallelStream().collect(
//				Collectors.groupingBy(ErpManagerOrderDetailVo::getItemName, Collectors.summingDouble(ErpManagerOrderDetailVo::getGrossProfits)));
//		
//        Map<String, Double> managerGroup_result = new LinkedHashMap<>();  
//        Map<String, Double> itemNameGroup_result = new LinkedHashMap<>();  
//        managerNameGroup.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).forEachOrdered(x -> managerGroup_result.put(x.getKey(), x.getValue()));
//		itemNameGroup.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).forEachOrdered(x -> itemNameGroup_result.put(x.getKey(), x.getValue()));
		List<String> xList = new ArrayList<String>();
		List<Double> ydataList = new ArrayList<Double>();
		List<String> x1List = new ArrayList<String>();
		list.stream().forEach(s -> setList(xList, x1List, ydataList, s));
		//managerGroup_result.forEach((k, v) -> setList(xList, ydataList, ));
		//itemNameGroup_result.forEach((k, v) -> setList1(k, v, x1List, y1dataList));
		resultMap.put("xList", xList);
		resultMap.put("ydataList", ydataList.subList(ydataList.size() > 20 ? ydataList.size() - 20 : 0, ydataList.size()));
		resultMap.put("x1List", x1List);
		resultMap.put("y1dataList", ydataList.subList(ydataList.size() > 20 ? ydataList.size() - 20 : 0, ydataList.size()));
		return resultMap;
	}
	
	private BigDecimal getAvgAreaPrice(String managerId, String whId, Integer itemBaseId, String dayTimeBegin, String dayTimeEnd) {
		
		BigDecimal totalMoney = new BigDecimal(0);
		Integer totalNum = 0;
		List<ErpPurchaseStockDetail> itemManagerDetailList = erpPurchaseStockDetailMapper.getByItemBaseIdTime(itemBaseId, managerId, whId, dayTimeBegin, dayTimeEnd);
		if(itemManagerDetailList != null){
			for (ErpPurchaseStockDetail info : itemManagerDetailList) {
				totalMoney = totalMoney.add(info.getTotalPrice());
				totalNum += info.getOperateStock();
			}
		}
		if(totalNum == 0){
			return new BigDecimal(0);
		}
		return totalMoney.divide(new BigDecimal(totalNum), 2, RoundingMode.HALF_UP);
	}

	private String getSalePrice(String supplierId, Integer itemBaseId, String dayTimeBegin, String dayTimeEnd) {
		
		BigDecimal totalMoney = new BigDecimal(0);
		Integer totalNum = 0;
		List<AnalysisSpOrderDetail> detaiList = analysisSpOrderDetailMapper.getBySupplierIdItemBaseId(supplierId, itemBaseId,  DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(dayTimeBegin), DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(dayTimeEnd));
		for (AnalysisSpOrderDetail detail : detaiList) {
			totalMoney = totalMoney.add(detail.getPrice().multiply(new BigDecimal(detail.getQuantity())));
			totalNum += detail.getQuantity();
		}
		if(totalNum == 0){
			return "0";
		}
		return totalMoney.divide(new BigDecimal(totalNum), 2, RoundingMode.HALF_UP) + "";
	}
	
	private void setList (List<String> xList, List<String> xList1, List<Double> ydataList, ErpManagerOrderDetailVo vo) {
		xList.add(vo.getManagerName());
		xList1.add(vo.getItemName());
		ydataList.add(vo.getGrossProfits());
	}
	
	// 商品交易分析
	@Override
	public Map<String, Object> getGoodsAna(HashMap<String, Object> paramMap, Integer top, Integer order_by_column) {

		paramMap.put("order_by_column", order_by_column == null ? 1 : order_by_column);
		paramMap.put("top", top);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// 1.交易额ydata
		// 2.订单次数y1data
		List<PlantItemDailyVo> dataGraphList = plantItemDailyMapper.getDataGraph(appendParam(paramMap));

		List<String> xdataList = new ArrayList<String>();
		List<String> ydataList = new ArrayList<String>();
		if (order_by_column == 1) {
			for (PlantItemDailyVo vo : dataGraphList) {
				ydataList.add(String.valueOf(vo.getTotalTurnover()));
				getNameByItemBaseId(vo.getItemBaseId(), vo);
				xdataList.add(String.valueOf(vo.getName()));
			}
		} else if (order_by_column == 2) {
			for (PlantItemDailyVo vo : dataGraphList) {
				ydataList.add(String.valueOf(vo.getOrderCount()));
				getNameByItemBaseId(vo.getItemBaseId(), vo);
				xdataList.add(String.valueOf(vo.getName()));
			}
		}

		resultMap.put("xdata", xdataList.toArray());
		resultMap.put("ydata", ydataList.toArray());
		return resultMap;
	}

	private void getNameByItemBaseId(Integer itemBaseId, PlantItemDailyVo vo) {
		ItemBase item = itemBaseMapper.getById(itemBaseId);
		if (item != null) {
			vo.setName(item.getName());
		}
	}

	public HashMap<String, Object> appendParam(HashMap<String, Object> map) {

		if (map.containsKey("nameOrbarCode") && map.get("nameOrbarCode") != null) {
			map.put("itemBaseIdList", itemBaseMapper.getByLikeNameOrMisde(map));
		}
		String sql = "having";
		String averageTurnoverMin = map.get("averageTurnoverMin") + "";
		String averageTurnoverMax = map.get("averageTurnoverMax") + "";
		String addTimeBegin = map.get("addTimeBegin") + "";
		String addTimeEnd = map.get("addTimeEnd") + "";
		String spGroupId = map.get("spGroupId") + "";
		if (StringUtil.isNotNullAndEmpty(averageTurnoverMin) && StringUtil.isNotNullAndEmpty(averageTurnoverMax)) {
			sql += " (sum(t1.turnover)/sum(t1.quantity)) >= " + averageTurnoverMin + " and (sum(t1.turnover)/sum(t1.quantity)) <= "
					+ averageTurnoverMax;
			map.put("averageTurnover", sql);
		} else if (StringUtil.isNotNullAndEmpty(averageTurnoverMin) && !StringUtil.isNotNullAndEmpty(averageTurnoverMax)) {
			sql += " (sum(t1.turnover)/sum(t1.quantity)) >= " + averageTurnoverMin;
			map.put("averageTurnover", sql);
		} else if (!StringUtil.isNotNullAndEmpty(averageTurnoverMin) && StringUtil.isNotNullAndEmpty(averageTurnoverMax)) {
			sql += " (sum(t1.turnover)/sum(t1.quantity)) <= " + averageTurnoverMax;
			map.put("averageTurnover", sql);
		}
		if (StringUtil.isNotNullAndEmpty(spGroupId)) {
			List<String> list = spGroupMapMapper.getBySpGroupId(Integer.valueOf(spGroupId));
			map.put("spIdList", list.isEmpty() ? null : list);
		}
		if (StringUtil.isNotNullAndEmpty(addTimeBegin)) {
			map.put("dayTimeBegin", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(addTimeBegin));
		}
		if (StringUtil.isNotNullAndEmpty(addTimeEnd)) {
			map.put("dayTimeEnd", DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(addTimeEnd));
		}
		if (map.containsKey("supplierName") && map.get("supplierName") != null) {
			List<String> supplierIdList = supplierMapper.getByName(map.get("supplierName") + "").stream().map(s -> s.getId())
					.collect(Collectors.toList());
			if (supplierIdList != null && supplierIdList.size() > 0) {
				map.put("supplierIdList", supplierIdList);
			}
		}
		return map;
	}

	private void getPreNumDayDataGraph(String dayTimeBegin, Integer preNumDay, List<?> datList, List<String> xList, List<String> ydataList,
			List<String> zjydataList) {
		for (int i = 0; i < preNumDay; i++) {
			String tomorrow = DateUtils.getPrex(dayTimeBegin, -i);
			xList.add(tomorrow);

			Boolean isHave = false;
			for (Object bean : datList) {
				if (bean instanceof TurnoverPage) {
					String addTime = ((TurnoverPage) bean).getAddTime();
					if (addTime.equals(tomorrow)) {
						ydataList.add(((TurnoverPage) bean).getTodayTurnover() + "");
						zjydataList.add(((TurnoverPage) bean).getTodayZjTurnover() + "");
						isHave = true;
						break;
					}
				} else if (bean instanceof SpGroupDailyReport) {
					String addTime = ((SpGroupDailyReport) bean).getDayTime() + "";
					if (addTime.equals(tomorrow)) {
						ydataList.add(((SpGroupDailyReport) bean).getTotalStore() + "");
						zjydataList.add(((SpGroupDailyReport) bean).getStoreOrderCount() + "");
						isHave = true;
						break;
					}
				}

			}
			if (!isHave) {
				ydataList.add("0.00");
				zjydataList.add("0.00");
			}
		}
	}
}
