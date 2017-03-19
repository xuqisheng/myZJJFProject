package com.corner.rpc.salesman.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.rpc.salesman.model.LinePlans;

public interface LinePlansMapper {
    int deleteByPrimaryKey(String lineId);

    int insert(LinePlans record);

    int insertSelective(LinePlans record);

    LinePlans selectByPrimaryKey(String lineId);

    int updateByPrimaryKeySelective(LinePlans record);

    int updateByPrimaryKey(LinePlans record);
    
    int deleteShopLineByShopNo(String shopNo);
    
    int deleteShopLineMapperByLineId(String lineId);
    
    int deleteShopLineByLineId(Map<String,Object> paraMap);
    
    int deleteLinePlans(Map<String,Object> paraMap);
    
    int insertLineShopMapper(LinePlans record);
    
    int deleteShopLineMapperByShopNos(Map<String,Object> paramMap);
    
    int updateDeptIdByUserId(Map<String,Object> paramMap);
    
    /**
     * 根据条件删除周计划映射关系
     * @param record
     * @return
     */
    int deleteWeekPlansMapperById(LinePlans record);
    
    /**
     * 新增周计划映射关系
     * @param record
     * @return
     */
    int insertWeekPlansMapper(LinePlans record);
    
    List<HashMap<String, Object>> getMyVisitLineList(String userId);
    
    List<HashMap<String, Object>> getMyVisitShopList(Map<String,Object> paraMap);
    
    List<HashMap<String, Object>> getShopBySalesmanIdList(String salesmanId);
    
	/**
	 * 查询用户拜访客户路线
	 * @param userId
	 * @throws Exception
	 */
	public List<HashMap<String,Object>> getVisitLineByUserId(String userId);
    
	/**
	 * 查询线路计划数据
	 * @param linePlans
	 * @throws Exceptionss
	 */
	public List<LinePlans> queryLinePlansList(LinePlans linePlans);
	
	/**
	 * 根据用户ID查询用户拜访计划列表
	 * @param userId
	 * @return
	 */
	public List<HashMap<String,Object>> getMyVisitPlansList(Map<String,Object> paraMap);
	
}