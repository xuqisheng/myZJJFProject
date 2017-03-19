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
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.beans.ro.SpCommentParamterRo;
import com.corner.scms.beans.vo.SpCommentVo;
import com.corner.scms.beans.vo.SpCommentVo1;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sp.SpCommentService;

@RequestMapping("/supplier/spComment")
@Controller
public class PcCommentController extends ScmsBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(PcCommentController.class);
	@Autowired
	SpCommentService spCommentService;
	

	/**
	 * 工作台获得批发商评论 
	* @Title
	* @Description: TODO 
	* @param @param paramter
	* @param @param request
	* @param @param model
	* @param @return
	* @2016年4月16日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("getSpComment.do")
	public Object getSpComment(SpCommentParamterRo paramter,HttpServletRequest request,Model model){
		Supplier supplier = getCurrentUser(Supplier.class, request);
		if(supplier == null){
			return ResponseUtils.sendMsg(false);
		}
		paramter.setSpId(supplier.getId());
		try {
			SpCommentVo1 spCommentVo1 =  spCommentService.getSpComment(paramter);
			if(spCommentVo1 != null){
				return ResponseUtils.sendMsg(true,spCommentVo1);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
	/**
	 * 工作台获得批发商综合评分 
	* @Title
	* @Description: TODO 
	* @param @param request
	* @param @param model
	* @param @return
	* @2016年4月16日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("getSumSpComment.do")
	public Object getSumSpComment(HttpServletRequest request,Model model){
		SpCommentParamterRo paramter = new SpCommentParamterRo();
		Supplier supplier = getCurrentUser(Supplier.class, request);
		if(supplier == null){
			return ResponseUtils.sendMsg(false);
		}
		paramter.setSpId(supplier.getId());
		try {
			SpCommentVo spCommentVo =  spCommentService.getSumSpComment(paramter);
			if(spCommentVo != null){
				return ResponseUtils.sendMsg(true,spCommentVo);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
}
