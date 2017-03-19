package com.corner.kefu.controller.sp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corner.core.beans.SpGroup;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.sp.PlantItemRo;
import com.corner.kefu.beans.vo.sp.PlantItemVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.sp.SpPlantItemService;
@Controller
@RequestMapping("/keFu/plantItem")
public class PcPlantItemController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(PcPlantItemController.class);
	
	@Autowired
	SpPlantItemService spPlantItemService;
	
	/**
	 * 库存预警列表
	* @Title
	* @Description: TODO 
	* @param @param plantItemRo
	* @param @param request
	* @param @param model
	* @param @return
	* @2016年4月8日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("getSupplierAllStock.do")
	public String getSupplierAllStock(PlantItemRo plantItemRo,HttpServletRequest request,Model model){
		model.addAttribute("plantItemRo",plantItemRo );
//		Integer pageIndex = 1;
//		String pageIndex1 = request.getParameter("pageIndex");
//		if(!StringUtil.stringIsNullOrEmpty(pageIndex1)){
//			pageIndex = Integer.parseInt(pageIndex1);
//		}
		if(!StringUtil.stringIsNullOrEmpty(plantItemRo.getNameAndMobile())){
			plantItemRo.setNameAndMobile(plantItemRo.getNameAndMobile().trim());
		}
		try {
			//查出所有定格
			List<SpGroup> spGroupList = spPlantItemService.getAllSpGroup();
			model.addAttribute("spGroupList",spGroupList);
			Pager<PlantItemVo> Pager = spPlantItemService.getSupplierAllStock(plantItemRo);
			if(Pager != null){
				model.addAttribute("plantItemVoList",Pager.getList());
				pageUtil(plantItemRo.getPageIndex(), Pager.getTotalSize(), plantItemRo.getPageSize(), request, model);
			}
			return "/supplier-goods/stock-warn";
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
		}
		
	}
	
	//
	/**
	 * 查看库存预警详情
	* @Title
	* @Description: TODO 
	* @param @param plantItemRo
	* @param @param request
	* @param @param model
	* @param @return
	* @2016年4月11日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("getSupplierStockDetail.do")
	public String getSupplierStockDetail(PlantItemRo plantItemRo,HttpServletRequest request,Model model){
		model.addAttribute("plantItemRo", plantItemRo);
//		String pageIndex1 = request.getParameter("pageIndex");
//		Integer pageIndex = 1;
//		if(!StringUtil.stringIsNullOrEmpty(pageIndex1)){
//			pageIndex = Integer.parseInt(pageIndex1);
//		}
		if(plantItemRo.getSpGroupId()==null || StringUtil.stringIsNullOrEmpty(plantItemRo.getSpId())){
			logger.info("spGroupId为空或spId为空！");
			return error("出错了！", model, request);
		}
		if(!StringUtil.stringIsNullOrEmpty(plantItemRo.getNameAndMobile())){
			plantItemRo.setNameAndMobile(plantItemRo.getNameAndMobile().trim());
		}
		try {
			PlantItemVo plantItemVo = spPlantItemService.getPlantItemByGroupAndSpId(plantItemRo);
			if(plantItemVo != null){
				model.addAttribute("plantItemVo", plantItemVo);
				Pager<PlantItemVo>  plantItemVoList = spPlantItemService.getPlantItemGoodsByGroupAndSpId(plantItemRo);
				if(plantItemVoList != null){
					model.addAttribute("plantItemVoList", plantItemVoList.getList());
					pageUtil(plantItemRo.getPageIndex(), plantItemVoList.getTotalSize(),plantItemRo.getPageSize(), request, model);
				}
			}
			return "/supplier-goods/stock-warn-detail";
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
		}
	} 
}
