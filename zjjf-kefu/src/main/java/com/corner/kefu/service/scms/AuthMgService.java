package com.corner.kefu.service.scms;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.corner.kefu.beans.vo.AuthVo;

@Service
public interface AuthMgService {
	public List<AuthVo> getAuthByAppIdOrRoleId(Map<String, Object> map);
}
