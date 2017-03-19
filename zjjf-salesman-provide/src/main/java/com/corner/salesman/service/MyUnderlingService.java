package com.corner.salesman.service;

import java.util.List;

import com.corner.salesman.model.MyUnderling;
import com.corner.salesman.model.User;

/**
 * @描述： 我的下属业务层接口
 * @author Longx
 * @创建时间  2016-01-26
 */
public interface MyUnderlingService {

	/**
	 * 根据用户ID获取下属信息（包括当前部门人员级下属部门列表）
	 * @param myUnderling
	 * @return
	 * @throws Exception
	 */
	public MyUnderling findMyUnderlingList(MyUnderling underling) throws Exception;
	
	/**
	 * 根据用户ID查询自己部门下的用户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<User> getDeptUserList(String userId) throws Exception;
	
}
