package com.corner.account.controller;

import com.corner.account.beans.ro.SpOrderMgCondition;
import com.corner.account.service.PublicService;
import com.corner.account.service.SpOrderInfoService;
import com.corner.core.beans.Accounter;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value="/account/sporderinfoctl")
public class SpOrderInfoController extends AccountBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(SpOrderInfoController.class);
	
	@Autowired
	PublicService publicService;
	
	@Autowired
	SpOrderInfoService spOrderInfoService;
	
	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, SpOrderMgCondition command) {
		logger.info("获取订单列表");
		Pager<SpOrderInfo> pager = spOrderInfoService.getSpOrderInfoList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	@RequestMapping(value = "/scmsList.do")
	public String scmsList(HttpServletRequest request, Model model, SpOrderMgCondition command){
		model.addAttribute("command" , command);
		return "ScmsOrder/scmsSpOrderInfo";
	}
	@RequestMapping(value = "/scmsListJson.do")
	@ResponseBody
	public Object scmsListJson(HttpServletRequest request, SpOrderMgCondition command){
		Pager<SpOrderInfo> pager = spOrderInfoService.getScmsSpOrderInfoList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	@RequestMapping(value = "/updateSpOrderCol1.do")
	@ResponseBody
	public Object updateSpOrderCol1(HttpServletRequest request , String remark , String id){
		Accounter accounter = getCurrentUser(request);
		if(accounter == null){
			return ResponseUtils.sendMsg(false , "操作失败，请登录后再尝试");
		}else if(StringUtil.stringIsNullOrEmpty(id)){
			return ResponseUtils.sendMsg(false , "操作失败，缺少必要参数");
		}
		return spOrderInfoService.updateSpOrderCol1(remark , id , accounter.getId());
	}
}
