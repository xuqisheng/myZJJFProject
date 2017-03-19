package com.zjjf.analysis.producer.authority;

import java.util.HashMap;
import java.util.List;

public interface IAuthoritydata {

	public Object[][] getAuthorityFilter(String userId, Integer menuId);

	public Object[] getOrderTitleCn(String userName, Integer menuId);

	/**
	 * 根据menuId获取配置的数据key
	 * 
	 * @param menuId
	 * @return
	 */
	public List<HashMap<String, Object>> getAllAuthorityKeysByMenuId(Integer menuId);

	/**
	 * 获取已经配置过的key
	 * 
	 * @param menuId
	 * @param roleId
	 * @return
	 */
	public List<Integer> getSelectKey(Integer menuId, Integer roleId);

	public List<HashMap<String, Object>> getParentTitle(Integer menuId, Integer baseRoleId);
}
