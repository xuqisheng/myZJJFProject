package com.corner.pc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.pc.beans.JoinInfo;
import com.corner.pc.beans.ro.JoinCondition;
import com.corner.pc.beans.vo.ModelMsg;
import com.corner.pc.config.SessionConfig;
import com.corner.pc.service.JoinInfoMgService;
import com.corner.pc.utils.ResponseUtils;

@Controller
@RequestMapping(value = "/pc/join")
public class JoinMgController extends APCBaseWebController {

	private static Logger logger = LoggerFactory.getLogger(JoinMgController.class);

	@Autowired
	JoinInfoMgService joinInfoMgService;

	@RequestMapping(value="/toJoinJsp.do")
	public String toJoinJsp(Model model) {
		try {
			List<JoinInfo> list = joinInfoMgService.getJoinfoList();
			model.addAttribute("list", list);
			return "join";
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}
	}

	@RequestMapping(value = "/submitInfo.do")
	@ResponseBody
	public Object submitInfo(HttpServletRequest request, HttpSession session, JoinCondition joinCondition) {

		if (joinCondition == null) {
			return ResponseUtils.sendMsg(false, "参数不能为空");
		}
		// 验证码不能为空
		if (StringUtils.isEmpty(joinCondition.getCheckCode())) {
			session.removeAttribute(SessionConfig.USER_SESSION_CODE);
			return ResponseUtils.sendMsg(false, "验证码不能为空");
		} else {
			String serverCode = (String) session.getAttribute(SessionConfig.USER_SESSION_CODE);
			if (serverCode == null || !serverCode.toLowerCase().equals(joinCondition.getCheckCode().trim().toLowerCase())) {
				session.removeAttribute(SessionConfig.USER_SESSION_CODE);
				return ResponseUtils.sendMsg(false, "验证码错误");
			}
		}
		session.removeAttribute(SessionConfig.USER_SESSION_CODE);
		// 用户名称不能为空
		if (StringUtils.isEmpty(joinCondition.getName())) {
			return ResponseUtils.sendMsg(false, "用户名称不能为空");
		}
		// 电话不能为空
		if (StringUtils.isEmpty(joinCondition.getMobile())) {
			return ResponseUtils.sendMsg(false, "电话不能为空");
		}
		// 店铺名称不能为空
		if (StringUtils.isEmpty(joinCondition.getStoreName())) {
			return ResponseUtils.sendMsg(false, "店铺名称不能为空");
		}
		// 店铺地址不能为空
		if (StringUtils.isEmpty(joinCondition.getStoreAdress())) {
			return ResponseUtils.sendMsg(false, "店铺地址不能为空");
		}
		try {
			ModelMsg msg = joinInfoMgService.submitJoinInfo(joinCondition);
			if (msg != null && msg.isSuccess()) {
				return ResponseUtils.sendMsg(true, "您的信息提交成功，请等待转角街坊审核！");
			} else {
				return ResponseUtils.sendMsg(false, msg.getMessage());
			}

		} catch (Exception e) {
			logger.error("提交合作信息异常", e);
			return ResponseUtils.sendMsg(false, "提交合作信息异常");
		}
	}

}
