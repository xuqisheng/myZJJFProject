package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.store.GoalSet;

public interface GoalSetMapper {

    int insert(HashMap<String, Object> paramMap);

    List<GoalSet> getList(HashMap<String, Object> paramMap);
    
    List<HashMap<String, Object>> getByMap(HashMap<String, Object> paramMap);
    
    Integer getCount(HashMap<String, Object> paramMap);

}