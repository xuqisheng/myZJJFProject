package com.corner.account.service;

import com.corner.account.beans.ro.AuthCondition;
import com.corner.core.beans.Auth;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;

import java.util.List;

public interface AuthMgService extends BaseService{

	ModelMsg updateByPrimaryKeySelective(Auth auth);

	Pager<Auth> getAuthPageList(AuthCondition command);

	ModelMsg addObject(Auth auth);

	List<Auth> getAllMenus();


}
