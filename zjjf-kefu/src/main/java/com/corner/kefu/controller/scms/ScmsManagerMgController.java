package com.corner.kefu.controller.scms;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ScmsGroup;
import com.corner.core.beans.ScmsManager;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.scms.ScOrderInfoMgRo;
import com.corner.kefu.beans.ro.scms.ScmsManagerMgRo;
import com.corner.kefu.config.SCMSConstants;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.PublicService;
import com.corner.kefu.service.scms.ScmsDistributionAreaService;
import com.corner.kefu.service.scms.ScmsManagerMgService;

@Controller
@RequestMapping(value="/scms/manager")
public class ScmsManagerMgController extends KefuBaseWebController{

	@Autowired
	ScmsManagerMgService scmsManagerMgService;
	@Autowired
	ScmsDistributionAreaService distributionAreaService;
	@Autowired
	PublicService publicService;
	
	
	private static Logger logger = LoggerFactory.getLogger(ScmsManagerMgController.class);
	
	@RequestMapping(value = "/toGoodsIndex.do")
	public String toGoodsIndex(HttpServletRequest request, ScmsManagerMgRo command){
		if("itemList".equals(command.getIsUpdate())){
			if(!StringUtil.stringIsNullOrEmpty(command.getId()))
				request.setAttribute("scmsManager" ,scmsManagerMgService.findById(command.getId()));
			return SCMSConstants.DEALER_GOODS_GOODS;
		}else{
			request.setAttribute("scmsManager", command);
			request.setAttribute("scmsManagers", scmsManagerMgService.getAllScmsManager(command));
			return SCMSConstants.DEALER_GOODS_INDEX;
		}
		
	}
	
