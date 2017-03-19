package com.zjjf.analysis.services.erp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.corner.ErpPurchaseStockDetail;
import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderDetail;
import com.zjjf.analysis.beans.erp.order.ErpManagerOrderDetailVo;
import com.zjjf.analysis.mapper.analysis.AnalysisSpOrderDetailMapper;
import com.zjjf.analysis.mapper.corner.ErpManagerOrderDetailMapper;
import com.zjjf.analysis.mapper.corner.ErpPurchaseStockDetailMapper;
import com.zjjf.analysis.producer.erp.IManagerAanlysisService;
import com.zjjf.analysis.services.AbstractModelService;
import com.zjjf.analysis.utils.DateUtils;

@Service(version = "1.0.0")
public class ManagerAanlysisServiceImpl extends AbstractModelService<ErpManagerOrderDetailVo> implements IManagerAanlysisService<ErpManagerOrderDetailVo> {

	@Autowired
	private AnalysisSpOrderDetailMapper analysisSpOrderDetailMapper;

	@Autowired
	private ErpManagerOrderDetailMapper erpManagerOrderDetailMapper;
	
	@Autowired
	private ErpPurchaseStockDetailMapper erpPurchaseStockDetailMapper;

	@Override
	public HashMap<String, Object> appendParam(HashMap<String, Object> paramMap) {

		return paramMap;
	}

	@Override
	public List<ErpManagerOrderDetailVo> getDataList(HashMap<String, Object> paramMap) {

		return erpManagerOrderDetailMapper.getData(appendParam(paramMap));
	}

	@Override
	public Integer getDataCount(HashMap<String, Object> paramMap) {

		return erpManagerOrderDetailMapper.getDataCount(appendParam(paramMap));
	}

	@Override
	public List<ErpManagerOrderDetailVo> getDataExcel(HashMap<String, Object> paramMap) {

		return erpManagerOrderDetailMapper.getDataExcel(appendParam(paramMap));
	}

	@Override
	public void adaptConvert(ErpManagerOrderDetailVo s, HashMap<String, Object> paramMap) {

		String dayTimeBegin = paramMap.get("dayTimeBegin") + "";
		String dayTimeEnd = paramMap.get("dayTimeEnd") + "";
		String managerId = s.getManagerId();
		Integer itemBaseId = s.getItemBaseId();
		String whId = s.getWhId();
		setErpManagerItemVo(s, managerId, whId, itemBaseId, dayTimeBegin, dayTimeEnd);
	}
	
	private void setErpManagerItemVo(ErpManagerOrderDetailVo s, String managerId, String whId, Integer itemBaseId, String dayTimeBegin, String dayTimeEnd){
		
		//s.setManagerId(erpManager.getId());sql里面有
		//s.setItemBaseId(itemBase.getId());sql里面有
		//s.setSupplierId(supplier.getId());sql里面有
		//s.setManagerCode(erpManager.getManagerCode());
		//s.setManagerName(erpManager.getManagerName());
		
		//s.setManagerStatus(erpManager.getStatusName());
		//s.setCooperWarehouse(supplier.getSupplierName());
		//s.setCooperation(supplier.getSupplierName());
		//s.setItemCode(itemBase.getMdseId()); sql里面有
		//s.setBarCode(itemBase.getMdseId());
		
		//s.setBoxCode(getBoxCode(itemBase));
		//s.setSpec(itemBase.getSpec());
		//s.setPkg(itemBase.getPkg());
		//s.setItemName(itemBase.getName());
		//s.setAreaPrice(getAreaPrice(s.getItemBaseId(), s.getManagerId(), s.getWhName()));
		//s.setTaxRate(); sql里面有
		
		s.setAvgAreaPrice(getAvgAreaPrice(managerId, whId, itemBaseId, dayTimeBegin, dayTimeEnd));
		s.setMinAreaPrice(getMinAreaPrice(managerId, whId, itemBaseId));
		s.setSalePrice(getSalePrice(s.getSupplierId(), s.getItemBaseId(), dayTimeBegin, dayTimeEnd));
		s.setGrossProfit((new BigDecimal(s.getSalePrice()).subtract(s.getAvgAreaPrice()) + ""));
		
		s.setGrossRate(getIncrease(new BigDecimal(s.getSalePrice()), s.getAvgAreaPrice()) + "%");
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
	
	private BigDecimal getMinAreaPrice(String managerId, String whId, Integer itemBaseId){
		
		Date now = new Date();
		String dayTimeEnd = DateFormatUtils.format(now, "yyyy-MM-dd");
		String yyyyMMddEnd = DateFormatUtils.format(now, "yyyyMMdd");
		String yyyyMMddBegin = DateUtils.getPrex(yyyyMMddEnd, 365);
		String dayTimeBegin = DateUtils.timeyyyyMMddConvertyyyy_MM_dd(yyyyMMddBegin);
		ErpPurchaseStockDetail erpPurchaseStockDetail = erpPurchaseStockDetailMapper.getMinAreaPriceByManagerIdWhIdItemBaseId(managerId, whId, itemBaseId, dayTimeBegin, dayTimeEnd);
		if(erpPurchaseStockDetail != null){
			return erpPurchaseStockDetail.getAreaPrice();
		}
		return new BigDecimal(0);
	}
	
	private static BigDecimal getIncrease(BigDecimal a, BigDecimal b) {
		
		if (BigDecimal.ZERO.equals(b) || b.doubleValue() == new BigDecimal(0.00).doubleValue()) {
			return new BigDecimal(0);
		} else {
			return a.subtract(b).multiply(new BigDecimal(100)).divide(b, 2, BigDecimal.ROUND_HALF_UP);
		}
	}
}
