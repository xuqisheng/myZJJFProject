package com.zjjf.analysis.mapper.corner;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.corner.ErpManager;
import com.zjjf.analysis.beans.analysis.corner.ManagerItemVo;

public interface ErpManagerMapper {
	
	List<ManagerItemVo> getByMap(Map<String,Object> paramMap);
	
	//==============
	ErpManager getById(@Param("id") String id);

    int insert(ErpManager record);

    int insertSelective(ErpManager record);

    ErpManager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ErpManager record);

    int updateByPrimaryKey(ErpManager record);
}