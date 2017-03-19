package com.zjjf.analysis.mapper.origin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.origin.supplier.SpGroupMap;

public interface SpGroupMapMapper {

	List<SpGroupMap> selectByIndex(Integer index);

	SpGroupMap getByGroupId(Integer groupId);

	List<SpGroupMap> getBySupplierId(String supplierId);
	
	SpGroupMap getBySpIdAndGroupId(@Param("supplierId") String supplierId, @Param("groupId") Integer groupId);
	
	List<SpGroupMap> getAll(@Param("dayTime") String dayTime, @Param("offset") Integer offset);
	
}