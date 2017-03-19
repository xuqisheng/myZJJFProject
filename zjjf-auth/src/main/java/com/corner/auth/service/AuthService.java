package com.corner.auth.service;

import com.corner.auth.beans.Auth;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.AuthRo;
import com.corner.auth.beans.vo.AuthVo;
import com.corner.auth.beans.vo.Pager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {
	Pager<Auth> getAuthListPage(AuthRo command);
	
	ModelMsg insertAuth(Auth command) throws Exception;
	
	ModelMsg updateAuth(Auth command) throws Exception;
	
	ModelMsg deleteAuth(String id) throws Exception;
	
	Auth getAuthById(String id);
	
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
