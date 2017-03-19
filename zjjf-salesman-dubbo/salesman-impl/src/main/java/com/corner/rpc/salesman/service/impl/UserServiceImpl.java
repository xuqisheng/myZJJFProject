package com.corner.rpc.salesman.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.rpc.salesman.api.service.UserService;
import com.corner.rpc.salesman.dao.UserMapper;
import com.corner.rpc.salesman.model.User;
import com.corner.salesman.commons.persistence.Page;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 根据用户ID获取用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User getUserById(String userId) throws Exception{
		return userMapper.selectByPrimaryKey(userId);
	}
	
    /**
     * 获取部门领导ID
     * @param deptId
     * @return
     */
	@Override
    public List<String> getDeptLeaderIdList(String deptId) throws Exception{
    	return userMapper.getDeptLeaderIdList(deptId);
    }

	/**
	 * 获取用户列表信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<User> getUserList(User user) throws Exception {
		return userMapper.getUserList(user);
	}
	
    /**
     * 查询用户列表(分页方法)
     * @param user
     * @return
     */
	@Override
	public Page<User> getUserList(Page<User> page, User user) throws Exception{
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		List<User> list = userMapper.getUserList(user);
		if(null != list && !list.isEmpty()){
			page.setList(list);
		}
		return page;
	}
	
    /**
     * 获取日报消息提醒用户列表
     * @param map
     * @return
     */
	@Override
    public List<HashMap<String,Object>> getReportWarnUserList(Map<String,Object> map) throws Exception{
		return userMapper.getReportWarnUserList(null);
	}
	
    /**
     * 获取未签到提醒用户列表
     * @param map
     * @return
     */
	@Override
    public List<HashMap<String,Object>> getSignWarnUserList(Map<String,Object> map) throws Exception{
    	return userMapper.getSignWarnUserList(map);
    }
    
    /**
     * 根据部门ID获取部门用户mobile列表信息
     * @param deptId
     * @return
     */
	@Override
    public List<String> getUserMobileList(String deptId) throws Exception{
		return userMapper.getUserMobileList(deptId);
    }
}
