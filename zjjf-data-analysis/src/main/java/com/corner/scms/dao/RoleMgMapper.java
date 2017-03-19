package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.Role;
import com.corner.scms.beans.ro.auth.RoleCondition;

public interface RoleMgMapper {

	List<Role> getPageList(RoleCondition command);

	int getPageListSize(RoleCondition command);
	
	List<String> getAuthsByRoleId(String id);

	int saveUserAuths(Map<String, Object> map);

}
