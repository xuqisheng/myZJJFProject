package com.zjjf.analysis.controller.suppliers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.beans.vo.orders.SpOrderVo;
import com.zjjf.analysis.common.constants.SessionConfig;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.base.IViewOptionList;
import com.zjjf.analysis.producer.orders.ISupplierFreeService;
import com.zjjf.analysis.utils.MapUtils;

@Controller
@RequestMapping(value = "/api/sp_order/financial/")
public class SupplierFreeController extends BaseController {

	@Reference(version = "1.0.0")
	private ISupplierFreeService<?> supplierFreeService;

	@Reference(version = "1.0.0")
	private IViewOptionList viewOptionList;

	@Reference(version = "1.0.0")
	private IAuthoritydata authoritydata;

	private static Logger logger = LoggerFactory.getLogger(SupplierFreeController.class);

	@RequestMapping(value = "/loadPage.do")
	public String orderLoaded(@RequestParam(value = "menuId", required = false, defaultValue = "17") Integer menuId,
			Model model) {

		model.addAttribute("menuId", menuId);
		return "analysis/financial/spOrderFinancialList";
	}

	@RequestMapping(value = "/getQueryParam.do")
	@ResponseBody
	public HashMap<String, Object> getQueryParam(
			@RequestParam(value = "menuId", required = false, defaultValue = "17") Integer menuId) throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);

		String userName = authInfo.getUserId();
		Integer level = authInfo.getLevel();
		resultMap.put("optionList", viewOptionList.getOptionList(userName, 1));
		resultMap.put("level", level);
		return resultMap;
	}

	@RequestMapping(value = "/spOrderFinancialList.do")
	@ResponseBody
	public HashMap<String, Object> querySpOrders(
			@RequestParam(value = "menuId", required = false, defaultValue = "17") Integer menuId, @RequestBody SpOrderVo vo)
			throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);
		String userName = authInfo.getUserId();
		HashMap<String, Object> paramMap = MapUtils.bean_2_map(vo);
		logger.info("传入参数paramMap:=======" + paramMap);
		resultMap.put("parentTitle", authoritydata.getParentTitle(menuId, authInfo.getBaseRoleId()));
		resultMap.put("key_cn", authoritydata.getOrderTitleCn(userName, menuId));
		resultMap.put("dataList", supplierFreeService.getData(userName, menuId, paramMap));
		resultMap.put("totalCount", supplierFreeService.getTotalCount(userName, menuId, paramMap));
		return resultMap;
	}
	
	@RequestMapping(value = "/portExcel.do", method = RequestMethod.GET)
	public String portExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "menuId", required = false, defaultValue = "17") Integer menuId) throws IOException {

		OutputStream outputStream = response.getOutputStream();
		HashMap<String, Object> paramMap = getQueryMap(request, Arrays.asList("cityId", "areaId", "spGroupId", "supplierName", "dayTime"));
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);
		logger.info("传入参数paramMap:=======" + paramMap);
		String userName = authInfo.getUserId();
		List<Object[]> excelData = supplierFreeService.getExcelData(userName, menuId, paramMap);
		InputStream inputStream = this.createExcel("订单明细报表", authoritydata.getOrderTitleCn(userName, menuId), excelData);
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
