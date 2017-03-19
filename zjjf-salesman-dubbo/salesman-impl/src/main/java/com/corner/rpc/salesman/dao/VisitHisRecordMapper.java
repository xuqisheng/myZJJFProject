package com.corner.rpc.salesman.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.rpc.salesman.model.VisitHisRecord;

public interface VisitHisRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VisitHisRecord record);

    int insertSelective(VisitHisRecord record);

    VisitHisRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VisitHisRecord record);

    int updateByPrimaryKey(VisitHisRecord record);
    
    int insertVisitHisRecord(Map<String,Object> paraMap);
    
    /**
     * 根据店铺shopNo检查是否存在
     * @param shopNo
     * @return
     */
    int checkShopNoIsExist(String shopNo);
    
    /**
     * 根据店铺shopNo检查是否存在
     * @param shopNo
     * @return
     */
    int checkTodayLineIsExist(String lineId);
    
    /**
     * 根据店铺shopNo检查对应店铺是否签过离店到（大于0表示已经签过离店）
     * @param shopNo
     * @return
     */
    int checkShopVisitStatus(String shopNo);
    
    /**
     * 根据线路ID修改已拜访总数记录
     * @param lineId
     * @return
     */
    int updateVisitHisRecord(String lineId);
    
    /**
     * 根据查询日期获取每一天的拜访结果列表
     * @param paraMap
     * @return
     */
    List<HashMap<String,Object>> getTodayVisitPlansList(VisitHisRecord record);
    
    /**
     * 根据查询日期获取每一天的拜访结果列表
     * @param paraMap
     * @return
     */
    List<HashMap<String,Object>> getHistoryVisitPlansList(VisitHisRecord record);
}