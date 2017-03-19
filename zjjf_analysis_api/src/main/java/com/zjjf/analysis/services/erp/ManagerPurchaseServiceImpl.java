package com.zjjf.analysis.services.erp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.corner.ErpPurchaseStockDetail;
import com.zjjf.analysis.beans.analysis.corner.ErpPurchaseStockDetailVo;
import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderDetail;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderDetailMapper;
import com.zjjf.analysis.mapper.corner.ErpPurchaseStockDetailMapper;
import com.zjjf.analysis.producer.erp.IManagerPurchaseService;
import com.zjjf.analysis.services.AbstractModelService;
import com.zjjf.analysis.utils.DateUtils;

@Service(version = "1.0.0")
public class ManagerPurchaseServiceImpl extends AbstractModelService<ErpPurchaseStockDetailVo> implements IManagerPurchaseService<ErpPurchaseStockDetailVo> {

	@Autowired
	private ErpPurchaseStockDetailMapper erpPurchaseStockDetailMapper;

	@Autowired
	private AnalysisSpOrderDetailMapper analysisSpOrderDetailMapper;

	@Override
	public HashMap<String, Object> appendParam(HashMap<String, Object> paramMap) {

		return paramMap;
	}

	@Override
	public List<ErpPurchaseStockDetailVo> getDataList(HashMap<String, Object> paramMap) {

		return erpPurchaseStockDetailMapper.getData(appendParam(paramMap));
	}

	@Override
	public Integer getDataCount(HashMap<String, Object> paramMap) {

		return erpPurchaseStockDetailMapper.getDataCount(appendParam(paramMap));
	}

	@Override
	public List<ErpPurchaseStockDetailVo> getDataExcel(HashMap<String, Object> paramMap) {

		return erpPurchaseStockDetailMapper.getDataExcel(appendParam(paramMap));
	}
	
	@Override
	public HashMap<String, Object> getDailyMummary(HashMap<String, Object> paramMap, String userName, Integer menuId){
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = erpPurchaseStockDetailMapper.getDailyMummary(paramMap);
		
		return resultMap;
	}

	@Override
	public void adaptConvert(ErpPurchaseStockDetailVo s, HashMap<String, Object> paramMap) {

		String dayTimeBegin = paramMap.get("dayTimeBegin") + "";
		String dayTimeEnd = paramMap.get("dayTimeEnd") + "";

//		Integer itemBaseId = s.getItemBaseId();
//		List<ErpManagerOrderDetail> itemManagerDetailList = erpManagerOrderDetailMapper.getByItemIdTime(itemBaseId, dayTimeBegin, dayTimeEnd);
//		if (itemManagerDetailList == null || itemManagerDetailList.size() == 0) {
//			return;
//		}
//		ErpManagerOrderDetail erpManagerOrderDetail = erpManagerOrderDetailMapper.getById(s.getPid());
//		if(erpManagerOrderDetail == null){
//			return;
//		}
		setErpPurchaseStockDetailVo(s, dayTimeBegin, dayTimeEnd);
	}

	private void setErpPurchaseStockDetailVo(ErpPurchaseStockDetailVo s, String dayTimeBegin, String dayTimeEnd) {

		// s.setOrderId(orderId);sql里面有
		//s.setSettleStatusName(erpPurchaseStockInfo.getSettleStatusName());
		//s.setManagerName(erpManager.getManagerName());
		// s.setBarCode(barCode);
		// s.setMdseId(mdseId);

		// s.setItemCode(itemCode);
		// s.setItemName(itemName);
		// s.setSpec(spec);
		//s.setQuantity(erpManagerOrderDetail.getQuantity());
		// s.setOperateQuantity(operateQuantity);

		//s.setParentOrderId(erpManagerOrderDetail.getOrderId());
		// s.setWh1Name(wh1Name);
		// s.setWh2Name(wh2Name);
		// s.setWh3Name(wh3Name);
		// s.setAddTime(addTime);

		//s.setAddUser(erpPurchaseStockInfo.getAddUser());
		//s.setGaveTime(erpManagerOrderDetail.getGaveTime() + "");
		//s.setAreaPrice(getAreaPrice(itemManagerDetailList));
		// s.setTreasuryPrices(s.getAreaPrice());
		s.setDiscountAreaPrice(getAvgAreaPrice(s.getItemBaseId(), s.getManagerId(), s.getWhId(), dayTimeBegin, dayTimeEnd));

		// s.setTreasuryTotalPrices(treasuryTotalPrices);
		s.setAvgAreaPrice(getAvgAreaPrice(s.getItemBaseId(), s.getManagerId(), s.getWhId(), dayTimeBegin, dayTimeEnd));
		s.setSalePrice(getSalePrice(s.getSupplierId(), s.getItemBaseId(), DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(dayTimeBegin), DateUtils.timeyyyy_MM_ddConvertyyyyMMdd(dayTimeEnd)));
		s.setGrossProfit(new BigDecimal(s.getSalePrice()).subtract(new BigDecimal(s.getAvgAreaPrice())) + "");
		s.setGrossRate(getIncrease(new BigDecimal(s.getSalePrice()), new BigDecimal(s.getAvgAreaPrice())) + "%");

	}

