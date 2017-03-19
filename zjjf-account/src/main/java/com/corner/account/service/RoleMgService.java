package com.corner.account.service;

import java.util.List;

import com.corner.account.beans.ro.RoleCondition;
import com.corner.core.beans.Role;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;

public interface RoleMgService extends BaseService{

	ModelMsg updateByPrimaryKeySelective(Role role);

	Pager<Role> getRolePageList(RoleCondition command);

	ModelMsg addObject(Role role);

	List<String> getAuthsByRoleId(String id);

	ModelMsg saveUserAuths(String roleId, String authIds);
}
