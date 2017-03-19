package com.corner.kefu.service.sp.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SpAdminVerifyRecord;
import com.corner.core.beans.User;
import com.corner.core.dao.SpAdminVerifyRecordMapper;
import com.corner.core.dao.UserMapper;
import com.corner.kefu.dao.sp.SpUserMgMapper;
import com.corner.kefu.service.sp.SpUserService;
/**
 * 用户service
 * @author aimee at 2015年6月16日上午10:41:46
 * @email 1297579898@qq.com
 */
@Service
public class SpUserServiceImpl implements SpUserService{
	@Autowired
	SpUserMgMapper spUserMgMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	SpAdminVerifyRecordMapper spAdminVerifyRecordMapper;
	/**
	 * 根据sotreId 查询User
	 * @param stId
	 * @return
	 */
	@Override
	public List<User> getUserByStoreId(Integer stId) {
		return spUserMgMapper.getUserByStoreId(stId);
	}
	
	@Override
	public void saveToRecord(String verifyadminId,String verifyAdminNm,String verifyObjectId,Integer VerifyObjIntId,String ActionNm){
		SpAdminVerifyRecord sp = new SpAdminVerifyRecord();
		sp.setVerifyadminId(verifyadminId);
		sp.setVerifyAdminNm(verifyAdminNm);
		sp.setVerifyObjectId(verifyObjectId);
		sp.setVerifyObjIntId(VerifyObjIntId);
		sp.setActionNm(ActionNm);
		Date date = new Date();
		sp.setActionTime(date);
		spAdminVerifyRecordMapper.insertSelective(sp);
	}

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
	@Override
	public User getUserBySupplierId(String id) {
		return spUserMgMapper.getUserBySupplierId(id);
	}

	
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
	@Override
	public List<User> getUserByMobile(String mobile) {
		return spUserMgMapper.getUserByMobile(mobile);
	}
}
