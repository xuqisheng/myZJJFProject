package com.corner.rpc.salesman.api.service;

import java.util.List;
import java.util.Map;

import com.corner.rpc.salesman.model.SpGroupLine;

public interface SpGroupLineService {

	/**
	 * 根据业务员ID获取对应管理路线
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getLineHashMap(String userId) throws Exception;
	
	/**
	 * 根据业务员ID获取对应管理定格列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
    public List<SpGroupLine> getMySpGroupList(String userId) throws Exception;
    
	/**
	 * 根据区域ID查询区域定格列表
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,String>> getSpGroupByAreaList(String areaId) throws Exception;
    
	/**
	 * 根据定格ID获取线路列表
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,String>> getLineBySpGroupList(String spGroupId) throws Exception;
    
	/**
	 * 根据线路ID获取对应线路客户列表
	 * @param record
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,String>> getCustHashMap(SpGroupLine record) throws Exception;
    
	/**
	 * 根据条件获取业务员管理商铺的坐标列表
	 * @param record
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,String>> getShopSiteList(SpGroupLine record) throws Exception;
    
    /**
     * 获取定格管理的业务代表
     * @param userId
     * @return
     */
    public String getSpGroupDbUserSet(String userId) throws Exception;
    
    /**
     * 获取业务员所属定格ID
     * @param userId
     * @return
     */
    public String getSpGroupByUserId(String userId) throws Exception;
    
    /**
     * 查询用户是否是DB代表（大于0表示一定为db代表）
     * @param userId
     * @return
     */
    public int queryUserIsDBType(String userId) throws Exception;
    
    /**
     * 获取部门及下属部门所有绑定的定格
     * @param paramMap
     * @return
     */
    public List<String> getDeptBindSpGroupList(Map<String,Object> paramMap) throws Exception;
}
