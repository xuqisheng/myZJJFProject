package com.zjjf.analysis.services.authority.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.authority.BaseRole;
import com.zjjf.analysis.mapper.analysis.BaseRoleMapper;
import com.zjjf.analysis.producer.authority.IAuthorityMenuService;
import com.zjjf.analysis.producer.authority.IRoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service(version="1.0.0")
public class AuthorityRoleServiceImpl implements IRoleService {

	private static Logger logger = LoggerFactory.getLogger(AuthorityRoleServiceImpl.class);

	@Autowired
	private IAuthorityMenuService baseMenuAuthService;

	@Autowired
	private BaseRoleMapper baseRoleMapper;

	/* @Transactional(propagation = Propagation.SUPPORTS, readOnly = true) */
	@Override
	public void deleteRole(Integer roleId) {

		deleteRoleById(roleId);
		baseMenuAuthService.deleteRoleAuthByRoleId(roleId);
		baseMenuAuthService.deleteRoleDataByRoleId(roleId);
	}

	/* @Transactional(propagation = Propagation.SUPPORTS, readOnly = true) */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public void saveRoleInfo(Integer roleId, String roleName, String creater, String selectPageJson,  String selectPageJsonKey, Integer level,
			Integer edit_type) {

		JSONArray pageJson = JSONArray.fromObject(selectPageJson);
		JSONArray pageKeyJson = JSONArray.fromObject(selectPageJsonKey);
		// 0新增， 1编辑， 2复用
		if (edit_type == 1) {
			update_base_role(roleId, roleName, level);
		} else if (edit_type == 2) {
			BaseRole baseRole = new BaseRole();
			add_base_role(baseRole, roleName, creater, level);
			roleId = baseRole.getId();
		} else if (edit_type == 0) {
			BaseRole baseRole = new BaseRole();
			add_base_role(baseRole, roleName, creater, level);
			roleId = baseRole.getId();
		}
		// 去除删掉权限的页面
		List<Integer> menuIdList = JSONArray.toList(pageJson);
		baseMenuAuthService.deleteRoleAuthByRoleId(roleId);
		for (int i = 0; i < pageKeyJson.size(); i++) {
			JSONArray keyJsonList = (JSONArray) pageKeyJson.get(i);
			for (Object object : keyJsonList) {
				JSONObject dataJson = (JSONObject)object;
				List<Integer> menuLeveIdList = getIdList(pageJson);
				List<Integer> dataIdList = getIdList(dataJson.getJSONArray("selectPageKeyJson"));
				Integer menuTwoId = dataJson.getInt("menuTwoId");
				// 已经去掉的menuId需要去除authDataKey
				if(!menuIdList.contains(menuTwoId)){
					baseMenuAuthService.deleteRoleData_by_menuId_roleId(roleId, menuTwoId);
					continue;
				}
				logger.info("menuTwoId:" + menuTwoId + "; menuLeveIdList:" + menuLeveIdList + ", dataIdList:" + dataIdList);
				baseMenuAuthService.save_base_auth_menu(menuTwoId, menuLeveIdList, dataIdList, roleId, level);
			}
		}
	}

	@Override
	public List<BaseRole> getAllRoleList() {

		return baseRoleMapper.getAllRole();
	}

	@Override
	public Integer isExitRole(String roleName) {

		return baseRoleMapper.getRoleByRoleName(roleName) != null ? 1 : 0;
	}

	@Override
	public BaseRole getRoleByRoleId(Integer roleId){
		
		BaseRole role = baseRoleMapper.getRoleByRoleId(roleId);
		return role == null ? new BaseRole() : role;
	}
	
	@Override
	public List<BaseRole> getRoleByUserId(String userId){
		
		return baseRoleMapper.getRoleByUserId(userId);
	}
	
	/************************************************************************************************************
	 * private 方法分割
	 * 
	 * @param baseRole
	 * @param roleName
	 * @param creater
	 * @param level
	 */
	private void add_base_role(BaseRole baseRole, String roleName, String creater, Integer level) {

		baseRole.setRoleNo(roleName);
		baseRole.setRoleName(roleName);
		baseRole.setRoleRemark(roleName);
		baseRole.setCreateTime(new Date());
		baseRole.setCreateUser(creater);
		baseRole.setStatus(1);
		baseRole.setOrdId(99);
		baseRole.setLevel(level);
		baseRole.setIsDelete("1");
		baseRoleMapper.insert(baseRole);
	}

	private void update_base_role(Integer id, String roleName, Integer level) {

		HashMap<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("roleName", roleName);
		updateMap.put("level", level);
		updateMap.put("id", id);
		baseRoleMapper.updateRole(updateMap);
	}

	private void deleteRoleById(Integer roleId) {

		baseRoleMapper.deleteById(roleId);
	}

	private List<Integer> getIdList(JSONArray list) {

		List<Integer> idList = new ArrayList<Integer>();
		for (Object object : list) {
			idList.add((Integer) object);
		}
		return idList;
	}
}
