package com.corner.auth.service;

import org.springframework.stereotype.Service;

import com.corner.auth.beans.Admin;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.UserRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.beans.vo.UserVo;

@Service
public interface AdminService {
	
	
	Pager<UserVo> getUserListPage(UserRo command);
	
	ModelMsg insertUser(UserRo command) throws Exception;
	
	ModelMsg updateUser(UserRo command) throws Exception;
	
	ModelMsg deleteUser(String id) throws Exception;
	
	Admin getUserById(String id);
}
