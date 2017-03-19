package com.corner.scms.controller.wh;

import com.corner.scms.beans.ro.wh.WhWalletLogMgRo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.PublicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: ScmsSpWalletMgController
 * @Description:仓库钱包控制器
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2015年12月4日 上午11:34:21
 *
 */
@Controller
@RequestMapping("/scms/wh")
public class ScmsWhWalletMgController extends ScmsBaseWebController {
	private static Logger logger = LoggerFactory.getLogger(ScmsWhWalletMgController.class);
	@Autowired
	PublicService publicService;
	/**
	 * 
	* @Title: toWhWalletLog 
	* @Description: 跳转到收支明细页面
	* @param: @param request
	* @param: @param spWithDrawRo
	* @param: @param model
	* @param: @return	设定文件 
	* @return String    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	@RequestMapping(value="/toWhWalletLog.do")
	public String toWhWalletLog(HttpServletRequest request , WhWalletLogMgRo whWalletLogMgRo ,Model model) {
		return this.error("交易异常" , model , request);
	}
	/**
	 * 
	* @Title: toWhWalletIndex 
	* @Description: 跳到仓库钱包页面
	* @param: @param request
	* @param: @param spWithDrawRo
	* @param: @param model
	* @param: @return	设定文件 
	* @return String    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	@RequestMapping("/toWhWalletIndex.do")
	public String toWhWalletIndex(HttpServletRequest request,Model model,WhWalletLogMgRo command) {
		return this.error("交易异常" , model , request);
	}
	/**
	 * 
	* @Title: toWhWithDrawById 
	* @Description: 查看体现进度
	* @param: @param request
	* @param: @param model
	* @param: @param id
	* @param: @return	设定文件 
	* @return String    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	@RequestMapping("/toWhWithDrawById.do")
	public String toWhWithDrawById(HttpServletRequest request,Model model,Long id) {
		return this.error("交易异常" , model , request);
	}
	
	/**
	 * 
	* @Title: toWhWithDraw 
	* @Description: 提现操作
	* @param: @param request
	* @param: @param model
	* @param: @param id
	* @param: @return	设定文件 
	* @return String    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	@RequestMapping("/toWhWithDraw.do")
	public String toWhWithDraw(HttpServletRequest request,Model model) {
		return this.error("交易异常" , model , request);
	}
}
