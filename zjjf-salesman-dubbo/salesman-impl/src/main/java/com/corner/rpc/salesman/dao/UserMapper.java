package com.corner.rpc.salesman.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.rpc.salesman.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    String queryUserIdByName(String userName);
    
    /**
     * 获取部门领导ID
     * @param deptId
     * @return
     */
    public List<String> getDeptLeaderIdList(String deptId);
    
	/**
	 * 获取用户列表信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserList(User user);
	
	/**
	 * 根据部门ID或用户ID 获取用户信息列表
	 * @param map
	 * @return
	 */
	public List<HashMap<String,Object>> getUserListHashMap(Map<String,Object> map);
	
    /**
     * 检查当前用户是否是领导(粗略校验)
     * @param record
     * @return
     */
    int checkIsLeader(String userId);
    
    /**
     * 获取日报消息提醒用户列表
     * @param map
     * @return
     */
    public List<HashMap<String,Object>> getReportWarnUserList(Map<String,Object> map);
    
    /**
     * 获取未签到提醒用户列表
     * @param map
     * @return
     */
    public List<HashMap<String,Object>> getSignWarnUserList(Map<String,Object> map);
    
    /**
     * 根据部门ID获取部门用户mobile列表信息
     * @param deptId
     * @return
     */
    public List<String> getUserMobileList(String deptId);
    
}