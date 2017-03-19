package com.corner.pc.service;

import com.corner.pc.beans.Recruit;
import com.corner.pc.beans.ro.RecruitCondition;
import com.corner.pc.beans.vo.ModelMsg;
import com.corner.pc.beans.vo.Pager;
import com.corner.pc.beans.vo.RecruitVo;

public interface RecruitMgService {

	ModelMsg deleteObjects(String string, String[] array);

	ModelMsg updateByPrimaryKeySelective(Recruit recruit);

	ModelMsg addObject(Recruit recruit);
	
	Pager<RecruitVo> getRecruitPageList(RecruitCondition command);

}
