package com.zjjf.analysis.services.authority.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.producer.authority.IAuthorityKeyArray;
import com.zjjf.analysis.producer.authority.IAuthorityLevel;
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.authority.IUserService;

@Service(version = "1.0.0")
public class AuthorityKeyArrayServiceImpl implements IAuthorityKeyArray {

	@Autowired
	private IAuthorityLevel authorityLevel;

	@Autowired
	private IUserService userService;

	@Autowired
	private IAuthoritydata authoritydata;

	@Override
	public Object[][] getAuthorityKeyArray(String userName, Integer menuId) {

		return authoritydata.getAuthorityFilter(userName, menuId);
	}

	@Override
	public void getAuthorityCityOrArea(HashMap<String, Object> paramMap, String userName, Integer menuId) {

		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level));
	}
}
