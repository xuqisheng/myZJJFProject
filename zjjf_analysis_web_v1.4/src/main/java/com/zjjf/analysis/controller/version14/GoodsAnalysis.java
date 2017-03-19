package com.zjjf.analysis.controller.version14;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api/goods/analysis")
public class GoodsAnalysis {

	@RequestMapping(value = "/loadPage.do")
	public String loaded(@RequestParam(value = "menuId", required = false, defaultValue = "8") Integer menuId, Model model) {
		model.addAttribute("menuId", menuId);
		return "analysis/version14/goodsAnalysis";
	}
}
