package com.corner.salesman.controller;

import java.util.Date;

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
import com.corner.salesman.model.Dictionary;
import com.corner.salesman.service.DictionaryService;
import com.corner.scms.beans.CustomerService;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.utils.IdGen;

@Controller
@RequestMapping(value="/dict")
public class DictionaryController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(DictionaryController.class);
	@Autowired
	private DictionaryService dictService;

	//转角业务员  首页 
	@RequestMapping(value = "/index.do")
	public String toListPage(Dictionary dictVo, HttpServletRequest request, Model model) {
		return "salesman/dict-list";
	}
	
	//转角业务员  首页 
	@RequestMapping(value = "/toAddDict.do")
	public String toAddSalesman(Dictionary dictVo, HttpServletRequest request, Model model) {
		//计算默认排序空的时候默认10，非空再原来基础加10
		Long sort = dictVo.getSort()==null?10:dictVo.getSort()+10;
		dictVo.setSort(sort);
		model.addAttribute("dictVo", dictVo);
		return "salesman/dict-add";
	}
	
	/**
	 * 跳转到用户编辑页面
	 * @param dictVo
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toEditPage.do")
	public String editDictPage(Dictionary dictVo, HttpServletRequest request, Model model) {
		String id = dictVo.getId();
		try {
			dictVo = dictService.findDictionaryById(id);
		} catch (Exception e) {
			logger.error("根据ID查询数据信息异常：{}",e.getMessage());
		}
		model.addAttribute("dictVo", dictVo);
		return "salesman/dict-edit";
	}
	
	/**
	 * 
	* @Title: addDictInfo 
	* @Description:   添加业务员信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/addDict.do")
	@ResponseBody
	public Object addDictInfo(Dictionary dictVo, HttpServletRequest request) {
		try {
			logger.info("添加字典信息为："+JSONUtil.objectToJSONString(dictVo));
			
			if(dictService.isExit_type_value_recode(dictVo)){
				return ResponseUtils.sendMsg(false, "类型为: " + dictVo.getType() + "; 键值为:" + dictVo.getValue() + "的字典记录已经存在！");
			}
			String type = dictVo.getType();
			if(StringUtils.isNotBlank(type) && "time_hz_type".equals(type)&& "time_hz_val".equals(dictVo.getLabel())){
				String value = dictVo.getValue();
				try{
					Integer.parseInt(value);
				}catch(Exception e){
					return ResponseUtils.sendMsg(false, "类型为: " + dictVo.getType() + "; 对应的键值:【" + dictVo.getValue() + "】只能为数字！");
				}
			}
			
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			Date date = new Date();
			dictVo.setCreateBy(supplier.getId());
			dictVo.setCreateTime(date);
			dictVo.setUpdateBy(supplier.getId());
			dictVo.setUpdateTime(date);
			dictVo.setId(IdGen.uuid());
			int size = dictService.addDictionary(dictVo);
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
	 * @Description: 字典分页查询列表
	 * @param request
	 * @param salesman
	 * @return Object    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/listPage.do")
	@ResponseBody
	public Object listPage(HttpServletRequest request, Dictionary dictVo) {
		try {
			logger.info("列表查询开始："+JSONUtil.objectToJSONString(dictVo));
			Pager<Dictionary> pager = dictService.getDictPageList(dictVo);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "新增业务员异常："+e);
		}
	}
	
	/**
	 * 
	* @Title: delDictionary 
	* @Description:   删除字典信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/delDict.do")
	@ResponseBody
	public Object delDictInfo(HttpServletRequest request, Dictionary dictVo) {
		try {
			logger.info("删除字典："+JSONUtil.objectToJSONString(dictVo));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			dictVo.setUpdateBy(supplier.getId());
			dictVo.setUpdateTime(new Date());
			int size = dictService.deleteDictionary(dictVo);
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
	* @Title: delDictionary 
	* @Description:   删除字典信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws555
	 */
	@RequestMapping(value = "/editDict.do")
	@ResponseBody
	public Object editDictInfo(HttpServletRequest request, Dictionary dictVo) {
		try {
			//logger.info("修改字典信息参数："+JSONUtil.objectToJSONString(dictVo));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			dictVo.setUpdateBy(supplier.getId());
			dictVo.setUpdateTime(new Date());
			//针对抓取用户坐标的时间频率做校验，只允许数字类型才可以
			String type = dictVo.getType();
			if(StringUtils.isNotBlank(type) && "time_hz_type".equals(type)&& "time_hz_val".equals(dictVo.getLabel())){
				String value = dictVo.getValue();
				try{
					Integer.parseInt(value);
				}catch(Exception e){
					return ResponseUtils.sendMsg(false, "类型为: " + dictVo.getType() + "; 对应的键值:【" + dictVo.getValue() + "】只能为数字！");
				}
			}
			
			int size = dictService.updateDictionary(dictVo);
			if(size>0){
				return ResponseUtils.sendMsg(true, "修改成功");
			}else{
				return ResponseUtils.sendMsg(false, "修改失败");
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "修改异常："+e);
		}
	}
}
