package com.corner.account.dao;

import java.util.List;
import java.util.Map;

import com.corner.account.beans.ro.RoleCondition;
import com.corner.core.beans.Role;

public interface RoleMgMapper {

	List<Role> getPageList(RoleCondition command);

	int getPageListSize(RoleCondition command);
	
	List<String> getAuthsByRoleId(String id);

	int saveUserAuths(Map<String, Object> map);

}
