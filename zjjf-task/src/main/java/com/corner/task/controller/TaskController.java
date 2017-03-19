package com.corner.task.controller;

import com.corner.task.beans.Admin;
import com.corner.task.beans.Task;
import com.corner.task.beans.ro.TaskRo;
import com.corner.task.beans.vo.Pager;
import com.corner.task.service.TaskService;
import com.corner.task.util.GTask;
import com.corner.task.util.ResponseUtils;
import com.corner.task.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/task/task")
public class TaskController extends TaskBaseWebController{

	@Autowired
	TaskService taskService;

	@RequestMapping(value = "/getTaskListPage.do")
	public String getRoleListPage(HttpServletRequest request,  Model model , TaskRo command){
		if(getCurrentUser(Admin.class, request) == null){
			return "../index";
		}
		Pager<Task> pager = taskService.getTaskListPage(command);
		// 共同分页封装
		model.addAttribute("list", pager.getList());
		model.addAttribute("size", pager.getTotalSize());
		model.addAttribute("command", command);
		pageUtil(command.getPageIndex(), pager.getTotalSize(), command.getPageSize(), request, model);
		return "/task/index";
	}
	
	@RequestMapping(value = "/getTaskById.do")
	@ResponseBody
	public Object getTaskById(HttpServletRequest request,String id) throws Exception{
		if(getCurrentUser(Admin.class, request) == null){
			ResponseUtils.sendMsg(false, "尚未登录请登录");
		}
		return ResponseUtils.sendMsg(true, taskService.getTaskById(id));
	}
	@RequestMapping(value = "/saveTask.do")
	@ResponseBody
	public Object getTaskById(HttpServletRequest request,Task task){
		if(getCurrentUser(Admin.class, request) == null){
			ResponseUtils.sendMsg(false, "尚未登录请登录");
		}
		try {
			if(StringUtil.stringIsNullOrEmpty(task.getId())){
				task.setId(StringUtil.getUUID());
				taskService.insertTask(task);
				return taskService.start(new GTask(task));
			}else{
				return taskService.updateTask(task);
			}
		} catch (Exception e) {
			logger.error("交易异常:{}" , e);
			return ResponseUtils.sendMsg(false, "交易异常：" + e.getMessage());
		}
	}
	@RequestMapping(value = "/onlyOneTime.do")
	@ResponseBody
	public Object onlyOneTime(HttpServletRequest request,String id) throws Exception{
		if(getCurrentUser(Admin.class, request) == null){
			ResponseUtils.sendMsg(false, "尚未登录请登录");
		}
		return taskService.onlyOneTime(new GTask(taskService.getTaskById(id)));
	}
	@RequestMapping(value = "/getTaskLogPage.do")
	@ResponseBody
	public Object getTaskLogPage(HttpServletRequest request,TaskRo command){
		if(getCurrentUser(Admin.class, request) == null){
			ResponseUtils.sendMsg(false, "尚未登录请登录");
		}
		command.setSortName("id");
		command.setSortOrder("desc");
		return ResponseUtils.sendPagination(taskService.getTaskLogPage(command));
	}
}
