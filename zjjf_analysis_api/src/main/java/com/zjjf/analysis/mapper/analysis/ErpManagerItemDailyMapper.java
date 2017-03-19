package com.zjjf.analysis.mapper.analysis;

import com.zjjf.analysis.beans.analysis.items.ErpManagerItemDaily;

import java.util.HashMap;
import java.util.List;

public interface ErpManagerItemDailyMapper {

    int insert(ErpManagerItemDaily record);
    
    List<ErpManagerItemDaily> getByMap(HashMap<String, Object> paramMap);
    
    Integer getCount(HashMap<String, Object> paramMap);
    
    void update(ErpManagerItemDaily item);
    

}