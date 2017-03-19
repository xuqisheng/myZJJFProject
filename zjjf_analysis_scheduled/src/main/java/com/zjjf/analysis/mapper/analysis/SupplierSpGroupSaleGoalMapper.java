package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.zjjf.analysis.beans.analysis.supplier.SupplierSpGroupSaleGoal;

public interface SupplierSpGroupSaleGoalMapper {

	int insert(SupplierSpGroupSaleGoal record);

	List<SupplierSpGroupSaleGoal> getBySpIdAndSpGroupIdAndDayTime(@Param("supplierId") String supplierId, @Param("spGroupId") Integer spGroupId,
			@Param("dayTime") Integer dayTime);

	int updateById(HashMap<String, Object> hashMap);

	SupplierSpGroupSaleGoal getGoal(@Param("spGroupId") Integer spGroupId, @Param("supplierId") String supplierId, @Param("dayTime") String dayTime);
}