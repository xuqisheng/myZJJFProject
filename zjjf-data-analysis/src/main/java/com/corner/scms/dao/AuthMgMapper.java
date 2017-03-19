package com.corner.scms.dao;

import java.util.List;

import com.corner.core.beans.Auth;
import com.corner.scms.beans.ro.auth.AuthCondition;

public interface AuthMgMapper {

	List<Auth> getPageList(AuthCondition command);

	int getPageListSize(AuthCondition command);

	List<Auth> getAllAccountAuth();

}
