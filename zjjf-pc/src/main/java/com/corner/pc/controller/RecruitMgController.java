package com.corner.pc.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.pc.beans.Recruit;
import com.corner.pc.beans.RecruitType;
import com.corner.pc.beans.ro.RecruitCondition;
import com.corner.pc.beans.vo.ModelMsg;
import com.corner.pc.beans.vo.Pager;
import com.corner.pc.beans.vo.RecruitVo;
import com.corner.pc.service.RecruitMgService;
import com.corner.pc.service.RecruitTypeService;
import com.corner.pc.utils.ResponseUtils;
import com.corner.pc.utils.StringUtil;


@Controller
@RequestMapping(value="/pc/recruit")
public class RecruitMgController extends APCBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(RecruitMgController.class);
	
	@Autowired RecruitMgService recruitMgService;
	@Autowired RecruitTypeService recruitTypeService;

	@RequestMapping(value = "/RecruitMgPage.do")
	public String index(HttpServletRequest request, Model model) {
		logger.debug("用户进入核心角色管理界面");
		RecruitCondition command = new RecruitCondition();
		command.setIsDelete(false);
		command.setStatus((byte)1);
		command.setPageIndex(0);
		command.setPageSize(20);
		command.setSortName("a.ordid");
		command.setSortOrder("asc");
		//获取所有招聘类型
		List<RecruitType> recruitTypeList = recruitTypeService.getAllRecruitType();
		model.addAttribute("recruitTypeList",recruitTypeList);
		Pager<RecruitVo> pager = recruitMgService.getRecruitPageList(command);
		model.addAttribute("recruits", pager.getList());
		model.addAttribute("recruit", true);
		return "recruit";
	}
	
	@RequestMapping(value = "/Delete.do")
	@ResponseBody
	public Object delete(@RequestParam(value = "ids", required = true) String ids) {
		if(StringUtils.isEmpty(ids)){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			String[] array = ids.split(",");
			ModelMsg msg = recruitMgService.deleteObjects("Recruit",array);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}

	@RequestMapping(value = "/Update.do")
	@ResponseBody
	public Object Update(HttpServletRequest request, Recruit recruit) {
		if(recruit.getId() == null){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else if(recruit.getRecruitTypeId() == null || recruit.getRecruitTypeId() == -1){
			return ResponseUtils.sendMsg(false,"请选择招聘类型");
		}else if (StringUtil.stringIsNullOrEmpty(recruit.getPostName())) {
			return ResponseUtils.sendMsg(false, "请填写招聘职位名称");
		}else if (StringUtil.stringIsNullOrEmpty(request.getParameter("ueditorContent"))) {
			return ResponseUtils.sendMsg(false, "请填写招聘内容");
		}else{
			recruit.setContent(request.getParameter("ueditorContent"));
			recruit.setUpdateTime(new Date());
			recruit.setAuther("admin");
			ModelMsg msg = recruitMgService.updateByPrimaryKeySelective(recruit);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}

	@RequestMapping(value = "/Add.do")
	@ResponseBody
	public Object add(HttpServletRequest request, Recruit recruit) {
		if(recruit.getRecruitTypeId() == null || recruit.getRecruitTypeId() == -1){
			return ResponseUtils.sendMsg(false,"请选择招聘类型");
		}else if (StringUtil.stringIsNullOrEmpty(recruit.getPostName())) {
			return ResponseUtils.sendMsg(false, "请填写招聘职位名称");
		}else if (StringUtil.stringIsNullOrEmpty(request.getParameter("ueditorContent"))) {
			return ResponseUtils.sendMsg(false, "请填写招聘内容");
		}else{
			recruit.setContent(request.getParameter("ueditorContent"));
			recruit.setAddTime(new Date());
			recruit.setUpdateTime(new Date());
			recruit.setAuther("admin");
			ModelMsg msg = recruitMgService.addObject(recruit);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"新增成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}
	
	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, RecruitCondition command) {
		Pager<RecruitVo> pager = recruitMgService.getRecruitPageList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
}
