package com.zjjf.analysis.services.corner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.corner.ErpPurchaseStockDetail;
import com.zjjf.analysis.beans.analysis.corner.ErpPurchaseStockInfo;
import com.zjjf.analysis.beans.analysis.items.ErpManagerItemDaily;
import com.zjjf.analysis.beans.analysis.items.PlantItemDaily;
import com.zjjf.analysis.mapper.analysis.ErpManagerItemDailyMapper;
import com.zjjf.analysis.mapper.analysis.PlantItemDailyMapper;
import com.zjjf.analysis.mapper.corner.ErpPurchaseStockDetailMapper;
import com.zjjf.analysis.mapper.corner.ErpPurchaseStockInfoMapper;
import com.zjjf.analysis.producer.authority.IAuthorityKeyArray;
import com.zjjf.analysis.producer.corner.ManagerAnaService;
import com.zjjf.analysis.services.AbstractBaseService;
import com.zjjf.analysis.utils.DateUtils;

@Service(version = "1.0.0")
public class ManagerAnaServiceImpl extends AbstractBaseService<ErpManagerItemDaily> implements ManagerAnaService {

	@Autowired
	private ErpManagerItemDailyMapper erpManagerItemDailyMapper;

	@Autowired
	private ErpPurchaseStockDetailMapper erpPurchaseStockDetailMapper;

	@Autowired
	private ErpPurchaseStockInfoMapper erpPurchaseStockInfoMapper;

	@Autowired
	private PlantItemDailyMapper plantItemDailyMapper;

	@Autowired
	private IAuthorityKeyArray authorityKeyArray;

	private List<ErpManagerItemDaily> getErpManagerAnalysis(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		// paramMap：页面的查询条件
		filterParamMap();
		List<ErpManagerItemDaily> itemList = erpManagerItemDailyMapper.getByMap(paramMap);

		if (itemList != null && itemList.size() != 0) {
			// *更新表的符合条件的记录的下列字段
			for (ErpManagerItemDaily item : itemList) {
				// String pid = item.getPid();
				String orderId = item.getOrderId();
				Integer itemBaseId = item.getItemBaseId();

				List<ErpPurchaseStockInfo> stockInfoList = getStockOrderByPOrderId(orderId);
				if (stockInfoList != null && stockInfoList.size() != 0) {
					BigDecimal avgAreaPrice = getAvgAreaPrice(stockInfoList, itemBaseId);
					BigDecimal discountAreaPrice = avgAreaPrice;
					HashMap<String, Object> updateParaMap = new HashMap<String, Object>();
					// 平均采购价
					updateParaMap.put("avgAreaPrice", avgAreaPrice);
					// 折实采购价
					updateParaMap.put("discountAreaPrice", discountAreaPrice);

					// *历史最低
					String today = DateUtils.formatDate(new Date(), "yyyyMMdd");
					ErpPurchaseStockDetail min = getMinAreaPrice(today, itemBaseId);
					if (min == null) {
						updateParaMap.put("minAreaPrice", 0);
					} else {
						updateParaMap.put("minAreaPrice", min.getAreaPrice());
					}

					item.setAvgAreaPrice(avgAreaPrice);
					item.setDiscountAreaPrice(discountAreaPrice);
					item.setMinAreaPrice(min.getAreaPrice());

					// 平均售价
					BigDecimal salePrice = getAvgSalePrice(itemBaseId, paramMap);
					item.setSalePrice(salePrice);

					// 商品毛利= 平均售价 - 平均采购
					// 毛利率= 毛利/平均售价
					item.setGrossProfit(item.getSalePrice().subtract(item.getAvgAreaPrice()));
					item.setGrossRate(item.getGrossProfit().divide(item.getSalePrice(), 5, RoundingMode.HALF_UP));

					erpManagerItemDailyMapper.update(item);

					return itemList;
				} else {
					continue;
				}
			}
		}
		return null;
	}

