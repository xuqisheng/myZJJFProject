package com.corner.salesman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.dao.GroupMapper;
import com.corner.salesman.model.Group;
import com.corner.salesman.service.GroupService;

/**
 * 用户组Service
 * @author 元宝
 * @version 2016-01-26
 */
@Service("groupService")
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupMapper groupMapper;

	@Override
	public Group findGroupInfo(String leaderId) throws Exception {
		return groupMapper.findGroupInfo(leaderId);
	}

	@Override
	public int getGroupUserTotal(String groupId) throws Exception {
		return groupMapper.getGroupUserTotal(groupId);
	}

}
