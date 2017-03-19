/**   
* @Title: ScmsShoppingCartController.java 
* @Package com.corner.scms.controller.sc 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月19日 上午11:48:15 
* @version V1.0   
*/

package com.corner.scms.controller.sc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.SystemInfo;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sc.ScOrderInfoMgService;
import com.corner.scms.service.sc.ScmsShoppingCartService;
import com.corner.scms.service.sp.ScmsSupplierMgService;


/**
 * @ClassName: ScmsShoppingCartController
 * @Description:采购单(购物车)控制器
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月19日 上午11:48:15
 * 
 */
@Controller
@RequestMapping(value = "/scms/cart")
public class ScmsShoppingCartController extends ScmsBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(ScmsShoppingCartController.class);

	@Autowired
	ScmsShoppingCartService scmsShoppingCartService;

	@Autowired
	ScmsSupplierMgService scmsSupplierMgService;

	@Autowired
	ScOrderInfoMgService scOrderInfoMgService;

	/**
	 * 
	* @Title: getCartList 
	* @Description:获取购物车列表
	* @param @param request
	* @param @param model
	* @param @return
	* @param @throws Exception
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getCartList.do")
	public String getCartList(HttpServletRequest request, Model model) throws Exception {
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier == null)
			return "/login/index";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplier", supplier);
		map = scmsShoppingCartService.getCartList(map);
		model.addAttribute("list", map.get("scmsMinimumVoList"));
		model.addAttribute("totalPrice", map.get("totalPrice"));
		model.addAttribute("totalProduct", map.get("totalProduct"));
		model.addAttribute("supplier", supplier);
		return "purchase/shopcart";
	}

	/**
	 * 
	* @Title: asynUpdateCart 
	* @Description:修改购物车
	* @param @param request
	* @param @return
	* @param @throws Exception
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/asynUpdateCart.do")
	@ResponseBody
	public Object asynUpdateCart(HttpServletRequest request) throws Exception {
		String type = request.getParameter("type");
		if (StringUtil.stringIsNullOrEmpty(type)) {
			throw new Exception("没有传type值");
		}
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplier", supplier);
		if (type.equals("check") || type.equals("uncheck")) {
			String cartIdStr = request.getParameter("cartIdStr");
			if (StringUtil.stringIsNullOrEmpty(cartIdStr)) {
				return ResponseUtils.sendMsg(false, "请选择商品!");
			}
			String[] cartIdArr = cartIdStr.split(",");
			List<String> cartIdList = Arrays.asList(cartIdArr);
			map.put("cartIdList", cartIdList);
			map.put("type", type);
			map = scmsShoppingCartService.updateCartListBySupplierId(map);
		} else if (type.equals("add")) {// 包括加减
			String cartId = request.getParameter("cartId");
			String quantity = request.getParameter("quantity");
			if (StringUtil.stringIsNullOrEmpty(cartId) || StringUtil.stringIsNullOrEmpty(quantity)) {
				return ResponseUtils.sendMsg(false, "缺少必要参数");// 缺少必要参数
			}
			if (Integer.parseInt(quantity) < 1) {
				return ResponseUtils.sendMsg(false, "商品数量不能小于1");
			}
			map.put("cartId", cartId);
			map.put("quantity", quantity);
			map = scmsShoppingCartService.addCartListBySupplierId(map);
			if (map == null) {
				return ResponseUtils.sendMsg(false, "null");
			}
		} else if (type.equals("delete")) {
			String cartIdStr = request.getParameter("cartIdArr");
			String[] cartIdArr = cartIdStr.split(",");
			if (cartIdArr == null || cartIdArr.length == 0) {
				return ResponseUtils.sendMsg(false, "请选择要删除的商品");
			}
			List<String> cartIdList = Arrays.asList(cartIdArr);
			map.put("cartIdList", cartIdList);
			map = scmsShoppingCartService.deleteShoppingCartBySupplierId(map);
		}
		if ((Boolean) map.get("noShopping") != null && (Boolean) map.get("noShopping") == true) {// 购物车中没有商品
			return ResponseUtils.sendMsg(false, null);
		}
		return ResponseUtils.sendMsg(true, map);
	}

	/**
	 * 
	* @Title: toConfigOrderInfo 
	* @Description:跳转到确认订单页面
	* @param @param request
	* @param @param model
	* @param @return
	* @param @throws Exception
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toConfigOrderInfo.do")
	public String toConfigOrderInfo(HttpServletRequest request, Model model) throws Exception {
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier == null)
			return "/login/index";
		supplier = scmsSupplierMgService.selectByPrimaryKey(supplier.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplier", supplier);
		map = scmsShoppingCartService.getConfirmedCartList(supplier);

		if (map == null) {// 表示购物车中有不符合起购量的商品
			return "redirect:/scms/cart/getCartList.do";
		}
		if ((Boolean) map.get("noShopping") != null && (Boolean) map.get("noShopping") == true) {// 购物车中没有商品
			return "redirect:/scms/procurementcenter/listPage.do";
		}
		//购物车中有商品,但是都没有选中
		Integer totalProduct = (Integer) map.get("totalProduct");
		if(totalProduct==0){//表示购物车中没有商品被选中
			return "redirect:/scms/cart/getCartList.do";
		}
		
		model.addAttribute("supplier", supplier);
		model.addAttribute("list", map.get("scmsMinimumVoList"));
		model.addAttribute("totalPrice", map.get("totalPrice"));
		model.addAttribute("freight", (Boolean)map.get("dfFee") ? 0.00 : map.get("freight"));
		model.addAttribute("scmsWarehouse", map.get("scmsWarehouse"));
		model.addAttribute("totalProduct", totalProduct);
		return "purchase/shopcart-confirm";
	}

	/**
	 * 
	* @Title: submitOrders 
	* @Description:提交订单
	* @param @param request
	* @param @param model
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/submitOrders.do")
	@ResponseBody
	public Object submitOrders(HttpServletRequest request, Model model) {
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		supplier = scmsSupplierMgService.selectByPrimaryKey(supplier.getId());
		String ordertype = request.getParameter("ordertype");// 配送方式 0-送货上门;2-自提
		if (StringUtil.stringIsNullOrEmpty(ordertype)) {
			return ResponseUtils.sendMsg(false, "ordertype为空");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplier", supplier);
		map.put("ordertype", ordertype);
		String remark = request.getParameter("remark");
		// TODO remark过滤和转义

		if (!StringUtil.stringIsNullOrEmpty(remark)) {
			map.put("remark", remark);
		}
		try {
			map = scmsShoppingCartService.addOrders(map);
		} catch (Exception e) {
			logger.error("【联合采购--提交订单错误】", e);
			return ResponseUtils.sendMsg(false, e.toString());
		}
		if (map == null) {
			// 表示提交的订单不满足要求
			return ResponseUtils.sendMsg(false, 1);// 表示购物车中存在不满足起购量的商品
		} else if ((Boolean) map.get("noShopping") != null && (Boolean) map.get("noShopping") == true) {
			return ResponseUtils.sendMsg(false, 2);// 表示购物车为空
		}else if (((Integer)map.get("totalProduct"))==0) {//表示购物车中选中的商品为0
			return ResponseUtils.sendMsg(false, 3);
		}
		return ResponseUtils.sendMsg(true, map.get("scOrderInfoId"));
	}

	/**
	 * 
	* @Title: toSettlement 
	* @Description:去结算页面
	* @param @param request
	* @param @param model
	* @param @return
	* @param @throws Exception
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toSettlement.do")
	public String toSettlement(HttpServletRequest request, Model model) throws Exception {
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier == null)
			return "/login/index";
		String orderid = request.getParameter("orderid");//ScOrderInfo 的主键
		if (StringUtil.stringIsNullOrEmpty(orderid)) {
			return "redirect:/scms/procurementcenter/listPage.do";// 跳转到采购中心
		}
		ScOrderInfo scOrderInfo = scOrderInfoMgService.selectByPrimaryKey(orderid);
		if (scOrderInfo == null || scOrderInfo.getStatus() >= 2 || scOrderInfo.getSupportStatus() == 1) {
			//没有该订单,该订单已经支付成功
			return "redirect:/scms/procurementcenter/listPage.do";// 跳转到采购中心
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplier", supplier);
		map.put("scOrderInfo", scOrderInfo);
		/**
		 * 再次联查该笔订单的价格 保证该笔订单中商品价格是最新价格.
		 * 有一种情况是,用户提交订单后,但是没有马上结算
		 * 然后下次结算的时候,后台商品价格变化
		 * 在这里进行联查,是为了确定用户钱包是否足够支付订单金额
		 */
		//map = scmsShoppingCartService.updateScOrderInfoToSettlement(map);
		model.addAttribute("orderId", scOrderInfo.getOrderId());
		model.addAttribute("scOrderInfoId", scOrderInfo.getId());
		supplier = scmsSupplierMgService.selectByPrimaryKey(supplier.getId());
		model.addAttribute("supplier", supplier);
		if(StringUtil.stringIsNullOrEmpty(supplier.getPayPassword())){
			model.addAttribute("noPayPassword", true);
		}
		BigDecimal totalPrice = scOrderInfo.getOrderPrice();// 订单总金额
		// 如果该笔订单之前已经有扣除过钱包余额
		if (scOrderInfo.getIsUsedBalance() != null && scOrderInfo.getIsUsedBalance() == true) {
			//该笔订单之前已经用钱包支付过了,此次只能用微信支付
			model.addAttribute("payMethod", 0);// 0表示仅使用微信支付;1表示仅用钱包支付;2表示用钱包和微信支付
			// 微信支付的部分是订单总金额减去钱包金额
			model.addAttribute("isUsedBalance", true);//用于页面显示已经用钱包支付了多少金额
			model.addAttribute("balanceUsedNum", scOrderInfo.getBalanceUsedNum());
			model.addAttribute("WXpayFee", scOrderInfo.getThirdPayFee());
			model.addAttribute("outOfWxPayPrice", scOrderInfo.getThirdPayRealNum());//微信实际支出
			totalPrice = scOrderInfo.getThirdPayRealNum();
		}else {
			if (supplier.getWallet() == null || supplier.getWallet().compareTo(new BigDecimal("0")) == 0) {
				model.addAttribute("payMethod", 0);// 0表示仅使用微信支付;1表示仅用钱包支付;2表示用钱包和微信支付
				// 微信支付的部分是订单总金额减去钱包金额
				BigDecimal outOfWxPayPrice = caculatWxPayPrice(totalPrice);//微信实际要支付的金额
				BigDecimal WXpayFee =outOfWxPayPrice.subtract(totalPrice);//微信手续费
				model.addAttribute("WXpayFee", WXpayFee);
				model.addAttribute("outOfWxPayPrice", outOfWxPayPrice.toPlainString());//微信实际支出
				scOrderInfo.setThirdPayFee(WXpayFee);
				scOrderInfo.setThirdPaymentNum(totalPrice);
				scOrderInfo.setThirdPayRealNum(outOfWxPayPrice);
				//更新订单支付方式
				//计算outOfPrice字段计算订单要支付的总额
				scOrderInfo.setOutOfPrice(outOfWxPayPrice);
				scOrderInfoMgService.updateByPrimaryKeySelective(scOrderInfo);
				totalPrice = totalPrice.add(WXpayFee);
			}else if (supplier.getWallet().compareTo(totalPrice) >= 0) {
				model.addAttribute("payMethod", 1);// 仅用钱包支付
				model.addAttribute("QBpayPrice", totalPrice);
				//更新订单支付方式
				scOrderInfo.setThirdPayFee(new BigDecimal("0"));
				scOrderInfo.setThirdPaymentNum(new BigDecimal("0"));
				scOrderInfo.setThirdPayRealNum(new BigDecimal("0"));
				scOrderInfo.setOutOfPrice(totalPrice);
				scOrderInfoMgService.updateByPrimaryKeySelective(scOrderInfo);
			} else {
				model.addAttribute("payMethod", 2);// 用钱包和微信支付
				model.addAttribute("QBpayPrice", supplier.getWallet());// 钱包要支付的金额
				BigDecimal WXpayPrice = totalPrice.subtract(supplier.getWallet());// 微信要支付的金额
				BigDecimal outOfWxPayPrice = caculatWxPayPrice(WXpayPrice);//微信实际要支付的金额
				BigDecimal WXpayFee =outOfWxPayPrice.subtract(WXpayPrice);//微信手续费
				model.addAttribute("WXpayFee", WXpayFee);
				model.addAttribute("outOfWxPayPrice", outOfWxPayPrice.toPlainString());
				totalPrice = totalPrice.add(WXpayFee);
				scOrderInfo.setThirdPayFee(WXpayFee);
				scOrderInfo.setThirdPaymentNum(WXpayPrice);
				scOrderInfo.setThirdPayRealNum(outOfWxPayPrice);
				//更新订单支付方式
				scOrderInfo.setOutOfPrice(totalPrice);
				scOrderInfoMgService.updateByPrimaryKeySelective(scOrderInfo);
			}
		}
		model.addAttribute("totalPrice", totalPrice);// 要在页面显示的应付金额
		return "purchase/shopcart-pay";
	}

	private BigDecimal caculatWxPayPrice(BigDecimal totalPrice) throws Exception{
		SystemInfo systemInfo =scmsShoppingCartService.getThirdFee();
		String content = systemInfo.getContent();
		if(!StringUtil.stringIsNullOrEmpty(content)){
			Float fee = Float.parseFloat(content);
			if(fee!=0){
				fee = 1-fee;
				totalPrice = totalPrice.divide(new BigDecimal(fee.toString()),2);
				return totalPrice;
			}
		}
		return totalPrice;
	}
}
