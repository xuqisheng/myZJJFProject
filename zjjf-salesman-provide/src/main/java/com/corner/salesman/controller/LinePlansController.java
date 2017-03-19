package com.corner.salesman.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.rpc.salesman.api.service.LinePlansService;
import com.corner.rpc.salesman.api.service.VisitHisRecordService;
import com.corner.rpc.salesman.model.LinePlans;
import com.corner.rpc.salesman.model.VisitHisRecord;
import com.corner.salesman.common.persistence.AppPage;
import com.corner.salesman.common.utils.AppJson;
import com.corner.salesman.common.utils.Encodes;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.commons.utils.DateUtils;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping("/mobile/line")
public class LinePlansController {
	
	private static final Logger logger = LoggerFactory.getLogger(LinePlansController.class);
	
	@Autowired
	private LinePlansService linePlansService;
	@Autowired
	private VisitHisRecordService visitHisRecordService;
	
	
	/**
	 * 根据用户ID获取拜访线路列表
	 * @param userId
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getVisitLineByUserId"})
	public Object getVisitLineByUserId(String salesmanId,HttpServletRequest request){
		Json json = new Json();
			try {
				 if(StringUtils.isBlank(salesmanId)){
					 json.setMsg("业务员ID不能为空！");
					 json.setSuccess(false);
					 json.setData("500");
					 return json;
				 }
				 
				 AppJson appJson = new AppJson();	
				 //声明一个list
				 List<HashMap<String,Object>> visitLinelist =linePlansService.getVisitLineByUserId(salesmanId);
				 //接收这个list的数据
			   	 appJson.setList(visitLinelist);
			   	 //显示数据
			   	 json.setData(appJson);
				 json.setMsg("获取拜访线路列表成功！");
				 //返回为true还是false
				 json.setSuccess(true);
			} catch (Exception e) {				 
				 json.setMsg("获取拜访线路列表异常！");
				 json.setSuccess(false);
				 json.setData("500");
			}
		return json;
		
	}
	
	/**
	 * 根据用户ID获取拜访计划列表
	 * @param userId
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyVisitPlansList"})
	public Object getMyVisitPlansList(LinePlans lineVo,HttpServletRequest request){
		Json json = new Json();
			try {
				 AppJson appJson = new AppJson();	
				 //声明一个list
				 List<HashMap<String,Object>> visitLinelist =linePlansService.getMyVisitPlansList(lineVo);
				 //接收这个list的数据
			   	 appJson.setList(visitLinelist);
			   	 //显示数据
			   	 json.setData(appJson);
				 json.setMsg("获取拜访计划列表成功！");
				 //返回为true还是false
				 json.setSuccess(true);
			} catch (Exception e) {				 
				 json.setMsg("获取拜访计划列表异常！");
				 json.setSuccess(false);
				 json.setData("500");
			}
		return json;
	}
	
	/**
	 * 根据用户ID获取拜访计划列表
	 * @param userId
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"updateVisitPlans"})
	public Object updateVisitPlans(LinePlans lineVo,HttpServletRequest request){
		Json json = new Json();
		try {
			 String salesmanId = lineVo.getSalesmanId();
			 String lineStr = lineVo.getLineStr();
			 if(StringUtils.isBlank(salesmanId)){
				 json.setMsg("业务员ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 if(StringUtils.isBlank(lineStr)){
				 json.setMsg("拜访计划不能为空！");
				 json.setSuccess(false);
				 json.setData("500");
				 return json;
			 }
			 //对字符串进行转码处理
			 lineStr = Encodes.urlDecode(lineStr);
			 lineVo.setLineStr(lineStr);
			 
			 linePlansService.updateVisitPlans(lineVo);
			 json.setMsg("拜访计划保存成功！");
			 json.setSuccess(true);
		} catch (Exception e) {				 
			 json.setMsg("拜访计划保存异常！");
			 json.setSuccess(false);
			 json.setData("500");
		}
		return json;
	}
	
	/**
	 * 获取今日拜访计划列表
	 * @param visitHisRecord
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getTodayVisitPlansList"})
	public Object getTodayVisitPlansList(VisitHisRecord visitHisRecord, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 //logger.info("获取商铺列表条件为：{}", JSON.toJSONString(shopInfo));
			 String deptId = visitHisRecord.getDeptId();
			 if(StringUtils.isBlank(deptId)){
				 json.setMsg("部门ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 Page<VisitHisRecord> page = visitHisRecordService.getTodayVisitPlansList(new Page<VisitHisRecord>(request, response), visitHisRecord);
			 AppPage<VisitHisRecord> target = new AppPage<VisitHisRecord>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取今日拜访计划列表成功！");
			 json.setSuccess(true);
			//logger.info("获取商铺列表信息为：{}", JSON.toJSONString(json));
		} catch (Exception e) {
			 json.setMsg("获取今日拜访计划列表异常！");
			 json.setSuccess(false);
			 json.setCode("500");
			logger.info("获取今日拜访计划列表异常：{}",e.getMessage());
		}
		return json;
	}
}
