package com.corner.scms.controller.erp;

import com.corner.core.beans.*;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.enums.ManagerCooperation;
import com.corner.core.enums.StockType;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.SpOrderInfoRo;
import com.corner.scms.beans.ro.erp.ERPManagerItemRo;
import com.corner.scms.beans.ro.erp.ERPOrderDetailRo;
import com.corner.scms.beans.vo.OrderInfoVo;
import com.corner.scms.beans.vo.erp.ERPManagerItemVo;
import com.corner.scms.beans.vo.erp.ERPOrderInfoVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.RegionService;
import com.corner.scms.service.erp.*;
import com.corner.scms.service.impl.ReginServiceImpl;
import com.corner.scms.service.sp.ScmsOrderInfoMgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/scms/ERPOrderInfo")
public class ERPOrderInfoController extends ScmsBaseWebController {
	private static Logger logger = LoggerFactory.getLogger(ERPOrderInfoController.class);
	@Autowired
	ERPOrderInfoService erpOrderInfoService;
	@Autowired
	ERPManagerService erpManagerService;
	@Autowired
	ERPManagerItemService managerItemService;
	@Autowired
	ScmsOrderInfoMgService scmsOrderInfoMgService;
	@Autowired
	ERPWarehouseService erpWarehouseService;
	@Autowired
    ERPPurchaseStockService erpPurchaseStockService;
	@Autowired
	RegionService regionService;

	@RequestMapping(value = {"/incoming/{orderId}"})
	@ResponseBody
	public Object incoming(@PathVariable("orderId") String orderId,ERPOrderDetailRo commod, HttpServletRequest request){
		Supplier supplier = getCurrentUser(Supplier.class , request);
		commod.setSupplierId(supplier.getId());
		commod.setSupplierName(supplier.getSupplierName());
		commod.setOrderId(orderId);
		for (int i = 0; i < commod.getIds().length; i++) {
			if(StringUtil.stringIsNullOrEmpty(commod.getIds()[i]))
				return ResponseUtils.sendMsg(ResponseUtils.FAILURE , "提交数据异常，请刷新重试");
		}
		User user = getUser(request);
		ModelMsg msg;
		try {
				msg = erpOrderInfoService.addERPPurchaseStockInInfo(commod ,user.getId() , user.getUserName());
		}catch (Exception e){
			logger.error(e+"");
			msg = new ModelMsg(false , e.getMessage());
		}
		return ResponseUtils.sendMsg(msg.isSuccess() , msg.getMessage());
	}

