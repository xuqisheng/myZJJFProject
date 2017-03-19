package com.corner.scms.controller.sp;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.ReminderRo;
import com.corner.scms.beans.vo.ReminderVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sp.ReminderService;
@Controller
@RequestMapping("/supplier/reminder")
public class ReminderController extends ScmsBaseWebController{
	private static final Logger logger = LoggerFactory.getLogger(ReminderController.class);
	 
	@Autowired
	ReminderService reminderService;
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
	@ResponseBody
	public Object getAllReminderByParam(Model model,HttpServletRequest request,ReminderRo reminderRo,Integer pageIndex){
		try {
			Supplier supplier = this.getCurrentUser(Supplier.class, request);
			if(supplier==null){
				return null;
			}
			reminderRo.setSpId(supplier.getId());
			reminderRo.setPageIndex(pageIndex+1);
			Pager<ReminderVo> pager = reminderService.getAllReminderByParam(reminderRo);
			return pager;
		} catch (Exception e) {
			logger.error("",e);
			return "";
		}
	}
}