	private String getSalePrice(String supplierId, Integer itemBaseId, String dayTimeBegin, String dayTimeEnd) {
		
		BigDecimal totalMoney = new BigDecimal(0);
		Integer totalNum = 0;
		List<AnalysisSpOrderDetail> detaiList = analysisSpOrderDetailMapper.getBySupplierIdItemBaseId(supplierId, itemBaseId, dayTimeBegin, dayTimeEnd);
		for (AnalysisSpOrderDetail detail : detaiList) {
			totalMoney = totalMoney.add(detail.getPrice().multiply(new BigDecimal(detail.getQuantity())));
			totalNum += detail.getQuantity();
		}
		if(totalNum == 0){
			return "0";
		}
		return totalMoney.divide(new BigDecimal(totalNum), 2, RoundingMode.HALF_UP) + "";
	}


	private String getAvgAreaPrice(Integer itemBaseId, String managerId, String whId, String dayTimeBegin, String dayTimeEnd){
		
		//String dayTimeBegin = "20150910";
		//String dayTimeEnd = "20161010";
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
			return "0";
		}
		return totalMoney.divide(new BigDecimal(totalNum), 2, RoundingMode.HALF_UP) + "";
	}


//	private String getAreaPrice(List<ErpManagerOrderDetail> itemManagerDetailList) {
//
//		BigDecimal totalMoney = new BigDecimal(0);
//		Integer totalNum = 0;
//		for (ErpManagerOrderDetail info : itemManagerDetailList) {
//			totalMoney = totalMoney.add(info.getAreaPrice().multiply(new BigDecimal(info.getOperateQuantity())));
//			totalNum += info.getOperateQuantity();
//		}
//		if (totalNum == 0) {
//			return "0";
//		}
//		return totalMoney.divide(new BigDecimal(totalNum), 2, RoundingMode.HALF_UP) + "";
//	}
	
//	private String getDiscountAreaPrice(Integer itemBaseId, String dayTimeBegin, String dayTimeEnd) {
//
//		BigDecimal totalMoney = new BigDecimal(0);
//		Integer totalNum = 0;
//		List<ErpManagerOrderDetail> itemManagerDetailList = erpManagerOrderDetailMapper.getByItemIdTime(itemBaseId, dayTimeBegin, dayTimeEnd);
//		if (itemManagerDetailList == null || itemManagerDetailList.size() == 0) {
//			return "0";
//		}
//		for (ErpManagerOrderDetail info : itemManagerDetailList) {
//			totalMoney = totalMoney.add(info.getAreaPrice().multiply(new BigDecimal(info.getOperateQuantity())));
//			totalNum += info.getOperateQuantity();
//		}
//		if (totalNum == 0) {
//			return "0";
//		}
//		return totalMoney.divide(new BigDecimal(totalNum), 2, RoundingMode.HALF_UP) + "";
//	}

	private static BigDecimal getIncrease(BigDecimal a, BigDecimal b) {

		if (BigDecimal.ZERO.equals(b) || b.doubleValue() == new BigDecimal(0.00).doubleValue()) {
			return new BigDecimal(0);
		} else {
			return a.subtract(b).multiply(new BigDecimal(100)).divide(b, 2, BigDecimal.ROUND_HALF_UP);
		}
	}
}
