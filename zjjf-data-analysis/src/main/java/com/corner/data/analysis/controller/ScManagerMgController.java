package com.corner.data.analysis.controller;

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

import com.corner.core.beans.ScManager;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.beans.ro.auth.ScManagerCondition;
import com.corner.scms.config.AuthorityConfig;
import com.corner.scms.service.ScManagerMgService;


@Controller
@RequestMapping(value="/scms/userctl")
public class ScManagerMgController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(ScManagerMgController.class);
	
	@Autowired ScManagerMgService scManagerMgService;

	@RequestMapping(value = "/AccountManagerPage.do")
	public String index(HttpServletRequest request, Model model) {
		logger.debug("用户进入核心权限管理界面");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			return "User/UserManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/Delete.do")
	@ResponseBody
	public Object delete(@RequestParam(value = "ids", required = true) String ids) {
		if(StringUtils.isEmpty(ids)){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			String[] array = ids.split(",");
			ModelMsg msg = scManagerMgService.deleteObjects("ScManager",array);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}

	@RequestMapping(value = "/Update.do")
	@ResponseBody
	public Object Update(HttpServletRequest request, ScManager ScManager) {
		if(ScManager == null || ScManager.getId() == null){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			ModelMsg msg = scManagerMgService.updateByPrimaryKeySelective(ScManager);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}

	@RequestMapping(value = "/Add.do")
	@ResponseBody
	public Object add(HttpServletRequest request, ScManager user) {
		if(user == null || user.getId() == null ||user.getSupplierName() ==null || user.getMobile() ==null ){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			ModelMsg msg = scManagerMgService.addObject(user);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}
	
	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, ScManagerCondition command) {
		logger.debug("RealName is {}",command.getRealName());
		Pager<ScManager> pager = scManagerMgService.getScManagerPageList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	@RequestMapping(value = "/getRolesById.do")
	@ResponseBody
	public Object getRolesById(HttpServletRequest request, String id) {
		return scManagerMgService.getRolesById(id);
	}
	
	@RequestMapping(value = "/saveRoles.do")
	@ResponseBody
	public Object saveRoles(HttpServletRequest request, String userId,String roleIds) {
		if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(roleIds)){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			ModelMsg msg = scManagerMgService.saveUserRoles(userId,roleIds);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}

}
