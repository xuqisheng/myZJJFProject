package com.zjjf.analysis.services.authority.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.authority.BaseRole;
import com.zjjf.analysis.beans.analysis.authority.BaseRoleUser;
import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.beans.analysis.base.BaseUserLevel;
import com.zjjf.analysis.beans.analysis.base.CustomerService;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.mapper.analysis.BaseRoleMapper;
import com.zjjf.analysis.mapper.analysis.BaseRoleUserMapper;
import com.zjjf.analysis.mapper.analysis.BaseUserLevelMapper;
import com.zjjf.analysis.mapper.origin.CustomerServiceMapper;
import com.zjjf.analysis.producer.authority.IRoleService;
import com.zjjf.analysis.producer.authority.IUserService;
import com.zjjf.analysis.producer.base.IRegionService;

@Service(version="1.0.0")
public class AuthorityUserServiceImpl implements IUserService {

	@Autowired
	private BaseRoleUserMapper baseRoleUserMapper;

	@Autowired
	private CustomerServiceMapper customerServiceMapper;

	@Autowired
	private BaseUserLevelMapper baseUserLevelMapper;
	
	@Autowired
	private BaseRoleMapper baseRoleMapper;

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IRegionService regionService;

	@Override
	public List<HashMap<String, Object>> getUserDataList(HashMap<String, Object> paramMap) {

		return baseRoleUserMapper.getUserList(paramMap);
	}

	@Override
	public Integer getTotalCount() {

		return baseRoleUserMapper.getTotalCount();
	}

	@Override
	public HashMap<String, Object> getUserInfo(Integer id) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if (id == 0) {
			resultMap.put("level", 2);
			return resultMap;
		}
		return baseRoleUserMapper.getById(id);
	}

	@Override
	public List<CustomerService> getLoginUserList(Integer userId) {

		List<String> exitUserIdList = new ArrayList<String>();
		List<BaseRoleUser> userList = baseRoleUserMapper.getExitUserList();
		for (BaseRoleUser bean : userList) {
			if (userId != null && userId > 0 && userId == bean.getId()) {
				continue;
			}
			exitUserIdList.add(bean.getUserId());
		}
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("exitUserIdList", exitUserIdList);
		return customerServiceMapper.getLoginUserList(paramMap);
	}

	@Override
	public List<String> getUserSelected(HashMap<String, Object> userInfoMap) {

		List<String> dataList = new ArrayList<String>();
		if (!userInfoMap.containsKey("id")) {
			return dataList;
		}
		Integer roleId = Integer.valueOf(userInfoMap.get("roleId") + "");
		Integer roleUserId = Integer.valueOf(userInfoMap.get("id") + "");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		paramMap.put("roleUserId", roleUserId);
		List<BaseUserLevel> listLevel = baseUserLevelMapper.getDataByMap(paramMap);
		for (BaseUserLevel bean : listLevel) {
			dataList.add(bean.getDataId() + "");
		}
		return dataList;
	}

	@Override
	public void saveUserInfo(Integer id, Integer roleId, String creater, String userName, String areaCodeStr) {

		if (areaCodeStr != null && areaCodeStr.length() > 0) {
			BaseRoleUser baseRoleUser = new BaseRoleUser();
			save_base_role_user(baseRoleUser, id, roleId, userName, areaCodeStr);
		}
	}

	@Override
	public void deleteUser(Integer id) {

		baseRoleUserMapper.deleteById(id);
		baseUserLevelMapper.deleteByRoleUserId(id);
	}

	@Override
	public Integer userNameRegular(String userName) {

		CustomerService userInfo = customerServiceMapper.getUserByCustomerServiceCredential(userName);
		if (userInfo != null) {
			return 1;
		}
		return 0;
	}

	@Override
	public AnaDictionary getSelectCity(Integer baseRoleId, Integer baseRoleUserId) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", baseRoleId);
		paramMap.put("roleUserId", baseRoleUserId);
		
		List<BaseUserLevel> list = baseUserLevelMapper.getDataByMap(paramMap);
		Integer areaId = 0;
		if(list.size() > 0){
			areaId = list.get(0).getDataId();
		}
		paramMap.clear();
		paramMap.put("areaId", areaId);
		List<AnaDictionary> listtwo = regionService.getCityByareaId(paramMap);
		return listtwo !=null && listtwo.size() > 0 ? listtwo.get(0) : null;
	}

	@Override
	public CustomerService getCustomerServiceByUserName(String userName){
		
		return customerServiceMapper.getUserByCustomerServiceCredential(userName);
	}
	
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
	
	/************************************************************************************************************
	 * private 方法分割
	 * 
	 * @param baseRoleUser
	 * @param id
	 * @param roleId
	 * @param userName
	 * @param areaCodeStr
	 */
	private void save_base_role_user(BaseRoleUser baseRoleUser, Integer id, Integer roleId, String userName, String areaCodeStr) {

		boolean active = (id == 0 ? true : false);
		if (active) {
			insertUser(baseRoleUser, roleId, userName);
		} else {
			updateUser(id, roleId, userName);
		}
		BaseRole baesRole = roleService.getRoleByRoleId(roleId);
		save_base_user_level(Arrays.asList(areaCodeStr.split(",")), roleId, active ? baseRoleUser.getId() : Integer.valueOf(id),
				baesRole.getLevel());
	}

	private void save_base_user_level(List<String> areaCodeList, Integer roleId, Integer roleUserId, Integer level) {

		if (areaCodeList != null && areaCodeList.size() > 0) {
			baseUserLevelMapper.deleteByRoleUserId(roleUserId);
			for (String dataId : areaCodeList) {
				BaseUserLevel baseUserLevel = new BaseUserLevel();
				baseUserLevel.setDataId(Integer.valueOf(dataId));
				baseUserLevel.setIsCheck(1);
				baseUserLevel.setLevel(level == null ? 0 : Integer.valueOf(level));
				baseUserLevel.setRoleId(roleId);
				baseUserLevel.setRoleUserId(roleUserId);
				baseUserLevelMapper.insert(baseUserLevel);
			}
		}
	}

	private void insertUser(BaseRoleUser baseRoleUser, Integer roleId, String userName) {

		baseRoleUser.setRoleId(roleId);
		baseRoleUser.setUserId(userName);
		baseRoleUserMapper.insert(baseRoleUser);
	}

	private void updateUser(Integer id, Integer roleId, String userName) {

		HashMap<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("id", id);
		userMap.put("roleId", roleId);
		userMap.put("userName", userName);
		baseRoleUserMapper.updateUser(userMap);
	}
}
