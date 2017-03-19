package com.zjjf.analysis.producer.authority;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.beans.analysis.base.CustomerService;
import com.zjjf.analysis.beans.login.AuthInfo;

public interface IUserService {

	public List<HashMap<String, Object>> getUserDataList(HashMap<String, Object> paramMap);

	public Integer getTotalCount();

	public HashMap<String, Object> getUserInfo(Integer id);

	public List<CustomerService> getLoginUserList(Integer userId);

	public List<String> getUserSelected(HashMap<String, Object> userInfoMap);

	public void saveUserInfo(Integer id, Integer roleId, String creater, String userName, String areaCodeStr);

	public void deleteUser(Integer id);

	public Integer userNameRegular(String userName);

	public AnaDictionary getSelectCity(Integer baseRoleId, Integer baseRoleUserId);
	
	public CustomerService getCustomerServiceByUserName(String userName);
	
	public AuthInfo getAuthInfoMap(String userName);
}
