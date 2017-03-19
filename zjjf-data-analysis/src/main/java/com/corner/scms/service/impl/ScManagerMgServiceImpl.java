package com.corner.scms.service.impl;

import com.corner.core.beans.ScManager;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.auth.ScManagerCondition;
import com.corner.scms.service.ScManagerMgService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScManagerMgServiceImpl implements ScManagerMgService {

	@Override
	public ModelMsg deleteObjects(String tableName, String[] array) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<ScManager> getScManagerPageList(ScManagerCondition command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMsg updateByPrimaryKeySelective(ScManager ScManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMsg addObject(ScManager user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRolesById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMsg saveUserRoles(String userId, String roleIds) {
		// TODO Auto-generated method stub
		return null;
	}

}

