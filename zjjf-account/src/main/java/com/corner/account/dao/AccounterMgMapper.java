package com.corner.account.dao;

import java.util.List;
import java.util.Map;

import com.corner.account.beans.ro.AccounterCondition;
import com.corner.core.beans.Accounter;


public interface AccounterMgMapper {

	List<Accounter> getPageList(AccounterCondition command);
	
	int getPageListSize(AccounterCondition command);

	List<String> getRolesById(String id);

	int saveUserRoles(Map<String, Object> map);


}