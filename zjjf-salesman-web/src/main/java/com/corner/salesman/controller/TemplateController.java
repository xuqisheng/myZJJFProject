package com.corner.salesman.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.salesman.model.Dictionary;
import com.corner.salesman.model.TmplDetailInfo;
import com.corner.salesman.model.TmplInfo;
import com.corner.salesman.service.DictionaryService;
import com.corner.salesman.service.TmplDetailInfoService;
import com.corner.salesman.service.TmplInfoService;
import com.corner.scms.beans.CustomerService;
import com.corner.scms.controller.ScmsBaseWebController;

@Controller
@RequestMapping(value="/tmpl")
public class TemplateController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(TemplateController.class);
	@Autowired
	private TmplInfoService tmplInfoService;
	@Autowired
	private DictionaryService dictService;
	@Autowired
	private TmplDetailInfoService tmplDetailService;

	//转角模板  首页 
	@RequestMapping(value = "/index.do")
	public String toListPage(TmplInfo tmplVo, HttpServletRequest request, Model model) {
		return "salesman/template-list";
	}
	
	//转角模板  首页 
	@RequestMapping(value = "/toAddPage.do")
	public String toAddTmplPage(TmplInfo tmplVo, HttpServletRequest request, Model model) {
		List<Dictionary> tmplTypeList = null;
		try {
			tmplTypeList = dictService.findDictListByType("template_type");
		} catch (Exception e) {
			logger.info("获取模板信息列表异常：{}",e.getMessage());
		}
		model.addAttribute("tmplTypeList", tmplTypeList);
		
		return "salesman/template-add";
	}
	
	/**
	 * 跳转到模板信息编辑页面
	 * @param tmplVo
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toEditPage.do")
	public String toEditTmplPage(String pageType,TmplInfo tmplVo, HttpServletRequest request, Model model) {
		List<Dictionary> tmplTypeList = null;
		TmplInfo tmplInfo = null;
		String id = tmplVo.getId();
		try {
			tmplTypeList = dictService.findDictListByType("template_type");
			tmplInfo = tmplInfoService.findTmplInfoById(id);
		} catch (Exception e) {
			logger.info("获取模板信息列表异常：{}",e.getMessage());
		}
		model.addAttribute("tmplInfo", tmplInfo);
		model.addAttribute("tmplTypeList", tmplTypeList);
		
		return "salesman/template-edit";
	}
	
   /**
	* @Title: addTmplInfo 
	* @Description:   添加模板信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/addTmplInfo.do")
	@ResponseBody
	public Object addTmplInfo(TmplInfo tmplVo, HttpServletRequest request) {
		try {
			//logger.info("添加模板信息为："+JSONUtil.objectToJSONString(tmplVo));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			Date date = new Date();
			tmplVo.setCreateBy(supplier.getId());
			tmplVo.setCreateTime(date);
			tmplVo.setUpdateBy(supplier.getId());
			tmplVo.setUpdateTime(date);
			tmplVo.setId(StringUtil.getUUID());
			tmplVo.setIsDelete("0");
			
			int size = tmplInfoService.addTmplInfo(tmplVo);
			if(size>0){
				return ResponseUtils.sendMsg(true, "添加成功");
			}else{
				return ResponseUtils.sendMsg(false, "添加失败");
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "添加异常："+e);
		}
	}
	
	/**
	 * @Title: listPage 
	 * @Description: 模板分页查询列表
	 * @param request
	 * @param salesman
	 * @return Object    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/listPage.do")
	@ResponseBody
	public Object listPage(HttpServletRequest request, TmplInfo tmplVo) {
		try {
			//logger.info("列表查询开始："+JSONUtil.objectToJSONString(tmplVo));
			Pager<TmplInfo> pager = tmplInfoService.getTmplInfoPageList(tmplVo);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "新增模板异常："+e);
		}
	}
	
	/**
	 * 
	* @Title: delTmplInfo 
	* @Description:   删除模板信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/delTmplInfo.do")
	@ResponseBody
	public Object delTmplInfo(HttpServletRequest request, TmplInfo tmplVo) {
		try {
			String id = tmplVo.getId();
			if(StringUtils.isBlank(id)){
				return ResponseUtils.sendMsg(false, "删除数据的id不能为空！");
			}
			
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			tmplVo.setUpdateBy(supplier.getId());
			tmplVo.setUpdateTime(new Date());
			int size = tmplInfoService.deleteTmplInfo(id);
			if(size>0){
				return ResponseUtils.sendMsg(true, "删除成功");
			}else{
				return ResponseUtils.sendMsg(false, "删除失败");
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "删除异常："+e);
		}
	}
	
	/**
	 * 
	* @Title: delTmplInfo 
	* @Description:   删除模板信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws555
	 */
	@RequestMapping(value = "/updateTmplInfo.do")
	@ResponseBody
	public Object updateTmplInfo(HttpServletRequest request, TmplInfo tmplVo) {
		try {
			logger.info("修改模板信息参数："+JSONUtil.objectToJSONString(tmplVo));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			tmplVo.setUpdateBy(supplier.getId());
			tmplVo.setUpdateTime(new Date());
			int size = tmplInfoService.updateTmplInfo(tmplVo);
			if(size>0){
				return ResponseUtils.sendMsg(true, "修改成功");
			}else{
				return ResponseUtils.sendMsg(false, "修改失败");
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "修改异常："+e);
		}
	}
	
	/**
	 * 跳转到模板信息编辑页面
	 * @param tmplVo
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryTpmlDetail.do")
	public String queryTpmlDetail(TmplDetailInfo detailVo, HttpServletRequest request, Model model) {
		List<TmplDetailInfo> detailList = null;
		try {
			String tmplId = detailVo.getTmplId();
			detailList = tmplDetailService.findTmplDetailByTmplId(tmplId);
		} catch (Exception e) {
			logger.info("获取模板信息列表异常：{}",e.getMessage());
		}
		model.addAttribute("detailVo", detailVo);
		model.addAttribute("detailList", detailList);
		
		return "salesman/template-detail";
	}	

   /**
	* @Title: addTmplInfo 
	* @Description:   添加模板信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/addTmplDetail.do")
	@ResponseBody
	public Object addTmplDetail(String tmplId,int tmplTotal,String[] tmplTrVals, HttpServletRequest request) {
		try {
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			String userId = null;
			if(null != supplier){
				userId = supplier.getId();
			}
			
			if(tmplTotal>1){
				tmplDetailService.addTmplDetailInfo(tmplId,tmplTrVals, userId);
			}else{
				//如果属性小于0的情况则删除属性明细表对应模板的属性，反之则为一条数据的场景
				if(tmplTotal<=0){
					tmplDetailService.delTmplDetailInfo(tmplId);
				}else{
					tmplDetailService.addSingleTmplDetailInfo(tmplId, tmplTrVals, userId);
				}
			}
			
			return ResponseUtils.sendMsg(true, "保存成功");
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "保存异常："+e);
		}
	}

}
