package com.corner.task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.task.beans.Admin;
import com.corner.task.beans.ro.LoginRo;
import com.corner.task.dao.AdminMapper;
import com.corner.task.dao.AuthorityMapper;
import com.corner.task.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	private static Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);

	@Autowired
	AuthorityMapper authorityMapper;
	@Autowired
	AdminMapper adminMapper;
	@Override
	public Admin getUserByAdminCredential(LoginRo loginRo) {
		return authorityMapper.getUserByAdminCredential(loginRo);
	}
}
