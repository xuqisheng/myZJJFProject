package com.corner.kefu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.ReminderRo;
import com.corner.kefu.beans.vo.ReminderVo;
import com.corner.kefu.dao.ReminderMgMapper;
import com.corner.kefu.service.ReminderService;
@Service
public class ReminderServiceImpl implements ReminderService {

	@Autowired 
	ReminderMgMapper reminderMgMapper;
	@Override
	public Pager<ReminderVo> getAllReminderByParam(ReminderRo reminderRo) {
		// TODO Auto-generated method stub
		List <ReminderVo> reminderVoList = reminderMgMapper.getAllReminderByParam(reminderRo);
		int num = reminderMgMapper.getAllReminderByParamCount(reminderRo);
		return new Pager<>(num, reminderVoList);
	}

}
