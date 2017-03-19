package com.corner.auth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.auth.beans.Role;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.RoleRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.beans.vo.RoleVo;
import com.corner.auth.dao.RoleMapper;
import com.corner.auth.dao.mg.RoleMgMapper;
import com.corner.auth.service.RoleService;
import com.corner.auth.utils.BeanUtil;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleMapper roleMapper;
	@Autowired
	RoleMgMapper roleMgMapper;
	@Override
	public Pager<RoleVo> getRoleListPage(RoleRo command) {
		List<RoleVo> list =roleMgMapper.getRoleListPage(command);
		int result = roleMgMapper.getRoleListPageCount(command);
		return new Pager<RoleVo>( result , list);
	}

	@Override
	public ModelMsg insertRole(RoleRo command) throws Exception {
		command.setCreateTime(new Date());
		command.setUpdateTime(new Date());
		int result = roleMapper.insertSelective(BeanUtil.toObject(Role.class, command));
		if(result == 0)
			   throw new Exception("新增数据错误");
		result = roleMgMapper.insertRoleAuthMap(command);
		return new ModelMsg(true, "新增成功");
	}

	@Override
	public ModelMsg updateRole(RoleRo command) throws Exception {
		command.setUpdateTime(new Date());
		int result = roleMapper.updateByPrimaryKeySelective(BeanUtil.toObject(Role.class, command));
		if(result == 0)
			   throw new Exception("修改数据错误");
		if(command.getAuthIds() == null || command.getAuthIds().length == 0){
			return new ModelMsg(true, "修改成功");
		}
		result = roleMgMapper.insertRoleAuthMap(command);
		return new ModelMsg(true, "修改成功");
	}

	@Override
	public ModelMsg deleteRole(String id) throws Exception {
		Role command = new Role();
		command.setUpdateTime(new Date());
		command.setIsDelete("1");
		int result = roleMapper.updateByPrimaryKeySelective(command);
		if(result == 0)
			   throw new Exception("删除数据错误");
		return new ModelMsg(true, "删除成功");
	}
	@Override
	public Role getRoleById(String id) {
		return roleMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<RoleVo> getRoleByAppIdOrUserId(Integer appId, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("appId", appId);
		return roleMgMapper.getRoleByAppIdOrUserId(map);
	}
}
