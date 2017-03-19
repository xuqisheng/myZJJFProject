package com.corner.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.corner.auth.beans.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.auth.service.PublicService;
import com.corner.auth.utils.ResponseUtils;

@Controller
@RequestMapping(value="/auth/public")
public class PublicController extends AuthBaseWebController{

	@Autowired
	PublicService publicService;
	

	@RequestMapping(value = "/getPositionListByDeptId.do")
	public @ResponseBody ResponseVo getPositionListByDeptId(HttpServletRequest request, Model model, Integer deptId) throws Exception{
		return ResponseUtils.sendMsg(true, publicService.getPositionListByDeptId(deptId));
	}
}
