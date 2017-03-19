package com.zjjf.analysis.controller.store;

import java.util.HashMap;
import java.util.List;

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
	public HashMap<String, Object> getList(
			@RequestParam(value = "menuId", required = false, defaultValue = "8") Integer menuId,
			@RequestBody HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession()
				.getAttribute(SessionConfig.userAuthMap);
		
		String userName = authInfo.getUserId();
		menuId = paramMap.get("menuId") == null ? 8 : Integer.parseInt(paramMap.get("menuId")+"");
		Integer offset = Integer.valueOf(paramMap.get("offset") + "");
		Integer pageNo = Integer.valueOf(paramMap.get("pageNo") + "");
		if (pageNo <= 0) {
			pageNo = 1;
		}
		paramMap.put("pageNo", (pageNo-1)*offset);
		resultMap.put("dataList", goalSetService.getList(userName, menuId,
				paramMap));
		resultMap.put("totalCount", goalSetService.getCount(paramMap));
		return resultMap;
	}
	
	@RequestMapping(value = "/addGoal.do")
	@ResponseBody
	public Boolean addGoal(
			@RequestBody HashMap<String, Object> paramMap) {
		
		List<HashMap<String, Object>> queryList = goalSetService.getByMap(paramMap);
		if (queryList == null || queryList.size() == 0) {
			String createTime =  DateUtils.getDate("yyyyMMdd");
			paramMap.put("createTime", createTime);
			Integer rs = goalSetService.insert(paramMap);
			System.out.println("新增" + rs + "条");
			return true;
		} else {
			return false;
		}
	}
	
}
