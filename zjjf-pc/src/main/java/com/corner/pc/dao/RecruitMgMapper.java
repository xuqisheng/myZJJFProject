package com.corner.pc.dao;

import java.util.List;

import com.corner.pc.beans.ro.RecruitCondition;
import com.corner.pc.beans.vo.RecruitVo;

public interface RecruitMgMapper{

	List<RecruitVo> getPageList(RecruitCondition command);

	int getPageListSize(RecruitCondition command);

}
