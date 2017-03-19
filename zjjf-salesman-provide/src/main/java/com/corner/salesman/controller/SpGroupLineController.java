package com.corner.salesman.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.rpc.salesman.api.service.SpGroupLineService;
import com.corner.rpc.salesman.model.SpGroupLine;
import com.corner.salesman.common.utils.AppJson;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.model.User;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping("/mobile/spgLine")
public class SpGroupLineController {
	private static final Logger logger = LoggerFactory.getLogger(SpGroupLineController.class);

	@Autowired
	private SpGroupLineService spgLineService;
	
	/**
	 * 获取用户路线列表
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMySpGroupList"})
	public Object getMySpGroupList(User user, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			String userId = user.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 AppJson appJson = new AppJson();
			 //1、定格列表
			 List<SpGroupLine> spgList = spgLineService.getMySpGroupList(userId);
			 appJson.setSpgList(spgList);
			 json.setData(appJson);
			 json.setSuccess(true);
			 json.setMsg("获取用户定格及路线列表成功！");
		} catch (Exception e) {
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("获取用户定格及路线列表异常！");
			logger.error("获取用户定格及路线列表异常{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 获取用户定格列表
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = {"getMySpGroupByUserId"})
	public Object getMySpGroupByUserId(User user, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			String userId = user.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 AppJson appJson = new AppJson();
			List<Map<String, String>> list = spgLineService.getMySpGroupHashMap(userId);
			appJson.setList(list);
			json.setData(appJson);
			json.setSuccess(true);
			json.setMsg("获取用户定格列表成功！");
		} catch (Exception e) {
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("获取用户定格列表异常！");
			logger.error("获取用户定格列表异常{}",e.getMessage());
		}
		return json;
	}*/
	
	
	/**
	 * 根据线路ID获取店铺列表
	 * @param lineVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getCustByLineId"})
	public Object getCustByLineId(SpGroupLine lineVO, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			String lineId = lineVO.getLineId();
			 if(StringUtils.isBlank(lineId)){
				 json.setMsg("线路ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 AppJson appJson = new AppJson();
			List<Map<String, String>> list = spgLineService.getCustHashMap(lineVO);
			appJson.setList(list);
			json.setData(appJson);
			json.setSuccess(true);
			json.setMsg("获取店铺列表成功！");
		} catch (Exception e) {
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("获取店铺列表异常！");
			logger.error("获取店铺列表异常{}",e.getMessage());
		}
		return json;
	}
	
	
	/**
	 * 根据条件获取业务员管理商铺的坐标列表
	 * @param lineVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getShopSiteList"})
	public Object getShopSiteList(SpGroupLine lineVO, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			String userId = lineVO.getUserId();
			 if(StringUtils.isBlank(userId)){
				 json.setMsg("用户ID不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			AppJson appJson = new AppJson();
			List<Map<String, String>> list = spgLineService.getShopSiteList(lineVO);
			appJson.setList(list);
			json.setData(appJson);
			json.setSuccess(true);
			json.setMsg("获取商铺的坐标列表成功！");
		} catch (Exception e) {
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("获取商铺的坐标列表异常！");
			logger.error("获取商铺的坐标列表异常{}",e.getMessage());
		}
		return json;
	}
}
