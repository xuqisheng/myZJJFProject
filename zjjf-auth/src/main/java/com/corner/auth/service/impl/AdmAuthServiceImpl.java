package com.corner.auth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.auth.beans.AdmAuth;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.AuthRo;
import com.corner.auth.beans.vo.AuthVo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.dao.AdmAuthMapper;
import com.corner.auth.dao.mg.AdmAuthMgMapper;
import com.corner.auth.service.AdmAuthService;
@Service
public class AdmAuthServiceImpl implements AdmAuthService {

	@Autowired
	AdmAuthMgMapper admAuthMgMapper;
	@Autowired
	AdmAuthMapper admAuthMapper;
	@Override
	public Pager<AdmAuth> getAuthListPage(AuthRo command) {
		List<AdmAuth> auths = admAuthMgMapper.getAuthListPage(command);
		int count = admAuthMgMapper.getAuthListPageCount(command);
		return new Pager<AdmAuth>(count , auths);
	}
	@Override
	public ModelMsg insertAuth(AdmAuth command) throws Exception {
		command.setCreateTime(new Date());
		command.setUpdateTime(new Date());
		int result = admAuthMapper.insertSelective(command);
		if(result == 0)
			   throw new Exception("新增数据错误");
		return new ModelMsg(true, "新增成功");
	}
	@Override
	public ModelMsg updateAuth(AdmAuth command) throws Exception {
		command.setUpdateTime(new Date());
		int result = admAuthMapper.updateByPrimaryKeySelective(command);
		if(result == 0)
			   throw new Exception("修改数据错误");
		return new ModelMsg(true, "修改成功");
	}
	@Override
	public ModelMsg deleteAuth(String id) throws Exception {
		int result = admAuthMapper.deleteByPrimaryKey(id);
		if(result == 0)
			   throw new Exception("删除数据错误");
		return new ModelMsg(true, "删除成功");
	}
	@Override
	public AdmAuth getAuthById(String id) {
		return admAuthMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<AuthVo> getAuthByAppIdOrRoleId(Integer appId , String[] roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", appId);
		map.put("roleId", roleId);
		map.put("sortOrder", "asc");
		map.put("sortName", "a.ordId,e.ordId");
		return admAuthMgMapper.getAuthByAppIdOrRoleId(map);
	}
}
