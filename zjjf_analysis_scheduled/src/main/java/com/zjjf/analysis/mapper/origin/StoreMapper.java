package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.origin.store.Store;

public interface StoreMapper {

    List<Store> selectByIndex(Integer index);
    
    Store getById(Integer id);
    
    Store getByStoreId(Integer id);
    
    List<Store> getAllStore(HashMap<String, Object> paramMap);
    
    List<Store> getBySpGroupId(Integer spGroupId);
    
    Integer getBySpGroupIdAndTime(@Param("spGroupId") Integer spGroupId, @Param("dayTime") String dayTime);
    
    Integer getBySpGroupIdAndMonthTime(@Param("spGroupId") Integer spGroupId, @Param("dayTimeEnd") String dayTime);
    
    Integer getBySpGroupIdMonth(@Param("spGroupId") Integer spGroupId, @Param("dayTimeBegin") String dayTimeBegin, @Param("dayTimeEnd") String dayTimeEnd);
    
    List<Integer> getBySpGroupIdBeforeTime(HashMap<String, Object> paramMap);
    
    Integer getNewRegStoreByIdList(HashMap<String, Object> paramMap);
    
}