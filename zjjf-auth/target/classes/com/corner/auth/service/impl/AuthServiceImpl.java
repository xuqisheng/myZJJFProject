package com.corner.auth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.auth.beans.Auth;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.AuthRo;
import com.corner.auth.beans.vo.AuthVo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.dao.AuthMapper;
import com.corner.auth.dao.mg.AuthMgMapper;
import com.corner.auth.service.AuthService;
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthMgMapper authMgMapper;
	@Autowired
	AuthMapper authMapper;
	@Override
	public Pager<Auth> getAuthListPage(AuthRo command) {
		List<Auth> auths = authMgMapper.getAuthListPage(command);
		int count = authMgMapper.getAuthListPageCount(command);
		return new Pager<Auth>(count , auths);
	}
	@Override
	public ModelMsg insertAuth(Auth command) throws Exception {
		command.setCreateTime(new Date());
		command.setIsDelete(false);
		int result = authMapper.insertSelective(command);
		if(result == 0)
			   throw new Exception("新增数据错误");
		return new ModelMsg(true, "新增成功");
	}
	@Override
	public ModelMsg updateAuth(Auth command) throws Exception {
		int result = authMapper.updateByPrimaryKeySelective(command);
		if(result == 0)
			   throw new Exception("修改数据错误");
		return new ModelMsg(true, "修改成功");
	}
	@Override
	public ModelMsg deleteAuth(String id) throws Exception {
		int result = authMapper.deleteByPrimaryKey(id);
		if(result == 0)
			   throw new Exception("删除数据错误");
		return new ModelMsg(true, "删除成功");
	}
	@Override
	public Auth getAuthById(String id) {
		return authMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<AuthVo> getAuthByAppIdOrRoleId(Integer appId , String[] roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", appId);
		map.put("roleId", roleId);
		map.put("sortOrder", "asc");
		map.put("sortName", "ordId,id,ordId2");
		return authMgMapper.getAuthByAppIdOrRoleId(map);
	}
	
}
