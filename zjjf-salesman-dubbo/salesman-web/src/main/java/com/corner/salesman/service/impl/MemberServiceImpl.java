package com.corner.salesman.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.rpc.salesman.model.Member;
import com.corner.salesman.mongo.dao.MemberDao;
import com.corner.salesman.service.api.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao memberDao;

	@Override
	public Member findByid(String id) {
		return memberDao.findById(id);
	}

	@Override
	public Member findOne(Map params) {
		return memberDao.findOne(params);
	}

	@Override
	public Member save(Member bean) {
		return memberDao.save(bean);
	}

	@Override
	public void update(String id, Object update) {
		//memberDao.update(id, update);
	}

}
