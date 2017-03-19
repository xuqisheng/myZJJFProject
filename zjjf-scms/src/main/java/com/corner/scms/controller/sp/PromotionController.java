package com.corner.scms.controller.sp;

import com.corner.core.beans.*;
import com.corner.core.beans.vo.Pager;
import com.corner.core.enums.ActivityType;
import com.corner.core.enums.PayMethod;
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.beans.ro.PromotionRo;
import com.corner.scms.beans.ro.ScmsStoreCondition;
import com.corner.scms.beans.vo.*;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sp.PromotionService;
import com.corner.scms.service.sp.SpVoucherGradedService;
import com.corner.scms.service.sp.StoreGruopService;
import com.corner.scms.utils.AccessTokenServiceImp;
import com.corner.scms.utils.WXSignUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value = "/scms/promotion")
public class PromotionController extends ScmsBaseWebController {

	private static Logger logger = LoggerFactory.getLogger(ScmsOrderInfoMgController.class);

	@Autowired
	private PromotionService promotionService;

	@Autowired
	private StoreGruopService storeGruopService;

	@Autowired
	private AccessTokenServiceImp accessTokenService;

	@Autowired
	SpVoucherGradedService spVoucherGradedService;

	// wxsign.do
	@RequestMapping(value = "/wxsign.do")
	@ResponseBody
	public Object wxsign(String url, HttpServletRequest request, Model model, ScmsStoreCondition condition) {
		System.out.println(url);
		if (StringUtils.isEmpty(url)) {
			return ResponseUtils.sendMsg(false, "url不能为空", request);
		}
		String ticket = accessTokenService.getWXTicket(request.getSession());
		if (StringUtils.isEmpty(url)) {
			return ResponseUtils.sendMsg(false, "ticket获取为空", request);
		}
		logger.info("【微信支付】获取前台加密的jsapi_ticket：{}", ticket);
		logger.info("【微信支付】获取前台加密的url：{}", url);
		Map<String, String> ret = WXSignUtil.sign(ticket, url);
		return ResponseUtils.sendMsg(true, ret);
	}

	// 客户分组首页
	@RequestMapping(value = "/home.do")
	public String scmshome(HttpServletRequest request, Model model, ScmsStoreCondition condition) {
		model.addAttribute("activityTypes" , ActivityType.values());
		return "/promotion/promotion-index";
	}

	// moreactive.do
	//查看更多活动  未参与的批发商活动
	@RequestMapping(value = "/moreactive.do")
	public String moreactive(HttpServletRequest request, Model model, ScmsStoreCondition condition) {

		return "/promotion/promotion-list";
	}

	//批发商 活动的列表查询    其中包括  平台的 和批发商已经参与的
	@RequestMapping(value = "/list.do")
	@ResponseBody
	public Object list(HttpServletRequest request, Model model, PromotionRo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
	     Calendar   calendar   =   new   GregorianCalendar(); 
	     if(condition.getRuleEnd()!=null){
	     calendar.setTime(condition.getRuleEnd()); 
	     calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
	     condition.setRuleEnd(calendar.getTime());}
	     //这个时间就是日期往后推一天的结果 
		List<SpVoucherActiveVo> list = this.promotionService.findAllVo(condition);
		System.out.println(request.getContextPath());
		return ResponseUtils.sendMsg(true, list);
	}

	//查看 更多活动 ajax
	@RequestMapping(value = "/morelist.do")
	@ResponseBody
	public Object morelist(HttpServletRequest request, Model model, PromotionRo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		List<SpVoucherActiveVo> list = this.promotionService.findmorelist(condition);
		int size = this.promotionService.findmorelistSize(condition);
		if(list!=null&&list.size()!=0){
			for (SpVoucherActiveVo spVoucherActiveVo : list) {
				BigDecimal plantHalve = new BigDecimal(Double.toString(spVoucherActiveVo.getPlantHalve()));
				BigDecimal spHalve = new BigDecimal("100").subtract(plantHalve);
				spVoucherActiveVo.setPlantHalveStr(plantHalve.toString());
				spVoucherActiveVo.setSpHalveStr(spHalve.toString());
			}
		}
		return ResponseUtils.sendPagination(new Pager<SpVoucherActiveVo>(size, list));
	}

