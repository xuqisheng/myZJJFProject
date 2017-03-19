package com.corner.scms.service.impl;

import com.corner.core.beans.User;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.dao.UserMapper;
import com.corner.scms.beans.ro.auth.LoginRo;
import com.corner.scms.dao.UserMgMapper;
import com.corner.scms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserMgMapper userMgMapper;

	public User selectOne(String id){
		return userMapper.selectByPrimaryKey(id);
	}
	public User selectUserByLoginRo(LoginRo ro){
		List<User> users = userMgMapper.selectUser(ro);
		if(users == null || users.size() == 0 || users.size()>1){
			return null;
		}
		return users.get(0);
	}

	@Override
	public User selectUserBySpId(String spId) {
		return userMgMapper.selectUserBySupplierId(spId);
	}

	@Override
	public Integer updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public ModelMsg deleteObjects(String tableName, String[] array) {
		return null;
	}
}
