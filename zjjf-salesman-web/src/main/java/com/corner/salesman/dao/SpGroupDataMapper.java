package com.corner.salesman.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.salesman.model.Shop;
import com.corner.salesman.model.SpGroupData;

public interface SpGroupDataMapper {
    int deleteSpgBusAgent(String spGroupId);
    int deleteSpgLine(String spGroupId);
    int deleteSpgLineMapper(String spGroupId);
    int deleteByPrimaryKey(String spGroupId);
    int deleteSpgBusAgentByDept(String deptId);
    int deleteSpGroupByDept(String deptId);
    int deleteShopLineByShopId(String shopId);

    int insert(SpGroupData record);

    int insertSelective(SpGroupData record);

    SpGroupData selectByPrimaryKey(String spGroupId);

    int updateByPrimaryKeySelective(SpGroupData record);

    int updateByPrimaryKey(SpGroupData record);
    
    int delSpGroupData(SpGroupData record);
    
    int delSpGroupBindDbUser(SpGroupData record);
    
    int deleteSpGroupByDeptAndSpgId(Map map);
    
    int delSpGroupBindDbUserByDeptId(SpGroupData record);
    
    int deleteJunkLine();
    
    int deleteJunkShop();
    
	/**
	 * 查询定格信息
	 * @param record
	 * @return
	 */
    public List<SpGroupData> getSpGroupDataPageList(SpGroupData record);
    
	/**
	 * 查询定格信息总数
	 * @param record
	 * @return
	 */
	public int getSpGroupDataPageSize(SpGroupData record);
	
	
	public int checkSpGroupDataIsExist(String spGroupId);
	
	public int checkSpGroupIsExist(SpGroupData record);
	
	/**
	 * 根据定格ID 获取该定格业务代表（格式：李坏,元宝,十四郎）
	 * @param spGroupId
	 * @return
	 */
	public String getSpGroupDbUser(String spGroupId);
	
	
	public int insertBusAgent(SpGroupData record);
    
	public int insertSpGroupLine(SpGroupData record);
    
	public int insertLineShopMapper(SpGroupData record);
    
    /**
     * 根据定格ID查询对应的商铺信息
     * @param spGroupId
     * @return
     */
    public List<Shop> findSpgLineCustList(String spGroupId);
    
    /**
     * 根据定格ID查询定格对应路线
     * @param spGroupId
     * @return
     */
    public List<SpGroupData> findSpgLineListById(String spGroupId);
    
    /**
     * 根据部门查询定格路线列表信息
     * @param record
     * @return
     */
    public List<SpGroupData> getSpGroupBindDeptList(SpGroupData record);
    
    /**
     * 根据定格ID获取用户拼接信息
     * @param spGroupId
     * @return
     */
    public HashMap<String,String> getMergeUserInfo(String spGroupId);
    
    /**
     * 根据条件查询定格信息
     * @param record
     * @return
     */
    public SpGroupData findSpGroupDataById(SpGroupData record);
    
    /**
     * 根据部门ID 查询对应部门绑定定格数据
     * @param deptId
     * @return
     */
    public List<String> findSpGroupIdListByDeptId(String deptId);
    
    /**
     * 根据定格ID查询定格对应的路线名称
     * @param spGroupId
     * @return
     */
    public String getSpGroupLineNameBySgpId(String spGroupId);
}