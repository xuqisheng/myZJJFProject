package com.zjjf.analysis.services.authority.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.BaseAuthMenu;
import com.zjjf.analysis.beans.analysis.base.BaseRoleAuth;
import com.zjjf.analysis.beans.analysis.base.BaseRoleData;
import com.zjjf.analysis.mapper.analysis.BaseAuthMenuMapper;
import com.zjjf.analysis.mapper.analysis.BaseMenuMapper;
import com.zjjf.analysis.mapper.analysis.BaseRoleAuthMapper;
import com.zjjf.analysis.mapper.analysis.BaseRoleDataMapper;
import com.zjjf.analysis.producer.authority.IAuthorityMenuService;
import com.zjjf.analysis.producer.authority.IAuthoritydata;

@Service(version = "1.0.0")
public class AuthorityMenuServiceImpl implements IAuthorityMenuService {

	private Integer roleAuthId = 1;

	private static Logger logger = LoggerFactory.getLogger(AuthorityMenuServiceImpl.class);

	@Autowired
	private BaseAuthMenuMapper baseAuthMenuMapper;

	@Autowired
	private BaseRoleAuthMapper baseRoleAuthMapper;

	@Autowired
	private BaseMenuMapper baseMenuMapper;

	@Autowired
	private BaseRoleDataMapper baseRoleDataMapper;

	@Autowired
	private IAuthoritydata authoritydata;

	@Override
	public void save_base_auth_menu(Integer menuTwoId, List<Integer> menuIdList, List<Integer> dataKeyList, Integer roleId,
			Integer level) {

		deleteRoleData_by_menuId_roleId(roleId, menuTwoId);
		add_base_role_data(dataKeyList, roleId, menuTwoId, level);
		//deleteRoleAuthBy_roleId_menuId(menuTwoId, roleId);
		//deleteRoleAuthBy_roleId_menuId(getPidByMenuId(menuTwoId), roleId);
		add_base_role_auth(roleId, getPidByMenuId(menuTwoId));
		add_base_role_auth(roleId, menuTwoId);
	}

	@Override
	public void deleteRoleAuthByRoleId(Integer roleId) {

		baseRoleAuthMapper.deleteByRoleId(roleId);
	}

	@Override
	public void deleteRoleDataByRoleId(Integer roleId) {

		baseRoleDataMapper.deleteByRoleId(roleId);
	}

	@Override
	public List<HashMap<String, Object>> getAllMenuTree(Integer roleId) {

		List<HashMap<String, Object>> menuTree = getAllMenu(null, null, 1);
		for (HashMap<String, Object> hashMap : menuTree) {
			Integer id = Integer.valueOf("" + hashMap.get("id"));
			hashMap.put("level2Tree", getAllMenu(null, id, 2));
			hashMap.put("keyLists", authoritydata.getAllAuthorityKeysByMenuId(id));
		}
		return menuTree;
	}

	@Override
	public List<HashMap<String, Object>> getSelectionTotal(Integer roleId) {

		List<HashMap<String, Object>> selectList = new ArrayList<HashMap<String, Object>>();
		List<Integer> menuList = getSelectMenu(null, roleId, 1);
		for (Integer menuId : menuList) {
			HashMap<String, Object> temp = new HashMap<String, Object>();
			temp.put("menuOneId", menuId);
			temp.put("selectionPage", getSelectMenu(menuId, roleId, 2));
			temp.put("selection", authoritydata.getSelectKey(menuId, roleId));
			selectList.add(temp);
		}
		return selectList;
	}

	@Override
	public List<List<HashMap<String, Object>>> getAllSelectPageKeyJson(Integer roleId) {

		List<List<HashMap<String, Object>>> resultList = new ArrayList<List<HashMap<String, Object>>>();
		List<Integer> menuIdList = baseRoleDataMapper.getMenuIdList(roleId);
		for (Integer menuTwoId : menuIdList) {
			resultList.add(getSelectPageKeyJson(roleId, menuTwoId));
		}
		return resultList;
	}

	@Override
	public List<HashMap<String, Object>> getSelectPageKeyJson(Integer roleId, Integer menuTwoId) {

		List<HashMap<String, Object>> selectList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> temp = new HashMap<String, Object>();
		temp.put("menuTwoId", menuTwoId);
		temp.put("selectPageKeyJson", authoritydata.getSelectKey(menuTwoId, roleId));
		selectList.add(temp);
		return selectList;
	}

