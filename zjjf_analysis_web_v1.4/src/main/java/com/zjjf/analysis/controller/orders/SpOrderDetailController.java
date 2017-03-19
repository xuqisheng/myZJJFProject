package com.zjjf.analysis.controller.orders;

import java.io.File;
import java.io.FileInputStream;
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
import com.zjjf.analysis.common.constants.Constants;
import com.zjjf.analysis.common.constants.SessionConfig;
import com.zjjf.analysis.controller.BaseController;
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.base.IViewOptionList;
import com.zjjf.analysis.producer.orders.IOrdersItemDetailService;
import com.zjjf.analysis.utils.CreateExcelUtils;
import com.zjjf.analysis.utils.CreateZipUtils;
import com.zjjf.analysis.utils.FileUtils;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Controller
@RequestMapping(value = "/api/sp_order/itemdetail")
public class SpOrderDetailController extends BaseController {

	@Reference(version = "1.0.0")
	private IOrdersItemDetailService<?> ordersItemDetailService;

	@Reference(version = "1.0.0")
	private IViewOptionList viewOptionList;

	@Reference(version = "1.0.0")
	private IAuthoritydata authoritydata;

	private static Logger logger = LoggerFactory.getLogger(SpOrderDetailController.class);

	@RequestMapping(value = "/loadPage.do")
	public String orderLoaded(@RequestParam(value = "menuId", required = false, defaultValue = "10") Integer menuId,
			Model model) {

		model.addAttribute("menuId", menuId);
		return "analysis/sp-order/orderItemDetailList";
	}

	@RequestMapping(value = "/getQueryParam.do")
	@ResponseBody
	public HashMap<String, Object> getQueryParam(
			@RequestParam(value = "menuId", required = false, defaultValue = "10") Integer menuId) throws IOException {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);

		String userName = authInfo.getUserId();
		Integer level = authInfo.getLevel();
		resultMap.put("optionList", viewOptionList.getOptionList(userName, 1));
		resultMap.put("level", level);
		return resultMap;
	}

	@RequestMapping(value = "/orderItemList.do")
	@ResponseBody
	public HashMap<String, Object> querySpOrders(
			@RequestParam(value = "menuId", required = false, defaultValue = "10") Integer menuId,
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
		resultMap.put("dataList", ordersItemDetailService.getData(userName, menuId, paramMap));
		resultMap.put("totalCount", ordersItemDetailService.getTotalCount(userName, menuId, paramMap));
		return resultMap;
	}
	
	@RequestMapping(value = "/portExcel.do", method = RequestMethod.GET)
	public String portExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "menuId", required = false, defaultValue = "10") Integer menuId) throws IOException {

		String path = Constants.excel_path;
		FileUtils.createFile(path);
		OutputStream outputStream = response.getOutputStream();
		HashMap<String, Object> paramMap = getQueryMap(request, Arrays.asList("cityId", "areaId", "spGroupId", "nameOrbarCode", "orderNos", "addTimeBegin", "addTimeEnd"));
		AuthInfo authInfo = (AuthInfo) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.userAuthMap);
		logger.info("传入参数paramMap:=======" + paramMap);
		String userName = authInfo.getUserId();
		Object[] titleColumn = authoritydata.getOrderTitleCn(userName, menuId);
		Integer count = ordersItemDetailService.getTotalCount(userName, menuId, paramMap);
		Integer totalShell = count / Constants.excelLimit + 1;
		for (int i = 0; i < totalShell; i++) {
			paramMap.put("pageNo", i * Constants.excelLimit);
			paramMap.put("offset", Constants.excelLimit);
			List<Object[]> dataList = ordersItemDetailService.getData(userName, menuId, paramMap);
			try {
				CreateExcelUtils.createExcelFile("order", path, titleColumn, dataList, i);
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		// 生成.zip文件;
		CreateZipUtils.createZipPath(path);
		// 删除目录下所有的文件;
		File file = new File(path);
		// 重新创建文件;
		file.mkdirs();
		InputStream inputStream = new FileInputStream(new File(path + ".zip"));
		response.setContentType("multipart/form-data; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1") + ".zip");
		response.setCharacterEncoding("utf-8");

		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();  
		// 删除文件;
		FileUtils.deleteExcelPath(file);
		outputStream.close();
		return null;
	}
}
