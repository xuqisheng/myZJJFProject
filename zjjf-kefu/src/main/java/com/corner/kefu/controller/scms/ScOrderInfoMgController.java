package com.corner.kefu.controller.scms;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.MaOrderInfo;
import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.ScmsWarehouse;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.scms.MaOrderInfoMgRo;
import com.corner.kefu.beans.ro.scms.ScOrderInfoMgRo;
import com.corner.kefu.beans.vo.MaOrderInfoVo;
import com.corner.kefu.beans.vo.sc.ScOrderInfoMgVo;
import com.corner.kefu.config.SCMSConstants;
import com.corner.kefu.config.SessionConfig;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.ItemBaseService;
import com.corner.kefu.service.scms.MaOrderInfoMgService;
import com.corner.kefu.service.scms.ScOrderDetailMgService;
import com.corner.kefu.service.scms.ScOrderInfoMgService;
import com.corner.kefu.service.scms.ScmsItemMgService;
import com.corner.kefu.service.scms.WarehouseService;

/**
 * 
* @ClassName: ScOrderInfoMgController 
* @Description: 合单信息操作
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2016年1月20日 下午3:52:39 
*
 */
@Controller
@RequestMapping(value="/scms/scOrder")
public class ScOrderInfoMgController extends KefuBaseWebController{
	@Autowired
	ScOrderInfoMgService scOrderInfoMgService;
	@Autowired
	ScOrderDetailMgService scOrderDetailMgService;
	@Autowired
	ScmsItemMgService scmsItemMgService;
	@Autowired
	MaOrderInfoMgService maOrderInfoMgService;
	@Autowired
	ItemBaseService itemBaseService;
	@Autowired
	WarehouseService warehouseService;
	
