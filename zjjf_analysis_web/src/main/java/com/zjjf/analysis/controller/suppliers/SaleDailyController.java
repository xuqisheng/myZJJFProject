package com.zjjf.analysis.controller.suppliers;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.common.constants.SessionConfig;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.base.IViewOptionList;
import com.zjjf.analysis.producer.store.ISaleDailyReportService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/api/sale/daily")
public class SaleDailyController extends BaseController {

	@Reference(version = "1.0.0")
	private IViewOptionList viewOptionList;

	@Reference(version = "1.0.0")
	private IAuthoritydata authoritydata;

	@Reference(version = "1.0.0")
	private ISaleDailyReportService<?> saleDailyReportService;

	private static Logger logger = LoggerFactory.getLogger(SaleDailyController.class);

	@RequestMapping(value = "/loadPage.do")
	public String orderLoaded(@RequestParam(value = "menuId", required = false, defaultValue = "8") Integer menuId, Model model) {

		model.addAttribute("menuId", menuId);
		return "analysis/spgroup/saleDailyList";
	}

	@RequestMapping(value = "/getQueryParam.do")
	@ResponseBody
	public HashMap<String, Object> getQueryParam(@RequestParam(value = "menuId", required = false, defaultValue = "35") Integer menuId)
			throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);

		String userName = authInfo.getUserId();
		Integer level = authInfo.getLevel();
		resultMap.put("optionList", viewOptionList.getOptionList(userName, 1));
		resultMap.put("level", level);
		return resultMap;
	}

	@RequestMapping(value = "/list.do")
	@ResponseBody
	public HashMap<String, Object> querySpOrders(@RequestParam(value = "menuId", required = false, defaultValue = "35") Integer menuId,
			@RequestBody HashMap<String, Object> paramMap) throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);

		String _pageNo = paramMap.get("pageNo") + "";
		String _offset = paramMap.get("offset") + "";
		Integer offset = _offset == null ? 5 : Integer.valueOf(_offset);
		Integer pageNo = _pageNo == null ? 1 : (Integer.valueOf(_pageNo) - 1) * offset;
		paramMap.put("pageNo", pageNo);
		paramMap.put("offset", offset);
		logger.info("传入参数paramMap:=======" + paramMap);
		String userName = authInfo.getUserId();
		resultMap.put("key_cn", authoritydata.getOrderTitleCn(userName, menuId));
		resultMap.put("parentTitle", authoritydata.getParentTitle(menuId, authInfo.getBaseRoleId()));
		resultMap.put("dataList", saleDailyReportService.getData(userName, menuId, paramMap));
		resultMap.put("totalCount", saleDailyReportService.getTotalCount(userName, menuId, paramMap));
		return resultMap;
	}

	@RequestMapping(value = "/portExcel.do", method = RequestMethod.GET)
	public String portExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "menuId", required = false, defaultValue = "35") Integer menuId) throws IOException {

		OutputStream outputStream = response.getOutputStream();
		HashMap<String, Object> paramMap = getQueryMap(request, Arrays.asList("cityId", "areaId", "spGroupId", "addTime", "supplierName"));
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);
		logger.info("传入参数paramMap:=======" + paramMap);
		String userName = authInfo.getUserId();
		List<Object[]> excelData = saleDailyReportService.getExcelData(userName, menuId, paramMap);
		InputStream inputStream = this.createExcel("销售日报报表", authoritydata.getOrderTitleCn(userName, menuId), excelData);
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=sp_order.xls");
		response.setCharacterEncoding("utf-8");

		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		outputStream.close();
		return null;
	}

}
