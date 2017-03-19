package com.corner.kefu.controller.scms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.scms.ScmsPurchaseRo;
import com.corner.kefu.beans.vo.ScOrderDetailVo;
import com.corner.kefu.beans.vo.ScOrderInfoVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.scms.PurchaseService;


@Controller
@RequestMapping(value="/scms/purchase")
//批发商采购订单


public class ScmsPurchaseController extends KefuBaseWebController{
	
	private static Logger logger = LoggerFactory.getLogger(ScmsPurchaseController.class);
	
	@Autowired
	private PurchaseService purchaseService;
	
	
	/**
	 * 
	* @Title: 批发商采购订单  首页
	* @param @param request
	* @param @param command
	* @return String   返回类型
	* @author 海灵子
	* @throws
	 */
	@RequestMapping(value = "/listPage.do")
	public String listPage(HttpServletRequest request,Model model,ScmsPurchaseRo condition) {
		Pager<ScOrderInfoVo> pager=this.purchaseService.findAllOrderInfo(condition);
		model.addAttribute("list", pager.getList());
		model.addAttribute("condition", condition);
		this.pageUtil(condition.getPageIndex(), pager.getTotalSize(), condition.getPageSize(), request, model);
		return "order/sp-purchase";
	}
	
	
	

	/**
	 * 
	* @Title: 批发商采购订单  查询 所有总订单的信息
	* @param @param request
	* @param @param command
	* @return Object   返回类型
	* @author 海灵子
	* @throws
	 */
	@RequestMapping(value = "/listdetail.do")
	public String listDetail(HttpServletRequest request, Model model,String orderId){
		ScOrderInfoVo vo=this.purchaseService.findOrderByOid(orderId);
		List<ScOrderDetailVo> list=this.purchaseService.findOrderDetail(orderId);
		model.addAttribute("vo",vo);
		model.addAttribute("list", list);
		return "order/sp-purchase-detail";
	}
	
	
	
}
