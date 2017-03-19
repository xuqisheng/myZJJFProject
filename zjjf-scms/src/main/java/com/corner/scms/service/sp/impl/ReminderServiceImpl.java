package com.corner.scms.service.sp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.ReminderRo;
import com.corner.scms.beans.vo.ReminderVo;
import com.corner.scms.dao.ReminderMgMapper;
import com.corner.scms.service.sp.ReminderService;
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
//	@Override
//	public int getReminderByOrderIdAndSpId(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return reminderMgMapper.getReminderByOrderIdAndSpId(map);
//	}
//	@Override
//	public void updateReminderStatusByOrderIdAndSpId(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		reminderMgMapper.updateReminderStatusByOrderIdAndSpId(map);
//	}

}
