package com.corner.b2b.service;

import java.util.List;

import com.corner.b2b.model.User;

public interface BaseService {
	

	
	List<User> getAll();
	
	String delete(String id);
	
	User findById(String id);
	
	String update(User addInfo);

	String addInfo(User add);

}
