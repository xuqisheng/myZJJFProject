package com.corner.kefu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corner.core.beans.ItemCatelog;
import com.corner.kefu.service.sp.SpItemCatelogService;

@Controller
public class HomeController extends KefuBaseWebController {
	@Autowired
	SpItemCatelogService itemCatelogService;
	
	
	@RequestMapping(value = "/initGoods.do")
	public String index(HttpServletRequest request, Model model) {
		Map<String, Object> map =  new HashMap<String, Object>();
		map.put("pid", 0);
		List<ItemCatelog> itemCatelogYiJiList = itemCatelogService.getAllItemCateByPid(map);
		model.addAttribute("itemCatelogYiJiList", itemCatelogYiJiList);
		return "init/goods";
	}

}
