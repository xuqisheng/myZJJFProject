package com.corner.kefu.dao;

import java.util.List;
import java.util.Map;

import com.corner.kefu.beans.vo.AuthVo;


public interface AuthMgMapper {
	public List<AuthVo> getAuthByAppIdOrRoleId(Map<String, Object> map);
}
