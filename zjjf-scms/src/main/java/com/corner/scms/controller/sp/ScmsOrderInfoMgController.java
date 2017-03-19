package com.corner.scms.controller.sp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.corner.core.beans.*;
import com.corner.core.enums.SpOrderOwnerType;
import com.corner.scms.service.erp.ERPMarketStockService;
import com.corner.scms.service.erp.ERPSpOrderOwnerService;
import com.corner.scms.utils.BeanUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.ScmsOrderInfoMgCondition;
import com.corner.scms.beans.ro.SpOrderInfoRo;
import com.corner.scms.beans.vo.OrderInfoVo;
import com.corner.scms.beans.vo.SpOrderDetailVo;
import com.corner.scms.beans.vo.SpOrderListVo;
import com.corner.scms.beans.vo.base.LogInVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sp.ReminderService;
import com.corner.scms.service.sp.ScmsOrderInfoMgService;
import com.corner.scms.service.sp.StoreService;

@Controller
@RequestMapping(value="/scms/orderctl")
public class ScmsOrderInfoMgController extends ScmsBaseWebController{

	private static Logger logger = LoggerFactory.getLogger(ScmsOrderInfoMgController.class);
	
	@Autowired
	ReminderService reminderService;
	@Autowired
	StoreService storeService;
	@Autowired
	ERPSpOrderOwnerService erpSpOrderOwnerService;
	@Autowired
	ScmsOrderInfoMgService scmsOrderInfoMgService;
	@Autowired
	ERPMarketStockService erpMarketStockService;

	private final String ORDER_LIST = "order/offline";
	private final String ORDER_ADD = "order/add-order";
	private final String LOGIN_URL="/login/index";
	
