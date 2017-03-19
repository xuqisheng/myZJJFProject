package com.corner.salesman.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
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
import com.corner.rpc.shop.api.service.RegionService;
import com.corner.rpc.shop.model.Region;
import com.corner.salesman.model.Department;
import com.corner.salesman.model.SalesmanMg;
import com.corner.salesman.model.SpGroupData;
import com.corner.salesman.service.DepartmentService;
import com.corner.salesman.service.SalesmanMgService;
import com.corner.salesman.service.SpGroupDataService;
import com.corner.scms.beans.CustomerService;
import com.corner.scms.controller.ScmsBaseWebController;

@Controller
@RequestMapping(value="/dept")
public class DepartmentController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	private DepartmentService deptService;
	@Autowired
	private SalesmanMgService salesmanService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private SpGroupDataService spgDataService;

	//转角业务员  首页 
	@RequestMapping(value = "/index.do")
	public String toListPage(Department department, HttpServletRequest request, Model model) {
		return "salesman/department-list";
	}
	
	//转角业务员  首页 
	@RequestMapping(value = "/toAddDepartment.do")
	public String toAddSalesman(Department department, HttpServletRequest request, Model model) {
		List<Department> deptList = null;
		List<Region> provinceList = null;
		try {
			deptList = deptService.findDeptList();
			//加载店宝省份数据列表
			Region region = new Region();
			region.setpId(1);
			provinceList = regionService.queryRegionList(region);
		} catch (Exception e) {
			logger.info("获取部门信息列表异常：{}",e.getMessage());
		}
		model.addAttribute("deptList", deptList);
		model.addAttribute("provinceList", provinceList);
		
		return "salesman/department-add";
	}
	
	/**
	 * 跳转到用户编辑页面
	 * @param department
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toEditPage.do")
	public String toEditDeptPage(String pageType,Department department, HttpServletRequest request, Model model) {
		List<Department> deptList = null;
		List<SalesmanMg> leaderList = null;
		List<Region> provinceList = null;
		List<SpGroupData> spgList = null;
		
		try {
			String deptId = department.getDeptId();
			deptList = deptService.findDeptList();
			department = deptService.findDepartmentById(deptId);
			leaderList = salesmanService.getDeptLeaderList(deptId);
			SpGroupData spgVO = new SpGroupData();
			//将部门对象上的部门ID及省市区的ID复制到定格对象上作为查询条件
			BeanUtils.copyProperties(spgVO, department);
			spgList = spgDataService.getSpGroupBindDeptList(spgVO);
			
			//加载店宝省份数据列表
			Region region = new Region();
			region.setpId(1);
			provinceList = regionService.queryRegionList(region);
		} catch (Exception e) {
			logger.info("获取部门信息列表异常：{}",e.getMessage());
		}
		model.addAttribute("dept", department);
		model.addAttribute("deptList", deptList);
		model.addAttribute("leaderList", leaderList);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("spgList", spgList);
		
		if("view".equals(pageType)){
			return "salesman/department-view";
		}
		return "salesman/department-edit";
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
	@RequestMapping(value = "/addDeptInfo.do")
	@ResponseBody
	public Object addDeptInfo(Department department, HttpServletRequest request) {
		try {
			logger.info("添加部门信息为："+JSONUtil.objectToJSONString(department));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			Date date = new Date();
			department.setCreateBy(supplier.getId());
			department.setCreateTime(date);
			department.setUpdateBy(supplier.getId());
			department.setUpdateTime(date);
			String deptId = department.getDeptId();
			if(StringUtils.isBlank(deptId)){
				return ResponseUtils.sendMsg(false, "部门编码不能为空！");
			}
			
			int deptNum = deptService.checkDeptIsExist(deptId);
			if(deptNum>0){
				return ResponseUtils.sendMsg(false, "部门编码已经存在！");
			}
			
			int size = deptService.addDepartment(department);
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
	 * @Description: 部门分页查询列表
	 * @param request
	 * @param salesman
	 * @return Object    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/listPage.do")
	@ResponseBody
	public Object listPage(HttpServletRequest request, Department department) {
		try {
			logger.info("列表查询开始："+JSONUtil.objectToJSONString(department));
			Pager<Department> pager = deptService.getDeptPageList(department);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "新增业务员异常："+e);
		}
	}
	
	/**
	 * 
	* @Title: delDepartment 
	* @Description:   删除部门信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/delDeptInfo.do")
	@ResponseBody
	public Object delDeptInfo(HttpServletRequest request, Department deptVo) {
		try {
			Integer id = deptVo.getId();
			String deptId = deptVo.getDeptId();
			if(id==null){
				return ResponseUtils.sendMsg(false, "删除数据的id不能为空！");
			}
			if(StringUtils.isBlank(deptId)){
				return ResponseUtils.sendMsg(false, "部门ID不能为空！");
			}
			
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			deptVo.setUpdateBy(supplier.getId());
			deptVo.setUpdateTime(new Date());
			int size = deptService.deleteDepartment(deptVo);
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
	* @Title: delDepartment 
	* @Description:   删除部门信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws555
	 */
	@RequestMapping(value = "/editDeptInfo.do")
	@ResponseBody
	public Object editDeptInfo(HttpServletRequest request, Department deptVo) {
		try {
			logger.info("修改部门信息参数："+JSONUtil.objectToJSONString(deptVo));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			deptVo.setUpdateBy(supplier.getId());
			deptVo.setUpdateTime(new Date());
			int size = deptService.updateDepartment(deptVo);
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
