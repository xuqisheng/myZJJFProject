package com.corner.task.controller;

import com.corner.task.beans.ro.OrderRo;
import com.corner.task.service.SpOrderInfoService;
import com.corner.task.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/task/spOrder")
public class SpOrderInfoController extends TaskBaseWebController{
	@Autowired
	SpOrderInfoService spOrderInfoService;

	@RequestMapping(value = "/selectSpOrderSales.do" , method = RequestMethod.GET)
	public String selectSpOrderSales(OrderRo orderRo , HttpServletRequest request , Model model){
		if(orderRo.getAddTime() == null || "".equals(orderRo.getAddTime())){
			orderRo.setAddTime(DateUtil.getFormateDate());
		}
		if(orderRo.getMdseId() != null && !"".equals(orderRo.getMdseId())){
			String[] mdseIds = orderRo.getMdseId().split(",");
			String[] mdseIds2 = new String[mdseIds.length]; 
			for (int i = 0; i < mdseIds2.length; i++) {
				mdseIds2[i] = mdseIds[i].trim();
			}
			orderRo.setMdseIds(mdseIds2);
		}
		if(orderRo.getItemId() != null && !"".equals(orderRo.getItemId())){
			String[] itemIds = orderRo.getItemId().split(",");
			String[] itemIds2 = new String[itemIds.length]; 
			for (int i = 0; i < itemIds2.length; i++) {
				itemIds2[i] = itemIds[i].trim();
			}
			orderRo.setItemIds(itemIds2);
		}
		List<Map<String, Object>> list = spOrderInfoService.selectSpOrderSales(orderRo);
		
		Map<String, Object> salesMap = new HashMap<String,Object>();
		for (Map<String, Object> map : list) {
			String mdseId = map.get("mdseId").toString();
			Integer sales = Integer.parseInt(map.get("sales").toString());
			salesMap.put(mdseId, salesMap.containsKey(mdseId) ? sales + Integer.parseInt(salesMap.get(mdseId).toString()) : sales);
		}
		model.addAttribute("ro", orderRo);
		model.addAttribute("list", list);
		model.addAttribute("salesMap", salesMap);
		return "order/index";
	};
}
