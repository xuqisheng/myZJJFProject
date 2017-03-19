package com.corner.salesman.dao;

import java.util.HashMap;
import java.util.List;

import com.corner.salesman.common.persistence.CrudDao;
import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.User;

@MyBatisDao
public interface UserMapper extends CrudDao<User> {

	/**
	 * 根据手机号查询用户信息
	 * @param user
	 * @return
	 */
    public User findUserByMobile(User user);
    
	/**
	 * 根据用户ID查询用户信息
	 * @param user
	 * @return
	 */
    public User findLeaderInfoById(String userId);
    
	/**
	 * 根据用户ID查询用户信息
	 * @param user
	 * @return
	 */
    public User findUserInfoById(String userId);
    
	/**
	 * 根据手机UUID查询用户信息
	 * @param user
	 * @return
	 */
    public User findUserBydeviceUUID(User user);
    
    /**
     * 修改用户密码
     * @param user
     * @return
     */
    public int updateUserPwd(User user);
    
    /**
     * 更新用户登陆时间
     * @param userId
     * @return
     */
    public int updateLoginLastTime(User user);
    
    /**
     * 修改用户设备信息
     * @param user
     * @return
     */
    public int updateUserDeviceInfo(User user);
    
    /**
     * 获取时间频率阀值
     * @return
     */
    public String getTimeHzThresholdVal();
    
    /**
     * 根据用户ID 获取用户名称
     * @param userId
     * @return
     */
    public String getUserNameById(String userId);
    
    /**
     * 获取有效用户ID
     * @return
     */
    public List<String> getEffectiveUserIdList();
    
    /**
     * 根据用户和部门ID，获取该用户所在部门有效用户ID
     * @return
     */
    public List<String> getDeptUserIdList(User user);
    
    /**
     * 查询所有部门管理者ID
     * @param deptId
     * @return
     */
    public List<String> getLeaderIdList();
    
    /**
     * 查询指定部用户ID列表
     * @param deptId
     * @return
     */
    public List<String> getUserIdByDeptList(String deptId);
    
    /**
     * 根据部门ID获取当前部门及下一级部门的数据信息
     * @param deptId
     * @return
     */
    public List<HashMap<String,String>> getKPIDeptLevelList(String deptId);
    
    /**
     * 根据部门ID获取当前部门下的定格信息列表
     * @param deptId
     * @return
     */
    public List<HashMap<String,String>> getKPIDeptLevelSpGroupList(String deptId);
    
}