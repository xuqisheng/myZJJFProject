package com.zjjf.analysis.controller.orders;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.common.constants.SessionConfig;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.base.IViewOptionList;
import com.zjjf.analysis.producer.orders.IScOrdersItemService;
import com.zjjf.analysis.producer.orders.IScOrdersService;
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
@RequestMapping(value = "/api/sc_order")
public class ScOrderController extends BaseController {

	@Reference(version = "1.0.0")
	private IScOrdersService scOrdersService;
	
	@Reference(version = "1.0.0")
	private IScOrdersItemService scOrdersItemService;

	@Reference(version = "1.0.0")
	private IViewOptionList viewOptionList;

	@Reference(version = "1.0.0")
	private IAuthoritydata authoritydata;

	private static Logger logger = LoggerFactory.getLogger(ScOrderController.class);

	@RequestMapping(value = "/loadPage.do")
	public String orderLoaded(@RequestParam(value = "menuId", required = false, defaultValue = "21") Integer menuId, Model model) {

		model.addAttribute("menuId", menuId);
		return "analysis/sc-order/scOrderList";
	}

	@RequestMapping(value = "/detailLoadPage.do")
	public String detailLoadPage(@RequestParam(value = "menuId", required = false, defaultValue = "21") Integer menuId, HttpServletRequest request,
			Model model) {

		model.addAttribute("menuId", menuId);
		model.addAttribute("orderId", request.getParameter("orderId"));
		return "analysis/sc-order/scOrderDetail";
	}

	@RequestMapping(value = "/getQueryParam.do")
	@ResponseBody
	public HashMap<String, Object> getQueryParam(
			@RequestParam(value = "menuId", required = false, defaultValue = "21") Integer menuId) throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);

		String userName = authInfo.getUserId();
		Integer level = authInfo.getLevel();
		resultMap.put("optionList", viewOptionList.getOptionList(userName, 1));
		resultMap.put("level", level);
		return resultMap;
	}

	@RequestMapping(value = "/scOrderList.do")
	@ResponseBody
	public HashMap<String, Object> queryScOrders(
			@RequestParam(value = "menuId", required = false, defaultValue = "21") Integer menuId,
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
		resultMap.put("parentTitle", authoritydata.getParentTitle(menuId, authInfo.getBaseRoleId()));
		resultMap.put("key_cn", authoritydata.getOrderTitleCn(userName, menuId));
		resultMap.put("dataList", scOrdersService.getOrderData(userName, menuId, paramMap));
		resultMap.put("totalCount", scOrdersService.getTotalCount(userName, menuId, paramMap));
		resultMap.put("orderMummary", scOrdersService.getOrderMummary(userName, menuId, paramMap));
		return resultMap;
	}

	@RequestMapping(value = "/portExcel.do", method = RequestMethod.GET)
	public String portExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "menuId", required = false, defaultValue = "21") Integer menuId) throws IOException {

		OutputStream outputStream = response.getOutputStream();
		HashMap<String, Object> paramMap = getQueryMap(request, Arrays.asList("cityId", "areaId", "spGroupId", "supportmetho",
				"supportStatus", "status", "orderNos", "storeName", "supplierName", "addTimeBegin", "addTimeEnd"));
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);
		logger.info("传入参数paramMap:=======" + paramMap);
		String userName = authInfo.getUserId();
		List<Object[]> excelData = scOrdersService.getExcelData(userName, menuId, paramMap);
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
	
	@RequestMapping(value = "/portItemExcel.do", method = RequestMethod.GET)
	public String portItemExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "menuId", required = false, defaultValue = "22") Integer menuId) throws IOException {

		OutputStream outputStream = response.getOutputStream();
		String orderId = request.getParameter("orderId");
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);
		String userName = authInfo.getUserId();
		List<Object[]> excelData = scOrdersItemService.getOrderItemListExcel(userName, orderId, menuId, new HashMap<String, Object>());
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

	@RequestMapping(value = "/getScOrderDetail.do")
	@ResponseBody
	public HashMap<String, Object> getScOrderDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "menuId", required = false, defaultValue = "22") Integer menuId,
			@RequestBody HashMap<String, Object> paramMap) throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String orderId = "" + paramMap.get("orderId");
		String _pageNo = paramMap.get("pageNo") + "";
		String _offset = paramMap.get("offset") + "";
		Integer offset = _offset == null ? 5 : Integer.valueOf(_offset);
		Integer pageNo = _pageNo == null ? 1 : (Integer.valueOf(_pageNo) - 1) * offset;
		paramMap.put("pageNo", pageNo);
		paramMap.put("offset", offset);
		logger.info("传入参数paramMap:=======" + paramMap);
		if (orderId == null || "".equals(orderId.trim())) {
			return resultMap;
		}
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);
		String userName = authInfo.getUserId();
		resultMap.put("key_cn", authoritydata.getOrderTitleCn(userName, menuId));
		resultMap.put("parentTitle", authoritydata.getParentTitle(menuId, authInfo.getBaseRoleId()));
		resultMap.put("orderInfo", scOrdersService.getOrderByOrderId(orderId));
		resultMap.put("orderItemList", scOrdersItemService.getOrderItemList(userName, orderId, menuId, paramMap));
		resultMap.put("totalCount", scOrdersItemService.getItemTotalCount(userName, orderId, menuId, new HashMap<String, Object>()));
		return resultMap;
	}

	@RequestMapping(value = "/exportOrderItem.do", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> exportOrderItem(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "menuId", required = false, defaultValue = "22") Integer menuId) throws IOException {

		OutputStream outputStream = response.getOutputStream();
		String orderId = request.getParameter("orderId");
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);
		String userName = authInfo.getUserId();
		List<Object[]> excelData = scOrdersItemService.getOrderItemList(userName, orderId, menuId, null);
		InputStream inputStream = this.createExcel("联合采购订单明细商品报表", authoritydata.getOrderTitleCn(userName, menuId), excelData);
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
