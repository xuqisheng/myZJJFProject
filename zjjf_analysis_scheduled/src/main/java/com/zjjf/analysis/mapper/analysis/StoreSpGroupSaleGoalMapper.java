package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.store.StoreSpGroupSaleGoal;

public interface StoreSpGroupSaleGoalMapper {

    int insert(StoreSpGroupSaleGoal record);

    StoreSpGroupSaleGoal selectByPrimaryKey(Integer id);
    
    List<StoreSpGroupSaleGoal> queryByMap(HashMap<String, Object> paramMap);
    
    StoreSpGroupSaleGoal getMonthGoal(@Param("spGroupId") Integer spGroupId, @Param("dayTime") String dayTime);
}