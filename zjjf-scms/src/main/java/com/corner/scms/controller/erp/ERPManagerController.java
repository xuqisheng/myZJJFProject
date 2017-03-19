package com.corner.scms.controller.erp;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ERPManager;
import com.corner.core.beans.Region;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.enums.ManagerStatus;
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.beans.ro.erp.ERPManagerRo;
import com.corner.scms.beans.vo.erp.ERPManagerVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.PublicService;
import com.corner.scms.service.erp.ERPManagerService;

@Controller
@RequestMapping("/scms/ERPMa")
public class ERPManagerController extends ScmsBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(ERPManagerController.class);
	@Autowired ERPManagerService managerService;
	
	@Autowired PublicService publicService;
	/**
	 * 获取批发商所有的供应商
	* @Title
	* @Description: TODO 
	* @param @param manager
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("getAllERPManager.do")
	public String getAllERPManager(ERPManagerRo manager,Model model,HttpServletRequest request){
		Supplier supplier = getCurrentUser(Supplier.class, request);
		if(supplier != null){
			manager.setSupplierId(supplier.getId());
		}else{
			return error("当前用户session已过期", model, request);
		}
		if(manager.getCodeOrName()!=null && !"".equals(manager.getCodeOrName())){
			manager.setCodeOrName(manager.getCodeOrName().trim());
		}
		model.addAttribute("managerRo", manager);
		try {
			Pager<ERPManagerVo> pager = managerService.getAllERPManager(manager);
			if(pager != null){
				pageUtil(manager.getPageIndex(), pager.getTotalSize(), manager.getPageSize(), request, model);
                model.addAttribute("menagerStatus", ManagerStatus.values());
				model.addAttribute("managerList", pager.getList());
				return "/erp/supplier/list";
			}else{
				return error("程序异常，请联系技术人员", model, request);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());
			return error("程序异常，请联系技术人员", model, request);
		}
	}
	/**
	 * 获取批发商所有的供应商
	 */
	@RequestMapping("getAllERPManagerJSON")
	@ResponseBody
	public Object getAllERPManagerJSON(ERPManagerRo manager,Model model,HttpServletRequest request){
		Supplier supplier = getCurrentUser(Supplier.class, request);
		manager.setSupplierId(supplier.getId());
		model.addAttribute("managerRo", manager);
		try {
			Pager<ERPManagerVo> pager = managerService.getAllERPManager(manager);
			pager.setFlag(true);
			return pager;
		} catch (Exception e) {
			logger.info(e.toString());
			return error("程序异常，请联系技术人员", model, request);
		}
	}
	
	/**
	 * 返回添加和编辑经销商页面
	* @Title
	* @Description: TODO 
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月2日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("returnAddOrEditPage.do")
	public String returnAddOrEditPage(Model model,HttpServletRequest request){
		//获取页面传过来的参数
		String action = request.getParameter("action");
		if(action == null || "".equals(action)){
			return error("请求有误,请重试", model, request);
		}
		//获取省
		List<Region> provinceList = publicService.getRetionByPid(1);
		model.addAttribute("provinceList", provinceList);
		if(action.equals("2")){
			String id = request.getParameter("id");
			if(id == null || "".equals(id)){
				return error("数据有误,未获取到供应商id", model, request);
			}
			//根据id获取经销商
			ERPManager manager = managerService.getERPManagerById(id);
			if(manager != null){
				model.addAttribute("manager", manager);
				//获取市
				if(manager.getProvinceId() != null){
					List<Region> cityList = publicService.getRetionByPid(manager.getProvinceId());
					model.addAttribute("cityList", cityList);
				}
				//获取区
				if(manager.getCityId() != null){
					List<Region> areaList = publicService.getRetionByPid(manager.getCityId());
					model.addAttribute("areaList", areaList);
				}
			}
		}
		return "/erp/supplier/edit";
	}
	
	@ResponseBody
	@RequestMapping("cascade.do")
	public Object cascade(Integer pId,Model model,HttpServletRequest request){
		try {
			List<Region> regionList = null;
			if(pId != null){
				regionList = publicService.getRetionByPid(pId);
			}
			return ResponseUtils.sendMsg(true, regionList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());
			return ResponseUtils.sendMsg(false, "程序异常,请联系技术人员");
		}
	}
	
	
	/**
	 * 批发商添加供应商
	* @Title
	* @Description: TODO 
	* @param @param manager
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("addERPManager.do")
	public Object addERPManager(ERPManagerRo manager,Model model,HttpServletRequest request){
		Supplier supplier = getCurrentUser(Supplier.class, request);
		if(supplier!=null){
			manager.setSupplierId(supplier.getId());
		}else{
			return ResponseUtils.sendMsg(false, "当前用户session已过期");
		}
		if(manager.getManagerName()==null ||
			"".equals(manager.getManagerName())||
			manager.getProvinceId()==null ||
			manager.getCityId()==null||
			manager.getAreaId()==null||
			manager.getAddress()==null||
			"".equals(manager.getAddress())||
			manager.getMobile()==null||
			"".equals(manager.getMobile())||
			manager.getBranderName()==null||
			"".equals(manager.getBranderName())||
			manager.getCleaningDay()==null||
			manager.getBankUserName()==null||
			"".equals(manager.getBankUserName())||
			manager.getBankName()==null ||
			"".equals(manager.getBankName())||
			manager.getBankNum()==null||
			"".equals(manager.getBankNum())||
			manager.getTaxNumber()==null||
			"".equals(manager.getTaxNumber())||
			manager.getStatus()==null
			){
			return ResponseUtils.sendMsg(false, "参数有误,请重新输入");
		}
		manager.setAddTime(new Date());
		manager.setUpdateTime(new Date());
		try {
			ModelMsg modelMsg = managerService.addERPManager(manager);
			if(modelMsg.isSuccess()){
				return ResponseUtils.sendMsg(true, modelMsg.getMessage());
			}else{
				return ResponseUtils.sendMsg(false, modelMsg.getMessage());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());
			return ResponseUtils.sendMsg(false, "程序异常,添加失败");
		}
	}
	
	/**
	 * 批发商编辑供应商
	* @Title
	* @Description: TODO 
	* @param @param manager
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("updateERPManager.do")
	public Object updateERPManager(ERPManagerRo manager,Model model,HttpServletRequest request){
		if(manager.getId()==null){
			return ResponseUtils.sendMsg(false, "数据有误,未获取到供应商id");
		}
		if(manager.getManagerName()==null ||
			"".equals(manager.getManagerName())||
			manager.getProvinceId()==null ||
			manager.getCityId()==null||
			manager.getAreaId()==null||
			manager.getAddress()==null||
			"".equals(manager.getAddress())||
			manager.getMobile()==null||
			"".equals(manager.getMobile())||
			manager.getBranderName()==null||
			"".equals(manager.getBranderName())||
			manager.getCleaningDay()==null||
			manager.getBankUserName()==null||
			"".equals(manager.getBankUserName())||
			manager.getBankName()==null ||
			"".equals(manager.getBankName())||
			manager.getBankNum()==null||
			"".equals(manager.getBankNum())||
			manager.getTaxNumber()==null||
			"".equals(manager.getTaxNumber())||
			manager.getStatus()==null
			){
			return ResponseUtils.sendMsg(false, "参数有误,请重新输入");
		}
		manager.setUpdateTime(new Date());
		try {
			ModelMsg modelMsg = managerService.updateERPManager(manager);
			if(modelMsg.isSuccess()){
				return ResponseUtils.sendMsg(true, modelMsg.getMessage());
			}else{
				return ResponseUtils.sendMsg(false, modelMsg.getMessage());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());
			return ResponseUtils.sendMsg(false, "程序异常,编辑失败");
		}
	}
	
	/**
	 * 批发商删除供应商
	* @Title
	* @Description: TODO 
	* @param @param id
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("deleteERPManager.do")
	@ResponseBody
	public Object deleteERPManager(String id){
		if(id == null){
			return ResponseUtils.sendMsg(false, "数据有误,未获取到供应商id");
		}
		try {
			boolean flag = managerService.deleteERPManager(id);
			if(flag){
				return ResponseUtils.sendMsg(true, "删除成功");
			}else{
				return ResponseUtils.sendMsg(false, "删除失败");
			}
		} catch (Exception e) {
			logger.info(e.toString());
			return ResponseUtils.sendMsg(false, "程序异常,删除失败");
		}
	}

	@RequestMapping("ERPManagerDetail/{id}")
	public String ERPMangerDetail(@PathVariable("id") String id , Model model){
		model.addAttribute("detail" , managerService.getERPManagerById(id));
		return "erp/supplier/detail";
	}
}
