package com.corner.scms.controller.sc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.ScmsManager;
import com.corner.core.beans.ScmsWarehouse;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.config.Constants;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.sc.MaOrderInfoMgRo;
import com.corner.scms.beans.ro.sc.ScOrderInfoMgRo;
import com.corner.scms.beans.vo.sc.MaOrderInfoMgVo;
import com.corner.scms.beans.vo.sc.ScOrderInfoMgVo;
import com.corner.scms.beans.vo.sc.ScOrderInfoVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sc.ScOrderDetailMgService;
import com.corner.scms.service.sc.ScOrderInfoMgService;
import com.corner.scms.service.sc.ScmsShoppingCartService;
import com.corner.scms.service.sp.ScmsSupplierMgService;

@Controller
@RequestMapping(value="/scms/scOrder")
public class ScOrderInfoMgController extends ScmsBaseWebController{

	private static Logger logger = LoggerFactory.getLogger(ScOrderInfoMgController.class);
	
	@Autowired
	ScOrderInfoMgService scOrderInfoMgService;
	@Autowired
	ScOrderDetailMgService scOrderDetailMgService;
	
	@Autowired
	ScmsSupplierMgService scmsSupplierMgService;
	
	@Autowired
	ScmsShoppingCartService scmsShoppingCartService;
	
