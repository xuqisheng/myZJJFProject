package com.corner.rpc.salesman.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.rpc.salesman.model.User;
import com.corner.salesman.commons.persistence.Page;


/**
 * 用户业务逻辑层接口
 * @author Administrator
 *
 */
public interface UserService {
	
	/**
	 * 根据用户ID获取用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User getUserById(String userId) throws Exception;
	
    /**
     * 获取部门领导ID
     * @param deptId
     * @return
     */
    public List<String> getDeptLeaderIdList(String deptId) throws Exception;

	/**
	 * 获取用户列表信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserList(User user) throws Exception;
	
    /**
     * 查询用户列表(分页方法)
     * @param user
     * @return
     */
	public Page<User> getUserList(Page<User> page, User user) throws Exception;
	
    /**
     * 获取日报消息提醒用户列表
     * @param map
     * @return
     */
    public List<HashMap<String,Object>> getReportWarnUserList(Map<String,Object> map) throws Exception;
    
    /**
     * 获取未签到提醒用户列表
     * @param map
     * @return
     */
    public List<HashMap<String,Object>> getSignWarnUserList(Map<String,Object> map) throws Exception;
    
    /**
     * 根据部门ID获取部门用户mobile列表信息
     * @param deptId
     * @return
     */
    public List<String> getUserMobileList(String deptId) throws Exception;
	
}
