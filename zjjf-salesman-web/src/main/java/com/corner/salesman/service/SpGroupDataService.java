package com.corner.salesman.service;

import java.util.List;

import com.corner.core.beans.vo.Pager;
import com.corner.salesman.model.SpGroupData;

/**
 * 定格与路线数据接口
 * @author Administrator
 *
 */
public interface SpGroupDataService {
	
    
	/**
	 * 查询定格分页方法
	 * @param spGroupData
	 * @return
	 * @throws Exception
	 */
	public Pager<SpGroupData> getSpGroupDataPageList(SpGroupData spGroupData) throws Exception;
	
	/**
	 * 添加定格信息方法
	 * @param spGroupData
	 * @return
	 * @throws Exception
	 */
	public int addSpGroupData(SpGroupData spGroupData) throws Exception;
	
	/**
	 * 修改定格信息方法
	 * @param spGroupData
	 * @return
	 * @throws Exception
	 */
	public int updateSpGroupData(SpGroupData spGroupData) throws Exception;
	
	/**
	 * 删除定格信息方法
	 * @param spGroupData
	 * @return
	 * @throws Exception
	 */
	public int deleteSpGroupData(SpGroupData spGroupData) throws Exception;
	
	/**
	 * 根据ID查询定格信息
	 * @param spGroupData
	 * @return
	 * @throws Exception
	 */
	public SpGroupData findSpGroupDataById(SpGroupData spGroupData) throws Exception;
	
	/**
	 * 检查新增定格ID是否已经存在
	 * @param spGroupId
	 * @return
	 */
	public int checkSpGroupDataIsExist(String spGroupId) throws Exception;
	
	public int checkSpGroupIsExist(SpGroupData record) throws Exception;
	
	/**
	 * 检查对应的定格是否已经存在
	 */
	public List<SpGroupData> getSpgLineData(String spGroupId) throws Exception;
	
    /**
     * 根据部门查询定格路线列表信息
     * @param deptId
     * @return
     */
    public List<SpGroupData> getSpGroupBindDeptList(SpGroupData spGroupData) throws Exception;
    
    /**
     * 保存同步的定格数据
     * @throws Exception
     */
    public void saveSyncSpGroupData(SpGroupData spGroupData) throws Exception;
}
