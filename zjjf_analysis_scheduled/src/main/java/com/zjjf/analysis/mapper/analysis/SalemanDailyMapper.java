package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.saleman.SalemanDaily;


public interface SalemanDailyMapper {

    int insert(SalemanDaily record);

    SalemanDaily selectByPrimaryKey(Integer id);
    
    List<SalemanDaily> getByMap(HashMap<String, Object> paramMap);
    
    void update(SalemanDaily record);

}