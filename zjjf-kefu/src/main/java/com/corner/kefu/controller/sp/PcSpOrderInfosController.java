package com.corner.kefu.controller.sp;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.Store;
import com.corner.core.beans.Supplier;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.SpOrderInfoRo;
import com.corner.kefu.beans.ro.sp.SupplierRo;
import com.corner.kefu.beans.vo.sp.SupplierVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.SpOrderInfoService;
import com.corner.kefu.service.sp.SpStoreService;
import com.corner.kefu.service.sp.SpSupplierService;


/**
 * 开店宝pc端订单控制器
 * 
 * @author aimee at 2015年6月5日上午10:58:41
 * @email 1297579898@qq.com
 */
@Controller
@RequestMapping(value = "/Customer/SpOrderInfo")
public class PcSpOrderInfosController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(PcSpOrderInfosController.class);

	@Autowired
	private SpOrderInfoService spOrderInfoService;
	@Autowired
	private SpStoreService spStoreService;
	@Autowired
	private SpSupplierService spSupplierService;

	/**
	 * 获取订单列表
	 * 
	 * @author Dick 2015年5月18日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param spOrderInfoRo
	 * @return
	 */
	@RequestMapping(value = "/GetSpOrderInfos.do")
	public String getSpOrderInfos(SpOrderInfoRo spOrderInfoRo, HttpServletRequest request, Model model) {
		try {
			if (spOrderInfoRo.getStatus() == null || spOrderInfoRo.getStatus() == 0) {
				spOrderInfoRo.setStatus((byte) 1);
			}

			Supplier supplier = (Supplier) request.getSession().getAttribute("supplier");
			CustomerService service = (CustomerService) request.getSession().getAttribute("service");
			if (supplier != null && service == null) {
				// 如果是供应商登录，则按手机号查询订单
				spOrderInfoRo.setSupplierTel(supplier.getMobile());
				model.addAttribute("supplierMobile", supplier.getMobile());
				if (spOrderInfoRo.getStatus() == 1) {
					return "order/order";
				}
			}
			switch (spOrderInfoRo.getStatus()){
				case 10 :
					spOrderInfoRo.setSortName("addTime");
				case 2 :
					spOrderInfoRo.setSortName("gaveTime");
				case 3 :
					spOrderInfoRo.setSortName("getOrderTime");
				case 4 :
					spOrderInfoRo.setSortName("printTime");
				case 40 :
					spOrderInfoRo.setSortName("whSendTime");
				case 50 :
					spOrderInfoRo.setSortName("whAckTime");
				case 5 :
					spOrderInfoRo.setSortName("printTime");
				default:
					spOrderInfoRo.setSortName("addTime");
			}
			spOrderInfoRo.setSortOrder("DESC");

			model.addAttribute("spOrderInfoRo", spOrderInfoRo);
			// 查询订单
			List<SpOrderInfo> spOrderInfos = spOrderInfoService.getSpOrderInfospc(spOrderInfoRo);
			model.addAttribute("spOrderInfos", spOrderInfos);
			// 订单总数量
			Integer count = spOrderInfoService.getSpOrderInfospcCount(spOrderInfoRo);
			this.pageUtil(spOrderInfoRo.getPageIndex(), count, spOrderInfoRo.getPageSize(), request, model);
			return "order/order";
		} catch (Exception e) {
			logger.error("PcSpOrderInfosController's method getSpOrderInfos has an error:{}", e);
		}
		return error("出错了", model, request);
	}

	
	/**
	 * 根据条件搜索订单
	 * 
	 * @author Dick 2015年6月11日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param spOrderInfoRo
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/SpOrderInfosCondition.do")
	public String getSpOrderInfosCondition(SpOrderInfoRo spOrderInfoRo, HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		if(!StringUtil.stringIsNullOrEmpty(spOrderInfoRo.getOrderid())){
			spOrderInfoRo.setStorename(spOrderInfoRo.getOrderid().trim());
		}
		/*if(spOrderInfoRo.getStatus()==null && spOrderInfoRo.getStatus().compareTo(new Byte("0"))==0){
			spOrderInfoRo.setStatus(new Byte("1"));
		}*///搜索不分状态
		spOrderInfoRo.setOrderid(spOrderInfoRo.getOrderid().trim());
		spOrderInfoRo.setStatus(null);
		spOrderInfoRo.setPageSize(spOrderInfoRo.getPageSize());
		if(StringUtils.isEmpty(spOrderInfoRo.getSupplierTel())){
			//客服在查找列表
			spOrderInfoRo.setSupplierTel(null);
		}else{
			//供应商在查找列表
			model.addAttribute("supplierMobile", spOrderInfoRo.getSupplierTel());
		}
		List<SpOrderInfo> spOrderInfos = spOrderInfoService.getSpOrderInfospc(spOrderInfoRo);
		if (spOrderInfos != null && spOrderInfos.size() == 1) {
			//如果结果只有一条则页面显示此条订单的状态
			spOrderInfoRo.setStatus(spOrderInfos.get(0).getStatus());
		}
		model.addAttribute("spOrderInfos", spOrderInfos);
		model.addAttribute("spOrderInfoRo", spOrderInfoRo);
		
		//订单长度
		Integer count = spOrderInfoService.getSpOrderInfospcCount(spOrderInfoRo);
		this.pageUtil(spOrderInfoRo.getPageIndex(), count, spOrderInfoRo.getPageSize(), request, model);
		return "order/order";
	}

	
	/**
	 * 获取当前用户session
	 * @param model
	 * @param request
	 * @return
	 */
	public String getCurrentUser(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session==null){
			model.addAttribute("relogin",true);
			return error("登录过期", model, request);
		}
		Object obj = session.getAttribute("service");
		if(obj==null){
			return error("无相应的权限", model, request);
		}
		return null;
	}

	
	/**
	 * 根据条件查询该订单内容
	 * 
	 * @author Dick 2015年5月18日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param spOrderInfoRo
	 * @return
	 */
	@RequestMapping(value = "/getSpOrderInfo.do")
	public String getSpOrderInfo(SpOrderInfoRo spOrderInfoRo, Model model, HttpServletRequest request) {
		try {
			if (StringUtil.stringIsNullOrEmpty(spOrderInfoRo.getOrderid())) {
				return error("请求的参数有误", model, request);
			}
			SpOrderInfo so = spOrderInfoService.getSpOrderInfo(spOrderInfoRo);
			if (so == null) {
				return error("该订单不存在", model, request);
			}
			//重新发送
			String send_agin = request.getParameter("send_agin");
			if (send_agin != null) {
				model.addAttribute("send_agin", send_agin);
			}
			Supplier supplier = (Supplier) request.getSession().getAttribute("supplier");

			if (supplier != null) {
				if (!supplier.getMobile().equals(so.getSupplierTel())) {
					return error("此订单不存在", model, request);
				}
			}
			// 订单详细
			List<SpOrderDetail> orderDetails = spOrderInfoService.getOrderDetail(spOrderInfoRo.getOrderid());
			BigDecimal bigDecimal = new BigDecimal(0.00);
			int ordernum = 0;
			for (SpOrderDetail orderDetail : orderDetails) {
				bigDecimal = bigDecimal.add(orderDetail.getTotalPrice());
				ordernum += orderDetail.getQuantity();
			}

			SupplierRo s = new SupplierRo();
			Store as = spStoreService.getStoreById(so.getStoreId());
			if (as == null) {
				return error("店铺不存在", model, request);
			}
			s.setAreaid(as.getAreaId());
			s.setPageSize(30);
			// 供应商信息
			List<SupplierVo> su = spSupplierService.getSuppliers(s).getList();
			model.addAttribute("supplierList", su);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderInfo", so);
			map.put("ordernum", ordernum);
			map.put("total", bigDecimal);
			map.put("orderDetail", orderDetails);
			map.put("linkOrderNo", orderDetails==null?null:orderDetails.get(0).getOrderId());
			model.addAttribute("map", map);
			return "order/order-detail";
		} catch (Exception e) {
			logger.error("PcSpOrderInfosController's method getSpOrderInfo has an error:{}", e);
		}
		return error("出错了", model, request);
	}

	
}
