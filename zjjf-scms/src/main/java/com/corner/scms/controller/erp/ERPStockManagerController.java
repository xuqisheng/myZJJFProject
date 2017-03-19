package com.corner.scms.controller.erp;

import com.corner.core.beans.ERPPlantItemLog;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.EditParamRo;
import com.corner.scms.beans.ro.erp.ERPLogicStockRo;
import com.corner.scms.beans.ro.erp.ERPPhysicsStockLogRo;
import com.corner.scms.beans.ro.erp.ERPPhysicsStockRo;
import com.corner.scms.beans.vo.erp.ERPLogicStockVo;
import com.corner.scms.beans.vo.erp.ERPPhysicsStockLogVo;
import com.corner.scms.beans.vo.erp.ERPPhysicsStockVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.erp.ERPStockManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/ERPStockManager")
public class ERPStockManagerController extends ScmsBaseWebController{
	private static Logger logger = LoggerFactory.getLogger(ERPWarehouseController.class);
	
	@Autowired
	ERPStockManagerService erpStockManagerService;
	
	//跳转到批发商库存页面
	@RequestMapping(value="/stockPage")
	public String plantItemPage(HttpServletRequest request,Model model){
		String nameAndMdseId = request.getParameter("nameAndMdseId");
		model.addAttribute("nameAndMdseId", nameAndMdseId);
		return "/product/stock";
	}
	//跳转到批发商库存页面
	@RequestMapping(value="/erp/stockPage")
	public String ERPPlantItemPage(HttpServletRequest request,Model model){
		return "/erp/product/stockOut-item";
	}

	//页面跳转
	@RequestMapping(value="/goGoodsStockPage/{action}")
	public String goGoodsStockPage(@PathVariable("action")String action,HttpServletRequest request,Model model){
		String goodsName = request.getParameter("goodsName");
		String mdseId = request.getParameter("mdseId");
		String spec = request.getParameter("spec");
		String pkg = request.getParameter("pkg");
		String warehouseId = request.getParameter("warehouseId");
		String itemBaseId = request.getParameter("itemBaseId");
		String typeMg = request.getParameter("typeMg");
		if(!StringUtil.stringIsNullOrEmpty(action)){
			model.addAttribute("goodsName", goodsName);
			model.addAttribute("mdseId", mdseId);
			model.addAttribute("spec", spec);
			model.addAttribute("pkg", pkg);
			model.addAttribute("warehouseId", warehouseId);
			model.addAttribute("itemBaseId", itemBaseId);

			if("1".equals(action)){
				if(!StringUtil.stringIsNullOrEmpty(typeMg)){
					model.addAttribute("typeMg", typeMg);
				}
				//跳转到库存详情
				return "/product/stock-detail";
			}else if("2".equals(action)){
				//跳转到库存明细
				return "/product/operation-detail";
			}
		}
		return error("请求有误,请重试", model, request);
	}
	
	//获取仓库库存
	@RequestMapping(value="/getSupplierStock")
	@ResponseBody
	public Object getSupplierStock(ERPLogicStockRo logicStock,HttpServletRequest request,Integer pageIndex){
		try {
			Supplier supplier = this.getCurrentUser(Supplier.class, request);
			if(supplier != null){
				logicStock.setSupplierId(supplier.getId());
			}
			if (pageIndex != null){
				logicStock.setPageIndex(pageIndex+1);
			}
			Pager<ERPLogicStockVo> page = erpStockManagerService.getSupplierStock(logicStock);
			return new Pager<ERPLogicStockVo>(true,page.getTotalSize(), page.getList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//获取仓库库存详情
	@RequestMapping(value="/getSupplierStockDetail/{warehouseId}/{itemBaseId}/{typeMg}")
	@ResponseBody
	public Object getSupplierStockDetail(@PathVariable("warehouseId")String warehouseId,@PathVariable("itemBaseId")Integer itemBaseId,HttpServletRequest request){
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(StringUtil.stringIsNullOrEmpty(warehouseId) || itemBaseId == null || supplier == null){
			return ResponseUtils.sendMsg(false, "请求有误,请重试");
		}
		ERPPhysicsStockRo physicsStock = new ERPPhysicsStockRo();
		physicsStock.setWarehouseId(warehouseId);
		physicsStock.setSupplierId(supplier.getId());
		physicsStock.setItemBaseId(itemBaseId);
		try {
			List<ERPPhysicsStockVo> physicsStockList = erpStockManagerService.getSupplierStockDetail(physicsStock);
			if(physicsStockList != null && physicsStockList.size()>0){
				return ResponseUtils.sendMsg(true, physicsStockList);
			}
			return ResponseUtils.sendMsg(true, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(true, null);
		}
	}
	
	//获取物理库存流水
	@RequestMapping(value="/getPhysicsStockLog")
	@ResponseBody
	public Object getPhysicsStockLog(ERPPhysicsStockLogRo physicsStockLog,HttpServletRequest request,Integer pageIndex){
		if(pageIndex != null){
			physicsStockLog.setPageIndex(pageIndex+1);
		}
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(StringUtil.stringIsNullOrEmpty(physicsStockLog.getWarehouseId()) || physicsStockLog.getItemBaseId() == null || supplier == null ){
			return null;
		}
		physicsStockLog.setSupplierId(supplier.getId());
		try {
			Pager<ERPPhysicsStockLogVo> pager = erpStockManagerService.getPhysicsStockLog(physicsStockLog);
			return new Pager<ERPPhysicsStockLogVo>(pager.getTotalSize(), pager.getList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//批发商库存修正
	@RequestMapping(value="/updateStock")
	@ResponseBody
	public Object updateStock(ERPPlantItemLog plantItemLog,HttpServletRequest request){
		try {
			Supplier supplier = this.getCurrentUser(Supplier.class, request);
			if(supplier == null){
				return ResponseUtils.sendMsg(false,"session已失效");
			}
			List<ERPPlantItemLog> erpPlantItemLoglist = new ArrayList<ERPPlantItemLog>();
			erpPlantItemLoglist.add(plantItemLog);
			erpStockManagerService.updateStock(erpPlantItemLoglist,supplier);
			return ResponseUtils.sendMsg(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,e.getMessage());
		}
	}
	
	//批发商批量商品入库
	@RequestMapping(value="/goosInputStock")
	@ResponseBody
	public Object goosInputStock(EditParamRo editParam,HttpServletRequest request){
		try {
			Supplier supplier = this.getCurrentUser(Supplier.class, request);
			if(supplier == null){
				return ResponseUtils.sendMsg(false,"session已失效");
			}
			erpStockManagerService.updateStock(editParam.getErpPlantItemLogs(),supplier);
			return ResponseUtils.sendMsg(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			return ResponseUtils.sendMsg(false,e.getMessage());
		}
	}
}
