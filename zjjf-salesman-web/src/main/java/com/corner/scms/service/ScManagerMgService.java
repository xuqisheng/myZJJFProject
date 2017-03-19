package com.corner.scms.service;

import com.corner.core.beans.ScManager;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.auth.ScManagerCondition;

import java.util.List;


public interface ScManagerMgService extends BaseService{

	Pager<ScManager> getScManagerPageList(ScManagerCondition command);

	ModelMsg updateByPrimaryKeySelective(ScManager ScManager);

	ModelMsg addObject(ScManager user);

	List<String> getRolesById(String id);

	ModelMsg saveUserRoles(String userId, String roleIds);

}