	@Override
	public List<Object[]> getList(String userName, Integer menuId, HashMap<String, Object> paramMap) {
		getErpManagerAnalysis(userName, menuId, paramMap);
		Object[][] keyArray = authorityKeyArray.getAuthorityKeyArray(userName, menuId);
		authorityKeyArray.getAuthorityCityOrArea(paramMap, userName, menuId);
		Object[] titleEn = this.getOrderTitleEn(keyArray);
		return erpManagerItemDailyMapper.getByMap(paramMap).stream().map(s -> sort_by_viewTitle(s, titleEn)).collect(Collectors.toList());
	}

	@Override
	public Integer getCount(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		return erpManagerItemDailyMapper.getCount(paramMap);
	}

	private void filterParamMap() {

	}

	private List<ErpPurchaseStockInfo> getStockOrderByPOrderId(String pOrderId) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("pOrderId", pOrderId);
		return erpPurchaseStockInfoMapper.getByMap(param);
	}

	private BigDecimal getAvgAreaPrice(List<ErpPurchaseStockInfo> stockInfoList, Integer itemBaseId) {
		BigDecimal totalPrice = new BigDecimal(0);
		Integer totalQuantity = 0;
		for (ErpPurchaseStockInfo stockInfo : stockInfoList) {
			HashMap<String, Object> itemBaseMap = new HashMap<String, Object>();
			itemBaseMap.put("orderId", stockInfo.getOrderId());
			itemBaseMap.put("itemBaseId", itemBaseId);
			List<ErpPurchaseStockDetail> stockDetailList = erpPurchaseStockDetailMapper.getByMap(itemBaseMap);
			// [A,$1][A,$2]
			for (ErpPurchaseStockDetail stock : stockDetailList) {
				Short operateQuantity = stock.getOperateQuantity();
				totalPrice = totalPrice.add(stock.getAreaPrice().multiply(BigDecimal.valueOf(operateQuantity)));
				totalQuantity = totalQuantity + operateQuantity;
			}
		}
		BigDecimal avgAreaPrice = totalPrice.divide(new BigDecimal(totalQuantity), 5, RoundingMode.HALF_UP);
		return avgAreaPrice;
	}

	private ErpPurchaseStockDetail getMinAreaPrice(String today, Integer itemBaseId) {
		HashMap<String, Object> minPriceParaMap = new HashMap<String, Object>();
		minPriceParaMap.put("itemBaseId", itemBaseId);
		minPriceParaMap.put("addTimeBegin", DateUtils.getPrex(today, 365));
		minPriceParaMap.put("addTimeEnd", today);
		ErpPurchaseStockDetail min = erpPurchaseStockDetailMapper.getMinAreaPrice(minPriceParaMap);
		return min;
	}

	private BigDecimal getAvgSalePrice(Integer itemBaseId, HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> plantParaMap = new HashMap<String, Object>();
		BigDecimal salePrice = new BigDecimal(0);
		plantParaMap.put("itemBaseId", itemBaseId);
		plantParaMap.put("dayTimeBegin", paramMap.get(""));
		plantParaMap.put("dayTimeEnd", paramMap.get(""));
		List<PlantItemDaily> plantItemDailyList = plantItemDailyMapper.getByMap(plantParaMap);

		if (plantItemDailyList != null && plantItemDailyList.size() != 0) {
			BigDecimal turnover = new BigDecimal(0);
			Integer quantity = 0;
			for (PlantItemDaily plantItemDaily : plantItemDailyList) {
				turnover = turnover.add(plantItemDaily.getTurnover());
				quantity += plantItemDaily.getQuantity();
			}
			salePrice = turnover.divide(new BigDecimal(quantity), 5, RoundingMode.HALF_UP);
		}
		return salePrice;
	}

	public static void main(String[] args) {
		String today = DateUtils.formatDate(new Date(), "yyyyMMdd");
		System.out.println(today);
		String prex = DateUtils.getPrex(today, 365);
		System.out.println(prex);
		BigDecimal a = new BigDecimal(130);
		BigDecimal b = a.divide(new BigDecimal(3), 5, RoundingMode.HALF_UP);
		System.out.println(b);

	}
}
