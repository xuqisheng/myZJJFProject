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
import com.corner.rpc.shop.api.service.SpGroupService;
import com.corner.rpc.shop.model.Region;
import com.corner.rpc.shop.model.SpGroup;
import com.corner.salesman.model.Department;
import com.corner.salesman.model.SpGroupData;
import com.corner.salesman.service.DepartmentService;
import com.corner.salesman.service.SalesmanMgService;
import com.corner.salesman.service.SpGroupDataService;
import com.corner.scms.beans.CustomerService;
import com.corner.scms.controller.ScmsBaseWebController;
/**
 * 定格管理控制层
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/spgLine")
public class SpGroupDataController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(SpGroupDataController.class);
	@Autowired
	private SpGroupDataService spgDataService;
	@Autowired
	private SalesmanMgService salesmanService;
	@Autowired
	private DepartmentService deptService;
	@Autowired
	private RegionService regionService;

	//转角业务员  首页 
	@RequestMapping(value = "/index.do")
	public String toListPage(SpGroupData spgVo, HttpServletRequest request, Model model) {
		return "salesman/spGroupLine-list";
	}
	
	//转角业务员  首页 
	@RequestMapping(value = "/toAddPage.do")
	public String addSpGroupDataPage(SpGroupData spgVo, HttpServletRequest request, Model model) {
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
		model.addAttribute("spgVo", spgVo);
		model.addAttribute("deptList", deptList);
		model.addAttribute("provinceList", provinceList);
		
		return "salesman/spGroupLine-add";
	}
	
	/**
	 * 加载修改定格路线信息
	 * @param spgVo
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toEditPage.do")
	public String toEditDeptPage(SpGroupData spgVo, HttpServletRequest request, Model model) {
		List<Department> deptList = null;
		List<Region> provinceList = null;
		try {
			deptList = deptService.findDeptList();
			
			String spGroupId = spgVo.getSpGroupId();
			SpGroupData headerVO = spgDataService.findSpGroupDataById(spgVo);
			List<SpGroupData> lineList = spgDataService.getSpgLineData(spGroupId);
			//加载店宝省份数据列表
			Region region = new Region();
			region.setpId(1);
			provinceList = regionService.queryRegionList(region);
			
			model.addAttribute("headerVO", headerVO);
			model.addAttribute("lineList", lineList);
			model.addAttribute("deptList", deptList);
			model.addAttribute("provinceList", provinceList);
			
		} catch (Exception e) {
			logger.info("加载修改定格路线信息异常：{}",e.getMessage());
		}
		return "salesman/spGroupLine-edit";
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
	@RequestMapping(value = "/addSpGroupData.do")
	@ResponseBody
	public Object addSpGroupData(SpGroupData spgVo, HttpServletRequest request) {
		try {
			logger.info("添加部门信息为："+JSONUtil.objectToJSONString(spgVo));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			Date date = new Date();
			spgVo.setCreateBy(supplier.getId());
			spgVo.setCreateTime(date);
			spgVo.setUpdateBy(supplier.getId());
			spgVo.setUpdateTime(date);
			spgVo.setIsDelete(0);
			String spGroupId = spgVo.getSpGroupId();
			String deptId = spgVo.getDeptId();
			String userIdSet = spgVo.getUserIdSet();
			String opType = spgVo.getOpType();
			
			
			if(StringUtils.isBlank(spGroupId)){
				return ResponseUtils.sendMsg(false, "定格ID不能为空！");
			}
			if(StringUtils.isBlank(userIdSet)){
				return ResponseUtils.sendMsg(false, "DB代表不能为空！");
			}
			if(StringUtils.isBlank(deptId)){
				return ResponseUtils.sendMsg(false, "部门编码不能为空！");
			}
			
			//如果opType为空表示为新增数据，必须校验，反正为修改直接删除重新insert即可
			if(StringUtils.isBlank(opType)){
				int spgNum = spgDataService.checkSpGroupDataIsExist(spgVo.getSpGroupId());
				if(spgNum>0){
					return ResponseUtils.sendMsg(false, "当前定格已经存在！");
				}
			}
			
			int size = spgDataService.addSpGroupData(spgVo);
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
	public Object listPage(HttpServletRequest request, SpGroupData spgVo) {
		try {
			logger.info("列表查询开始："+JSONUtil.objectToJSONString(spgVo));
			Pager<SpGroupData> pager = spgDataService.getSpGroupDataPageList(spgVo);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "查询定格路线信息异常："+e);
		}
	}
	
	/**
	 * 
	* @Title: delSpGroupData 
	* @Description:   删除定格路线信息
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/delSpGroupData.do")
	@ResponseBody
	public Object delSpGroupData(HttpServletRequest request, SpGroupData spgVo) {
		try {
			String id = spgVo.getSpGroupId();
			if(id==null){
				return ResponseUtils.sendMsg(false, "删除数据的SpGroupId不能为空！");
			}
	
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			spgVo.setUpdateBy(supplier.getId());
			spgVo.setUpdateTime(new Date());
			int size = spgDataService.deleteSpGroupData(spgVo);
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
	* @Title: delSpGroupData 
	* @Description:   修改定格路线信息
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws555
	 */
	@RequestMapping(value = "/editSpGroupData.do")
	@ResponseBody
	public Object editSpGroupData(HttpServletRequest request, SpGroupData spgVo) {
		try {
			logger.info("修改部门信息参数："+JSONUtil.objectToJSONString(spgVo));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			spgVo.setUpdateBy(supplier.getId());
			spgVo.setUpdateTime(new Date());
			int size = spgDataService.updateSpGroupData(spgVo);
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
	* @Title: syncSpGroupData 
	* @Description:   同步定格路线
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws555
	 */
	@RequestMapping(value = "/syncSpGroupData.do")
	@ResponseBody
	public Object syncSpGroupData(HttpServletRequest request, SpGroupData spgVo) {
		try {
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			Date date = new Date();
			spgVo.setUpdateBy(supplier.getId());
			spgVo.setUpdateTime(date);
			spgVo.setCreateBy(supplier.getId());
			spgVo.setCreateTime(date);
		
			spgDataService.saveSyncSpGroupData(spgVo);
			//3、返回页面知会刷新页面数据
			return ResponseUtils.sendMsg(true, "修改成功");
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "修改异常："+e.getMessage());
		}
	}
}
