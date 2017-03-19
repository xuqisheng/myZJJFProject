package com.corner.kefu.controller.erp;

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

import com.corner.core.beans.ERPManager;
import com.corner.core.beans.Region;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.kefu.beans.ro.erp.ERPManagerRo;
import com.corner.kefu.beans.ro.erp.ERPWarehouseRo;
import com.corner.kefu.beans.ro.erp.ERPmanagerItemRo;
import com.corner.kefu.beans.vo.erp.ERPManagerItemVo;
import com.corner.kefu.beans.vo.erp.ERPManagerVo;
import com.corner.kefu.beans.vo.erp.ERPWarehouseVo;
import com.corner.kefu.beans.vo.sp.SupplierVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.PublicService;
import com.corner.kefu.service.erp.ERPManagerService;
import com.corner.kefu.service.sp.ERPWarehouseService;
import com.corner.kefu.service.sp.SpSupplierService;

@Controller
@RequestMapping("/ERPMa")
public class ERPManagerController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(ERPManagerController.class);
	@Autowired ERPManagerService managerService;
	
	@Autowired PublicService publicService;
	@Autowired
	ERPWarehouseService eRPWarehouseService;
	
	@Autowired
	SpSupplierService spSupplierService;
	
	/**
	 * 
	* @Title: getJsonErpManagerItem 
	* @Description:获取ERPmagerItem 商品
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getJsonErpManagerItem.do")
	@ResponseBody
	public Object getJsonErpManagerItem(ERPmanagerItemRo itemRo) {
		Pager<ERPManagerItemVo> pager = managerService.getJsonErpManagerItem(itemRo);
		pager.setFlag(true);
		return pager;
	}
	
	
	
	/**
	 * 
	* @Title: getAllJsonErpWareHose 
	* @Description:获取所有仓库数据
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getAllJsonErpWareHose.do")
	@ResponseBody
	public Object getAllJsonErpWareHose(ERPWarehouseRo warehouseRo) {
		Pager<ERPWarehouseVo> pager = eRPWarehouseService.getAllJsonErpWareHose(warehouseRo);
		pager.setFlag(true);
		return pager;
	}
	
	
	
	
	
	/**
	 * 
	* @Title: saveManagerSupplier 
	* @Description:保存供应商和批发商的关系
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/saveManagerSupplier.do")
	@ResponseBody
	public Object saveManagerSupplier(ERPManagerRo managerRo,HttpServletRequest request) {
		if(StringUtils.isEmpty(managerRo.getId())){
			return ResponseUtils.sendMsg(false, "缺少供应商id!");
		}
		String spIdArr[] = request.getParameterValues("spIdArr");
		if(spIdArr==null||spIdArr.length==0){
			return ResponseUtils.sendMsg(false, "缺少批发商id数据!");
		}
		managerRo.setSpIdArr(spIdArr);
		ModelMsg msg = managerService.saveManagerSupplier(managerRo);
		if(msg.isSuccess()){
			return ResponseUtils.sendMsg(true);
		}else {
			return ResponseUtils.sendMsg(true,msg.getMessage());
		}
	}
	
	
	
	
	@RequestMapping("/toManageList.do")
	public String toManageList() {
		return "/erp/erpManager/list";
	}
	
	
	
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
	@RequestMapping("/getAllERPManager.do")
	@ResponseBody
	public Object getAllERPManager(ERPManagerRo manager,Model model,HttpServletRequest request){
		Pager<ERPManagerVo> pager = managerService.getAllERPManager(manager);
		if(pager!=null){
			pager.setFlag(false);
		}
		pager.setFlag(true);
		return pager;
	}

	/**
	 * 
	* @Title: getERPManagerByWhId 
	* @Description:根据仓库id获取供应商列表
	* @param @param managerRo
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getERPManagerByWhId.do")
	@ResponseBody
	public Object getERPManagerByWhId(ERPManagerRo managerRo) {
		Pager<ERPManagerVo> pager = managerService.getERPManagerByWhId(managerRo);
		if(pager!=null){
			pager.setFlag(false);
		}
		pager.setFlag(true);
		return pager;
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
			model.addAttribute("manager", manager);
			
			/*if(manager != null){
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
			}*/
		}
		return "/erp/erpManager/edit";
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
	* @Title: toSetDistribute 
	* @Description:跳转到设置批发商页面
	* @param @param manager
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toSetDistribute.do")
	public String toSetDistribute(ERPManagerRo manager,HttpServletRequest request,Model model) {
		if(StringUtils.isEmpty(manager.getId())){
			return error("缺少供应商id", model, request);
		}
		List<SupplierVo> spList = spSupplierService.getMnagerSupplierList(manager);
		model.addAttribute("spList", spList);
	    model.addAttribute("managerId", manager.getId());
		return "erp/erpManager/setDistribute";
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
	@RequestMapping("/addERPManager.do")
	public Object addERPManager(ERPManagerRo manager,Model model,HttpServletRequest request){
		/*if(manager.getManagerName()==null ||
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
		}*/
		if(manager.getCleaningDayStatus().intValue()==2){//账期
			if(manager.getCleaningDay()==null){
				return ResponseUtils.sendMsg(false, "账期天数不能为空!");
			}
		}
		manager.setAddTime(new Date());
		manager.setUpdateTime(new Date());
		try {
			ModelMsg modelMsg = managerService.addERPManager(manager);
			if(modelMsg.isSuccess()){
				return ResponseUtils.sendMsg(true, manager.getId());
			}else{
				return ResponseUtils.sendMsg(false, modelMsg.getMessage());
			}
		} catch (Exception e) {
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
		/*if(manager.getManagerName()==null ||
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
		}*/
		manager.setUpdateTime(new Date());
		if(manager.getCleaningDayStatus().intValue()==2){//账期
			if(manager.getCleaningDay()==null){
				return ResponseUtils.sendMsg(false, "账期天数不能为空!");
			}
		}
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
	
}
