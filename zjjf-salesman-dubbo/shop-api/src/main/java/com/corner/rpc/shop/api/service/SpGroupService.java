package com.corner.rpc.shop.api.service;

import java.util.HashMap;
import java.util.List;

import com.corner.rpc.shop.model.SpGroup;

/**
 * 店宝定格业务接口
 * 
 * @author yuanbao
 */
public interface SpGroupService {

	/**
	 * 根据区域ID查询定格列表信息
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<SpGroup> querySpGroupListByAreaId(Integer areaId) throws Exception;
	
	/**
	 * 获取全部有效的定格信息（供excel导入的时候使用）
	 */
	public List<HashMap<String, String>> getEnableSpGroupCache() throws Exception;
	
	/**
	 * 根据查询条件获取对应区域定格列表
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<SpGroup> querySpGroupList(SpGroup record) throws Exception;
}
