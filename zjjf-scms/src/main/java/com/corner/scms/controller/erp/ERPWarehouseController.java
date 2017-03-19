package com.corner.scms.controller.erp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ERPWarehouseUser;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPOrderDetailRo;
import com.corner.scms.beans.ro.erp.ERPWarehouseRo;
import com.corner.scms.beans.vo.erp.ERPWarehouseUserVo;
import com.corner.scms.beans.vo.erp.ERPWarehouseVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.erp.ERPOrderInfoService;
import com.corner.scms.service.erp.ERPWarehouseService;
import com.corner.scms.service.sp.ScmsPlantItemMgService;
import com.corner.scms.service.sp.SpRegionService;

@Controller
@RequestMapping("/erp/warehouse")
public class ERPWarehouseController extends ScmsBaseWebController {
	private static Logger logger = LoggerFactory.getLogger(ERPWarehouseController.class);
	@Autowired
	ERPOrderInfoService erpOrderInfoService;
	@Autowired
	ERPWarehouseService erpWarehouseService;
	@Autowired
	SpRegionService regionService;
	@Autowired 
	ScmsPlantItemMgService plantItemMgService;
	
	
	/**
	 * 前往仓库用户设置界面
	 * @return
	 */
	@RequestMapping(value = {"list" , ""})
	public String list(ERPOrderDetailRo commd, HttpServletRequest request , Model model){
		Supplier supplier = getCurrentUser(Supplier.class , request);
		Pager<ERPWarehouseUserVo> pager = erpOrderInfoService.erpWarehouseUserListPage(commd , supplier.getId());
		this.pageUtil(commd.getPageIndex() , pager.getTotalSize() , commd.getPageSize() , request , model);
		model.addAttribute("list" , pager.getList());
		return "erp/account/list";
	}
	/**
	 * 前往仓库用户设置界面
	 * @return
     */
	@RequestMapping(value = {"toEdit"})
	public String toAddERPWarehouseUser(String id,HttpServletRequest request , Model model) throws Exception{
		Supplier supplier = getCurrentUser(Supplier.class , request);
		model.addAttribute("warehouses" , erpWarehouseService.getERPWarehouseLevel1All(supplier.getId()));
		if(!StringUtil.stringIsNullOrEmpty(id))
			model.addAttribute("user" , erpOrderInfoService.getERPWarehouseUserById(id));
		return "erp/account/add";
	}
	@RequestMapping("addERPWarehouseUser/{whId}")
	@ResponseBody
	public Object addERPWarehouseUser(@PathVariable("whId") String whId ,  ERPWarehouseUser user ,HttpServletRequest request){
		if(StringUtil.stringIsNullOrEmpty(whId , user.getName() , user.getUserName()))
			return new ModelMsg(false , "缺少参数");
		try {
			erpOrderInfoService.addERPWarehouseUser(user , whId);
		}catch (Exception e){
			return ResponseUtils.sendMsg(false , e.getMessage());
		}
		return ResponseUtils.sendMsg(true);
	}
	@RequestMapping("checkUserName/{userName}")
	@ResponseBody
	public Object checkUserName(@PathVariable("userName") String userName ,  ERPWarehouseUser user ,HttpServletRequest request){
		if(StringUtil.stringIsNullOrEmpty(userName)){
			return ResponseUtils.sendMsg(false , "请传入用户名");
		}
		return ResponseUtils.sendMsg(erpOrderInfoService.checkUserName(user.getUserName()));
	}
	
	
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@——————仓库管理（龙五：2016-8-15）——————@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
	//仓库列表
	@RequestMapping(value="/getWarehouseBySupplierId")
	public String getWarehouseBySupplierId(ERPWarehouseRo warehouseRo,HttpServletRequest request,Model model){
		model.addAttribute("warehouseRo", warehouseRo);
		try {
			Supplier supplier = this.getCurrentUser(Supplier.class, request);
			if(supplier != null){
				warehouseRo.setSupplierId(supplier.getId());
			}
			warehouseRo.setWhLevel((byte)1);
			Pager<ERPWarehouseVo> page = erpWarehouseService.getWarehouseBySupplierId(warehouseRo);
			if(page != null){
				model.addAttribute("warehouseList", page.getList());
				pageUtil(warehouseRo.getPageIndex(), page.getTotalSize(), warehouseRo.getPageSize(), request, model);
			}
			return "/erp/warehouse/wh";
		} catch (Exception e) {
			e.printStackTrace();
			return error("程序异常，请联系技术人员", model, request);
		}
	}
	
