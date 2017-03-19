package com.corner.b2c.service;

import com.corner.b2c.model.User;

import java.util.List;

public interface BaseService {
	

	
	List<User> getAll();
	
	String delete(String id);
	
	User findById(String id);
	
	String update(User addInfo);

	String addInfo(User add);

}
