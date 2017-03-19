package com.corner.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.corner.auth.beans.AdmAuth;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.AuthRo;
import com.corner.auth.beans.vo.AuthVo;
import com.corner.auth.beans.vo.Pager;

@Service
public interface AdmAuthService {
	
	
	Pager<AdmAuth> getAuthListPage(AuthRo command);
	
	ModelMsg insertAuth(AdmAuth command) throws Exception;
	
	ModelMsg updateAuth(AdmAuth command) throws Exception;
	
	ModelMsg deleteAuth(String id) throws Exception;
	
	AdmAuth getAuthById(String id);
	
	/**
	 * 
	* @Title: getAuthByUserId 
	* @Description: 根据用户ID获取用户权限 
	* @param: @param userId
	* @param: @return	设定文件 
	* @return List<AuthVo>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	List<AuthVo> getAuthByAppIdOrRoleId(Integer appId , String[] roleId);
}
