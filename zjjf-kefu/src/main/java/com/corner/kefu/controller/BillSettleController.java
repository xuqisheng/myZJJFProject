package com.corner.kefu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.Region;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.kefu.beans.ro.BillSettleCondition;
import com.corner.kefu.beans.ro.CheckBillCondition;
import com.corner.kefu.beans.vo.BillSettleVo;
import com.corner.kefu.service.BillSettleService;
import com.corner.kefu.service.PublicService;
import com.corner.kefu.service.SpOrderInfoService;


@Controller
@RequestMapping(value="/account/billsettlectl")
public class BillSettleController extends KefuBaseWebController {
	
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
		List<Region> list = publicService.getRegions();
		model.addAttribute("areaList", list);
		return "BillSettle/SettleManage";
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
		model.addAttribute("supplierId", supplierId);
		return "BillSettle/SettleOrderListManage";
	}
	
	@RequestMapping(value = "/SpList.do")
	@ResponseBody
	public Object spList(HttpServletRequest request, CheckBillCondition command) {
		Pager<BillSettleVo> pager = billSettleService.getBillSettleOrderList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	@RequestMapping(value = "/SettleOrderInfoManagerPage.do")
	public String settleOrderInfoManagerPage(HttpServletRequest request,String spOrderId, Model model) {
		logger.debug("用户进入核心权限管理界面");
		if(StringUtils.isEmpty(spOrderId)){
			model.addAttribute("message", "订单号不能为空");
			return "Alert";
		}else{
			spOrderInfoService.getSpOrderAllForPage(spOrderId,model);
			return "BillSettle/SettleOrderInfoManage";
		}
	}
}
