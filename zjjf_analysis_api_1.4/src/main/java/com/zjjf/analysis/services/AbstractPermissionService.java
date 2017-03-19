package com.zjjf.analysis.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.producer.authority.IAuthorityLevel;
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.authority.IUserService;
import com.zjjf.analysis.utils.MapUtils;

@Service(version = "1.0.0")
public abstract class AbstractPermissionService<T> extends AbstractViewService<T> {

	@Autowired
	private IAuthorityLevel authorityLevel;

	@Autowired
	private IUserService userService;

	@Autowired
	private IAuthoritydata authoritydata;

	public void getPermissionsMap(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level));
	}

	public Object[][] getPermissionsKeyList(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		Object[][] authorityArray = authoritydata.getAuthorityFilter(userName, menuId);
		MapUtils.array_2_map(authorityArray, paramMap);
		return authorityArray;
	}
}
