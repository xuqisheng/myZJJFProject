package com.corner.scms.service;

import java.util.List;

import com.corner.core.beans.Role;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.auth.RoleCondition;

public interface RoleMgService extends BaseService{

	ModelMsg updateByPrimaryKeySelective(Role role);

	Pager<Role> getRolePageList(RoleCondition command);

	ModelMsg addObject(Role role);

	List<String> getAuthsByRoleId(String id);

	ModelMsg saveUserAuths(String roleId, String authIds);
}
