package com.zjjf.analysis.controller.suppliers;

import java.util.ArrayList;
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
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.supplier.ISupplierSaleGoalService;
import com.zjjf.analysis.utils.GoalSetBase;

@Controller
@RequestMapping({ "/api/spgroup/goal" })
public class SpGroupGoalController extends GoalSetBase {

	@Reference(version = "1.0.0")
	private IAuthoritydata authoritydata;

	@Reference(version = "1.0.0")
	private ISupplierSaleGoalService saleGoalSetService;

	@RequestMapping(value = "/loadPage.do")
	public String orderLoaded(
			@RequestParam(value = "menuId", required = false, defaultValue = "8") Integer menuId,
			@RequestParam(required = false) Integer dayTime, Model model) {

		model.addAttribute("menuId", menuId);
		if (dayTime != null) {
			model.addAttribute("dayTime", dayTime);
		}
		return "analysis/supplier/supplierGroupGoalSet";
	}

	@RequestMapping(value = "/querySaleGoalSet.do")
	@ResponseBody
	public HashMap<String, Object> querySaleGoalSet(
			@RequestParam(value = "menuId", required = false, defaultValue = "8") Integer menuId,
			@RequestBody HashMap<String, Object> paramMap) {

		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession()
				.getAttribute(SessionConfig.userAuthMap);
		String userName = authInfo.getUserId();
		menuId = paramMap.get("menuId") == null ? 8 : Integer.valueOf(paramMap
				.get("menuId") + "");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("cn_keys",
				authoritydata.getOrderTitleCn(userName, menuId));
		resultMap.put("parentTitle",
				authoritydata.getParentTitle(menuId, authInfo.getBaseRoleId()));
		resultMap.put("dataList",
				saleGoalSetService.query(userName, menuId, paramMap));
		return resultMap;
	}

	@RequestMapping(value = "/updateSaleGoalSet.do")
	@ResponseBody
	public HashMap<String, Object> batchUpdate(
			@RequestBody List<Object[]> listArray) {

		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		for (Object[] obj : listArray) {
			HashMap<String, Object> colMap = new HashMap<String, Object>();
			colMap.put("id", obj[0]);
			this.setSpMap(obj, colMap);
			mapList.add(colMap);
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("array", mapList);
		Integer affected = null;
		affected = saleGoalSetService.batchUpdate(map);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("affected", affected);
		return resultMap;
	}
	
}
