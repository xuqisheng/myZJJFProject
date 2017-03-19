package com.corner.auth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.auth.beans.Department;
import com.corner.auth.beans.Position;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.JobRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.service.PositionService;
import com.corner.auth.service.PublicService;
import com.corner.auth.utils.ResponseUtils;

@Controller
@RequestMapping(value="/auth/job")
public class JobController extends AuthBaseWebController{
	@Autowired
	PublicService publicService;
	@Autowired
	PositionService positionService;
	
	@RequestMapping(value = "/toJobIndex.do")
	public String toJobIndex(HttpServletRequest request,  Model model, JobRo jobRo) throws Exception{
		List<Department> departments = publicService.getAllDepartmentList();
		model.addAttribute("departmentList", departments);
		Pager<Position> positionList = positionService.getPositionList(jobRo);
		model.addAttribute("positionList", positionList.getList());
		model.addAttribute("jobRo", positionList);
		this.pageUtil(jobRo.getPageIndex(), positionList.getTotalSize(), jobRo.getPageSize(), request, model);
		return "/system/job";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/savePosition.do")
	public Object savePosition(JobRo jobRo) throws Exception{
		return positionService.savePosition(jobRo);
	}
	@ResponseBody
	@RequestMapping(value = "/saveDepartment.do")
	public Object saveDepartment(JobRo jobRo) throws Exception{
		return positionService.saveDepartment(jobRo);
	}
	@ResponseBody
	@RequestMapping(value = "/selectById.do")
	public Object selectById(Integer id , Integer type) throws Exception{
		ModelMsg modelMsg = positionService.selectById(id , type);
		return ResponseUtils.sendMsg(modelMsg.isSuccess() , modelMsg.isSuccess() ? modelMsg.getData() : modelMsg.getMessage());
	}
}
