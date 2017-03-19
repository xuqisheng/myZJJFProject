package com.corner.kefu.controller.scms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ScmsGroup;
import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.ScmsWarehouse;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.scms.ScmsDistributionAreaRo;
import com.corner.kefu.beans.vo.ScmsGroupVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.scms.ScmsDistributionAreaService;
import com.corner.kefu.service.scms.WarehouseService;


@Controller
@RequestMapping(value="/scms/distributionArea")
//经销区域 管理
public class ScmsDistributionAreaController extends KefuBaseWebController{

	@Autowired
	private ScmsDistributionAreaService distributionAreaService;
	
	@Autowired
	private WarehouseService wareservice;
	
	private static Logger logger = LoggerFactory.getLogger(ScmsDistributionAreaController.class);
	
	
	/**
	 * 
	* @Title: 经销区域 管理  首页
	* @param @param request
	* @param @param command
	* @return String   返回类型
	* @author 海灵子
	* @throws
	 */
	@RequestMapping(value = "/listPage.do")
	public String listPage(HttpServletRequest request,Model model,ScmsDistributionAreaRo condition,Integer pageIndex) {
		//查询所有的定格    即分类
		List<ScmsGroup> groupMapKeys=this.distributionAreaService.findAllArea();
		Pager<ScmsGroupVo> result=this.distributionAreaService.findAllScmsDistributionArea(condition);
		List<ScmsGroupVo> list=result.getList();
		int size=result.getTotalSize();
		List<ScmsWarehouse> warehouse=this.wareservice.findWareHouseNoPage();
		model.addAttribute("groupMapKeys", groupMapKeys);
		model.addAttribute("warehouse", warehouse);
		model.addAttribute("list", list);
		model.addAttribute("condition", condition);
		this.pageUtil(condition.getPageIndex(), size, condition.getPageSize(), request, model);
		return "dealer-group/index";
	}
	
	
	/**
	 * 
	* @Title: 新增 区域
	* @param @param request
	* @param @param command
	* @return  返回类型
	* @author 海灵子
	* @throws
	 */
	@RequestMapping(value = "/addArea.do")
	@ResponseBody
	public Object addArea(ScmsGroup scmsGroup){
		int result=this.distributionAreaService.addScmsGroup(scmsGroup);
		if(result!=0){
			return ResponseUtils.sendMsg(true, "增加成功");
		}
		return ResponseUtils.sendMsg(false, "操作失败！");
	}
	
	
	@RequestMapping(value = "/findById.do")
	@ResponseBody
	public Object findById(String id){
		ScmsGroup group=this.distributionAreaService.findById(id);
		return ResponseUtils.sendMsg(true, group);
	}
	
	/**
	 * 
	* @Title: 新增分类
	* @param @param request
	* @param @param command
	* @return  返回类型
	* @author 海灵子
	* @throws
	 */
	@RequestMapping(value = "/addCategory.do")
	@ResponseBody
	public Object addCategory(ScmsGroup scmsGroup){
		
		scmsGroup.setUpId(0);
		int result=this.distributionAreaService.addScmsGroup(scmsGroup);
		if(result!=0){
			return ResponseUtils.sendMsg(true, "增加成功");
		}
		return ResponseUtils.sendMsg(false, "操作失败！");
	}
	
	//删除区域
	@RequestMapping(value = "/deleteArea.do")
	@ResponseBody
	public Object deleteArea(Integer id){
		List<ScmsItem> items=this.distributionAreaService.findItemByAreaid(id);
		if(items.size()>0){
			return ResponseUtils.sendMsg(false, "该区域下有商品不能做删除操作！");
		}
		List<Supplier> suppliers=this.distributionAreaService.findSupplierByAreaId(id);
		if(suppliers.size()>0){
			return ResponseUtils.sendMsg(false, "该区域下有批发商不能做删除操作！");
		}
		int result=this.distributionAreaService.deleteArea(id);
		if(result!=0){
			return ResponseUtils.sendMsg(true, "操作成功");
		}
		return ResponseUtils.sendMsg(false, "操作失败！");
		
	}
	
	//删除分类
		@RequestMapping(value = "/deletecategory.do")
		@ResponseBody
		public Object deletecategory(Integer id){
			int result=this.distributionAreaService.deletecategory(id);
			if(result!=0){
				return ResponseUtils.sendMsg(true, "操作成功");
			}
			return ResponseUtils.sendMsg(false, "操作失败！");
			
		}
	
	//更新 分类
		@RequestMapping(value = "/updateCategory.do")
		@ResponseBody
		public Object updateCategory(ScmsGroup group){
			int result=this.distributionAreaService.updateCategory(group);
			if(result!=0){
				return ResponseUtils.sendMsg(true, "操作成功");
			}
			return ResponseUtils.sendMsg(false, "操作失败！");
			
		}
		
		
	
	//更新区域
	@RequestMapping(value = "/update.do")
	@ResponseBody
	public Object update(ScmsGroup group){
		ScmsGroup groupOld=this.distributionAreaService.findById(group.getId().toString());
		if(!groupOld.getWarehouseId().equals(group.getWarehouseId())){
			int num=this.distributionAreaService.findOrders(groupOld.getWarehouseId());
			if(num>0){
				return ResponseUtils.sendMsg(false, "该区域原仓库有商品未入库不能更换仓库");
			}
		}
		
		int result=this.distributionAreaService.update(group);
		if(result!=0){
			return ResponseUtils.sendMsg(true, "操作成功");
		}
		return ResponseUtils.sendMsg(false, "操作失败！");
		
	}
	/**
	 * 
	* @Title: findScmsGroupByUpId 
	* @Description: 根据上级分类ID  获取分类信息列表
	* @param @param group
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/findScmsGroupByUpId.do")
	@ResponseBody
	public Object findScmsGroupByUpId(ScmsGroup group){
		if(group.getUpId() == null || StringUtil.stringIsNullOrEmpty(group.getUpId()+""))
			return ResponseUtils.sendMsg(false, "缺少必要信息");
		List<ScmsGroup> result=this.distributionAreaService.findScmsGroupByUpId(group.getUpId().toString());
		return ResponseUtils.sendMsg(true, result);
	}
	
}

