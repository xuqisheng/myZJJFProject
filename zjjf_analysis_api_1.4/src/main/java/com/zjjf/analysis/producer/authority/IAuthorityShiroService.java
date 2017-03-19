package com.zjjf.analysis.producer.authority;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.login.AuthInfo;

public interface IAuthorityShiroService {

	public AuthInfo getAuthInfoMap(String userName);

	public String getRoleSet(String userId);

	public String getPermissionsSet(String userId);

	public List<HashMap<String, Object>> getMenuTree(String userId);

}
