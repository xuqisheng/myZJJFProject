/**   
* @Title: ERPMarketStockInfoController.java 
* @Package com.corner.scms.controller.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月8日 下午2:19:48 
* @version V1.0   
*/

package com.corner.scms.controller.erp;

import java.math.BigDecimal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;


import com.corner.core.beans.*;
import com.corner.core.enums.SpOrderOwnerCash;
import com.corner.core.enums.SpOrderOwnerType;
import com.corner.core.utils.StringUtil;
import com.corner.scms.interceptor.Token;
import com.corner.scms.service.erp.*;
import com.corner.scms.service.sp.SpOrderService;
import com.corner.scms.utils.BeanUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ERPSpOrderOwnerDetail;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.enums.SpOrderOwnerType;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.beans.ro.erp.ERPMarketStockInfoRo;
import com.corner.scms.beans.ro.erp.SpOrderInfoMgRo;
import com.corner.scms.beans.vo.SpOrderDetailMgVo;
import com.corner.scms.beans.vo.SpOrderInfoMgVo;
import com.corner.scms.beans.vo.erp.ERPMarketStockDetailVo;
import com.corner.scms.beans.vo.erp.ERPMarketStockInfoVo;
import com.corner.scms.beans.vo.erp.ERPWarehouseVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.erp.ERPSpOrderOwnerService;
import com.corner.scms.utils.BeanUtil;

