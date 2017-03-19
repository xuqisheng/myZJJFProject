package com.corner.rpc.salesman.dao;

import java.util.List;
import java.util.Map;

import com.corner.rpc.salesman.model.SpGroupLine;

public interface SpGroupLineMapper {
    int deleteByPrimaryKey(String spGroupId);

    int insert(SpGroupLine record);

    int insertSelective(SpGroupLine record);

    SpGroupLine selectByPrimaryKey(String spGroupId);

    int updateByPrimaryKeySelective(SpGroupLine record);

    int updateByPrimaryKey(SpGroupLine record);
    
	public int insertBusAgent(SpGroupLine record);
    
	public int insertSpGroupLine(SpGroupLine record);
    
	public int insertLineShopMapper(SpGroupLine record);
	
	/**
	 * 根据店铺ID删除店铺与路线的映射关系
	 * @param shopId
	 * @return
	 */
	public int deleteLineShopMapperByShopId(String shopId);

	/**
	 * 根据业务员ID获取对应管理路线列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,String>> getLineHashMap(String userId);
    
	/**
	 * 根据业务员ID获取对应管理定格列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,String>> getSpGroupHashMap(String userId);
    
	/**
	 * 根据业务员ID获取对应管理定格列表
	 * @param lineId
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,String>> getCustHashMap(SpGroupLine record);
    
	/**
	 * 根据业区域ID查询区域定格列表
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,String>> getSpGroupByAreaList(String areaId);
    
	/**
	 * 根据条件获取业务员管理商铺的坐标列表
	 * @param record
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,String>> getShopSiteList(SpGroupLine record);
    
	/**
	 * 根据业务员ID获取对应管理定格列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
    public List<SpGroupLine> getMySpGroupList(String userId);
    
    
	/**
	 * 根据业务员ID获取对应管理定格列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,String>> getSpgLineListById(String spGroupId);
    
    /**
     * 检查定格路线是否已经存在
     * @param record
     * @return
     */
    public int checkIsExistSpgLine(SpGroupLine record);
    
    /**
     * 检查定格路线是否已经存在
     * @param record
     * @return
     */
    public int checkIsExistSpGroup(String spGroupId);
    
    /**
     * 查询定格路线数据信息
     * @param record
     * @return
     */
    public SpGroupLine querySpGroupLine(SpGroupLine record);
    
    /**
     * 获取定格管理的业务代表
     * @param userId
     * @return
     */
    public String getSpGroupDbUserSet(String userId);
    
    /**
     * 获取业务员所属定格ID
     * @param userId
     * @return
     */
    public String getSpGroupByUserId(String userId);
    
    /**
     * 查询用户是否是DB代表（大于0表示一定为db代表）
     * @param userId
     * @return
     */
    public int queryUserIsDBType(String userId);
    
    /**
     * 获取部门及下属部门所有绑定的定格
     * @param paramMap
     * @return
     */
    public List<String> getDeptBindSpGroupList(Map<String,Object> paramMap);
    
}