package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.origin.supplier.SpGroup;

public interface SpGroupMapper {

	List<SpGroup> selectByIndex(Integer index);

	SpGroup getById(Integer id);
	
	List<SpGroup> query(HashMap<String, Object> paramMap);
	
	List<SpGroup> getAll(@Param("dayTime") String dayTime, @Param("offset") Integer offset);

}