	@Override
	public List<Integer> getSelectPageJson(Integer roleId) {

		return getSelectMenu(null, roleId, 2);
	}

	@Override
	public HashMap<String, Object> selectPageKeyJson(Integer roleId, Integer menuTwoId) {

		HashMap<String, Object> temp = new HashMap<String, Object>();
		temp.put("menuTwoId", menuTwoId);
		temp.put("pageKeyJson", authoritydata.getAllAuthorityKeysByMenuId(menuTwoId));
		temp.put("selectPageKeyJson", authoritydata.getSelectKey(menuTwoId, roleId));
		return temp;
	}

	/************************************************************************************************************
	 * private 方法分割
	 * 
	 * @param pid
	 * @param roleId
	 */
	public void deleteRoleAuthBy_roleId_menuId(Integer menuId, Integer roleId) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", menuId);
		paramMap.put("roleId", roleId);
		logger.info(this.getClass() + "deleteBy_roleId_menuId: roleId: " + roleId + ", id: " + menuId);
		baseRoleAuthMapper.deleteByRoleIdMenuId(paramMap);
	}

	private void add_base_role_auth(Integer roleId, Integer menuId) {

		BaseRoleAuth baseRoleAuth = new BaseRoleAuth();
		Integer menuAuthId = getAuthMenuId(roleAuthId, Integer.valueOf(menuId));
		if (menuAuthId == 0) {
			logger.info("roleId: " + roleId + ", menuId: " + menuId + "的菜单权限Id不等于1");
			return;
		}
		baseRoleAuth.setMenuAuthId(menuAuthId);
		baseRoleAuth.setRoleId(roleId);
		baseRoleAuthMapper.insert(baseRoleAuth);
	}

	private Integer getPidByMenuId(Integer menuId) {

		return baseMenuMapper.getPidByMenuId(menuId);
	}

	private void add_base_role_data(List<Integer> dataKeyList, Integer roleId, Integer menuId, Integer level) {

		for (Integer datatemplateId : dataKeyList) {
			addBaseRoleData(menuId, roleId, datatemplateId, level);
		}
	}

	private void addBaseRoleData(Integer menuId, Integer roleId, Integer datatemplateId, Integer level) {

		BaseRoleData baseRoleData = new BaseRoleData();
		baseRoleData.setIsChecked(1);
		baseRoleData.setLevel(level);
		baseRoleData.setMenuId(menuId);
		baseRoleData.setRoleId(roleId);
		baseRoleData.setDatatemplateId(datatemplateId);
		baseRoleDataMapper.insert(baseRoleData);
	}

	@Override
	public void deleteRoleData_by_menuId_roleId(Integer roleId, Integer menuId) {

		HashMap<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("roleId", roleId);
		updateMap.put("menuId", menuId);
		baseRoleDataMapper.deleteByMenuIdAndRoleId(updateMap);
	}

	private Integer getAuthMenuId(Integer roleAuthId, Integer menuId) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("authId", roleAuthId);
		paramMap.put("menuId", menuId);
		BaseAuthMenu baseAuthMenu = baseAuthMenuMapper.getAuthByMenuIdAndAuthId(paramMap);
		if (baseAuthMenu != null) {
			return baseAuthMenu.getId();
		}
		return 0;
	}

	private List<HashMap<String, Object>> getAllMenu(Integer id, Integer pid, Integer level) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("level", level);
		paramMap.put("id", id);
		paramMap.put("pid", pid);
		return baseMenuMapper.getAllMenu(paramMap);
	}

	private List<Integer> getSelectMenu(Integer pid, Integer roleId, Integer level) {

		List<Integer> idList = new ArrayList<Integer>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pid", pid);
		paramMap.put("roleId", roleId);
		paramMap.put("level", level);
		List<BaseRoleAuth> menuList = baseRoleAuthMapper.getSelectMenuByRoleId(paramMap);
		for (BaseRoleAuth baseRoleAuth : menuList) {
			if (!idList.contains(baseRoleAuth.getMenuAuthId())) {
				idList.add(baseRoleAuth.getMenuAuthId());
			}
		}
		return idList;
	}

	@Override
	public void deleteRoleAuthIdNotInIdList(List<Integer> idList, Integer roleId) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("menuAuthIdList", idList);
		map.put("roleId", roleId);
		baseRoleAuthMapper.deleteRoleAuthIdNotInIdList(map);
	}
}
