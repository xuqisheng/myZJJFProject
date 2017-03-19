package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.supplier.GoalSet;

public interface GoalSetMapper {

    List<GoalSet> selectByMap(HashMap<String, Object> paramMap);

}