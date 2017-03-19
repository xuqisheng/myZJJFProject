package com.corner.kefu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corner.core.beans.SpGroup;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.ReminderRo;
import com.corner.kefu.beans.vo.ReminderVo;
import com.corner.kefu.service.ReminderService;
import com.corner.kefu.service.sp.SpPlantItemService;
@Controller
@RequestMapping("/kefu/reminder")
public class ReminderController extends KefuBaseWebController{
	private static final Logger logger = LoggerFactory.getLogger(ReminderController.class);
	 
	@Autowired
	ReminderService reminderService;
	@Autowired
	SpPlantItemService spPlantItemService;
	//
	/**
	 * 获取所有催单的订单
	* @Title
	* @Description: TODO 
	* @param @param model
	* @param @param request
	* @param @param reminderRo
	* @param @return
	* @2016年4月11日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("/getAllReminderByParam.do")
	public String getAllReminderByParam(Model model,HttpServletRequest request,ReminderRo reminderRo){
		model.addAttribute("reminderRo", reminderRo);
//		String pageIndex1 = request.getParameter("pageIndex");
//		Integer pageIndex = 1;
//		if(pageIndex1 != null && !("").equals(pageIndex1)){
//			pageIndex = Integer.parseInt(pageIndex1);
//		}
		if(!StringUtil.stringIsNullOrEmpty(reminderRo.getSupplierNameOrTel())){
			reminderRo.setSupplierNameOrTel(reminderRo.getSupplierNameOrTel().trim());
		}
		if(!StringUtil.stringIsNullOrEmpty(reminderRo.getStoreNameOrTel())){
			reminderRo.setStoreNameOrTel(reminderRo.getStoreNameOrTel().trim());
		}
		//查出所有定格
		List<SpGroup> spGroupList = spPlantItemService.getAllSpGroup();
		model.addAttribute("spGroupList",spGroupList);
		try {
			Pager<ReminderVo> pager = reminderService.getAllReminderByParam(reminderRo);
			if(pager != null){
				model.addAttribute("reminderVoList", pager.getList());
				pageUtil(reminderRo.getPageIndex(), pager.getTotalSize(), reminderRo.getPageSize(), request, model);
			}
			return "/order/shop-reminder";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("",e);
			return error("出错了！", model, request);
		}
	}
}
