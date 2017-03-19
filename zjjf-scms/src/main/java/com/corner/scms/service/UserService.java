package com.corner.scms.service;

import com.corner.core.beans.User;
import com.corner.scms.beans.ro.auth.LoginRo;

public interface UserService extends BaseService {
	User selectOne(String id);
	User selectUserByLoginRo(LoginRo ro);
	User selectUserBySpId(String id);
	Integer updateUser(User user);
}
