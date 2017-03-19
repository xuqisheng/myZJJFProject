package com.zjjf.analysis.mapper.corner;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.corner.ErpPurchaseStockDetail;
import com.zjjf.analysis.beans.analysis.corner.ErpPurchaseStockDetailVo;
import com.zjjf.analysis.mapper.IMapper;

public interface ErpPurchaseStockDetailMapper extends IMapper<ErpPurchaseStockDetailVo> {

	HashMap<String, Object> getDailyMummary(HashMap<String, Object> map);

	List<ErpPurchaseStockDetail> getByItemBaseIdTime(@Param("itemBaseId") Integer itemBaseId, @Param("managerId") String managerId,
			@Param("whId") String whId, @Param("dayTimeBegin") String dayTimeBegin, @Param("dayTimeEnd") String dayTimeEnd);

	ErpPurchaseStockDetail getMinAreaPriceByManagerIdWhIdItemBaseId(@Param("managerId") String managerId, @Param("whId") String whId,
			@Param("itemBaseId") Integer itemBaseId, @Param("dayTimeBegin") String dayTimeBegin, @Param("dayTimeEnd") String dayTimeEnd);
}