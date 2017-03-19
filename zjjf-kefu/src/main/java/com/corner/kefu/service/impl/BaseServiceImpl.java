package com.corner.kefu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.kefu.dao.KefuUserMapper;
import com.corner.kefu.model.User;
import com.corner.kefu.service.BaseService;

@Service("baseService")
public class BaseServiceImpl implements BaseService {
	
	@Autowired
	private KefuUserMapper addMapper;


	public String addInfo(User addInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return addMapper.getAll();
	}
	public String delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public User findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public String update(User addInfo) {
		// TODO Auto-generated method stub
		return null;
	}

//
//	public String addInfo(User addInfo) {
//		if (addMapper.insertSelective(addInfo) == 1) {
//			return "添加成功";
//		}
//		return "添加失败";
//	}
//
//	public List<User> getAll() {
//		return addMapper.getAll();
//	}
//
//	public String delete(String id) {
//		if (addMapper.deleteByPrimaryKey(id) == 1) {
//			return "删除成功";
//		}
//		return "删除失败";
//	}
//
//	public User findById(String id) {
//		return addMapper.selectByPrimaryKey(id);
//	}
//
//	public String update(User addInfo) {
//		if (addMapper.updateByPrimaryKeySelective(addInfo) == 1) {
//			return "更新成功";
//		}
//		return "更新失败";
//	}
//	

}
