package com.corner.auth.service;

import org.springframework.stereotype.Service;

import com.corner.auth.beans.Position;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.JobRo;
import com.corner.auth.beans.vo.Pager;

@Service
public interface PositionService {
	Pager<Position> getPositionList(JobRo jobRo);
	
	ModelMsg savePosition(JobRo jobRo);
	
	ModelMsg saveDepartment(JobRo jobRo);
	
	ModelMsg selectById(Integer id , Integer type);
}
