package com.corner.account.service.impl;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.corner.account.beans.ro.LoginRo;
import com.corner.account.beans.vo.MenuTreeNode;
import com.corner.account.beans.vo.UserInSession;
import com.corner.account.config.SessionConfig;
import com.corner.account.dao.AuthorityMapper;
import com.corner.account.service.AuthorityService;
import com.corner.core.beans.Accounter;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.dao.AccounterMapper;
import com.corner.core.utils.safe.MD5;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	
	private static Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);

	@Autowired AuthorityMapper authorityMapper;
	
	@Autowired AccounterMapper accounterMapper;
	
	@Override
	public Accounter getUserByUserCredential(String credential) {
		return authorityMapper.getUserByUserCredential(credential);
	}

	@Override
	public Set<String> getRolesByUserId(String userId) {
		return authorityMapper.getRolesByUserId(userId);
	}

	@Override
	public Set<String> getAuthsByUserId(String userId) {
		return authorityMapper.getAuthsByUserId(userId);
	}

	@Override
	public ModelMsg dealSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model) {
		Accounter rightUser = authorityMapper.getUserByUserCredential(loginRo.getUserName());
		if(rightUser == null){
			return new ModelMsg(false,"未找到用户");
		}
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_SESSION_KEY, rightUser);
		Accounter accounter =new Accounter();
		accounter.setId(rightUser.getId());
		accounter.setLastIP(request.getRemoteAddr());
		accounter.setLastTime(new Date());
		int count = accounterMapper.updateByPrimaryKeySelective(accounter);
		if(count == 1){
			logger.info("用户登录成功,id:{},手机号：{},名称,{}",rightUser.getId(),rightUser.getMobile(),rightUser.getRealName());
			return new ModelMsg(true,"登录成功");			
		}else{
			return new ModelMsg(false,"数据异常");						
		}
	}

	@Override
	public ModelMsg updatePassword(String newPassword) {
		Accounter accounter =new Accounter();
		Accounter current = (Accounter)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
		accounter.setId(current.getId());
		accounter.setPassword(MD5.StringToMd5(newPassword));
		int count = accounterMapper.updateByPrimaryKeySelective(accounter);
		if(count == 1){
			return new ModelMsg(true,newPassword);			
		}else{
			return new ModelMsg(false,"密码修改异常");						
		}
	}

	@Override
	public MenuTreeNode getSubjectMenus() {
		Session session = SecurityUtils.getSubject().getSession();
		Accounter currentsubject = (Accounter)session.getAttribute(SessionConfig.USER_SESSION_KEY);
		if(currentsubject == null) return null;
		UserInSession userInSession = new UserInSession(currentsubject,authorityMapper);
		session.setAttribute(SessionConfig.USER_AUTHENTICATION, userInSession);
		return userInSession == null ? null : userInSession.getSubjectMenu() ;
	}



}
