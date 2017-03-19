package com.corner.scms.controller.erp;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ERPManager;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.beans.ro.erp.ERPManagerItemRo;
import com.corner.scms.beans.vo.erp.ERPManagerItemVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.erp.ERPManagerItemService;
import com.corner.scms.service.erp.ERPManagerService;
@Controller
@RequestMapping("/scms/ERPMaItem")
public class ERPManagerItemController extends ScmsBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(ERPManagerItemController.class);
	
	@Autowired ERPManagerItemService managerItemService; 
	@Autowired ERPManagerService managerService;
	
	/**
	 * 获取批发商下某个供应商的所有商品
	* @Title
	* @Description: TODO 
	* @param @param managerItemRo
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("getAllManagerItem.do")
	public String getAllManagerItem(ERPManagerItemRo managerItemRo,Model model,HttpServletRequest request) {
		Supplier supplier = getCurrentUser(Supplier.class, request);
		if(supplier != null){
			managerItemRo.setSupplierId(supplier.getId());
		}else{
			return error("当前用户session已过期", model, request);
		}
		if(managerItemRo.getManagerId() == null){
			return error("数据有误,未获取到供应商id", model, request);
		}else{
			ERPManager manager = managerService.getERPManagerById(managerItemRo.getManagerId());
			if(manager != null){
				model.addAttribute("manager", manager);
			}else{
				return error("未获取到供应商信息", model, request);
			}
		}
		if(managerItemRo.getNumOrName() != null && !"".equals(managerItemRo.getNumOrName())){
			managerItemRo.setNumOrName(managerItemRo.getNumOrName().trim());
		}
		model.addAttribute("managerItemRo", managerItemRo);
		try {
			Pager<ERPManagerItemVo> page = managerItemService.getAllManagerItem(managerItemRo);
			if(page != null){
				pageUtil(managerItemRo.getPageIndex(), page.getTotalSize(), managerItemRo.getPageSize(), request, model);
				model.addAttribute("managerItemList", page.getList());
				return "/erp/supplier/goods";
			}else{
				return error("程序异常，请联系技术人员", model, request);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.toString());
			return error("程序异常，请联系技术人员", model, request);
		}
	}
	
	@RequestMapping("returnAddOrUpdatePage.do")
	public String returnAddOrUpdatePage(Model model,HttpServletRequest request){
		String action = request.getParameter("action");
		if(action == null || "".equals(action)){
			return error("请求有误，请重试", model, request);
		}
		//查出供应商信息
		String managerId = request.getParameter("managerId");
		if(managerId == null || "".equals(managerId)){
			return error("未获取到供应商id", model, request);
		}
		ERPManager manager = managerService.getERPManagerById(managerId);
		if(manager != null){
			model.addAttribute("manager", manager);
		}else{
			return error("未获取到供应商信息", model, request);
		}
		if("1".equals(action)){
			return "/erp/supplier/goods-add";
		}else{
			String id = request.getParameter("id");
			if(id==null || "".equals(id)){
				return error("数据有误，未获取到商品id", model, request);
			}
			ERPManagerItemVo managerItemVo = managerItemService.getManagerItemById(id); 
			if(managerItemVo != null){
				model.addAttribute("managerItemVo", managerItemVo);
			}
			return "/erp/supplier/goods-edit";
		}
		
	}
	
	/**
	 * 添加商品
	* @Title
	* @Description: TODO 
	* @param @param managerId
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("addERPManagerItem.do")
	public Object addERPManagerItem(String itemBaseIds[],String areaPrices[],String taxRates[], String managerId,Model model,HttpServletRequest request){
		Supplier supplier = getCurrentUser(Supplier.class, request);
		if(supplier == null){
			return ResponseUtils.sendMsg(false, "当前用户session已过期");
		}
		if(managerId == null){
			return ResponseUtils.sendMsg(false, "数据有误,未获取到供应商id");
		}
		if(itemBaseIds == null || itemBaseIds.length==0 ||
			areaPrices == null || areaPrices.length==0){
			return ResponseUtils.sendMsg(false, "请输入完整数据");
		}
		try {
			ModelMsg modelMsg = managerItemService.addERPManagerItem(managerId,supplier.getId(),itemBaseIds,areaPrices,taxRates);
			if(modelMsg.isSuccess()){
				return ResponseUtils.sendMsg(true, modelMsg.getMessage());
			}else{
				return ResponseUtils.sendMsg(false, modelMsg.getMessage());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());
			return ResponseUtils.sendMsg(false, "程序异常，请联系技术人员");
		}
	}
	
	/**
	 * 修改商品
	* @Title
	* @Description: TODO 
	* @param @param managerItemRo
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("updateERPManagerItem.do")
	public Object updateERPManagerItem(ERPManagerItemRo managerItemRo,Model model,HttpServletRequest request){
		if(managerItemRo.getId()==null){
			return ResponseUtils.sendMsg(false, "数据有误,未获取到商品id");
		}
		if(managerItemRo.getAreaPrice() == null || managerItemRo.getTaxRate() == null){
			return ResponseUtils.sendMsg(false, "参数有误,请重新输入");
		}
		try {
			ModelMsg modelMsg = managerItemService.updateERPManagerItem(managerItemRo);
			if(modelMsg.isSuccess()){
				return ResponseUtils.sendMsg(true, modelMsg.getMessage());
			}else{
				return ResponseUtils.sendMsg(false, modelMsg.getMessage());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());
			return ResponseUtils.sendMsg(false, "程序异常，请联系技术人员");
		}
	}
	
	/**
	 * 删除商品
	* @Title
	* @Description: TODO 
	* @param @param id
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("deleteERPManagerItem.do")
	public Object deleteERPManagerItem(String id,Model model,HttpServletRequest request){
		if(id == null){
			return ResponseUtils.sendMsg(false, "数据有误,未获取到商品id");
		}
		try {
			boolean flag = managerItemService.deleteERPManagerItem(id);
			if(flag){
				return ResponseUtils.sendMsg(true, "删除成功");
			}else{
				return ResponseUtils.sendMsg(false, "删除失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());
			return ResponseUtils.sendMsg(false, "程序异常，请联系技术人员");
		}
	}
	
	/**
	 * 根据商品条形码收索商品
	* @Title
	* @Description: TODO 
	* @param @param mdseId
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月2日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("getitemByMdseId.do")
	public Object getitemByMdseId(String mdseId,Model model,HttpServletRequest request){
		if(mdseId == null || "".equals(mdseId)){
			return ResponseUtils.sendMsg(false, "参数有误,请重新输入");
		}else{
			mdseId = mdseId.trim();
		}
		String action = request.getParameter("action");
		ModelMsg modelMsg = null;
		try {
			if(action != null && !"".equals(action)){
				if("1".equals(action)){
					modelMsg = managerItemService.getitemByMdseId(mdseId);
				}else{
					modelMsg = managerItemService.getitemByMdseIdx(mdseId);
				}
				if(modelMsg.isSuccess()){
					return ResponseUtils.sendMsg(true,modelMsg.getData());
				}else{
					return ResponseUtils.sendMsg(false,modelMsg.getMessage());
				}
			}else{
				return ResponseUtils.sendMsg(false, "请求有误，请重试");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.toString());
			return ResponseUtils.sendMsg(false, "程序异常，请联系技术人员");
		}
	}
}