	//库区列表
	@RequestMapping(value="getWhareaBySupplierId")
	public String getWhareaBySupplierId(ERPWarehouseRo warehouseRo,HttpServletRequest request,Model model){
		model.addAttribute("warehouseRo", warehouseRo);
		try {
			Supplier supplier = this.getCurrentUser(Supplier.class, request);
			if(supplier != null){
				warehouseRo.setSupplierId(supplier.getId());
			}
			warehouseRo.setWhLevel((byte)2);
			Pager<ERPWarehouseVo> page = erpWarehouseService.getWarehouseBySupplierId(warehouseRo);
			if(page != null){
				model.addAttribute("warehouseList", page.getList());
				pageUtil(warehouseRo.getPageIndex(), page.getTotalSize(), warehouseRo.getPageSize(), request, model);
			}
			return "/erp/warehouse/whArea";
		} catch (Exception e) {
			e.printStackTrace();
			return error("程序异常，请联系技术人员", model, request);
		}
	}
	
	//库位列表
	@RequestMapping(value="getWhpositionBySupplierId")
	public String getWhpositionBySupplierId(ERPWarehouseRo warehouseRo,HttpServletRequest request,Model model){
		model.addAttribute("warehouseRo", warehouseRo);
		try {
			Supplier supplier = this.getCurrentUser(Supplier.class, request);
			if(supplier != null){
				warehouseRo.setSupplierId(supplier.getId());
			}
			String upId = "0";
			List<ERPWarehouseVo> treeList = erpWarehouseService.createTree(upId,supplier.getId());
			if(treeList != null){
				model.addAttribute("treeList", JSONUtil.objectToJSONString(treeList));
			}
			warehouseRo.setWhLevel((byte)3);
			Pager<ERPWarehouseVo> page = erpWarehouseService.getWarehouseBySupplierId(warehouseRo);
			if(page != null){
				model.addAttribute("warehouseList", page.getList());
				pageUtil(warehouseRo.getPageIndex(), page.getTotalSize(), warehouseRo.getPageSize(), request, model);
			}
			return "/erp/warehouse/whPosition";
		} catch (Exception e) {
			e.printStackTrace();
			return error("程序异常，请联系技术人员", model, request);
		}
	}
	
	//仓库元素级联
	@RequestMapping(value="/getWhpositionBySupplierId/{upId}")
	@ResponseBody
	public Object getWarehouseByUpId(@PathVariable("upId")String upId,HttpServletRequest request){
		if(StringUtil.stringIsNullOrEmpty(upId)){
			return ResponseUtils.sendMsg(false);
		}
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier == null){
			return ResponseUtils.sendMsg(false);
		}
		try {
			List<ERPWarehouseVo> whList = erpWarehouseService.getWarehouseByUpId(upId,supplier.getId());
			if(whList != null && whList.size()>0){
				return ResponseUtils.sendMsg(true,whList);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtils.sendMsg(false);
		}
		
	}
	
	//仓库tree级联
	@RequestMapping(value="/createTree")
	@ResponseBody
	public Object createTree(HttpServletRequest request){
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier == null){
			return ResponseUtils.sendMsg(false);
		}
		String upId = "0";
		try {
			List<ERPWarehouseVo> treeList = erpWarehouseService.createTree(upId,supplier.getId());
			if(treeList != null){
				return ResponseUtils.sendMsg(true,treeList);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtils.sendMsg(false);
		}
		
	}
	
	//根据id获取数据
	private ERPWarehouseVo getDataById(String id,HttpServletRequest request){
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier == null){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("supplierId", supplier.getId());
		ERPWarehouseVo warehouse = erpWarehouseService.getWarehouseById(map);
		if(warehouse != null){
			return warehouse;
		}
		return null;
	}
	
	//返回仓库编辑页面(暂不使用)
//	@RequestMapping(value="/returnWarehousePage/{action}")
//	public String returnWarehousePage(@PathVariable("action")Integer action,Model model,HttpServletRequest request){
//		if(action != null){
//			//省市区集合
//			List<RegionRegistVo> sub = regionService.getAllAreaAndSpGroup();
//			if(sub != null && sub.size() > 0){
//				model.addAttribute("sub", JSONUtil.objectToJSONString(sub));
//			}
//			//拉取仓库对应对的区域
//			Supplier supplier = this.getCurrentUser(Supplier.class, request);
//			if(supplier == null){
//				return null;
//			}
//			List<PlantItemVo> plantItemList = plantItemMgService.getWarehouseArea(supplier.getId());
//			if(action == 2){
//				String id = request.getParameter("id");
//				if(!StringUtil.stringIsNullOrEmpty(id)){
//					ERPWarehouseVo warehouse = this.getDataById(id, request);
//					if(warehouse != null){
//						model.addAttribute("warehouse", warehouse);
//					}
//				}
//			}
//			return "/erp/warehouse/wh-edit";
//		}
//		return error("请求有误，请重试", model, request);
//	}
	
