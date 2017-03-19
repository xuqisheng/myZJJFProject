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

import com.corner.core.beans.ScmsFreightTpl;
import com.corner.core.beans.ScmsFreightTplMap;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.ScmsFreightTplRo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.scms.TempletService;

@Controller
@RequestMapping(value="/scms/templet")
public class TempletController extends KefuBaseWebController{

	private static Logger logger = LoggerFactory.getLogger(TempletController.class);
	
	@Autowired
	private TempletService templetService;
	
	@RequestMapping(value = "/listpage.do")
	public String listPage(HttpServletRequest request,Model model,ScmsFreightTpl condition){
		Pager<ScmsFreightTpl> pager=this.templetService.findAllTemplet(condition);
		model.addAttribute("list", pager.getList());
		model.addAttribute("condition", condition);
		this.pageUtil(condition.getPageIndex(), pager.getTotalSize(), condition.getPageSize(), request, model);
		return "warehouse/freight-tpl";
	}
	
	//删除功能
	@RequestMapping(value = "/deleteObject.do")
	@ResponseBody
	public Object deleteObject(Model model,String id){
		Integer result=this.templetService.deleteObject(id);
		if(result==1){
			return ResponseUtils.sendMsg(true, "操作成功！");
		}else if(result==3){
			return ResponseUtils.sendMsg(false, "该模板有仓库在使用，不能删除！");
		}
		return ResponseUtils.sendMsg(false, "操作失败!");

	}
	
		
	//进入添加页面
	@RequestMapping(value = "/addpage.do")
	public String addpage(HttpServletRequest request,Model model){
		
		return "warehouse/freight-tpl-edit";
	}
	//添加 功能
	@RequestMapping(value = "/addObject.do")
	@ResponseBody
	public Object addObject(ScmsFreightTplRo condition){
		Object obj=this.checkNumberIsRight(condition);
		if(obj!=null){
			return obj;
		}
		int result=this.templetService.addObject(condition);
		
		if(result!=0){
			return ResponseUtils.sendMsg(true, "操作成功！");
		}
		return ResponseUtils.sendMsg(false, "操作失败!");
	}
	
	
	//进入编辑页面
		@RequestMapping(value = "/editObject.do")
		public String editObject(HttpServletRequest request,Model model,String id){
			if(!StringUtil.stringIsNullOrEmpty(id)){
				ScmsFreightTpl tpl=this.templetService.findTelById(id);
				List<ScmsFreightTplMap> list=this.templetService.findAllTplMapByUid(id);
				if(tpl.getPaidMethods() == 2){
					model.addAttribute("tplMap", list.get(0));
				}
				model.addAttribute("list", list);
				model.addAttribute("tpl", tpl);
			}
			return "warehouse/freight-tpl-edit";
		}
		
		
		private Object checkNumberIsRight(ScmsFreightTplRo condition){
			if(condition.getPaidMethods()==0){
				for(int i=0;i<condition.getOrderPrice().length;i++){
					for(int j=i+1;j<condition.getOrderPrice().length;j++){
						if(condition.getOrderPrice()[i].compareTo(condition.getOrderPrice()[j])==0){
							return ResponseUtils.sendMsg(false, "单笔订单金额不能存在相同的值！");
						}
					}
				}
				
				for(int i=0;i<condition.getFreight().length;i++){
					for(int j=i+1;j<condition.getFreight().length;j++){
						if(condition.getFreight()[i].floatValue()==condition.getFreight()[j].floatValue()){
							return ResponseUtils.sendMsg(false, "百分比不能存在相同的值！");
						}
					}
				}
			}else{
				for(int i=0;i<condition.getOrderNum().length;i++){
					for(int j=i+1;j<condition.getOrderNum().length;j++){
						if(condition.getOrderNum()[i].shortValue()==condition.getOrderNum()[j].shortValue()){
							return ResponseUtils.sendMsg(false, "订单数不能存在相同的值！");
						}
					}
				}
				
				for(int i=0;i<condition.getFreightPrice().length;i++){
					for(int j=i+1;j<condition.getFreightPrice().length;j++){
						if(condition.getFreightPrice()[i].compareTo(condition.getFreightPrice()[j])==0){
							return ResponseUtils.sendMsg(false, "运费金额不能存在相同的值！");
						}
					}
				}
			}
			return null;
		}
	
	//编辑功能
		@RequestMapping(value = "/edit.do")
		@ResponseBody
		public Object edit(ScmsFreightTplRo condition){
			Object obj=this.checkNumberIsRight(condition);
			if(obj!=null){
				return obj;
			}
			int result=this.templetService.edit(condition);
			if(result!=0){
				return ResponseUtils.sendMsg(true, "操作成功！");
			}
			return ResponseUtils.sendMsg(false, "操作失败!");
		}
}