	/**
	 * 
	* @Title: listPage 
	* @Description: 分页查询经销商信息 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/listPage.do")
	public String listPage(HttpServletRequest request, ScmsManagerMgRo command , Model model) {
		logger.info("列表查询开始："+JSONUtil.objectToJSONString(command));
		Pager<ScmsManager> pager = scmsManagerMgService.getScmsManagerPageList(command);
		request.setAttribute("scmsManagers", pager.getList());
		request.setAttribute("size", pager.getTotalSize());
		request.setAttribute("scmsManager", command);
		pageUtil(command.getPageIndex(), pager.getTotalSize(), command.getPageSize(), request, model);
		if("manager".equals(command.getIsUpdate()))
			return SCMSConstants.DEALER;
		else
			return SCMSConstants.DEALER_GOODS_INDEX;
	}
	
	/**
	 * 
	* @Title: addScmsManager 
	* @Description: 添加经销商信息 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/addScmsManager.do")
	@ResponseBody
	public Object addScmsManager(HttpServletRequest request, ScmsManagerMgRo command) {
		try {
			logger.info("添加经销商"+JSONUtil.objectToJSONString(command));
			if(StringUtil.stringIsNullOrEmpty(command.getManagerName() , command.getMobile() , command.getPassword())){
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			}else if(command.getGroupIds() == null || command.getGroupIds().length == 0){
				return ResponseUtils.sendMsg(false, "缺少经销区域信息");
			}
			//TODO 先使用当前日期作为批发商编号
			if(StringUtil.stringIsNullOrEmpty(command.getManagerCode()))
				command.setManagerCode(DateUtil.getOrderNum());
			
			ModelMsg modelMsg = scmsManagerMgService.addScmsManager(command);
			if(modelMsg.isSuccess())
				return ResponseUtils.sendMsg(true, "添加成功");
			else
				return ResponseUtils.sendMsg(false, "添加失败");
		} catch (Exception e) {
			logger.error("" , e);
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	
	/**
	 * 
	* @Title: updateScmsManager 
	* @Description: 修改经销商信息 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/updateScmsManager.do")
	@ResponseBody
	public Object updateScmsManager(HttpServletRequest request, ScmsManagerMgRo command) {
		try {
			logger.info("修改经销商"+JSONUtil.objectToJSONString(command));
			if(StringUtil.stringIsNullOrEmpty(command.getId(), command.getManagerName()))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			else if(command.getGroupIds() == null || command.getGroupIds().length == 0)
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			ModelMsg modelMsg =  scmsManagerMgService.updateScmsManager(command); 
			return ResponseUtils.sendMsg(modelMsg.isSuccess(), modelMsg.getMessage());
		}catch (Exception e) {
			logger.error("" , e);
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	/**
	 * 
	* @Title: addOrUpdate 
	* @Description: 添加编辑经销商 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(HttpServletRequest request, ScmsManagerMgRo command) {
		logger.info("查询经销商"+JSONUtil.objectToJSONString(command));
		request.setAttribute("scmsGroupUpId0s" , distributionAreaService.findScmsGroupBy0());	//获取分类信息
		request.setAttribute("regions", publicService.findRegionByPId("1"));	//获取省信息
		if("add".equals(command.getIsUpdate())){
			return SCMSConstants.DEALER_ADD;
		}else{
			command =  scmsManagerMgService.findById(command.getId());
			request.setAttribute("scmsManager", command);
			return SCMSConstants.DEALER_EDIT;
		}
	}
	
	/**
	 * 
	* @Title: findById 
	* @Description: 添加编辑经销商 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/findById.do")
	@ResponseBody
	public Object findById(ScmsManagerMgRo command) {
		logger.info("查询经销商"+JSONUtil.objectToJSONString(command));
		command =  scmsManagerMgService.findById(command.getId());
		return ResponseUtils.sendMsg(true, command);
	}
	/**
	 * 
	* @Title: checkManagerCode 
	* @Description: 校验经销商编号
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/checkManagerCode.do")
	@ResponseBody
	public Object checkManagerCode(HttpServletRequest request, ScmsManagerMgRo command) {
		try {
			logger.info("校验---经销商编号：{"+command.getManagerCode()+"},手机号：{"+command.getMobile()+"},用户名：{"+command.getUserName()+"}");
			if("".equals(command.getManagerCode())&&"".equals(command.getUserName())&&"".equals(command.getMobile()))
				return ResponseUtils.sendMsg(false, "请录入必要元素");
			ModelMsg modelMsg =  scmsManagerMgService.checkManagerCode(command);  
			return ResponseUtils.sendMsg(modelMsg.isSuccess(), modelMsg.getMessage());
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	
	/**
	 * 
	* @Title: deleteScmsManager 
	* @Description: 修改经销商信息 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/deleteScmsManager.do")
	@ResponseBody
	public Object deleteScmsManager(HttpServletRequest request, String id) {
		if(StringUtil.stringIsNullOrEmpty(id))
			return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
		ModelMsg modelMsg =  scmsManagerMgService.deleteManager(id); 
		return ResponseUtils.sendMsg(modelMsg.isSuccess(), modelMsg.getMessage());
	}
	
	/**
	 * 
	* @Title: updateSupplier 
	* @Description: 添加修改经销区域信息
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/updateSupplier.do")
	@ResponseBody
	public Object updateSupplier(HttpServletRequest request, ScOrderInfoMgRo command) {
		try {
			if(StringUtil.stringIsNullOrEmpty(command.getSupplierId() , command.getAddOrDel()))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			
			Supplier supplier = new Supplier();
			supplier.setId(command.getSupplierId());
			//1.添加	, 其他删除    批发商
			supplier.setBsCircleId("1".equals(command.getAddOrDel()) ? command.getGroupId() : 0);
			ModelMsg modelMsg =  scmsManagerMgService.updateSupplier(supplier); 
			if(modelMsg.isSuccess())
				return ResponseUtils.sendMsg(true, "修改成功");
			else
				return ResponseUtils.sendMsg(false, "修改失败");
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	
	/**
	 * 
	* @Title: getSupplierPageList 
	* @Description: 经销区域添加删除批发商列表
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/addSupplierPageList.do")
	public String addSupplierPageList(HttpServletRequest request, ScmsManagerMgRo command , String groupId, Model model) {
		logger.info("经销区域ID：{"+groupId+"} ，查询条件：{"+command.getSupplierCode()+"}");
			Pager<Supplier> pager = scmsManagerMgService.getSupplierPageList(command);
			ScmsGroup group = distributionAreaService.findById(groupId);
			
			request.setAttribute("suppliers", pager.getList());
			request.setAttribute("size", pager.getTotalSize());
			request.setAttribute("group", group);
			request.setAttribute("scmsManager", command);
			this.pageUtil(command.getPageIndex(), pager.getTotalSize(), command.getPageSize(), request, model);
			return SCMSConstants.DEALER_GROUP_MANAGE_SP_ADD;
	}
}