	// groupcheck
	@RequestMapping(value = "/groupcheck.do")
	@ResponseBody
	public Object groupcheck(HttpServletRequest request, Model model, PromotionRo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		SpVoucherActive sp = this.promotionService.findActiveById(condition);
		StoreGroupVo v = new StoreGroupVo();
		v.setSupplierId(user.getId());
		Map<String, Object> g = new HashMap<String, Object>();
		StoreGroupVo vo = new StoreGroupVo();
		vo.setSupplierId(user.getId());
		List<StoreGroup> all = this.storeGruopService.findAllGroups(vo);
		if (sp.getRuleType() == 9 || sp.getRuleType() == 10 || sp.getRuleType() == 11 || sp.getRuleType() == 12) {
			g.put("product", "product");
		}
		List<StoreGroup> group = new ArrayList<StoreGroup>();
		for (StoreGroup grop : all) {
			List<SpVoucherActive> ac = this.promotionService.findActiveByGroup(grop);
			if (ac.size() <= 0) {
				group.add(grop);
			} else {
				boolean istrue = true;
				for (SpVoucherActive a : ac) {
					if (a.getRuleEnd().getTime() <= sp.getRuleStart().getTime() || a.getRuleStart().getTime() >= sp.getRuleEnd().getTime()) {

					} else {
						istrue = false;
						break;
					}
				}
				if (istrue) {
					group.add(grop);
				}
			}
		}

		g.put("all", group);
		return ResponseUtils.sendMsg(true, g);
	}

	// findproduct
	@RequestMapping(value = "/findproduct.do")
	@ResponseBody
	public Object findproduct(HttpServletRequest request, Model model, PromotionRo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		SpVoucherActive ac = this.promotionService.findActiveById(condition);
		condition.setSupplierId(user.getId());
		if (ac.getSendGoods() == null || ac.getSendGoods().trim().equals("")) {
			List<ScmsItemBaseVo> vos = this.promotionService.findproduct(condition);
			return ResponseUtils.sendMsg(true, vos);
		} else {
			String[] goods = ac.getSendGoods().split("&");
			List<ScmsItemBaseVo> vos = new ArrayList<ScmsItemBaseVo>();
			for (String p : goods) {
				vos.add(this.promotionService.findVoById(p.split(":")[0]));
			}
			return ResponseUtils.sendMsg(true, vos);
		}

	}

	// addpromotion
	@RequestMapping(value = "/addpromotion.do")
	@ResponseBody
	public Object addpromotion(HttpServletRequest request, Model model, PromotionRo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		condition.setId(condition.getSpVoucherActiveId().toString());
		SpVoucherActive active = this.promotionService.findActiveById(condition);
		SpVoucherActiveGift gift = this.promotionService.findGift(condition);
		if (active.getRuleEnd().getTime() < new Date().getTime()) {
			return ResponseUtils.sendMsg(false, "该活动已经结束！");
		}
		if (active.getRuleStart().getTime() < new Date().getTime() && active.getStatus() == 0) {
			return ResponseUtils.sendMsg(false, "该活动已经停止！");
		}
		if (active.getRuleType() == 9 || active.getRuleType() == 10 || active.getRuleType() == 11 || active.getRuleType() == 12) {
			if (condition.getScmsItemId() == null) {
				return ResponseUtils.sendMsg(false, "请选择赠送商品！");
			}
		}
		if (active.getRuleType() == 12) {
			String buyGoods = active.getBuyGoods();
			String[] goods = buyGoods.split(":");
			condition.setNumberInt(Integer.parseInt(goods[0]));
			List<PlantItem> item = this.promotionService.findPlantItem(condition);
			if (item == null) {
				return ResponseUtils.sendMsg(false, "不能参与此活动！");
			}
		}
		try {
			if (condition.getNumber() != null) {
				if (condition.getNumber().trim().equals("")) {
					return ResponseUtils.sendMsg(false, "赠送数量不能为空！");
				}
				condition.setNumberInt(Integer.parseInt(condition.getNumber()));
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "赠送数量只能为数字！");
		}

		try {
			if (condition.getCount() != null) {
				if (condition.getCount().trim().equals("")) {
					return ResponseUtils.sendMsg(false, "赠送总量不能为空！");
				}
				condition.setCountInt(Integer.parseInt(condition.getCount()));
				if (condition.getCountInt() <= 0) {
					return ResponseUtils.sendMsg(false, "赠送总量必须为整数！");
				}
				if (condition.getNumberInt() > condition.getCountInt()) {
					return ResponseUtils.sendMsg(false, "赠送数量不能大于总量！");
				}
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "赠送总量只能为数字！");
		}

		if (condition.getIds() == null || condition.getIds().length == 0) {
			return ResponseUtils.sendMsg(false, "请选择要参与的组！");
		}
		if (condition.getScmsItemId() != null && active.getSendGoods() == null) {
			int countProduct = this.promotionService.findCountProduct(condition);
			if (countProduct < condition.getCountInt()) {
				return ResponseUtils.sendMsg(false, "赠送总量不能大于库存总量数" + countProduct);
			}
			if (condition.getCountInt() <= 0) {
				return ResponseUtils.sendMsg(false, "赠送总量必须为整数");
			}
		}
		if (gift != null) {
			if (gift.getNumber() < condition.getNumberInt()) {
				return ResponseUtils.sendMsg(false, "赠送数量不能大于平台设置的数值");
			}
			if (gift.getCount() < condition.getCountInt()) {
				return ResponseUtils.sendMsg(false, "赠送总量不能大于平台设置的数值");
			}
		}
		this.promotionService.addPromotion(condition, active);

		return ResponseUtils.sendMsg(true, "操作成功!");
	}

