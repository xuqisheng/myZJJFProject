package com.corner.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.auth.beans.Department;
import com.corner.auth.beans.Position;
import com.corner.auth.beans.ro.JobRo;
import com.corner.auth.dao.mg.DepartmentMgMapper;
import com.corner.auth.dao.mg.PositionMgMapper;
import com.corner.auth.service.PublicService;
@Service
public class PublicServiceImpl implements PublicService {
	@Autowired
	DepartmentMgMapper departmentMapper;
	@Autowired
	PositionMgMapper positionMapper;
	@Override
	public List<Department> getAllDepartmentList() {
		return departmentMapper.getAllDepartmentList();
	}
	@Override
	public List<Position> getPositionListByDeptId(Integer deptId) {
		JobRo jobRo = new JobRo();
		jobRo.setDeptId(deptId);
		jobRo.setPageSize(1000);
		return positionMapper.getPositionList(jobRo);
	}
}
