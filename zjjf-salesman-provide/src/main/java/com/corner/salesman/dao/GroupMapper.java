package com.corner.salesman.dao;

import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.Group;

@MyBatisDao
public interface GroupMapper {

	/**
	 * 根据领导ID 查询管理组信息
	 * @param leaderId
	 * @return
	 */
	public Group findGroupInfo(String leaderId);
	
	/**
	 * 根据用户组ID查询组人员总数
	 * @param groupId
	 * @return
	 */
	public int getGroupUserTotal(String groupId);
}
