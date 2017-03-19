package com.corner.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.corner.auth.beans.AdmRole;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.RoleRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.beans.vo.RoleVo;

@Service
public interface AdmRoleService {
	
	
	Pager<RoleVo> getRoleListPage(RoleRo command);
	
	ModelMsg insertRole(RoleRo command) throws Exception;
	
	ModelMsg updateRole(RoleRo command) throws Exception;
	
	ModelMsg deleteRole(String id) throws Exception;
	
	AdmRole getRoleById(String id);
	
	/**
	 * 
	* @Title: getRoleByAppIdOrUserId 
	* @Description: 根据用户ID获取用户权限 
	* @param: @param userId
	* @param: @return	设定文件 
	* @return List<AuthVo>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	List<RoleVo> getRoleByAppIdOrUserId(Integer appId , String userId);
}