	/********************经销商管理Begin*******************/
	/**
	 * 获得经销商所有的订单
	* @Title
	* @Description: TODO 
	* @param @param command
	* @param @return
	* @2016年1月25日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("/getScOrderInfoList.do")
	public String getScOrderInfoList(HttpServletRequest request,Model model,MaOrderInfoMgRo maOrderInfoMgRo){
		ScmsManager scmsManager = getCurrentUser(ScmsManager.class, request);
		maOrderInfoMgRo.setPageSize(5);;
		if(scmsManager == null)
			return "/login/dealer";
		if(scmsManager!=null){
			maOrderInfoMgRo.setManagerId(scmsManager.getId());
		}
		try {
			Pager<MaOrderInfoMgVo> page = scOrderInfoMgService.getScOrderInfoList(maOrderInfoMgRo);
			if(page != null){
				model.addAttribute("maOrderInfoMgRo", maOrderInfoMgRo);
				model.addAttribute("queryStatus", maOrderInfoMgRo.getQueryStatus());
				model.addAttribute("maOrderInfoMgVoList", page.getList());
				model.addAttribute("size", page.getTotalSize());
				pageUtil(maOrderInfoMgRo.getPageIndex(), page.getTotalSize(), maOrderInfoMgRo.getPageSize(), request, model);
				return "/order-dealer/index";
			}else{
				return "/order-dealer/index";
			}
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
	}
	
	
	//返回经销商订单详情页面和打印页面
	@RequestMapping(value = "/returnDetailsAndPrintPage.do")
	public String returnDetailsPage(HttpServletRequest request,Model model,String id){
		ScmsManager scmsManager = getCurrentUser(ScmsManager.class, request);
    	try {
    		MaOrderInfoMgVo maOrderInfoMgVo = scOrderInfoMgService.getScOrderInfoById(id);
			if(maOrderInfoMgVo != null){
				model.addAttribute("maOrderInfoMgVo", maOrderInfoMgVo);
				return "/order-dealer/detail";
			}else{
				return "/order-dealer/detail";
			}
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
	}
	
	/********************经销商管理end*******************/
	/********************仓库管理begin*******************/
	/**
	 * 获取仓库入库订单列表
	* @Title
	* @Description: TODO 
	* @param @param scOrderInfoMgRo
	* @param @return
	* @2016年1月27日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping(value = "/getWarehouseOrderList.do")
    public String getWarehouseOrderList(HttpServletRequest request,Model model,MaOrderInfoMgRo maOrderInfoMgRo){
		try {
			ScmsWarehouse scmsWarehouse = getCurrentUser(ScmsWarehouse.class, request);
			if(scmsWarehouse != null){
				maOrderInfoMgRo.setWarehouseId(scmsWarehouse.getId());
			}
			Pager<MaOrderInfoMgVo> maOrderInfoMgVoList = scOrderInfoMgService.getWarehouseOrderList(maOrderInfoMgRo);
			if(maOrderInfoMgVoList != null){
				model.addAttribute("maOrderInfoMgRo", maOrderInfoMgRo);
				model.addAttribute("maOrderInfoMgVoList", maOrderInfoMgVoList.getList());
				model.addAttribute("size", maOrderInfoMgVoList.getTotalSize());
				pageUtil(maOrderInfoMgRo.getPageIndex(), maOrderInfoMgVoList.getTotalSize(), maOrderInfoMgRo.getPageSize(), request, model);
			}
			return "/order-warehouse/godownentry";
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
    }
    
	
	/**
	 * 仓库配送单列表
	* @Title
	* @Description: TODO 
	* @param @param scOrderInfoMgRo
	* @param @return
	* @2016年3月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("/getWarehouseDeliveryList.do")
	public String getWarehouseDeliveryList(HttpServletRequest request,Model model,ScOrderInfoMgRo scOrderInfoMgRo){
		try {
			ScmsWarehouse scmsWarehouse = getCurrentUser(ScmsWarehouse.class, request);
			if(scmsWarehouse == null)
				return "/login/warehouse";
			if(scmsWarehouse != null){
				scOrderInfoMgRo.setWarehouseId(scmsWarehouse.getId());
				scOrderInfoMgRo.setPageSize(5);
				scOrderInfoMgRo.setOrder("asc");
				scOrderInfoMgRo.setSort("addTime");
			}
			Pager<ScOrderInfoMgVo> pager = scOrderInfoMgService.selectWarehouseOrderPageList(scOrderInfoMgRo);
			if(pager != null){
				model.addAttribute("list", pager.getList());
				model.addAttribute("size", pager.getTotalSize());
				model.addAttribute("scOrderInfoMgRo", scOrderInfoMgRo);
			}
			this.pageUtil(scOrderInfoMgRo.getPageIndex(), pager.getTotalSize(), scOrderInfoMgRo.getPageSize(), request, model);
			return "/order-warehouse/index";
		} catch (Exception e) {
			logger.error("",e);
			return error("交易异常"+e, model, request);
		}
	}
	
	/**
	 * 	自提或配送状态修改
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年3月2日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("updateGoodsStatus.do")
    public Object updateGoodsStatus(HttpServletRequest request){
		String[] ids = request.getParameterValues("ids[]");
		String[] names = request.getParameterValues("names[]");
		String orderId = request.getParameter("orderId");
    	try {
			ModelMsg modelMsg = scOrderInfoMgService.updateGoodsStatus(orderId,ids,names);
			if(modelMsg != null && modelMsg.isSuccess()){
				return ResponseUtils.sendMsg(true, modelMsg.getMessage());
			}else{
				return ResponseUtils.sendMsg(false, modelMsg.getMessage());
			}
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false,"操作失败！");
		}
    }
	
	
	/**
	 * 仓库打印订单
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年3月3日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("printWarehouseOrder.do")
	@ResponseBody
	public Object printWarehouseOrder(HttpServletRequest request){
		String orderId = request.getParameter("orderId");
		String[] ids = request.getParameterValues("ids[]");
		if(ids == null || ids.length<=0){
			return ResponseUtils.sendMsg(false,"请选择要打印的商品！");
		}
		try {
			ScOrderInfoMgVo scOrderInfoMgVo  = scOrderInfoMgService.printWarehouseOrder(orderId,ids);
			if(scOrderInfoMgVo != null){
				return ResponseUtils.sendMsg(true,scOrderInfoMgVo);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("出错了！",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
    /********************仓库管理end*******************/
	
