package com.corner.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.auth.beans.Department;
import com.corner.auth.beans.Position;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.JobRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.dao.DepartmentMapper;
import com.corner.auth.dao.PositionMapper;
import com.corner.auth.dao.mg.PositionMgMapper;
import com.corner.auth.service.PositionService;
import com.corner.auth.utils.BeanUtil;
@Service
public class PositionServiceImpl implements PositionService {
	@Autowired
	PositionMgMapper positionMgMapper;
	@Autowired
	PositionMapper positionMapper;
	@Autowired
	DepartmentMapper departmentMapper;

	@Override
	public Pager<Position> getPositionList(JobRo jobRo) {
		List<Position> positions = positionMgMapper.getPositionList(jobRo);
		Integer count = positionMgMapper.getPositionListCount(jobRo);
		return new Pager<Position>(count , positions);
	}
	@Override
	public ModelMsg savePosition(JobRo jobRo) {
		Position position = BeanUtil.toObject(Position.class, jobRo);
		if(jobRo.getId() == null || jobRo.getId() == 0){
			int result = positionMapper.insertSelective(position);
			if(result == 0)
				return new ModelMsg(false, "新增失败");
			return new ModelMsg(true, "新增成功");
		}else{
			int result = positionMapper.updateByPrimaryKeySelective(position);
			if(result == 0)
				return new ModelMsg(false, "新增失败");
			return new ModelMsg(true, "新增成功");
		}
	}

	@Override
	public ModelMsg saveDepartment(JobRo jobRo) {
		Department position = BeanUtil.toObject(Department.class, jobRo);
		if(jobRo.getId() == null || jobRo.getId() == 0){
			int result = departmentMapper.insertSelective(position);
			if(result == 0)
				return new ModelMsg(false, "新增失败");
			return new ModelMsg(true, "新增成功");
		}else{
			int result = departmentMapper.updateByPrimaryKeySelective(position);
			if(result == 0)
				return new ModelMsg(false, "新增失败");
			return new ModelMsg(true, "新增成功");
		}
	}
	@Override
	public ModelMsg selectById(Integer id, Integer type) {
		Object object = new Object();
		if(type == 0){
			
		}else if(type == 1){	//部门
			object = departmentMapper.selectByPrimaryKey(id);
		}else if(type == 2){	//职位
			object = positionMapper.selectByPrimaryKey(id);
		}else{
			
		}
		if(object == null)
			return new ModelMsg(false, "未找到对应数据");
		return new ModelMsg(true, "未找到对应数据" , BeanUtil.toMapHaveNull(object));
	}
}
