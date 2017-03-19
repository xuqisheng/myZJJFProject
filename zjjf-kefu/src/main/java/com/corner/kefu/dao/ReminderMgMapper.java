package com.corner.kefu.dao;

import java.util.List;

import com.corner.kefu.beans.ro.ReminderRo;
import com.corner.kefu.beans.vo.ReminderVo;

public interface ReminderMgMapper {

	public List<ReminderVo> getAllReminderByParam(ReminderRo reminderRo);
	public int getAllReminderByParamCount(ReminderRo reminderRo);
}
