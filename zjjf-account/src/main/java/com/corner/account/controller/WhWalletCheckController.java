package com.corner.account.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.account.beans.ro.WhWalletCheckMgCondition;
import com.corner.account.beans.vo.WhWalletCheckVo;
import com.corner.account.config.AuthorityConfig;
import com.corner.account.service.WhWithDrawService;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.ResponseUtils;

/**
 * 
* @ClassName: SpWalletCheckController 
* @Description: TODO(批发商日审核月审核界面) 
* @author 铁中棠  tiezhongtang@izjjf.cn 
* @date 2016年3月16日 下午3:45:09 
*
 */

@Controller
@RequestMapping(value="/account/whwalletcheckctl")
public class WhWalletCheckController extends AccountBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(WhWalletCheckController.class);
	
	@Autowired
	WhWithDrawService whWithDrawService;
	
	@RequestMapping(value = "/WhMoneyCheckPage.do")
	public Object whMoneyCheckPage(HttpServletRequest request, Model model) {
		logger.debug("准备进入maMoneyCheckPage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			SimpleDateFormat startdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			model.addAttribute("defaultStart",startdf.format(new Date()));
			SimpleDateFormat enddf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			model.addAttribute("defaultEnd",enddf.format(new Date()));
			model.addAttribute("defaultTime", DateUtil.getDate());
			return "WhWithDraw/WhWithDrawCKListManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}

	@RequestMapping(value = "/WhWithDrawCheckList.do")
	@ResponseBody
	public Object whWithDrawCheckList(HttpServletRequest request, WhWalletCheckMgCondition command) {
		Pager<WhWalletCheckVo> pager = whWithDrawService.getWhOneDayCheckList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}

	
}
