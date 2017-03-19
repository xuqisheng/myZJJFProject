package com.corner.kefu.dao.sp;

import java.util.List;

import com.corner.core.beans.User;

public interface SpUserMgMapper {
	List<User> getUserByStoreId(Integer storeId);

	
	/**
	 * 
	* @Title: getUserBySupplierId 
	* @Description:根据批发商id获取User
	* @param @param id
	* @param @return
	* @return User    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	User getUserBySupplierId(String id);

	/**
	 * 
	* @Title: getUserByMobile 
	* @Description:根据手机号获取User
	* @param @param mobile
	* @param @return
	* @return List<User>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<User> getUserByMobile(String mobile);
	
}
