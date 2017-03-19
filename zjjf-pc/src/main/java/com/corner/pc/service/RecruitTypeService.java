package com.corner.pc.service;

import java.util.List;

import com.corner.pc.beans.RecruitType;
import com.corner.pc.beans.vo.RecruitTypeVo;

public interface RecruitTypeService {
	public List<RecruitType> getAllRecruitType();
	
	public List<RecruitTypeVo> getRecruitInfo();
}
