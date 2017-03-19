package com.corner.scms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.Accounter;
import com.corner.core.beans.Auth;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.AuthMapper;
import com.corner.scms.beans.ro.auth.AuthCondition;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.dao.AuthMgMapper;
import com.corner.scms.service.AuthMgService;

@Service
public class AuthMgServiceImpl extends BaseServiceImpl implements AuthMgService {

	@Autowired AuthMapper authMapper;
	
	@Autowired AuthMgMapper authMgMapper;

	@Override
	public ModelMsg updateByPrimaryKeySelective(Auth auth) {
		int count = authMapper.updateByPrimaryKeySelective(auth);
		if(count == 1){
			return new ModelMsg(true,"修改成功");	
		}else{
			return new ModelMsg(false,"修改失败");	
		}
	}

	@Override
	public Pager<Auth> getAuthPageList(AuthCondition command) {
		List<Auth> list=authMgMapper.getPageList(command);
		int size = authMgMapper.getPageListSize(command);
		return new Pager<Auth>(size,list);
	}

	@Override
	public ModelMsg addObject(Auth auth) {
		if(auth == null || auth.getId() == null){
			return new ModelMsg(false,"修改失败");
		}else{
			Accounter current = (Accounter)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(current==null){
				return new ModelMsg(false,"请登录后进行操作");
			}
			auth.setCreateUser(current.getId());
			auth.setCreateTime(new Date());
			auth.setPosition((byte)4);
			int count = authMapper.insertSelective(auth);
			if(count == 1){
				return new ModelMsg(true,"修改成功");	
			}else{
				return new ModelMsg(false,"修改异常");	
			}
		}
	}

	@Override
	public List<Auth> getAllMenus() {
		return authMgMapper.getAllAccountAuth();
	}

}
