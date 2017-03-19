package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.Auth;
import com.corner.scms.beans.ro.auth.AuthCondition;
import com.corner.scms.beans.vo.sc.ScmsAuthVo;

public interface AuthMgMapper {

	List<Auth> getPageList(AuthCondition command);

	int getPageListSize(AuthCondition command);

	List<Auth> getAllAccountAuth();
	
	public List<ScmsAuthVo> getAuthByAppIdOrRoleId(Map<String, Object> map);
}
