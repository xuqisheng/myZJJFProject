package com.corner.salesman.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.corner.salesman.common.utils.EmojiFilter;
import com.corner.salesman.common.utils.Encodes;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.model.Proprietor;
import com.corner.salesman.service.ProprietorService;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping("/mobile/proprietor")
public class ProprietorController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProprietorController.class);

	@Autowired
	private ProprietorService proprietorService;	
	        
	/**
	 * 添加经营者信息
	 * @param proprietor
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addProprietor"})
	public Object addProprietorInfo(Proprietor proprietor, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 logger.info("提交经营者信息为：{}", JSON.toJSON(proprietor));
			 String name = proprietor.getName();
			 String post = proprietor.getPost();
			 String nativePlace = proprietor.getNativePlace();
			 name = Encodes.urlDecode(name);
			 proprietor.setName(name);
			 post = Encodes.urlDecode(post);
			 proprietor.setPost(post);
			 nativePlace = Encodes.urlDecode(nativePlace);
			 proprietor.setNativePlace(nativePlace);
			 
			 proprietorService.addProprietorInfo(proprietor);
			 json.setMsg("添加经营者信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 json.setMsg("添加经营者信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
			logger.info("添加经营者信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 修改经营者信息
	 * @param proprietor
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"updateProprietor"})
	public Object updateProprietor(Proprietor proprietor, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 logger.info("经营者信息为：{}", JSON.toJSON(proprietor));
			 String name = proprietor.getName();
			 String post = proprietor.getPost();
			 String nativePlace = proprietor.getNativePlace();
			 name = Encodes.urlDecode(name);
			 post = Encodes.urlDecode(post);
			 nativePlace = Encodes.urlDecode(nativePlace);
			 
			 if(StringUtils.isNotBlank(name)){
				 if(EmojiFilter.containsEmoji(name)){
					json.setMsg("负责任名称不能包含特殊字符！");
					json.setSuccess(false);
					json.setCode("500");
					return json;
				 }
			 }
			 if(StringUtils.isNotBlank(nativePlace)){
				 if(EmojiFilter.containsEmoji(nativePlace)){
					json.setMsg("籍贯不能包含特殊字符！");
					json.setSuccess(false);
					json.setCode("500");
					return json;
				 }
			 }
			 if(StringUtils.isNotBlank(post)){
				 if(EmojiFilter.containsEmoji(post)){
					json.setMsg("职位不能包含特殊字符！");
					json.setSuccess(false);
					json.setCode("500");
					return json;
				 }
			 }
			 proprietor.setName(name);
			 proprietor.setPost(post);
			 proprietor.setNativePlace(nativePlace);
			 
			 proprietorService.updateProprietorInfo(proprietor);
			 
			 //准备返回经营者ID给app端使用
			 Proprietor resultOjb = new Proprietor();
			 resultOjb.setId(proprietor.getId());
			 json.setData(resultOjb);
			 json.setMsg("修改经营者信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 json.setMsg("修改经营者信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
			logger.info("修改经营者信息异常：{}",e.getMessage());
		}
		return json;
	}
	
}
