package com.corner.account.dao;

import java.util.List;

import com.corner.account.beans.ro.AuthCondition;
import com.corner.core.beans.Auth;

public interface AuthMgMapper {

	List<Auth> getPageList(AuthCondition command);

	int getPageListSize(AuthCondition command);

	List<Auth> getAllAccountAuth();

}
