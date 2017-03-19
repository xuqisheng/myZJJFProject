package com.corner.auth.dao.mg;

import java.util.List;
import java.util.Map;

import com.corner.auth.beans.ro.RoleRo;
import com.corner.auth.beans.vo.RoleVo;



public interface AdmRoleMgMapper {
	public List<RoleVo> getRoleListPage(RoleRo command);
	public Integer getRoleListPageCount(RoleRo command);
	public Integer insertRoleAuthMap(RoleRo command);
	
	public List<RoleVo> getRoleByAppIdOrUserId(Map<String, Object> map);
}