	/**
	 * 修改订单状态
	* @Title
	* @Description: TODO 
	* @param @param str
	* @param @param status
	* @param @return
	* @2016年2月3日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("updateOrderStatus.do")
	@ResponseBody
	public Object updateOrderStatus(String id,String str){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("str", str);
		map.put("id", id);
		if(str != null && str.equals("shipments")){
			map.put("status", Constants.SCMS_MANAGER_SATUS_2);
		}else if(str != null && str.equals("warehousing")){
			map.put("status", Constants.SCMS_WAREHOUSE_SATUS_2);
		}else if(str != null && str.equals("dispatch")){
			map.put("status", Constants.SCMS_WAREHOUSE_SATUS_3);
		}
		map.put("publicTime", new Date());
		try {
			scOrderInfoMgService.updateOrderStatus(map);
			return ResponseUtils.sendMsg(true);
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
  /***************************采购单确认订单begin*************************************/
	/**
	 * 本方法暂时没用
	* @Title: confirmedOrderInfo 
	* @Description:提交批发商订单
	* @param @return
	* @param @throws Exception
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/confirmedOrderInfo.do")
	public String confirmedOrderInfo(HttpServletRequest request,Model model) throws Exception{
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier == null)
			return "/login/index";
		//查询钱包余额
		supplier = scmsSupplierMgService.selectByPrimaryKey(supplier.getId());
		
		scOrderInfoMgService.confiremdOrderInfo(supplier);
		
		//查询购物车
		return "";
	}
	
  /***************************采购单确认订单end*************************************/
	
	
	/********************海灵子    我的采购单 *******************/
	@RequestMapping("/myOrderInfo.do")
	public String myOrderInfo(HttpServletRequest request,Model model,ScOrderInfoMgRo scOrderInfoMgRo) {
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		scOrderInfoMgRo.setSupplierId(supplier.getId());
		List<ScOrderInfoVo>  scOrderInfo=this.scOrderInfoMgService.findMyOrderInfo(scOrderInfoMgRo);
		int size=this.scOrderInfoMgService.findMyOrderInfoSize(scOrderInfoMgRo);
		model.addAttribute("Ro", scOrderInfoMgRo);
		model.addAttribute("scOrderInfo", scOrderInfo);
		model.addAttribute("size", size);
		return "purchase/order";
	}
	
	
	
	//总单确认收货
	@RequestMapping("/confirmation.do")
	@ResponseBody
	public synchronized Object confirmation(HttpServletRequest request,Model model,ScOrderInfoMgRo scOrderInfoMgRo) throws Exception{
		return new ModelMsg(false , "交易异常");
	}
	
	
	
	//单个子单确认收货
	@RequestMapping("/updateDetailState.do")
	@ResponseBody
	public synchronized Object updateDetailState(HttpServletRequest request,Model model,String id) throws Exception{
		return new ModelMsg(false , "交易异常");
	}
	
	
	//删除订单
	@RequestMapping("/deleteinfo.do")
	@ResponseBody
	public Object deleteinfo(HttpServletRequest request,Model model,ScOrderInfoMgRo scOrderInfoMgRo){
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			return ResponseUtils.sendMsg(false,"你已退出登录，请重新登录!");
		}
		scOrderInfoMgRo.setSupplierId(supplier.getId());
		return this.scOrderInfoMgService.deleteinfo(scOrderInfoMgRo);
	}
	
	
	//订单详情
	@RequestMapping("/scorderdetail.do")
	public String scorderdetail(HttpServletRequest request,Model model,ScOrderInfoMgRo  scOrderInfoMgRo){
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			return "login/index";
		}
		List<ScOrderInfoVo>  scOrderInfo=this.scOrderInfoMgService.findMyOrderInfo(scOrderInfoMgRo);
		if(scOrderInfo.size()>0){
			ScOrderInfoVo orderInfoVo = scOrderInfo.get(0);
			if(orderInfoVo.getDfFee())
				orderInfoVo.setFreight(new BigDecimal(0));
			model.addAttribute("scOrderInfo", orderInfoVo);
		}
		
		return "purchase/order-detail";
	}
	
	/**
	 * 
	* @Title: getAllDetail 
	* @Description:查询所有订单的详情页面
	* @param @param model
	* @param @param scOrderInfoMgRo
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getAllDetail.do")
	public String getAllDetail(Model model,ScOrderInfoMgRo  scOrderInfoMgRo) throws Exception{
		List<ScOrderInfoVo>  scOrderInfo=this.scOrderInfoMgService.getAllDetail(scOrderInfoMgRo);
		if(scOrderInfo.size()>0){
			model.addAttribute("scOrderInfo", scOrderInfo.get(0));
		}
		return "purchase/order-detail";
	}
	
}

