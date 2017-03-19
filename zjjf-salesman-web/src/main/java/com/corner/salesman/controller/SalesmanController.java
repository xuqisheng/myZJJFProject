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
import com.corner.core.utils.safe.MD5;
import com.corner.salesman.model.Department;
import com.corner.salesman.model.Dictionary;
import com.corner.salesman.model.Json;
import com.corner.salesman.model.SalesmanMg;
import com.corner.salesman.service.DepartmentService;
import com.corner.salesman.service.DictionaryService;
import com.corner.salesman.service.SalesmanMgService;
import com.corner.scms.beans.CustomerService;
import com.corner.scms.controller.ScmsBaseWebController;
@Controller
@RequestMapping(value="/account")
public class SalesmanController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(SalesmanController.class);
	@Autowired
	private SalesmanMgService salesmanService;
	@Autowired
	private DepartmentService deptService;
	@Autowired
	private DictionaryService dictService;
	
	//转角业务员  首页 
	@RequestMapping(value = "/index.do")
	public String toIndexPage(SalesmanMg salesman, HttpServletRequest request, Model model) {
		List<Department> deptList = null;
		try {
			deptList = deptService.findDeptList();
		} catch (Exception e) {
			logger.info("获取部门信息列表异常：{}",e.getMessage());
		}
		model.addAttribute("deptList", deptList);
		return "salesman/index";
	}
	
	//转角业务员  首页 
	@RequestMapping(value = "/toAddSalesman.do")
	public String toAddSalesman(SalesmanMg salesman, HttpServletRequest request, Model model) {
		List<Department> deptList = null;
		List<Dictionary> dictList = null;
		try {
			deptList = deptService.findDeptList();
			dictList = dictService.findDictListByType("post_type");
		} catch (Exception e) {
			logger.info("获取部门信息列表异常：{}",e.getMessage());
		}
		model.addAttribute("dictList", dictList);
		model.addAttribute("deptList", deptList);
		
		return "salesman/salesman-add";
	}
	
	/**
	 * @Title: toLeaderPage 
	 * @Description:跳转到部门领导列表页 
	 * @param salesman
	 * @param request
	 * @param model
	 * @return Object    返回类型
	 * @author yuanbao@izjjf.cn
	 */
	@RequestMapping(value = "/toUserList.do")
	public String toUserList(SalesmanMg salesman, HttpServletRequest request, Model model) {
		model.addAttribute("salesman", salesman);
		return "salesman/user-list";
	}
	
	/**
	 * @Title: toSalesmanPage 
	 * @Description:跳转到业务员列表页 
	 * @param salesman
	 * @param request
	 * @param model
	 * @return Object    返回类型
	 * @author yuanbao@izjjf.cn
	 */
	@RequestMapping(value = "/toSalesmanPage.do")
	public String toSalesmanPage(SalesmanMg salesman, HttpServletRequest request, Model model) {
		
		try {
			List<SalesmanMg> list = salesmanService.getSalesmanList(salesman);
			model.addAttribute("list", list);
		} catch (Exception e) {
			logger.info("获取业务员信息列表异常：{}",e.getMessage());
		}
		
		return "salesman/salesmanList";
	}
	
	/**
	 * @Title: leaderList 
	 * @Description:部门领导列表方法
	 * @param salesman
	 * @param request
	 * @param model
	 * @return Object    返回类型
	 * @author yuanbao@izjjf.cn
	 */
	@RequestMapping(value = "/selectUserList.do")
	@ResponseBody
	public Object selectUserList(SalesmanMg salesman, HttpServletRequest request, Model model) {
		try {
			logger.info("查询部门领导参数：{}",JSONUtil.objectToJSONString(salesman));
			Pager<SalesmanMg> pager = salesmanService.getUserPageList(salesman);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "查询部门领导异常："+e);
		}
	}
	
	/**
	 * @Title: listPage 
	 * @Description: 分页查询业务员信息 
	 * @param request
	 * @param salesman
	 * @return Object    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/listPage.do")
	@ResponseBody
	public Object listPage(HttpServletRequest request, SalesmanMg salesman) {
		try {
			logger.info("列表查询开始："+JSONUtil.objectToJSONString(salesman));
			Pager<SalesmanMg> pager = salesmanService.getPageList(salesman);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "新增业务员异常："+e);
		}
	}
	
	/**
	 * 
	* @Title: addSalesman 
	* @Description:   添加业务员信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/addSalesman.do")
	@ResponseBody
	public Object addSalesman(HttpServletRequest request, SalesmanMg salesman) {
		try {
			logger.info("添加业务员："+JSONUtil.objectToJSONString(salesman));
			//校验帐号信息是否已经存在
			String mobile = salesman.getMobile();
			int num = salesmanService.chekAccountIsExist(mobile);
			if(num>0){
				return ResponseUtils.sendMsg(false, "该帐号（手机号）已注册过！");
			}
			
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			String password = salesman.getPassword();
			if(StringUtils.isNotBlank(password)){
				String md5Pwd = MD5.StringToMd5(password);
				salesman.setPassword(md5Pwd);
			}
			Date date = new Date();
			salesman.setId(StringUtil.getUUID());
			salesman.setCreateBy(supplier.getId());
			salesman.setCreateTime(date);
			salesman.setUpdateBy(supplier.getId());
			salesman.setUpdateTime(date);
			int size = salesmanService.addSalesmanMg(salesman);
			if(size>0){
				return ResponseUtils.sendMsg(true, "添加成功");
			}else{
				return ResponseUtils.sendMsg(false, "添加失败");
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "添加异常："+e);
		}
	}
	
	//转角业务员  首页 
	@RequestMapping(value = "/toEditSalesman.do")
	public String toEditSalesman(SalesmanMg salesman, HttpServletRequest request, Model model) {
		List<Department> deptList = null;
		List<Dictionary> dictList = null;
		try {
			deptList = deptService.findDeptList();
			dictList = dictService.findDictListByType("post_type");
		} catch (Exception e) {
			logger.info("获取部门信息列表异常：{}",e.getMessage());
		}
		
		try {
			String id = salesman.getId();
			logger.info("查询用户ID={}，部门id={}",id,salesman.getDeptId());
			salesman =  salesmanService.findSalesmanMgById(salesman); 
			
		} catch (Exception e) {
			logger.info("根据用户ID查询业务员信息异常={}",e.getMessage());
		}
		
		model.addAttribute("dictList", dictList);
		model.addAttribute("deptList", deptList);
		model.addAttribute("salesman", salesman);
		model.addAttribute("longx", salesman);
		return "salesman/salesman-edit";
	}
	
	/**
	 * 
	* @Title: updateSalesman 
	* @Description:   修改业务员信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/updateSalesman.do")
	@ResponseBody
	public Object updateSalesman(HttpServletRequest request, SalesmanMg salesman) {
		try {
			logger.info("修改业务员："+JSONUtil.objectToJSONString(salesman));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			salesman.setUpdateBy(supplier.getId());
			
			String password = salesman.getPassword();
			if(StringUtils.isNotBlank(password)){
				String md5Pwd = MD5.StringToMd5(password);
				salesman.setPassword(md5Pwd);
			}
			salesman.setUpdateTime(new Date());
			salesmanService.updateSalesmanMg(salesman);
			return ResponseUtils.sendMsg(true, "修改成功");
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "修改异常："+e);
		}
	}
	
   /**
	* @Title: delSalesmanMg 
	* @Description:   是否删除业务员信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	*/
	@RequestMapping(value = "/delSalesmanMg.do")
	@ResponseBody
	public Object delSalesmanMg(HttpServletRequest request, SalesmanMg salesman) {
		try {
			logger.info("删除业务员："+JSONUtil.objectToJSONString(salesman));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			salesman.setUpdateBy(supplier.getId());
			salesman.setUpdateTime(new Date());
			int size = salesmanService.delSalesmanMg(salesman);
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
	 * 
	 * @Title: findById 
	 * @Description: 根据用户ID查询业务员信息
	 * @param @param request
	 * @param @param command
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/findById.do")
	public String findSalesmanById(HttpServletRequest request, SalesmanMg salesman,Model model) {
		try {
			String id = salesman.getId();
			logger.info("查询用户ID={}",id);
			salesman =  salesmanService.findSalesmanMgById(salesman); 
			model.addAttribute("salesman", salesman);
		} catch (Exception e) {
			logger.info("根据用户ID查询业务员信息异常={}",e.getMessage());
		}
		return null;
	}

   /**
	* @Title: checkDeptRelation
	* @Description: 检查是否为其他部门的管理者
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	*/
	@RequestMapping(value = "/checkDeptRelation.do")
	@ResponseBody
	public Object checkDeptRelation(HttpServletRequest request, String userId) {
		Json json = new Json();
		try {
			 if(StringUtils.isBlank(userId)){
				json.setMsg("用户ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 String deptName = deptService.checkIsDeptLeader(userId);
			 json.setData(deptName);
			 json.setMsg("获取部门关系成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("获取部门关系异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		return json;
	}
	
}
