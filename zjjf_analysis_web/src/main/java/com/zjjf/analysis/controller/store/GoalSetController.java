package com.zjjf.analysis.controller.store;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.common.constants.SessionConfig;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.store.IGoalSetService;
import com.zjjf.analysis.utils.DateUtils;

@Controller
@RequestMapping("/api/store/goalset")
public class GoalSetController extends BaseController {

	@Reference(version = "1.0.0")
	private IAuthoritydata authoritydata;

	@Reference(version = "1.0.0")
	private IGoalSetService goalSetService;

	@RequestMapping(value = "/loadPage.do")
	public String loaded(@RequestParam(value = "menuId", required = false, defaultValue = "8") Integer menuId, Model model) {
		model.addAttribute("menuId", menuId);
		return "analysis/store/goalSet";
	}

	@RequestMapping(value = "/getList.do")
	@ResponseBody
	public HashMap<String, Object> getList(@RequestParam(value = "menuId", required = false, defaultValue = "8") Integer menuId,
			HttpServletRequest request) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> reqMap = new HashMap<String, Object>();
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);

		String userName = authInfo.getUserId();
		menuId = request.getParameter("menuId") == null ? 8 : Integer.parseInt(request.getParameter("menuId") + "");
		Integer offset = Integer.valueOf(request.getParameter("offset") + "");
		Integer pageNo = Integer.valueOf(request.getParameter("pageNo") + "");
		if (pageNo <= 0) {
			pageNo = 1;
		}

		reqMap = reqConvertMap(request);
		reqMap.put("offset", offset);
		reqMap.put("pageNo", (pageNo - 1) * offset);

		resultMap.put("dataList", goalSetService.getList(userName, menuId, reqMap));
		resultMap.put("totalCount", goalSetService.getCount(reqMap));
		return resultMap;
	}

	private HashMap<String, Object> reqConvertMap(HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Enumeration<String> keys = req.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			if (req.getParameter(key) != null) {
				resultMap.put(key, req.getParameter(key));
			}
		}
		return resultMap;
	}

	@RequestMapping(value = "/addGoal.do")
	@ResponseBody
	public Boolean addGoal(@RequestBody HashMap<String, Object> paramMap) {

		List<HashMap<String, Object>> queryList = goalSetService.getByMap(paramMap);
		if (queryList == null || queryList.size() == 0) {
			String createTime = DateUtils.getDate("yyyyMMdd");
			paramMap.put("createTime", createTime);
			Integer rs = goalSetService.insert(paramMap);
			System.out.println("新增" + rs + "条");
			return true;
		} else {
			return false;
		}
	}

}
