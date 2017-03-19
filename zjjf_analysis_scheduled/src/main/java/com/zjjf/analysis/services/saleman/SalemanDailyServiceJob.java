package com.zjjf.analysis.services.saleman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjjf.analysis.beans.ajie.Salesman;
import com.zjjf.analysis.beans.analysis.saleman.Saleman;
import com.zjjf.analysis.beans.analysis.saleman.SalemanDaily;
import com.zjjf.analysis.mapper.ajie.SalemanMapper;
import com.zjjf.analysis.mapper.ajie.SalesmanMapper;
import com.zjjf.analysis.mapper.analysis.SalemanDailyMapper;
import com.zjjf.analysis.mapper.analysis.StoreDailyMapper;
import com.zjjf.analysis.mapper.origin.StoreMapper;
import com.zjjf.analysis.services.core.AbstractTimeService;

@Service
public class SalemanDailyServiceJob extends AbstractTimeService<Salesman, SalemanDaily> {

	private Map<String, List<String>> usedSalesmanId = new HashMap<String, List<String>>();
	
	List<String> usedIdList = new ArrayList<String>();
	
	@Autowired
	private SalemanDailyMapper salemanDailyMapper;
	
	@Autowired
	private StoreDailyMapper storeDailyMapper;
	
	@Autowired
	private SalesmanMapper salesmanMapper;
	
	@Autowired
	private SalemanMapper salemanMapper;
	
	@Autowired
	private StoreMapper storeMapper;
	
	@Override
	public List<? extends Salesman> getDataByTime(String yyyyMMdd,
			Integer offset) {
		
		//业务员-店铺 关系表 {one by one}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (offset-1)*1000);
		List<Salesman> salesmanList= salesmanMapper.getAll(map); 
		
		return salesmanList;
	}

	@Override
	public void _process(Salesman t, SalemanDaily v, String dayTime) {
		
		String salesmanId = t.getSalesmanId();
		
		if (salesmanId == null || "".equals(salesmanId)) {
			return;
		}
		
		if(usedSalesmanId.containsKey(dayTime)) {
			if (usedSalesmanId.get(dayTime).contains(salesmanId)) {
				return;
			}
		} else {
			usedIdList = new ArrayList<String>();
		}
		//统计该salesmanId的店铺list
		List<Salesman> shopOfsalesmanMapList = getAllStoreOfSalesman(salesmanId);
		if (shopOfsalesmanMapList == null) {
			return;
		}
		//记录已遍历过的salesmanId
		usedIdList.add(salesmanId);
		usedSalesmanId.put(dayTime, usedIdList);
		//0.开始根据salesmanId的shopOfsalesmanMapList对应关系 统计
		v.setSalemanId(salesmanId);
		Saleman saleman = salemanMapper.selectByPrimaryKey(salesmanId);
		if (saleman == null) {
			return;
		}
		v.setSalemanName(saleman.getUserName());
		v.setDayTime(Integer.valueOf(dayTime));
		v.setCreateTime((int)(System.currentTimeMillis() / 1000));
		//1.店宝交易额//2.自营交易额//3.订单量//4.活跃客户//6.(+)KPI交易额//7.自营KPI交易额
		//获取storeId列表
		String inStoreIds = getStoreListStrOfMan(shopOfsalesmanMapList);//('1','2')
		SalemanDaily dailyInfo = getSalemanSumStores(inStoreIds, dayTime, null);
		SalemanDaily zjDaily = getSalemanSumStores(inStoreIds, dayTime, true);
		if (dailyInfo != null) {
			v.setTurnover(dailyInfo.getTurnover());
			v.setOrderCount(dailyInfo.getOrderCount());
			v.setActiveStore(dailyInfo.getActiveStore());
			v.setZjturnover(zjDaily.getZjturnover());
			v.setKpiTurnover(dailyInfo.getKpiTurnover());
			//自营KPI交易额
			v.setZjKpiTurnover(zjDaily.getKpiTurnover());
		}
		
		//5.新增客户
		Integer regStoreCount = getRegStoreCount(inStoreIds, dayTime);
		if (regStoreCount != null) {
			v.setNewRegStore(regStoreCount);
		}
		
		List<SalemanDaily> repeatList = querySalemanDailyBymanId(salesmanId, dayTime);
		if (repeatList != null && repeatList.size() != 0) {
			salemanDailyMapper.update(v);
		} else if (repeatList != null && repeatList.size() == 0) {
			salemanDailyMapper.insert(v);
		}
	}
	
	private List<SalemanDaily> querySalemanDailyBymanId(String salemanId, String dayTime) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("salemanId", salemanId);
		paramMap.put("dayTime", dayTime);
		return salemanDailyMapper.getByMap(paramMap);
	}
	
	private String idList2String(List<Object> idList) {
		StringBuilder sb = new StringBuilder("(");
		for (Object id : idList) {
			sb.append("'").append(id).append("'").append(",");
		}
		String appendStr = sb.substring(0, sb.length()-1) + ")";
		return appendStr;
	}
	
	private String getStoreListStrOfMan(List<Salesman> shopOfsalesmanMapList) {
		List<Object> storeIdList = new ArrayList<Object>();
		for (Salesman man : shopOfsalesmanMapList) {
			Integer storeId = man.getStoreId();
			if (storeId == null) {
				continue;
			}
			storeIdList.add(storeId);
		}
		String str = "('NULL')";
		if (storeIdList.size() != 0) {
			str = idList2String(storeIdList);
		}
		return str;
	}
	
	private SalemanDaily getSalemanSumStores(String inStoreIds, String dayTime, Boolean isZj) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("storeIds", inStoreIds);
		map.put("dayTime", dayTime);
		if (isZj == null) {
		} else if (isZj) {
			map.put("isZj", 2);
		} else if (!isZj) {
			map.put("isZj", 1);
		}
		return storeDailyMapper.getSumStoresInfo(map);
	}
	
	private Integer getRegStoreCount(String inStoreIds, String dayTime) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("storeIds", inStoreIds);
		map.put("dayTime", dayTime);
		return storeMapper.getNewRegStoreByIdList(map);
	}
	
	private List<Salesman> getAllStoreOfSalesman(String salesmanId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("salesmanId", salesmanId);
		return salesmanMapper.getByMap(map);
	}
	
	public static void main(String[] args) {
		System.out.println((int)(System.currentTimeMillis() / 1000));
	}
}
