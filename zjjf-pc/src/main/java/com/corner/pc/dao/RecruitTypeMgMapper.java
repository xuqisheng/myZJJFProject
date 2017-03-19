package com.corner.pc.dao;

import java.util.List;

import com.corner.pc.beans.RecruitType;
import com.corner.pc.beans.vo.RecruitTypeVo;

public interface RecruitTypeMgMapper{
	public List<RecruitType> getAllRecruitType();
	
	public List<RecruitTypeVo> getRecruitInfo();
}
