package com.corner.rpc.salesman.api.service;

import java.util.HashMap;
import java.util.List;

import com.corner.rpc.salesman.model.VisitHisRecord;
import com.corner.salesman.commons.persistence.Page;

public interface VisitHisRecordService {

	/**
	 * 通过线路ID copy线路数据insert到历史记录表中
	 * @param lineId
	 * @throws Exception
	 */
    public void insertVisitHisRecord(String lineId) throws Exception;
    
    /**
     * 根据店铺shopNo检查是否存在
     * @param shopNo
     * @return
     */
    public int checkTodayShopNoIsExist(String shopNo) throws Exception;
    
	
    /**
     * 检查当天线路ID是否存在
     * @param lineId
     * @return
     */
	public int checkTodayLineIsExist(String lineId) throws Exception;
    
    /**
     * 根据店铺shopNo检查对应店铺是否签过离店到（大于0表示已经签过离店）
     * @param shopNo
     * @return
     */
    public int checkShopVisitStatus(String shopNo) throws Exception;
    
    /**
     * 根据线路ID修改已拜访总数记录
     * @param lineId
     * @return
     */
    public void updateVisitHisRecord(String lineId) throws Exception;
    
    /**
     * 获取当天的拜访结果列表
     * @param record
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getTodayVisitPlansList(VisitHisRecord record) throws Exception;
    
    /**
     * 根据查询日期获取每一天的拜访结果列表(分页方法)
     * @param page
     * @param record
     * @return
     * @throws Exception
     */
    public Page<VisitHisRecord> getTodayVisitPlansList(Page<VisitHisRecord> page, VisitHisRecord record) throws Exception;
}
