package com.corner.rpc.salesman.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.rpc.salesman.model.LinePlans;

public interface LinePlansService {

	/**
	 * 查询线路计划数据
	 * @param linePlans
	 * @throws Exception
	 */
	public List<LinePlans> queryLinePlansList(LinePlans linePlans) throws Exception;
	
	/**
	 * 保存线路计划
	 * @param linePlans
	 * @throws Exception
	 */
	public void saveLinePlans(LinePlans linePlans) throws Exception;
	
	/**
	 * 查询客户下的路线
	 * @param linePlans
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getMyVisitLineList(LinePlans record)throws Exception;
	
	/**
	 * 查询业务员当天的拜访计划
	 * @param paramMap
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getMyTodayVisitPansList(Map<String, Object> paramMap)throws Exception;

	/**
	 * 修改拜访计划
	 * @param linePlans
	 * @throws Exception
	 */
	public void updateVisitPlans(LinePlans linePlans) throws Exception;
	
	/**
	 * 切换用户部门时候，修改原来个人路线数据中的deptId
	 * @param paramMap
	 * @throws Exception
	 */
	public void updateDeptIdByUserId(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 查询用户拜访客户路线
	 * @param userId
	 * @throws Exception
	 */
	public List<HashMap<String,Object>> getVisitLineByUserId(String userId) throws Exception;
	
	/**
	 * 根据用户ID查询用户拜访计划列表
	 * @param userId
	 * @return
	 */
	public List<HashMap<String,Object>> getMyVisitPlansList(LinePlans lineVo) throws Exception;
	
	/**
	 * 根据线路ID删除客户和线路数据
	 * @param lineId
	 * @throws Exception
	 */
	public void deleteShopLineByLineId(String lineId) throws Exception;

}
