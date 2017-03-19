package com.corner.scms.service.sp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.Store;
import com.corner.core.beans.StoreGroup;
import com.corner.core.beans.StoreGroupMember;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.StoreGroupMemberMapper;
import com.corner.core.dao.StoreMapper;
import com.corner.scms.dao.StoreMgMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sp.StoreGroupMemberService;
import com.corner.scms.service.sp.StoreService;


/**
 * 商铺Service
 * 
 * @author Howe at 2015年2月5日下午1:32:21
 * @Email itzihao@sina.com
 * @Desc
 */
@Service
public class StoreGroupMemberServiceImpl extends BaseServiceImpl implements StoreGroupMemberService{
	
	@Autowired
	private StoreGroupMemberMapper mapper;
	
	@Autowired
	private StoreMgMapper storeMapper;

	@Override
	public StoreGroupMember findMember(StoreGroupMember condition) {
		
		return this.storeMapper.findMember(condition);
	}

	@Override
	public void insertObject(StoreGroupMember condition) {
		this.mapper.insertSelective(condition);
	}

	@Override
	public void deleteObject(String id) {
		this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateObject(StoreGroupMember mem) {
		this.mapper.updateByPrimaryKeySelective(mem);
	}

	@Override
	public void deleteByGroupId(String id) {
		this.storeMapper.deleteByGroupId(id);
	}

	
	
	
}
