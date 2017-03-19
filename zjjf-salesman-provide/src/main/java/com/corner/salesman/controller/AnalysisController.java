package com.corner.salesman.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.corner.salesman.commons.dao.PageData;
import com.corner.salesman.commons.utils.mail.BaseController;
import com.zjjf.analysis.producer.ajie.ISpGroupTurnoverService;
import com.zjjf.analysis.producer.ajie.ISpOrderDetailService;
import com.zjjf.analysis.producer.ajie.IStoreTurnoverService;
import com.zjjf.analysis.producer.ajie.ISupplierTurnoverService;

/** 
 * 类名称：DemoController
 * 创建人：zs 
 * 创建时间：2016-02-22
 */
//@Controller
//@RequestMapping(value="/anlysis")
public class AnalysisController extends BaseController {
	
	/*@Reference(version = "1.0.0")
	private ISpGroupTurnoverService spGroupTurnoverService;

	@Reference(version = "1.0.0")
	private IStoreTurnoverService storeTurnoverService;

	@Reference(version = "1.0.0")
	private ISupplierTurnoverService supplierTurnoverService;

	@Reference(version = "1.0.0")
	private ISpOrderDetailService spOrderDetailService;*/
	
	@Autowired
	private ISpGroupTurnoverService spGroupTurnoverService;
	@Autowired
	private IStoreTurnoverService storeTurnoverService;
	@Autowired
	private ISupplierTurnoverService supplierTurnoverService;
	@Autowired
	private ISpOrderDetailService spOrderDetailService;

	@RequestMapping(value = "/storeData.do", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> storeData(
			@RequestParam(value = "dayTime", required = false, defaultValue = "") String dayTime,
			@RequestParam(value = "supplierCode", required = false, defaultValue = "") Integer storeId,
			@RequestParam(value = "timeType", required = false, defaultValue = "") String timeType,
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "offset", required = false, defaultValue = "10") Integer offset) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dayTime", dayTime);
		paramMap.put("storeId", storeId);
		paramMap.put("timeType", timeType);
		paramMap.put("pageNo", pageNo);
		paramMap.put("offset", offset);
		resultMap.put("data", storeTurnoverService.getData(paramMap));
		return resultMap;
	}

	/*@RequestMapping(value = "/supplierData.do", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> supplierData(
			@RequestParam(value = "dayTime", required = false, defaultValue = "") String dayTime,
			@RequestParam(value = "supplierCode", required = false, defaultValue = "") String supplierCode,
			@RequestParam(value = "supplierId", required = false, defaultValue = "") String supplierId) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dayTime", dayTime);
		paramMap.put("supplierCode", supplierCode);
		paramMap.put("supplierId", supplierId);
		resultMap.put("data", supplierTurnoverService.getData(paramMap));
		return resultMap;
	}

	@RequestMapping(value = "/spGroupData.do", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> spGroupData(
			@RequestParam(value = "dayTime", required = false, defaultValue = "") String dayTime,
			@RequestParam(value = "spGroupId", required = false, defaultValue = "") Integer spGroupId) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dayTime", dayTime);
		paramMap.put("spGroupId", spGroupId);
		resultMap.put("data", spGroupTurnoverService.getData(paramMap));
		return resultMap;
	}*/
}