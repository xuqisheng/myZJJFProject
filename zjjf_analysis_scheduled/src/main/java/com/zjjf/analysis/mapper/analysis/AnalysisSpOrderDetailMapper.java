package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.orders.AnalysisSpOrderDetail;
import com.zjjf.analysis.beans.origin.orders.SpOrderDetailVo;

public interface AnalysisSpOrderDetailMapper {

	int insert(AnalysisSpOrderDetail record);

	public List<AnalysisSpOrderDetail> getDetailDataByInfoId(Integer infoId);

	public void batchAdd(List<AnalysisSpOrderDetail> dataList);
	
	public AnalysisSpOrderDetail getByOrgPkId(String org_pk_id);	
	
	void updateBean(AnalysisSpOrderDetail v);
	
	List<AnalysisSpOrderDetail> getPlantItemList(HashMap<String, Object> map);
	
	SpOrderDetailVo getTurnOverByItemId(HashMap<String, Object> paramMap);
	
	void updateStatus(@Param("orderId2") String orderId2, @Param("status") Integer status);
}