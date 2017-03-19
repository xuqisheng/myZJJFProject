package com.corner.kefu.controller;

import com.corner.core.beans.AppItemLabel;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.AppItemLabelRo;
import com.corner.kefu.beans.vo.AppItemLabelVo;
import com.corner.kefu.service.AppItemLabelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("kefu/appItemLabel")
public class AppItemLabelController extends KefuBaseWebController{
	private static final Logger logger = LoggerFactory.getLogger(AppItemLabelController.class);
	
	@Autowired
	AppItemLabelService appItemLabelService;
	
	@RequestMapping(value="toList")
	public String toList(){
		return "/system/labelList";
	}
	
	//标贴列表
	@RequestMapping(value="/getAllAppItemLabel")
	@ResponseBody
	public Object getAllAppItemLabel(AppItemLabelRo itemLabel,Model model,HttpServletRequest request){
		try {
			Pager<AppItemLabelVo> page = appItemLabelService.getAllAppItemLabel(itemLabel);
			return page;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//跳转页面
	@RequestMapping(value="/returnEditPage/{action}")
	public String returnEditPage(@PathVariable("action")Integer action,Model model,HttpServletRequest request){
		if(action == 2){
			String id = request.getParameter("id");
			if(StringUtil.stringIsNullOrEmpty(id)){
				return error("请求有误，请重试", model, request);
			}
			AppItemLabel itemLabel = appItemLabelService.getAppItemLabelById(id);
			if(itemLabel != null)
				model.addAttribute("itemLabel", itemLabel);
		}
		return "/system/labelEdit";
	}
	
	//添加标贴
	@RequestMapping(value="/addAppItemLabel")
	@ResponseBody
	public Object addAppItemLabel(AppItemLabel itemLabel){
		if(!checkParam(itemLabel))
			return ResponseUtils.sendMsg(false, "参数不完整，请重新操作");
		itemLabel.setId(StringUtil.getUUID());
		itemLabel.setAddTime(new Date());
		itemLabel.setUpdateTime(new Date());
		try {
			appItemLabelService.addAppItemLabel(itemLabel);
			return ResponseUtils.sendMsg(true, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "添加失败");
		}
		
	}

	//编辑标贴
	@RequestMapping(value="/updateAppItemLabel")
	@ResponseBody
	public Object updateAppItemLabel(AppItemLabel itemLabel){
		if(StringUtil.stringIsNullOrEmpty(itemLabel.getId()))
			return ResponseUtils.sendMsg(false, "请求有误，请重新操作");
		if(!checkParam(itemLabel))
			return ResponseUtils.sendMsg(false, "参数不完整，请重新操作");
		itemLabel.setUpdateTime(new Date());
		try {
			appItemLabelService.updateAppItemLabel(itemLabel);
			return ResponseUtils.sendMsg(true, "编辑成功");
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "编辑失败");
		}
	}

	private boolean checkParam(AppItemLabel itemLabel) {
		if(StringUtil.stringIsNullOrEmpty(itemLabel.getName(),itemLabel.getTagImg())){
			return false;
		}
		return true;
	}
	
	//删除or恢复
	@ResponseBody
	@RequestMapping(value="/updateDelOrReco/{id}/{isDelete}")
	public Object updateDelOrReco(@PathVariable("id")String id,@PathVariable("isDelete")Boolean isDelete){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("isDelete", isDelete);
		try {
			appItemLabelService.delOrReco(map);
			return ResponseUtils.sendMsg(true,"操作成功");
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false,"操作失败");
		}
	}
	
	//停用or启用
	@ResponseBody
	@RequestMapping(value="/updateStopOrStart/{id}/{status}")
	public Object updateStopOrStart(@PathVariable("id")String id,@PathVariable("status")Byte status){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("status", status);
		try {
			appItemLabelService.delOrReco(map);
			return ResponseUtils.sendMsg(true,"操作成功");
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false,"操作失败");
		}
	}
	
}
