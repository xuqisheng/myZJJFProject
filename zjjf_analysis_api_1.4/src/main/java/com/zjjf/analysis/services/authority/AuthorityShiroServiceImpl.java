package com.zjjf.analysis.services.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.authority.BaseAuthority;
import com.zjjf.analysis.beans.analysis.authority.BaseRole;
import com.zjjf.analysis.beans.analysis.authority.BaseRoleUser;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.mapper.analysis.BaseAuthorityMapper;
import com.zjjf.analysis.mapper.analysis.BaseMenuMapper;
import com.zjjf.analysis.mapper.analysis.BaseRoleMapper;
import com.zjjf.analysis.mapper.analysis.BaseRoleUserMapper;
import com.zjjf.analysis.producer.authority.IAuthorityShiroService;

@Service(version="1.0.0")
public class AuthorityShiroServiceImpl  implements IAuthorityShiroService{

	@Autowired
	private BaseMenuMapper baseMenuMapper;

	@Autowired
	private BaseRoleMapper baseRoleMapper;
	
	@Autowired
	private BaseRoleUserMapper baseRoleUserMapper;

	@Autowired
	private BaseAuthorityMapper baseAuthorityMapper;

	@Override
	public AuthInfo getAuthInfoMap(String userName) {
		
		AuthInfo authInfo = new AuthInfo();
		BaseRoleUser baseRoleUser = baseRoleUserMapper.getByUserId(userName);
		BaseRole baseRole = baseRoleMapper.getRoleByRoleId(baseRoleUser.getRoleId());
		authInfo.setBaseRoleId(baseRoleUser.getRoleId());
		authInfo.setUserId(baseRoleUser.getUserId());
		authInfo.setLevel(baseRole.getLevel());
		authInfo.setBaseRoleUserId(baseRoleUser.getId());
		return authInfo;
	}
	

	@Override
	public String getRoleSet(String userId) {

		String roleStr = "";
		// Set<String> roleSet = new HashSet<String>();
		List<BaseRole> roleList = baseRoleMapper.getRoleByUserId(userId);
		if (roleList.size() > 0) {
			for (BaseRole baseRole : roleList) {
				// roleSet.add(baseRole.getRoleNo());
				roleStr = baseRole.getRoleNo() + ",";
			}
			return roleStr.substring(0, roleStr.length() - 1);
		}
		return roleStr;
	}

	@Override
	public String getPermissionsSet(String userId) {

		String permissionStr = "";
		// Set<String> roleSet = new HashSet<String>();
		List<BaseAuthority> roleList = baseAuthorityMapper.getAuthorityByUserId(userId);
		if (roleList.size() > 0) {
			for (BaseAuthority baseAuthority : roleList) {
				// roleSet.add(baseAuthority.getRoleNo() + ":" +
				// baseAuthority.getAuthString());
				permissionStr = permissionStr + baseAuthority.getAuthString() + ",";
			}
			return permissionStr.substring(0, permissionStr.length() - 1);
		}
		return permissionStr;
	}
	
	@Override
	public List<HashMap<String, Object>> getMenuTree(String userId) {
		
		List<Integer> rollIdList = getRoleIdByUserId(userId);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("level", 1);
		paramMap.put("userId", userId);
		paramMap.put("rollIdList",  rollIdList);
		List<HashMap<String, Object>> menuTree = baseMenuMapper.getMenuLevel(paramMap);
		for (HashMap<String, Object> hashMap : menuTree) {
			paramMap.clear();
			paramMap.put("pid", hashMap.get("id"));
			paramMap.put("level", 2);
			paramMap.put("userId", userId);
			paramMap.put("rollIdList",  rollIdList);
			List<HashMap<String, Object>> level2Tree = baseMenuMapper.getMenuLevel(paramMap);
			hashMap.put("level2Tree", level2Tree);
		}
		return menuTree;
	}
	
	private List<Integer> getRoleIdByUserId(String userId) {
		
		List<Integer> idList = new ArrayList<Integer>();
		List<BaseRole> roleList =  baseRoleMapper.getRoleByUserId(userId);
		for (BaseRole baseRole : roleList) {
			idList.add(baseRole.getId());
		}
		return idList;
	}
}
