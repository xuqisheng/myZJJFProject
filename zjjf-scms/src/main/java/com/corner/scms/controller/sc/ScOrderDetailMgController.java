package com.corner.scms.controller.sc;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.sc.ScOrderDetailRo;
import com.corner.scms.beans.vo.sc.ScOrderDetailVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sc.ScOrderDetailMgService;
@Controller
@RequestMapping("scms/orderDetail")
public class ScOrderDetailMgController extends ScmsBaseWebController {
	private static Logger logger = LoggerFactory.getLogger(ScOrderDetailMgController.class);
	
	@Autowired
	ScOrderDetailMgService scOrderDetailMgService;
	
	
	//返回仓库订单明细页面
	@RequestMapping("returnWarehouseOrderDetailPage.do")
	public String returnWarehouseOrderDetailPage(HttpServletRequest request,Model model,ScOrderDetailRo scOrderDetailRo){
		//先获得明细汇总
		try {
			ScOrderDetailVo scOrderDetailVoCount = scOrderDetailMgService.getOrderDetail(scOrderDetailRo);
			if(scOrderDetailVoCount != null){
				model.addAttribute("scOrderDetailVoCount", scOrderDetailVoCount);
			}
			return "/order-warehouse/detail-goods";
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
	}
	

	/**
	 * 查询订单明细 列表
	 * @Title
	 * @Description: TODO 
	 * @param @param scOrderDetailRo
	 * @param @return
	 * @2016年1月27日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	@RequestMapping("getOrderDetailList.do")
	@ResponseBody
	public Object getOrderDetailList(HttpServletRequest request,Model model,ScOrderDetailRo scOrderDetailRo) {
		try {
			Pager<ScOrderDetailVo> scOrderDetailVoList = scOrderDetailMgService.getOrderDetailList(scOrderDetailRo);
			if(scOrderDetailVoList != null){
				return new Pager<ScOrderDetailVo>(scOrderDetailVoList.getTotalSize(), scOrderDetailVoList.getList());
			}else{
				return new Pager<ScOrderDetailVo>(scOrderDetailVoList.getTotalSize(), scOrderDetailVoList.getList());
			}
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
	} 
}