/** 
* @ClassName: ERPMarketStockInfoController 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月8日 下午2:19:48 
*  
*/
@Controller
@RequestMapping("/scms/erpMarket")
public class ERPMarketStockInfoController extends ScmsBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(ERPMarketStockInfoController.class);
	private static final String PATH = "stlx-sale-out/";
	@Autowired
	ERPMarketStockInfoService eRPMarketStockInfoService;
	@Autowired
	ERPWarehouseService eRPWarehouseService;
	@Autowired
	ERPMarketStockService erpMarketStockService;
	@Autowired
	ERPSpOrderOwnerService erpSpOrderOwnerService;
	@Autowired
	SpOrderService spOrderService;

	
	/**
	 * 
	* @Title: updateSaleOut 
	* @Description:保存销售出库当
	* @param @param stockInfoRo
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/updateSaleOut.do")
	@ResponseBody
	public synchronized Object updateSaleOut(ERPMarketStockInfoRo stockInfoRo,HttpServletRequest request) {
		if(StringUtils.isEmpty(stockInfoRo.getId())){
		   return ResponseUtils.sendMsg(false, "缺少销售出库单id!");
		}
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			//TODO 跳转到登陆页面
		}
		//区位id数组
		String[] wareHouseIdArr = request.getParameterValues("wareHouseIdArr");
		//出库数量数组
		String[] operateQuantityArr = request.getParameterValues("operateQuantityArr");
		//SpOrderDetailid 数组
		String[] orderDetailIdArr = request.getParameterValues("orderDetailIdArr");
		if(wareHouseIdArr==null){
			return ResponseUtils.sendMsg(false, "缺少区位id数据!");
		}
		if(operateQuantityArr==null){
			return ResponseUtils.sendMsg(false, "缺少出库数量数据!");
		}
		if(orderDetailIdArr==null){
			return ResponseUtils.sendMsg(false, "缺少出库单详情数据!");
		}
		if(wareHouseIdArr.length!=operateQuantityArr.length||operateQuantityArr.length!=orderDetailIdArr.length){
			return ResponseUtils.sendMsg(false, "区位id数据,出库数量数据,出库单详情数据不匹配!");
		}
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("stockInfoRo", stockInfoRo);
		paramMap.put("wareHouseIdArr", wareHouseIdArr);
		paramMap.put("operateQuantityArr", operateQuantityArr);
		paramMap.put("orderDetailIdArr", orderDetailIdArr);
		paramMap.put("supplier", supplier);
		ModelMsg msg = eRPMarketStockInfoService.updateSaleOut(paramMap);
		if(msg.isSuccess()){
			return ResponseUtils.sendMsg(true);
		}else {
			return ResponseUtils.sendMsg(false, msg.getMessage());
		}
	}
	
	
	
	/**
	 * 
	* @Title: addSaleBack 
	* @Description:保存销售退货单
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/addSaleBack.do")
	@ResponseBody
	public Object addSaleBack(HttpServletRequest request) {
		
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			//TODO 跳转到登陆页面
		}
		
		//销售出库单
		String marketStockInfoId = request.getParameter("marketStockInfoId");
		//ERPMarketStockDetail id 数组
		String[] detailIdArr = request.getParameterValues("detailIdArr");
		//operateStockArr 退货数量
		String[] operateStockArr = request.getParameterValues("operateStockArr");
		//备注信息
		String[] remarkArr = request.getParameterValues("remarkArr");
		
		if(StringUtils.isEmpty(marketStockInfoId)){
			return ResponseUtils.sendMsg(false, "缺少出库单id!");
		}
		if(detailIdArr==null){
			return ResponseUtils.sendMsg(false, "缺少出库单详情id!");
		}
		if(operateStockArr==null){
			return ResponseUtils.sendMsg(false, "缺少退货数量数据!");
		}
		if(detailIdArr.length!=operateStockArr.length){
			return ResponseUtils.sendMsg(false, "出库单详情id和退货数量数据不匹配!");
		}
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("marketStockInfoId", marketStockInfoId);
		paramMap.put("detailIdArr", detailIdArr);
		paramMap.put("operateStockArr", operateStockArr);
		paramMap.put("remarkArr", remarkArr);
		paramMap.put("supplier", supplier);
		ModelMsg msg = eRPMarketStockInfoService.addSaleBack(paramMap);
		if(msg.isSuccess()){
			return ResponseUtils.sendMsg(true);
		}else {
			return ResponseUtils.sendMsg(false,msg.getMessage());
		}
	}
	
	
	/**
	 * 
	* @Title: getMarkeStockDetailList 
	* @Description:
	* @param @param stockInfoRo
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getMarkeStockDetailList.do")
	@ResponseBody
	public Object getMarkeStockDetailList(HttpServletRequest request) {
		//ERPMarketStockInfo 主键
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)){
			return ResponseUtils.sendMsg(false, "缺少出库单id!");
		}
		List<ERPMarketStockDetailVo> list = eRPMarketStockInfoService.getStockDetailListByStockInfoId(id);
		return ResponseUtils.sendMsg(true, list);
	}
	
	
	
	/**
	 * 
	* @Title: getErpMarketStockInfos 
	* @Description:获取已审核通过的销售出库单
	* @param @param stockInfoRo
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getErpMarketStockInfos.do")
	@ResponseBody
	public Object getErpMarketStockInfos(ERPMarketStockInfoRo stockInfoRo ,HttpServletRequest request) {
		Supplier supplier = getCurrentUser(Supplier.class , request);
		stockInfoRo.setSupplierId(supplier.getId());
		Pager<Map<String, Object>> pager = eRPMarketStockInfoService.getErpMarketStockInfos(stockInfoRo);
		pager.setFlag(true);
		return pager;
	}

	/**
	 * 
	* @Title: toAddSaleOutBack 
	* @Description:跳转到新增销售退货
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toAddSaleOutBack.do")
	public String toAddSaleOutBack() {
		return "stlx-sale-back/add-sale-back";
	}
	
	
	/**
	 * 
	* @Title: upDateMarketStock 
	* @Description:编辑出库单
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/upDateMarketStock.do")
	@ResponseBody
	public synchronized Object upDateMarketStock(HttpServletRequest request) {
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			//TODO 跳转到登陆页面
		}
		String type = request.getParameter("type");
		String ids = request.getParameter("ids");
		String[] idArr = StringUtils.split(ids, ",");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("idArr", idArr);
		paramMap.put("supplier", supplier);
		try {
			if(type.equals("check")){//审核
				ModelMsg msg = eRPMarketStockInfoService.updateStatus(paramMap);
				if(!msg.isSuccess()){
					return ResponseUtils.sendMsg(false, msg.getMessage());
				}
			}else if (type.equals("outStock")) {//出库
				eRPMarketStockInfoService.updateOutStock(paramMap);
			}else if (type.equals("send")) {//确认送达
				eRPMarketStockInfoService.updateSend(paramMap);
			}else if (type.equals("orderBack")) {//退货
				eRPMarketStockInfoService.updateOrderBack(paramMap);
			}else if (type.equals("delete")) {//删除
				eRPMarketStockInfoService.updateDel(paramMap);
			}
			return ResponseUtils.sendMsg(true);
		}catch (Exception e){
			return ResponseUtils.sendMsg(false, e.getMessage());
		}
	}
	
	
	/**
	 * 
	* @Title: upDateMarketBackStock 
	* @Description:编辑退货单
	* @param @param stockInfoRo
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/upDateMarketBackStock.do")
	@ResponseBody
	public Object upDateMarketBackStock(ERPMarketStockInfoRo stockInfoRo,HttpServletRequest request) {
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			//TODO 跳转到登陆页面
		}
		String type = request.getParameter("type");
		String ids = request.getParameter("ids");
		String[] idArr = StringUtils.split(ids, ",");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("idArr", idArr);
		paramMap.put("supplier", supplier);
		if(type.equals("check")){//审核
			ModelMsg msg = eRPMarketStockInfoService.updateSaleBackStatus(paramMap);
		}
		return null;
	}
	
	
	/**
	 * 
	* @Title: toWachMarketStockInfo 
	* @Description:
	* @param @param infoRo
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toWachMarketStockInfo.do")
	@Token(save = true)
    public String toWachMarketStockInfo(ERPMarketStockInfoRo infoRo,HttpServletRequest request,Model model) {
		ERPMarketStockInfoVo erpMarketStockInfoVo = eRPMarketStockInfoService.getERPMarketStockInfoVoById(infoRo);
		if(erpMarketStockInfoVo==null){
			return error("销售单不存在或被删除!", model, request);
		}

		model.addAttribute("stockInfo", erpMarketStockInfoVo);
		List<ERPMarketStockDetailVo> list = eRPMarketStockInfoService.getStockDetailList(erpMarketStockInfoVo);
		model.addAttribute("list", list);
		model.addAttribute("ownerTypes" , new SpOrderOwnerType[]{SpOrderOwnerType.MONEY});
		model.addAttribute("ownerCashs" , SpOrderOwnerCash.values());
		model.addAttribute("ownerDetail" , erpSpOrderOwnerService.getOwnerDetail(erpMarketStockInfoVo.getpId()));

		return PATH+"sale-out-view";
	}	
	
	
	/**
	 * 
	* @Title: saveMarketStockInfo 
	* @Description:保存销售出库单
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/saveMarketStockInfo.do")
	@ResponseBody
	public synchronized Object saveMarketStockInfo(HttpServletRequest request) throws Exception{
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			//TODO 跳转到登陆页面
		}
		
        //仓库id		
		String whId = request.getParameter("whId");
		if(StringUtils.isEmpty(whId)){
			return ResponseUtils.sendMsg(false, "缺少仓库id!");
		}
		
		//SpOrDerInfo level=2 的orderId
		String spOrderId = request.getParameter("spOrderId");
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(spOrderId)){
			return ResponseUtils.sendMsg(false, "缺少销售订单id!");
		}
		//SpOrderDetail id
		String[] orderDetailIdArr = request.getParameterValues("orderDetailIdArr");
		String[] ids = request.getParameterValues("ids");

		//出库操作数量
		String[] operateQuantityArr = request.getParameterValues("operateQuantityArr");
		//仓库id
		String[] wareHouseIdArr = request.getParameterValues("wareHouseIdArr");
		//已出库数量
		String[] operateStockArr = request.getParameterValues("operateStockArr");
		if(orderDetailIdArr==null){
			return ResponseUtils.sendMsg(false, "缺少订单详情id!");
		}
		if(operateQuantityArr==null){
			return ResponseUtils.sendMsg(false, "缺少已出库数量!");
		}
		if(operateStockArr==null){
			return ResponseUtils.sendMsg(false, "缺少出库数量!");
		}
		if(wareHouseIdArr==null){
			return ResponseUtils.sendMsg(false, "缺少库位id!");
		}
		if(orderDetailIdArr.length!=operateQuantityArr.length
		    &&operateQuantityArr.length!=wareHouseIdArr.length
		    &&wareHouseIdArr.length!=operateStockArr.length){
			return ResponseUtils.sendMsg(false, "订单详情id,出库数量,仓库id数据不匹配!");
		}
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("whId", whId);
		paramMap.put("spOrderId", spOrderId);
		paramMap.put("id", StringUtil.stringIsNullOrEmpty(id) ? "":id);
		paramMap.put("ids", ids);
		paramMap.put("orderDetailIdArr", orderDetailIdArr);
		paramMap.put("operateQuantityArr", operateQuantityArr);
		paramMap.put("wareHouseIdArr", wareHouseIdArr);
		paramMap.put("operateStockArr", operateStockArr);
		paramMap.put("supplier", supplier);
		
		ModelMsg msg = eRPMarketStockInfoService.save(paramMap);
		if(msg.isSuccess()){
			return ResponseUtils.sendMsg(true , msg.getData());
		}else {
			return ResponseUtils.sendMsg(false,msg.getMessage());
		}
	}
	
	
	
	/**
	 * 
	* @Title: getWarehouseLevel3 
	* @Description:
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getWarehouseLevel3.do")
	@ResponseBody
	public Object getWarehouseLevel3(HttpServletRequest request) throws Exception{
		String wareHouseId = request.getParameter("wareHouseId");
		String whName = request.getParameter("whName");
		if(StringUtils.isEmpty(wareHouseId)){
			return ResponseUtils.sendMsg(false, "缺少仓库id!");
		}
		Map<String , Object> map = new HashedMap();
		map.put("wareHouseId" , wareHouseId);
		map.put("whName" , whName);
		List<ERPWarehouseVo> list = eRPWarehouseService.getWarehouseLevel3ByLevel1Id(map);
		if(list==null||list.size()==0){
			return ResponseUtils.sendMsg(false, "缺少仓位数据!");
		}
		return ResponseUtils.sendMsg(true, list);
	}
	
	
	/**
	 * 
	* @Title: getOrDerDetailList 
	* @Description:获取订单详情
	* @param @param orderInfoMgRo
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getOrDerDetailList.do")
	@ResponseBody
	public Object getOrDerDetailList(SpOrderInfoMgRo orderInfoMgRo, HttpServletRequest request) {
		Pager<SpOrderDetailMgVo> pager = eRPMarketStockInfoService.getOrDerDetailList(orderInfoMgRo);
		List<ERPSpOrderOwnerDetail> list = erpSpOrderOwnerService.getOwnerDetail(orderInfoMgRo.getId());
		for (ERPSpOrderOwnerDetail erpSpOrderOwnerDetail : list) {
			if(erpSpOrderOwnerDetail.getType() == SpOrderOwnerType.MONEY.getIndex())
				continue;
			else if(erpSpOrderOwnerDetail.getOutStockNum() >= erpSpOrderOwnerDetail.getCashQuantity())
				continue;
			SpOrderDetailMgVo vo = BeanUtil.toObject(SpOrderDetailMgVo.class , erpSpOrderOwnerDetail);
			vo.setPrice(new BigDecimal(0));
			vo.setQuantity(erpSpOrderOwnerDetail.getCashQuantity());
			vo.setTotalPrice(vo.getPrice());
			pager.getList().add(vo);
		}
		pager.setFlag(true);
		return pager;
	}
	
	
	/**
	 * 
	* @Title: toSaveSaleOutOrder 
	* @Description:跳转到新增销售单页面
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toSaveSaleOutOrder.do")
	public String toSaveSaleOutOrder(String id, Model model,HttpServletRequest request) throws Exception {
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			return "/login/index";
		}else if(!StringUtil.stringIsNullOrEmpty(id)) {
			List<ERPMarketStockInfo> list = erpMarketStockService.getInfoByPId(id , null);
			if(list == null || list.size() == 0){
				SpOrderInfo info = spOrderService.getInfoById(id);
				List<SpOrderDetail> spOrderDetails = spOrderService.getDetailByOrderId2(info.getOrderId());
				List<ERPSpOrderOwnerDetail> details = erpSpOrderOwnerService.getOwnerDetail(info.getId() , SpOrderOwnerType.ITEM);
				for (ERPSpOrderOwnerDetail detail : details) {
					SpOrderDetail s= BeanUtil.toObject(SpOrderDetail.class , detail);
					s.setQuantity(detail.getCashQuantity());
					s.setPrice(new BigDecimal(0));
					s.setTotalPrice(new BigDecimal(0));
					spOrderDetails.add(s);
				}
				model.addAttribute("detail" ,spOrderDetails);
				model.addAttribute("info", info);
			}else{
				return "redirect:toWachMarketStockInfo.do?id="+list.get(0).getId();
			}
		}

		
		List<ERPWarehouse> list = eRPWarehouseService.getERPWarehouseLevel1All(supplier.getId());
		if(list==null||list.size()==0){
			return error("没有仓库!", model, request);
		}
		/**获取默认仓库仓位信息**/
		model.addAttribute("warhouseList", list);
		List<ERPWarehouse> list3 = eRPWarehouseService.getWarehouseLevel3(list.get(0).getId());
		model.addAttribute("warhouseList3", list3);

		return PATH + "add-sale-out";
	}
	/**
	 *
	 * @Title: toEditSaleOut
	 * @Description:编辑销售出库单
	 * @param @param stockInfoRo
	 * @param @return
	 * @return String    返回类型
	 *@author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@RequestMapping("/toEditSaleOut.do")
	public String toEditSaleOut(ERPMarketStockInfoRo stockInfoRo,HttpServletRequest request,Model model) throws Exception {
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			return "/login/index";
		}

		if(StringUtils.isEmpty(stockInfoRo.getId())){
			return error("销售出库单id为空!", model, request);
		}

		ERPMarketStockInfoVo stockInfoVo = eRPMarketStockInfoService.selectMarketStockVoById(stockInfoRo);
		model.addAttribute("stockInfoVo", stockInfoVo);
		List<ERPMarketStockDetailVo> detailVos = eRPMarketStockInfoService.selectMarketStockDetail(stockInfoVo);
		model.addAttribute("detailVos", detailVos);
		List<ERPWarehouse> list = eRPWarehouseService.getERPWarehouseLevel1All(supplier.getId());
		if(list==null||list.size()==0){
			return error("没有仓库!", model, request);
		}
		/**获取默认仓库仓位信息**/
		model.addAttribute("warhouseList", list);
		List<ERPWarehouse> list3 = eRPWarehouseService.getWarehouseLevel3(list.get(0).getId());
		model.addAttribute("warhouseList3", list3);

		if(!StringUtil.stringIsNullOrEmpty(stockInfoVo.getpId())) {
			SpOrderInfo info = spOrderService.getInfoById(stockInfoVo.getpId());
			List<SpOrderDetail> spOrderDetails = spOrderService.getDetailByOrderId2(info.getOrderId());

			List<ERPSpOrderOwnerDetail> details = erpSpOrderOwnerService.getOwnerDetail(info.getId() , SpOrderOwnerType.ITEM);
			for (ERPSpOrderOwnerDetail detail : details) {
				SpOrderDetail s= BeanUtil.toObject(SpOrderDetail.class , detail);
				s.setQuantity(detail.getCashQuantity());
				s.setPrice(new BigDecimal(0));
				s.setTotalPrice(new BigDecimal(0));
				spOrderDetails.add(s);
			}
			model.addAttribute("detail" ,spOrderDetails);
			model.addAttribute("info", info);
		}
		return PATH + "add-sale-out";
	}

	/**
	 * 
	* @Title: getSpOrderInfoList 
	* @Description:获取当前登陆批发商的销售订单
	* @param @param orderInfoRo
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getSpOrderInfoList.do")
	@ResponseBody
	public Object getSpOrderInfoList(SpOrderInfoMgRo orderInfoMgRo, HttpServletRequest request) {
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if (supplier == null) {
			// TODO 跳转到登陆页面
		}
		orderInfoMgRo.setSupplierId(supplier.getId());
		Pager<SpOrderInfo> pager = spOrderService.getSpOrderInfoList(orderInfoMgRo);
		pager.setFlag(true);
		return pager;
	}

	/**
	 * 
	* @Title: toSaleOutIndex 
	* @Description:跳转到销售出库页面
	* @param @param request
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toSaleOutIndex.do")
	@Token(save = true)
	public String toSaleOutIndex(HttpServletRequest request,ERPMarketStockInfoRo infoRo,Model model) {
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier==null){
			return "/login/index";
		}
		infoRo.setSupplierId(supplier.getId());
		String levelStr = request.getParameter("level");
		if(StringUtils.isNotEmpty(levelStr)){
			infoRo.setLevel(Byte.parseByte(levelStr));
			model.addAttribute("levelStr", levelStr);
		}else {
			infoRo.setLevel((byte)1);
			model.addAttribute("levelStr", 1);
		}
		String pagerIndexStr = request.getParameter("pageIndex");
		if(StringUtils.isNotEmpty(pagerIndexStr)){
			infoRo.setPageIndex(Integer.parseInt(pagerIndexStr));
			model.addAttribute("pagerIndexStr", pagerIndexStr);
		}else{
			infoRo.setPageIndex(1);
		}
		if(infoRo.getStartTime()!=null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(infoRo.getStartTime());
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			model.addAttribute("startTime", DateUtil.date2String(infoRo.getStartTime()));
		}
		if(infoRo.getEndTime()!=null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(infoRo.getEndTime());
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			model.addAttribute("endTime", DateUtil.date2String(infoRo.getEndTime()));
			calendar.add(Calendar.DATE, 1);
			infoRo.setEndTime(calendar.getTime());
		}
		if(StringUtils.isNotEmpty(infoRo.getKeyStr())){
			infoRo.setKeyStr(StringUtils.trim(infoRo.getKeyStr()));
			model.addAttribute("keyStr", StringUtils.trim(infoRo.getKeyStr()));
		}
		if(infoRo.getStockStatus()!=null){
			if(infoRo.getStockStatus().intValue()==-1){
				infoRo.setStockStatus(null);
				model.addAttribute("stockStatus", "-1");
			}else {
				model.addAttribute("stockStatus", infoRo.getStockStatus());
			}
		}else {
			model.addAttribute("stockStatus", "-1");
		}
		Pager<ERPMarketStockInfoVo> pager = eRPMarketStockInfoService.getSaleOutList(infoRo);
		List<ERPMarketStockInfoVo> list = pager.getList();
		model.addAttribute("list", list);
		pageUtil(infoRo.getPageIndex(), pager.getTotalSize(), infoRo.getPageSize(), request, model);
			return PATH + "sale-out";
	}
}
