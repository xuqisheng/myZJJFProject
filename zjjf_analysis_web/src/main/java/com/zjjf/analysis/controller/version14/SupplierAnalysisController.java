package com.zjjf.analysis.controller.version14;

import java.io.IOException;
import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.common.constants.SessionConfig;
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.corner.ManagerAnaService;

@Controller
@RequestMapping("api/supplier/analysis")
public class SupplierAnalysisController {

	@Reference(version = "1.0.0")
	private ManagerAnaService managerAnaService;

	@Reference(version = "1.0.0")
	private IAuthoritydata authoritydata;

	private static Logger logger = LoggerFactory.getLogger(SupplierAnalysisController.class);

	@RequestMapping(value = "/loadPage.do")
	public String loaded(@RequestParam(value = "menuId", required = false, defaultValue = "8") Integer menuId, Model model) {
		model.addAttribute("menuId", menuId);
		return "analysis/version14/managerAnalysis";
	}

	@RequestMapping(value = "/getList.do")
	@ResponseBody
	public HashMap<String, Object> getList(@RequestParam(value = "menuId", required = false, defaultValue = "48") Integer menuId,
			@RequestBody HashMap<String, Object> paramMap) throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);

		String _pageNo = paramMap.get("pageNo") + "";
		String _offset = paramMap.get("offset") + "";
		Integer offset = _offset == null ? 5 : Integer.valueOf(_offset);
		Integer pageNo = _pageNo == null ? 1 : (Integer.valueOf(_pageNo) - 1) * offset;
		paramMap.put("pageNo", pageNo);
		paramMap.put("offset", offset);
		logger.info("参数paramMap:=======" + paramMap);
		String userName = authInfo.getUserId();
		resultMap.put("key_cn", authoritydata.getOrderTitleCn(userName, menuId));
		resultMap.put("parentTitle", authoritydata.getParentTitle(menuId, authInfo.getBaseRoleId()));
		resultMap.put("dataList", managerAnaService.getList(userName, menuId, paramMap));
		resultMap.put("totalCount", managerAnaService.getCount(userName, menuId, paramMap));

		return resultMap;
	}

	@RequestMapping(value = "/getQueryParam.do")
	@ResponseBody
	public HashMap<String, Object> getQueryParam(@RequestParam(value = "menuId", required = false, defaultValue = "8") Integer menuId)
			throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		return resultMap;
	}

}