	// watchActive
	@RequestMapping(value = "/watchActive.do")
	public String watchActive(HttpServletRequest request, Model model, PromotionRo condition) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		SpVoucherActive active = this.promotionService.findActiveById(condition);
		condition.setSpVoucherActiveId(active.getId());
		// 优惠劵
		if (active.getRuleType() == 1 || active.getRuleType().intValue()==13) {
			SpVoucherTemp temp = this.promotionService.findTempById(active.getSendId());
			model.addAttribute("temp", temp);
		}
		// 满送优惠劵和满送优惠劵+商品
		if (active.getRuleType() == 2 || active.getRuleType() == 9) {
			List<SpVoucherGradedVo> gradeVoList = spVoucherGradedService.getListByActiveId(active);
			model.addAttribute("gradeVoList", gradeVoList);

		}
		List<String[]> result = new ArrayList<String[]>();
		// 赠送商品
		if (active.getRuleType() == 9 || active.getRuleType() == 10 || active.getRuleType() == 11 || active.getRuleType() == 12) {
			String sendGoods = active.getSendGoods();
			if (sendGoods == null || sendGoods.equals("")) {
				SpVoucherActiveGift gift = this.promotionService.findApVoucherActiveGift(condition);
				if (gift != null) {
					String[] product = new String[4];
					product[1] = gift.getItemBaseName();
					product[2] = gift.getNumber().toString();
					product[3] = gift.getCount().toString();
					result.add(product);
				}
			} else {
				String[] r = sendGoods.split("&");
				for (String res : r) {
					result.add(res.split(":"));
				}
			}

			model.addAttribute("result", result);
		}
		if(active.getRuleType()==11||active.getRuleType()==11){
			model.addAttribute("show", false);
		}
		String rulePayStrArr[] = org.apache.commons.lang.StringUtils.split(active.getRulePayStr(), " ,");
		String RulePayStr = "";
		for (String rulePayStr : rulePayStrArr) {
			if(!StringUtils.isEmpty(rulePayStr)){
				RulePayStr+=PayMethod.getName(Byte.parseByte(rulePayStr))+"  ";
			}
		}
		
		
		SpVoucherActiveVo spVoucherActiveVo = new SpVoucherActiveVo();
		BeanUtils.copyProperties(active, spVoucherActiveVo);
		//用于处理前台页面计算平台分担比例的问题
		BigDecimal plantHalve = new BigDecimal(Double.toString(active.getPlantHalve()));
		BigDecimal spHalve = new BigDecimal(100).subtract(plantHalve);
		spVoucherActiveVo.setPlantHalveStr(plantHalve.toString());
		spVoucherActiveVo.setSpHalveStr(spHalve.toString());
		if(active.getRuleType().intValue()==13){
			SendTimeConfig sendTimeConfig = promotionService.getSendTimeConfigById(active.getTransportTimeType());
			model.addAttribute("sendTimeConfig",sendTimeConfig);
		}
		model.addAttribute("RulePayStr", RulePayStr);
		model.addAttribute("numberInt", condition.getNumberInt());
		model.addAttribute("active", spVoucherActiveVo);
		return "/promotion/promotion-detail";
	}

	@RequestMapping("/getSpVoucherTemp.do")
	public String getSpVoucherTemp(SpVoucherTemp spVoucherTemp, Model model) throws Exception {
		SpVoucherTempVo spVoucherTempVo = promotionService.getSpVoucherTemp(spVoucherTemp);
		model.addAttribute("spVoucherTempVo", spVoucherTempVo);
		return "/promotion/coupon-detail";
	}

	// updateActive_supplier
	@RequestMapping(value = "/updateActive_supplier.do")
	@ResponseBody
	public Object updateActive_supplier(HttpServletRequest request, Model model, PromotionRo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		/*
		 * if(condition.getIds()==null||condition.getIds().length==0){ return
		 * ResponseUtils.sendMsg(false,"请选择要参与的组！"); }
		 */
		this.promotionService.updateActive_supplier(condition);

		return ResponseUtils.sendMsg(true, "操作成功");
	}

	@RequestMapping(value = "/group.do")
	@ResponseBody
	public Object group(HttpServletRequest request, Model model, PromotionRo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		List<StoreGroup> alread = this.storeGruopService.findGroupsByconditionalread(condition);
		List<StoreGroup> other = this.storeGruopService.findGroupsByconditionother(condition);
		StoreGroupVo v = new StoreGroupVo();
		v.setSupplierId(user.getId());
		List<StoreGroup> all = this.storeGruopService.findGroupsByconditionalreadyno(condition);

		Map<String, Object> g = new HashMap<String, Object>();
		g.put("alread", alread);
		g.put("other", other);
		g.put("all", all);
		return ResponseUtils.sendMsg(true, g);
	}

	// promotiondetail
	@RequestMapping(value = "/promotiondetail.do")
	public String promotiondetail(HttpServletRequest request, Model model, PromotionRo condition) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		SpVoucherActive active = this.promotionService.findActiveById(condition);
		if (active.getRuleType() == 1 || active.getRuleType() == 2 || active.getRuleType() == 9) { // 优惠券
			Double num = this.promotionService.findMoneyByCondition(condition);
			BigDecimal b = new BigDecimal(num);
			num = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			int count = this.promotionService.findCountPeople(condition);
			model.addAttribute("num", num);
			model.addAttribute("count", count);
		} else if (active.getRuleType() == 3 || active.getRuleType() == 10) { // 满减
			Double num = this.promotionService.findMoneyJByCondition(condition);
			BigDecimal b = new BigDecimal(num);
			num = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			int count = this.promotionService.findCountJPeople(condition);
			model.addAttribute("num", num);
			model.addAttribute("count", count);
		}
		model.addAttribute("condition", condition);
		return "/promotion/promotion-join-detail";
	}

	// moneycheck
	@RequestMapping(value = "/moneycheck.do")
	@ResponseBody
	public Object moneycheck(HttpServletRequest request, Model model, PromotionRo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		condition.setPageIndex(pageIndex + 1);
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		SpVoucherActive active = this.promotionService.findActiveById(condition);

		Pager<SpvoucherVo> stores = null;
		if (active.getRuleType() == 1 || active.getRuleType() == 2 || active.getRuleType() == 9) {
			model.addAttribute("show", 1);
			stores = this.promotionService.findByCondition(condition);
		} else if (active.getRuleType() == 3 || active.getRuleType() == 10) {
			stores = this.promotionService.findByConditionJ(condition);
			model.addAttribute("show", 1);
		}
		model.addAttribute("page", pageIndex);
		return ResponseUtils.sendPagination(stores);
	}

}
