package com.zjjf.analysis.services.corner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.corner.ErpPurchaseStockDetail;
import com.zjjf.analysis.beans.analysis.corner.ErpPurchaseStockInfo;
import com.zjjf.analysis.beans.analysis.items.PlantItemDaily;
import com.zjjf.analysis.beans.analysis.orders.ErpPurchaseDaily;
import com.zjjf.analysis.mapper.analysis.ErpPurchaseDailyMapper;
import com.zjjf.analysis.mapper.analysis.PlantItemDailyMapper;
import com.zjjf.analysis.mapper.corner.ErpPurchaseStockDetailMapper;
import com.zjjf.analysis.mapper.corner.ErpPurchaseStockInfoMapper;
import com.zjjf.analysis.producer.corner.IPurchaseStockAnaService;
import com.zjjf.analysis.utils.DateUtils;

@Service(version="1.0.0")
public class PurchaseStockAnaServiceImpl implements IPurchaseStockAnaService{

	@Autowired
	private ErpPurchaseDailyMapper erpPurchaseDailyMapper;
	
	@Autowired
	private ErpPurchaseStockInfoMapper erpPurchaseStockInfoMapper;
	
	@Autowired
	private ErpPurchaseStockDetailMapper erpPurchaseStockDetailMapper;
	
	@Autowired
	private PlantItemDailyMapper plantItemDailyMapper;
	
	@Override
	public List<ErpPurchaseDaily> getErpPurchaseReportAna(
			HashMap<String, Object> paramMap) {
		
		List<ErpPurchaseDaily> purchaseDailyList = erpPurchaseDailyMapper.getByMap(paramMap);
		if (purchaseDailyList != null && purchaseDailyList.size() != 0) {
			for (ErpPurchaseDaily purchaseDaily : purchaseDailyList) {
				Integer itemBaseId = purchaseDaily.getItemBaseId();
				//*历史最低
				String today = DateUtils.formatDate(new Date(), "yyyyMMdd");
				ErpPurchaseStockDetail min = getMinAreaPrice(today, itemBaseId);
				if (min == null) {
					purchaseDaily.setMinItemPrice(new BigDecimal(0));
				} else {
					purchaseDaily.setMinItemPrice(min.getAreaPrice());
				}
				
				//*平均售价
				BigDecimal avgSalePrice = getAvgSalePrice(itemBaseId, paramMap);
				//*平均采购价
				BigDecimal avgAreaPrice = new BigDecimal(0);
				List<ErpPurchaseStockInfo> stockInfoList = getStockOrderByPOrderId(purchaseDaily.getPurchaseId());;
				if (stockInfoList != null && stockInfoList.size() != 0) {
					avgAreaPrice = getAvgAreaPrice(stockInfoList, itemBaseId);
				}
				//*毛利
				BigDecimal profit = avgSalePrice.subtract(avgAreaPrice);
				//*毛利率
				BigDecimal profitRate = profit.divide(avgSalePrice);
				
				purchaseDaily.setAvgSlaePrice(avgSalePrice);
				purchaseDaily.setProfit(profit);
				purchaseDaily.setProfitRate(profitRate);
				
				erpPurchaseDailyMapper.update(purchaseDaily);
			}
		}
		return null;
	}
	
	
	private List<ErpPurchaseStockInfo> getStockOrderByPOrderId(String pOrderId) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("pOrderId", pOrderId);
		return erpPurchaseStockInfoMapper.getByMap(param);
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
			salePrice = turnover.divide(new BigDecimal(quantity));
		}
		return salePrice;
	}
	
	private BigDecimal getAvgAreaPrice(List<ErpPurchaseStockInfo> stockInfoList, Integer itemBaseId) {
		BigDecimal totalPrice = new BigDecimal(0);
		Integer totalQuantity = 0;
		for (ErpPurchaseStockInfo stockInfo : stockInfoList) {
			HashMap<String, Object> itemBaseMap = new HashMap<String, Object>();
			itemBaseMap.put("orderId", stockInfo.getOrderId());
			itemBaseMap.put("itemBaseId", itemBaseId);
			List<ErpPurchaseStockDetail> stockDetailList = erpPurchaseStockDetailMapper.getByMap(itemBaseMap);
			//[A,$1][A,$2]
			for (ErpPurchaseStockDetail stock : stockDetailList) {
				Short operateQuantity = stock.getOperateQuantity();
				totalPrice = totalPrice.add(stock.getAreaPrice().multiply(BigDecimal.valueOf(operateQuantity)));
				totalQuantity = totalQuantity + operateQuantity;
			}
		}
		BigDecimal avgAreaPrice = totalPrice.divide(new BigDecimal(totalQuantity));
		return avgAreaPrice;
	}
}
