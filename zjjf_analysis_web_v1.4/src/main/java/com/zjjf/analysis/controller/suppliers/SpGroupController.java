package com.zjjf.analysis.controller.suppliers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zjjf.analysis.controller.BaseController;

@Controller
@RequestMapping(value = "/api/spgroup/view")
public class SpGroupController extends BaseController {

	@RequestMapping(value = "/loadPage.do")
	public String orderLoaded(@RequestParam(value = "menuId", required = false, defaultValue = "8") Integer menuId, Model model) {

		model.addAttribute("menuId", menuId);
		return "analysis/spgroup/spGroupList";
	}

}
