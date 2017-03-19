package com.corner.kefu.service;

import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.ReminderRo;
import com.corner.kefu.beans.vo.ReminderVo;

public interface ReminderService {

	
	public Pager<ReminderVo> getAllReminderByParam(ReminderRo reminderRo);
}
