package com.corner.kefu.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.service.PublicService;

@Controller
@RequestMapping(value="/kefu/public")
public class PublicController extends KefuBaseWebController {

	private static Logger logger = LoggerFactory.getLogger(PublicController.class);
	@Autowired
	PublicService publicService;
	
	
	/**
	 * 
	* @Title: findRegionsByPId 
	* @Description: 查询地区列表信息，通过pId
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/findRegionsByPId.do")
	@ResponseBody
	public Object findRegionsByPId(HttpServletRequest request, String pId) {
		if(StringUtil.stringIsNullOrEmpty(pId))
			return ResponseUtils.sendMsg(false, "请输入正确的pId");
		return ResponseUtils.sendMsg(true, publicService.findRegionByPId(pId));
	}
}
