package com.corner.pc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.pc.beans.RecruitType;
import com.corner.pc.beans.vo.RecruitTypeVo;
import com.corner.pc.dao.RecruitTypeMgMapper;
import com.corner.pc.service.RecruitTypeService;
@Service
public class RecruitTypeServiceImpl extends ABaseServiceImpl implements RecruitTypeService {
	
	@Autowired
	RecruitTypeMgMapper recruitTypeMgMapper; 

	@Override
	public List<RecruitType> getAllRecruitType() {
		// TODO Auto-generated method stub
		return recruitTypeMgMapper.getAllRecruitType();
	}

	@Override
	public List<RecruitTypeVo> getRecruitInfo() {
		// TODO Auto-generated method stub
		return recruitTypeMgMapper.getRecruitInfo();
	}
	
}
