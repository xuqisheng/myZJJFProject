package com.zjjf.analysis.controller.version14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.dubbo.config.annotation.Reference;
import com.zjjf.analysis.producer.authority.IAuthoritydata;

@Controller
@RequestMapping("api/supplier/purchase")
public class SupplierPurchaseController {

	@Reference(version = "1.0.0")
	private IAuthoritydata authoritydata;

	private static Logger logger = LoggerFactory.getLogger(SupplierPurchaseController.class);

	@RequestMapping(value = "/loadPage.do")
	public String loaded(@RequestParam(value = "menuId", required = false, defaultValue = "8") Integer menuId, Model model) {
		model.addAttribute("menuId", menuId);
		
		logger.info("args: {menuId: " + menuId);
		return "analysis/version14/supplierPurchase";
	}

}
