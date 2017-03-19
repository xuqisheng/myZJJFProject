package com.corner.scms.dao;

import java.util.List;

import com.corner.scms.beans.ro.ReminderRo;
import com.corner.scms.beans.vo.ReminderVo;


public interface ReminderMgMapper {

	public List<ReminderVo> getAllReminderByParam(ReminderRo reminderRo);
	public int getAllReminderByParamCount(ReminderRo reminderRo);
	
//	public int getReminderByOrderIdAndSpId(Map<String, Object> map);
//	public void updateReminderStatusByOrderIdAndSpId(Map<String, Object> map);
}
