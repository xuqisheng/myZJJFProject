package com.corner.account.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.account.beans.ro.SystemConfigCondition;
import com.corner.account.config.AuthorityConfig;
import com.corner.account.service.SystemConfigMgService;
import com.corner.core.beans.SettlementConf;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;


@Controller
@RequestMapping(value="/account/sysconfigctl")
public class SysConfigMgController extends AccountBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(SysConfigMgController.class);
	
	@Autowired SystemConfigMgService configMgService;

	@RequestMapping(value = "/SettlementConfManagerPage.do")
	public String index(HttpServletRequest request, Model model) {
		logger.debug("用户进入核心权限管理界面");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			return "SettlementConf/SettleConfList";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, SystemConfigCondition command) {
		Pager<SettlementConf> pager = configMgService.getSettlementConfPageList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	@RequestMapping(value = "/Delete.do")
	@ResponseBody
	public Object delete(@RequestParam(value = "ids", required = true) String ids) {
		if(StringUtils.isEmpty(ids)){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			String[] array = ids.split(",");
			ModelMsg msg = configMgService.deleteObjects("SettlementConf",array);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}

	@RequestMapping(value = "/Update.do")
	@ResponseBody
	public Object Update(HttpServletRequest request, SettlementConf settlementConf) {
		if(settlementConf == null || settlementConf.getId() == null){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			ModelMsg msg = configMgService.updateByPrimaryKeySelective(settlementConf);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}

	@RequestMapping(value = "/Add.do")
	@ResponseBody
	public Object add(HttpServletRequest request, SettlementConf settlementConf) {
		if(settlementConf == null){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			ModelMsg msg = configMgService.addObject(settlementConf);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}


}