	private static Logger logger = LoggerFactory.getLogger(ScOrderInfoMgController.class);
	
	
	/**
	 * 
	* @Title: listPage 
	* @Description: 分页查询订单信息 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/listPage.do")
	public String listPage(HttpServletRequest request, MaOrderInfoMgRo command , Model model) throws Exception{
		command.setPageSize(2);
		command.setSortName("kfStatus,managerStatus,warehouseStatus,totQuantity");
		command.setSortOrder("desc");
		if(command.getStatus() != 1 && command.getStatus() !=2 && command.getStatus() !=3)
			command.setStatus(0);
		Pager<MaOrderInfoVo> pager = maOrderInfoMgService.getPageList(command);
		Map<String, Object> detail = new HashMap<String, Object>();
		for (MaOrderInfoVo maOrderInfoVo : pager.getList()) {
			detail.put("maOrderInfoId", maOrderInfoVo.getId());
			detail.put("total", 1);	//部分要素需要汇总显示时加上
			maOrderInfoVo.setScOrderDetails(scOrderDetailMgService.findOrderDetailList(detail));
		}
		request.setAttribute("orderList", pager.getList());
		request.setAttribute("size", pager.getTotalSize());
		request.setAttribute("maOrderInfo", command);
		this.pageUtil(command.getPageIndex(), pager.getTotalSize(), command.getPageSize(), request, model);
		return SCMSConstants.ORDER_DEALER_SUMMARY;
	}
	
	@RequestMapping(value = "/goSummary.do")
	public String goSummary(HttpServletRequest request, ScOrderInfoMgRo command) {
		if("edit".equals(command.getAddOrDel())){
			request.setAttribute("orderInfo", scOrderInfoMgService.findOrderDetailSumByItemIdAndOrderId2(command));
			return SCMSConstants.DEALER_SUMMARY_DETAIL;
		}
		return SCMSConstants.ORDER_DEALER_SUMMARY;
	}
	
	/**
	 * @Title: listDetailPage 
	 * @Description: 分页查询订单信息 
	 * @param @param request
	 * @param @param command
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/listDetailPage.do")
	public String listDetailPage(HttpServletRequest request, ScOrderInfoMgRo command, Model model) {
		logger.info("列表查询开始："+JSONUtil.objectToJSONString(command));
		if(StringUtil.stringIsNullOrEmpty(command.getItemBaseId() , command.getMaOrderInfoId() )){
			return "error";
		}
		Pager<Map> pager = scOrderInfoMgService.getScOrderDetailPageList(command);
		request.setAttribute("orderInfo", scOrderInfoMgService.findOrderDetailSumByItemIdAndOrderId2(command));
		request.setAttribute("size", pager.getTotalSize());
		request.setAttribute("list", pager.getList());
		request.setAttribute("order", command);
		this.pageUtil(command.getPageIndex(), pager.getTotalSize(), command.getPageSize(), request, model);
		return SCMSConstants.DEALER_SUMMARY_DETAIL;
	}
	/**
	 * @Title: listDetailPage 
	 * @Description: 分页查询订单信息 
	 * @param @param request
	 * @param @param command
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/printOrder.do")
	public String printOrder(HttpServletRequest request, String id) {
		ScOrderInfoMgRo command = new ScOrderInfoMgRo();
		command.setMaOrderInfoId(id);
		command.setPageIndex(1);
		command.setPageSize(10000);
		Pager<ScOrderInfoMgVo> pager = scOrderInfoMgService.getSupplerOrderDetailByOrder2(command);
		request.setAttribute("orderInfo", scOrderInfoMgService.findOrderDetailSumByItemIdAndOrderId2(command));
		request.setAttribute("list", pager.getList());
		request.setAttribute("order", command);
		return SCMSConstants.DEALER_SUMMARY_PRINT;
	}
	
	/**
	 * 
	 * @Title: orderSubmit 
	 * @Description: 订单提交
	 * @param @param request
	 * @param @param command
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/orderSubmit.do")
	@ResponseBody
	public Object orderSubmit(HttpServletRequest request, String id) throws Exception{
		logger.info("订单提交id：{}" , id);
		if(StringUtil.stringIsNullOrEmpty(id))
			return ResponseUtils.sendMsg(false,SCMSConstants.IS_NOT_NULL);
		/**订单金额校验**/
		MaOrderInfo maOrderInfo = maOrderInfoMgService.selectByPrimaryKey(id);
		if(maOrderInfo == null)
			return ResponseUtils.sendMsg(false , "查询到该笔订单");
		if(maOrderInfo.getKfStatus() == 3){
			return ResponseUtils.sendMsg(false , "该订单已提交！");
		}
		BigDecimal goodsPrice = new BigDecimal(0);
		BigDecimal outOfPrice = new BigDecimal(0);
		BigDecimal markPrice = new BigDecimal(0);
		BigDecimal zfee = new BigDecimal(0);
		/**订单数据补充**/
		Map<String, Object> detail = new HashMap<String, Object>();
		detail.put("maOrderInfoId", maOrderInfo.getId());
		List<ScOrderInfo> infos = scOrderInfoMgService.selectScOrderInfoList(detail);
		/**获取子订单内容**/
		for (ScOrderInfo scOrderInfo : infos) {
			BigDecimal areaTotalPrice = new BigDecimal(0);
			detail.put("orderId2", scOrderInfo.getOrderId());
			List<ScOrderDetail> scOrderDetails = scOrderDetailMgService.findOrderDetailList(detail);
			for (ScOrderDetail scOrderDetail : scOrderDetails) {
				ScmsItem scmsItem = scmsItemMgService.selectByPrimaryKey(scOrderDetail.getItemId());
				scOrderDetail.setAreaPrice(scmsItem.getAreaPrice());
				scOrderDetail.setAreaTotalPrice(new BigDecimal(scOrderDetail.getQuantity()).multiply(scmsItem.getAreaPrice()));
				scOrderDetail.setMarketPrice(scmsItem.getMarketPrice());
				scOrderDetail.setName(scmsItem.getGoodName());
				areaTotalPrice = areaTotalPrice.add(scOrderDetail.getAreaTotalPrice());
				try {
					Map<String, Object> itemBase= itemBaseService.selectByPrimaryKey(scmsItem.getItemBaseId());
					if(itemBase.get("pkg") != null && !"".equals(itemBase.get("pkg")))
						scOrderDetail.setPkg("" + itemBase.get("pkg"));
					if(itemBase.get("brand") != null && !"".equals(itemBase.get("brand")))
						scOrderDetail.setBrand("" + itemBase.get("brand"));
					scOrderDetail.setWayCode("" + itemBase.get("mdseId"));
					scOrderDetail.setBarCode("" + itemBase.get("barCode"));
					if(itemBase.get("imgS") != null && !"".equals(itemBase.get("imgS")))
						scOrderDetail.setImg("" + itemBase.get("imgS"));
					if(itemBase.get("spec") != null && !"".equals(itemBase.get("spec")))
						scOrderDetail.setSpec("" + itemBase.get("spec"));
				} catch (Exception e) {
					return ResponseUtils.sendMsg(false , "商品库数据有误！");
				}
				outOfPrice = outOfPrice.add(scOrderDetail.getAreaTotalPrice());
				goodsPrice = goodsPrice.add(scOrderDetail.getTotalPrice());
				markPrice = markPrice.add(scOrderDetail.getMarketPrice().multiply(new BigDecimal(scOrderDetail.getQuantity())));
				ModelMsg msg = scOrderDetailMgService.updateOrderDetail(scOrderDetail);
				if(!msg.isSuccess())
					return ResponseUtils.sendMsg(false , msg.getMessage());
			}
			scOrderInfo.setOutOfPrice(areaTotalPrice);	//更新进货价
			scOrderInfoMgService.updateByPrimaryKeySelective(scOrderInfo);
		}
		
		BigDecimal zmaoli = goodsPrice.subtract(outOfPrice);
		ScmsWarehouse scmsWarehouse =warehouseService.findWareHouseById(maOrderInfo.getWarehouseId());
		if(scmsWarehouse == null)
			return ResponseUtils.sendMsg(false , "仓库信息不存在");
		maOrderInfo.setGoodsPrice(goodsPrice);
		maOrderInfo.setOutOfPrice(outOfPrice);
		maOrderInfo.setMarkePrice(markPrice);
		maOrderInfo.setZfee(goodsPrice);
		maOrderInfo.setZmaoli(zmaoli);
		maOrderInfo.setConsignee(scmsWarehouse.getBranderName());
		maOrderInfo.setMobile(scmsWarehouse.getBranderTel());
		maOrderInfo.setWarehouseName(scmsWarehouse.getName());
		maOrderInfo.setAddress(scmsWarehouse.getHouseAddress());
		maOrderInfo.setKfSubmitTime(new Date());
		Subject subject = SecurityUtils.getSubject();
		CustomerService user =(CustomerService)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
		if(user == null){
			return ResponseUtils.sendMsg(false , "该用户已失效，请重新登录");
		}
		maOrderInfo.setKfId(user.getId());
		maOrderInfo.setKfName(user.getNickName());
		maOrderInfo.setKfStatus(new Byte("3"));	//已提交
		
		ModelMsg msg = maOrderInfoMgService.updateByPrimaryKeySelective(maOrderInfo);
		if(!msg.isSuccess())
			return ResponseUtils.sendMsg(false , msg.getMessage());
		return ResponseUtils.sendMsg(true , "订单提交成功");
	}
}

