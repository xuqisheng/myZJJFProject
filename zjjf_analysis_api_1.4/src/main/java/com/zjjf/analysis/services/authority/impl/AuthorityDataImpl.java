package com.zjjf.analysis.services.authority.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.authority.BaseRole;
import com.zjjf.analysis.beans.analysis.base.BaseRoleData;
import com.zjjf.analysis.beans.analysis.base.BaseRoleDataTemplate;
import com.zjjf.analysis.common.constants.Constants;
import com.zjjf.analysis.mapper.analysis.BaseRoleDataMapper;
import com.zjjf.analysis.mapper.analysis.BaseRoleDataTemplateMapper;
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.authority.IRoleService;
import com.zjjf.analysis.services.AbstractBaseService;

@Service(version="1.0.0")
public class AuthorityDataImpl extends AbstractBaseService<Object> implements IAuthoritydata {

	@Autowired
	private BaseRoleDataMapper baseRoleDataMapper;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private BaseRoleDataTemplateMapper baseRoleDataTemplateMapper;

	/**
	 * 获取权限字段
	 * 
	 * @param paramMap
	 */
	@Override
	public Object[][] getAuthorityFilter(String userName, Integer menuId) {

		List<BaseRole> roleList = roleService.getRoleByUserId(userName);
		String roleIds = "(";
		for (BaseRole baseRole : roleList) {
			roleIds += baseRole.getId() + ",";
		}
		HashMap<String, Object> dataParam = new HashMap<String, Object>();
		dataParam.put("menuId", menuId);
		dataParam.put("roleIds", roleIds.substring(0, roleIds.length() - 1) + ")");
		List<BaseRoleData> templateList = baseRoleDataMapper.getAuthorityData(dataParam);
		if (templateList == null || templateList.size() == 0) {
			return new Object[][] {};
		}
		Object[][] authorityArray = new Object[templateList.size()][2];
		for (int i = 0; i < templateList.size(); i++) {
			BaseRoleData template = templateList.get(i);
			authorityArray[i][0] = Constants.authority_query + template.getAuthkey().trim();
			authorityArray[i][1] = template.getName().trim();
		}
		return authorityArray;
	}

	@Override
	public Object[] getOrderTitleCn(String userName, Integer menuId) {

		return super.getOrderTitleCn(this.getAuthorityFilter(userName, menuId));
	}

	public Object[] getOrderTitleEn(String userName, Integer menuId) {

		return super.getOrderTitleEn(this.getAuthorityFilter(userName, menuId));
	}

	@Override
	public List<HashMap<String, Object>> getAllAuthorityKeysByMenuId(Integer menuId) {

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> dataParam = new HashMap<String, Object>();
		dataParam.put("menuId", menuId);
		List<BaseRoleDataTemplate> dataList = baseRoleDataTemplateMapper.getRoleDataTemplate(dataParam);
		for (BaseRoleDataTemplate bean : dataList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", bean.getId());
			map.put("code", bean.getAuthkey().trim());
			map.put("name", bean.getName());
			resultList.add(map);
		}
		return resultList;
	}

	@Override
	public List<Integer> getSelectKey(Integer menuId, Integer roleId) {

		List<Integer> idList = new ArrayList<Integer>();
		HashMap<String, Object> dataParam = new HashMap<String, Object>();
		dataParam.put("roleId", roleId);
		dataParam.put("menuId", menuId);
		List<BaseRoleData> dataList = baseRoleDataMapper.getBy_datatemplateId_menuId(dataParam);
		for (BaseRoleData baseRoleData : dataList) {
			idList.add(baseRoleData.getDatatemplateId());
		}
		return idList;
	}
	
	@Override
	public List<HashMap<String, Object>> getParentTitle(Integer menuId, Integer baseRoleId) {
		
		List<Integer> idList = new ArrayList<Integer>();
		HashMap<String, Object> dataParam = new HashMap<String, Object>();
		dataParam.put("menuId", menuId);
		dataParam.put("roleId", baseRoleId);
		List<BaseRoleData> list = baseRoleDataMapper.getAuthorityData(dataParam);
		if(list.size() == 0){
			return null;
		}
		for (BaseRoleData bean : list) {
			idList.add(bean.getId());
		}
		dataParam.put("idList", idList);
		return baseRoleDataTemplateMapper.getParentTitle(dataParam);
	}

	public void addBaseRoleData(Integer menuId, Integer roleId, Integer datatemplateId, Integer level) {

		BaseRoleData baseRoleData = new BaseRoleData();
		baseRoleData.setIsChecked(1);
		baseRoleData.setLevel(level);
		baseRoleData.setMenuId(menuId);
		baseRoleData.setRoleId(roleId);
		baseRoleData.setDatatemplateId(datatemplateId);
		baseRoleDataMapper.insert(baseRoleData);
	}

	public void delete_by_menuId_roleId(Integer menuId, Integer roleId) {

		HashMap<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("roleId", roleId);
		updateMap.put("menuId", menuId);
		baseRoleDataMapper.deleteByMenuIdAndRoleId(updateMap);
	}

	public void deleteByRoleId(Integer roleId) {

		baseRoleDataMapper.deleteByRoleId(roleId);
	}
}
