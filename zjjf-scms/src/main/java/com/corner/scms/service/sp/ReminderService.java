package com.corner.scms.service.sp;

import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.ReminderRo;
import com.corner.scms.beans.vo.ReminderVo;

public interface ReminderService {

	
	public Pager<ReminderVo> getAllReminderByParam(ReminderRo reminderRo);

//	public int getReminderByOrderIdAndSpId(Map<String, Object> map);
//
//	public void updateReminderStatusByOrderIdAndSpId(Map<String, Object> map);
}
