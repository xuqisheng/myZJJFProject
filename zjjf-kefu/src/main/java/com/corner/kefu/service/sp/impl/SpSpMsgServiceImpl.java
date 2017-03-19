package com.corner.kefu.service.sp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SpPushMsg;
import com.corner.core.beans.SpPushMsgType;
import com.corner.core.dao.SpPushMsgMapper;
import com.corner.kefu.dao.sp.SpSpMsgMgMapper;
import com.corner.kefu.service.sp.SpSpMsgService;
import com.corner.kefu.utils.PCSavePushMsgUtil;

@Service
public class SpSpMsgServiceImpl implements SpSpMsgService {

	@Autowired 
	SpSpMsgMgMapper spSpMsgMgMapper;
	@Autowired
	SpPushMsgMapper spPushMsgMapper;
	@Autowired
	PCSavePushMsgUtil pcSavePushMsgUtil;
	@Override
	public List<SpPushMsg> getSpPushMsgList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spSpMsgMgMapper.getSpPushMsgList(map);
	}
	@Override
	public SpPushMsg getSpPushMsgById(String id) {
		// TODO Auto-generated method stub
		return spPushMsgMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<SpPushMsgType> getSpPushMSGList() {
		// TODO Auto-generated method stub
		return spSpMsgMgMapper.getSpPushMSGList();
	}
	@Override
	public void addSpPushMsg(Map<String, Object> map) {
		map.put("spPushMsgMapper", spPushMsgMapper);
		map.put("spSpMsgMgMapper", spSpMsgMgMapper);
		// TODO Auto-generated method stub
		pcSavePushMsgUtil.savePushMsg(map);
	}
	@Override
	public void removeMsgBySpPushMsgId(String id) {
		// TODO Auto-generated method stub
		spSpMsgMgMapper.removeMsgBySpPushMsgId(id);
	}
	
}
