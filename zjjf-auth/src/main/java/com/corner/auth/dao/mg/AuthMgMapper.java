package com.corner.auth.dao.mg;

import java.util.List;
import java.util.Map;

import com.corner.auth.beans.Auth;
import com.corner.auth.beans.ro.AuthRo;
import com.corner.auth.beans.vo.AuthVo;


public interface AuthMgMapper {
	
	public List<AuthVo> getAuthByAppIdOrRoleId(Map<String, Object> map);
	
	public List<Auth> getAuthListPage(AuthRo command);
	public Integer getAuthListPageCount(AuthRo command);

}
