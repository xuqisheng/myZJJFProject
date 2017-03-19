package com.corner.salesman.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.corner.salesman.common.utils.Json;
import com.corner.salesman.model.User;

/**  
 * @desc  用户业务层接口
 * 创建时间：2015-1-27 下午5:15:03  
 * @author 元宝  
 * @version 2.2  
 */

public interface UserService {
	
	/**
	 * 用户登陆方法
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Json Login(User user,HttpServletRequest request) throws Exception;
	
    /**
     * 根据用户ID检查是否是部门领导（大约0表示为领导）
     * @param user
     * @return
     */
    public int checkIsLeader(String userId) throws Exception;
    
    /**
     * 修改用户密码
     * @param user
     * @return
     */
    public Json updateUserPwd(User user) throws Exception;
    
    /**
     * 获取我所在的区域编码
     * @param userId
     * @return
     */
    public HashMap<String,String> getMyInArea(String userId) throws Exception;
    
    /**
     * 根据部门ID获取当前部门及子部门的信息
     * @param deptId
     * @return
     */
    public List<HashMap<String, String>> getDeptLevelList(String deptId) throws Exception;
    
    /**
     * 根据部门ID获取当前部门及下一级部门的数据信息
     * @param deptId
     * @return
     */
    public List<HashMap<String,String>> getKPIDeptLevelList(String deptId) throws Exception;
    
}
