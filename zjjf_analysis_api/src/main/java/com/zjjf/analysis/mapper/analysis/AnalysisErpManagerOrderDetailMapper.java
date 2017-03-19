package com.zjjf.analysis.mapper.analysis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.erp.AnalysisErpManagerOrderDetail;
import com.zjjf.analysis.mapper.IMapper;

public interface AnalysisErpManagerOrderDetailMapper extends IMapper<AnalysisErpManagerOrderDetail> {

	AnalysisErpManagerOrderDetail selectByPrimaryKey(Integer id);

	List<AnalysisErpManagerOrderDetail> getByManagerIdSupplierIdItemBaseId(@Param("managerId") String managerId,
			@Param("supplierId") String supplierId, @Param("itemBaseId") Integer itemBaseId, @Param("dayTimeBegin") String dayTimeBegin,
			@Param("dayTimeEnd") String dayTimeEnd);

	AnalysisErpManagerOrderDetail getMinAreaPriceByManagerIdSupplierIdItemId(@Param("managerId") String managerId,
			@Param("supplierId") String supplierId, @Param("itemBaseId") Integer itemBaseId, @Param("dayTimeBegin") String dayTimeBegin,
			@Param("dayTimeEnd") String dayTimeEnd);

}