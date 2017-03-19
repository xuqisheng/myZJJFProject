package com.corner.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.corner.auth.beans.Department;
import com.corner.auth.beans.Position;

@Service
public interface PublicService {
	List<Department> getAllDepartmentList();
	
	List<Position> getPositionListByDeptId(Integer deptId);
}
