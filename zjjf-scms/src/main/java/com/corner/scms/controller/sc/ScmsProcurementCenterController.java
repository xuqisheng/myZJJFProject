package com.corner.scms.controller.sc;

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

import com.corner.core.beans.ItemCatelog;
import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.ScmsShoppingCart;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.SupplierMapper;
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.beans.ro.sc.ScmsProcurementCenterRo;
import com.corner.scms.beans.vo.ScmsItemBaseVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sc.ScmsItemMgService;
import com.corner.scms.service.sc.ScmsProcurementCenterService;


// 采购中心 controller
@Controller
@RequestMapping(value="/scms/procurementcenter")
public class ScmsProcurementCenterController extends ScmsBaseWebController{

	private static Logger logger = LoggerFactory.getLogger(ScmsProcurementCenterController.class);
	
	@Autowired
	private ScmsProcurementCenterService scmsProcurementCenterService;
	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
	private ScmsItemMgService scmsItemMgService;
	
	
	/**
	 * 
	* @Title: 采购中心  首页
	* @param @param request
	* @param @param command
	* @return String   返回类型
	* @author 海灵子
	* @throws
	 */
	@RequestMapping(value = "/listPage.do")
	public String listPage(HttpServletRequest request,Model model,ScmsProcurementCenterRo  condition,Integer pageIndex) {
		Supplier supplier=this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			return "login/index";
		}
		supplier = supplierMapper.selectByPrimaryKey(supplier.getId());
		String name1=null,name2=null;
		List<Map<String, Object>> items=this.scmsProcurementCenterService.findAllItems(supplier.getBsCircleId());
		if(condition.getCateID1()!=null){  // 一级分类
			for (Map<String, Object> map : items) {
				if(condition.getCateID1().equals(map.get("cateId"))){
					name1 = map.get("cateName").toString();
					condition.setCateIDs(map.get("cateId2").toString().split(","));
				}
			}
		}
		if(condition.getCateID()!=null){   //二级分类 时 要去查询 上级分类的名字
			ItemCatelog catelog =this.scmsProcurementCenterService.selectByPrimaryKey(condition.getCateID());
			name2 = catelog.getName();
			for (Map<String, Object> map : items) {
				if(catelog.getPid().equals(map.get("cateId"))){
					name1 = map.get("cateName").toString();
				}
			}
		}
		
		//当前登录用户的 id
		condition.setUid(supplier.getId());
		//* @Title: 查询商品 并分页
		
		Object object=this.selectCount(request, model, new ScmsShoppingCart()); //采购单数量
		condition.setBscirdedId(supplier.getBsCircleId());
		condition.setPageSize(50);
		Pager<ScmsItemBaseVo> page=this.scmsProcurementCenterService.findItemBase(condition);
		model.addAttribute("name1", name1);
		model.addAttribute("name2", name2);
		model.addAttribute("object", object);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("condition", condition);
		model.addAttribute("items", items);
		model.addAttribute("size", page.getTotalSize());
		model.addAttribute("list", page.getList());
		return "purchase/index";
	}
	 
	/**
	 * 查询热销商品
	* @Title
	* @Description: TODO 
	* @param @param request
	* @param @param model
	* @param @param condition
	* @param @param pageIndex
	* @param @return
	* @2016年4月20日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping(value = "/listPage1.do")
	@ResponseBody
	public Object listPage1(HttpServletRequest request,Model model,ScmsProcurementCenterRo  condition,Integer pageIndex) {
		Supplier supplier=this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			return "login/index";
		}
		supplier = supplierMapper.selectByPrimaryKey(supplier.getId());
		String name1=null,name2=null;
		List<Map<String, Object>> items=this.scmsProcurementCenterService.findAllItems(supplier.getBsCircleId());
		if(condition.getCateID1()!=null){  // 一级分类
			for (Map<String, Object> map : items) {
				if(condition.getCateID1().equals(map.get("cateId"))){
					name1 = map.get("cateName").toString();
					condition.setCateIDs(map.get("cateId2").toString().split(","));
				}
			}
		}
		if(condition.getCateID()!=null){   //二级分类 时 要去查询 上级分类的名字
			ItemCatelog catelog =this.scmsProcurementCenterService.selectByPrimaryKey(condition.getCateID());
			name2 = catelog.getName();
			for (Map<String, Object> map : items) {
				if(catelog.getPid().equals(map.get("cateId"))){
					name1 = map.get("cateName").toString();
				}
			}
		}
		//当前登录用户的 id
		condition.setUid(supplier.getId());
		condition.setBscirdedId(supplier.getBsCircleId());
		condition.setPageIndex(1);
		condition.setPageSize(10);
		condition.setOrderNum(1);
		List<ScmsItemBaseVo> page=scmsProcurementCenterService.findItemBase1(condition);
		return ResponseUtils.sendMsg(true, page);
	}
	
	

	/**
	 * 
	* @Title: 加入采购单 
	* * @param @param request
	* @param @param command
	* @return String   返回类型
	* @author 海灵子
	* @throws
	 */
	@RequestMapping(value = "/addShop.do")
	@ResponseBody
	public Object addShop(HttpServletRequest request,Model model,ScmsShoppingCart cart){
		
		Supplier supplier=this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			return ResponseUtils.sendMsg(false, "请重新登录！");
		}
		ScmsItem scmsItem=scmsItemMgService.selectScmsItemById(cart.getScmsItemId());
		if(scmsItem == null)
			return ResponseUtils.sendMsg(false, "添加失败，请刷新页面再尝试!");
		else if(scmsItem.getIsDelete())
			return ResponseUtils.sendMsg(false, "添加失败，请刷新页面再尝试!");
		cart.setSupplierId(supplier.getId());
		ModelMsg result=this.scmsProcurementCenterService.addShop(cart);
		return ResponseUtils.sendMsg(result.isSuccess(), result.getMessage());
	}
	
	
	//采购单数量
	@RequestMapping(value = "/selectCount.do")
	@ResponseBody
	public Object selectCount(HttpServletRequest request,Model model,ScmsShoppingCart cart){
		
		Supplier supplier=this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			return ResponseUtils.sendMsg(false, "请重新登录！");
		}
		cart.setSupplierId(supplier.getId());
		int result=this.scmsProcurementCenterService.selectCount(cart);
		
		return result;
	}
	
	/**
	 * 
	* @Title: 商品详情
	* * @param @param request
	* @param @param command
	* @return String   返回类型
	* @author 海灵子
	* @throws
	 */
	@RequestMapping(value = "/productDetail.do")
	public String shopDetail(HttpServletRequest request,Model model,String id) {
		Supplier supplier=this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			return "login/index";
		}
		ScmsItemBaseVo itemBase=this.scmsProcurementCenterService.findItemVoById(id);
		List<Map<String, Object>> items=this.scmsProcurementCenterService.findAllItems(supplier.getBsCircleId());
		Object object=this.selectCount(request, model, new ScmsShoppingCart()); //采购单数量
		model.addAttribute("items", items);
		model.addAttribute("itemBase", itemBase);
		model.addAttribute("object", object);
		return "purchase/item";
	}
	
}
