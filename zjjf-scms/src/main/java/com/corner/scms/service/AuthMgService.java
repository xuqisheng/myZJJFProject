package com.corner.scms.service;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.Auth;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.auth.AuthCondition;
import com.corner.scms.beans.vo.sc.ScmsAuthVo;

public interface AuthMgService extends BaseService{

	ModelMsg updateByPrimaryKeySelective(Auth auth);

	Pager<Auth> getAuthPageList(AuthCondition command);

	ModelMsg addObject(Auth auth);

	List<Auth> getAllMenus();
	
	public List<ScmsAuthVo> getAuthByAppIdOrRoleId(Map<String, Object> map);

}
