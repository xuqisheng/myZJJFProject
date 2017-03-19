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

import com.corner.rpc.shop.api.service.RegionService;
import com.corner.rpc.shop.model.Region;
import com.corner.salesman.model.Json;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping(value="/region")
public class RegionController {
	private static final Logger logger = LoggerFactory.getLogger(RegionController.class);

	@Autowired
	private RegionService regionService;
	
	/**
	 * 根据pId获取区域信息数据列表
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"queryRegionList"})
	public Object queryRegionList(Region region, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 Integer pid = region.getpId();
			 if(null == pid){
				json.setCode("500");
				json.setSuccess(false);
				json.setMsg("查询条件Pid不能为空！");
				return json;
			 }
			
			 List list = regionService.queryRegionList(region);
			 json.setList(list);
			 json.setSuccess(true);
			 json.setMsg("获取区域信息成功！");
		} catch (Exception e) {
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("获取区域信息异常！");
			logger.error("获取区域信息异常{}",e.getMessage());
		}
		return json;
	}
	
	
}
