/**   
* @Title: StoreTestController.java 
* @Package com.corner.salesman.web.controller.test 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月14日 上午6:01:53 
* @version V1.0   
*/

package com.corner.salesman.web.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.rpc.shop.api.service.StoreService;

/** 
* @ClassName: StoreTestController 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月14日 上午6:01:53 
*  
*/
@Controller
@RequestMapping("/store")
public class StoreTestController {

	@Autowired
	StoreService storeService;
	
	
	@RequestMapping("/getStoreList.do")
	@ResponseBody
	public Object getStoreList(HttpServletRequest request) {
		String status = request.getParameter("status");
		List<String> saleManMobiles = new ArrayList<>();
		saleManMobiles.add("13900000000");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("status", status);
		paramMap.put("saleManMobiles", saleManMobiles);
		List<HashMap<String, Object>> list = storeService.getStoreList(paramMap);
		return list;
	}
	
	
	@RequestMapping("/updateStoreStatus.do")
	@ResponseBody
	public Object updateStoreStatus(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("storeId", 253);
		paramMap.put("status", 1);
		int i = storeService.updateStoreStatus(paramMap);
		System.out.println(i);
	   return i;	
	}
	
	
	@RequestMapping("/getStoreDetailInfo.do")
	@ResponseBody
	public Object getStoreDetailInfo(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("storeId", 253);
		Map<String, Object> map = storeService.getStoreDetailInfo(paramMap);
		return map;
	}
}
