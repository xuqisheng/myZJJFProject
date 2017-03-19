package com.corner.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.account.beans.ro.MaOrderMgCondition;
import com.corner.account.beans.vo.MaOrderInfoMgVo;
import com.corner.account.config.AuthorityConfig;
import com.corner.account.config.SessionConfig;
import com.corner.account.service.MaOrderInfoService;
import com.corner.account.service.PublicService;
import com.corner.core.beans.Accounter;
import com.corner.core.beans.MaOrderInfo;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;


@Controller
@RequestMapping(value="/account/scmsorderinfoctl")
public class MaOrderInfoController extends AccountBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(MaOrderInfoController.class);
	
	@Autowired
	PublicService publicService;
	
	@Autowired
	MaOrderInfoService maOrderInfoService;
	
	@RequestMapping(value = "/toMaOrderInfoDetail.do")
	public String detail(HttpServletRequest request,String id, Model model) {
		logger.debug("准备进入toMaOrderInfoDetail");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			model.addAttribute("scOrderInfo" , maOrderInfoService.getScOrderInfoById(id));
			
			return "ScmsOrder/OrderDetailManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	@RequestMapping(value = "/toMaOrderInfoPage.do")
	public Object list(HttpServletRequest request, Model model) {
		logger.debug("准备进入toMaOrderInfoPage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			return "ScmsOrder/OrderInfoManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	@RequestMapping(value = "/toMaOrderInfoPageList.do")
	@ResponseBody
	public Object list(HttpServletRequest request, MaOrderMgCondition command) {
		logger.info("获取订单列表");
		Pager<MaOrderInfoMgVo> pager = maOrderInfoService.getMaOrderInfoList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	@RequestMapping(value = "/orderSubmit.do")
	@ResponseBody
	public Object orderSubmit(HttpServletRequest request, String id , String whTradeNo ,String whAcRemark) {
		if(StringUtil.stringIsNullOrEmpty(id , whTradeNo))
			return ResponseUtils.sendMsg(true , "缺少必要元素");
		logger.info("订单确认收款");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			Accounter user =(Accounter)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			MaOrderInfo maOrderInfo = maOrderInfoService.selectMaOrderInfoById(id);
			if(maOrderInfo.getWhPayStatus())
				return ResponseUtils.sendMsg(true , "该订单已付款");
			maOrderInfo.setWhAccountId(user.getId());
			maOrderInfo.setWhTradeNo(whTradeNo);
			maOrderInfo.setWhAcRemark(whAcRemark);
			ModelMsg modelMsg = maOrderInfoService.submitMaOrderInfo(maOrderInfo);
			return ResponseUtils.sendMsg(modelMsg.isSuccess() , modelMsg.getMessage());
		}else{
			return ResponseUtils.sendMsg(false , "您无权访问该界面");
		}
	}
	
	
}
