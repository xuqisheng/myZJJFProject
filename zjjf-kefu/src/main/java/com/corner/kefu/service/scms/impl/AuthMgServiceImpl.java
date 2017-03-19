package com.corner.kefu.service.scms.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.kefu.beans.vo.AuthVo;
import com.corner.kefu.dao.AuthMgMapper;
import com.corner.kefu.service.scms.AuthMgService;
@Service
public class AuthMgServiceImpl implements AuthMgService {

	@Autowired
	AuthMgMapper authMgMapper;
	@Override
	public List<AuthVo> getAuthByAppIdOrRoleId(Map<String, Object> map) {
		List<AuthVo> authList = authMgMapper.getAuthByAppIdOrRoleId(map);
		return authList;
	}
	
}
