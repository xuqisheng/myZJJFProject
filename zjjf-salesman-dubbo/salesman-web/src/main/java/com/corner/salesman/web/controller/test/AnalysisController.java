package com.corner.salesman.web.controller.test;

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




/*import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.zs.cat.cms.api.service.DemoService;
import com.zs.cat.commons.dao.Page;
import com.zs.cat.commons.dao.PageData;
import com.zs.cat.commons.util.AppUtil;
import com.zs.cat.commons.util.Const;
import com.zs.cat.commons.util.ObjectExcelView;
import com.zs.cat.commons.util.mail.BaseController;
import com.zs.cat.web.util.Jurisdiction;*/


/** 
 * 类名称：DemoController
 * 创建人：zs 
 * 创建时间：2016-02-22
 */
@Controller
@RequestMapping(value="/anlysis")
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
	
	/*String menuUrl = "demo/list.do"; //菜单地址(权限用)
	@Autowired
	private SignTimeRecordService signTimeRecordService;
	@Autowired
	private RegionService regionService;
	
	*//**
	 * 新增
	 *//*
	@ResponseBody
	@RequestMapping(value="/queryRegionList")
	public Object queryRegionList(Region region) throws Exception{
		List<Region> list = regionService.queryRegionList(region);
		
		System.err.println(JSON.toJSONString(list.size()));
		return list;
	}
	*//**
	 * 新增
	 *//*
	@RequestMapping(value="/findById")
	public void findById(String id) throws Exception{
		SignTimeRecord record = signTimeRecordService.findSignTimeRecordById(id);
		
		System.err.println(JSON.toJSONString(record));
	}*/
	
	/**
	 * 新增
	 */
	/*@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Demo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("DEMO_ID", this.get32UUID());	//主键
		pd.put("USERNAME", "元宝");
		pd.put("AGE", "26");
		demoService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}*/
	
	/**
	 * 删除
	 */
	/*@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Demo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			demoService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}*/
	
	/**
	 * 修改
	 */
	/*@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Demo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		demoService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}*/
	
	/**
	 * 列表
	 */
	/*@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Demo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = demoService.list(page);	//列出Demo列表
			mv.setViewName("cms/demo/demo_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			//mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}*/
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Demo页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("cms/demo/demo_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	

	
	/* ===============================权限================================== */
	/*public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}*/
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
