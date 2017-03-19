package com.corner.kefu.service.sp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.corner.core.beans.User;
/**
 * 用户service
 * @author aimee at 2015年6月16日上午10:41:46
 * @email 1297579898@qq.com
 */
@Service
public interface SpUserService {
	/**
	 * 根据sotreId 查询User
	 * @param stId
	 * @return
	 */
	List<User> getUserByStoreId(Integer stId);
	
	/**
	 * 插入审核记录
	 * @author aimee at 2015年6月16日上午10:44:03
	 * @email 1297579898@qq.com
	 * @param verifyadminId
	 * @param verifyAdminNm
	 * @param verifyObjectId
	 * @param verifyObjectNm
	 * @param ActionNm
	 */
	void saveToRecord(String verifyadminId,String verifyAdminNm,String verifyObjectId,Integer VerifyObjIntId,String ActionNm);

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
