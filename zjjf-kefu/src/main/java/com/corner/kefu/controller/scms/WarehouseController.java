package com.corner.kefu.controller.scms;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.Region;
import com.corner.core.beans.ScmsFreightTpl;
import com.corner.core.beans.ScmsWarehouse;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.safe.MD5;
import com.corner.kefu.beans.ro.scms.ScmsWarehouseRo;
import com.corner.kefu.beans.vo.ScmsWarehouseVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.PublicService;
import com.corner.kefu.service.scms.TempletService;
import com.corner.kefu.service.scms.WarehouseService;


@Controller
@RequestMapping(value="/scms/warehouse")
public class WarehouseController extends KefuBaseWebController{
	
	private static Logger logger = LoggerFactory.getLogger(WarehouseController.class);
	
	@Autowired
	private PublicService publicService;

	@Autowired
	private TempletService templetService;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private TempletService templetservice;
	
	
	@RequestMapping(value = "/listPage.do")
	public String listPage(HttpServletRequest request,Model model,ScmsWarehouseRo condition){
		Pager<ScmsWarehouseVo> pager= this.warehouseService.findAllOrderInfo(condition);
		if(condition.getProvince()!=null){
			List<Region> city=this.publicService.findRegionByPId(condition.getProvince().toString());
			model.addAttribute("city", city);
		}
		if(condition.getCity()!=null){
			List<Region> county=this.publicService.findRegionByPId(condition.getCity().toString());
			model.addAttribute("county", county);
		}
		model.addAttribute("list", pager.getList());
		model.addAttribute("condition", condition);
		request.setAttribute("regions", publicService.findRegionByPId("1"));	//获取省信息
		this.pageUtil(condition.getPageIndex(), pager.getTotalSize(), condition.getPageSize(), request, model);
		return "warehouse/warehouse-index";
	}
	
	@RequestMapping(value = "/listTemplet.do")
	@ResponseBody
	public Object listTemplet(ScmsFreightTpl condition,int pageIndex){
		condition.setPageIndex(pageIndex+1);
		Pager<ScmsFreightTpl> pager=this.templetservice.findAllTemplet(condition);
		return ResponseUtils.sendPagination(pager);
	}

	
	
	//添加功能
	@RequestMapping(value = "/add.do")
	public String addScmsWarehonse(HttpServletRequest request,Model model){
		
		request.setAttribute("regions", publicService.findRegionByPId("1"));	//获取省信息
		return "warehouse/warehouse-edit";
	}
	
	
	//添加功能
	@RequestMapping(value = "/addWareHouse.do")
	@ResponseBody
	public Object addScmsWarehonse(HttpServletRequest request,ScmsWarehouse condition){
		condition.setPassword(MD5.StringToMd5(condition.getPassword()));
		List<ScmsWarehouse> checkhouseCodeResult=this.warehouseService.checkhouseCode(condition.getHouseCode());//仓库编码校验是否重复
		if(checkhouseCodeResult.size()>0){
			return ResponseUtils.sendMsg(false, "仓库编码已被使用，请更换其他编码！");
		}
		if(condition.getBranderTel()==null){
			return ResponseUtils.sendMsg(false, "请输入手机号！");
		}
		List<ScmsWarehouse> checkbranderTelResult=this.warehouseService.checkbranderTel(condition.getBranderTel());//仓库负责任手机号校验是否重复
		if(checkbranderTelResult.size()>0){
			return ResponseUtils.sendMsg(false, "仓库负责人手机号码已被使用，请更换！");
		}
		condition.setUpdateTime(new Date());
		ModelMsg modelMsg = warehouseService.addScmsWarehonse(condition);
		return ResponseUtils.sendMsg(modelMsg.isSuccess(), modelMsg.getMessage());
	}
	
	
	//查询 仓库 用户名是否 存在
	@RequestMapping(value = "/checkName.do")
	@ResponseBody
	public Object checkName(Model model,String userName){
		Integer result=this.warehouseService.checkName(userName);
		if(result!=0){
			return ResponseUtils.sendMsg(false, "用户名已存在！");
		}
		return ResponseUtils.sendMsg(true, "用户名可以使用");
		
	}
	
	
	//删除功能
	@RequestMapping(value = "/deleteObject.do")
	@ResponseBody
	public Object deleteObject(Model model,String id){
		Integer result=this.warehouseService.deleteObject(id);
		if(result==1){
			return ResponseUtils.sendMsg(true, "操作成功！");
		}else if(result==3){
			return ResponseUtils.sendMsg(false, "有区域在使用该仓库，不能删除！");
		}
		return ResponseUtils.sendMsg(false, "操作失败!");
		
	}
	
	
	//编辑功能
	@RequestMapping(value = "/editObject.do")
	public String edit(Model model,String id){
		ScmsWarehouse warehouse=this.warehouseService.findWareHouseById(id);
		ScmsFreightTpl tpl=templetService.findTelById(warehouse.getTplId());
		warehouse.setTplName(tpl.getName());
		if(warehouse.getProvince()!=null){
			List<Region> city=this.publicService.findRegionByPId(warehouse.getProvince().toString());
			model.addAttribute("city", city);
		}
		if(warehouse.getCity()!=null){
			List<Region> county=this.publicService.findRegionByPId(warehouse.getCity().toString());
			model.addAttribute("county", county);
		}
		model.addAttribute("warehouse", warehouse);
		model.addAttribute("regions", publicService.findRegionByPId("1"));	//获取省信息
		return "warehouse/warehouse-edit";
	}
	
	
	//编辑功能
	@RequestMapping(value = "/edit.do")
	@ResponseBody
	public Object edit(ScmsWarehouse hoScmsWarehouse){
		List<ScmsWarehouse> checkhouseCodeResult=this.warehouseService.checkhouseCode(hoScmsWarehouse.getHouseCode());//仓库编码校验是否重复
		if(checkhouseCodeResult!=null&&checkhouseCodeResult.size()>0&&!checkhouseCodeResult.get(0).getId().equals(hoScmsWarehouse.getId())){
			return ResponseUtils.sendMsg(false, "仓库编码已被使用，请更换其他编码！");
		}
		List<ScmsWarehouse> checkbranderTelResult=this.warehouseService.checkbranderTel(hoScmsWarehouse.getBranderTel());//仓库负责任手机号校验是否重复
		if(checkbranderTelResult!=null&&checkbranderTelResult.size()>0&&!checkbranderTelResult.get(0).getId().equals(hoScmsWarehouse.getId())){
			return ResponseUtils.sendMsg(false, "仓库负责人手机号码已被使用，请更换！");
		}
		if(hoScmsWarehouse.getPassword()!=null&&!hoScmsWarehouse.getPassword().trim().equals("")){
			hoScmsWarehouse.setPassword(MD5.StringToMd5(hoScmsWarehouse.getPassword()));
		}else{
			hoScmsWarehouse.setPassword(null);
		}
		Integer result=this.warehouseService.edit(hoScmsWarehouse);
		if(result!=0){
			return ResponseUtils.sendMsg(true, "操作成功！");
		}
		return ResponseUtils.sendMsg(false, "操作失败!");
	}
	
	
	
}
