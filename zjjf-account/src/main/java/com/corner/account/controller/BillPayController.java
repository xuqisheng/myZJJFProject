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

import com.corner.account.beans.ro.BillPayCondition;
import com.corner.account.beans.vo.BillPayVo;
import com.corner.account.config.AuthorityConfig;
import com.corner.account.service.AcSheetService;
import com.corner.account.service.BillPayService;
import com.corner.account.service.PublicService;
import com.corner.account.service.SpOrderInfoService;
import com.corner.core.beans.Region;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;


@Controller
@RequestMapping(value="/account/billpayctl")
public class BillPayController extends AccountBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(BillPayController.class);
	
	@Autowired
	PublicService publicService;
	
	@Autowired
	BillPayService billPayService;
	
	@Autowired
	SpOrderInfoService spOrderInfoService;
	
	@Autowired
	AcSheetService acSheetService;
	
	@RequestMapping(value = "/BillPayManagerPage.do")
	public String index(HttpServletRequest request, Model model) {
		logger.debug("用户进入核心权限管理界面BillPayManagerPage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			List<Region> list = publicService.getRegions();
			model.addAttribute("areaList", list);
			return "BillPay/BillPayManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, BillPayCondition command) {
		Pager<BillPayVo> pager = billPayService.getBillPayOverViewList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	
	/**
	 * 待付款管理页面入口 type == "toPay"
	 * 付款中管理页面入口 type == "inSheet"
	 * 已付款管理页面入口 type == "alreadyPay"
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/BillPayOrderListManagerPage.do")
	public String toPayOrderListManagerPage(HttpServletRequest request,BillPayCondition command, Model model) {
		logger.debug("用户进入核心权限管理界面BillPayManagerPage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN) && !StringUtils.isEmpty(command.getType()) ){
			List<Region> list = publicService.getRegions();
			model.addAttribute("areaList", list);
			model.addAttribute("supplierId", command.getSupplierId());
			model.addAttribute("acStatus", command.getAcStatus());
			if("toPay".equals(command.getType())){
				return "BillPay/ToPayManage";
			}else if("inSheet".equals(command.getType())){
				return "BillPay/InSheetManage";
			}else if("alreadyPay".equals(command.getType())){
				return "BillPay/AlreadyPayManage";
			}else{
				model.addAttribute("message","您无权访问该界面");
				return "Alert";
			}
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	/**
	 * 待付款管理页面入口 type == "toPay"
	 * 付款中管理页面入口 type == "inSheet"
	 * 已付款管理页面入口 type == "alreadyPay"
	 * 页面列表接口
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping(value = "/PerStatusList.do")
	@ResponseBody
	public Object perStatusList(HttpServletRequest request, BillPayCondition command) {
		Pager<BillPayVo> pager = billPayService.getBillPayStatusList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
}
