package com.corner.account.service;

import java.util.List;

import com.corner.account.beans.ro.AccounterCondition;
import com.corner.core.beans.Accounter;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;


public interface AccounterMgService extends BaseService{

	Pager<Accounter> getAccounterPageList(AccounterCondition command);

	ModelMsg updateByPrimaryKeySelective(Accounter accounter);

	ModelMsg addObject(Accounter user);

	List<String> getRolesById(String id);

	ModelMsg saveUserRoles(String userId, String roleIds);

}
