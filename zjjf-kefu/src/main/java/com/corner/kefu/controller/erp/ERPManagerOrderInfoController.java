/**   
* @Title: ERPManagerOrderInfoController.java 
* @Package com.corner.kefu.controller.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月4日 下午8:53:24 
* @version V1.0   
*/

package com.corner.kefu.controller.erp;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.ERPManager;
import com.corner.core.beans.ERPManagerOrderInfo;
import com.corner.core.beans.ERPPurchaseStockInfo;
import com.corner.core.beans.ERPWarehouse;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.kefu.beans.ro.erp.ERPManagerOrderInfoRo;
import com.corner.kefu.beans.ro.erp.ERPPurchaseStockInfoRo;
import com.corner.kefu.beans.vo.erp.ERPManagerOrderDetailVo;
import com.corner.kefu.beans.vo.erp.ERPManagerOrderInfoVo;
import com.corner.kefu.beans.vo.erp.ERPPurchaseStockDetailVo;
import com.corner.kefu.beans.vo.erp.ERPPurchaseStockInfoVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.erp.ERPManagerOrderInfoService;
import com.corner.kefu.service.erp.ERPManagerService;
import com.corner.kefu.service.sp.ERPWarehouseService;

/** 
* @ClassName: ERPManagerOrderInfoController 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月4日 下午8:53:24 
*  
*/
@Controller
@RequestMapping("/kefu/erpOrder")
public class ERPManagerOrderInfoController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(ERPManagerOrderInfoController.class);
	private final static String PATH = "stlx-purchase-order/";
	@Autowired
	ERPManagerOrderInfoService eRPManagerOrderInfoService;
	
	@Autowired
	ERPManagerService eRPManagerService;
	
	@Autowired
	ERPWarehouseService eRPWarehouseService;

	
	
	/**
	 * 
	* @Title: updatePurchaseOrder 
	* @Description:审核采购退货订单
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/updatePurchaseOrder.do")
	public synchronized Object updatePurchaseOrder(HttpServletRequest request) {
		String managerIdStr = request.getParameter("managerIdStr");
		if(StringUtils.isEmpty(managerIdStr)){
			return ResponseUtils.sendMsg(false,"缺少订单数据!");
		}
		CustomerService service = this.getCurrentUser(CustomerService.class, request);
		if(service==null){
			//TODO 跳转到登陆页面
		}
		String[] managerIdStrArr = managerIdStr.split(",");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("service", service);
		paramMap.put("managerIdStrArr", managerIdStrArr);
		ModelMsg msg = eRPManagerOrderInfoService.updatePurchaseOrder(paramMap);
		if(msg.isSuccess()){
			return ResponseUtils.sendMsg(true);
		}else{
			return ResponseUtils.sendMsg(false,msg.getMessage());
		}
	}
	
	
	
	
	/**
	 * 
	* @Title: checkErpOrderInfo 
	* @Description:审核订单
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/checkErpOrderInfo.do")
	@ResponseBody
	public Object checkErpOrderInfo(HttpServletRequest request) {
		String managerIdStr = request.getParameter("managerIdStr");
		CustomerService service = getCurrentUser(CustomerService.class, request);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("managerIdStr", managerIdStr);
		paramMap.put("service", service);
		ModelMsg msg = eRPManagerOrderInfoService.checkErpOrderInfo(paramMap);

		if (msg.isSuccess()) {
			return ResponseUtils.sendMsg(true);
		} else {
			return ResponseUtils.sendMsg(false);
		}
	}

	/**
	 * 
	* @Title: delErpManagerOrderInfo 
	* @Description:删除采购单
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/delErpManagerOrderInfo.do")
	@ResponseBody
	public Object delErpManagerOrderInfo(HttpServletRequest request) {
		String delManagerIdStr = request.getParameter("delManagerIdStr");
		ModelMsg msg = eRPManagerOrderInfoService.delErpManagerOrderInfo(delManagerIdStr);
		if (msg.isSuccess()) {
			return ResponseUtils.sendMsg(true);
		} else {
			return ResponseUtils.sendMsg(false);
		}
	}

	/**
	 * 
	* @Title: getOrderInfoById 
	* @Description:获取采购订单
	* @param @param orderInfoRo
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getOrderInfoById.do")
	@ResponseBody
	public Object getOrderInfoById(ERPManagerOrderInfoRo orderInfoRo) {
		if (StringUtils.isEmpty(orderInfoRo.getId())) {
			return ResponseUtils.sendMsg(false, "缺少采购订单id!");
		}
		ERPManagerOrderInfo orderInfo = eRPManagerOrderInfoService.selectById(orderInfoRo.getId());
		if (orderInfo == null||orderInfo.getIsDelete()) {
			return ResponseUtils.sendMsg(false, "采购订单不存在!");
		}
		//获取订单明细
		orderInfoRo.setOrderId(orderInfo.getOrderId());
		List<ERPManagerOrderDetailVo> detailVos = eRPManagerOrderInfoService.getOrderInfoVoById(orderInfoRo);
		int totalNum = 0;
		if(detailVos!=null&&detailVos.size()!=0){
			for (ERPManagerOrderDetailVo erpManagerOrderDetailVo : detailVos) {
				totalNum+=erpManagerOrderDetailVo.getQuantity().intValue();
			}
		}
		//获取供应商
		ERPManager manager = eRPManagerService.getERPManagerById(orderInfo.getManagerId());
		//获取仓库
		ERPWarehouse erpWarehouse = eRPWarehouseService.selectByPrimaryKey(orderInfo.getWhId());
		
		ERPManagerOrderInfoVo orderInfoVo = new ERPManagerOrderInfoVo();
		BeanUtils.copyProperties(orderInfo, orderInfoVo);
		orderInfoVo.setDetailVos(detailVos);
		orderInfoVo.setErpManager(manager);
		orderInfoVo.setErpWarehouse(erpWarehouse);
		orderInfoVo.setTotalNum(totalNum);
		
		return ResponseUtils.sendMsg(true, orderInfoVo);
	}

	/**
	 * 
	* @Title: toEditERPOrderInfo 
	* @Description:修改采购单
	* @param @param orderInfoRo
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toEditERPOrderInfo.do")
	public String toEditERPOrderInfo(ERPManagerOrderInfoRo orderInfoRo, Model model, HttpServletRequest request) {
		if (StringUtils.isEmpty(orderInfoRo.getId())) {
			return error("缺少采购单id!", model, request);
		}
		String action = request.getParameter("action");
		ERPManagerOrderInfo orderInfo = eRPManagerOrderInfoService.selectById(orderInfoRo.getId());
		if (orderInfo == null||orderInfo.getIsDelete()) {
			return error("采购单	不存在!", model, request);
		}
		if (action.equals("edit")) {
			if (orderInfo.getStatus().intValue() == 1) {
				return error("采购单已审核通过不能修改!", model, request);
			}
		}
		orderInfoRo.setOrderId(orderInfo.getOrderId());
		List<ERPManagerOrderDetailVo> detailVos = eRPManagerOrderInfoService.getOrderInfoVoById(orderInfoRo);
		model.addAttribute("detailVos", detailVos);
		model.addAttribute("orderInfo", orderInfo);
		int caiGou = 0;
		int danQian = 0;
		if(detailVos!=null&&detailVos.size()!=0){
			for (ERPManagerOrderDetailVo erpManagerOrderDetailVo : detailVos) {
				caiGou+=erpManagerOrderDetailVo.getQuantity()==null?0:erpManagerOrderDetailVo.getQuantity().intValue();
				danQian+=erpManagerOrderDetailVo.getOperateQuantity()==null?0:erpManagerOrderDetailVo.getOperateQuantity().intValue();
			}
		}
        model.addAttribute("caiGou", caiGou);
        model.addAttribute("danQian", danQian);
		if (action.equals("watch")) {
			return PATH + "purchase-view";
		}
		return PATH + "add-purchase-order";
	}
	
	/**
	 * 
	* @Title: toWatchPurchaseBackOrder 
	* @Description:
	* @param @param request
	* @param @param stockInfoRo
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toWatchPurchaseBackOrder.do")
	public String toWatchPurchaseBackOrder(HttpServletRequest request,ERPPurchaseStockInfoRo stockInfoRo,Model model) {
		if(StringUtils.isEmpty(stockInfoRo.getId())){
			return error("确实必要参数id!", model, request);
		}
		ERPPurchaseStockInfo purchaseStockInfo = eRPManagerOrderInfoService.getPurchaseStockInfoById(stockInfoRo);
		if(purchaseStockInfo==null||purchaseStockInfo.getIsDelete()){
			return error("该笔采购退货单不存在或者已被删除!", model, request);
		}
		ERPPurchaseStockInfoVo purchaseStockInfoVo = new ERPPurchaseStockInfoVo();
		BeanUtils.copyProperties(purchaseStockInfo, purchaseStockInfoVo);
		List<ERPPurchaseStockDetailVo> purchaseStockDetailVos = eRPManagerOrderInfoService.getPurcherStockDetailsByOrDerId(purchaseStockInfo);
		purchaseStockInfoVo.setPurchaseStockDetailVos(purchaseStockDetailVos);
        model.addAttribute("purchaseStockInfo", purchaseStockInfoVo);		
        model.addAttribute("purchaseStockDetailVos", purchaseStockDetailVos);		
		return PATH+"purchase-back-view";
	}
	

	@RequestMapping("/addERPOrderInfo.do")
	@ResponseBody
	public Object addERPOrderInfo(ERPManagerOrderInfoRo orderInfoRo, HttpServletRequest request) {
		if (StringUtils.isNotEmpty(orderInfoRo.getId())) {
			ERPManagerOrderInfo orderInfo = eRPManagerOrderInfoService.selectById(orderInfoRo.getId());
			if (orderInfo == null || orderInfo.getIsDelete()) {
				return ResponseUtils.sendMsg(false, "保存失败,该订单不存在或已被删除!");
			}
			orderInfoRo.setOrderId(orderInfo.getOrderId());// 设置订单编号
			orderInfoRo.setStatus((byte) 0);// 审核状态
			orderInfoRo.setId(orderInfo.getId());
		}
		if (StringUtils.isEmpty(orderInfoRo.getWhId())) {
			return ResponseUtils.sendMsg(false, "缺少仓库id!");
		}
		if (StringUtils.isEmpty(orderInfoRo.getManagerId())) {
			return ResponseUtils.sendMsg(false, "缺少供应商id!");
		}
		String managerItemIdArr[] = request.getParameterValues("managerItemIdArr");
		String managerItemNumArr[] = request.getParameterValues("managerItemNumArr");
		String managerItemPriceArr[] = request.getParameterValues("managerItemPriceArr");
		if (managerItemIdArr == null) {
			return ResponseUtils.sendMsg(false, "缺少商品id数据!");
		}
		if (managerItemNumArr == null) {
			return ResponseUtils.sendMsg(false, "缺少采购数量数据!");
		}
		if (managerItemPriceArr == null) {
			return ResponseUtils.sendMsg(false, "缺少采购价格数据!");
		}
		if (managerItemIdArr.length != managerItemNumArr.length
				|| managerItemNumArr.length != managerItemPriceArr.length) {
			return ResponseUtils.sendMsg(false, "商品id,采购数量,采购价格数据不一致!");
		}
		CustomerService customerService = this.getCurrentUser(CustomerService.class, request);
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderInfoRo", orderInfoRo);
		paramMap.put("managerItemIdArr", managerItemIdArr);
		paramMap.put("managerItemNumArr", managerItemNumArr);
		paramMap.put("managerItemPriceArr", managerItemPriceArr);
		paramMap.put("customerService", customerService);
		ModelMsg msg = eRPManagerOrderInfoService.save(paramMap);
		if (msg.isSuccess()) {
			return ResponseUtils.sendMsg(true,msg.getData());
		} else {
			return ResponseUtils.sendMsg(false,msg.getMessage());
		}
	}

	/**
	 * 
	* @Title: toWatchERPOrder 
	* @Description:跳转到查看采购单页面
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toWatchERPOrder.do")
	public String toWatchERPOrder() {
		return PATH + "purchase-view";
	}

	/**
	 * 
	* @Title: toAddERPOrder 
	* @Description:跳转到新增采购单页面
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toAddERPOrder.do")
	public String toAddERPOrder() {
		return PATH + "add-purchase-order";
	}
	
	
	/**
	 * 
	* @Title: toErpOrderIndex 
	* @Description:跳转到采购单列表页
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toErpOrderIndex.do")
	public String toErpOrderIndex(HttpServletRequest request, Model model, ERPManagerOrderInfoRo orderInfoRo) {
		String pageIndexStr = request.getParameter("pageIndex");
		if (StringUtils.isEmpty(pageIndexStr)) {
			pageIndexStr = "1";
		}
		model.addAttribute("pageIndex", pageIndexStr);

		if (StringUtils.isNotEmpty(orderInfoRo.getWhName())) {
			orderInfoRo.setWhName(orderInfoRo.getWhName().trim());
			model.addAttribute("whName", orderInfoRo.getWhName().trim());
		}
		if (orderInfoRo.getStartTime() != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(orderInfoRo.getStartTime());
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			orderInfoRo.setStartTime(calendar.getTime());
			model.addAttribute("startTime", calendar.getTime());
		}
		if (orderInfoRo.getEndTime() != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(orderInfoRo.getEndTime());
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			model.addAttribute("endTime", calendar.getTime());
			calendar.add(Calendar.DATE, 1);
			orderInfoRo.setEndTime(calendar.getTime());
		}
		if(orderInfoRo.getCheckStatus()==null || orderInfoRo.getCheckStatus().intValue()==-1){
			model.addAttribute("checkStatus", "-1");
		}else {
			model.addAttribute("checkStatus", orderInfoRo.getCheckStatus());
		}
		Pager<ERPManagerOrderInfoVo> pager = eRPManagerOrderInfoService.getOrderInfoList(orderInfoRo);
		List<ERPManagerOrderInfoVo> list = pager.getList();
		Integer total = pager.getTotalSize();
		model.addAttribute("list", list);
		pageUtil(orderInfoRo.getPageIndex(), total, orderInfoRo.getPageSize(), request, model);
		if(orderInfoRo.getType()==null||orderInfoRo.getType().intValue()==1){
			model.addAttribute("type", "1");//查询采购订单
			return PATH + "purchase-order";
		}else {
			model.addAttribute("type", "2");//查询采购退货
			return PATH + "purchase-back";
		}
	}
	
	/**
	 * 
	* @Title: toPurchaseBaceOrder 
	* @Description:获取采购退货数据
	* @param @param stockInfoRo
	* @param @param model
	* @param @param request
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toPurchaseBaceOrder.do")
	public String toPurchaseBaceOrder(ERPPurchaseStockInfoRo stockInfoRo,Model model,HttpServletRequest request) {
		String pageIndexStr = request.getParameter("pageIndex");
		if (StringUtils.isEmpty(pageIndexStr)) {
			pageIndexStr = "1";
		}
		model.addAttribute("pageIndex", pageIndexStr);
		if(StringUtils.isNotEmpty(stockInfoRo.getKeyStr())){
			stockInfoRo.setKeyStr(stockInfoRo.getKeyStr().trim());
			model.addAttribute("keyStr", stockInfoRo.getKeyStr().trim());
		}
		if (stockInfoRo.getStartTime() != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(stockInfoRo.getStartTime());
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			stockInfoRo.setStartTime(calendar.getTime());
			model.addAttribute("startTime", calendar.getTime());
		}
		if (stockInfoRo.getEndTime() != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(stockInfoRo.getEndTime());
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			model.addAttribute("endTime", calendar.getTime());
			calendar.add(Calendar.DATE, 1);
			stockInfoRo.setEndTime(calendar.getTime());
		}
		if(stockInfoRo.getCheckStatus()==null || stockInfoRo.getCheckStatus().intValue()==-1){
			model.addAttribute("checkStatus", "-1");
		}else {
			model.addAttribute("checkStatus", stockInfoRo.getCheckStatus());
		}
		Pager<ERPPurchaseStockInfoVo> pager = eRPManagerOrderInfoService.getPurchaseBack(stockInfoRo);
		List<ERPPurchaseStockInfoVo> list = pager.getList();
		Integer total = pager.getTotalSize();
		model.addAttribute("list", list);
		pageUtil(stockInfoRo.getPageIndex(), total, stockInfoRo.getPageSize(), request, model);
		return PATH + "purchase-back";
	}
}
