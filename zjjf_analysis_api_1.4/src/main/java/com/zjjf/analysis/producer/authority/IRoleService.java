package com.zjjf.analysis.producer.authority;

import java.util.List;

import com.zjjf.analysis.beans.analysis.authority.BaseRole;

public interface IRoleService {

	public void deleteRole(Integer roleId);

	public void saveRoleInfo(Integer roleId, String roleName, String creater, String selectPageJson, String selectPageJsonKey, Integer level, Integer edit_type);

	public List<BaseRole> getAllRoleList();

	public Integer isExitRole(String roleName);

	public BaseRole getRoleByRoleId(Integer roleId);
	
	public List<BaseRole> getRoleByUserId(String userId);
}
