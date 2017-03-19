package com.corner.account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.account.beans.ro.BillSettleCondition;
import com.corner.account.beans.vo.BillSettleVo;
import com.corner.account.config.AuthorityConfig;
import com.corner.account.service.BillSettleService;
import com.corner.account.service.PublicService;
import com.corner.account.service.SpOrderInfoService;
import com.corner.core.beans.Region;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;


@Controller
@RequestMapping(value="/account/billsettlectl")
public class BillSettleController extends AccountBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(BillSettleController.class);
	
	@Autowired
	PublicService publicService;
	
	@Autowired
	BillSettleService billSettleService;
	
	@Autowired
	SpOrderInfoService spOrderInfoService;
	
	@RequestMapping(value = "/BillSettleManagerPage.do")
	public String index(HttpServletRequest request, Model model) {
		logger.debug("用户进入核心权限管理界面BillSettleManagerPage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			List<Region> list = publicService.getRegions();
			model.addAttribute("areaList", list);
			return "BillSettle/SettleManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, BillSettleCondition command) {
		Pager<BillSettleVo> pager = billSettleService.getBillSettleList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	@RequestMapping(value = "/SettleOrderListManagerPage.do")
	public String settleOrderListManagerPage(String supplierId,HttpServletRequest request,Model model) {
		logger.debug("用户进入核心权限管理界面settleOrderListManagerPage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			//List<Region> list = publicService.getRegions();
			//model.addAttribute("areaList", list);
			model.addAttribute("supplierId", supplierId);
			return "BillSettle/SettleOrderListManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/SpList.do")
	@ResponseBody
	public Object spList(HttpServletRequest request, BillSettleCondition command) {
		Pager<BillSettleVo> pager = billSettleService.getBillSettleOrderList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	@RequestMapping(value = "/SettleOrderInfoManagerPage.do")
	public String settleOrderInfoManagerPage(HttpServletRequest request,String spOrderId, Model model) {
		logger.debug("用户进入核心权限管理界面");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			if(StringUtils.isEmpty(spOrderId)){
				model.addAttribute("message", "订单号不能为空");
				return "Alert";
			}else{
				spOrderInfoService.getSpOrderAllForPage(spOrderId,model);
				return "BillSettle/SettleOrderInfoManage";
			}
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
}
