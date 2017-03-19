package com.corner.scms.controller.sc;

import com.corner.scms.beans.ro.ma.MaWalletLogRo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.PublicService;
import com.corner.scms.service.fac.MaWithDrawMgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/scms/manager")
public class ScmsManagerMgController extends ScmsBaseWebController {

	@Autowired
	MaWithDrawMgService maWithDrawMgService;
	@Autowired
	PublicService publicService;
	private static Logger logger = LoggerFactory.getLogger(ScmsManagerMgController.class);

	/**
	 * 
	* @Title: toMaWithDraw 
	* @Description:跳转到提现页面
	* @param @return
	* @param @throws Exception
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toMaWithDraw.do")
	public String toMaWithDraw(HttpServletRequest request, Model model) throws Exception {
		return this.error("交易异常" , model , request);
	}

	/**
	 * 
	* @Title: toMaWalletLog 
	* @Description:跳转到经销商钱包收支明细页面
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toMaWalletLog.do")
	public String toMaWalletLog(HttpServletRequest request , MaWalletLogRo maWalletLogRo , Model model) {
		return this.error("交易异常" , model , request);
	}

	/**
	 *
	* @Title: toWallet 
	* @Description:跳转到经销商钱包页
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toWallet.do")
	public String toWallet(HttpServletRequest request,Model model ,MaWalletLogRo command) throws Exception{
		return this.error("交易异常" , model , request);
	}
}
