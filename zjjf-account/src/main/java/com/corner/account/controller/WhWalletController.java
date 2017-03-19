package com.corner.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.corner.account.beans.ro.WhWithDrawMgCondition;
import com.corner.account.beans.vo.ScmsWarehouseWithDrawVo;
import com.corner.account.beans.vo.WhWithDrawVo;
import com.corner.account.config.AuthorityConfig;
import com.corner.account.service.WhWithDrawService;
import com.corner.core.beans.WhWDSheet;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;


@Controller
@RequestMapping(value="/account/whwalletctl")
public class WhWalletController extends AccountBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(WhWalletController.class);
	
	@Autowired
	WhWithDrawService whWithDrawService;
	
	@RequestMapping(value = "/WithDrawManagerPage.do")
	public Object list(HttpServletRequest request, Model model) {
		logger.debug("准备进入WithDrawManagerPage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			return "WhWithDraw/WhWithDrawManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}

	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, WhWithDrawMgCondition command) {
		Pager<ScmsWarehouseWithDrawVo> pager = whWithDrawService.getWhWithDrawList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	@RequestMapping(value = "/WhWDListManagerPage.do")
	public Object spWDListManagerPage(HttpServletRequest request,String whId,Integer status, Model model) {
		logger.debug("准备进入MaWDListManagerPage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			model.addAttribute("whId", whId);
			model.addAttribute("status", status==null?0:status);
			return "WhWithDraw/WhWithDrawListManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/DetailList.do")
	@ResponseBody
	public Object DetailList(HttpServletRequest request, WhWithDrawMgCondition command) {
		Pager<WhWithDrawVo> pager = whWithDrawService.getWhWithDrawDetailList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	@RequestMapping(value = "/UpdateWithDrawPass.do")
	@ResponseBody
	public Object updateAcStatusPass(HttpServletRequest request, WhWithDrawMgCondition command) {
		if(command ==null || command.getStatus()==null || StringUtils.isEmpty(command.getWithDrawIds())){
			return ResponseUtils.sendMsg(false,"参数错误");
		}else{			
			ModelMsg msg = whWithDrawService.updateWithDrawStatus(command);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}
	
	/**
	 * 生成结算单据，保存在服务器目录里面
	 * @param request
	 * @param command
	 * @throws Exception 
	 */
	@RequestMapping(value = "/CreatDWSheet.do")
	@ResponseBody
	public Object creatDWSheet(HttpServletRequest request,HttpServletResponse response,WhWithDrawMgCondition command) throws Exception {
		if(command != null && !StringUtils.isEmpty(command.getWithDrawIds())){
			ModelMsg msg =  whWithDrawService.addDWSheet(command,request,response);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,msg.getData());				
			}else{
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}else{
			return ResponseUtils.sendMsg(false,"参数错误");	
		}
	}
	
	
	@RequestMapping(value = "/WhWDSheetManagerPage.do")
	public String spWDSheetManagerPage(HttpServletRequest request, Model model) {
		logger.debug("用户进入核心权限管理界面MaWDSheetManagerPage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			return "WhWithDraw/WhWDSheetManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/WhSheetList.do")
	@ResponseBody
	public Object wDSheetList(HttpServletRequest request, WhWithDrawMgCondition command) {
		Pager<WhWDSheet> pager = whWithDrawService.getWhWDSheetList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	
	/**
	 * 录入反馈，银行流水
	 * @param request
	 * @param command
	 * @throws Exception 
	 */
	@RequestMapping(value = "/FillWDBack.do")
	@ResponseBody
	public Object fillWDBack(HttpServletRequest request,HttpServletResponse response,WhWithDrawMgCondition command) throws Exception {
		if(command != null 
				&& !StringUtils.isEmpty(command.getBankDealNo())
				&& !StringUtils.isEmpty(command.getId())){
			ModelMsg msg =  whWithDrawService.updateWDsheetByCondition(command);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,msg.getMessage());				
			}else{
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}else{
			return ResponseUtils.sendMsg(false,"参数错误");	
		}
	}
	
}