	/********************************编辑入库******************************************/
	@RequestMapping("/toAdd")
	public String toAdd(Model model , HttpServletRequest request) throws Exception{
		Supplier supplier = getCurrentUser(Supplier.class , request);
		model.addAttribute("warehouses" , erpWarehouseService.getERPWarehouseLevel1All(supplier.getId()));
		return "erp/product/instock-add";
	}
	@RequestMapping("/addOrderInfo")
	@ResponseBody
	public Object addOrderInfo(ERPOrderDetailRo commod , HttpServletRequest request){
		Supplier supplier = getCurrentUser(Supplier.class , request);
		commod.setSupplierId(supplier.getId());
		commod.setSupplierName(supplier.getSupplierName());
		try {
			erpOrderInfoService.addOrderInfo(commod);
		}catch (Exception e){
			return ResponseUtils.sendMsg(false , e.getMessage());
		}
		return ResponseUtils.sendMsg(true , "添加成功");
	}
	@RequestMapping(value = {"/list" , ""})
	public String list(ERPOrderDetailRo commod , HttpServletRequest request, Model model){
		Supplier supplier = getCurrentUser(Supplier.class , request);
		commod.setSupplierId(supplier.getId());
		ERPWarehouse warehouse = getCurrentUser(ERPWarehouse.class , request);
		if (warehouse != null) {
			commod.setWhId(warehouse.getId());
		}
		Pager<ERPManagerOrderInfo> list =  erpOrderInfoService.list(commod);
		pageUtil(commod.getPageIndex() , list.getTotalSize() , commod.getPageSize() , request , model);
		model.addAttribute("commod" , commod);
		model.addAttribute("list" , list.getList());
		return "erp/product/instock-list";
	}
	@RequestMapping(value = {"/detail/{type}"})
	public String detail(String orderId, @PathVariable("type") String type,HttpServletRequest request, Model model) throws Exception{
		ERPOrderInfoVo erpOrderInfoVo = erpOrderInfoService.detail(orderId);
		//	查询采购单信息
		model.addAttribute("info" , erpOrderInfoVo);
		model.addAttribute("warehouses" , erpWarehouseService.getWarehouseLevel3(erpOrderInfoVo.getWhId()));
		model.addAttribute("stockTypes" , StockType.values());
		if("1".equals(type)){
			//	查询入库信息
			model.addAttribute("lists" , erpPurchaseStockService.selectStockListByPorderId(orderId));
			return "erp/product/instock-edit";
		}else{
			model.addAttribute("manager" , erpManagerService.getERPManagerById(erpOrderInfoVo.getManagerId()));
			ERPWarehouse warehouse = erpWarehouseService.getWarehouseById(erpOrderInfoVo.getWhId());
			Region region_area = regionService.getReginById(warehouse.getAreaId());
			if (region_area != null)
				warehouse.setAddress(region_area.getName()+warehouse.getAddress());
			Region region_city = regionService.getReginById(warehouse.getCityId());
			if (region_city != null)
				warehouse.setAddress(region_city.getName()+warehouse.getAddress());
			Region region_province = regionService.getReginById(warehouse.getProvinceId());
			if (region_province != null)
				warehouse.setAddress(region_province.getName()+warehouse.getAddress());
			model.addAttribute("warehouse",warehouse);

			return "erp/product/instock-purchase-print";
		}

	}
	@RequestMapping(value = {"/detail/{orderId}/{orderId2}"})
	public String detailPrint(@PathVariable("orderId") String orderId,@PathVariable("orderId2") String orderId2, HttpServletRequest request, Model model) throws Exception{
		ERPOrderInfoVo vo = erpOrderInfoService.detail(orderId);
		//	查询采购单信息
		model.addAttribute("info" , vo);
		model.addAttribute("warehouse" , erpWarehouseService.getWarehouseById(vo.getWhId()));
		//	查询入库信息
		model.addAttribute("lists" , erpPurchaseStockService.selectStockListByOrderId(orderId2));
		return "erp/product/instock-print";
	}
	@RequestMapping("/addItemList/json")
	@ResponseBody
	public Object addItemList(ERPManagerItemRo commod, Model model ,HttpServletRequest request){
		Supplier supplier = getCurrentUser(Supplier.class , request);
		commod.setSupplierId(supplier.getId());
		return managerItemService.getAllManagerItem(commod);
	}
	@RequestMapping("/outStockList")
	public String outStockList(){
		return "erp/product/outstock-list";
	}
	@RequestMapping("/getMangerItem/{itemCode}")
	@ResponseBody
	public Object getManagerItemByItemCode(HttpServletRequest request,@PathVariable("itemCode") String itemCode){
		try {
			itemCode = String.format("%08d", Integer.parseInt(itemCode.trim()));
		}catch (Exception e){
			return ResponseUtils.sendMsg(false , "未查询到数据");
		}
		Supplier supplier = getCurrentUser(Supplier.class , request);
		ERPManagerItemVo vo = managerItemService.getManagerItemByItemCode(itemCode , supplier.getId());
		if(vo == null)
			return ResponseUtils.sendMsg(false , "未查询到数据");
		return ResponseUtils.sendMsg(true , vo);
	}
	@RequestMapping("outstockList")
	public String outstockList(){
		return "erp/product/outstock-list";
	}

