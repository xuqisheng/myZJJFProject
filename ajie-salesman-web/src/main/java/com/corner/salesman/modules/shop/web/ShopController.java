/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.shop.web;

import com.corner.rpc.salesman.api.service.DeptService;
import com.corner.rpc.shop.api.service.RegionService;
import com.corner.rpc.shop.model.Region;
import com.corner.salesman.common.config.Global;
import com.corner.salesman.common.persistence.Page;
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.common.web.BaseController;
import com.corner.salesman.commons.persistence.Json;
import com.corner.salesman.modules.shop.entity.Shop;
import com.corner.salesman.modules.shop.service.ShopService;
import com.corner.salesman.modules.sys.entity.User;
import com.corner.salesman.modules.sys.utils.UserUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 客户管理Controller
 * @author setsail
 * @version 2016-08-05
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/shop")
public class ShopController extends BaseController {

	@Autowired
	private ShopService shopWebService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private com.corner.rpc.salesman.api.service.ShopService shopService;
	
	/*@ModelAttribute
	public Shop get(@RequestParam(required=false) String shopNo) {
		Shop entity = null;
		if (StringUtils.isNotBlank(shopNo)){
			entity = shopWebService.get(shopNo);
		}
		if (entity == null){
			entity = new Shop();
		}
		return entity;
	}*/
	
	@RequiresPermissions("shop:shop:view")
	@RequestMapping(value = {"list", ""})
	public String list(Shop shop, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List<Region> provinceList = null;
		/*List<Region> cityList = null;
		List<Region> areaList = null;
		List<Dept>  deptList = null;
		String provinceId = shop.getProvinceId();
		String cityId = shop.getCityId();*/
		String isAllot = shop.getIsAllot();
		//设置默认值
		shop.setIsAllot(StringUtils.isBlank(isAllot)?"N":isAllot);

		try {
			//加载省份数据列表
			Region region = new Region();
			region.setpId(1);
			provinceList = regionService.queryRegionList(region);
			/*if(StringUtils.isNotBlank(provinceId)){
				region.setpId(Integer.parseInt(provinceId));
				cityList = regionService.queryRegionList(region);
			}
			if(StringUtils.isNotBlank(cityId)){
				region.setpId(Integer.parseInt(cityId));
				areaList = regionService.queryRegionList(region);
			}
			
			deptList = deptService.getDeptmentAllList();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//加载客户数据列表
		Page<Shop> page = shopWebService.findPage(new Page<Shop>(request, response), shop); 
		model.addAttribute("shop", shop);
		model.addAttribute("page", page);
		model.addAttribute("provinceList", provinceList);
		/*model.addAttribute("deptList", deptList);
		model.addAttribute("cityList", cityList);
		model.addAttribute("areaList", areaList);*/
		return "modules/shop/shopList";
	}
	
	
	@RequestMapping(value = {"toBindUserPage"})
	public String toBindUserPage(Shop shop,HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/shop/shopBindSalesman";
	}
	
	@RequestMapping(value = {"toSelectShopPage"})
	public String toSelectShopPage(String divId,Shop shop, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("divId", divId);
		model.addAttribute("shop", shop);
		return "modules/shop/selectShopList";
	}
	
	@RequestMapping(value = {"getMyShopList"})
	public String geyMyShopList(String divId,Shop shop, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Shop> page = shopWebService.findPage(new Page<Shop>(request, response), shop); 
		model.addAttribute("divId", divId);
		model.addAttribute("shop", shop);
		model.addAttribute("page", page);
		return "modules/shop/selectShopList";
	}

	@RequiresPermissions("shop:shop:view")
	@RequestMapping(value = "form")
	public String form(Shop shop, Model model) {
		model.addAttribute("shop", shop);
		return "modules/shop/shopForm";
	}

	@RequiresPermissions("shop:shop:edit")
	@RequestMapping(value = "save")
	public String save(Shop shop, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shop)){
			return form(shop, model);
		}
		shopWebService.save(shop);
		addMessage(redirectAttributes, "保存客户管理成功");
		return "redirect:"+Global.getAdminPath()+"/shop/shop/?repage";
	}
	
	@RequiresPermissions("shop:shop:edit")
	@RequestMapping(value = "delete")
	public String delete(Shop shop, RedirectAttributes redirectAttributes) {
		shopWebService.delete(shop);
		addMessage(redirectAttributes, "删除客户管理成功");
		return "redirect:"+Global.getAdminPath()+"/shop/shop/?repage";
	}
	
	/**
	 * 批量将店铺分配给指定业务员
	 * @param shop
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "bacthBindShop")
	public Object bacthBindShop(Shop shop, RedirectAttributes redirectAttributes) {
		Json json = new Json();
		try{
			User user = UserUtils.getUser();
			String updateBy = user.getId();
			com.corner.rpc.salesman.model.Shop shopVo = new com.corner.rpc.salesman.model.Shop();
			BeanUtils.copyProperties(shopVo,shop);
			shopVo.setUpdateBy(updateBy);
			shopService.bacthBindShop(shopVo);
			json.setMsg("批量绑定客户信息成功！");
			json.setSuccess(true);
		}catch(Exception e){
			json.setMsg("批量绑定客户信息异常！");
			json.setCode("500");
			json.setSuccess(false);
		}
		return json;
	}
	

}