	@RequestMapping(value = "/listPage.do")
	public String list(HttpServletRequest request,Model model, ScmsOrderInfoMgCondition command) {
		model.addAttribute("staging", command.getStaging());
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			LogInVo logInVo = new LogInVo();
			Supplier user = getCurrentUser(Supplier.class, request);
			if(user == null){
				return LOGIN_URL;
			}else{
				logInVo.setUserId(user.getId());
				logInVo.setUserName(user.getSupplierName());
			}
			request.setAttribute("logInVo", logInVo);
			if("1".equals(command.getStaging())){
				return ORDER_ADD;
			}else{
				return ORDER_LIST;
			}
		}else{
			return LOGIN_URL;
		}
	}
	
	/**
	 * 
	 * @Title: list 
	 * @Description: 查询订单列表（分页查询） 
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/list.do")
	@ResponseBody
	public Object list(HttpServletRequest request, ScmsOrderInfoMgCondition command, Model model) {
		try {
			logger.info("订单列表查询开始");
			command.setSortName("getOrderTime");
			command.setSortOrder("desc");
			Supplier supplier = getCurrentUser(Supplier.class, request);
			command.setSupplierId(supplier.getId());
			
			String getOrderTimeStart = command.getGetOrderTimeStart();
			String getOrderTimeEnd = command.getGetOrderTimeEnd();
			if(!StringUtil.stringIsNullOrEmpty(getOrderTimeStart,getOrderTimeEnd)){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = new   GregorianCalendar();
				c.setTime(dateFormat.parse(getOrderTimeEnd));
				c.add(Calendar.DATE, 1);
				command.setGetOrderTimeEnd(dateFormat.format(c.getTime()));
				c.setTime(dateFormat.parse(getOrderTimeStart));
				c.set(Calendar.DATE , c.get(Calendar.DATE) - 1);
				command.setGetOrderTimeStart(getOrderTimeStart);
			}
			Pager<ScmsOrderInfo> pager = scmsOrderInfoMgService.getScmsOrderInfoPageList(command);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}

	/**
	 * 
	 * @Title: selectByOrderId 
	 * @Description: 查询单笔订单信息通过orderId 
	 * @param @param request
	 * @param @param command
	 * @param @param model
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/selectByOrderId.do")
	@ResponseBody
	public Object selectByOrderId(HttpServletRequest request, ScmsOrderInfoMgCondition command, Model model) {
		logger.info("查询单笔订单开始：orderId"+command.getOrderId());
		if(StringUtil.stringIsNullOrEmpty(command.getOrderId())){
			logger.info("未上送orderId");
			return ResponseUtils.sendMsg(false,null);
		}
		command= scmsOrderInfoMgService.selectByOrderId(command.getOrderId());
		if(command == null){
			logger.info("未查询到数据,请联系技术");
			return ResponseUtils.sendMsg(false,null);
		}
		List<ScmsOrderDetail> orderDetails = scmsOrderInfoMgService.getDetailByOrderId(command.getOrderId());
		command.setScmsOrderDetails(orderDetails);
		command.setStoreType(command.getCol1());
		return ResponseUtils.sendMsg(true, command);
	}

	/**
	 * 
	 * @Title: update 
	 * @Description: 修改订单信息
	 * @param @param request
	 * @param @param info
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/update.do")
	@ResponseBody
	public Object update(HttpServletRequest request, ScmsOrderInfoMgCondition info) {
		if(info == null || info.getOrderId() == null){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			ModelMsg msg = scmsOrderInfoMgService.updateByPrimaryKeySelective(info);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,msg.getData());				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}
	/**
	 * 
	 * @Title: delete 
	 * @Description: 删除订单信息
	 * @param @param request
	 * @param @param info
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/delete.do")
	@ResponseBody
	public Object delete(HttpServletRequest request, ScmsOrderInfoMgCondition info) {
		if(info == null || info.getId() == null){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}else{
			ScmsOrderInfo orderInfo = scmsOrderInfoMgService.selectById(info.getId());
			if(orderInfo == null)
				return ResponseUtils.sendMsg(false,"订单信息不存在");
			info.setOrderId(orderInfo.getOrderId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderInfo.getOrderId());
			map.put("actype", "1");
			ModelMsg msg2 = scmsOrderInfoMgService.updatePlantItemStock(map);
			if(!msg2.isSuccess())
				return ResponseUtils.sendMsg(msg2.isSuccess(),msg2.getMessage());
			ModelMsg msg = scmsOrderInfoMgService.deleteByPrimaryKeySelective(info);
			return ResponseUtils.sendMsg(msg.isSuccess(),msg.getMessage());		
		}
	}

	/**
	 * @Title: insert 
	 * @Description: 插入新的订单信息 
	 * @param @param request
	 * @param @param info
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/insert.do")
	@ResponseBody
	public Object insert(HttpServletRequest request, ScmsOrderInfoMgCondition info) {
		logger.info("店主(收货人)姓名为：{}", info.getConsignee());
		logger.info("店主(收货人)手机号为：{}", info.getMobile());
		logger.info("店主(收货人)地址为：{}", info.getAddress());
		logger.info("订单提交信息为：{}", JSONUtil.objectToJSONString(info));
		if(info == null || StringUtil.stringIsNullOrEmpty(info.getJsType()))
			return ResponseUtils.sendMsg(false,"确少结算方式");
		else if(StringUtil.stringIsNullOrEmpty(info.getMobile(),info.getStoreName(),info.getStoreType(),info.getAddress()))
			return ResponseUtils.sendMsg(false,"缺少收件人信息");
		else if(info.getItemBaseId() == null || info.getItemBaseId().length == 0)
			return ResponseUtils.sendMsg(false,"未录入商品信息");
		else{
			for (int i = 0; i < info.getItemBaseId().length; i++) {
//				if(StringUtil.stringIsNullOrEmpty(info.getItemBaseId()[i])&&StringUtil.stringIsNullOrEmpty(info.getPrice()[i])&&StringUtil.stringIsNullOrEmpty(info.getItemBaseName()[i]) && StringUtil.stringIsNullOrEmpty(info.getQuantity()[i])){
//
//				}
				if(StringUtil.stringIsNullOrEmpty(info.getItemBaseId()[i]))
					return ResponseUtils.sendMsg(false,"缺少商品信息,请完善");
				if(StringUtil.stringIsNullOrEmpty(info.getPrice()[i]))
					return ResponseUtils.sendMsg(false,"缺少商品价格,请完善");
				if(StringUtil.stringIsNullOrEmpty(info.getItemBaseName()[i]))
					return ResponseUtils.sendMsg(false,"缺少缺少商品名称,请完善");
				if(StringUtil.stringIsNullOrEmpty(info.getQuantity()[i]))
					return ResponseUtils.sendMsg(false,"缺少商品数量,请完善");
			}
			Supplier supplier = getCurrentUser(Supplier.class, request);
			info.setSupplierId(supplier.getId());	//一级批发商编号

			try {
				ModelMsg msg = scmsOrderInfoMgService.insert(info);
				if(msg.isSuccess())
					return ResponseUtils.sendMsg(true,msg.getData());
				return ResponseUtils.sendMsg(false,msg.getMessage());
			}catch (Exception e){
				logger.error(e.getMessage());
				return ResponseUtils.sendMsg(false,e.getMessage());
			}
		}
			
	}

	/**
	 * 
	 * @Title: toDayAll 
	 * @Description: 今日订单 
	 * @param @param request
	 * @param @param info
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/toDayAll.do")
	@ResponseBody
	public Object toDayAll(HttpServletRequest request, Model model) {
		logger.info("查询今日订单信息："+DateUtil.getDate());
		int orderTotal = 0;
		BigDecimal orderTotalPrice = new BigDecimal(0.00);
		int scmsOrderTotal = 0;
		BigDecimal scmsOrderTotalPrice = new BigDecimal(0.00);

		Supplier supplier = getCurrentUser(Supplier.class, request);
		if(supplier == null || "".equals(supplier.getId())){
			return ResponseUtils.sendMsg(false , "用户未登录");
		}
		
		Map<String, Object> order = scmsOrderInfoMgService.getOrderTodayAll(supplier.getId());
		orderTotal = Integer.parseInt(order.get("total") + "");
		if(order.get("totalPrice") != null)
			orderTotalPrice = new BigDecimal(order.get("totalPrice").toString());
		logger.info("查询orderInfo(转角)订单信息----订单数："+orderTotal+",金额："+orderTotalPrice);

		Map<String, Object> scmsOrder = scmsOrderInfoMgService.getScmsOrderTodayAll(supplier.getId());
		scmsOrderTotal = Integer.parseInt(scmsOrder.get("total") + "");
		if(scmsOrder.get("totalPrice") != null)
			scmsOrderTotalPrice = new BigDecimal(scmsOrder.get("totalPrice").toString());
		logger.info("查询scmsOrderInfo(线下)订单信息----订单数："+scmsOrderTotal+",金额："+scmsOrderTotalPrice);

		order = new HashMap<String, Object>();
		order.put("priceCollect", orderTotalPrice.add(scmsOrderTotalPrice));	//今日销售总额
		order.put("orderCollect", orderTotal + scmsOrderTotal);	//今日交易笔数
		order.put("orderTotal", orderTotal);	//转角订单笔数
		order.put("scmsOrderTotal", scmsOrderTotal);	//线下订单笔数
		order.put("orderTotalPrice", orderTotalPrice);	//转角交易金额
		order.put("scmsOrderTotalPrice", scmsOrderTotalPrice);	//线下交易金额

		return ResponseUtils.sendMsg(true, order);
	}

	/**
	 * 
	 * @Title: countOrder 
	 * @Description: 计算订单金额 
	 * @param @param request
	 * @param @param info
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/countOrder.do")
	@ResponseBody
	public Object countOrder(HttpServletRequest request, ScmsOrderInfoMgCondition info) {
		if(info.getPrice() == null || info.getQuantity() == null){
			return ResponseUtils.sendMsg(false,"请正确录入商品信息");
		}else if(info.getPrice().length == 0 || info.getQuantity().length == 0){
			return ResponseUtils.sendMsg(false , "请正确录入商品信息");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		BigDecimal totalPrice = new BigDecimal("0.00");
		int total = 0;
		for (int i = 0; i < info.getQuantity().length; i++) {
			if(StringUtil.stringIsNullOrEmpty(info.getQuantity()[i]))
				continue;
			if(StringUtil.stringIsNullOrEmpty(info.getPrice()[i]))
				continue;
			
			total += Integer.parseInt(info.getQuantity()[i]);
			totalPrice = totalPrice.add(new BigDecimal(info.getPrice()[i]).multiply(new BigDecimal(info.getQuantity()[i])));
		}
		if(total == 0){
			return ResponseUtils.sendMsg(false);
		}
		map.put("total", total);
		map.put("totalPrice", totalPrice);
		return ResponseUtils.sendMsg(true,map);
	}
	@RequestMapping(value = "/test.do")
	public String test(HttpServletRequest request, Model model) {
		
		
		return "index/index";
	}
	
	/**
	 * 
	* @Title: addScmsSpSalePrice 
	* @Description: 添加出货价记录 
	* @param @param request
	* @param @param info
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/addScmsSpSalePrice.do")
	@ResponseBody
	public Object addScmsSpSalePrice(HttpServletRequest request,ScmsOrderInfoMgCondition info) {
		Supplier supplier = getCurrentUser(Supplier.class, request);
		info.setSupplierId(supplier.getId());	//一级批发商编号
		ModelMsg msg = scmsOrderInfoMgService.addScmsSpSalePrice(info);
		if(msg != null && msg.isSuccess()){
			return ResponseUtils.sendMsg(true,msg.getData());				
		}else{				
			return ResponseUtils.sendMsg(false,msg.getData());				
		}
	}
	/**
	 * 
	* @Title: addScmsSpSalePrice 
	* @Description: 修改客户类型重新计算金额 
	* @param @param request
	* @param @param info
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/updateStoreType.do")
	@ResponseBody
	public Object updateStoreType(HttpServletRequest request,ScmsOrderInfoMgCondition info) {
		Supplier supplier = getCurrentUser(Supplier.class, request);
		info.setSupplierId(supplier.getId());	//一级批发商编号
		ModelMsg msg = scmsOrderInfoMgService.updateStoreType(info);
		if(msg != null && msg.isSuccess()){
			return ResponseUtils.sendMsg(true,msg.getData());				
		}else{				
			return ResponseUtils.sendMsg(false,msg.getData());				
		}
	}
	//转角订单  
	/***************************************************************************************************************/
	//转角订单  首页 
	@RequestMapping(value = "/GetSpOrderInfos.do")
	public String getSpOrderInfos(SpOrderInfoRo spOrderInfoRo, HttpServletRequest request, Model model) {
		{
			
			return "order/index";
		}

	}
	
	//日期加减多少天
	private Date dateAdd(Date date,int number){
		Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DAY_OF_YEAR,number);//日期加10天
        Date dt1=rightNow.getTime();
        return dt1;
	}


	@RequestMapping(value = "/GetSpOrderInfo.do")
	public String GetSpOrderInfo(SpOrderInfoRo  spOrderInfoRo, HttpServletRequest request, Model model) throws Exception{
		if (StringUtil.stringIsNullOrEmpty(spOrderInfoRo.getStoreid())) {
			logger.info("请求的参数有误");
			return null;
		}
		OrderInfoVo spOrderInfo=this.scmsOrderInfoMgService.findByOrId(spOrderInfoRo.getStoreid());
		if (spOrderInfo == null) {
			logger.info("id为{}的订单不存在",spOrderInfoRo.getStoreid());
			return null;
		}
//		List<ERPMarketStockInfo> list = erpMarketStockService.getInfoByPId(spOrderInfo.getId() , null);
//		if(list != null && list.size() > 0){
//			return "redirect:/scms/erpMarket/toWachMarketStockInfo.do?id="+list.get(0).getId();
//		}

		//查询业务员手机号
//		String yewuyuanmobile = scmsOrderInfoMgService.getyewuyuanmobile(spOrderInfo.getStoreId());
		// 订单详细
		List<SpOrderDetailVo> orderDetails = scmsOrderInfoMgService.getZjjfOrderDetail(spOrderInfoRo.getStoreid());
		BigDecimal bigDecimal = new BigDecimal(0.00);
		int ordernum = 0;
		for (SpOrderDetail orderDetail : orderDetails) {
			bigDecimal = bigDecimal.add(orderDetail.getTotalPrice());
			ordernum += orderDetail.getQuantity();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderInfo", spOrderInfo);
		map.put("ordernum", ordernum);
		map.put("total", bigDecimal);
		map.put("orderDetail", orderDetails);
		map.put("linkOrderNo", orderDetails==null||orderDetails.size()==0?null:orderDetails.get(0).getOrderId());
//		map.put("yewuyuanmobile", yewuyuanmobile);
		model.addAttribute("map", map);

		model.addAttribute("ownerDetail" , erpSpOrderOwnerService.getOwnerDetailToVo(spOrderInfo.getId()));
		return "order/detail";
	}

	
	//转角订单  查询所有   ajax
		@RequestMapping(value = "/GetSpOrderInfoslist.do")
		@ResponseBody
		public Object GetSpOrderInfoslist(SpOrderInfoRo spOrderInfoRo, HttpServletRequest request, Integer pageIndex){
//			
// 			if (spOrderInfoRo.getStatus() !=null&&spOrderInfoRo.getStatus() <= 1) {
//				spOrderInfoRo.setStatus((byte) -10);
//			}
//			if(spOrderInfoRo.getStatus() !=null&&spOrderInfoRo.getStatus()==10){
//				spOrderInfoRo.setStatus(null);
//			}
			Supplier user = getCurrentUser(Supplier.class, request);
			if(user==null){
				return null;
			}
			spOrderInfoRo.setSupplierId(user.getId());
			// 分页
			if(pageIndex!=null){
				spOrderInfoRo.setPageIndex(pageIndex+1);
			}else{
				spOrderInfoRo.setPageIndex(1);
			}
			if(spOrderInfoRo.getPageSize()<=0)spOrderInfoRo.setPageSize(10);
			
			// 按状态排序
			if(spOrderInfoRo.getStatus()!=null){
				switch (spOrderInfoRo.getStatus()){
					case 10 :
						spOrderInfoRo.setSortName("e.addTime");
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
			}
			if(spOrderInfoRo.getDateTo()!=null){
				spOrderInfoRo.setDateTo(dateAdd(spOrderInfoRo.getDateTo(), 1));//日期加1天  满足sql 查询
			}
			if(spOrderInfoRo.getSupportmetho()!=null&&spOrderInfoRo.getSupportmetho()==2){
				spOrderInfoRo.setSortName("addTime");
				spOrderInfoRo.setSortOrder("DESC");
			}else if(spOrderInfoRo.getSupportmetho()!=null&&(spOrderInfoRo.getSupportmetho()==1||spOrderInfoRo.getSupportmetho()==3||spOrderInfoRo.getSupportmetho()==4)){
				spOrderInfoRo.setSortName("supportTime");
				spOrderInfoRo.setSortOrder("DESC");
			}
			if(!StringUtil.stringIsNullOrEmpty(spOrderInfoRo.getStoreid())){
				spOrderInfoRo.setStoreid(spOrderInfoRo.getStoreid().trim());
			}
			// 查询订单
			List<SpOrderInfo> spOrderInfos = scmsOrderInfoMgService.getSpOrderInfospc(spOrderInfoRo);

			Integer count = scmsOrderInfoMgService.selectSpOrderCountOfStatus(spOrderInfoRo);
			Pager<SpOrderInfo> result=new Pager<SpOrderInfo>();
			result.setTotalSize(count);
			result.setList(spOrderInfos);
			return result;
		}
	
	/**
	 * 供应商更新订单的状态
	 * 
	 * @param orderid
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/updateStatusATime.do")
	public ModelAndView updateStatusATime(String orderid, Byte status, Byte logisticsStatus,Model model,HttpServletRequest request) {
		try {
			if (StringUtil.stringIsNullOrEmpty(orderid)) {
				model.addAttribute("message", "参数错误");
				return new ModelAndView("/error");
			}
			//获得批发商对象
			Supplier supplier = getCurrentUser(Supplier.class, request);
			SpOrderInfoRo ro = new SpOrderInfoRo();
			ro.setStoreid(orderid);
			//根据条件查询订单
			OrderInfoVo sp1 = scmsOrderInfoMgService.findByOrId(ro.getStoreid());
			if(sp1 != null && sp1.getStatus() == 6){//已取消
				model.addAttribute("message", "该订单已取消");
				return new ModelAndView("/error");
			}
			logger.debug("该笔订单信息", sp1);
			SpOrderInfo sp = new  SpOrderInfo();
			sp.setId(sp1.getId());
			sp.setLevel(sp1.getLevel());
			sp.setpId(sp1.getpId());
			sp.setOrderId(orderid);
			if (status != null) {
				sp.setStatus(status);
				Date date = new Date();
				switch (status) {
				case (byte) 3:
					//初始化提单时间
					sp.setGetOrderTime(date);
					break;
				case (byte) 4:
					//初始化打印时间
					sp.setPrintTime(date);
					break;
				case (byte) 5:
					//初始化送达时间
					sp.setAckTime(date);
					break;
				}
			}
			if (logisticsStatus != null) {
				sp.setLogisticsStatus(logisticsStatus);
				Date date = new Date();
				switch (logisticsStatus) {
					case (byte) 1:
						//初始化发货时间
						sp.setWhSendTime(date);
						break;
					case (byte) 4:
						//初始化送达时间
						sp.setWhAckTime(date);
						break;
				}
			}
			//更新订单
			boolean result = scmsOrderInfoMgService.updateOrder(sp);
			if (result) {
//				if(status==4){
//					//更新催单状态
//					//1:先查有没有催单的信息
//					Map<String, Object> map = new HashMap<String, Object>();
//					map.put("spId", supplier.getId());
//					map.put("orderId", orderid);
//					int num = reminderService.getReminderByOrderIdAndSpId(map);
//					if(num>0){
//						map.put("processStatus", Byte.parseByte("1"));
//						reminderService.updateReminderStatusByOrderIdAndSpId(map);
//					}
//				}
				
				//重定向
				return new ModelAndView("redirect:GetSpOrderInfo.do?storeid=" + orderid);
			}
		} catch (Exception e) {
			logger.error("ScmsOrderInfoMgController's method updateStatusATime has an error:{}", e);
			model.addAttribute("" +
					"", e.getMessage());
			//重定向
			return new ModelAndView("/error");

		}
			return null;
	}

	//修改店铺备注地址
	@RequestMapping("addAddressRemark.do")
	@ResponseBody
	public Object addAddressRemark(Integer storeId,String addressRemark){
		if(storeId != null){
			try {
				boolean flag = storeService.addAddressRemark(storeId,addressRemark);
				if(flag){
					return ResponseUtils.sendMsg(true);
				}
				return ResponseUtils.sendMsg(false);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseUtils.sendMsg(false);
			}
		}else{
			return ResponseUtils.sendMsg(false);
		}
	}
	
	@RequestMapping("getCautionOrder.do")
	@ResponseBody
	public Object getCautionOrder(HttpServletRequest request){
		Supplier user = getCurrentUser(Supplier.class, request);
		if(user==null){
			return ResponseUtils.sendMsg(false);
		}
		try {
			List<SpOrderListVo> spOrderInfoList = scmsOrderInfoMgService.getCautionOrder(user.getId());
			return ResponseUtils.sendMsg(true, spOrderInfoList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false);
		}
		
	}

}

