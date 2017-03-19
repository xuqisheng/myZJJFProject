package com.corner.salesman.service;

import com.corner.salesman.model.Group;

public interface GroupService {
	/**
	 * 根据领导ID 查询管理组信息
	 * @param leaderId
	 * @return
	 */
	public Group findGroupInfo(String leaderId) throws Exception;
	
	/**
	 * 根据用户组ID查询组人员总数
	 * @param groupId
	 * @return
	 */
	public int getGroupUserTotal(String groupId) throws Exception;
}
