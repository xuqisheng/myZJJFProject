package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;

import com.zjjf.analysis.beans.analysis.AnaProperty;

public interface AnaPropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnaProperty record);

    int insertSelective(AnaProperty record);

    AnaProperty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnaProperty record);

    int updateByPrimaryKey(AnaProperty record);
    
    /**
     * 
     * @param anaKey
     * @return
     */
    AnaProperty selectByAnaKey(String anaKey);
    
    
    void updateAnaValueByAnaKey(HashMap<String, String> paramMap);
}