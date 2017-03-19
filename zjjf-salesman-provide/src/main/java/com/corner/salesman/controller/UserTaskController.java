package com.corner.salesman.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.corner.salesman.common.persistence.AppObject;
import com.corner.salesman.common.utils.DateUtils;
import com.corner.salesman.common.utils.IdGen;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.model.TaskCycle;
import com.corner.salesman.model.UserTask;
import com.corner.salesman.service.UserTaskService;

/**  
 * @描述 用户任务控制管理层
 * 创建时间：2015-1-28 下午1:17:27  
 * @author 元宝  
 * @version 2.2  
 */
@Controller
@RequestMapping("/mobile/task")
public class UserTaskController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserTaskController.class);

	@Autowired
	private UserTaskService userTaskService;
	
	/**
	 * 根据条件查询任务列表信息
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"list"})
	public Object findUserTaskList(UserTask userTask, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			String sessionId = request.getSession().getId();
			System.err.println("sessionId="+sessionId);
			userTask.setCycleIds(DateUtils.getChineseWeekday());
			
			logger.info("获取任务列表条件：", JSONArray.toJSONString(json));
			List<UserTask> taskList = userTaskService.findUserTaskList(userTask);
			AppObject<UserTask> resultObj = new AppObject<UserTask>();
			resultObj.setList(taskList);
			json.setData(resultObj);
			json.setSuccess(true);
			json.setMsg("获取任务列表成功！");
			logger.info("获取任务信息列表为：", JSONArray.toJSONString(json));
		} catch (Exception e) {
			logger.info("获取任务列表异常：{}", e.getMessage());
			json.setMsg("获取任务列表异常！");
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 添加任务信息
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addTask"})
	public Object addTask(UserTask userTask, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			logger.info("添加任务信息为：", JSONArray.toJSONString(json));
			String taskTopic = userTask.getTaskTopic();
			String userId = userTask.getUserId();
			String cycleIds = userTask.getCycleIds();
			String createBy = userTask.getCreateBy();
			//1、校验任务必填信息
			if(StringUtils.isBlank(taskTopic)){
				json.setMsg("任务名称不能为空！");
				json.setSuccess(false);
				return json;
			}
			if(StringUtils.isBlank(userId)){
				json.setMsg("任务分配人不能为空！");
				json.setSuccess(false);
				return json;
			}
			if(StringUtils.isBlank(cycleIds)){
				json.setMsg("任务汇报周期不能为空！");
				json.setSuccess(false);
				return json;
			}
			if(StringUtils.isBlank(cycleIds)){
				json.setMsg("任务汇报周期不能为空！");
				json.setSuccess(false);
				return json;
			}
			if(StringUtils.isBlank(createBy)){
				json.setMsg("任务创建人不能为空！");
				json.setSuccess(false);
				return json;
			}
			//2、设置任务ID
			userTask.setTaskId(IdGen.uuid());
			int resultNum = userTaskService.insertTaskInfo(userTask);
			if(resultNum>0){
				json.setMsg("添加任务信息成功！");
				json.setSuccess(true);
			}else{
				json.setMsg("添加任务信息失败！");
				json.setSuccess(false);
			}
			
		} catch (Exception e) {
			logger.info("添加任务信息列异常：{}", e.getMessage());
			json.setMsg("添加任务列表异常！");
			json.setSuccess(false);
		}
		logger.info("添加任务返回信息为：", JSONArray.toJSONString(json));
		
		return json;
	}
	
	/**
	 * 获取任务周期列表
	 * @param userTask
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getTaskCycle"})
	public Object getTaskCycleList(TaskCycle taskCycle, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			List<TaskCycle> cycleList = userTaskService.findTaskCycleList(taskCycle);
			AppObject<TaskCycle> resultObj = new AppObject<TaskCycle>();
			resultObj.setList(cycleList);
			json.setData(resultObj);
			json.setMsg("获取任务周期列表信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			logger.info("获取任务周期列表信息异常：{}", e.getMessage());
			json.setMsg("获取任务周期列表信息异常！");
			json.setSuccess(false);
		}
		logger.info("获取任务周期列表信息为：", JSONArray.toJSONString(json));
		
		return json;
	}
}
