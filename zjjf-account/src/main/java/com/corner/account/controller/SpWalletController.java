package com.corner.account.controller;

import java.math.BigDecimal;
import java.util.Map;

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

import com.corner.account.beans.ro.SpWithDrawMgCondition;
import com.corner.account.beans.vo.SpWithDrawVo;
import com.corner.account.config.AuthorityConfig;
import com.corner.account.service.PlantWalletService;
import com.corner.account.service.SpWalletService;
import com.corner.account.service.SpWithDrawService;
import com.corner.core.beans.SpWDSheet;
import com.corner.core.beans.SpWalletLog;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;


@Controller
@RequestMapping(value="/account/spwalletctl")
public class SpWalletController extends AccountBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(SpWalletController.class);
	
	@Autowired
	SpWithDrawService spWithDrawService;
	@Autowired
	PlantWalletService plantWalletService;
	
	@Autowired
	SpWalletService spWalletService;
	
	@RequestMapping(value = "/WithDrawManagerPage.do")
	public Object list(HttpServletRequest request, Model model) {
		logger.debug("准备进入BillSheetManage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			return "SpWithDraw/SpWithDrawManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}

	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, SpWithDrawMgCondition command) {
		Pager<SpWithDrawVo> pager = spWithDrawService.getSpWithDrawList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	@RequestMapping(value = "/SpWDListManagerPage.do")
	public Object spWDListManagerPage(HttpServletRequest request,String supplierId,Integer status, Model model) {
		logger.debug("准备进入BillSheetManage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			model.addAttribute("supplierId", supplierId);
			model.addAttribute("status", status==null?0:status);
			return "SpWithDraw/spWithDrawListManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/DetailList.do")
	@ResponseBody
	public Object DetailList(HttpServletRequest request, SpWithDrawMgCondition command) {
		Pager<SpWithDrawVo> pager = spWithDrawService.getSpWithDrawDetailList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	@RequestMapping(value = "/UpdateWithDrawPass.do")
	@ResponseBody
	public Object updateAcStatusPass(HttpServletRequest request, SpWithDrawMgCondition command) {
		if(command ==null || command.getStatus()==null || StringUtils.isEmpty(command.getWithDrawIds())){
			return ResponseUtils.sendMsg(false,"参数错误");
		}else{			
			ModelMsg msg = spWithDrawService.updateWithDrawStatus(command);
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
	public Object creatDWSheet(HttpServletRequest request,HttpServletResponse response,SpWithDrawMgCondition command) throws Exception {
		if(command != null && !StringUtils.isEmpty(command.getWithDrawIds())){
			ModelMsg msg =  spWithDrawService.addDWSheet(command,request,response);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,msg.getData());				
			}else{
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}else{
			return ResponseUtils.sendMsg(false,"参数错误");	
		}
	}
	
	
	@RequestMapping(value = "/SpWDSheetManagerPage.do")
	public String spWDSheetManagerPage(HttpServletRequest request, Model model) {
		logger.debug("用户进入核心权限管理界面SpWDSheetManagerPage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			return "SpWithDraw/SpWDSheetManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/WDSheetList.do")
	@ResponseBody
	public Object wDSheetList(HttpServletRequest request, SpWithDrawMgCondition command) {
		Pager<SpWDSheet> pager = spWithDrawService.getSpWDSheetList(command);
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
	public Object fillWDBack(HttpServletRequest request,HttpServletResponse response,SpWithDrawMgCondition command) throws Exception {
		if(command != null 
				&& !StringUtils.isEmpty(command.getBankDealNo())
				&& !StringUtils.isEmpty(command.getId())){
			ModelMsg msg =  spWithDrawService.updateWDsheetByCondition(command);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,msg.getMessage());				
			}else{
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}else{
			return ResponseUtils.sendMsg(false,"参数错误");	
		}
	}
	
	@RequestMapping(value = "/SpWallet.do")
	public Object SpWallet(HttpServletRequest request, Model model) {
		logger.debug("准备进入SpWallet.jsp");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			return "SpWallet/SpWallet";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	@RequestMapping(value = "/SpWalletList.do")
	@ResponseBody
	public Object SpWalletList(HttpServletRequest request, SpWithDrawMgCondition command) {
		Pager<Map<String, Object>> pager = spWalletService.getSpWalletPage(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	@RequestMapping(value = "/updateSpWallet.do")
	@ResponseBody
	public Object updateSpWallet(HttpServletRequest request,String[] spId , BigDecimal money , Byte optType , String remark) throws Exception{
		if(StringUtil.stringIsNullOrEmpty(remark))
			return new ModelMsg(false, "请输入备注");
		else if(optType == null)
			return new ModelMsg(false, "请选择类型");
		else if(money.compareTo(new BigDecimal("0")) <= 0)
			return new ModelMsg(false, "请输入大于0的金额");
		ModelMsg modelMsg = new ModelMsg(false, "不合法数据");
		for (String string : spId) {
			if(StringUtil.stringIsNullOrEmpty(string))
				return new ModelMsg(false, "不合法数据");
			SpWalletLog spWalletLog = new SpWalletLog(); 
			spWalletLog.setPayer(Byte.valueOf("32"));
			spWalletLog.setSpId(string);
			spWalletLog.setGeter(Byte.valueOf("10"));
			spWalletLog.setActionType(Byte.valueOf("2"));
			spWalletLog.setOptType(optType);
			spWalletLog.setRemark(remark);
			spWalletLog.setMoney(money.multiply(new BigDecimal("-1")));
			modelMsg = plantWalletService.updateSupplerWalletAndLog(spWalletLog);
			if(!modelMsg.isSuccess())
				break;
		}
		return ResponseUtils.sendMsg(modelMsg.isSuccess(), modelMsg.getMessage());
	}
	@RequestMapping(value = "/SpWalletLog.do")
	public Object SpWalletLog(HttpServletRequest request, Model model , SpWithDrawMgCondition command) {
		logger.debug("准备进入SpWallet.jsp");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			model.addAttribute("command",command);
			return "SpWallet/SpWalletLog";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	@RequestMapping(value = "/SpWalletLogList.do")
	@ResponseBody
	public Object SpWalletLogList(HttpServletRequest request, SpWithDrawMgCondition command) {
		Pager<Map<String, Object>> pager = spWalletService.getSpWalletLogPage(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
}
