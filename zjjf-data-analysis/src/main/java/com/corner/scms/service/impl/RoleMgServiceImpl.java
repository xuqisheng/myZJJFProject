package com.corner.scms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.corner.core.beans.Role;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.RoleMapper;
import com.corner.scms.beans.ro.auth.RoleCondition;
import com.corner.scms.dao.AuthorityMapper;
import com.corner.scms.dao.RoleMgMapper;
import com.corner.scms.service.RoleMgService;

@Service
public class RoleMgServiceImpl  extends BaseServiceImpl  implements RoleMgService {

	@Autowired RoleMapper roleMapper;
	
	@Autowired RoleMgMapper roleMgMapper;
	
	@Autowired
	AuthorityMapper authorityMapper;

	@Override
	public ModelMsg updateByPrimaryKeySelective(Role role) {
		int count = roleMapper.updateByPrimaryKeySelective(role);
		if(count == 1){
			return new ModelMsg(true,"修改成功");	
		}else{
			return new ModelMsg(false,"修改失败");	
		}
	}

	@Override
	public Pager<Role> getRolePageList(RoleCondition command) {
		List<Role> list=roleMgMapper.getPageList(command);
		int size = roleMgMapper.getPageListSize(command);
		return new Pager<Role>(size,list);
	}

	@Override
	public ModelMsg addObject(Role role) {
		if(role == null || role.getId() == null){
			return new ModelMsg(false,"修改失败");
		}else{
			int count = roleMapper.insertSelective(role);
			if(count == 1){
				return new ModelMsg(true,"修改成功");	
			}else{
				return new ModelMsg(false,"修改异常");	
			}
		}
	}
	
	@Override
	public List<String> getAuthsByRoleId(String id) {
		if(id == null){
			return null;
		}else{
			return roleMgMapper.getAuthsByRoleId(id);
		}
	}

	@Override
	public ModelMsg saveUserAuths(String roleId, String authIds) {
		if(StringUtils.isEmpty(roleId) || StringUtils.isEmpty(authIds)){
			return new ModelMsg(false, "修改失败");
		}else{
			String[] ids = authIds.split(",");
			if(ids == null || ids.length < 1){
				return new ModelMsg(false, "修改失败");
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("roleId", roleId);
			map.put("authIds", ids);
			int count = roleMgMapper.saveUserAuths(map);
			if (count >= 0) {
				return new ModelMsg(true, "修改成功");
			} else {
				return new ModelMsg(false, "修改异常");
			}
		}
	}

}