	@RequestMapping(value="/getWarehouseById/{id}")
	@ResponseBody
	public Object getWarehouseById(@PathVariable("id")String id,HttpServletRequest request){
		try {
			ERPWarehouseVo warehouse = this.getDataById(id, request);
			if(warehouse != null){
				return ResponseUtils.sendMsg(true, warehouse);
			}
			return ResponseUtils.sendMsg(false, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("程序异常");
			return ResponseUtils.sendMsg(false, null);
		}
	}
	
	//添加
	@RequestMapping(value="/addWarehouse/{whLevel}")
	@ResponseBody
	public Object addWarehouse(@PathVariable("whLevel")Byte whLevel,ERPWarehouseRo warehouseRo,HttpServletRequest request){
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier == null){
			return ResponseUtils.sendMsg(false,"session已失效,请刷新后重试");
		}
		//校验
		ModelMsg modelMsg = this.ckeckField(whLevel,"add",warehouseRo);
		if(!modelMsg.isSuccess()){
			return ResponseUtils.sendMsg(false,modelMsg.getMessage());
		}
		warehouseRo.setSupplierId(supplier.getId());
		warehouseRo.setCreateName(supplier.getSupplierName());
		warehouseRo.setWhLevel(whLevel);
		warehouseRo.setId(StringUtil.getUUID());
		boolean msg = erpWarehouseService.addWarehouse(warehouseRo);
		if(msg){
			return ResponseUtils.sendMsg(true,"添加成功");
		}else{
			return ResponseUtils.sendMsg(false,"添加失败");
		}
	}
	
	//编辑
	@RequestMapping(value="/editWarehouse/{whLevel}/{id}")
	@ResponseBody
	public Object editWarehouse(@PathVariable("whLevel")Byte whLevel,@PathVariable("id")String id,ERPWarehouseRo warehouseRo,HttpServletRequest request){
		if(StringUtil.stringIsNullOrEmpty(id)){
			return ResponseUtils.sendMsg(false,"请求有误,请刷新后重试");
		}
		//校验
		ModelMsg modelMsg = this.ckeckField(whLevel,"edit",warehouseRo);
		if(!modelMsg.isSuccess()){
			return ResponseUtils.sendMsg(false,modelMsg.getMessage());
		}
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier == null){
			return ResponseUtils.sendMsg(false,"session已失效,请刷新后重试");
		}
		warehouseRo.setWhLevel(whLevel);
		boolean msg = erpWarehouseService.editWarehouse(warehouseRo);
		if(msg){
			return ResponseUtils.sendMsg(true,"编辑成功");
		}else{
			return ResponseUtils.sendMsg(false,"编辑失败");
		}
	}
	
	//校验的方法
	private ModelMsg ckeckField(Byte whLevel, String str, ERPWarehouseRo warehouseRo) {
		boolean flag = true;
		if(whLevel == 1){
			if(StringUtil.stringIsNullOrEmpty(warehouseRo.getName(),warehouseRo.getCode(),warehouseRo.getContacts(),warehouseRo.getMobile(),warehouseRo.getAddress())){
				flag=false;
			}
		}else if(whLevel == 2 || whLevel == 3){
			if(StringUtil.stringIsNullOrEmpty(warehouseRo.getName(),warehouseRo.getCode())){
				flag=false;
			}
		}else{
			flag=false;
		}
		if(!flag){
			return new ModelMsg(false, "参数输入有误");
		}else{
			return new ModelMsg(true, null);
		}
	}
	
	//删除
	@RequestMapping(value="/delWarehouse/{level}/{id}")
	@ResponseBody
	public Object delWarehouse(@PathVariable("level")Byte level,@PathVariable("id")String id,HttpServletRequest request){
		if(level == null || StringUtil.stringIsNullOrEmpty(id)){
			return ResponseUtils.sendMsg(false, "数据有误");
		}
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier == null){
			return ResponseUtils.sendMsg(false,"session已失效,请刷新后重试");
		}
		try {
			ModelMsg msg = erpWarehouseService.delWarehouse(level,id,supplier.getId());
			if(msg.isSuccess()){
				return ResponseUtils.sendMsg(true);
			}else{
				return ResponseUtils.sendMsg(false,msg.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"程序异常");
		}
	}
	
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@——————仓库管理（龙五：2016-8-15）——————@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
	
}
