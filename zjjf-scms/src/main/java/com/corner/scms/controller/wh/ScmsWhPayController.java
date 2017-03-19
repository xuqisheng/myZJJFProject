package com.corner.scms.controller.wh;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.MaOrderInfo;
import com.corner.core.beans.PlantWalletLog;
import com.corner.core.beans.ScmsWarehouse;
import com.corner.core.beans.SystemInfo;
import com.corner.core.beans.WhWalletLog;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.vo.sc.MaOrderInfoMgVo;
import com.corner.scms.config.SCMSConstants;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.PublicService;
import com.corner.scms.service.sc.ScOrderInfoMgService;

/**
 * 
 * @ClassName: ScmsSpWalletMgController
 * @Description:仓库钱包控制器
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2015年12月4日 上午11:34:21
 *
 */
@Controller
@RequestMapping("/scms/whPay")
public class ScmsWhPayController extends ScmsBaseWebController {
	private static Logger logger = LoggerFactory.getLogger(ScmsWhPayController.class);
	@Autowired
	ScOrderInfoMgService scOrderInfoMgService;
	@Autowired
	PublicService publicService;
	/**
	 * 
	* @Title: toPay
	* @Description: 去支付页面
	* @param: @param request
	* @param: @param spWithDrawRo
	* @param: @param model
	* @param: @return	设定文件 
	* @return String    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	@RequestMapping(value="/toPay.do")
	public String toPay(HttpServletRequest request , String id ,Model model) {
		if(StringUtil.stringIsNullOrEmpty(id))
			return error("缺少必要参数", model, request);
		ScmsWarehouse scmsWarehouse = this.getCurrentUser(ScmsWarehouse.class, request);
		MaOrderInfoMgVo maOrderInfoMgVo = scOrderInfoMgService.getScOrderInfoById(id);
		if(maOrderInfoMgVo != null)
			model.addAttribute("maOrderInfoMgVo", maOrderInfoMgVo);
		SystemInfo info = publicService.getSystemInfoById(SCMSConstants.BANK_INFO);
		if(info != null)
			model.addAttribute("bankInfo", JSONUtil.JSONToObject(info.getContent() , Map.class));
		model.addAttribute("wallet", "0");
		return "order-warehouse/detail-pay";
	}
	
	
	/**
	 * 
	* @Title: toPay
	* @Description: 去支付页面
	* @param: @param request
	* @param: @param spWithDrawRo
	* @param: @param model
	* @param: @return	设定文件 
	* @return String    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	@RequestMapping(value="/pay.do")
	@ResponseBody
	public Object pay(HttpServletRequest request , String id , String payPassword ,Model model) throws Exception{
		return ResponseUtils.sendMsg(false, "系统异常");
	}
}
