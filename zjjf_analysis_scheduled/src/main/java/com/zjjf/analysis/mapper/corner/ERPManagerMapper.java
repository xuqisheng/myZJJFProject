package com.zjjf.analysis.mapper.corner;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.corner.ERPManager;
import com.zjjf.analysis.beans.corner.ERPManagerItemVo;

public interface ERPManagerMapper {
	
	List<ERPManagerItemVo> get(HashMap<String, Object> paramMap);
	//
    int deleteByPrimaryKey(String id);

    int insert(ERPManager record);

    int insertSelective(ERPManager record);

    ERPManager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ERPManager record);

    int updateByPrimaryKey(ERPManager record);
}