package com.corner.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.corner.auth.beans.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.auth.beans.Region;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.RegionRo;
import com.corner.auth.beans.vo.Pager;
import com.corner.auth.beans.vo.RegionVo;
import com.corner.auth.service.AuthService;
import com.corner.auth.service.RegionService;
import com.corner.auth.utils.ResponseUtils;

@Controller
@RequestMapping(value="/auth/region")
public class RegionController extends AuthBaseWebController{

	@Autowired
	RegionService regionService;
	@Autowired
	AuthService authService;
	
	@RequestMapping(value = "/getRegionListPage.do")
	public String getRegionListPage(HttpServletRequest request,  Model model, RegionRo command){
		command.setSortName("id");
		command.setSortOrder("asc");
		Pager<RegionVo> pager = regionService.getRegionListPage(command);
		// 共同分页封装
		model.addAttribute("regionList", pager.getList());
		model.addAttribute("size", pager.getTotalSize());
		model.addAttribute("regionMgRo", command);
		pageUtil(command.getPageIndex(), pager.getTotalSize(), command.getPageSize(), request, model);
		return "/system/region";
	}

	@RequestMapping(value = "/updateRegion.do")
	public @ResponseBody ResponseVo updateRegion(HttpServletRequest request, Model model, Region command) throws Exception{
		ModelMsg modelMsg = regionService.updateRegion(command);
		return ResponseUtils.sendMsg(modelMsg.isSuccess(), modelMsg.getMessage());
	}
}
