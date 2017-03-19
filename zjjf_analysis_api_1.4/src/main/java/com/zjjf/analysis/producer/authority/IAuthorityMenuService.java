package com.zjjf.analysis.producer.authority;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IAuthorityMenuService {

	void save_base_auth_menu(Integer pid, List<Integer> menuIdList, List<Integer> dataKeyList, Integer roleId, Integer level);

	void deleteRoleAuthByRoleId(Integer roleId);

	void deleteRoleDataByRoleId(Integer roleId);

	public List<HashMap<String, Object>> getAllMenuTree(Integer roleId);

	public List<HashMap<String, Object>> getSelectionTotal(Integer roleId);

	public List<Integer> getSelectPageJson(Integer roleId);

	public List<List<HashMap<String, Object>>> getAllSelectPageKeyJson(Integer roleId);

	public List<HashMap<String, Object>> getSelectPageKeyJson(Integer roleId, Integer menuTwoId);

	public HashMap<String, Object> selectPageKeyJson(Integer roleId, Integer menuTwoId);
	
	void deleteRoleAuthIdNotInIdList(List<Integer> idList, Integer roleId);
	
	public void deleteRoleData_by_menuId_roleId(Integer menuId, Integer roleId);
}
