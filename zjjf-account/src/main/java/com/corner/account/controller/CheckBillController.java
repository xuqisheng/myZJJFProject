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

import com.corner.account.beans.ro.CheckBillCondition;
import com.corner.account.beans.ro.SpOrderMgCondition;
import com.corner.account.beans.vo.CheckBillVo;
import com.corner.account.config.AuthorityConfig;
import com.corner.account.service.CheckBillService;
import com.corner.account.service.PublicService;
import com.corner.account.service.SpOrderInfoService;
import com.corner.core.beans.Region;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;

@Controller
@RequestMapping(value="/account/checkbillctl")
public class CheckBillController extends AccountBaseWebController {

	private static Logger logger = LoggerFactory.getLogger(CheckBillController.class);
	
	@Autowired
	CheckBillService checkBillService;
	
	@Autowired
	PublicService publicService;
	
	@Autowired
	SpOrderInfoService spOrderInfoService;
	
	@RequestMapping(value = "/CheckBillManagerPage.do")
	public String index(HttpServletRequest request, Model model) {
		logger.debug("用户进入核心权限管理界面");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			List<Region> list = publicService.getRegions();
			model.addAttribute("areaList", list);
			return "CheckBill/CheckBillManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, CheckBillCondition command) {
		Pager<CheckBillVo> pager = checkBillService.getCheckBillList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}

	
	@RequestMapping(value = "/CheckBillDetailManagerPage.do")
	public String checkBillDetailManagerPage(HttpServletRequest request,String supplierId, Model model) {
		logger.debug("用户进入核心权限管理界面");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			//List<Region> list = publicService.getRegions();
			//model.addAttribute("areaList", list);
			model.addAttribute("supplierId", supplierId);
			return "CheckBill/CheckBillOrderListManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/SpList.do")
	@ResponseBody
	public Object spList(HttpServletRequest request, CheckBillCondition command) {
		Pager<CheckBillVo> pager = checkBillService.getCheckBillDetailList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	
	@RequestMapping(value = "/CheckBillOrderInfoManagerPage.do")
	public String checkBillOrderInfoManagerPage(HttpServletRequest request,String spOrderId, Model model) {
		logger.debug("用户进入核心权限管理界面");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			if(StringUtils.isEmpty(spOrderId)){
				model.addAttribute("message", "订单号不能为空");
				return "Alert";
			}else{
				spOrderInfoService.getSpOrderAllForPage(spOrderId,model);
				return "CheckBill/CheckBillOrderInfoManage";
			}
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/UpdateAcStatusPass.do")
	@ResponseBody
	public Object updateAcStatusPass(HttpServletRequest request, SpOrderMgCondition command) {
		if(command ==null || StringUtils.isEmpty(command.getSpOrderIds())){
			return ResponseUtils.sendMsg(false,"参数错误");
		}else{			
			ModelMsg msg = spOrderInfoService.updateAcStatus(command);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}
}
