package com.corner.kefu.controller.scms;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.scms.ScmsMinimumMgRo;
import com.corner.kefu.config.SCMSConstants;
import com.corner.kefu.config.SessionConfig;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.scms.ScmsMinimumMgService;

@Controller
@RequestMapping(value="/scms/minimum")
public class ScmsMinimumMgController extends KefuBaseWebController{
	@Autowired
	ScmsMinimumMgService scmsMinimumMgService;
	/**
	 * 
	 * @Title: findByMdseId 
	 * @Description: 通过基础商品条形码查询此商品所以信息
	 * @param @param request
	 * @param @param command
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/updateScmsMinimum.do")
	@ResponseBody
	public Object updateScmsMinimum(HttpServletRequest request, ScmsMinimumMgRo command) {
		try {
			if(StringUtil.stringIsNullOrEmpty(command.getManagerId()))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			if(command.getMinimums() == null || command.getIds() == null || command.getMinimums().length == 0 || command.getIds().length == 0)
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			for (int i = 0; i < command.getIds().length; i++) {
				if(StringUtil.stringIsNullOrEmpty(command.getIds()[i] , command.getMinimums()[i]))
					return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			}
			Subject subject = SecurityUtils.getSubject();
			CustomerService user =(CustomerService)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			command.setKefuId(user.getId());
			ModelMsg msg =  scmsMinimumMgService.updateScmsMinimum(command);
			return ResponseUtils.sendMsg(msg.isSuccess(), msg.getMessage());
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
}

