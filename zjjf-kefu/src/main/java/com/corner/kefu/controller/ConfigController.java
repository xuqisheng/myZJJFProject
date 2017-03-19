package com.corner.kefu.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.SendTimeConfig;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.ConfigRo;
import com.corner.kefu.beans.ro.SendTimeConfigRo;
import com.corner.kefu.beans.vo.ConfigVo;
import com.corner.kefu.beans.vo.SendTimeConfigVo;
import com.corner.kefu.service.ConfigService;
@Controller
@RequestMapping("/keFu/systemConfig")
public class ConfigController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);
	@Autowired
	ConfigService configService; 
	
	
	@RequestMapping("/saveDeliveryConfig.do")
	@ResponseBody
	public Object saveDeliveryConfig(HttpServletRequest request) {
		String scopTypeStr = request.getParameter("scopType");
		if(StringUtils.isEmpty(scopTypeStr)){
			return ResponseUtils.sendMsg(false, "请选择定格类型!");
		}
		String discountStr = request.getParameter("discount");
		String[] spGroupIdArr = request.getParameterValues("spGroupIdArr");
		String[] discountArr = request.getParameterValues("discountArr");
		
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("scopTypeStr", scopTypeStr);
		paramMap.put("discountStr", discountStr);
		paramMap.put("spGroupIdArr", spGroupIdArr);
		paramMap.put("discountArr", discountArr);
		ModelMsg msg = configService.saveDeliveryConfig(paramMap);
		if(msg.isSuccess()){
			return ResponseUtils.sendMsg(true);
		}
		return ResponseUtils.sendMsg(false,msg.getMessage());
	}
	
	
	/**
	 * 
	* @Title: toDeliveryConfig 
	* @Description:
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toDeliveryConfig.do")
	public String  toDeliveryConfig() {
		return "system/distribute-way";
	}
	
	
	
	
	/**
	 * 获取所有系统配置信息 
	* @Title
	* @Description: TODO 
	* @param @param systemConfigRo
	* @param @param request
	* @param @param model
	* @param @return
	* @2016年4月14日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("/getAllConfig.do")
	public String getAllConfig(ConfigRo configRo,HttpServletRequest request,Model model ){
		Date endtime = configRo.getEndTime();
		if(!StringUtil.stringIsNullOrEmpty(configRo.getConfigName())){
			configRo.setConfigName(configRo.getConfigName().trim());
		}
		if(configRo.getEndTime()!=null){
			Date endTime = configRo.getEndTime();
			endTime = DateUtils.addDays(endTime, 1);
			configRo.setEndTime(endTime);
		}
		try {
			Pager<ConfigVo> pager = configService.getAllConfig(configRo);
			if(pager!= null){
				configRo.setEndTime(endtime);
				model.addAttribute("systemConfigVoList", pager.getList());
				model.addAttribute("configRo", configRo);
				pageUtil(configRo.getPageIndex(), pager.getTotalSize(), configRo.getPageSize(), request, model);
			}
			return "/system/parm-config";
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
		}
	}
	
	//回到列表页面
	@RequestMapping(value="toListPage")
	public String toListPage(){
		return "/appSetting/sendTimeList";
	}
	
	//送货时间配置列表
	@RequestMapping(value="getSendTimeConfig")
	@ResponseBody
	public Object getSendTimeConfig(SendTimeConfigRo sendTimeConfigRo){
		try {
			Pager<SendTimeConfigVo> pager = configService.getSendTimeConfig(sendTimeConfigRo);
			return pager;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//返回编辑页面
	@RequestMapping(value="toEditPage/{action}")
	public String toEditPage(@PathVariable Integer action,Model model,HttpServletRequest request){
		if(action != null && action==2){
			String id = request.getParameter("id");
			SendTimeConfig sendTimeConfig = configService.getSendTimeConfigById(Byte.parseByte(id));
			if(sendTimeConfig != null){
				model.addAttribute("sendTimeConfig", sendTimeConfig);
			}
		}
		return "/appSetting/sendTimeEdit";
	}
	
	//添加送货时间配置
	@RequestMapping(value="addSendTimeConfig")
	@ResponseBody
	public Object addSendTimeConfig(SendTimeConfig sendTimeConfig){
		if(StringUtil.stringIsNullOrEmpty(sendTimeConfig.getName()))
			return ResponseUtils.sendMsg(false, "参数不完整");
		sendTimeConfig.setAddTime(new Date());
		sendTimeConfig.setUpdateTime(new Date());
		try {
			boolean flag = configService.addSendTimeConfig(sendTimeConfig);
			if(flag){
				return ResponseUtils.sendMsg(true, "添加成功");
			}else{
				return ResponseUtils.sendMsg(false, "添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "程序异常，请联系技术人员");
		}
	}
	
	//修改送货时间配置
	@RequestMapping(value="updateSendTimeConfig")
	@ResponseBody
	public Object updateSendTimeConfig(SendTimeConfig sendTimeConfig){
		if(sendTimeConfig.getId()== null || StringUtil.stringIsNullOrEmpty(sendTimeConfig.getName())){
			return ResponseUtils.sendMsg(false, "参数不完整");
		}
		sendTimeConfig.setUpdateTime(new Date());
		try {
			boolean flag = configService.updateSendTimeConfig(sendTimeConfig);
			if(flag){
				return ResponseUtils.sendMsg(true, "编辑成功");
			}else{
				return ResponseUtils.sendMsg(false, "编辑失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "程序异常，请联系技术人员");
		}
		
	}
	
	//删除送货时间配置
	@ResponseBody
	@RequestMapping(value="/updateDelOrReco/{id}/{isDelete}")
	public Object updateDelOrReco(@PathVariable("id")String id,@PathVariable("isDelete")Boolean isDelete){
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("isDelete", isDelete);
		try {
			configService.delOrReco(map);
			return ResponseUtils.sendMsg(true,"操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"操作失败");
		}
	}
	
}
