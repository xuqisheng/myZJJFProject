package com.corner.auth.dao.mg;

import java.util.List;
import java.util.Map;

import com.corner.auth.beans.AdmAuth;
import com.corner.auth.beans.ro.AuthRo;
import com.corner.auth.beans.vo.AuthVo;


public interface AdmAuthMgMapper {
	public List<AuthVo> getAuthByAppIdOrRoleId(Map<String, Object> map);
	
	public List<AdmAuth> getAuthListPage(AuthRo command);
	public Integer getAuthListPageCount(AuthRo command);

}