	@RequestMapping(value = "outstockDetail/{orderId}")
	public String GetSpOrderInfo(@PathVariable("orderId") String orderId, HttpServletRequest request, Model model) throws Exception{
		if (StringUtil.stringIsNullOrEmpty(orderId)) {
			logger.info("请求的参数有误");
			return null;
		}

		OrderInfoVo spOrderInfo=this.scmsOrderInfoMgService.findByOrId(orderId);
		if (spOrderInfo == null) {
			logger.info("id为{}的订单不存在",orderId);
			return null;

		}
		// 订单详细
		List<SpOrderDetail> orderDetails = scmsOrderInfoMgService.getOrderDetail(orderId);
		BigDecimal bigDecimal = new BigDecimal(0.00);
		int ordernum = 0;
		for (SpOrderDetail orderDetail : orderDetails) {
			bigDecimal = bigDecimal.add(orderDetail.getTotalPrice());
			ordernum += orderDetail.getQuantity();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderInfo", spOrderInfo);
		map.put("ordernum", ordernum);
		map.put("total", bigDecimal);
		map.put("orderDetail", orderDetails);
		map.put("linkOrderNo", orderDetails==null?null:orderDetails.get(0).getOrderId());
		Supplier supplier = getCurrentUser(Supplier.class , request);
		map.put("warehouses" , erpWarehouseService.getERPWarehouseLevel1All(supplier.getId()));
		model.addAttribute("map", map);
		return "erp/product/outstock-detail";
	}

	/**
	 * 供应商更新订单的状态
	 *
	 * @param orderid
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/updateStatusATime")
	public ModelAndView updateStatusATime(String orderid, Byte status, Model model, HttpServletRequest request) {
		try {
			if (StringUtil.stringIsNullOrEmpty(orderid)) {
				model.addAttribute("message", "参数错误");
				return new ModelAndView("PcKdianbao/error");
			}
			//获得批发商对象
			Supplier supplier = getCurrentUser(Supplier.class, request);
			SpOrderInfoRo ro = new SpOrderInfoRo();
			ro.setStoreid(orderid);
			//根据条件查询订单
			OrderInfoVo sp1 = scmsOrderInfoMgService.findByOrId(ro.getStoreid());
			if(sp1 != null && sp1.getStatus() == 6){//已取消
				model.addAttribute("message", "该订单已取消");
				return new ModelAndView("PcKdianbao/error");
			}
			logger.debug("该笔订单信息", sp1);
			SpOrderInfo sp = new  SpOrderInfo();
			sp.setId(sp1.getId());
			sp.setLevel(sp1.getLevel());
			sp.setpId(sp1.getpId());
			if (status != null) {
				sp.setStatus(status);
				Date date = new Date();
				switch (status) {
					case (byte) 3:
						//初始化提单时间
						sp.setGetOrderTime(date);
						break;
					case (byte) 4:
						//初始化打印时间
						sp.setPrintTime(date);
						break;
					case (byte) 5:
						//初始化送达时间
						sp.setAckTime(date);
						break;
				}
			}
			//更新订单
			boolean result = scmsOrderInfoMgService.updateOrder(sp);
			if (result) {
//				if(status==4){
//					//更新催单状态
//					//1:先查有没有催单的信息
//					Map<String, Object> map = new HashMap<String, Object>();
//					map.put("spId", supplier.getId());
//					map.put("orderId", orderid);
//					int num = reminderService.getReminderByOrderIdAndSpId(map);
//					if(num>0){
//						map.put("processStatus", Byte.parseByte("1"));
//						reminderService.updateReminderStatusByOrderIdAndSpId(map);
//					}
//				}

				//重定向
				return new ModelAndView("redirect:outstockDetail/" + orderid + ".do");
			}
		} catch (Exception e) {
			logger.error("ScmsOrderInfoMgController's method updateStatusATime has an error:{}", e);
		}
		return null;
	}
}
